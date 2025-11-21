package Ej5;

import javax.crypto.Cipher;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;

public class Cliente {
    public static void main(String[] args) throws Exception {
        Socket conexion = new Socket("localhost", 5500);
        ObjectOutputStream os = new ObjectOutputStream(conexion.getOutputStream());
        ObjectInputStream is = new ObjectInputStream(conexion.getInputStream());

        KeyPair clave = generarClaves();
        os.writeObject(clave.getPublic());
        byte[] cifrado = (byte[]) is.readObject();
        String msjCif = new String(cifrado);
        System.out.println("Recibido: " + msjCif);
        System.out.println("Mensaje descifrado = " + descifrar(cifrado, clave.getPrivate()));

        os.close();
        conexion.close();
    }

    private static String descifrar(byte[] msg, PrivateKey clave) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, clave);
        return new String(cipher.doFinal(msg));
    }

    private static KeyPair generarClaves() throws Exception {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048);
        return gen.generateKeyPair();
    }
}


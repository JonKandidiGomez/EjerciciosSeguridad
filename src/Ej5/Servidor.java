package Ej5;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;

public class Servidor {
    public static void main(String[] args) throws Exception {
        ServerSocket servidor = new ServerSocket(5500);
        Socket cliente = servidor.accept();
        ObjectOutputStream os = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream is = new ObjectInputStream(cliente.getInputStream());

        PublicKey clave = (PublicKey) is.readObject();
        String msj = "Mensaje de cifrado asimétrico";
        byte[] cifrado = cifrar(msj, clave);
        os.writeObject(cifrado);


        cliente.close();
        servidor.close();
    }

        public static byte[] cifrar(String msg, PublicKey clave) throws Exception {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, clave);
            return cipher.doFinal(msg.getBytes());
        }
}

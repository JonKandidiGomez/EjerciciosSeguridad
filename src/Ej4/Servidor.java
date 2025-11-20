package Ej4;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws Exception {
        ServerSocket servidor = new ServerSocket(5500);
        Socket cliente = servidor.accept();
        ObjectInputStream is = new ObjectInputStream(cliente.getInputStream());

        SecretKey clave = (SecretKey) is.readObject();
        byte[] cifrado = (byte[]) is.readObject();

        System.out.println("Mensaje descifrado = " + descifrar(cifrado, clave));

        cliente.close();
        servidor.close();
    }

    public static String descifrar(byte[] cifrado, SecretKey clave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, clave);
        return new String(cipher.doFinal(cifrado));
    }
}

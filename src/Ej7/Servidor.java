package Ej7;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 6000;
        System.setProperty("javax.net.ssl.keyStore", "/home/149fa03@arriagainfo.local/Escritorio/AlmacenSSL/AlmacenSSL.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "1234567");
        SSLServerSocketFactory sfact = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket servidorSSL;

        KeyGenerator kg;
        try {
            kg = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        kg.init(128);
        SecretKey clave = kg.generateKey();

        try {
            servidorSSL = (SSLServerSocket) sfact.createServerSocket(puerto);
            SSLSocket cliente = (SSLSocket) servidorSSL.accept();
            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());

            oos.writeObject(clave);
            byte[] mCifrado = (byte[]) ois.readObject();
            String mDescifrado = descifrar(mCifrado, clave);

            System.out.println("Mensaje recibido = " + mDescifrado);

            servidorSSL.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String descifrar(byte[] cifrado, SecretKey clave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, clave);
        return new String(cipher.doFinal(cifrado));
    }
}

package Ej7;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class Cliente {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String host = "localhost";
        int puerto = 6000;
        System.setProperty("javax.net.ssl.trustStore", "/home/149fa03@arriagainfo.local/Escritorio/AlmacenSSL/UsuarioAlmacenSSL");
        System.setProperty("javax.net.ssl.trustStorePassword", "890123");
        SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket cliente;
        try {
            cliente = (SSLSocket) sfact.createSocket(host, puerto);
            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());

            SecretKey clave = (SecretKey) ois.readObject();
            System.out.println("Escribe un mensaje para el servidor");
            String msn = br.readLine();
            byte[] mCifrado = cifrar(msn, clave);

            String msc = new String(mCifrado);

            oos.writeObject(mCifrado);
            System.out.println("Mensaje cifrado enviado al servidor = " + msc);

            cliente.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] cifrar(String texto, SecretKey clave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, clave);
        return cipher.doFinal(texto.getBytes());
    }
}

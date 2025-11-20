package Ej4;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Socket conexion = new Socket("localhost", 5500);
        ObjectOutputStream os = new ObjectOutputStream(conexion.getOutputStream());

        SecretKey clave = null;
        byte[] cifrado = null;

        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            clave = kg.generateKey();
            String original = "Mi mensaje";
            cifrado = cifrar(original, clave);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        os.writeObject(clave);
        os.writeObject(cifrado);

        os.close();
        conexion.close();
    }

    public static byte[] cifrar(String texto, SecretKey clave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, clave);
        return cipher.doFinal(texto.getBytes());
    }
}

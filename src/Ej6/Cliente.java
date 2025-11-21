package Ej6;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.*;

public class Cliente {
    public static void main(String[] args) throws Exception {
        Socket conexion = new Socket("localhost", 5500);
        ObjectOutputStream os = new ObjectOutputStream(conexion.getOutputStream());

        KeyPair clave = generarClaves();
        Signature sg = Signature.getInstance("SHA256withRSA");
        sg.initSign(clave.getPrivate());

        String msj = "Mensaje de prueba de firma digital.";
        sg.update(msj.getBytes());
        byte[] firmaDigital = sg.sign();

        os.writeObject(clave.getPublic());
        os.writeObject(msj);
        os.writeObject(firmaDigital);
        System.out.println("Clave publica mensaje y firma enviados");

        os.close();
        conexion.close();
    }

    private static KeyPair generarClaves() throws Exception {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048);
        return gen.generateKeyPair();
    }
}
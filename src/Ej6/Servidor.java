package Ej6;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PublicKey;
import java.security.Signature;

public class Servidor {
    public static void main(String[] args) throws Exception {
        ServerSocket servidor = new ServerSocket(5500);
        Socket cliente = servidor.accept();
        ObjectOutputStream os = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream is = new ObjectInputStream(cliente.getInputStream());

        Signature sg = Signature.getInstance("SHA256withRSA");

        PublicKey clPublica = (PublicKey) is.readObject();
        String msj = (String) is.readObject();
        byte[] firmaDigital = (byte[]) is.readObject();
        sg.initVerify(clPublica);
        sg.update(msj.getBytes());
        boolean check = sg.verify(firmaDigital);

        if (check) {
            System.out.println("FIRMA VERIFICADA CON CLAVE PÚBLICA.");
        } else {
            System.out.println("FIRMA NO VERIFICADA");
        }

        cliente.close();
        servidor.close();
    }
}

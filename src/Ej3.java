import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Ej3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion = 0;
        String usr = null;
        String pw = null;
        byte[] usrDigest = null;
        byte[] pwDigest = null;

        while(opcion != 3) {
            System.out.println("""
                    1. Registrarse
                    2. Iniciar sesión
                    3. Salir""");
            opcion = Integer.parseInt(br.readLine());

            if (opcion == 1) {
                System.out.println("Nombre de usuasrio:");
                usr = br.readLine();
                System.out.println("Contraseña:");
                pw = br.readLine();

                try {
                    MessageDigest md = MessageDigest.getInstance("SHA-256");
                    md.update(usr.getBytes());
                    usrDigest = md.digest();
                    md.update(pw.getBytes());
                    pwDigest = md.digest();
                } catch (NoSuchAlgorithmException e) {
                    System.err.println("Algoritmo no disponible.");
                }

            } else if (opcion == 2) {
                if (usr != null && pw != null) {
                    System.out.println("Nombre de usuasrio:");
                    usr = br.readLine();
                    System.out.println("Contraseña:");
                    pw = br.readLine();

                    byte[] usrIn = new byte[0];
                    byte[] pwIn = new byte[0];

                    try {
                        MessageDigest md = MessageDigest.getInstance("SHA-256");
                        md.update(usr.getBytes());
                        usrIn = md.digest();
                        md.update(pw.getBytes());
                        pwIn = md.digest();
                    } catch (NoSuchAlgorithmException e) {
                        System.err.println("Algoritmo no disponible.");
                    }

                    if (toHexadecimal(usrDigest).equals(toHexadecimal(usrIn)) && toHexadecimal(pwDigest).equals(toHexadecimal(pwIn))) {
                        System.out.println("Sesión iniciada con éxito.");
                    } else {
                        System.out.println("Usuario o contraseña incorrectos.");
                    }

                } else {
                    System.out.println("Tienes que registrarte primero");
                }
            } else {
                System.out.println("Agur");
            }
        }
    }

    private static String toHexadecimal(byte[] hash) {
        StringBuilder hex = new StringBuilder();
        for (byte b : hash) {
            String h = Integer.toHexString(b & 0xFF);
            if (h.length() == 1) hex.append("0");
            hex.append(h);
        }
        return hex.toString().toUpperCase();
    }
}

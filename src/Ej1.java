import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ej1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Introduce un DNI valido");
        String DNI = br.readLine();
        while(!validarDNI(DNI)) {
            System.out.println("DNI no valido");
            DNI = br.readLine();
        }

        System.out.println("Introduce una matricula");
        String matricula = br.readLine();
        while (!validarMatricula(matricula)) {
            System.out.println("Matricula no valida");
            matricula = br.readLine();
        }
    }


    private static boolean validarDNI(String DNI) {
        //return DNI.matches("^[0-9]{8}[A-Z]$");

        Pattern pattern = Pattern.compile("^[0-9]{8}[A-Z]$");
        Matcher matcher = pattern.matcher(DNI);

        return matcher.matches();
    }

    private static boolean validarMatricula(String matricula) {
        //return matricula.matches("^[0-9]{4}-[A-Z]{3}$");

        Pattern pattern = Pattern.compile("^[0-9]{4}-[A-Z]{3}$");
        Matcher matcher = pattern.matcher(matricula);

        return matcher.matches();
    }
}
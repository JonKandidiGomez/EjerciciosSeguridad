import java.io.IOException;
import java.util.logging.*;

public class Ej2 {

    private static final Logger logger = Logger.getLogger("MiLog");

    public static void main(String[] args) throws IOException {
        FileHandler fh = new FileHandler("Ej2.log", true);
        ConsoleHandler handler = new ConsoleHandler();
        logger.setLevel(Level.ALL);
        logger.addHandler(handler);
        SimpleFormatter form = new SimpleFormatter();
        fh.setFormatter(form);
    }
}

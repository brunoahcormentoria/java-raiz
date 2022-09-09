import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final String USER = "postgres";
    private static final String PASS = "123";
    private static final String DB = "java_raiz";
    private static final String URL = "jdbc:postgresql://127.0.0.1:15432/";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(URL.concat(DB), USER, PASS)) {
            
            System.out.println("### Conectou ao banco postgres!");

        } catch (SQLException se) {
            System.out.println("### Nao conectou!");
            System.out.println("SQLException: " + se.getMessage());
        } catch (Exception e) {
            System.out.println("### Nao conectou!");
            System.out.println("### Exception: " + e);
        }
    }

}
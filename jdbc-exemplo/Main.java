import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Main {

    private static final String USER = "postgres";
    private static final String PASS = "123";
    private static final String DB = "java_raiz";
    private static final String URL = "jdbc:postgresql://127.0.0.1:15432/";

    private static final String SQL_TABLE_DROP = "DROP TABLE PESSOA";

    private static final String SQL_TABLE_CREATE = "CREATE TABLE PESSOA"
    + "("
    + " ID serial,"
    + " NOME varchar(100) NOT NULL,"
    + " EMAIL varchar(50) NOT NULL,"
    + " CREATED_DATE timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,"
    + " UPDATED_DATE timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,"
    + " PRIMARY KEY (ID)"
    + ")";

    private static final String SQL_INSERT = "INSERT INTO PESSOA (NOME, EMAIL, CREATED_DATE, UPDATED_DATE) VALUES (?,?,?,?)";

    private static final String SQL_UPDATE = "UPDATE PESSOA SET EMAIL=?,UPDATED_DATE=? WHERE NOME=?";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(URL.concat(DB), USER, PASS)) {
            
            System.out.println("### Conectou ao banco postgres!");

            Statement statement = conn.createStatement();
            statement.execute(SQL_TABLE_DROP);
            statement.execute(SQL_TABLE_CREATE);

            conn.setAutoCommit(false);

            PreparedStatement psInsert = conn.prepareStatement(SQL_INSERT);
            
            // Run list of insert commands
            psInsert.setString(1, "mateus");
            psInsert.setString(2, "mateus@email.com");
            psInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            psInsert.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            psInsert.execute();

            psInsert.setString(1, "victor");
            psInsert.setString(2, "victor@email.com");
            psInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            psInsert.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            psInsert.execute();

            PreparedStatement psUpdate = conn.prepareStatement(SQL_UPDATE);

            psUpdate.setString(1, "victor_victor@email.com");
            psUpdate.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            psUpdate.setString(3, "victor");
            psUpdate.execute();

            conn.commit();

            conn.setAutoCommit(true);
           
        } catch (SQLException se) {
            System.out.println("### Nao conectou!");
            System.out.println("SQLException: " + se.getMessage());
        } catch (Exception e) {
            System.out.println("### Nao conectou!");
            System.out.println("### Exception: " + e);
        }
    }

}
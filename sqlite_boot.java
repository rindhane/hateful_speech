import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlite_boot
{
    public static void main(String [] args) throws ClassNotFoundException
    {
        Class.forName("org.sqlite.JDBC");

        Connection connection = null;
        try {
            //create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:C:/SOFTWARE_KIT/sqlite-tools/db/sample.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); 
            statement.executeUpdate("create table person (id integer, name string)");
            statement.executeUpdate("insert into person values(1, 'leo')");
            statement.executeUpdate("insert into person values(2, 'yui')");
            ResultSet rs = statement.executeQuery("select * from person");
            while (rs.next()){
                //read the result set 
                System.out.println("name =" + rs.getString("name"));
                System.out.println("id ="+rs.getInt("id"));
            }

        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            try {
                if (connection !=null)
                connection.close();
            }
            catch(SQLException e)
            {
                System.err.println(e);
            }
        }
    }

}



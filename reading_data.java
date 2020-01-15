import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class reading_data
{
    static ResultSet sql_retrieve(Statement statement, String dbName, String query_string) throws Exception
    {
        String base = String.format("select * from %1$s  %2$s",dbName,query_string);
        ResultSet rs = statement.executeQuery(base);
        return rs;    

    } 

    public static void main (String [] args) throws Exception
    {
        String dbName = "hate_data";
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        //create a database connection
        String url= "jdbc:sqlite:C:/SOFTWARE_KIT/sqlite-tools/db/sample.db"; 
        connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement(); 
        statement.setQueryTimeout(30);
        
        ResultSet rs= sql_retrieve(statement,dbName,"");
        while (rs.next()){
                //read the result set 
                System.out.println("id ="+rs.getInt("id") + "\t"+ "tweet =" + rs.getString("tweet"));
                System.out.println();
            }
        connection.close();
    }
}
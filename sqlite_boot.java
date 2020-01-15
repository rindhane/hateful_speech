import java.io.FileReader;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.CsvListReader;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.prefs.CsvPreference;
import java.util.List;



public class sql_trial
{
    static ArrayList<String> comp_string(FileReader ar, int cols, char separator) throws Exception 
    {
        int al;
        char foo;
        String hold_string;
        ArrayList<String> string_set = new ArrayList<String>();
        char test_end = separator;
        for (int i= 0; i< cols; i++ ) 
        {
            hold_string="";
            do 
            {
                test_end = (i<cols-1)? separator :'\n';
                al = ar.read();
                foo = (char) al ;
                if (foo == test_end) 
                {
                    continue;
                }else {
                    hold_string = hold_string + Character.toString(foo);
                }
                
            }while(foo != test_end);
            string_set.add(hold_string);
        }
        return string_set;
    }


    static boolean check_table_exist(Statement statement, String dbName) throws Exception 
    {
        String sql_dbname="'"+dbName +"'";
        String string_query =String.format("SELECT name FROM sqlite_master WHERE type='table' AND name= %s",sql_dbname);
        ResultSet rs = statement.executeQuery(string_query);
        int numberOfColumns = rs.getMetaData().getColumnCount();
        while(rs.next())
        {
            String catalogs = rs.getString(1);
            if(dbName.equals(catalogs))
            {
                return true;
            }
        }
        return false;
    }
    
    static boolean sql_entry(Statement statement,String dbName, String insert_string) throws Exception
    {
        
        if (!check_table_exist(statement, dbName))
        {
            String pattern = "(id integer, count integer, hate_speech integer, offensive_language integer, neither integer, class integer , tweet text)";
            String str_execute= String.format("create table %1$s %2$s",dbName, pattern);
            statement.executeUpdate(str_execute);
        }
        //System.out.println(insert_string);
        statement.executeUpdate(insert_string);
        return true;
    }

    static String readWithCsvListReader(FileReader file_p, String dbName) throws Exception
     {
         
        ICsvListReader listReader = new CsvListReader(file_p, CsvPreference.STANDARD_PREFERENCE);
        
        
        //final CellProcessor[] processors = getProcessors();
        //customerList = listReader.read(processors);
        List<String> row=listReader.read();
        String s1 = row.get(0);
        String s2 =row.get(1);
        String s3= row.get(2);
        String s4= row.get(3);
        String s5 = row.get(4);
        String s6= row.get(5);
        String s7= row.get(6);
        s7 = s7.replaceAll("'", "''");
        s7= "'"+ s7 + "'" ;
        String base_string = "insert into %1$s values (%2$s,%3$s,%4$s,%5$s,%6$s,%7$s,%8$s)";
        String insert_string= String.format(base_string,dbName,s1,s2,s3,s4,s5,s6,s7);

        return insert_string;
    }

    static ResultSet sql_retrieve(Statement statement, String dbName, String query_string) throws Exception
    {
        String base = String.format("select * from %1$s  %2$s",dbName,query_string);
        ResultSet rs = statement.executeQuery(base);
        return rs;    

    } 

    static void run_cmd () throws Exception
    {
        //List<String> command = new ArrayList<String>();
        //command.add("notepad.exe");
        //command.add ("trial.py");

        //ProcessBuilder pb = new ProcessBuilder(command);
        //Process process = pb.start();
        //OutputStream stdOutput = process.getOutputStream();
        //InputStream inputStream = process.getInputStream();
        //InputStream errorStream = process.getErrorStream();
        //String command ="cmd /c start cmd.exe /K \"dir && ping localhost\""
        //String [] command  ={ cmd };
        String [] command = {"cmd", "/K","Start","new_java.bat"};       
        Runtime.getRuntime().exec(command);
    }

    public static void main (String [] args) throws Exception
    {
        String csvFile ="C:\\Java\\hateful_speech\\data.csv";
        int no_of_cols=7;
        char csvSplitBy = ',';
        FileReader br =new FileReader(csvFile);
        String dbName = "hate_data";
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        //create a database connection
        String url= "jdbc:sqlite:C:/SOFTWARE_KIT/sqlite-tools/db/sample.db"; 
        connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement(); 
        statement.setQueryTimeout(30);
        ArrayList<String> catch_text =null;
        boolean check = false;
        int i=0;
        while(i<20)
        {
            //catch_text = comp_string(br,no_of_cols,csvSplitBy);
            //String insert_string = readWithCsvListReader(br, dbName);
            if ((i==0)) 
            {
                ++i;
                continue; //skipping the header in csv file
            }
            
            /*String s1 = catch_text.get(0);
            String s2 =catch_text.get(1);
            String s3= catch_text.get(2);
            String s4= catch_text.get(3);
            String s5 = catch_text.get(4);
            String s6= catch_text.get(5);
            String s7=catch_text.get(6);
            s7 = s7.replaceAll("'", "''");
            s7= "'"+ s7 + "'" ;*/
            //System.out.println(s7);
            
            //System.out.println(insert_string);
            //check = sql_entry(statement,dbName, insert_string);
            ++i;
            
        } 
        if (check)
        {
            System.out.println("entry done");   
        }
        br.close();
        connection.close();
        run_cmd() ;     
    }

     
}
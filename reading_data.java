import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;



public class trial
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


    public static void main (String [] args) throws Exception
    {
        String csvFile ="C:\\Java\\hateful_speech\\data.csv";
        int no_of_cols=7;
        char csvSplitBy = ',';
        FileReader br =new FileReader(csvFile);
        ArrayList<String> catch_text =null;
        for (int i =0 ; i< 5; i++)
        {
            catch_text = comp_string(br,no_of_cols,csvSplitBy);
            for (String items : catch_text)
            {
                System.out.print(items + '\t');
            }
        }    
        System.out.println();
        System.out.println(catch_text.size());
        br.close();
    }
}
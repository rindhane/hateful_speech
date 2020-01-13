import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.*;
import java.util.List;

public class reading_data{
    public static void main(String[] args) throws ClassNotFoundException
    {
        String csvFile ="C:\\Java\\hateful_speech\\data.csv";
        BufferedReader br = null ;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br =new BufferedReader(new FileReader(csvFile));
            String [] nextRecord;
            int i=0;
            while (i<20)
            {
                line=br.readLine();
                nextRecord =line.split(cvsSplitBy);
                for (String cell :nextRecord){
                    System.out.print(cell +"\t");
                }
                System.out.println();
                i++;
            }
            //List <String[]> allData =csvreader.readAll() ;
            /*for (String[] row : allData) 
            {
                for (String cell : row )
                {
                    System.out.print(cell+ "\t");

                }
                // printing empty separator line
                System.out.println();
            }*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if (br !=null) {
                try {
                    br.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
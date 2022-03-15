import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class DataReader {
    //private FileReader fr;
    private Scanner scanner;

    public DataReader(FileInputStream inputStream){
        scanner = new Scanner(inputStream);
        /*try{
            //fr = new FileReader(path);

        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }*/
    }

    public boolean hasNextLine(){
        return scanner.hasNextLine();
    }

    public String getNextLine(){
        return scanner.nextLine();
    }

    public void close(){
        scanner.close();
        /*try {
            fr.close();
            scanner.close();
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }*/
    }
}

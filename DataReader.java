import java.io.InputStream;
import java.util.Scanner;

public class DataReader {
    private Scanner scanner;
    private String line;
    private int limiter;

    public DataReader(InputStream inputStream){
        scanner = new Scanner(inputStream);
        line = "";
        limiter = 0;
    }

    public boolean isExists() {
        return scanner.hasNextLine() || limiter < line.length();
    }

    public void getNextLine(){
        line = scanner.nextLine();
        limiter = 0;
    }

    public String getWord() {
        if (line.isEmpty() || limiter == line.length()) {
            getNextLine();
        }
        String word = "";
        while (line.length() > limiter) {
            if (word.isEmpty() && !Character.isLetterOrDigit(line.charAt(limiter))) {
                limiter++;
                continue;
            }
            else if (Character.isLetterOrDigit(line.charAt(limiter))) {
                word += line.charAt(limiter);
                limiter++;
            }
            else if (!word.isEmpty() && !Character.isLetterOrDigit(line.charAt(limiter))) {
                limiter++;
                break;
            }
            if (word.isEmpty() && (limiter == line.length())) {
                getNextLine();
            }
        }
        return word;
    }

    public void close(){
        scanner.close();
    }
}

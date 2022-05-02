import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class DataWriter {
    private OutputStreamWriter writer;
    private int wordsAmount;
    private char limiter;

    public DataWriter(OutputStream path, char limit, int words){
        writer = new OutputStreamWriter(path);
        wordsAmount = words;
        limiter = limit;
    }

    public void writeLine(String word, int words) throws IOException {
        double frequency = words / (double)wordsAmount;
        writer.write(word + limiter);
        writer.write(String.valueOf(frequency) + limiter);
        writer.write(String.valueOf(frequency * 100) + '%' + limiter + System.lineSeparator());
    }

    public void writeTotalWords() throws IOException {
        writer.write("Total words: " + limiter + wordsAmount + System.lineSeparator());
    }

    public void close() {
        try {
            writer.close();
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }
    }
}

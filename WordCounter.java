import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class WordCounter {
    private HashMap<String, Integer> map;
    private int wordsAmount;

    public WordCounter(){
        map = new HashMap<>();
        wordsAmount = 0;
    }

    public void count(String path){
        DataReader dataReader = null;
        try {
            dataReader = new DataReader(new FileInputStream(path));
        }
        catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
            //e.printStackTrace();
        }
        String str;
        while (dataReader.hasNextLine()){
            str = dataReader.getNextLine();
            parseLine(str);
        }
        dataReader.close();
    }

    public void outputResult(String path){
        Writer writer = new Writer(path);
        map.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(e -> {
                    try {
                        writer.println(e.getKey() + ", " + e.getValue() + ", " + (double) e.getValue() / wordsAmount * 100 + "%");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
        writer.close();
    }

    private void parseLine(String str){
        StringBuilder word = new StringBuilder();
        int strSize = str.length();
        for (int i = 0; i < strSize; ++i){
            char symbol = str.charAt(i);
            if (Character.isLetterOrDigit(symbol)){
                word.append(symbol);
            }
            else if (!word.isEmpty()) {
                countWord(word.toString());
                word = new StringBuilder();
            }
        }
        if (!word.toString().isEmpty()){
            countWord(word.toString());
        }
    }

    private void countWord(String word){
        if (!map.containsKey(word)){
            map.put(word, 0);
        }
        map.put(word, map.get(word) + 1);
        wordsAmount++;
    }
}

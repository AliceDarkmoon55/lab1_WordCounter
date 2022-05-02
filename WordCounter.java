import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Comparator;


public class WordCounter {
    private TableKeyValue<String, Integer> statistics;
    private int wordsAmount;

    public WordCounter(){
        statistics = new TableKeyValue<>(addComp);
        wordsAmount = 0;
    }

    private Comparator<PairKeyValue<String, Integer>> addComp = new Comparator<PairKeyValue<String, Integer>>() {
        @Override
        public int compare (PairKeyValue<String, Integer> first, PairKeyValue<String, Integer> second) {
            return first.getKey().compareTo(second.getKey());
        }
    };

    private Comparator<PairKeyValue<String, Integer>> sortComp = new Comparator<PairKeyValue<String, Integer>>() {
        @Override
        public int compare (PairKeyValue<String, Integer> first, PairKeyValue<String, Integer> second) {
            int res = second.getValue() - first.getValue();
            if (res != 0) {
                return res;
            }
            else {
                return first.getKey().compareTo(second.getKey());
            }
        }
    };

    public void count(InputStream inputStream){
        DataReader dataReader = null;
        try {
            dataReader = new DataReader(inputStream);
            while (dataReader.isExists()) {
                String word = dataReader.getWord();
                if (!word.isEmpty()) {
                    if (!statistics.add(word, 1)) {
                        int value = statistics.getElement(word, 1).getValue();
                        statistics.getElement(word, 1).setValue(value + 1);
                    }
                    wordsAmount++;
                }
            }
        }
        finally {
                if (dataReader != null) {
                    dataReader.close();
                }
        }
    }

    public void outputResult(OutputStream outputStream, char delimiter) {
        DataWriter dataWriter = null;
        try {
            PairKeyValue<String, Integer>[] toPrint = statistics.getSortedData(sortComp);
            dataWriter = new DataWriter(outputStream, delimiter, wordsAmount);
            for (int i = 0; i < toPrint.length; i++) {
                dataWriter.writeLine(toPrint[i].getKey(), toPrint[i].getValue());
            }
            dataWriter.writeTotalWords();
        } catch (IOException ex) {
            System.err.println(ex.getLocalizedMessage());
        } finally {
            if (dataWriter != null) {
                dataWriter.close();
            }
        }
    }
}

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class TableKeyValue<Key, Value> {
    private TreeSet<PairKeyValue<Key, Value>> table;
    Comparator<PairKeyValue<Key, Value>> keyComp;

    public TableKeyValue(Comparator<PairKeyValue<Key, Value>> keyComparator) {
        table = new TreeSet<>(keyComparator);
        keyComp = keyComparator;
    }

    public boolean add(Key key, Value val) {
        return table.add(new PairKeyValue<>(key, val));
    }

    public PairKeyValue<Key, Value> getElement(Key key, Value val) {
        return table.floor(new PairKeyValue<>(key, val));
    }

    public PairKeyValue<Key, Value>[] getSortedData(Comparator<PairKeyValue<Key, Value>> reSortComparator) {
        TreeSet<PairKeyValue<Key, Value>> sortedData;
        if (reSortComparator != keyComp) {
            sortedData = new TreeSet<>(reSortComparator);
            sortedData.addAll(table);
        }
        else {
            sortedData = table;
        }
        Iterator<PairKeyValue<Key, Value>> it = sortedData.iterator();
        PairKeyValue<Key, Value>[] array = (PairKeyValue<Key, Value>[]) new PairKeyValue[sortedData.size()];
        for (int i = 0; i < sortedData.size(); i++) {
            array[i] = it.next();
        }
        return array;
    }
}
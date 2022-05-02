import java.util.Objects;

public class PairKeyValue <Key, Value>{
    private Key key;
    private Value value;

    public PairKeyValue(Key key, Value val) {
        this.key = key;
        this.value = val;
    }

    public Key getKey() {
        return this.key;
    }

    public Value getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass() || obj == null) {
            return false;
        }
        PairKeyValue<?, ?> pair = (PairKeyValue<?, ?>) obj;
        return Objects.equals(key, pair.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    public void setValue(Value val) {
        this.value = val;
    }
}

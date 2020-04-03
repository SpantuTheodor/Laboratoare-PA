public class Token {
    private int value;

    public Token(int index) {
        this.value = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

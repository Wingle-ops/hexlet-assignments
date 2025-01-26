package exercise;

public class ReversedSequence implements CharSequence{

    private String reverse = "";

    ReversedSequence(String string) {
        for (int i = string.length() - 1; i >= 0; i--) {
            this.reverse += string.charAt(i);
        }
    }

    @Override
    public int length() {
        return reverse.length();
    }

    @Override
    public char charAt(int index) {
        return reverse.charAt(index);
    }

    @Override
    public String subSequence(int start, int end) {
        return reverse.substring(start, end);
    }

    @Override
    public String toString() {
        return reverse;
    }
}

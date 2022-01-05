package br.com.rodrigodonizettio.alphabet;

public enum Alphabet {
    A(0, "a"),
    B(1, "b"),
    C(2, "c");

    private final Integer index;
    private final String letter;

    Alphabet(int index, String letter) {
        this.index = index;
        this.letter = letter;
    }

    public Integer getIndex() {
        return index;
    }

    public String getLetter() {
        return letter;
    }
}

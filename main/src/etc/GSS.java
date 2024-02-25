package etc;

public enum GSS {
    GUTTER("-", 0),
    STRIKE("X", 10),
    //STRIKE_BLANK(" ", 10),
    SPARE("/",0);

    private final String sign;
    private final int score;
    GSS(String sign, int score) {
        this.sign = sign;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getSign() {
        return sign;
    }
}


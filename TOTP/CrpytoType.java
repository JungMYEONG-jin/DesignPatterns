package hello.core.rfc;

public enum CryptoType {

    HmacSHA1("HmacSHA1"), HmacSHA256("HmacSHA256"), HmacSHA512("HmacSHA512");

    private final String algo;

    CryptoType(String algo)
    {
        this.algo = algo;
    }

    public String toString()
    {
        return algo;
    }
}

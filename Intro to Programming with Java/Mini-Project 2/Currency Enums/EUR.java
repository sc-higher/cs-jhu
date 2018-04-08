public enum EUR
{
    ONEC(1),
    TWOC(2),
    FIVEC(5),
    TENC(10),
    TWENTYC(20),
    FIFTYC(50),
    HUNDREDC(100),
    TWOHUNDREDC(200),
    FIVEN(500),
    TENN(1000),
    TWENTYN(2000);


    private int value;

    EUR(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}
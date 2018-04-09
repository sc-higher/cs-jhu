public enum JPY
{
    ONE(1),
    FIVE(5),
    TEN(10),
    FIFTY(50),
    HUNDRED(100),
    FIVEHUNDRED(500),
    ONEK(1000),
    TWOK(2000);

    private int value;

    JPY(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}
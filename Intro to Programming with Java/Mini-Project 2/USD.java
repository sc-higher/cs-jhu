public enum USD
{
    PENNY(1),
    NICKLE(5),
    DIME(10),
    QUARTER(25),
    ONE(100),
    FIVE(500),
    TEN(1000),
    TWENTY(2000);

    private int value;

    USD(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}
public class Investment {
    private double worth;
    private double gram;
    private String currency;

    public Investment(double worth, double gram, String currency) {
        this.worth = worth;
        this.gram = gram;
        this.currency = currency;
    }

    public double getWorth() { return worth; }
    public double getGram() { return gram; }
    public String getCurrency() { return currency; }
}
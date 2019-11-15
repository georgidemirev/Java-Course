package bg.sofia.uni.fmi.mjt.smartcity.hub;

public class NameAndPowerConsumptionForPeriod implements Comparable{

    private double powerConsumption;
    private String name;

    public NameAndPowerConsumptionForPeriod(double powerConsumption, String name) {
        this.powerConsumption = powerConsumption;
        this.name = name;
    }

    public double getPowerConsumption() {
        return powerConsumption;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof NameAndPowerConsumptionForPeriod)) {
            throw new RuntimeException();
        }
        if(this.powerConsumption < ((NameAndPowerConsumptionForPeriod)o).getPowerConsumption()){
            return -1;
        }else{
            return 1;
        }
    }
}

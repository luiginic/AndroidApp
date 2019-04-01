package client.test.model;

public class PacientDailyInfo {
    private Integer water;
    private Integer weight;
    private Integer pulse;
    private Integer temperature;

    public PacientDailyInfo(Integer water, Integer weight, Integer pulse, Integer temperature) {
        this.water = water;
        this.weight = weight;
        this.pulse = pulse;
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return water.toString()+ ',' + weight.toString()+',' + pulse.toString() +','+ temperature.toString();
    }
}

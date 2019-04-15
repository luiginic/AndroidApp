package client.test.data_collection.model;

import java.util.Date;

public class PacientDailyInfo {
    private Integer water;
    private Integer weight;
    private Integer pulse;
    private Integer temperature;
    private Integer steps;
    private String today;

    public PacientDailyInfo(Integer water, Integer weight, Integer pulse, Integer temperature, Integer steps, String today) {
        this.water = water;
        this.weight = weight;
        this.pulse = pulse;
        this.temperature = temperature;
        this.steps = steps;
        this.today = today;
    }

    @Override
    public String toString() {
        return water.toString()+ ',' + weight.toString()+',' + pulse.toString() +','+ temperature.toString() + ',' + steps.toString();
    }
}

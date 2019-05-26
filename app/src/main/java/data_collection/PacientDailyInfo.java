package data_collection;

import android.content.Intent;

public class PacientDailyInfo {
    private Integer water;
    private Integer weight;
    private Integer pulse;
    private Integer temperature;
    private Integer steps;
    private String today;
    private Integer patient_id;
    private Integer calories;

    public PacientDailyInfo(Integer water, Integer weight, Integer pulse, Integer temperature, Integer steps, String today, Integer patient_id, Integer calories) {
        this.water = water;
        this.weight = weight;
        this.pulse = pulse;
        this.temperature = temperature;
        this.steps = steps;
        this.today = today;
        this.patient_id = patient_id;
        this.calories = calories;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getWater() {
        return water;
    }

    public void setWater(Integer water) {
        this.water = water;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getPulse() {
        return pulse;
    }

    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    @Override
    public String toString() {
        return "PacientDailyInfo{" +
                "water=" + water +
                ", weight=" + weight +
                ", pulse=" + pulse +
                ", temperature=" + temperature +
                ", steps=" + steps +
                ", today='" + today + '\'' +
                ", patient_id=" + patient_id +
                ", calories=" + calories +
                '}';
    }
}

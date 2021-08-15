package com.example.Assignment1;

public class car
{
    private String UniqueId;
    private String Model;
    private Long Price;
    private String Transmission;
    private Long Mileage;
    private String FuelType;
    private Integer Tax;
    private Double Mpg;
    private Float EngineSize;
    private Integer Year;

    public Long getMileage() {
        return Mileage;
    }

    public Double getMpg() {
        return Mpg;
    }

    public void setMpg(Double mpg) {
        Mpg = mpg;
    }

    public Integer getTax() {
        return Tax;
    }

    public void setTax(Integer tax) {
        Tax = tax;
    }

    public void setMileage(Long mileage) {
        Mileage = mileage;
    }

    public String getFuelType() {
        return FuelType;
    }

    public void setFuelType(String fuelType) {
        FuelType = fuelType;
    }

    public String getTransmission() {
        return Transmission;
    }

    public void setTransmission(String transmission) {
        Transmission = transmission;
    }

    public String getModel() {
        return Model;
    }

    public Long getPrice() {
        return Price;
    }

    public void setPrice(Long price) {
        Price = price;
    }

    public Float getEngineSize() {
        return EngineSize;
    }

    public void setEngineSize(Float engineSize) {
        EngineSize = engineSize;
    }

    public void setModel(String model) {
        Model = model;
    }

    public Integer getYear() {
        return Year;
    }

    public void setYear(Integer year) {
        Year = year;
    }



    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }




}
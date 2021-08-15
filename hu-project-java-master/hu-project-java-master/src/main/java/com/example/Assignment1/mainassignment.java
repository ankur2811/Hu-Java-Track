package com.example.Assignment1;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import java.io.*;
public class mainassignment{
    static List<car> audi=new ArrayList<>();
    static List<car> bmw=new ArrayList<>();

    public static void main(String[] args)
    {

        GetCarData();
        System.out.println(audi.size());
        car c1=NewCarLowPrice();
        System.out.println("*************************************************************************");
        System.out.println("Newest car which has the lowest price");
        System.out.println(c1.getUniqueId()+" "+c1.getModel()+" "+c1.getFuelType()+" "+c1.getTransmission()+" "+c1.getPrice()+" "+c1.getEngineSize()+" "+c1.getMileage()+" "+c1.getMpg()+" "+c1.getTax()+" "+c1.getYear());
        System.out.println("*************************************************************************");
        System.out.println("Petrol BMW with auto transmission that has the highest mileage");
        c1=petrolBmwAutoHighMile();
        System.out.println(c1.getUniqueId()+" "+c1.getModel()+" "+c1.getFuelType()+" "+c1.getTransmission()+" "+c1.getPrice()+" "+c1.getEngineSize()+" "+c1.getMileage()+" "+c1.getMpg()+" "+c1.getTax()+" "+c1.getYear());
        System.out.println("*************************************************************************");
        System.out.println("Diesel audi with manual transmission that has the highest mileage");
        c1=dieselAudiManualHighMile();
        System.out.println(c1.getUniqueId()+" "+c1.getModel()+" "+c1.getFuelType()+" "+c1.getTransmission()+" "+c1.getPrice()+" "+c1.getEngineSize()+" "+c1.getMileage()+" "+c1.getMpg()+" "+c1.getTax()+" "+c1.getYear());
        System.out.println("*************************************************************************");
        System.out.println("Top 10 cheapest cars with 2 liter engine.");
        List<car> list=top10CheaptwolitreEngine();
        list.forEach((c)->{
            System.out.println(c.getUniqueId()+" "+c.getModel()+" "+c.getFuelType()+" "+c.getTransmission()+" "+c.getPrice()+" "+c.getEngineSize()+" "+c.getMileage()+" "+c.getMpg()+" "+c.getTax()+" "+c.getYear());
        });
        System.out.println("*************************************************************************");
        System.out.println("5 top models from bmw and audi which has mileage above 70000");
        list=top5ModelsMileAbove70000();
        list.forEach((c)->{
            System.out.println(c.getUniqueId()+" "+c.getModel()+" "+c.getFuelType()+" "+c.getTransmission()+" "+c.getPrice()+" "+c.getEngineSize()+" "+c.getMileage()+" "+c.getMpg()+" "+c.getTax()+" "+c.getYear());
        });
        System.out.println("*************************************************************************");
        System.out.println("model year and transmission type of top 10 petrol cars with tax above 200(both audi and bmw sets).");
        list=top10WithTaxAbove200();
        list.forEach((c) ->{
            System.out.println(c.getUniqueId()+c.getYear()+" "+c.getTransmission());
        });
        System.out.println("*************************************************************************");
        System.out.println(" top 3 latest bmw model of type automatic with price between 80,000 and 1,00,000");
        list=top3BMWAuto80KTo100K();
        list.forEach((c)->{
            System.out.println(c.getUniqueId()+" "+c.getModel()+" "+c.getFuelType()+" "+c.getTransmission()+" "+c.getPrice()+" "+c.getEngineSize()+" "+c.getMileage()+" "+c.getMpg()+" "+c.getTax()+" "+c.getYear());
        });
        System.out.println("*************************************************************************");
        System.out.println("top 5 semi-auto audi models with mpg greater than 60.0");
        list=top5semiautoMPGgreater60();
        list.forEach((c)->{
            System.out.println(c.getUniqueId()+" "+c.getModel()+" "+c.getFuelType()+" "+c.getTransmission()+" "+c.getPrice()+" "+c.getEngineSize()+" "+c.getMileage()+" "+c.getMpg()+" "+c.getTax()+" "+c.getYear());
        });
        System.out.println("*************************************************************************");
        System.out.println("model and year of manual bmw and audi with the highest price");
        c1=ManualBMWAudiHighPrice();
        System.out.println(c1.getUniqueId()+" "+ c1.getModel()+" "+ c1.getYear());
    }


    //Function to give newest car which has the lowest price, considering both the sets
    public static car NewCarLowPrice()
    {
        List<car> combinedList = Stream.of(audi, bmw)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
        List<car> newest= combinedList.stream().sorted((c1, c2)-> c2.getYear().compareTo(c1.getYear())).collect(Collectors.toList());
        int year=newest.get(0).getYear();
        List<car> newest1=newest.stream().filter(s->s.getYear().equals(year)).collect(Collectors.toList());
        return newest1.stream().sorted((c1,c2)->c1.getPrice().compareTo(c2.getPrice())).collect(Collectors.toList()).get(0);
    }

    //Function to get petrol BMW with auto transmission that has the highest mileage
    public static car petrolBmwAutoHighMile()
    {
        return bmw.stream().filter((c) -> c.getFuelType().equals("Petrol") && c.getTransmission().equals("Automatic")).collect(Collectors.toList()).stream().sorted((c1,c2) -> c2.getMileage().compareTo(c1.getMileage())).collect(Collectors.toList()).get(0);
    }


    // Function to get diesel audi with manual transmission that has the highest mileage
    public static car dieselAudiManualHighMile()
    {
        return audi.stream().filter((c) -> c.getFuelType().equals("Diesel") && c.getTransmission().equals("Manual")).collect(Collectors.toList()).stream().sorted((c1,c2)-> c2.getMileage().compareTo(c1.getMileage())).collect(Collectors.toList()).get(0);

    }

    //Function to give top 10 cheapest cars with 2 liter engine.
    public static List<car> top10CheaptwolitreEngine()
    {
        List<car> combinedList=Stream.of(audi,bmw).flatMap(x -> x.stream()).collect(Collectors.toList());
        return combinedList.stream().sorted((c1,c2)-> c1.getPrice().compareTo(c2.getPrice())).filter((c) -> c.getEngineSize()==2).limit(10).collect(Collectors.toList());

    }

    //Function to give 5 top models from bmw and audi which has mileage above 70000
    public static List<car> top5ModelsMileAbove70000()
    {
        List<car> combinedList=Stream.of(audi,bmw).flatMap(x-> x.stream()).collect(Collectors.toList());
        return combinedList.stream().sorted((c1 ,c2) -> c2.getYear().compareTo(c1.getYear())).collect(Collectors.toList()).stream().filter((x) -> x.getMileage()>70000).limit(5).collect(Collectors.toList());
    }

    //Function to get cars with model year and transmission type of top 10 petrol cars with tax above 200(both audi and bmw sets)
    public static List<car> top10WithTaxAbove200()
    {

        List<car> combinedCars=Stream.of(audi,bmw).flatMap(x-> x.stream()).collect(Collectors.toList());
        return combinedCars.stream().sorted((c1, c2) -> c2.getPrice().compareTo(c1.getPrice())).collect(Collectors.toList()).stream().filter((c) -> c.getTax()>200 && c.getFuelType().equals("Petrol")).limit(10).collect(Collectors.toList());
    }

    //Function to get the top 3 latest bmw model of type automatic with price between 80,000 and 1,00,000
    public static List<car> top3BMWAuto80KTo100K()
    {
        return bmw.stream().sorted((c1,c2) -> c2.getYear().compareTo(c1.getYear())).collect(Collectors.toList()).stream().filter((c) -> c.getTransmission().equals("Automatic") && c.getPrice()>80000 && c.getPrice()<100000).limit(3).collect(Collectors.toList());

    }

    //Function to get top 5 semi-auto audi models with mpg greater than 60.0.
    public static List<car> top5semiautoMPGgreater60()
    {
        return audi.stream().filter((x)-> x.getTransmission().equals("Semi-Auto") && x.getMpg()>60).limit(5).sorted((c1 , c2) -> c2.getPrice().compareTo(c1.getPrice())).collect(Collectors.toList());
    }


    //Function to get model and year of manual bmw and audi with the highest price
    public static car ManualBMWAudiHighPrice()
    {
        List<car> combinedList=Stream.of(audi,bmw).flatMap((x) -> x.stream()).collect(Collectors.toList());
        return combinedList.stream().sorted((c1,c2) -> c2.getPrice().compareTo(c1.getPrice())).collect(Collectors.toList()).stream().filter((c) -> c.getTransmission().equals("Manual")).limit(1).collect(Collectors.toList()).get(0);
    }
    //Function To Fetch CSV file data in list and assign unique Id
    public static void GetCarData() {
        Scanner sc = null;
        try {


            sc=new Scanner(new File("src/main/java/com/example/Assignment1/bmw.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sc.nextLine();
        car car;
        while(sc.hasNext())
        {
            String[] data=sc.nextLine().split(",");
            car=new car();
            car.setUniqueId(UUID.randomUUID().toString());
            car.setModel(data[0]);
            car.setYear(Integer.parseInt(data[1]));
            car.setPrice(Long.parseLong(data[2]));
            car.setTransmission(data[3]);
            car.setMileage(Long.parseLong(data[4]));
            car.setFuelType(data[5]);
            car.setTax(Integer.parseInt(data[6]));
            car.setMpg(Double.parseDouble(data[7]));
            car.setEngineSize(Float.parseFloat(data[8]));
            bmw.add(car);

        }


        try {
            sc = new Scanner(new File("src/main/java/com/example/Assignment1/audi.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sc.nextLine();

        while(sc.hasNext())
        {
            String[] data=sc.nextLine().split(",");
            car=new car();
            car.setUniqueId(UUID.randomUUID().toString());
            car.setModel(data[0]);
            car.setYear(Integer.parseInt(data[1]));
            car.setPrice(Long.parseLong(data[2]));
            car.setTransmission(data[3]);
            car.setMileage(Long.parseLong(data[4]));
            car.setFuelType(data[5]);
            car.setTax(Integer.parseInt(data[6]));
            car.setMpg(Double.parseDouble(data[7]));
            car.setEngineSize(Float.parseFloat(data[8]));
            audi.add(car);

        }

    }
}
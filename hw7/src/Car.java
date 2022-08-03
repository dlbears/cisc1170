
class FuelGauge {
    private double gallons;

    public FuelGauge() {
        gallons = 0;
    }

    public FuelGauge(double initFuel) {
        gallons = initFuel;
    }

    public double getGallons() { return gallons; }

    public void addFuel() { gallons += 1; }
    public void useFuel() { 
        double used = 1.0 + Math.random();
        if (gallons > used) {
            gallons -= used;
        } else {
            gallons = 0;
        }
    }
}

class Odometer {
    private int mileage;
    private FuelGauge fuel;
    private final int MPG = 24;

    public Odometer() {
        mileage = 0;
        fuel = new FuelGauge();
    }

    public Odometer(FuelGauge initFuel) {
        fuel = initFuel;
    }

    public Odometer(int initMileage) {
        mileage = initMileage;
    }

    public Odometer(FuelGauge initFuel, int initMileage) {
        fuel = initFuel;
        mileage = initMileage;
    }

    public int readMiles() { return mileage; }

    public void addMile() {
        if (mileage < 999_999) {
            mileage++;
        } else {
            mileage = 0;
        }

        if (mileage % MPG == 0) {
            fuel.useFuel();
        }
    }

} 

public class Car {
    private int yearModel, speed;
    private String make;
    public FuelGauge fuel;
    public Odometer mileage;

    public Car(int model, String initMake) {
        yearModel = model;
        make = initMake;
        speed = 0;
        fuel = new FuelGauge();
        mileage = new Odometer(fuel);
    }

    public int getSpeed() { return speed; }
    public int getYearModel() { return yearModel; }
    public String getMake() { return make; }

    public void accelerate() { speed += 5; }
    public void brake() { 
        if (speed != 0) mileage.addMile(); // adds mileage and decreases fuel
        if (speed >= 5) speed -= 5;
        else speed = 0;
    }


    public static void main(String[] args) {
        Car a = new Car(2020, "Ford");

        for (int i = 0; i < 5; i++) a.fuel.addFuel(); // add 5 gallons
        while (a.fuel.getGallons() > 0) {
            System.out.printf("Current mileage = %d miles\n remaining fuel = %f gallons\n", a.mileage.readMiles(), a.fuel.getGallons());
            a.accelerate();
            a.accelerate();
            a.brake();
            System.out.printf("Current speed = %d\n\n", a.getSpeed());
            
        }
        System.out.printf("Current mileage = %d miles\n remaining fuel = %f gallons\n", a.mileage.readMiles(), a.fuel.getGallons());
        System.out.printf("Current speed = %d\n\n", a.getSpeed());
    }
}


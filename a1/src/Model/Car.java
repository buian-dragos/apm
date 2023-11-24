package Model;

public class Car implements GenericVehicle {
    private int price;

    public Car(int price){
        this.price=price;
    }

    @Override
    public int getPrice(){
        return this.price;
    }
    @Override
    public String toString(){
        return "Car that costs: " + this.price + ".";
    }
}
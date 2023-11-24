package Model;

public class Truck implements GenericVehicle {
    private int price;

    public Truck(int price){
        this.price=price;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public String toString(){
        return "Truck that costs: " + this.price + ".";
    }
}
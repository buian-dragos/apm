package Model;

public class Motorcycle implements GenericVehicle {
    private int price;

    public Motorcycle(int price){
        this.price=price;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
    @Override
    public String toString(){
        return "Motorcycle that costs: " + this.price + ".";
    }
}

package View;

import Controller.Controller;
import Model.*;
import java.util.Scanner;

public class View {
    Controller controller = new Controller();
    Scanner keyboard = new Scanner(System.in);
    private void addObject() throws Exception {
        this.printAddMenuOptions();
        int option = Integer.valueOf(this.keyboard.nextLine());
        System.out.println("Price: ");
        int price = Integer.valueOf(this.keyboard.nextLine());
        switch(option) {
            case 1:
                this.controller.add(new Car(price));
                break;
            case 2:
                this.controller.add(new Motorcycle(price));
                break;

            case 3:
                this.controller.add(new Truck(price));
                break;
            default:
                System.out.println("Incorrect choice. Try again.");
                break;
        }
    }

    private void deleteObject() throws Exception{
        System.out.println("Choose index to delete: ");
        int index = Integer.valueOf(this.keyboard.nextLine());
        controller.delete(index);
    }

    private void printRequestedObjects(){
        GenericVehicle[] vehicles = controller.getWantedVehicles();
        for(int i=0;i<vehicles.length;i++)
            if (vehicles[i]!=null)
                System.out.println(vehicles[i]);
    }
    private void printMenuOptions(){
        System.out.println("Press:");
        System.out.println("1 to add an entity.");
        System.out.println("2 to delete an entity.");
        System.out.println("3 to print all vehicles with a price > 1000.");
        System.out.println("0 to exit the program.");
    }
    private void printAddMenuOptions(){
        System.out.println("1 to add a car.");
        System.out.println("2 to add a motorbike.");
        System.out.println("3 to add a truck.");
    }
    public void run(){
        boolean stop=false;

        while (!stop){
            this.printMenuOptions();
            System.out.println("> ");
            int option;
            try{
                option = Integer.valueOf(this.keyboard.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }

            switch(option){
                case 1:
                    try {
                        this.addObject();
                    }
                    catch (Exception exc) {
                        System.out.print(exc.getMessage());
                    }
                    finally {
                        break;
                    }
                case 2:
                    try {
                        this.deleteObject();
                    }
                    catch (Exception exc) {
                        System.out.print(exc.getMessage());
                    }
                    finally {
                        break;
                    }
                case 3:
                    this.printRequestedObjects();
                    break;
//                case 4:
//                    this.printAllObjects();
//                    break;
                case 0:
                    stop = true;
                    break;
                default:
                    System.out.println("Wrong cmd!");
                    break;
            }
        }
    }
}
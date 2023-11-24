package Controller;

import Model.GenericVehicle;

import Repository.*;

public class Controller {
    private RepositoryInterface repo = new Repository();

    public void add(GenericVehicle vehicle) throws Exception{
        if (vehicle.getPrice()<0) {
            throw new Exception("Price cannot be negative!");
        }
        if (repo.isFull())
        {
            throw new Exception("Cannot insert objects anymore!");
        }
        this.repo.add(vehicle);
    }

    public void delete(int index) throws Exception{
        if (index>=this.repo.getSize() || index<0) {
            throw new Exception("Index not in [0, length) range.");
        }
        this.repo.delete(index);
    }

    public GenericVehicle[] getWantedVehicles(){
        GenericVehicle[] allVehicles = repo.getList();
        GenericVehicle[] newVehicles = new GenericVehicle[10];
        int newLength = 0;
        for (int i=0;i<repo.getSize();i++)
            if(allVehicles[i].getPrice()>1000)
                newVehicles[newLength++] = allVehicles[i];
        return newVehicles;
    }

}
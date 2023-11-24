package Repository;

import Model.GenericVehicle;

public class Repository implements RepositoryInterface{
    private GenericVehicle[] list;
    private int length = 0;
    public Repository(){
        list = new GenericVehicle[100];
    }

    @Override
    public void add(GenericVehicle vehicle){
        list[this.length++] = vehicle;
    }

    @Override
    public void delete(int index) {
        length--;
        for (int i = index; i<this.length; i++){
            list[i] = list[i +1];
        }
    }

    @Override
    public int getSize(){
        return this.length;
    }

    public GenericVehicle[] getList(){return this.list;}

    @Override
    public boolean isFull()
    {
        return this.length==100;
    }
}
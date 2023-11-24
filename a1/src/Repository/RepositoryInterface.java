package Repository;

import Model.GenericVehicle;

public interface RepositoryInterface {
    public void add(GenericVehicle vehicle);
    public void delete(int index);
    public int getSize();
    public boolean isFull();
    public GenericVehicle[] getList();
}
public class Vehicle implements IManipulate
{
    // declare Vehicle variables
    private String vehicleName;
    private int vehicleYear;

    // constructor
    public Vehicle(String vehicleName, int vehicleYear)
    {
        this.vehicleName = vehicleName;
        this.vehicleYear = vehicleYear;
    }

    // get and set methods
    public String getVehicleName()
    {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName)
    {
        this.vehicleName = vehicleName;
    }

    public int getVehicleYear()
    {
        return vehicleYear;
    }

    public void setVehicleYear(int vehicleYear)
    {
        this.vehicleYear = vehicleYear;
    }

    // interface methods
    @Override
    public void drawObject()
    {
        System.out.println("Drawing a vehicle.");
    }

    @Override
    public void rotateObject()
    {
        System.out.println("Rotating a vehicle.");
    }

    @Override
    public void resizeObject()
    {
        System.out.println("Resizing a vehicle.");
    }

    @Override
    public void playSound()
    {
        System.out.println("Vehicle sound.");
    }

}
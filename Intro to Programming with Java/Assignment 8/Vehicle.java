public class Vehicle implements IManipulate
{
    // declare Vehicle variables
    private String vehicleName;
    private int vehicleYear;

    // constructor
    public Vehicle(String name, int year)
    {
        vehicleName = name;
        vehicleYear = year;
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
    public void drawObject()
    {
        System.out.println("Drawing a vehicle.");
    }

    public void rotateObject()
    {
        System.out.println("Rotating a vehicle.");
    }

    public void resizeObject()
    {
        System.out.println("Resizing a vehicle.");
    }

    public void playSound()
    {
        System.out.println("Vehicle sound.");
    }

}
public class Animal implements IManipulate
{
    // declare Vehicle variables
    private String animalName;
    private int animalAge;

    // constructor
    public Animal(String animalName, int animalAge)
    {
        this.animalName = animalName;
        this.animalAge = animalAge;
    }

    // get and set methods
    public String getAnimalName()
    {
        return animalName;
    }

    public void setAnimalName(String animalName)
    {
        this.animalName = animalName;
    }

    public int getAnimalAge()
    {
        return animalAge;
    }

    public void setAnimalAge(int animalAge)
    {
        this.animalAge = animalAge;
    }

    // interface methods
    @Override
    public void drawObject()
    {
        System.out.println("Drawing an animal.");
    }

    @Override
    public void rotateObject()
    {
        System.out.println("Rotating an animal.");
    }

    @Override
    public void resizeObject()
    {
        System.out.println("Resizing an animal.");
    }

    @Override
    public void playSound()
    {
        System.out.println("Animal sound.");
    }

}
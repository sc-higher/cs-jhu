public class Animal implements IManipulate
{
    // declare Vehicle variables
    private String animalName;
    private int animalAge;

    // constructor
    public Animal(String name, int age)
    {
        animalName = name;
        animalAge = age;
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
    public void drawObject()
    {
        System.out.println("Drawing an animal.");
    }

    public void rotateObject()
    {
        System.out.println("Rotating an animal.");
    }

    public void resizeObject()
    {
        System.out.println("Resizing an animal.");
    }

    public void playSound()
    {
        System.out.println("Animal sound.");
    }

}
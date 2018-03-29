/**
 * This program is my response to Assignment 8 for the
 * class 605.201.81 Intro to Programming Using Java at
 * the JHU EPP CS program.
 *
 * <TEXT HERE>
 *
 * @author: Sean Connor
 */

public class ManipulateAnimals
{
    public static void main(String[] args)
    {
        // create array of type Drawable
        IManipulate[] objectArray = new IManipulate[4];

        // create Animal and Vehicle objects
        IManipulate animal1 = new Animal("Shadow", 7);
        IManipulate animal2 = new Animal("Sassy",5);
        IManipulate vehicle1 = new Vehicle("Ford F150", 2005);
        IManipulate vehicle2 = new Vehicle("Toyota Prius", 2012);

        // assign array indices to created Animal and Vehicle objects
        objectArray[0] = animal1;
        objectArray[1] = vehicle1;
        objectArray[2] = animal2;
        objectArray[3] = vehicle2;

        // loop through array and execute methods
        for (int i=0; i<4; i++)
        {
            objectArray[i].drawObject();
            objectArray[i].rotateObject();
            objectArray[i].resizeObject();
            objectArray[i].playSound();
            System.out.println();
        }
    }
}
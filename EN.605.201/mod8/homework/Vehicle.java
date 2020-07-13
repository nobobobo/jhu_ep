/**
 * Vehicle class
 */
public class Vehicle implements MyInterface {
    private String name;
    private int age;

    /**
     * Constructor
     */
    Vehicle() {
        this.name = "";
        this.age = 0;
    }

    /**
     * Name setter
     */
    public void SetName(String name) {
        this.name = name;
    }

    /**
     * Age Setter
     */
    public void SetAge(int age) {
        this.age = age;
    }

    /**
     * Name Getter
     */
    public String getName() {
        return this.name;
    }

    /**
     * Age getter
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Methods implemented by interface MyInterface:
     * void drawObject() 
     * void rotateObject() 
     * void resizeObject() 
     * void playSound()
     */
    public void drawObject() {
        System.out.println("Drawing a Vehicle");
    }

    public void rotateObject() {
        System.out.println("Rotating a Vehicle");
    }

    public void resizeObject() {
        System.out.println("Resizing a Vehicle");
    }

    public void playSound() {
        System.out.println("Vehicle Sound");

    }

}
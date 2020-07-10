public class Vehicle implements MyInterface{
    private String name;
    private int age;

    Vehicle() {
        this.name = "";
        this.age = 0;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public void SetAge(int age) {
        this.age = age;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public void drawObject(){
        System.out.println("Drawing a Vehicle");
    }

    public void rotateObject(){
        System.out.println("Rotating a Vehicle");
    }

    public void resizeObject(){
        System.out.println("Resizing a Vehicle");
    }

    public void playSound(){
        System.out.println("Vehicle Sound");

    }

}
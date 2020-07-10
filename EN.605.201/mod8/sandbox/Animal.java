/**
 * Animal class
 */
public class Animal implements MyInterface{
    private String name;

    /**
     * Constructor
     */
    Animal(){
        this.name = "";
    }

    /**
     * Name setter
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Name getter
     * @return Name
     */
    public String getName(){
        return this.name;
    }

    /** 
     * void drawObject()
     */
    public void drawObject(){
        System.out.println("Drawing an Animal");
    }

    /**
     * void rotateObject()
     */
    public void rotateObject(){
        System.out.println("Rotating an Animal");

    }

    /**
     * void resizeObject()
     */
    public void resizeObject(){
        System.out.println("Resizing an Animal");
    }

    /**
     * void playSound()
     */
    public void playSound(){
        System.out.println("Animal Sound");
    }
}
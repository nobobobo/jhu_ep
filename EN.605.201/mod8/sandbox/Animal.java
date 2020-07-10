public class Animal implements MyInterface{
    private String name;

    Animal(){
        this.name = "";
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void drawObject(){
        System.out.println("Drawing an Animal");
    }

    public void rotateObject(){
        System.out.println("Rotating an Animal");

    }

    public void resizeObject(){
        System.out.println("Resizing an Animal");
    }

    public void playSound(){
        System.out.println("Animal Sound");
    }
}
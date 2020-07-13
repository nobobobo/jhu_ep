public class ManipulateAnimals {

    /**
     * main method
     * @param args
     * 
     * creates an array of MyInterface objects storeing two Animal instances and two Vehicle instances.
     * Iteratively access each instance and run methods polymorphismly
     */
    public static void main(String args[]){
        MyInterface[] objects = {
            new Animal(), new Vehicle(), new Animal(), new Vehicle()
        };

        for (int i = 0; i< objects.length; i++){
            System.out.println("Object "+(i+1) + ":");
            objects[i].drawObject();
            objects[i].rotateObject();
            objects[i].resizeObject();
            objects[i].playSound();
            System.out.println();
        }
    }
    
}

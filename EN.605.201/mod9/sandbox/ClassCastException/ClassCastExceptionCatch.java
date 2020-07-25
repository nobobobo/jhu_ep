public class ClassCastExceptionCatch {
    public static void main(String[] args) {
        try {
            Animal dog = new Dog("Pug");
            Animal dog2cat = (Cat)dog;
        } catch (ClassCastException exception){
            System.out.println("This program caught: " + exception);
        }
    }

}

class Animal{
    private String name;

    Animal(String name){
        this.name = name;
    }

}

class Dog extends Animal{
    
    Dog(String name){
        super(name);
    }

}

class Cat extends Animal{

    Cat(String name){
        super(name);
    }

}
import javafx.event.*;

public class ButtonHandlingDemo extends Application{
    private Button myButton;

    public void start (Stage myStage){
        myState.setTitle("Button Handling Demo");
        FlowPane rootNode = new FlowPane(10,10);
        rootNode.setAlignment(Pos.CENTER);

        Scene myScene = new Scene(rootNode, 300, 200);

        myButton = new Button("Click me");

        myButton.setOnAction(new ButtonHandler());
    }

    class ButtonHandler(ActionEvent e){
        myButton.setText("Clicked");
    }
}


export PATH_TO_FX=/Users/nobo/dev/javafx-sdk-14.0.2.1/lib
javac --module-path $PATH_TO_FX --add-modules javafx.controls MyFirstFxApp.java 
java --module-path $PATH_TO_FX --add-modules javafx.controls Assignment12_nhayashi.java 
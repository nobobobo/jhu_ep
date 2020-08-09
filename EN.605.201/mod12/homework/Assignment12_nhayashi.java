import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.geometry.*;

public class Assignment12_nhayashi extends Application{
    public void start (Stage myStage){
        myStage.setTitle("Simple Calculator");
        myStage.setResizable(false);
        GridPane rootNode = new GridPane();
        rootNode.setHgap(5);
        rootNode.setVgap(5);
        rootNode.setAlignment(Pos.CENTER);

        Scene myScene = new Scene(rootNode, 300, 200);

        Label num1Label = new Label("First Value: ");
        Label num2Label = new Label("Second Value: ");
        Label sumLabel = new Label("Sum is: ");

        TextField num1Field = new TextField();
        TextField num2Field = new TextField();
        TextField sumField = new TextField();
        sumField.setEditable(false);

        Button aButton = new Button("Calculate");

        rootNode.add(num1Label, 0,0);
        rootNode.add(num1Field, 1,0);
        rootNode.add(num2Label, 0,1);
        rootNode.add(num2Field, 1,1);
        rootNode.add(sumLabel, 0,2);
        rootNode.add(sumField, 1,2);

        rootNode.add(aButton,1,3);
        rootNode.setHalignment(aButton, HPos.RIGHT);
        
        myStage.setScene(myScene);
        myStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
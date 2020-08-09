import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.geometry.*;

public class MyFirstFxApp extends Application{
    public void start (Stage myStage){
        myStage.setTitle("My First Java FX Application");
        FlowPane rootNode = new FlowPane();
        Scene myScene = new Scene(rootNode, 300, 200);

        Label num1Label = new Label("First Value: ");
        Label num2Label = new Label("Second Value: ");
        Label sumLabel = new Label("Sum is: ");

        TextField num1Field = new TextField();
        TextField num2Field = new TextField();
        TextField sumField = new TextField();
        sumField.setEditable(false);

        rootNode.getChildren().addAll(num1Label, num1Field, num2Label, num2Field, sumLabel, sumField);
        myStage.setScene(myScene);
        myStage.show();
    }
}
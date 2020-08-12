import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.event.*;

/**
 * <h2> Loan Calculator </h2> is a Java-based GUI application 
 * calculates amounts of monthly and total payment given the information of Annucal Interest Rate, # Of Year and loan amount
 *  
 * @author  Noboru Hayashi
 * @version 1.0
 * @since   2020-08-11
 * 
 */
public class LoanCalculator extends Application{
    private Button calcButton;
    private TextField airField;
    private TextField noyField;
    private TextField laField;
    private TextField mpField;
    private TextField tpField;

    /**
     * Inner class for handling the click event on the button
     */
    class ButtonHandler implements EventHandler<ActionEvent>{
 
        /**
         * handle method: calculate monthly payment amount and total payment by getting string input from multiple text fields
         */
        public void handle(ActionEvent e){   
            if (isDouble(airField.getText()) && 
                isInt(noyField.getText()) &&
                isDouble(laField.getText()) ){
                    double i = Double.parseDouble(airField.getText()) / 100 / 12;
                    int n = Integer.parseInt(noyField.getText()) * 12;
                    double a = Double.parseDouble(laField.getText());

                    double mp = (i*a)/ (1 - Math.pow(1+i,-n));
                    mpField.setText(String.format("%.2f", mp));

                    double tp = n * mp;
                    tpField.setText(String.format("%.2f", tp));
                } else {
                    mpField.setText("ERR: Please enter");
                    tpField.setText("valid numbers.");
                }
                
        }
    }

    /**
     * start method for Loan Calculator application
     */
    public void start (Stage stage){
        // initialize state
        stage.setTitle("Loan Calculator");
        stage.setResizable(false);

        // initialize grid pane as rootnode
        GridPane rootNode = new GridPane();
        rootNode.setHgap(5);
        rootNode.setVgap(5);
        rootNode.setAlignment(Pos.CENTER);

        Scene scene = new Scene(rootNode, 450, 300);

        // define labels
        Label airLabel = new Label("Annual Interest Rate: ");
        Label noyLabel = new Label("Number of Years: ");
        Label laLabel = new Label("Loan Amount: ");
        Label mpLabel = new Label("Monthly Payment: ");
        Label tpLabel = new Label("Total Payment: ");
 

        // init text fields
        airField = new TextField();
        noyField = new TextField();
        laField = new TextField();
        mpField = new TextField();
        mpField.setEditable(false);
        tpField = new TextField();
        tpField.setEditable(false);

        // init button, and add button handler to id
        calcButton = new Button("Calculate");
        calcButton.setOnAction(new ButtonHandler());

        // add nodes to the grid pane by indices
        rootNode.add(airLabel, 0,0);
        rootNode.add(airField, 1,0);
        rootNode.add(noyLabel, 0,1);
        rootNode.add(noyField, 1,1);
        rootNode.add(laLabel, 0,2);
        rootNode.add(laField, 1,2);
        rootNode.add(mpLabel, 0,3);
        rootNode.add(mpField, 1,3);
        rootNode.add(tpLabel, 0,4);
        rootNode.add(tpField, 1,4);

        rootNode.add(calcButton,1,5);
        
        // set asslignment for the button
        rootNode.setHalignment(calcButton, HPos.RIGHT);
        
        // setting the scene to the stage and show.
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Helper function for examining the input string is numeric(double) or not
     */
    public static boolean isDouble(String str){
        if (str == null){
            return false;
        } 
        try {
            double d = Double.parseDouble(str);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * Helper function for examining the input string is numeric(integer) or not
     */
    public static boolean isInt(String str){
        if (str == null){
            return false;
        } 
        try {
            int d = Integer.parseInt(str);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
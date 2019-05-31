package sample;

import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.layout.GridPane;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static java.lang.Character.isUpperCase;

public class DataEntryGUI extends Application {

    public static void setNamePrompt(TextField name){
        name.setPromptText("Name");
    }

    public static void setTelephonePrompt(TextField telephone){
        telephone.setPromptText("(###) ###-####");
    }

    public static void nameInvalidityHelper(TextField name){
        String text = name.getText();
        if (text.contains(" ")){ // if input is separated into words
            String[] textArr = text.split(" ");
            if (textArr.length > 2){ // if more than 2 words are put in
                name.setStyle("-fx-text-inner-color: red;");
            }
            else { //if 2 words are put in
                if ((textArr[0].length() + textArr[1].length()) > 20){ // if name length is more than 20 char
                    name.setStyle("-fx-text-inner-color: red;");
                }
                else { // if name length is good
                    if (!(isUpperCase(textArr[0].charAt(0)))) { // if first character of first word ISN'T uppercase
                        name.setStyle("-fx-text-inner-color: red;");
                    } else { // if first character of first word IS uppercase
                        textArr[0] = textArr[0].substring(1);
                        if (textArr[0].equals(textArr[0].toLowerCase())) { // if all letters after first letter are lower case
                            name.setStyle("-fx-text-inner-color: black;");
                        } else {
                            name.setStyle("-fx-text-inner-color: red;");
                        }
                    }
                    if (!(isUpperCase(textArr[1].charAt(0)))) { //if first character of second word ISN'T uppercase
                        name.setStyle("-fx-text-inner-color: red;");
                    } else { //if first character of second word IS uppercase
                        textArr[1] = textArr[1].substring(1);
                        if (textArr[1].equals(textArr[1].toLowerCase())) { // if all letters after first letter are lower case
                            name.setStyle("-fx-text-inner-color: black;");
                        } else {
                            name.setStyle("-fx-text-inner-color: red;");
                        }
                    }
                }
            }
        }
        else{ //input is one word
            name.setStyle("-fx-text-inner-color: red;");
        }
    }

    public static void telephoneInvalidityHelper(TextField telephone){
        String text = telephone.getText();
        if (text.length() != 14){
            telephone.setStyle("-fx-text-inner-color: red;");
        }
        else{
            if (!(text.charAt(0) == '(')){
                telephone.setStyle("-fx-text-inner-color: red;");
            }
            else if (!(text.charAt(4) == ')')){
                telephone.setStyle("-fx-text-inner-color: red;");
            }
            else if (!(text.charAt(5) == ' ')){
                telephone.setStyle("-fx-text-inner-color: red;");
            }
            else if (!(text.charAt(9) == '-')){
                telephone.setStyle("-fx-text-inner-color: red;");
            }
            else if (!(Character.isDigit(text.charAt(1))) ||
                    !(Character.isDigit(text.charAt(2))) ||
                    !(Character.isDigit(text.charAt(3))) ||
                    !(Character.isDigit(text.charAt(6))) ||
                    !(Character.isDigit(text.charAt(7))) ||
                    !(Character.isDigit(text.charAt(8))) ||
                    !(Character.isDigit(text.charAt(10))) ||
                    !(Character.isDigit(text.charAt(11))) ||
                    !(Character.isDigit(text.charAt(12))) ||
                    !(Character.isDigit(text.charAt(13)))){
                telephone.setStyle("-fx-text-inner-color: red;");
            }
            else{
                telephone.setStyle("-fx-text-inner-color: black;");
            }
        }
    }

    public static boolean isNameColorBlack(TextField name){
        return name.getStyle().equals("-fx-text-inner-color: black;");
    }

    public static boolean isTelephoneColorBlack(TextField telephone){
        return telephone.getStyle().equals("-fx-text-inner-color: black;");
    }

    public static boolean canCreateProfiles(TextField name1, TextField name2, TextField name3, TextField telephone1, TextField telephone2, TextField telephone3) {
        return name1.getText().isEmpty() ||
                name2.getText().isEmpty() ||
                name3.getText().isEmpty() ||
                telephone1.getText().isEmpty() ||
                telephone2.getText().isEmpty() ||
                telephone3.getText().isEmpty();
    }

    public static boolean invalidInformationExists(TextField name1, TextField name2, TextField name3, TextField telephone1, TextField telephone2, TextField telephone3) {
        return !(isNameColorBlack(name1)) ||
                !(isNameColorBlack(name2)) ||
                !(isNameColorBlack(name3)) ||
                !(isTelephoneColorBlack(telephone1)) ||
                !(isTelephoneColorBlack(telephone2)) ||
                !(isTelephoneColorBlack(telephone3));
    }

    public static boolean validInformationExists(TextField name1, TextField name2, TextField name3, TextField telephone1, TextField telephone2, TextField telephone3){
        return isNameColorBlack(name1) &&
                isNameColorBlack(name2) &&
                isNameColorBlack(name3) &&
                isTelephoneColorBlack(telephone1) &&
                isTelephoneColorBlack(telephone2) &&
                isTelephoneColorBlack(telephone3);
    }

    @Override
    public void start(Stage primaryStage){

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        TextField name1 = new TextField();
        setNamePrompt(name1);
        name1.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue){ //when user tabs to next slot
                nameInvalidityHelper(name1);
            }
        });
        pane.add(name1, 0, 0);

        TextField name2 = new TextField();
        setNamePrompt(name2);
        name2.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue){ //when user tabs to next slot
                nameInvalidityHelper(name2);
            }
        });
        pane.add(name2, 0, 1);

        TextField name3 = new TextField();
        setNamePrompt(name3);
        name3.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue){ //when user tabs to next slot
                nameInvalidityHelper(name3);
            }
        });
        pane.add(name3, 0, 2);

        TextField telephone1 = new TextField();
        setTelephonePrompt(telephone1);
        telephone1.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue){ //when user tabs to next slot
                telephoneInvalidityHelper(telephone1);
            }
        });
        pane.add(telephone1, 1, 0);

        TextField telephone2 = new TextField();
        setTelephonePrompt(telephone2);
        telephone2.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue){ //when user tabs to next slot
                telephoneInvalidityHelper(telephone2);
            }
        });
        pane.add(telephone2, 1, 1);

        TextField telephone3 = new TextField();
        setTelephonePrompt(telephone3);
        telephone3.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue){ //when user tabs to next slot
                telephoneInvalidityHelper(telephone3);
            }
        });
        pane.add(telephone3, 1, 2);

        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(name1.textProperty(),
                        name2.textProperty(),
                        name3.textProperty(),
                        telephone1.textProperty(),
                        telephone2.textProperty(),
                        telephone3.textProperty());
            }

            @Override
            protected boolean computeValue(){
                return (canCreateProfiles(name1, name2, name3, telephone1, telephone2, telephone3));
            }
        };

        Button btAdd = new Button("Create Profiles");
        btAdd.disableProperty().bind(bb);
        btAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){

                GridPane newWindow = new GridPane();

                newWindow.setAlignment(Pos.CENTER);
                newWindow.setHgap(5.5);
                newWindow.setVgap(5.5);

                Button closeButton = new Button("Close");
                closeButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Stage stage = (Stage) closeButton.getScene().getWindow();
                        stage.close();
                    }
                });
                newWindow.add(closeButton, 0, 1);
                GridPane.setHalignment(closeButton, HPos.CENTER);

                Label informUser = null;
                boolean savedTitle = false;

                if (validInformationExists(name1, name2, name3, telephone1, telephone2, telephone3)){
                    name1.setDisable(true);
                    name2.setDisable(true);
                    name3.setDisable(true);
                    telephone1.setDisable(true);
                    telephone2.setDisable(true);
                    telephone3.setDisable(true);
                    informUser = new Label("The profiles have been saved and added to the database.");
                    savedTitle = true;
                }
                if (invalidInformationExists(name1, name2, name3, telephone1, telephone2, telephone3)) {
                    informUser = new Label("Invalid input: you have attempted to provide one or more invalid input(s). Please correct the information displayed in red and retry.");
                    savedTitle = false;
                }

                newWindow.add(informUser, 0, 0);
                GridPane.setHalignment(informUser, HPos.CENTER);

                Scene scene = new Scene(newWindow, 800, 100);
                Stage sndWindow = new Stage();
                if (savedTitle){
                    sndWindow.setTitle("Data Saved");
                }
                else{
                    sndWindow.setTitle("Invalid Input Error");
                }
                sndWindow.setScene(scene);

                sndWindow.show();
            }
        });

        pane.add(btAdd, 0, 3, 2, 1);
        GridPane.setHalignment(btAdd, HPos.CENTER);

        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Data Entry GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

package com.mycompany.tv2guitest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class SecondaryController implements Initializable {   

    @FXML
    private AnchorPane backgroundAP;
    @FXML
    private Polygon backgroundShade;
    @FXML
    private AnchorPane usernameBackground;
    @FXML
    private AnchorPane passwordBackground;
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Circle loginCircle;
    @FXML
    private Circle loginCircle1;
    @FXML
    private Circle loginCircle2;
    @FXML
    private Rectangle minimizeRectangle;
    @FXML
    private Rectangle maximizeRectangle;
    @FXML
    private StackPane closeButton;
    @FXML
    private Rectangle closeRectangle1;
    @FXML
    private Rectangle closeRectangle2;
    @FXML
    private AnchorPane topDragBar;
    @FXML
    private StackPane loginButton;
    @FXML
    private Rectangle loginRectangle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // create stops 
        Stop[] stop = {new Stop(0, Color.valueOf(Info.accentStartColor)), new Stop(1, Color.valueOf(Info.accentEndColor))};
        // create a Linear gradient object 
        LinearGradient accentGradient = new LinearGradient(0, 1, 1, 0, true, CycleMethod.NO_CYCLE, stop);

        loginCircle.setFill(accentGradient);
        loginCircle1.setFill(accentGradient);
        loginCircle2.setFill(accentGradient);

        // BUTTONS
        // Set the color of the buttons
        loginRectangle.setFill(accentGradient);

        closeRectangle1.setFill(accentGradient);
        closeRectangle2.setFill(accentGradient);
        maximizeRectangle.setFill(accentGradient);
        minimizeRectangle.setFill(accentGradient);

        usernameBackground.setStyle("-fx-background-radius: " + Info.roundAmount + "; -fx-border-radius: " + Info.roundAmount + "; -fx-background-color: " + Info.forgroundColor + ";");
        passwordBackground.setStyle("-fx-background-radius: " + Info.roundAmount + "; -fx-border-radius: " + Info.roundAmount + "; -fx-background-color: " + Info.forgroundColor + ";");

        // BACKGROUND
        // Set the color of the shade in the background
        backgroundShade.setFill(Paint.valueOf(Info.backgroundShadeColor));
        backgroundAP.setStyle("-fx-background-color: " + Info.backgroundColor + ";");
    }
    
    @FXML
    private void handleDeselect() {
        backgroundAP.requestFocus();
    }

    @FXML
    private void handleButtonHoveringEnter(MouseEvent event) {
        StackPane hoveredObject = ((StackPane) event.getSource());
        hoveredObject.setScaleX(hoveredObject.getScaleX() + Info.scaleAmount);
        hoveredObject.setScaleY(hoveredObject.getScaleY() + Info.scaleAmount);
    }

    @FXML
    private void handleButtonHoveringExit(MouseEvent event) {
        StackPane hoveredObject = ((StackPane) event.getSource());
        hoveredObject.setScaleX(hoveredObject.getScaleX() - Info.scaleAmount);
        hoveredObject.setScaleY(hoveredObject.getScaleY() - Info.scaleAmount);
    }

    @FXML
    private void usernameAction() throws IOException {
        if ("".equals(passwordText.getText().trim())) {
            passwordText.requestFocus();
        } else {
            loginAction();
        }
    }

    @FXML
    private void passwordAction() throws IOException {
        loginAction();
    }

    @FXML
    private void minimizeAction() {
        App.stage.setIconified(true);
    }

    @FXML
    private void maximizeAction() {
        if (!App.stage.isMaximized()) {
            System.out.println("fullscreen");
            App.stage.setMaximized(true);
        } else {
            System.out.println("not fullscreen");
            App.stage.setMaximized(false);
        }
    }

    @FXML
    private void closeButtonAction() {
        Platform.exit();
    }

    @FXML
    private void handleTopDragDragged(MouseEvent event) {
        App.stage.setX(event.getScreenX() + App.xOffset);
        App.stage.setY(event.getScreenY() + App.yOffset);
    }

    // Handles 
    @FXML
    private void handleTopDragPressed(MouseEvent event) {
        App.xOffset = App.stage.getX() - event.getScreenX();
        App.yOffset = App.stage.getY() - event.getScreenY();
    }

    @FXML
    private void loginAction() throws IOException {
        if (Info.username.equals(usernameText.getText()) && Info.password.equals(passwordText.getText())) {
            App.setRoot("primary");
            System.out.println("CORRECT");
        } else {
            System.out.println("WRONG");
        }
        System.out.println(usernameText.getText());
    }
}
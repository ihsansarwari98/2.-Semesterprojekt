package com.mycompany.creditsystem.presentation;

import com.mycompany.creditsystem.presentation.Info;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SwitchButton extends StackPane {
    private final Rectangle background = new Rectangle(40, 25);
    private final Button button = new Button();
    private String buttonStyleOff = "-fx-background-color: " + Info.backgroundColor + ";";
    private String buttonStyleOn = "-fx-background-color: " + Info.backgroundColor + ";";
    private boolean state;

    private void init() {
        getChildren().addAll(background, button);
        setMinSize(40, 25);
        background.setArcHeight(background.getHeight());
        background.setArcWidth(background.getHeight());
        background.setFill(Paint.valueOf(Info.forgroundColor));
        double r = 2.0;
        button.setShape(new Circle(r));
        setAlignment(button, Pos.CENTER_LEFT);
        setMargin(button, new Insets(0,0,0,4));
        button.setMaxSize(15, 15);
        button.setMinSize(15, 15);
        button.setStyle(buttonStyleOff);
    }

    public SwitchButton() {
        init();
        EventHandler<Event> click = new EventHandler<Event>() {
            @Override
            public void handle(Event e) {
                if (state) {
                    button.setStyle(buttonStyleOff);
                    background.setFill(Paint.valueOf(Info.forgroundColor));
                    setAlignment(button, Pos.CENTER_LEFT);
                    setMargin(button, new Insets(0,0,0,4));
                    state = false;
                } else {
                    button.setStyle(buttonStyleOn);
                    background.setFill(Info.accentGradient);
                    setAlignment(button, Pos.CENTER_RIGHT);
                    setMargin(button, new Insets(0,4,0,0));
                    state = true;
                }
            }
        };

        button.setFocusTraversable(false);
        setOnMouseClicked(click);
        button.setOnMouseClicked(click);
    }

    public boolean isState() {
        return state;
    }
}
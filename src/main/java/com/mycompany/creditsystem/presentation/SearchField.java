package com.mycompany.creditsystem.presentation;

import javafx.geometry.NodeOrientation;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class SearchField {
    private StackPane stackPane;
    private Rectangle rectangle;
    private VBox vBox;
    private HBox hbox;
    private AnchorPane anchorPaneBackground;
    private TextField textField;
    private ScrollPane scrollPane;
    private VBox vBoxResults;
    private ArrayList searchResults;

    public SearchField(StackPane stackPane, Rectangle rectangle, VBox vBox, AnchorPane anchorPaneBackground, HBox hbox, TextField textField, ScrollPane scrollPane, VBox vBoxResults) {
        this.stackPane = stackPane;
        this.rectangle = rectangle;
        this.vBox = vBox;
        this.anchorPaneBackground = anchorPaneBackground;
        this.hbox = hbox;
        this.textField = textField;
        this.scrollPane = scrollPane;
        this.vBoxResults = vBoxResults;
    }

    public SearchField() {
        this.stackPane = new StackPane();
        this.rectangle = new Rectangle();
        this.vBox = new VBox();
        this.anchorPaneBackground = new AnchorPane();
        this.hbox = new HBox();
        this.textField = new TextField();
        this.scrollPane = new ScrollPane();
        this.vBoxResults = new VBox();
    }


    public void setSearchResults(ArrayList searchResults) {
        this.searchResults = searchResults;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    @Override
    public String toString() {
        return textField.getText();
    }

    public ArrayList getSearchResults() {
        return searchResults;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public VBox getvBoxResults() {
        return vBoxResults;
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    public AnchorPane getAnchorPaneBackground() {
        return anchorPaneBackground;
    }

    public HBox getHbox() {
        return hbox;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public TextField getTextField() {
        return textField;
    }

    public VBox getvBox() {
        return vBox;
    }

}

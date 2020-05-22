package com.mycompany.creditsystem.presentation;

import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
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

    /*
    stackpane
        rectangle
        vBox
            anchorPaneBackground
                hBox
                    textField
            scrollpane
                vBoxResults
     */

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

    public SearchField(StackPane stackPane) {
        this.stackPane = stackPane;
        this.rectangle = (Rectangle) stackPane.getChildren().get(0);
        this.vBox = (VBox) stackPane.getChildren().get(1);
        this.anchorPaneBackground = (AnchorPane) vBox.getChildren().get(0);
        this.hbox  = (HBox) anchorPaneBackground.getChildren().get(0);

        for (int i = 0; i < hbox.getChildren().size(); i++) {
            if (hbox.getChildren().get(i) instanceof TextField) {
                this.textField = (TextField) hbox.getChildren().get(i);
            }
        }

        this.scrollPane = (ScrollPane) vBox.getChildren().get(1);
        this.vBoxResults = (VBox) scrollPane.getContent();
    }

    public static SearchField createSearchField(SearchStatus userData, String textFieldText, int width, NodeOrientation orientation) {
        SearchField searchField = new SearchField();

        // Setup the order of the searchField
        searchField.getStackPane().getChildren().addAll(searchField.getRectangle(), searchField.getvBox());
        searchField.getvBox().getChildren().addAll(searchField.getAnchorPaneBackground(), searchField.getScrollPane());
        searchField.getAnchorPaneBackground().getChildren().add(searchField.getHbox());
        searchField.getHbox().getChildren().add(searchField.getTextField());
        searchField.getScrollPane().setContent(searchField.getvBoxResults());

        // assign the parameters
        searchField.getTextField().setUserData(userData);
        searchField.getTextField().setText(textFieldText);
        searchField.getRectangle().setWidth(width);
        searchField.getStackPane().setUserData(orientation);

        // Set other properties
        HBox.setHgrow(searchField.getTextField(), Priority.ALWAYS);
        searchField.getTextField().setStyle("-fx-font-size: " + Info.fontSizeDefault + "; -fx-text-fill: " + Info.backgroundColor + ";");
        searchField.getTextField().applyCss();
        searchField.getTextField().applyCss();
        searchField.getAnchorPaneBackground().applyCss();
        searchField.getRectangle().setHeight(searchField.getAnchorPaneBackground().prefHeight(-1));

        searchField.getvBox().setFillWidth(true);
        searchField.getvBoxResults().setFillWidth(true);
        searchField.getScrollPane().setFitToWidth(true);
        searchField.getScrollPane().setFitToHeight(true);

        // handle orientation
        if (orientation.equals(NodeOrientation.LEFT_TO_RIGHT)) {
            searchField.getStackPane().setAlignment(Pos.TOP_LEFT);
            searchField.getvBox().setAlignment(Pos.TOP_LEFT);
            searchField.getHbox().setAlignment(Pos.TOP_LEFT);
            StackPane.setAlignment(searchField.getRectangle(), Pos.TOP_LEFT);
            searchField.getvBox().setAlignment(Pos.TOP_LEFT);
            searchField.getvBoxResults().setAlignment(Pos.TOP_LEFT);
            searchField.getStackPane().setAlignment(Pos.CENTER_LEFT);
            searchField.getTextField().setAlignment(Pos.CENTER_LEFT);

            AnchorPane.setTopAnchor(searchField.getHbox(), (double) 0);
            AnchorPane.setRightAnchor(searchField.getHbox(), (double) 0);
            AnchorPane.setLeftAnchor(searchField.getHbox(), (double) 10);
            AnchorPane.setBottomAnchor(searchField.getHbox(), (double) 0);

        } else {
            searchField.getStackPane().setAlignment(Pos.TOP_RIGHT);
            searchField.getvBox().setAlignment(Pos.TOP_RIGHT);
            searchField.getHbox().setAlignment(Pos.TOP_RIGHT);
            StackPane.setAlignment(searchField.getRectangle(), Pos.TOP_RIGHT);
            searchField.getvBox().setAlignment(Pos.TOP_RIGHT);
            searchField.getvBoxResults().setAlignment(Pos.TOP_RIGHT);
            searchField.getStackPane().setAlignment(Pos.CENTER_RIGHT);
            searchField.getTextField().setAlignment(Pos.CENTER_RIGHT);

            AnchorPane.setTopAnchor(searchField.getHbox(), (double) 0);
            AnchorPane.setRightAnchor(searchField.getHbox(), (double) 10);
            AnchorPane.setLeftAnchor(searchField.getHbox(), (double) 0);
            AnchorPane.setBottomAnchor(searchField.getHbox(), (double) 0);
        }

        return searchField;
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

    public void setAnchorPaneBackground(AnchorPane anchorPaneBackground) {
        this.anchorPaneBackground = anchorPaneBackground;
    }

    public void setHbox(HBox hbox) {
        this.hbox = hbox;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setScrollPane(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public void setvBoxResults(VBox vBoxResults) {
        this.vBoxResults = vBoxResults;
    }
}

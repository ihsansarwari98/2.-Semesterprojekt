package com.mycompany.tv2guitest;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class PrimaryController implements Initializable, BaseController {

    @FXML
    private VBox programList;
    @FXML
    private TextField textFieldSearchBar;
    @FXML
    private StackPane closeButton;
    @FXML
    private AnchorPane menuCurve;
    @FXML
    private AnchorPane searchBarBackground;
    @FXML
    private AnchorPane backgroundAP;
    @FXML
    private VBox sidePanelBackground;
    @FXML
    private ScrollPane productionScrollPane;
    @FXML
    private Label nameLabel;
    @FXML
    private AnchorPane sideBar;
    @FXML
    private Polygon backgroundShade;
    @FXML
    private Polygon sideBarTopColor;
    @FXML
    private Rectangle buttomCurveColor;
    @FXML
    private Rectangle topCurveColor;
    @FXML
    private Circle buttomCurveOverlap;
    @FXML
    private Circle topCurveOverlap;
    @FXML
    private Rectangle closeRectangle1;
    @FXML
    private Rectangle closeRectangle2;
    @FXML
    private Rectangle maximizeRectangle;
    @FXML
    private Rectangle minimizeRectangle;
    @FXML
    private Circle loginCircle;
    @FXML
    private Rectangle searchRectangleBG;
    @FXML
    private ScrollPane searchResultScrollPane;
    @FXML
    private VBox searchResults;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateProperties();
        Info.sidePanelOn = true;

        // TRASH FOR TESTING
        Info.productions.add(new Program("1", Program.Status.Red));
        Info.productions.add(new Program("2", Program.Status.Red));
        Info.productions.add(new Program("2", Program.Status.Red));
        Info.productions.add(new Program("3", Program.Status.Red));
        Info.productions.add(new Program("3", Program.Status.Red));
        Info.productions.add(new Program("3", Program.Status.Red));
        Info.productions.add(new Program("4", Program.Status.Red));
        Info.productions.add(new Program("4", Program.Status.Red));
        Info.productions.add(new Program("4", Program.Status.Red));
        Info.productions.add(new Program("4", Program.Status.Red));
        Info.productions.add(new Program("5", Program.Status.Red));
        Info.productions.add(new Program("5", Program.Status.Red));
        Info.productions.add(new Program("5", Program.Status.Red));
        Info.productions.add(new Program("5", Program.Status.Red));
        Info.productions.add(new Program("5", Program.Status.Red));
        Info.productions.add(new Program("6", Program.Status.Red));
        Info.productions.add(new Program("6", Program.Status.Red));
        Info.productions.add(new Program("6", Program.Status.Red));
        Info.productions.add(new Program("6", Program.Status.Red));
        Info.productions.add(new Program("6", Program.Status.Red));
        Info.productions.add(new Program("6", Program.Status.Red));
        Info.productions.add(new Program("Pulp Fiction", Program.Status.Red));
        Info.productions.add(new Program("Taxidermlia", Program.Status.Red));
        Info.productions.add(new Program("Naked Lunch", Program.Status.Red));
        Info.productions.add(new Program("There Will Be Blood", Program.Status.Red));

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), (ActionEvent event) -> {
            // this code will be called every second
            updateEverySecond();
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateProperties() {
        Info.updateColors();
        // BUTTONS
        // Set the color of the buttons
        loginCircle.setFill(Info.accentGradient);
        closeRectangle1.setFill(Info.accentGradient);
        closeRectangle2.setFill(Info.accentGradient);
        maximizeRectangle.setFill(Info.accentGradient);
        minimizeRectangle.setFill(Info.accentGradient);

        // SEARCH BAR
        // Set the color and round the corners of the search bar 
        searchBarBackground.setStyle("-fx-background-radius: " + Info.roundAmount + "; -fx-border-radius: " + Info.roundAmount + "; -fx-background-color: " + Info.forgroundColor + ";");
        searchRectangleBG.setFill(Info.accentGradient);

        // BACKGROUND
        // Set the color of the shade in the background
        backgroundShade.setFill(Paint.valueOf(Info.backgroundShadeColor));
        backgroundAP.setStyle("-fx-background-color: " + Info.backgroundColor + ";");

        // SIDE BAR
        // Set the color of side bar top
        sideBarTopColor.setFill(Info.accentGradient);
        // Set the color of side bar curves
        buttomCurveColor.setFill(Paint.valueOf(Info.forgroundColor));
        topCurveColor.setFill(Paint.valueOf(Info.accentEndColor));
        // Set the color of the circles that overlap the rectangles (same as background)
        buttomCurveOverlap.setFill(Paint.valueOf(Info.backgroundColor));
        topCurveOverlap.setFill(Paint.valueOf(Info.backgroundColor));
        // Set the color of side bar background
        sidePanelBackground.setStyle("-fx-background-color: " + Info.forgroundColor + ";");

    }

    private void updateEverySecond() {
        if (textFieldSearchBar.isFocused()) {
            wordSearch();
        }

        // TODO: remove later
        updateProductionList();

    }

    private String tempUserSearch = "";

    private void wordSearch() {
        String userSearch = textFieldSearchBar.getText().toLowerCase();
        int searchResultSpacing = 10;
        int searchResultTextPadding = 50;

        // TODO: Fix select/deselect and writing visibility
        // Only updates when a change is made in the textField
        if (!userSearch.equals(tempUserSearch)) {
            tempUserSearch = userSearch;
            if (!userSearch.isBlank()) {
                searchResults.getChildren().clear();
                for (int i = 0; i < Info.productions.size(); i++) {
                    String title = Info.productions.get(i).getTitle().toLowerCase();

                    // if the text written in the searchbar is equal to any result in the production list // DATABASE
                    if (title.contains(userSearch)) { // DATABASE
                        AnchorPane ap = new AnchorPane();
                        Label titleText = new Label(Info.productions.get(i).getTitle());

                        searchResults.getChildren().add(ap);
                        ap.getChildren().addAll(titleText);

                        searchRectangleBG.setVisible(true);
                        searchResultScrollPane.setVisible(true);
                    }
                }

                if (searchResults.getChildren().size() == 0) {
                    searchRectangleBG.setVisible(false);
                    searchResultScrollPane.setVisible(false);
                    searchRectangleBG.setHeight(searchBarBackground.getHeight());
                }

                // Goes through each Label and AnchorPane and sets the style accordingly
                for (int i = 0; i < searchResults.getChildren().size(); i++) {
                    AnchorPane ap = (AnchorPane) searchResults.getChildren().get(i);
                    Label titleText = (Label) ap.getChildren().get(0);

                    searchResults.setAlignment(Pos.TOP_LEFT);
                    searchResults.setSpacing(searchResultSpacing);
                    ap.setCursor(Cursor.HAND);

                    titleText.setPadding(new Insets(0, 0, 0, searchResultTextPadding));
                    titleText.setStyle("-fx-text-fill: " + Info.fontColor2 + "; -fx-font-size: " + Info.fontSizeDefault + ";");
                    titleText.setAlignment(Pos.CENTER_LEFT);

                    ap.setOnMouseEntered(this::handleSearchMenuHoveringEnter);
                    ap.setOnMouseExited(this::handleSearchMenuHoveringExit);
                    ap.setOnMouseClicked(this::handleSearchMenuHoveringClicked);

                    titleText.applyCss();
                    calculateSearchResultsHeight(titleText);
                }
            }
        } else if (userSearch.isBlank()) {
            searchRectangleBG.setVisible(false);
            searchResultScrollPane.setVisible(false);
        }
    }

    private void calculateSearchResultsHeight(Label titleText) {
        int visibleResults = 3;
        int searchResultSize = searchResults.getChildren().size();

        if (searchResultSize == 0) {
            searchRectangleBG.setHeight(searchBarBackground.getHeight()); // no results
        } else if (searchResultSize < visibleResults) { // Maximum amount of search results shown
            searchRectangleBG.setHeight(searchBarBackground.getHeight() + (titleText.prefHeight(-1) + 10) * (searchResultSize));
        } else {
            searchRectangleBG.setHeight(searchBarBackground.getHeight() + (titleText.prefHeight(-1) + 10) * (visibleResults));
        }
    }

    // Handles what happens when you enter a search on the search bar
    @FXML
    private void handleSearch() { // TODO: REWORK EVERYTHING

        for (int i = 0; i < Info.productions.size(); i++) {
            if (textFieldSearchBar.getText().equals(Info.productions.get(i).getTitle())) {
                System.out.println(Info.productions.get(i).getCredits());

            }

        }

        String startColorCommand = "!startColor ";
        String endColorCommand = "!endColor ";
        String switchColorCommand = "!switchColor";
        String addProgramCommand = "!addProgram ";
        String addCreditCommand = "!addCredit ";
        String showCreditsCommand = "!showCredits ";


        if (textFieldSearchBar.getText().startsWith(startColorCommand)) {
            Info.accentStartColor = (textFieldSearchBar.getText().substring(startColorCommand.length()));
            System.out.println(Info.accentStartColor);

        } else if (textFieldSearchBar.getText().startsWith(endColorCommand)) {
            Info.accentEndColor = (textFieldSearchBar.getText().substring(endColorCommand.length()));
            System.out.println(Info.accentEndColor);

        } else if ((textFieldSearchBar.getText().startsWith(switchColorCommand))) {
            String tempColor = Info.accentEndColor;
            Info.accentEndColor = Info.accentStartColor;
            Info.accentStartColor = tempColor;

        } else if ((textFieldSearchBar.getText().startsWith(addProgramCommand))) {
            String programTitle = textFieldSearchBar.getText().substring(addProgramCommand.length());
            Info.productions.add(new Program(programTitle, Program.Status.Red));

            updateProductionList();
        } else if (textFieldSearchBar.getText().startsWith(addCreditCommand)) {
            String line = (textFieldSearchBar.getText().substring(addCreditCommand.length()));
            String[] content = line.split(" ");

            for (int i = 0; i < Info.productions.size(); i++) {
                if (content[0].equals(Info.productions.get(i).getTitle())) {
                    Info.productions.get(i).addCredit(content[1], content[2]);
                    System.out.println("added " + content[1] + " with the role " + content[2] + " to " + Info.productions.get(i).getTitle());
                }
            }

        } else if (textFieldSearchBar.getText().startsWith(showCreditsCommand)) {
            String programTitle = textFieldSearchBar.getText().substring(showCreditsCommand.length());
            System.out.println("Showing credits for " + programTitle);

            for (int i = 0; i < Info.productions.size(); i++) {
                if (programTitle.equals(Info.productions.get(i).getTitle())) {
                    System.out.println(Info.productions.get(i).getCredits());
                }
            }
        }

        textFieldSearchBar.clear();

        updateProperties();
    }

    @FXML
    private void handleDeselect() {
        backgroundAP.requestFocus();
        searchRectangleBG.setVisible(false);
        searchResultScrollPane.setVisible(false);
        searchRectangleBG.setHeight(searchBarBackground.getHeight());
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void optionButtonAction() {
        System.out.println("Open options");
    }

    @FXML
    private void sidePanelAction() {
        if (Info.sidePanelOn) {
            sidePanelOff();
            Info.sidePanelOn = false;
        } else {
            sidePanelOn();
            Info.sidePanelOn = true;
        }
    }

    private void updateProductionList() {

        Collections.sort(Info.productions);

        programList.getChildren().clear();

        for (int i = 0; i < Info.productions.size(); i++) {

            HBox hb = new HBox();
            Circle circle = new Circle(4);
            Label title = new Label(Info.productions.get(i).getTitle());

            programList.getChildren().add(hb);

            hb.getChildren().addAll(circle, title);
            hb.setSpacing(25);
            hb.setAlignment(Pos.CENTER_LEFT);
            title.setCursor(Cursor.HAND);

            if (Info.productions.get(i).getStatus() == Program.Status.Red) {
                circle.setFill(Paint.valueOf(Info.statusRed)); // DATABASE
            } else if (Info.productions.get(i).getStatus() == Program.Status.Yellow) {
                circle.setFill(Paint.valueOf(Info.statusYellow)); // DATABASE
            } else {
                circle.setFill(Paint.valueOf(Info.statusGreen)); // DATABASE
            }

            title.setStyle("-fx-text-fill: " + Info.fontColor1 + "; -fx-font-size: " + Info.fontSizeDefault + ";");

        }
    }

    private void animateSidePanel(int size) {
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(sideBar.prefWidthProperty(), size, Interpolator.EASE_OUT);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    private void sidePanelOn() {
        menuCurve.setOpacity(100);
        animateSidePanel(Info.sideBarWidth);
    }

    private void sidePanelOff() {
        menuCurve.setOpacity(0);
        animateSidePanel(0);
    }

    public void handleSearchMenuHoveringClicked(MouseEvent event) {
        AnchorPane ap = ((AnchorPane) event.getSource());
        Label titleText = (Label) ap.getChildren().get(0);
        textFieldSearchBar.setText(titleText.getText());
    }

    public void handleSearchMenuHoveringEnter(MouseEvent event) {
        AnchorPane ap = ((AnchorPane) event.getSource());
        Label titleText = (Label) ap.getChildren().get(0);
        titleText.setStyle("-fx-text-fill: white; -fx-font-size: " + Info.fontSizeDefault + ";");
        //ap.setStyle("-fx-background-color: rgba(0,0,0,0.20) ; -fx-background-radius: " + Info.roundAmount + "; -fx-border-radius: " + Info.roundAmount + ";");
        // TODO: Få ap til at blive inden for "searchRectangleBG"
    }

    public void handleSearchMenuHoveringExit(MouseEvent event) {
        AnchorPane ap = ((AnchorPane) event.getSource());
        Label titleText = (Label) ap.getChildren().get(0);
        titleText.setStyle("-fx-text-fill: " + Info.forgroundColor + "; -fx-font-size: " + Info.fontSizeDefault + ";");
        //ap.setStyle("-fx-background-color: transparent;");

    }

    @Override
    public void handleTopDragClicked(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
            maximizeButtonAction();
        }
    }

    @Override
    public void handleTopDragDragged(MouseEvent event) {
        App.stage.setX(event.getScreenX() + App.xOffset);
        App.stage.setY(event.getScreenY() + App.yOffset);
    }

    @Override
    public void handleTopDragPressed(MouseEvent event) {
        App.xOffset = App.stage.getX() - event.getScreenX();
        App.yOffset = App.stage.getY() - event.getScreenY();
    }

    @Override
    public void handleButtonHoveringEnter(MouseEvent event) {
        StackPane hoveredObject = ((StackPane) event.getSource());
        hoveredObject.setScaleX(hoveredObject.getScaleX() + Info.scaleAmount);
        hoveredObject.setScaleY(hoveredObject.getScaleY() + Info.scaleAmount);
    }

    @Override
    public void handleButtonHoveringExit(MouseEvent event) {
        StackPane hoveredObject = ((StackPane) event.getSource());
        hoveredObject.setScaleX(hoveredObject.getScaleX() - Info.scaleAmount);
        hoveredObject.setScaleY(hoveredObject.getScaleY() - Info.scaleAmount);
    }

    @Override
    public void minimizeButtonAction() {
        App.stage.setIconified(true);
    }

    @Override
    public void maximizeButtonAction() {
        if (!App.stage.isMaximized()) {
            System.out.println("fullscreen");
            App.stage.setMaximized(true);
        } else {
            System.out.println("not fullscreen");
            App.stage.setMaximized(false);
        }
    }

    @Override
    public void closeButtonAction() {
        Platform.exit();
    }
}

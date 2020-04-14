package com.mycompany.creditsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import com.mycompany.creditsystem.domain.*;
import com.mycompany.creditsystem.domain.Production.Status;
import com.mycompany.creditsystem.persistence.Info;
import com.mycompany.creditsystem.persistence.PersistenceHandler;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class PrimaryController implements Initializable {

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
    private Label nameText;
    @FXML
    private Label roleText;
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
    @FXML
    private Label descriptionTitleText;
    @FXML
    private ScrollPane descriptionScrollPane;
    @FXML
    private VBox descriptionVBoxName;
    @FXML
    private VBox descriptionVBoxRole;
    @FXML
    private Rectangle descriptionRectangleSplitter;
    @FXML
    private Polygon homeButton;
    @FXML
    private Rectangle rectangleSortSplitter;
    @FXML
    private Label topSortingLabel;
    @FXML
    private Label bottomSortingLabel;
    @FXML
    private AnchorPane mineProduktioner;
    @FXML
    private AnchorPane nameAndRoleAP;
    @FXML
    private VBox nameAndRole;
    @FXML
    private AnchorPane sortingAP;
    @FXML
    private Rectangle loginRectangleSplitter;
    @FXML
    private Rectangle loginRectangleBG;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private AnchorPane loginAP;


    // idk what im doing
    private PersistenceHandler persistanceHandler;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateProperties();
        setNameAndRole();

        loadLoginElements();


        Info.productions.addListener((ListChangeListener<Production>) change -> {
            updateProductionList();
        });

        Info.sidePanelOn = true;
        // idk
        persistanceHandler = PersistenceHandler.getInstance();

        // TRASH FOR TESTING
        Info.productions.add(new Production("1"));
        Info.productions.add(new Production("2"));
        Info.productions.add(new Production("2"));
        Info.productions.add(new Production("3"));
        Info.productions.add(new Production("3"));
        Info.productions.add(new Production("3"));
        Info.productions.add(new Production("4"));
        Info.productions.add(new Production("4"));
        Info.productions.add(new Production("4"));
        Info.productions.add(new Production("4"));
        Info.productions.add(new Production("5"));
        Info.productions.add(new Production("5"));
        Info.productions.add(new Production("5"));
        Info.productions.add(new Production("5"));
        Info.productions.add(new Production("5"));
        Info.productions.add(new Production("6"));
        Info.productions.add(new Production("6"));
        Info.productions.add(new Production("6"));
        Info.productions.add(new Production("6"));
        Info.productions.add(new Production("6"));
        Info.productions.add(new Production("6"));
        Info.productions.add(new Production("Pulp Fiction"));
        Info.productions.add(new Production("Taxidermlia"));
        Info.productions.add(new Production("Naked Lunch"));
        Info.productions.add(new Production("There Will Be Blood"));

        Info.getProduction("There Will Be Blood").setStatus(Status.Green);

        Info.getProduction("There Will Be Blood").addCredit(new Credit("John Mogensen","VFX"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Teis Larsen","Actor"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Lars Vilbo","Dressing"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("John Mogensen","VFX"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Teis Larsen","Actor"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Lars Vilbo","Dressing"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("John Mogensen","VFX"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Teis Larsen","Actor"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Lars Vilbo","Dressing"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("John Mogensen","VFX"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Teis Larsen","Actor"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Lars Vilbo","Dressing"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("John Mogensen","VFX"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Teis Larsen","Actor"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Lars Vilbo","Dressing"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("John Mogensen","VFX"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Teis Larsen","Actor"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Lars Vilbo","Dressing"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("John Mogensen","VFX"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Teis Larsen","penis"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Lars Vilbo","Dressing"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("John Mogensen","VFX"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Teis Larsen","Actor"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Lars Vilbo","Dressing"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("John Mogensen","VFX"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Teis Larsen","Actor"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Lars Vilbo","Dressing"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("John Mogensen","VFX"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Teis Larsen","Actor"));
        Info.getProduction("There Will Be Blood").addCredit(new Credit("Lars Vilbo","Dressing"));
        /////

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), (ActionEvent event) -> {
            // this code will be called every second
            updateEverySecond();
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


    }

    // Updates the graphical style and fill
    private void updateProperties() {
        Info.updateColors();
        // BUTTONS
        // Set the color of the buttons
        loginCircle.setFill(Info.accentGradient);
        closeRectangle1.setFill(Info.accentGradient);
        closeRectangle2.setFill(Info.accentGradient);
        maximizeRectangle.setFill(Info.accentGradient);
        minimizeRectangle.setFill(Info.accentGradient);
        homeButton.setFill(Info.accentGradient);

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
        rectangleSortSplitter.setFill(Info.accentGradient);
        loginRectangleSplitter.setFill(Info.accentGradient);
        loginRectangleBG.setFill(Info.accentGradient);

        // DESCRIPTION
        descriptionRectangleSplitter.setFill(Info.accentGradient);
        descriptionScrollPane.getStyleClass().add("dark-thumb");

    }

    // Is getting called many times a second
    private void updateEverySecond() {
        if (textFieldSearchBar.isFocused()) {
            updateSearchResultList();
        }

        handleSearchFocus();
    }

    @FXML
    private void handleLogin() {
        System.out.println("login stuff");
        if (usernameTextField.getText().equals(Info.currentUser.getUsername()) &&
                passwordTextField.getText().equals(Info.currentUser.getPassword())) {
            enableElements(Info.currentUser.getAccessRole());
            sidePanelBackground.getChildren().remove(loginAP);
        } else {
            System.out.println("wrong password");
        }
    }

    private void enableElements(AccessRole accessRole) {
        switch(accessRole) {
            case publicUser:
                break;
            case producer:
                nameAndRoleAP.getChildren().add(nameAndRole);
                sidePanelBackground.getChildren().add(mineProduktioner);
                sidePanelBackground.getChildren().add(sortingAP);
                break;
            case productionCompany:
                break;
            case admin:
                break;
            default:
                System.out.println("ERROR while loading user access type.");
        }
    }

    private void loadLoginElements() {
        sidePanelBackground.getChildren().clear();
        sidePanelBackground.getChildren().add(nameAndRoleAP);
        nameAndRoleAP.getChildren().remove(nameAndRole);
        sidePanelBackground.getChildren().add(loginAP);
        usernameTextField.clear();
        passwordTextField.clear();
    }

    // Displays the credits and sets their style
    private void showCreditList(Production program) {
        descriptionVBoxName.getChildren().clear();
        descriptionVBoxRole.getChildren().clear();

        if (program.getCredits().size() == 0) {
            calculateSplitterRectangleHeight(null, program);
        } else {
            for (int i = 0; i < program.getCredits().size(); i++) {
                Credit credit = (Credit) program.getCredits().get(i);
                Label nameLB = new Label(credit.getName());
                TextField nameTF = new TextField();
                Label roleLB = new Label(credit.getRole());
                TextField roleTF = new TextField();

                descriptionVBoxName.getChildren().add(nameLB);
                descriptionVBoxRole.getChildren().add(roleLB);

                nameLB.setStyle("-fx-text-fill: " + Info.fontColor2 + "; -fx-font-size: " + Info.fontSizeDefault + ";");
                roleLB.setStyle("-fx-text-fill: " + Info.fontColor2 + "; -fx-font-size: " + Info.fontSizeDefault + ";");
                nameLB.setAlignment(Pos.CENTER_RIGHT);
                roleLB.setAlignment(Pos.CENTER_LEFT);
                nameTF.setAlignment(Pos.CENTER_RIGHT);

                nameLB.setOnMouseClicked(e -> {
                    for (int j = 0; j < descriptionVBoxName.getChildren().size(); j++) {

                        if (descriptionVBoxName.getChildren().get(j) instanceof Label) {
                            Label nameCheck = (Label)descriptionVBoxName.getChildren().get(j);
                            if (nameCheck == nameLB) {

                                nameTF.setText(nameLB.getText());
                                descriptionVBoxName.getChildren().add(j, nameTF);
                                descriptionVBoxName.getChildren().remove(nameLB);
                            }
                        }
                    }
                });

                nameTF.setOnKeyPressed(ev -> {
                    if (ev.getCode() == KeyCode.ENTER) {
                        for (int k = 0; k < descriptionVBoxName.getChildren().size(); k++) {
                            if (descriptionVBoxName.getChildren().get(k) instanceof TextField) {
                                TextField textCheck = (TextField)descriptionVBoxName.getChildren().get(k);
                                if (textCheck == nameTF) {

                                    nameLB.setText(nameTF.getText());
                                    descriptionVBoxName.getChildren().add(k, nameLB);
                                    descriptionVBoxName.getChildren().remove(nameTF);
                                }
                            }
                        }
                    }
                });

                nameLB.applyCss();
                roleLB.applyCss();

                calculateSplitterRectangleHeight(nameLB, program);
            }
        }
    }

    // Makes the search text white when focused and clicking ENTER searches the focused text
    private void handleSearchFocus() {
        for (int i = 0; i < searchResults.getChildren().size(); i++) {
            AnchorPane ap = (AnchorPane)searchResults.getChildren().get(i);
            Label titleText = (Label)ap.getChildren().get(0);
            if (titleText.isFocused()) {
                titleText.setOnKeyPressed(e -> {
                    if (e.getCode() == KeyCode.ENTER) {
                        textFieldSearchBar.setText(titleText.getText());
                        handleSearch();
                    }
                });
                titleText.setStyle("-fx-text-fill: white; -fx-font-size: " + Info.fontSizeDefault + ";"); // same as hover
            } else {
                titleText.setStyle("-fx-text-fill: " + Info.forgroundColor + "; -fx-font-size: " + Info.fontSizeDefault + ";");
            }
        }
    }

    // Holds the last updated text in the search bar
    private String tempUserSearch = "";
    Boolean selectBlank = true;

    // Updates and displays the search results
    private void updateSearchResultList() {
        String userSearch = textFieldSearchBar.getText().toLowerCase();

        // TODO: Fix select/deselect and writing visibility
        // Only updates when a change is made in the textField
        if (!userSearch.equals(tempUserSearch)) {
            tempUserSearch = userSearch;
            if (!userSearch.isBlank()) {
                searchResults.getChildren().clear();
                for (int i = 0; i < Info.productions.size(); i++) {
                    String title = Info.productions.get(i).getTitle().toLowerCase();

                    // if the text written in the search bar is equal to any result in the production list // DATABASE
                    if (title.contains(userSearch)) { // DATABASE
                        AnchorPane ap = new AnchorPane();
                        Label titleText = new Label(Info.productions.get(i).getTitle());

                        searchResults.getChildren().add(ap);
                        ap.getChildren().addAll(titleText);
                    }

                }

                if (searchResults.getChildren().size() == 0) {
                    searchRectangleBG.setHeight(searchBarBackground.getHeight());
                }

                styleSearchResults();

            } else {
                // triggers if the search bar becomes empty while the using is typing
                displaySearchHistory();
                styleSearchResults();
            }
        } else if (userSearch.isBlank()) {
            // triggers if the search bar is empty and just focused
            if (selectBlank) {
                selectBlank = false;
                displaySearchHistory();
                styleSearchResults();
            }
        }
    }

    // Search history
    private void displaySearchHistory() {
        searchResults.getChildren().clear();
        for (int i = 0; i < searchHistory.size(); i++) {
            String title = searchHistory.get(i).getTitle();
            AnchorPane ap = new AnchorPane();
            Label titleText = new Label(title);

            searchResults.getChildren().add(ap);
            ap.getChildren().addAll(titleText);
        }
    }

    private void styleSearchResults() {
        int searchResultSpacing = 10;
        int searchResultTextPadding = 50;

        for (int i = 0; i < searchResults.getChildren().size(); i++) {
            AnchorPane ap = (AnchorPane) searchResults.getChildren().get(i);
            Label titleText = (Label) ap.getChildren().get(0);

            searchResults.setAlignment(Pos.TOP_LEFT);
            searchResults.setSpacing(searchResultSpacing);
            ap.setCursor(Cursor.HAND);

            titleText.setPadding(new Insets(0, 0, 0, searchResultTextPadding));
            titleText.setStyle("-fx-text-fill: " + Info.fontColor2 + "; -fx-font-size: " + Info.fontSizeDefault + ";");
            titleText.setAlignment(Pos.CENTER_LEFT);
            titleText.setFocusTraversable(true);

            ap.setOnMouseEntered(this::handleSearchMenuHoveringEnter);
            ap.setOnMouseExited(this::handleSearchMenuHoveringExit);
            ap.setOnMouseClicked(this::handleSearchMenuHoveringClicked);

            titleText.applyCss();
            calculateSearchResultsHeight(titleText);
        }
    }
    // Calculates and sets the height of the search result tab
    private void calculateSearchResultsHeight(Label titleText) {
        int visibleResults = 3;
        int searchResultSize = searchResults.getChildren().size();

        if (searchResultSize == 0) {
            searchRectangleBG.setHeight(searchBarBackground.getHeight()); // no results
        } else if (searchResultSize < visibleResults) { // Maximum amount of search results shown
            searchRectangleBG.setHeight(searchBarBackground.getHeight() + (titleText.prefHeight(-1) + searchResults.getSpacing()) * (searchResultSize));
        } else {
            searchRectangleBG.setHeight(searchBarBackground.getHeight() + (titleText.prefHeight(-1) + searchResults.getSpacing()) * (visibleResults));
        }
    }

    // Calculates and sets the height of the splitter rectangle for the production description
    private void calculateSplitterRectangleHeight (Label name, Production production) {
        if (name == null) {
            descriptionRectangleSplitter.setHeight(0);
        } else {
            descriptionRectangleSplitter.setHeight((name.prefHeight(-1) + descriptionVBoxName.getSpacing()) * production.getCredits().size());
        }
    }

    // Handles what happens when you search
    @FXML
    private void handleSearch() { // TODO: REWORK EVERYTHING

        String addProgramCommand = "!addProgram ";
        String addCreditCommand = "!addCredit ";

        // Add program logic
        if ((textFieldSearchBar.getText().startsWith(addProgramCommand))) {
            String programTitle = textFieldSearchBar.getText().substring(addProgramCommand.length());
            Info.productions.add(new Production(programTitle));
        }
        // Add credit logic
        else if (textFieldSearchBar.getText().startsWith(addCreditCommand)) {
            String line = (textFieldSearchBar.getText().substring(addCreditCommand.length()));
            String[] content = line.split(" ");

            for (int i = 0; i < Info.productions.size(); i++) {
                if (content[0].equals(Info.productions.get(i).getTitle())) {
                    Info.productions.get(i).addCredit(new Credit(content[1], content[2]));
                    System.out.println("added " + content[1] + " with the role " + content[2] + " to " + Info.productions.get(i).getTitle());
                }
            }
        }
        // Actual search function
        else {
            if (Info.getProduction(textFieldSearchBar.getText()) != null) {
                Production program = Info.getProduction(textFieldSearchBar.getText());
                descriptionTitleText.setText(program.getTitle());
                showCreditList(program);
                addToProductionHistory(program);

            } else {
                System.out.println("Production doesn't exist in the database");
            }
        }

        textFieldSearchBar.clear();
        handleDeselect();
        updateProperties();
    }

    private ArrayList<Production> searchHistory = new ArrayList<>();
    // add program to search history
    private void addToProductionHistory(Production production) {
        for (int i = 0; i < searchHistory.size(); i++) {
            if (searchHistory.get(i) == production) {
                searchHistory.remove(i);
            }
        }
        searchHistory.add(0, production);
    }

    // Handles when the user clicks on the background to deselect what was previously focused
    @FXML
    private void handleDeselect() {
        backgroundAP.requestFocus();
        searchRectangleBG.setHeight(searchBarBackground.getHeight());
        searchResults.getChildren().clear();
        selectBlank = true;
    }

    private void setNameAndRole() {
        String name = Info.currentUser.getName();
        String role = Info.currentUser.getAccessRole().toString();
        nameText.setText(name);
        roleText.setText(role.substring(0, 1).toUpperCase() + role.substring(1));
    }

    private Boolean nameComparatorShift = true;

    @FXML
    private void sortByName() {
        ProductionNameComparator nameComparator = new ProductionNameComparator();
        if (nameComparatorShift) {
            Info.productions.sort(nameComparator);
            nameComparatorShift = false;
            topSortingLabel.setText("A");
            bottomSortingLabel.setText("Z");
        } else {
            Info.productions.sort(nameComparator.reversed());
            nameComparatorShift = true;
            topSortingLabel.setText("Z");
            bottomSortingLabel.setText("A");
        }
    }

    private Boolean deadlineComparatorShift = true;

    @FXML
    private void sortByDeadline() {
        ProductionDeadlineComparator deadlineComparator = new ProductionDeadlineComparator();
        if (deadlineComparatorShift) {
            Info.productions.sort(deadlineComparator);
            deadlineComparatorShift = false;
        } else {
            Info.productions.sort(deadlineComparator.reversed());
            deadlineComparatorShift = true;
        }
    }

    @FXML
    private void homeButtonAction() {
        System.out.println("do home thing");
        loadLoginElements();
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

        programList.getChildren().clear();

        for (int i = 0; i < Info.productions.size(); i++) {

            HBox hb = new HBox();
            Circle circle = new Circle(4);
            AnchorPane ap = new AnchorPane();
            VBox vb = new VBox();
            Label title = new Label(Info.productions.get(i).getTitle());
            Label deadline = new Label(Info.productions.get(i).getDeadlineString());

            programList.getChildren().add(hb);
            hb.getChildren().addAll(circle, ap);
            ap.getChildren().add(vb);
            vb.getChildren().addAll(title, deadline);
            HBox.setHgrow(ap, Priority.ALWAYS);

            hb.setSpacing(25);
            hb.setAlignment(Pos.CENTER_LEFT);
            ap.setCursor(Cursor.HAND);

            if (Info.productions.get(i).getStatus() == Production.Status.Red) {
                circle.setFill(Paint.valueOf(Info.statusRed)); // DATABASE
            } else if (Info.productions.get(i).getStatus() == Production.Status.Yellow) {
                circle.setFill(Paint.valueOf(Info.statusYellow)); // DATABASE
            } else {
                circle.setFill(Paint.valueOf(Info.statusGreen)); // DATABASE
            }

            ap.setOnMouseClicked(e -> {
                textFieldSearchBar.setText(title.getText());
                handleSearch();
            });

            title.setStyle("-fx-text-fill: " + Info.fontColor1 + "; -fx-font-size: " + Info.fontSizeDefault + ";");
            deadline.setStyle("-fx-text-fill: " + Info.fontColor3 + "; -fx-font-size: " + Info.fontSizeSmall + ";");

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

    @FXML
    public void handleSearchMenuHoveringClicked(MouseEvent event) {
        AnchorPane ap = ((AnchorPane) event.getSource());
        Label titleText = (Label) ap.getChildren().get(0);
        textFieldSearchBar.setText(titleText.getText());
        handleSearch();
    }

    @FXML
    public void handleSearchMenuHoveringEnter(MouseEvent event) {
        AnchorPane ap = ((AnchorPane) event.getSource());
        Label titleText = (Label) ap.getChildren().get(0);
        titleText.setStyle("-fx-text-fill: white; -fx-font-size: " + Info.fontSizeDefault + ";");
        //ap.setStyle("-fx-background-color: rgba(0,0,0,0.20) ; -fx-background-radius: " + Info.roundAmount + "; -fx-border-radius: " + Info.roundAmount + ";");
        // TODO: Få ap til at blive inden for "searchRectangleBG"
    }

    @FXML
    public void handleSearchMenuHoveringExit(MouseEvent event) {
        AnchorPane ap = ((AnchorPane) event.getSource());
        Label titleText = (Label) ap.getChildren().get(0);
        titleText.setStyle("-fx-text-fill: " + Info.forgroundColor + "; -fx-font-size: " + Info.fontSizeDefault + ";");
        //ap.setStyle("-fx-background-color: transparent;");

    }

    @FXML
    public void handleTopDragClicked(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
            maximizeButtonAction();
        }
    }

    @FXML
    public void handleTopDragDragged(MouseEvent event) {
        App.stage.setX(event.getScreenX() + App.xOffset);
        App.stage.setY(event.getScreenY() + App.yOffset);
    }

    @FXML
    public void handleTopDragPressed(MouseEvent event) {
        App.xOffset = App.stage.getX() - event.getScreenX();
        App.yOffset = App.stage.getY() - event.getScreenY();
    }

    @FXML
    public void handleButtonHoveringEnter(MouseEvent event) {
        StackPane hoveredObject = ((StackPane) event.getSource());
        hoveredObject.setScaleX(hoveredObject.getScaleX() + Info.scaleAmount);
        hoveredObject.setScaleY(hoveredObject.getScaleY() + Info.scaleAmount);
    }

    @FXML
    public void handleButtonHoveringExit(MouseEvent event) {
        StackPane hoveredObject = ((StackPane) event.getSource());
        hoveredObject.setScaleX(hoveredObject.getScaleX() - Info.scaleAmount);
        hoveredObject.setScaleY(hoveredObject.getScaleY() - Info.scaleAmount);
    }

    @FXML
    public void minimizeButtonAction() {
        App.stage.setIconified(true);
    }

    @FXML
    public void maximizeButtonAction() {
        if (!App.stage.isMaximized()) {
            App.stage.setMaximized(true);
        } else {
            App.stage.setMaximized(false);
        }
    }

    @FXML
    public void closeButtonAction() {
        Platform.exit();
    }
}

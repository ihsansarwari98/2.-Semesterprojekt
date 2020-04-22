package com.mycompany.creditsystem.presentation;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mycompany.creditsystem.domain.logic.*;
import com.mycompany.creditsystem.domain.logic.Production.Status;

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
import javafx.scene.text.Text;
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
    private Circle loginCircle1;
    @FXML
    private Circle loginCircle2;
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
    private VBox descriptionVBox;
    @FXML
    private Text homeText;
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
    private Rectangle loginRectangleSplitter;
    @FXML
    private Rectangle loginRectangleBG;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private BorderPane sortingBorderPane;
    @FXML
    private Rectangle rectangleLogoutSplitter;
    @FXML
    private AnchorPane loginAP;
    @FXML
    private AnchorPane logoutAP;
    @FXML
    private BorderPane titleAndDescriptionBP;
    @FXML
    private BorderPane searchBarBP;
    @FXML
    private Text logoText;
    @FXML
    private Rectangle rectangleLogoSplitter;
    @FXML
    private VBox logoVBox;
    @FXML
    private HBox leftTopMenuHBox;
    @FXML
    private HBox descriptionTitleHBox;

    // idk what im doing
    //private ProductionLogic productionLogic = new ProductionLogic();
    private SystemFacade systemFacade = new SystemFacade();

    private boolean loggedin;
    HBox switchButtonHBox = new HBox();
    SwitchButton switchButton = new SwitchButton();
    Label editLabel = new Label("Toggle \nEditability");
    private Production activeProduction;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateProperties();

        enableElements(User.AccessRole.publicUser);

        Info.productions.addListener((ListChangeListener<Production>) change -> {
            updateProductionList();
        });

//        System.out.println(systemFacade.productionLogic.getProductions());
//        System.out.println(systemFacade.userLogic.getUsers());
        System.out.println(systemFacade.userLogic.getUsers().get(0).getPassword());
        System.out.println(systemFacade.userLogic.getUsers().get(0).getUsername());


        //System.out.println(systemFacade.currentUser.getUser());

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

        Info.credits.add(new Credit("Maren Jytte Jensen"));
        Info.credits.add(new Credit("Jeppe Jytte Jensen"));
        Info.credits.add(new Credit("Holger Jytte Jensen"));
        Info.credits.add(new Credit("Frede Jytte Jensen"));
        Info.credits.add(new Credit("Mustafa Jytte Jensen"));
        Info.credits.add(new Credit("Maren2 Jytte Jensen"));
        Info.credits.add(new Credit("123PENIS Jytte Jensen"));

        Info.getProduction("There Will Be Blood").addCredit(Info.credits.get(1));
        Info.getProduction("There Will Be Blood").addCredit(Info.credits.get(2));
        Info.getProduction("There Will Be Blood").addCredit(Info.credits.get(3));
        Info.getProduction("There Will Be Blood").addCredit(Info.credits.get(4));

        Info.users.add(new Producer("John", "1", "1"));
        Info.users.add(new Producer("Kim", "3", "3"));
        Info.users.add(new Administrator("Lars", "2", "2"));
        Info.users.add(new ProductionCompany("Penis Joe", "4", "4"));

        /////

        homeButtonAction();
        Info.sidePanelOn = true;
        sidePanelAction();

        switchButtonHBox.getChildren().addAll(switchButton, editLabel);

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
        loginCircle.setStroke(Info.accentGradient);
        loginCircle1.setFill(Info.accentGradient);
        loginCircle2.setFill(Info.accentGradient);

        closeRectangle1.setFill(Info.accentGradient);
        closeRectangle2.setFill(Info.accentGradient);
        maximizeRectangle.setFill(Info.accentGradient);
        minimizeRectangle.setFill(Info.accentGradient);
        homeText.setFill(Info.accentGradient);

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
        rectangleLogoutSplitter.setFill(Info.accentGradient);

        // DESCRIPTION
        descriptionScrollPane.getStyleClass().add("dark-thumb");
        descriptionTitleText.setStyle("-fx-text-fill: " + Info.forgroundColor + ";");

        logoText.setFill(Info.accentGradient);
        rectangleLogoSplitter.setFill(Info.accentGradient);
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
        if (systemFacade.userLogic.userLogin(usernameTextField.getText(), passwordTextField.getText())) {
            enableElements(systemFacade.currentUser.getUser().getAccessRole());

            // Sets myProductions
            systemFacade.currentUser.setMyProductions(systemFacade.productionLogic.getProductionsLinkedToProducer(systemFacade.currentUser.getUser().getId()));

            setNameAndRole();
            sidePanelBackground.getChildren().remove(loginAP);
            sidePanelBackground.getChildren().add(logoutAP);
            handleDeselect();
            updateSearchResultList();

        } else {
            enableElements(User.AccessRole.publicUser);
            System.out.println("password and username doesn't match");
        }
    }

    private void setNameAndRole() {
        String role = systemFacade.currentUser.getUser().getAccessRole().toString();
        String[] roleName = role.split("(?=\\p{Upper})");
        StringBuilder capitalizedRoleName = new StringBuilder();
        for (String s : roleName) {
            capitalizedRoleName.append(s.substring(0, 1).toUpperCase()).append(s.substring(1)).append(" ");
        }

        nameText.setText(systemFacade.currentUser.getUser().getName());
        roleText.setText(capitalizedRoleName.toString());
    }

    private void editableElement() {
        switchButton.setAlignment(Pos.CENTER);
        editLabel.setStyle("-fx-font-size: " + 11 + "; -fx-font-weight: regular; -fx-text-fill: " + Info.forgroundColor + ";");
        switchButtonHBox.setSpacing(10);
        switchButtonHBox.setAlignment(Pos.CENTER);
        descriptionTitleHBox.getChildren().add(switchButtonHBox);
    }

    private void enableElements(User.AccessRole accessRole) {
        switch (accessRole) {
            case publicUser:
                sidePanelBackground.getChildren().clear();
                sidePanelBackground.getChildren().add(nameAndRoleAP);
                sidePanelBackground.getChildren().add(loginAP);

                nameAndRoleAP.getChildren().remove(nameAndRole);
                nameAndRoleAP.getChildren().remove(sortingBorderPane);

                descriptionTitleHBox.getChildren().remove(switchButtonHBox);

                usernameTextField.clear();
                passwordTextField.clear();
                break;
            case producer:
                nameAndRoleAP.getChildren().add(nameAndRole);
                nameAndRoleAP.getChildren().add(sortingBorderPane);
                sidePanelBackground.getChildren().add(mineProduktioner);
                editableElement();
                break;
            case productionCompany:
                nameAndRoleAP.getChildren().add(nameAndRole);
                nameAndRoleAP.getChildren().add(sortingBorderPane);
                break;
            case admin:
                nameAndRoleAP.getChildren().add(nameAndRole);
                nameAndRoleAP.getChildren().add(sortingBorderPane);
                break;
            default:
                System.out.println("ERROR while loading user access type.");
        }
    }

    @FXML
    private void handleLogout() {
        systemFacade.currentUser.setUser(null);
        enableElements(User.AccessRole.publicUser);
    }

    // TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private void showCreditList(Production production) {
        descriptionVBox.getChildren().clear();
        for (int i = 0; i < production.getCredits().size(); i++) {
            Credit credit = (Credit) production.getCredits().get(i);
            // TODO get credit role on production (production_credit_role_subscription)
            //Text roleText = new Text(credit.getRole());
            Label name = new Label(credit.getName());
            VBox vb = new VBox();

            //roleText.setFill(Info.accentGradient);
            roleText.setStyle("-fx-font-weight: bold; -fx-font-size:" + Info.fontSizeBig + ";");
            name.setStyle("-fx-font-size: " + Info.fontSizeDefault + "; -fx-text-fill: " + Info.forgroundColor + ";");
            vb.setAlignment(Pos.TOP_CENTER);
            vb.setSpacing(10);

            if (descriptionVBox.getChildren().size() <= 0) {
                descriptionVBox.getChildren().add(vb);
                vb.getChildren().add(roleText);
                vb.getChildren().add(name);
            } else {
                boolean foundRole = false;
                for (int j = 0; j < descriptionVBox.getChildren().size(); j++) {
                    VBox vbox = (VBox) descriptionVBox.getChildren().get(j);
                    Text role = (Text) vbox.getChildren().get(0);

                    // TODO get credit role on production (production_credit_role_subscription)
                    /*
                    if (credit.getRole().equals(role.getText())) {
                        vbox.getChildren().add(name);
                        foundRole = true;
                    }

                    */
                }
                if (!foundRole) {
                    descriptionVBox.getChildren().add(vb);
                    vb.getChildren().add(roleText);
                    vb.getChildren().add(name);
                }
            }
        }
    }

    // Makes the search text white when focused and clicking ENTER searches the focused text

    private void handleSearchFocus() {
        for (int i = 0; i < searchResults.getChildren().size(); i++) {
            AnchorPane ap = (AnchorPane) searchResults.getChildren().get(i);
            Label titleText = (Label) ap.getChildren().get(0);
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
    private boolean selectBlank = true;

    // Updates and displays the search results
    private void updateSearchResultList() {
        String userSearch = textFieldSearchBar.getText().toLowerCase();

        // TODO: Fix select/deselect and writing visibility
        // Only updates when a change is made in the textField
        if (!userSearch.equals(tempUserSearch)) {
            tempUserSearch = userSearch;
            if (!userSearch.isBlank()) {
                searchResults.getChildren().clear();
                for (int i = 0; i < systemFacade.productionLogic.getProductions(userSearch).size(); i++) {
                    // if the text written in the search bar is equal to any result in the production list // DATABASE
                    AnchorPane ap = new AnchorPane();
                    Label titleText = new Label(systemFacade.productionLogic.getProductions(userSearch).get(i).getTitle());

                    searchResults.getChildren().add(ap);
                    ap.getChildren().addAll(titleText);
                }

                if (searchResults.getChildren().size() == 0) {
                    searchRectangleBG.setHeight(searchBarBackground.getHeight());
                }

                styleSearchResults();

            } else {
                // triggers if the search bar becomes empty while the using is typing
                if (systemFacade.currentUser.getSearchHistory().size() > 0) {
                    displaySearchHistory();
                    styleSearchResults();
                } else {
                    searchResults.getChildren().clear();
                    searchRectangleBG.setHeight(searchBarBackground.getHeight());
                }
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
        if (systemFacade.currentUser.getSearchHistory() != null) {
            for (int i = 0; i < systemFacade.currentUser.getSearchHistory().size(); i++) {
                String title = systemFacade.currentUser.getSearchHistory().get(i).getTitle();
                AnchorPane ap = new AnchorPane();
                Label titleText = new Label(title);

                searchResults.getChildren().add(ap);
                ap.getChildren().addAll(titleText);
            }
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
        int searchResultSize = searchResults.getChildren().size();

        if (searchResultSize == 0) {
            searchRectangleBG.setHeight(searchBarBackground.prefHeight(-1)); // no results
        } else if (searchResultSize < Info.visibleResults) { // Maximum amount of search results shown
            searchRectangleBG.setHeight(searchBarBackground.prefHeight(-1) + (titleText.prefHeight(-1) + searchResults.getSpacing()) * (searchResultSize));
        } else {
            searchRectangleBG.setHeight(searchBarBackground.prefHeight(-1) + (titleText.prefHeight(-1) + searchResults.getSpacing()) * (Info.visibleResults));
        }
    }


    // Handles what happens when you search
    // TODO
    @FXML
    private void handleSearch() { // TODO: REWORK EVERYTHING

        String addProgramCommand = "!addProgram ";
        String addCreditCommand = "!addCredit ";

        // Add program logic
        if ((textFieldSearchBar.getText().startsWith(addProgramCommand))) {
            String programTitle = textFieldSearchBar.getText().substring(addProgramCommand.length());
            systemFacade.productionLogic.createProduction(new Production(programTitle));
        }
        // Add credit logic
        else if (textFieldSearchBar.getText().startsWith(addCreditCommand)) {
            String line = (textFieldSearchBar.getText().substring(addCreditCommand.length()));
            String[] content = line.split(" ");


            /*
            for (int i = 0; i < Info.productions.size(); i++) {
                if (content[0].equals(Info.productions.get(i).getTitle())) {
                    Info.productions.get(i).addCredit(new Credit(content[1], content[2]));
                    System.out.println("added " + content[1] + " with the role " + content[2] + " to " + Info.productions.get(i).getTitle());
                }
            }

             */
        }
        // Actual search function
        else {
            if (Info.getProduction(textFieldSearchBar.getText()) != null) {

                Production program = Info.getProduction(textFieldSearchBar.getText());
                if (activeProduction == null) {
                    loadTitleAndDescriptionElements();
                }
                activeProduction = program;
                descriptionTitleText.setText(program.getTitle());
                showCreditList(program);
                systemFacade.currentUser.addToSearchHistory(program);
                calculateSearchBarAnchors();

            } else {
                System.out.println("Production doesn't exist in the database");
            }
        }

        textFieldSearchBar.clear();
        handleDeselect();
        updateProperties();
    }

    private void updateProductionList() {
        if (systemFacade.currentUser.getUser() != null) {
            if (systemFacade.currentUser.getUser().getAccessRole().equals(User.AccessRole.producer)) {
                programList.getChildren().clear();
                for (Production production : systemFacade.currentUser.getMyProductions()) {
                    HBox hb = new HBox();
                    Circle circle = new Circle(4);
                    AnchorPane ap = new AnchorPane();
                    VBox vb = new VBox();
                    Label title = new Label(production.getTitle());
                    Label deadline = new Label(production.getDeadlineString());

                    programList.getChildren().add(hb);
                    hb.getChildren().addAll(circle, ap);
                    ap.getChildren().add(vb);
                    vb.getChildren().addAll(title, deadline);
                    HBox.setHgrow(ap, Priority.ALWAYS);

                    hb.setSpacing(25);
                    hb.setAlignment(Pos.CENTER_LEFT);
                    ap.setCursor(Cursor.HAND);

                    if (production.getStatus() == Status.Red) {
                        circle.setFill(Paint.valueOf(Info.statusRed)); // DATABASE
                    } else if (production.getStatus() == Status.Yellow) {
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
        }
    }

    // Handles when the user clicks on the background to deselect what was previously focused
    @FXML
    private void handleDeselect() {
        backgroundAP.requestFocus();
        searchRectangleBG.setHeight(searchBarBackground.getHeight());
        searchResults.getChildren().clear();
        selectBlank = true;
    }

    private Boolean nameComparatorShift = true;

    @FXML
    private void sortByName() {
        ProductionNameComparator nameComparator = new ProductionNameComparator();
        if (nameComparatorShift) {
            systemFacade.currentUser.getMyProductions().sort(nameComparator);
            nameComparatorShift = false;
            topSortingLabel.setText("A");
            bottomSortingLabel.setText("Z");
        } else {
            systemFacade.currentUser.getMyProductions().sort(nameComparator.reversed());
            nameComparatorShift = true;
            topSortingLabel.setText("Z");
            bottomSortingLabel.setText("A");
        }
        updateProductionList();
    }

    private Boolean deadlineComparatorShift = true;

    @FXML
    private void sortByDeadline() {
        ProductionDeadlineComparator deadlineComparator = new ProductionDeadlineComparator();
        if (deadlineComparatorShift) {
            systemFacade.currentUser.getMyProductions().sort(deadlineComparator);
            deadlineComparatorShift = false;
        } else {
            systemFacade.currentUser.getMyProductions().sort(deadlineComparator.reversed());
            deadlineComparatorShift = true;
        }
        updateProductionList();
    }

    public void calculateSearchBarAnchors() {
        if (activeProduction == null) {
            // triggers when the problem opens
            if (backgroundAP.getHeight() == 0) {
                int windowStartHeight = 720; // Can't seem to get backgroundAP.prefHeight(-1) to give 720, so i'm hardcoding it for now
                AnchorPane.setTopAnchor(searchBarBP, (windowStartHeight / 2) - logoVBox.prefHeight(-1));
                //AnchorPane.setTopAnchor(searchBarBP, (backgroundAP.prefHeight(-1) / 2) - logoVBox.prefHeight(-1));
            } else {
                AnchorPane.setTopAnchor(searchBarBP, (backgroundAP.getHeight() / 2) - logoVBox.prefHeight(-1));
            }
        } else {
            AnchorPane.setTopAnchor(searchBarBP, (double) 30);
        }
    }

    private void loadTitleAndDescriptionElements() {
        backgroundAP.getChildren().remove(searchBarBP);
        backgroundAP.getChildren().add(2, titleAndDescriptionBP);
        descriptionTitleText.applyCss();
        backgroundAP.getChildren().add(3, searchBarBP);
        searchBarBP.getChildren().remove(logoVBox);
    }

    @FXML
    private void homeButtonAction() {
        activeProduction = null;
        backgroundAP.getChildren().remove(titleAndDescriptionBP);
        searchBarBP.getChildren().remove(logoVBox);
        searchBarBP.setTop(logoVBox);
        BorderPane.setAlignment(logoVBox, Pos.TOP_CENTER);
        calculateSearchBarAnchors();
        updateProductionList();
    }

    @FXML
    private void sidePanelAction() {
        // closes the side panel
        if (Info.sidePanelOn) {
            Info.sidePanelOn = false;
            // check if the application just opened
            if (backgroundAP.getHeight() == 0) {
                sideBar.setPrefWidth(0); // closes the side panel without animation
            } else {
                animateSidePanel(0);
            }
            menuCurve.setOpacity(0);
            // opens the side panel
        } else {
            Info.sidePanelOn = true;
            menuCurve.setOpacity(100);
            animateSidePanel(Info.sideBarWidth);
        }
    }

    private void animateSidePanel(int size) {
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(sideBar.prefWidthProperty(), size, Interpolator.EASE_OUT);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
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
        calculateSearchBarAnchors();
    }

    @FXML
    public void closeButtonAction() {
        Platform.exit();
    }
}

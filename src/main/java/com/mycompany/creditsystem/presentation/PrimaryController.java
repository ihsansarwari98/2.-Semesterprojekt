package com.mycompany.creditsystem.presentation;

import com.mycompany.creditsystem.domain.logic.*;

import java.net.URL;
import java.sql.SQLOutput;
import java.util.*;

import com.mycompany.creditsystem.persistence.Production;
import com.mycompany.creditsystem.persistence.User;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
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

    /*
     * UserData:
     * -1   = not a searchable textField
     * 0    = productions
     * 1    = credits
     * 2    = roles
     *
     * AccessRole:
     * 0    = publicUser
     * 1    = producer
     * 2    = productionCompany
     * 3    = admin
     *
     */

    @FXML
    private VBox productionList;
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
    private AnchorPane myProductions;
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
    private VBox descriptionTitleVBox;
    @FXML
    private AnchorPane mineProduktionerProductionCompany;
    @FXML
    private AnchorPane mineProducereProductionCompany;
    @FXML
    private VBox programListProductionCompany;
    @FXML
    private VBox producerListProductionCompany;
    @FXML
    private Rectangle addProducerRectangle1;
    @FXML
    private Rectangle addProducerRectangle2;
    @FXML
    private ScrollPane producerScrollPaneProductionCompany;
    @FXML
    private Label editProductionText;
    @FXML
    private Label cancelEditProductionText;
    @FXML
    private Label saveEditProductionText;
    @FXML
    private Rectangle editProductionRectangle;
    @FXML
    private Rectangle cancelEditProductionRectangle;
    @FXML
    private Rectangle saveEditProductionRectangle;
    @FXML
    private HBox editOptionsHBox;
    @FXML
    private StackPane editProductionButton;
    @FXML
    private StackPane cancelEditProductionButton;
    @FXML
    private StackPane saveEditProductionButton;
    @FXML
    private StackPane deleteProductionButton;
    @FXML
    private VBox descriptionBodyVBox;
    @FXML
    private StackPane searchBarStackPane;
    @FXML
    private VBox searchBarVBox;
    @FXML
    private HBox searchBarHBox;
    @FXML
    private Circle addCreditButton;
    @FXML
    private BorderPane creditBorderPane;
    @FXML
    private HBox descriptionHBoxHeader;
    @FXML
    private VBox vBoxHeader;
    @FXML
    private VBox titleAndDescriptionVBox;
    @FXML
    private Rectangle addProductionRectangle1;
    @FXML
    private Rectangle addProductionRectangle2;
    @FXML
    private Rectangle addCompanyRectangle1;
    @FXML
    private Rectangle addCompanyRectangle2;
    @FXML
    private BorderPane confirmDeletePane;
    @FXML
    private AnchorPane confirmPopUpBackground;
    @FXML
    private Rectangle cancelDeleteRectangle;
    @FXML
    private Rectangle deleteRectangle;
    @FXML
    private HBox titlebarButtonsHBox;
    @FXML
    private StackPane noConfirmationPane;
    @FXML
    private StackPane yesConfirmationPane;
    @FXML
    private Label messageConfirmationLabel;
    @FXML
    private VBox producerList;
    @FXML
    private AnchorPane myProducers;
    @FXML
    private VBox myLists;
    @FXML
    private VBox companyList;
    @FXML
    private AnchorPane myCompanies;
    @FXML
    private StackPane addProductionStackPane;
    @FXML
    private HBox myProductionsHeaderHBox;
    @FXML
    private StackPane addProducerStackPane;
    @FXML
    private HBox myProducersHeaderHBox;

    private boolean scrollableEdit = false;
    private String tempUserSearch = "";
    private boolean selectBlank = true;
    private SystemFacade systemFacade = new SystemFacade();
    private TextField focusedTextField = null;
    private SearchField focusedSearchField = null;
    private SearchField lastSearchField = null;
    private SearchField productionSearchField = new SearchField(searchBarStackPane, searchRectangleBG, searchBarVBox, searchBarBackground, searchBarHBox, textFieldSearchBar, searchResultScrollPane, searchResults);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateProperties();
        enableElements(0);
        checkCanEdit();
        homeButtonAction();
        Info.sidePanelOn = true;
        sidePanelAction();

        // makes the scrollPane in "edit production" scroll to the bottom when adding new credits
        descriptionVBox.heightProperty().addListener(observable -> {
            if (systemFacade.getState().equals("editing") && scrollableEdit) {
                descriptionScrollPane.setVvalue(1D);
                scrollableEdit = false;
            }
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), (ActionEvent event) -> {
            // this code will be called every 30 milliseconds
            updateEverySecond();
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    // Updates the graphical style and fill of the application
    private void updateProperties() {
        Info.updateColors();
        // -- BUTTONS
        // Set the color of the buttons
        loginCircle.setStroke(Info.accentGradient);
        loginCircle1.setFill(Info.accentGradient);
        loginCircle2.setFill(Info.accentGradient);
        addCreditButton.setFill(Info.accentGradient);

        closeRectangle1.setFill(Info.accentGradient);
        closeRectangle2.setFill(Info.accentGradient);
        maximizeRectangle.setFill(Info.accentGradient);
        minimizeRectangle.setFill(Info.accentGradient);
        homeText.setFill(Info.accentGradient);
        editProductionRectangle.setFill(Info.accentGradient);
        cancelEditProductionRectangle.setFill(Info.accentGradient);
        saveEditProductionRectangle.setFill(Info.accentGradient);

        // -- SEARCH BAR
        // Set the color and round the corners of the search bar
        searchBarBackground.setStyle("-fx-background-radius: " + Info.roundAmount + "; -fx-border-radius: " + Info.roundAmount + "; -fx-background-color: " + Info.forgroundColor + ";");
        searchRectangleBG.setFill(Info.accentGradient);
        textFieldSearchBar.setUserData(SearchStatus.productions);

        // -- BACKGROUND
        // Set the color of the shade in the background
        backgroundShade.setFill(Paint.valueOf(Info.backgroundShadeColor));
        backgroundAP.setStyle("-fx-background-color: " + Info.backgroundColor + ";");

        // -- SIDE BAR
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

        usernameTextField.setUserData(SearchStatus.notSearchable);
        passwordTextField.setUserData(SearchStatus.notSearchable);
        // Set the color of side bar buttons
        addProductionRectangle1.setFill(Info.accentGradient);
        addProductionRectangle2.setFill(Info.accentGradient);
        addProducerRectangle1.setFill(Info.accentGradient);
        addProducerRectangle2.setFill(Info.accentGradient);
        addCompanyRectangle1.setFill(Info.accentGradient);
        addCompanyRectangle2.setFill(Info.accentGradient);

        // -- DESCRIPTION
        descriptionScrollPane.getStyleClass().add("dark-thumb");
        descriptionTitleText.setStyle("-fx-text-fill: " + Info.forgroundColor + ";");

        logoText.setFill(Info.accentGradient);
        rectangleLogoSplitter.setFill(Info.accentGradient);

        // Sets confirmation pop-up
        confirmPopUpBackground.setStyle("-fx-border-radius: 16 ; -fx-background-radius: 16 ; -fx-background-color:" + Info.backgroundColor + "; -fx-border-color: DFDFDF;");
        backgroundAP.getChildren().remove(confirmDeletePane);

    }

    // Is getting called many times a second
    private void updateEverySecond() {
        getFocusedSearchField();
    }

    private void getFocusedSearchField() {
        // If the focused node is a textfield and the UserData isn't = SearchStatus.notSearchable (meaning that the textfield is not for searching)
        if ((App.scene.focusOwnerProperty().get() instanceof TextField) &&
                (App.scene.focusOwnerProperty().get().getUserData() != null) &&
                (!App.scene.focusOwnerProperty().get().getUserData().equals(SearchStatus.notSearchable))) {
            focusedTextField = (TextField) App.scene.focusOwnerProperty().get();
            StackPane stackPane = (StackPane) focusedTextField.getParent().getParent().getParent().getParent();
            Rectangle rectangle = (Rectangle) stackPane.getChildren().get(0);
            VBox vbox = (VBox) stackPane.getChildren().get(1);
            AnchorPane anchorPaneBackground = (AnchorPane) vbox.getChildren().get(0);
            HBox hbox = (HBox) anchorPaneBackground.getChildren().get(0);
            ScrollPane scrollPane = (ScrollPane) vbox.getChildren().get(1);
            VBox vboxResults = (VBox) scrollPane.getContent();
            focusedSearchField = new SearchField(stackPane, rectangle, vbox, anchorPaneBackground, hbox, focusedTextField, scrollPane, vboxResults);

            // if the focusedTextField is the textFieldSearchBar then set the correct userData for the searchBar
            if (focusedTextField.equals(textFieldSearchBar)) {
                stackPane.setUserData(NodeOrientation.LEFT_TO_RIGHT);
            }

            if (lastSearchField == null) {
                lastSearchField = focusedSearchField;
            }

            if (!lastSearchField.getTextField().equals(focusedTextField)) {
                handleDeselect(lastSearchField);
                lastSearchField = focusedSearchField;
            }
        }
        if (focusedSearchField != null) {
            handleFocusedSearchField();
        }
    }

    // Method is used for adding new Productions
    @FXML
    private void addProductionHandler(MouseEvent event) {
        addNewHandler("production");
        descriptionTitleText.setText("Add Production");
    }


    private void addProductionCompanyHandler(MouseEvent event) {
        addNewHandler("company");
        descriptionTitleText.setText("Add Company");
    }

    @FXML
    private void addProducerHandler(MouseEvent event) {
        addNewHandler("producer");
        descriptionTitleText.setText("Add Producer");
    }

    private void addAdministratorHandler(MouseEvent event) {
        addNewHandler("administrator");
        descriptionTitleText.setText("Add Administrator");
    }


    // Sets up UI for adding a new entity.
    private void addNewHandler(String addType) {

        // TODO Lav Labels over TextFields
        // Removes unnecessary elements
        descriptionTitleVBox.getChildren().remove(editOptionsHBox);
        titleAndDescriptionVBox.getChildren().remove(vBoxHeader);
        descriptionBodyVBox.getChildren().remove(creditBorderPane);
        backgroundAP.getChildren().remove(searchBarBP);
        backgroundAP.getChildren().remove(logoVBox);
        backgroundAP.getChildren().remove(titleAndDescriptionBP);
        backgroundAP.getChildren().add(titleAndDescriptionBP);


        // Setting up layout for formula
        int searchFieldLength = 300;
        int labelAndSearchFieldVBoxSpacing = 6;
        int labelAndSearchFieldHBoxSpacing = 15;
        descriptionVBox.getChildren().clear();
        descriptionVBox.setSpacing(10);
        descriptionVBox.setFillWidth(false);
        descriptionVBox.setAlignment(Pos.TOP_CENTER);

        descriptionHBoxHeader.setFillHeight(false);
        descriptionHBoxHeader.setPrefWidth(searchFieldLength);

        // setting up the labels
        Label titleLabel = new Label("Title");
        titleLabel.setTextFill(Info.accentGradient);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: " + Info.fontSizeDefault + ";");
        Label companyLabel = new Label("Company");
        companyLabel.setTextFill(Info.accentGradient);
        companyLabel.setStyle("-fx-font-weight: bold; -fx-font-size: " + Info.fontSizeDefault + ";");
        Label usernameLabel = new Label("Username");
        usernameLabel.setTextFill(Info.accentGradient);
        usernameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: " + Info.fontSizeDefault + ";");
        Label passwordLabel = new Label("Password");
        passwordLabel.setTextFill(Info.accentGradient);
        passwordLabel.setStyle("-fx-font-weight: bold; -fx-font-size: " + Info.fontSizeDefault + ";");
        Label producerLabel = new Label("Responsible Producer");
        producerLabel.setTextFill(Info.accentGradient);
        producerLabel.setStyle("-fx-font-weight: bold; -fx-font-size: " + Info.fontSizeDefault + ";");

        // Setting up HBOXes to create form.
        // Title and company name
        HBox titleAndCompanyHBox = new HBox();
        titleAndCompanyHBox.setSpacing(labelAndSearchFieldHBoxSpacing);
        VBox titleVBox = new VBox();
        titleVBox.setAlignment(Pos.TOP_CENTER);
        titleVBox.setSpacing(labelAndSearchFieldVBoxSpacing);
        VBox companyVBox = new VBox();
        companyVBox.setAlignment(Pos.TOP_CENTER);
        companyVBox.setSpacing(labelAndSearchFieldVBoxSpacing);

        titleVBox.getChildren().add(titleLabel);
        companyVBox.getChildren().add(companyLabel);
        titleAndCompanyHBox.getChildren().addAll(titleVBox, companyVBox);

        // username and password
        HBox usernameAndPasswordHBox = new HBox();
        usernameAndPasswordHBox.setSpacing(labelAndSearchFieldHBoxSpacing);
        VBox usernameVBox = new VBox();
        usernameVBox.setAlignment(Pos.TOP_CENTER);
        usernameVBox.setSpacing(labelAndSearchFieldVBoxSpacing);
        VBox passwordVBox = new VBox();
        passwordVBox.setAlignment(Pos.TOP_CENTER);
        passwordVBox.setSpacing(labelAndSearchFieldVBoxSpacing);
        usernameVBox.getChildren().add(usernameLabel);
        passwordVBox.getChildren().add(passwordLabel);
        usernameAndPasswordHBox.getChildren().addAll(usernameVBox, passwordVBox);

        // associated producer
        HBox associatedProducerHBox = new HBox();
        VBox producerVBox = new VBox();
        producerVBox.setAlignment(Pos.TOP_CENTER);
        producerVBox.setSpacing(labelAndSearchFieldVBoxSpacing);
        producerVBox.getChildren().add(producerLabel);
        associatedProducerHBox.getChildren().add(producerVBox);

        HBox submitAndCancelButtonHBox = new HBox();

        // submit button
        StackPane submitButtonStackPane = new StackPane();
        Rectangle submitRectangle = new Rectangle();
        submitRectangle.setArcHeight(40);
        submitRectangle.setArcWidth(40);
        submitRectangle.setHeight(35);
        submitRectangle.setWidth(110);
        submitRectangle.setFill(Info.accentGradient);
        Label submitLabel = new Label("Submit");
        submitLabel.setStyle("-fx-text-fill: " + Info.backgroundColor + "; -fx-font-size: " + Info.fontSizeDefault + "; -fx-font-weight: bold;");
        submitButtonStackPane.getChildren().addAll(submitRectangle, submitLabel);
        submitButtonStackPane.setCursor(Cursor.HAND);

        // Cancel button
        StackPane cancelButtonStackPane = new StackPane();
        Rectangle cancelRectangle = new Rectangle();
        cancelRectangle.setArcHeight(40);
        cancelRectangle.setArcWidth(40);
        cancelRectangle.setHeight(35);
        cancelRectangle.setWidth(110);
        cancelRectangle.setFill(Paint.valueOf(Info.fontColor3));
        Label cancelLabel = new Label("Cancel");
        cancelLabel.setStyle("-fx-text-fill: " + Info.backgroundColor + "; -fx-font-size: " + Info.fontSizeDefault + "; -fx-font-weight: bold;");
        cancelButtonStackPane.getChildren().addAll(cancelRectangle, cancelLabel);
        cancelButtonStackPane.setCursor(Cursor.HAND);

        // add cancel and submit to hbox
        submitAndCancelButtonHBox.setSpacing(labelAndSearchFieldHBoxSpacing);
        submitAndCancelButtonHBox.getChildren().addAll(cancelButtonStackPane, submitButtonStackPane);

        // Adding SearchField to function as text field, with proper styling.
        SearchField titleField = SearchField.createSearchField(SearchStatus.notSearchable,
                "",
                300,
                NodeOrientation.LEFT_TO_RIGHT);
        titleVBox.getChildren().add(titleField.getStackPane());
        styleSearchResults(titleField);
        titleField.getTextField().setPromptText("Title");

        // set the companyFieldSearchStatus so only the admin can search
        SearchStatus companyFieldSearchStatus;
        if (systemFacade.currentUser.getUser().getAccessRoleInt() == 3) {
            companyFieldSearchStatus = SearchStatus.productionCompanies;
        } else {
            companyFieldSearchStatus = SearchStatus.notSearchable;
        }

        // set the productionCompany SearchField
        SearchField companyField = SearchField.createSearchField(companyFieldSearchStatus,
                "",
                300,
                NodeOrientation.LEFT_TO_RIGHT);
        companyVBox.getChildren().add(companyField.getStackPane());
        styleSearchResults(companyField);
        companyField.getTextField().setPromptText("Associated company name");

        // if the user isn't admin
        if (systemFacade.currentUser.getUser().getAccessRoleInt() != 3) {
            companyField.getTextField().setText(systemFacade.currentUser.getUser().getName());
            companyField.getTextField().setEditable(false);
            companyField.getTextField().setCursor(Cursor.DEFAULT);
        }

        // sets the usernameSearchField
        SearchField usernameField = SearchField.createSearchField(SearchStatus.notSearchable,
                "",
                300,
                NodeOrientation.LEFT_TO_RIGHT);
        usernameVBox.getChildren().add(usernameField.getStackPane());
        styleSearchResults(usernameField);
        usernameField.getTextField().setPromptText("Username");

        // sets the passwordSearchField
        SearchField passwordField = SearchField.createSearchField(SearchStatus.notSearchable,
                "",
                300,
                NodeOrientation.LEFT_TO_RIGHT);
        passwordVBox.getChildren().add(passwordField.getStackPane());
        styleSearchResults(passwordField);
        passwordField.getTextField().setPromptText("Password");

        // sets the Associated producer
        SearchField producerField = SearchField.createSearchField(SearchStatus.producers,
                "",
                300,
                NodeOrientation.LEFT_TO_RIGHT);
        producerVBox.getChildren().add(producerField.getStackPane());
        styleSearchResults(producerField);
        producerField.getTextField().setPromptText("Associated producer");

        // Action method for cancel button
        cancelButtonStackPane.setOnMouseClicked(e -> {

            if (systemFacade.getActiveProduction() == null) {
                homeButtonAction();
            } else {
                loadSearchElements(systemFacade.getActiveProduction());
            }
        });

        // Action method for submit button
        // Creating method for getting TextField and submitting to Database
        submitButtonStackPane.setOnMouseClicked(e -> {
            int userID = 0;
            switch (addType) {
                // TODO KODE BODY FOR METODER
                case "production":
                    String productionTitle = titleField.getTextField().getText();

                    if (!producerField.getTextField().getText().isBlank() && !productionTitle.isBlank()) {

                        // if the production doesn't exists
                        if (systemFacade.productionLogic.getProductions(productionTitle).size() <= 0) {
                            systemFacade.productionLogic.createProduction(productionTitle);

                            //Links production to responsible producer
                            userID = systemFacade.userLogic.getIDFromName(producerField.getTextField().getText());
                            systemFacade.productionLogic.linkProductionToUser(systemFacade.productionLogic.getProduction(productionTitle).getId(), userID);
                            System.out.println(userID + " has been linked to production: " + productionTitle);

                            //Links production-Company to responsible producer
                            userID = systemFacade.userLogic.getIDFromName(companyField.getTextField().getText());
                            systemFacade.productionLogic.linkProductionToUser(systemFacade.productionLogic.getProduction(productionTitle).getId(), userID);
                            System.out.println(userID + " has been linked to production: " + productionTitle);

                            System.out.println(userID + " is the ID of Responsible Producer");
                            System.out.println(titleField.getTextField().getText() + " has been added to the system!");

                            updateSidePanel();
                            loadSearchElements(systemFacade.productionLogic.getProduction(productionTitle));

                        } else {
                            System.out.println("production already exists");
                            actionDeniedColorChange(titleField);
                        }
                    } else {
                        if (titleField.getTextField().getText().isBlank()) {
                            actionDeniedColorChange(titleField);
                        }
                        if (producerField.getTextField().getText().isBlank()) {
                            actionDeniedColorChange(producerField);
                        }
                        if (companyField.getTextField().getText().isBlank()) {
                            actionDeniedColorChange(companyField);
                        }
                    }

                    break;
                case "company":

                    break;
                case "producer":
                    String name = titleField.getTextField().getText();
                    String username = usernameField.getTextField().getText();
                    String password = passwordField.getTextField().getText();
                    String company = companyField.getTextField().getText();

                    if (!name.isBlank() && !username.isBlank() && !password.isBlank()) {
                        boolean usernameAvailable = false;
                        boolean nameAvailable = false;

                        // if the name already exists
                        if (systemFacade.userLogic.getUser(name) == null) {
                            nameAvailable = true;
                        } else {
                            System.out.println("Producer already exists");
                            actionDeniedColorChange(titleField);
                        }

                        // if username already exists
                        if (!systemFacade.userLogic.isUsernameTaken(username)) {
                            usernameAvailable = true;
                        } else {
                            System.out.println("username already in use, find another");
                            actionDeniedColorChange(usernameField);
                        }

                        if (usernameAvailable && nameAvailable) {
                            systemFacade.userLogic.createUser(name, username, password, 1);
                            System.out.println("User " + name + " has been added to the database");

                            userID = systemFacade.userLogic.getIDFromName(name);
                            int companyID = systemFacade.userLogic.getIDFromName(companyField.getTextField().getText());

                            systemFacade.userLogic.linkProducerToCompany(userID, companyID);
                            System.out.println(name + " has been linked to " + company);
                            updateSidePanel();

                            titleField.getTextField().appendText("");
                            usernameField.getTextField().appendText("");
                            passwordField.getTextField().appendText("");
                            companyField.getTextField().appendText("");

                        }
                    } else {
                        if (name.isBlank()) {
                            actionDeniedColorChange(titleField);
                        }
                        if (username.isBlank()) {
                            actionDeniedColorChange(usernameField);
                        }
                        if (password.isBlank()) {
                            actionDeniedColorChange(passwordField);
                        }
                        if (company.isBlank()) {
                            actionDeniedColorChange(companyField);
                        }
                    }

                    break;
                default:
                    System.out.println("Something went wrong...");
                    break;
            }
        });

        // Switch statement to add proper HBoxes depending on addType.
        switch (addType) {
            case "production":
                descriptionVBox.getChildren().addAll(titleAndCompanyHBox, associatedProducerHBox, submitAndCancelButtonHBox);
                break;
            case "administrator":
            case "company":
            case "producer":
                descriptionVBox.getChildren().addAll(titleAndCompanyHBox, usernameAndPasswordHBox, submitAndCancelButtonHBox);
                titleLabel.setText("Name");
                titleField.getTextField().setPromptText("Name");
                break;
            default:
                System.out.println("Something went wrong...");
                break;

        }
    }

    private void actionDeniedColorChange(SearchField searchField) {
        // Make the textField red
        if (!searchField.getAnchorPaneBackground().getStyle().contains("-fx-background-color: " + Info.statusRed)) {
            searchField.getAnchorPaneBackground().setStyle("-fx-background-color: " + Info.statusRed + "; -fx-background-radius: " + Info.roundAmount + "; -fx-border-radius: " + Info.roundAmount + ";");
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    searchField.getAnchorPaneBackground().setStyle("-fx-background-color: " + Info.forgroundColor + "; -fx-background-radius: " + Info.roundAmount + "; -fx-border-radius: " + Info.roundAmount + ";");
                }
            };
            timer.schedule(timerTask, 750L);
        }
    }

    @FXML
    private void handleLogin() {
        if (systemFacade.userLogic.userLogin(usernameTextField.getText(), passwordTextField.getText())) {
            sidePanelBackground.getChildren().remove(loginAP);
            enableElements(systemFacade.currentUser.getUser().getAccessRoleInt());

            systemFacade.updateMyLists();
            setNameAndRole();
            sidePanelBackground.getChildren().add(logoutAP);
            handleDeselect();
            updateSidePanel();
            textFieldSearchBar.requestFocus();

        } else {
            enableElements(0);
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

    private boolean checkCanEdit() {
        // Checks if the active production isn't the home screen and the User is logged in
        // As well as if the User has access to the active production
        if (systemFacade.getActiveProduction() != null && systemFacade.currentUser.getUser() != null &&
                (systemFacade.productionLogic.isProductionLinkedToUser(systemFacade.getActiveProduction().getId(), systemFacade.currentUser.getUser().getId()) ||
                        systemFacade.currentUser.getUser().getAccessRoleInt() == 3)) { // accessRole admin
            loadEditElement(true);
            return true;
        } else {
            loadEditElement(false);
            return false;
        }
    }

    // Loads the edit production button, and removes the save and cancel button
    private void loadEditElement(boolean toggle) {
        if (toggle) {
            descriptionTitleVBox.getChildren().remove(editOptionsHBox);
            descriptionTitleVBox.getChildren().add(0, editOptionsHBox);
            editOptionsHBox.getChildren().removeAll(cancelEditProductionButton, saveEditProductionButton, editProductionButton, deleteProductionButton);
            editOptionsHBox.getChildren().add(editProductionButton);

        } else {
            descriptionTitleVBox.getChildren().remove(editOptionsHBox);
        }
    }

    // Loads and removes node elements based on the users access role
    private void enableElements(int accessRoleNumber) {
        switch (accessRoleNumber) {
            case 0:
                titleAndDescriptionVBox.getChildren().remove(vBoxHeader);
                descriptionBodyVBox.getChildren().remove(creditBorderPane);

                sidePanelBackground.getChildren().clear();
                myLists.getChildren().remove(myProductions);
                myLists.getChildren().remove(myProducers);
                myLists.getChildren().remove(myCompanies);
                myProductionsHeaderHBox.getChildren().remove(addProductionStackPane);
                myProducersHeaderHBox.getChildren().remove(addProducerStackPane);

                sidePanelBackground.getChildren().add(nameAndRoleAP);
                sidePanelBackground.getChildren().add(loginAP);

                nameAndRoleAP.getChildren().remove(nameAndRole);
                nameAndRoleAP.getChildren().remove(sortingBorderPane);

                usernameTextField.clear();
                passwordTextField.clear();

                checkCanEdit();
                break;
            case 1:
                nameAndRoleAP.getChildren().add(nameAndRole);
                nameAndRoleAP.getChildren().add(sortingBorderPane);
                sidePanelBackground.getChildren().add(myLists);
                myLists.getChildren().add(myProductions);

                checkCanEdit();
                break;
            case 2:
                nameAndRoleAP.getChildren().add(nameAndRole);
                nameAndRoleAP.getChildren().add(sortingBorderPane);
                sidePanelBackground.getChildren().add(myLists);
                myLists.getChildren().add(myProductions);
                myLists.getChildren().add(myProducers);
                myProductionsHeaderHBox.getChildren().add(addProductionStackPane);
                myProducersHeaderHBox.getChildren().add(addProducerStackPane);
                checkCanEdit();
                break;
            case 3:
                nameAndRoleAP.getChildren().add(nameAndRole);
                nameAndRoleAP.getChildren().add(sortingBorderPane);
                sidePanelBackground.getChildren().add(myLists);
                myLists.getChildren().add(myCompanies);
                checkCanEdit();
                break;
            default:
                System.out.println("ERROR while loading user access type.");
        }
    }

    @FXML
    private void handleLogout() {
        systemFacade.currentUser.setUser(null);
        enableElements(0);
        homeButtonAction();
    }

    @FXML
    private void addCredit() {

        HBox hBox = new HBox();
        hBox.setSpacing(20);

        descriptionVBox.getChildren().add(hBox);
        scrollableEdit = true;

        SearchField creditSearchField = SearchField.createSearchField(SearchStatus.credits,
                "",
                300,
                NodeOrientation.RIGHT_TO_LEFT);
        hBox.getChildren().add(creditSearchField.getStackPane());
        styleSearchResults(creditSearchField);

        hBox.getChildren().add(createRemoveCreditElement());

        SearchField roleSearchField = SearchField.createSearchField(SearchStatus.roles,
                "",
                300,
                NodeOrientation.LEFT_TO_RIGHT);
        hBox.getChildren().add(roleSearchField.getStackPane());
        styleSearchResults(roleSearchField);
    }

    // Shows the list of credits connected to a production
    private void showCreditList() {
        // Sets properties for the parent VBox
        descriptionVBox.getChildren().clear();
        descriptionVBox.setAlignment(Pos.TOP_CENTER);
        systemFacade.setState("searching");
        titleAndDescriptionVBox.getChildren().remove(vBoxHeader);
        descriptionBodyVBox.getChildren().remove(creditBorderPane);

        Map creditWithRolesInProduction = systemFacade.creditLogic.getCreditWithRole(systemFacade.getActiveProduction().getId());

        // Loops through every credit in the active production
        for (Object creditWithRole : creditWithRolesInProduction.entrySet()) {
            Map.Entry creditWithRoleObject = (Map.Entry) creditWithRole;

            // Gets the role of the credit
            Text roleText = new Text(creditWithRoleObject.getValue().toString());

            // Gets the name of the credit
            Label name = new Label(creditWithRoleObject.getKey().toString());

            // Creates a vBox for storing credits with the same role
            VBox vb = new VBox();

            // Style the roleText, name and Vbox
            roleText.setFill(Info.accentGradient);
            roleText.setStyle("-fx-font-weight: bold; -fx-font-size:" + Info.fontSizeBig + ";");
            name.setStyle("-fx-font-size: " + Info.fontSizeDefault + "; -fx-text-fill: " + Info.forgroundColor + ";");
            vb.setAlignment(Pos.TOP_CENTER);
            vb.setSpacing(10);

            boolean foundRole = false;
            // Loop through the descriptionVBox to check if the role of the credit exists as it's own vBox
            for (int j = 0; j < descriptionVBox.getChildren().size(); j++) {
                VBox vbox = (VBox) descriptionVBox.getChildren().get(j);
                Text role = (Text) vbox.getChildren().get(0);

                // If the role is already in the list, add the credit's name to the vBox
                if (creditWithRoleObject.getValue().toString().equals(role.getText())) {
                    vbox.getChildren().add(name);
                    foundRole = true;
                }
            }
            // If the role isn't in the vBox, add it to the descriptionVBox
            if (!foundRole) {
                descriptionVBox.getChildren().add(vb);
                vb.getChildren().add(roleText);
                vb.getChildren().add(name);
            }
        }
    }

    private void styleSearchResults(SearchField searchField) {
        searchField.getRectangle().setFill(Info.accentGradient);
        searchField.getRectangle().setArcHeight(40);
        searchField.getRectangle().setArcWidth(40);

        searchField.getAnchorPaneBackground().setStyle("-fx-background-color: " + Info.forgroundColor + "; -fx-border-radius: " + Info.roundAmount + "; -fx-background-radius: " + Info.roundAmount + ";");

        int searchResultSpacing = 10;
        int mainSearchResultTextPadding = 50;
        int searchResultTextPadding = 20;

        for (int i = 0; i < searchField.getvBoxResults().getChildren().size(); i++) {
            AnchorPane ap = (AnchorPane) searchField.getvBoxResults().getChildren().get(i);
            Label titleText = (Label) ap.getChildren().get(0);

            searchField.getvBoxResults().setAlignment(Pos.TOP_CENTER);
            searchField.getvBoxResults().setSpacing(searchResultSpacing);
            ap.setCursor(Cursor.HAND);

            titleText.setStyle("-fx-text-fill: " + Info.fontColor2 + "; -fx-font-size: " + Info.fontSizeDefault + ";");

            if (searchField.getStackPane().getUserData().equals(NodeOrientation.LEFT_TO_RIGHT)) {
                if (searchField.getTextField().equals(textFieldSearchBar)) {
                    titleText.setPadding(new Insets(0, 0, 0, mainSearchResultTextPadding));
                } else {
                    titleText.setPadding(new Insets(0, 0, 0, searchResultTextPadding));
                }
            } else {
                AnchorPane.setRightAnchor(titleText, (double) 0);
                titleText.setPadding(new Insets(0, searchResultTextPadding, 0, 0));
            }

            ap.setOnMouseEntered(this::handleSearchMenuHoveringEnter);
            ap.setOnMouseExited(this::handleSearchMenuHoveringExit);
            ap.setOnMouseClicked(this::handleSearchMenuHoveringClicked);

            titleText.applyCss();
            calculateSearchResultsHeight(searchField, titleText);
            // makes the scrollbar unable to extend beyond the background rectangle
            searchField.getScrollPane().setMaxHeight(searchField.getRectangle().getHeight() - searchField.getAnchorPaneBackground().getHeight());
            searchField.getScrollPane().setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            // enables the white thumb theme to the scrollbar
            searchField.getScrollPane().getStyleClass().add("white-thumb");
        }
    }

    // Calculates and sets the height of the search result tab
    private void calculateSearchResultsHeight(SearchField searchField, Label titleText) {
        if (searchField.getvBoxResults().getChildren().size() == 0) {
            searchField.getRectangle().setHeight(searchField.getAnchorPaneBackground().prefHeight(-1)); // no results
        } else if (searchField.getvBoxResults().getChildren().size() < Info.visibleResults) { // Maximum amount of search results shown
            searchField.getRectangle().setHeight(searchField.getAnchorPaneBackground().prefHeight(-1) + (titleText.prefHeight(-1) + searchField.getvBoxResults().getSpacing()) * (searchField.getvBoxResults().getChildren().size()));
        } else {
            searchField.getRectangle().setHeight(searchField.getAnchorPaneBackground().prefHeight(-1) + (titleText.prefHeight(-1) + searchField.getvBoxResults().getSpacing()) * (Info.visibleResults));
        }
    }

    private void handleFocusedSearchField() {
        if (!focusedSearchField.getTextField().getUserData().equals(SearchStatus.notSearchable) && !focusedSearchField.getTextField().getText().isBlank()) {
            // Only updates when a change is made in the textField
            if (!focusedSearchField.getTextField().getText().equals(tempUserSearch)) {
                tempUserSearch = focusedSearchField.getTextField().getText();
                ArrayList searchResults = getSearchResultList((SearchStatus) focusedSearchField.getTextField().getUserData(), focusedTextField.getText());
                focusedSearchField.setSearchResults(searchResults);

                focusedSearchField.getvBoxResults().getChildren().clear();
                // add search result AnchorPane and titleText for every
                for (int i = 0; i < focusedSearchField.getSearchResults().size(); i++) {

                    AnchorPane ap = new AnchorPane();
                    Label titleText = new Label();

                    // displays the searchResult
                    Object searchResult = focusedSearchField.getSearchResults().get(i);
                    ap.setUserData(searchResult);
                    titleText.setText(searchResult.toString());

                    focusedSearchField.getvBoxResults().getChildren().add(ap);
                    ap.getChildren().add(titleText);
                    titleText.setAlignment(Pos.CENTER_RIGHT);
                    titleText.setFocusTraversable(true);
                }

                if (focusedSearchField.getvBoxResults().getChildren().size() == 0) {
                    focusedSearchField.getRectangle().setHeight(focusedSearchField.getAnchorPaneBackground().getHeight());
                }

                styleSearchResults(focusedSearchField);

            }
            // Controlling search results and blank search field
        } else if (focusedSearchField.getTextField().getText().isBlank()) {
            if (focusedSearchField.getTextField().getUserData().equals(SearchStatus.productions) && systemFacade.currentUser.getSearchHistory().size() > 0) {
                displaySearchHistory();
                styleSearchResults(focusedSearchField);
            } else {
                focusedSearchField.getvBoxResults().getChildren().clear();
                focusedSearchField.getRectangle().setHeight(focusedSearchField.getAnchorPaneBackground().getHeight());
            }
        }
        handleSearchFocus();
    }

    // Makes the search text white when focused and clicking ENTER searches the focused text
    private void handleSearchFocus() {
        for (int i = 0; i < focusedSearchField.getvBoxResults().getChildren().size(); i++) {
            AnchorPane ap = (AnchorPane) focusedSearchField.getvBoxResults().getChildren().get(i);
            Label titleText = (Label) ap.getChildren().get(0);

            if (titleText.isHover() || titleText.isFocused()) {
                titleText.setStyle("-fx-text-fill: " + Info.fontColor1 + "; -fx-font-size: " + Info.fontSizeDefault + ";");
                titleText.setOnKeyPressed(e -> {
                    if (e.getCode() == KeyCode.ENTER) {

                        // makes the focus travel a lot smoother
                        for (int j = 0; j < descriptionVBox.getChildren().size(); j++) {
                            HBox hbox = (HBox) descriptionVBox.getChildren().get(j);
                            if (j + 1 > descriptionVBox.getChildren().size()) {
                                StackPane stackPane1 = (StackPane) hbox.getChildren().get(0);
                                SearchField searchField1 = new SearchField(stackPane1);
                                StackPane stackPane2 = (StackPane) hbox.getChildren().get(2);
                                SearchField searchField2 = new SearchField(stackPane2);
                                if (searchField1.getTextField().equals(focusedSearchField.getTextField())) {
                                    searchField2.getTextField().requestFocus();
                                } else if (searchField2.getTextField().equals(focusedSearchField.getTextField()) && !(j + 1 >= descriptionVBox.getChildren().size())) {
                                    HBox hboxNext = (HBox) descriptionVBox.getChildren().get(j + 1);
                                    StackPane nextStackPane = (StackPane) hboxNext.getChildren().get(0);
                                    SearchField nextSearchField = new SearchField(nextStackPane);
                                    System.out.println(nextSearchField);
                                    nextSearchField.getTextField().requestFocus();
                                }
                            }
                        }
                        // sets the text as the TextField's text or searches for a production
                        focusedSearchField.getTextField().setText(titleText.getText());
                        if (focusedSearchField.getTextField().getUserData().equals(SearchStatus.productions)) {
                            loadSearchElements(ap.getUserData());
                        }
                    }
                });
            } else {
                titleText.setStyle("-fx-text-fill: " + Info.fontColor2 + "; -fx-font-size: " + Info.fontSizeDefault + ";");
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

    // Handles what happens when you search
    @FXML
    private void handleSearch() {
        loadSearchElements(getSpecificSearchResult(SearchStatus.productions, focusedSearchField.getTextField().getText()));
    }

    private ArrayList getSearchResultList(SearchStatus searchStatus, String search) {

        ArrayList searchResults = null;
        if (!search.isBlank() && !searchStatus.equals(SearchStatus.notSearchable)) {
            if (searchStatus.equals(SearchStatus.productions)) {
                searchResults = systemFacade.productionLogic.getProductions(search);
            } else if (searchStatus.equals(SearchStatus.credits)) {
                searchResults = systemFacade.creditLogic.getCredits(search);
            } else if (searchStatus.equals(SearchStatus.roles)) {
                searchResults = systemFacade.roleLogic.getRoles(search);
            } else if (searchStatus.equals(SearchStatus.producers)) {
                searchResults = systemFacade.userLogic.getProducers(search);
            } else if (searchStatus.equals(SearchStatus.productionCompanies)) {
                searchResults = systemFacade.userLogic.getProductionCompanies(search);
            } else if (searchStatus.equals(SearchStatus.administrators)) {
                searchResults = systemFacade.userLogic.getAdministrators(search);
            } else {
                System.out.println("An error occurred: searchStatus not set correctly");
            }
        }

        return searchResults;
    }

    private Object getSpecificSearchResult(SearchStatus searchStatus, String search) {

        Object searchResult = null;
        ArrayList searchResults = getSearchResultList(searchStatus, search);

        if (searchResults != null && searchResults.size() > 0) {
            if (searchResults.size() == 1) {
                searchResult = searchResults.get(0);
            }
        }
        return searchResult;
    }

    private void loadSearchElements(Object object) {
        if (object != null) {
            if (systemFacade.getActiveProduction() == null) {
                loadTitleAndDescriptionElements();
            } else {
                homeButtonAction();
                loadTitleAndDescriptionElements();
            }

            if (object instanceof Production) {
                // set the active production to be the current production
                systemFacade.setActiveProduction(systemFacade.productionLogic.getProduction(object.toString()));
                // add title to title text element
                descriptionTitleText.setText(object.toString());
                // add production to search history
                systemFacade.currentUser.addToSearchHistory(systemFacade.productionLogic.getProduction(object.toString()));
                showCreditList();
                calculateSearchBarAnchors();
                checkCanEdit();

            } else if (object instanceof User) {
                titleAndDescriptionVBox.getChildren().remove(vBoxHeader);
                descriptionBodyVBox.getChildren().remove(creditBorderPane);
                backgroundAP.getChildren().remove(searchBarBP);
                descriptionVBox.getChildren().clear();
                descriptionTitleText.setText(object.toString());
                calculateSearchBarAnchors();
                checkCanEdit();
                loadUserDescription((User) object);
            }
        }

        textFieldSearchBar.clear();
        handleDeselect();
        updateProperties();
    }


    private void loadUserDescription(User user) {
        //Sets up the subtext, of the title
        Label subtitle = new Label(user.getAccessRole().toString() + " | Created : " + user.getCreationDate());
        Label relatedProdstitle = new Label("Related Productions");

        subtitle.setStyle("-fx-text-fill: grey; -fx-padding: 20px 0 10px 0;");
        relatedProdstitle.setStyle("-fx-font-weight: bold; -fx-font-size: " + Info.fontSizeDefault + ";");

        //Sets up for "related productions" section
        descriptionVBox.getChildren().addAll(subtitle);
        if (systemFacade.productionLogic.getProductionsLinkedToUser(user.getId()).size() >= 1) {
            descriptionVBox.getChildren().addAll(relatedProdstitle);
        }

        //Sets up every Production
        ArrayList productionsLinkedToUser = systemFacade.productionLogic.getProductionsLinkedToUser(user.getId());
        for (int i = 0; i < productionsLinkedToUser.size(); i++) {
            VBox vBox = new VBox();
            descriptionVBox.getChildren().add(vBox);
            int x = i;
            Rectangle split = new Rectangle(30, 1, Info.accentGradient);

            Label label = new Label(productionsLinkedToUser.get(i).toString());
            label.setCursor(Cursor.HAND);
            label.setStyle("-fx-text-fill: " + Info.forgroundColor + "; -fx-font-size: " + Info.fontSizeDefault + "; -fx-padding: 8px 0px 16px 0px");
            vBox.getChildren().addAll(label, split);
            vBox.setAlignment(Pos.CENTER);

            label.setOnMouseEntered(event -> {
                label.setTextFill(Info.accentGradient);
            });
            label.setOnMouseExited(event -> {
                label.setTextFill(Paint.valueOf(Info.forgroundColor));
            });

            label.setOnMouseClicked(e -> {
                loadSearchElements(systemFacade.productionLogic.getProduction(productionsLinkedToUser.get(x).toString()));
            });
        }
        //Creating delete producer Button, GUI
        StackPane deleteProducerButtonPane = new StackPane();
        Text deleteProducerTxt = new Text("Delete Producer");
        Rectangle backgroundColor = new Rectangle();

        backgroundColor.setArcHeight(16);
        backgroundColor.setArcWidth(16);
        backgroundColor.setHeight(35);
        backgroundColor.setWidth(150);

        deleteProducerTxt.setFill(Paint.valueOf(Info.fontColor1));

        backgroundColor.setFill(Info.accentGradient);

        deleteProducerTxt.setStyle("-fx-text-fill:" + Info.backgroundColor + "; -fx-font-weight: bold; -fx-font-size: " + Info.fontSizeDefault + ";");
        deleteProducerButtonPane.getChildren().addAll(backgroundColor, deleteProducerTxt);

        descriptionVBox.getChildren().add(deleteProducerButtonPane);
        descriptionVBox.setMargin(deleteProducerButtonPane, new Insets(20, 0, 0, 0));

        //Setting up functionality
        deleteProducerButtonPane.setCursor(Cursor.HAND);
        deleteProducerButtonPane.setOnMouseClicked(e -> {
            systemFacade.userLogic.deleteUser(systemFacade.userLogic.getIDFromName(user.toString()));
            System.out.println(user.toString() + " has now been deleted");
            homeButtonAction();
            updateSidePanel();
        });
    }

    private void editProduction() {
        if (systemFacade.getActiveProduction() != null) {
            int searchFieldLength = 300;
            systemFacade.setState("editing");

            descriptionVBox.getChildren().clear();
            descriptionVBox.setSpacing(10);
            descriptionVBox.setFillWidth(false);
            descriptionVBox.setAlignment(Pos.TOP_CENTER);

            descriptionHBoxHeader.setFillHeight(false);
            descriptionHBoxHeader.setPrefWidth(searchFieldLength);

            Text nameCaption = new Text("Credit name");
            Text roleCaption = new Text("Credit role");

            nameCaption.setStyle("-fx-font-weight: bold; -fx-font-size: " + Info.fontSizeDefault + ";");
            roleCaption.setStyle("-fx-font-weight: bold; -fx-font-size: " + Info.fontSizeDefault + ";");

            nameCaption.setFill(Info.accentGradient);
            roleCaption.setFill(Info.accentGradient);

            descriptionHBoxHeader.getChildren().clear();
            descriptionHBoxHeader.getChildren().addAll(nameCaption, roleCaption);

            Map creditWithRolesInProduction = systemFacade.creditLogic.getCreditWithRole(systemFacade.getActiveProduction().getId());

            for (Object creditWithRole : creditWithRolesInProduction.entrySet()) {
                Map.Entry creditWithRoleObject = (Map.Entry) creditWithRole;

                HBox hBox = new HBox();
                hBox.setAlignment(Pos.TOP_CENTER);
                hBox.setSpacing(20);
                descriptionVBox.getChildren().add(hBox);

                // Create credit name searchField
                SearchField creditSearchField = SearchField.createSearchField(SearchStatus.credits,
                        creditWithRoleObject.getKey().toString(),
                        searchFieldLength,
                        NodeOrientation.RIGHT_TO_LEFT);
                hBox.getChildren().add(creditSearchField.getStackPane());
                styleSearchResults(creditSearchField);


                // add delete-credit element
                hBox.getChildren().add(createRemoveCreditElement());

                // Create credit role searchField
                SearchField roleSearchField = SearchField.createSearchField(SearchStatus.roles,
                        creditWithRoleObject.getValue().toString(),
                        searchFieldLength,
                        NodeOrientation.LEFT_TO_RIGHT);
                hBox.getChildren().add(roleSearchField.getStackPane());
                styleSearchResults(roleSearchField);

            }
        }
    }

    private StackPane createRemoveCreditElement() {
        // Create elements
        StackPane stackPane = new StackPane();
        Rectangle r1 = new Rectangle();
        Rectangle r2 = new Rectangle();

        // add properties to r1
        r1.setFill(Info.accentGradient);
        r1.setHeight(16);
        r1.setWidth(3);
        r1.setRotate(45);

        // add properties to r2
        r2.setFill(Info.accentGradient);
        r2.setHeight(16);
        r2.setWidth(3);
        r2.setRotate(-45);

        // add properties to stackPane
        stackPane.getChildren().addAll(r1, r2);
        stackPane.setAlignment(Pos.TOP_CENTER);
        stackPane.setPadding(new Insets(10, 0, 0, 0));
        stackPane.setCursor(Cursor.HAND);

        // add an onClick event to the stackPane, that removes the credit
        stackPane.setOnMouseClicked(e -> {
            HBox hBox = (HBox) stackPane.getParent();
            descriptionVBox.getChildren().remove(hBox);

        });

        return stackPane;
    }

    private void saveCredits() {
        // Removes all credits from a production
        systemFacade.creditLogic.removeAllCreditsFromProduction(systemFacade.getActiveProduction().getId());

        // Loop through and get Credit name and role TextFields
        for (int i = 0; i < descriptionVBox.getChildren().size(); i++) {
            HBox hBox = (HBox) descriptionVBox.getChildren().get(i);

            // Credit name Textfield
            StackPane stackPane1 = (StackPane) hBox.getChildren().get(0);
            VBox vBox1 = (VBox) stackPane1.getChildren().get(1);
            AnchorPane anchorPane1 = (AnchorPane) vBox1.getChildren().get(0);
            HBox hBox1 = (HBox) anchorPane1.getChildren().get(0);
            TextField creditNameTextField = (TextField) hBox1.getChildren().get(0);

            // Credit role Textfield
            StackPane stackPane2 = (StackPane) hBox.getChildren().get(2);
            VBox vBox2 = (VBox) stackPane2.getChildren().get(1);
            AnchorPane anchorPane2 = (AnchorPane) vBox2.getChildren().get(0);
            HBox hBox2 = (HBox) anchorPane2.getChildren().get(0);
            TextField creditRoleTextField = (TextField) hBox2.getChildren().get(0);

            // If the credit doesn't exist, add it to the system
            if (systemFacade.creditLogic.getCredits(creditNameTextField.getText()).size() <= 0) {
                System.out.println("Adding " + creditNameTextField.getText() + " to the database in Credits");
                systemFacade.creditLogic.createStringCredit(creditNameTextField.getText());
            }

            // If the role doesn't exist, add it to the system
            if (systemFacade.roleLogic.getRoles(creditRoleTextField.getText()).size() <= 0) {
                System.out.println("Adding " + creditRoleTextField.getText() + " to the database in Roles");
                systemFacade.roleLogic.createStringRole(creditRoleTextField.getText());
            }

            int roleId = systemFacade.roleLogic.getRoles(creditRoleTextField.getText()).get(0).getId();
            int creditId = systemFacade.creditLogic.getCredits(creditNameTextField.getText()).get(0).getId();

            // Add credit and role to the production
            if (!(roleId == 0 || creditId == 0)) { // NOR-gate
                systemFacade.creditLogic.addCreditRelation(systemFacade.activeProduction.getId(), creditId, roleId);
            }
        }
    }

    private void updateProducerList() {
        producerList.getChildren().clear();
        ArrayList producers = systemFacade.currentUser.getMyProducers();

        for (Object producer : producers) {
            HBox hb = new HBox();
            Circle circle = new Circle(4);
            AnchorPane ap = new AnchorPane();
            VBox vb = new VBox();
            Label name = new Label(producer.toString());
            producerList.getChildren().add(hb);

            hb.getChildren().addAll(circle, ap);
            ap.getChildren().add(vb);
            vb.getChildren().add(name);

            name.setWrapText(true);
            ap.setUserData(producer);
            hb.setSpacing(25);
            hb.setAlignment(Pos.CENTER_LEFT);
            ap.setCursor(Cursor.HAND);
            HBox.setHgrow(ap, Priority.ALWAYS);

            ap.setOnMouseClicked(e -> {
                AnchorPane source = (AnchorPane) e.getSource();
                loadSearchElements(source.getUserData());
            });

            circle.setFill(Info.accentGradient);
            name.setStyle("-fx-text-fill: " + Info.fontColor1 + "; -fx-font-size: " + Info.fontSizeDefault + ";");
        }
    }

    private void updateProductionCompanyList() {
        companyList.getChildren().clear();

    }

    private void updateSidePanel() {
        systemFacade.updateMyLists();
        if (systemFacade.currentUser.getUser().getAccessRoleInt() == 1) {
            updateProductionList();
        } else if (systemFacade.currentUser.getUser().getAccessRoleInt() == 2) {
            updateProductionList();
            updateProducerList();
        } else if (systemFacade.currentUser.getUser().getAccessRoleInt() == 3) {
            updateProductionList();
            updateProductionCompanyList();
        }
        calculateMyListHeight();
    }

    private void updateProductionList() {
        productionList.getChildren().clear();

        for (int i = 0; i < systemFacade.currentUser.getMyProductions().size(); i++) {

            HBox hb = new HBox();
            Circle circle = new Circle(4);
            AnchorPane ap = new AnchorPane();
            VBox vb = new VBox();
            Label title = new Label(systemFacade.currentUser.getMyProductions().get(i).getTitle());
            Label deadline = new Label(systemFacade.currentUser.getMyProductions().get(i).getDeadlineString());

            productionList.getChildren().add(hb);

            hb.getChildren().addAll(circle, ap);
            ap.getChildren().add(vb);
            vb.getChildren().addAll(title, deadline);
            title.setWrapText(true);
            ap.setUserData(systemFacade.currentUser.getMyProductions().get(i));
            HBox.setHgrow(ap, Priority.ALWAYS);

            hb.setSpacing(25);
            hb.setAlignment(Pos.CENTER_LEFT);
            ap.setCursor(Cursor.HAND);

            if (systemFacade.currentUser.getMyProductions().get(i).getStatusInt() == 0) {
                circle.setFill(Paint.valueOf(Info.statusRed));
            } else if (systemFacade.currentUser.getMyProductions().get(i).getStatusInt() == 1) {
                circle.setFill(Paint.valueOf(Info.statusYellow));
            } else {
                circle.setFill(Paint.valueOf(Info.statusGreen));
            }

            ap.setOnMouseClicked(e -> {
                AnchorPane source = (AnchorPane) e.getSource();
                loadSearchElements(source.getUserData());
            });

            title.setStyle("-fx-text-fill: " + Info.fontColor1 + "; -fx-font-size: " + Info.fontSizeDefault + ";");
            deadline.setStyle("-fx-text-fill: " + Info.fontColor3 + "; -fx-font-size: " + Info.fontSizeSmall + ";");
        }
    }

    // Handles when the user clicks on the background to deselect what was previously focused
    private void handleDeselect(SearchField searchField) {
        if (searchField != null) {
            if (!searchField.getTextField().getUserData().equals(SearchStatus.notSearchable)) {
                searchField.getRectangle().setHeight(searchField.getAnchorPaneBackground().getHeight());
                searchField.getvBoxResults().getChildren().clear();
            }
        }
    }

    @FXML
    private void handleDeselect() {
        if (focusedSearchField != null) {
            if (!focusedSearchField.getTextField().getUserData().equals(SearchStatus.notSearchable)) {
                focusedSearchField.getRectangle().setHeight(focusedSearchField.getAnchorPaneBackground().getHeight());
                focusedSearchField.getvBoxResults().getChildren().clear();
            }
        }

        focusedSearchField = null;
        selectBlank = true;
        backgroundAP.requestFocus();

    }

    @FXML
    private void sortByName() {
        if (systemFacade.currentUser.sortByName()) {
            topSortingLabel.setText("A");
            bottomSortingLabel.setText("Z");
        } else {
            topSortingLabel.setText("Z");
            bottomSortingLabel.setText("A");
        }
        updateSidePanel();
    }

    @FXML
    private void sortByDeadline() {
        systemFacade.currentUser.sortByDeadline();
        updateSidePanel();
    }

    private void calculateSearchBarAnchors() {
        if (systemFacade.getActiveProduction() == null) {
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

    private void calculateMyListHeight() {
        // had to hardcode the default list height because "myLists.getHeight()" returns 0
        double myListHeight = 445;
        // sets the height to the correct size when maximizing
        if (App.stage.isMaximized()) {
            myListHeight = myLists.getHeight();
        }
        if (myLists.getChildren().size() >= 1) {
            // get the spacing between the elements in the list
            double spacing = (myLists.getChildren().size() - 1) * myLists.getSpacing();
            // subtract it from the full height
            double heightWithoutSpacing = myListHeight - spacing;
            // loop through the list and give each element the height divided by the amount of elements
            for (int i = 0; i < myLists.getChildren().size(); i++) {
                AnchorPane anchorPane = (AnchorPane) myLists.getChildren().get(i);
                anchorPane.setPrefHeight((heightWithoutSpacing) / (myLists.getChildren().size()));
            }
        }
    }

    private void loadTitleAndDescriptionElements() {
        backgroundAP.getChildren().remove(titleAndDescriptionBP);
        backgroundAP.getChildren().add(2, titleAndDescriptionBP);
        descriptionVBox.setAlignment(Pos.TOP_CENTER);
        descriptionTitleText.applyCss();
        backgroundAP.getChildren().remove(searchBarBP);
        backgroundAP.getChildren().add(3, searchBarBP);
        searchBarBP.getChildren().remove(logoVBox);
    }

    @FXML
    private void homeButtonAction() {
        systemFacade.setActiveProduction(null);
        backgroundAP.getChildren().remove(titleAndDescriptionBP);
        backgroundAP.getChildren().remove(searchBarBP);
        backgroundAP.getChildren().add(3, searchBarBP);
        searchBarBP.getChildren().remove(logoVBox);
        searchBarBP.setTop(logoVBox);
        BorderPane.setAlignment(logoVBox, Pos.TOP_CENTER);
        calculateSearchBarAnchors();
        checkCanEdit();
        systemFacade.setState("home");
    }

    @FXML
    private void sidePanelAction() {
        // closes the side panel
        if (Info.sidePanelOn) {
            Info.sidePanelOn = false;
            usernameTextField.setFocusTraversable(false);
            passwordTextField.setFocusTraversable(false);
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
            usernameTextField.setFocusTraversable(true);
            passwordTextField.setFocusTraversable(true);
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
    private void handleEditProductionClick(MouseEvent event) {
        descriptionTitleVBox.getChildren().remove(editOptionsHBox);
        descriptionTitleVBox.getChildren().add(0, editOptionsHBox);
        editOptionsHBox.getChildren().remove(editProductionButton);
        editOptionsHBox.getChildren().addAll(cancelEditProductionButton, saveEditProductionButton, deleteProductionButton);
        titleAndDescriptionVBox.getChildren().add(1, vBoxHeader);
        descriptionBodyVBox.getChildren().add(creditBorderPane);
        editProduction();
    }

    @FXML
    private void handleCancelEditProductionClick(MouseEvent event) {
        System.out.println("canceled edit");
        descriptionTitleVBox.getChildren().remove(editOptionsHBox);
        descriptionTitleVBox.getChildren().add(0, editOptionsHBox);
        editOptionsHBox.getChildren().removeAll(cancelEditProductionButton, saveEditProductionButton, deleteProductionButton);
        editOptionsHBox.getChildren().add(editProductionButton);
        titleAndDescriptionVBox.getChildren().remove(vBoxHeader); //Hey_ HO
        descriptionBodyVBox.getChildren().remove(creditBorderPane);
        showCreditList();
    }

    @FXML
    private void handleSaveEditProductionClick(MouseEvent event) {
        saveCredits();
        System.out.println("saving");
        descriptionTitleVBox.getChildren().remove(editOptionsHBox);
        descriptionTitleVBox.getChildren().add(0, editOptionsHBox);
        editOptionsHBox.getChildren().removeAll(cancelEditProductionButton, saveEditProductionButton, deleteProductionButton);
        editOptionsHBox.getChildren().add(editProductionButton);
        titleAndDescriptionVBox.getChildren().remove(vBoxHeader);
        descriptionBodyVBox.getChildren().remove(creditBorderPane);
        showCreditList();
    }

    @FXML
    private void handleDeleteProductionClick(MouseEvent event) {
        if (systemFacade.getActiveProduction() != null) {
            backgroundAP.getChildren().add(confirmDeletePane);
        }
    }

    @FXML
    private void cancelDelete(MouseEvent event) {
        backgroundAP.getChildren().remove(confirmDeletePane);
    }

    @FXML
    private void proceedDelete(MouseEvent event) {
        backgroundAP.getChildren().remove(confirmDeletePane);
        backgroundAP.getChildren().remove(titleAndDescriptionBP);
        systemFacade.productionLogic.deleteProduction(systemFacade.getActiveProduction().getId());
        updateSidePanel();
        homeButtonAction();
    }

    private void handleSearchMenuHoveringClicked(MouseEvent event) {
        AnchorPane ap = (AnchorPane) event.getSource();
        Label titleText = (Label) ap.getChildren().get(0);
        focusedSearchField.getTextField().setText(titleText.getText());
        if (focusedSearchField.getTextField().getUserData().equals(SearchStatus.productions)) {
            loadSearchElements(ap.getUserData());
        }
    }

    private void handleSearchMenuHoveringEnter(MouseEvent event) {
        AnchorPane ap = (AnchorPane) event.getSource();
        Label titleText = (Label) ap.getChildren().get(0);
        ap.setCursor(Cursor.HAND);
        titleText.setStyle("-fx-text-fill: white; -fx-font-size: " + Info.fontSizeDefault + ";");
        //ap.setStyle("-fx-background-color: rgba(0,0,0,0.20) ; -fx-background-radius: " + Info.roundAmount + "; -fx-border-radius: " + Info.roundAmount + ";");
    }

    private void handleSearchMenuHoveringExit(MouseEvent event) {
        AnchorPane ap = (AnchorPane) event.getSource();
        Label titleText = (Label) ap.getChildren().get(0);
        titleText.setStyle("-fx-text-fill: " + Info.forgroundColor + "; -fx-font-size: " + Info.fontSizeDefault + ";");
        //ap.setStyle("-fx-background-color: transparent;");
    }

    @FXML
    private void deleteButtonHoverHandler(MouseEvent event) {

        StackPane chStck = (StackPane) event.getSource();
        StackPane prStck = (StackPane) chStck.getParent();
        Rectangle rec = (Rectangle) prStck.getChildren().get(0);
        rec.setFill(Paint.valueOf(Info.backgroundShadeColor));

    }

    @FXML
    private void deleteButtonHoverExitHandler(MouseEvent event) {

        StackPane chStck = (StackPane) event.getSource();
        StackPane prStck = (StackPane) chStck.getParent();
        Rectangle rec = (Rectangle) prStck.getChildren().get(0);
        rec.setFill(Paint.valueOf(Info.backgroundColor));

    }

    @FXML
    private void handleTopDragClicked(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
            maximizeButtonAction();
        }
    }

    @FXML
    private void handleTopDragDragged(MouseEvent event) {
        App.stage.setX(event.getScreenX() + App.xOffset);
        App.stage.setY(event.getScreenY() + App.yOffset);
    }

    @FXML
    private void handleTopDragPressed(MouseEvent event) {
        App.xOffset = App.stage.getX() - event.getScreenX();
        App.yOffset = App.stage.getY() - event.getScreenY();
    }

    @FXML
    private void handleButtonHoveringEnter(MouseEvent event) {
        StackPane hoveredObject = ((StackPane) event.getSource());
        hoveredObject.setCursor(Cursor.HAND);
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
    private void minimizeButtonAction() {
        App.stage.setIconified(true);
    }

    @FXML
    private void maximizeButtonAction() {
        if (!App.stage.isMaximized()) {
            App.stage.setMaximized(true);
        } else {
            App.stage.setMaximized(false);
        }
        calculateSearchBarAnchors();
        calculateMyListHeight();
    }

    @FXML
    private void closeButtonAction() {
        Platform.exit();
    }
}

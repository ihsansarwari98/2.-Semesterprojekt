package com.mycompany.creditsystem.presentation;

import com.mycompany.creditsystem.domain.logic.*;

import java.net.URL;
import java.sql.BatchUpdateException;
import java.util.*;

import com.mycompany.creditsystem.persistence.User;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
    private VBox descriptionVBoxRight;
    @FXML
    private VBox descriptionTitleVBox;
    @FXML
    private AnchorPane mineProduktionerProductionCompany;
    @FXML
    private VBox programListProductionCompany;
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
    private Rectangle addProductionRectangle;
    @FXML
    private Rectangle addProductionRectangle1;

    TextField nameTextField;
    TextField usernameTextField1;
    TextField passwordTextField1;
    TextField companyTextField;
    TextField producerTextField;


    private boolean scrollableEdit = false;
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
        addProductionRectangle.setFill(Info.accentGradient);
        addProductionRectangle1.setFill(Info.accentGradient);

        // -- DESCRIPTION
        descriptionScrollPane.getStyleClass().add("dark-thumb");
        descriptionTitleText.setStyle("-fx-text-fill: " + Info.forgroundColor + ";");

        logoText.setFill(Info.accentGradient);
        rectangleLogoSplitter.setFill(Info.accentGradient);
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

            if (lastSearchField == null) {
                lastSearchField = focusedSearchField;
            }

            if (!lastSearchField.getTextField().equals(focusedTextField)) {
                handleDeselect(lastSearchField);
                lastSearchField = focusedSearchField;
            }

        }
        if (focusedSearchField != null) {
            handleSearchFocus();
        }
    }


    String addType = null;

    public String getAddType() {
        return addType;
    }

    public void setAddType(String addType) {
        this.addType = addType;
    }

    // Method is used for adding new Productions
    @FXML
    void addProductionHandler(MouseEvent event) {
        setAddType("production");
        addNewHandler();
        descriptionTitleText.setText("ADD PRODUCTION");
    }


    void addProductionCompanyHandler() {
        setAddType("company");
        addNewHandler();
        descriptionTitleText.setText("ADD COMPANY");
    }

    void addProducerHandler() {
        setAddType("producer");
        addNewHandler();
        descriptionTitleText.setText("ADD PRODUCER");
    }

    void addAdministratorHandler() {
        setAddType("administrator");
        addNewHandler();
        descriptionTitleText.setText("ADD ADMINISTRATOR");
    }


    // Sets up UI for adding a new entity.
    void addNewHandler() {

        // TODO Lav Labels over TextFields
        // Removes unnecessary elements
        backgroundAP.getChildren().remove(searchBarBP);
        backgroundAP.getChildren().remove(logoVBox);
        backgroundAP.getChildren().add(titleAndDescriptionBP);

        // Setting up layout for formula
        int searchFieldLength = 300;
        descriptionVBox.getChildren().clear();
        descriptionVBox.setSpacing(10);
        descriptionVBox.setFillWidth(false);
        descriptionVBox.setAlignment(Pos.TOP_CENTER);

        descriptionHBoxHeader.setFillHeight(false);
        descriptionHBoxHeader.setPrefWidth(searchFieldLength);


        Label nameLabel = new Label();
        nameLabel.setText("NAME");
        nameLabel.setTextFill(Info.accentGradient);
        nameLabel.setStyle("-fx-font-weight: bold");
        Label companyLabel = new Label();
        companyLabel.setText("COMPANY");
        companyLabel.setTextFill(Info.accentGradient);
        companyLabel.setStyle("-fx-font-weight: bold");
        Label usernameLabel = new Label();
        usernameLabel.setText("USERNAME");
        usernameLabel.setTextFill(Info.accentGradient);
        usernameLabel.setStyle("-fx-font-weight: bold");
        Label passwordLabel = new Label();
        passwordLabel.setText("PASSWORD");
        passwordLabel.setTextFill(Info.accentGradient);
        passwordLabel.setStyle("-fx-font-weight: bold");
        Label producerLabel = new Label();
        producerLabel.setText("RESPONSIBLE PRODUCER");
        producerLabel.setTextFill(Info.accentGradient);
        producerLabel.setStyle("-fx-font-weight: bold");


        // Setting up HBOXes to create form.
        HBox hBox1 = new HBox();
        hBox1.setSpacing(15);
        nameTextField = new TextField();
        nameTextField.setPromptText("Name");
        companyTextField = new TextField();
        companyTextField.setPromptText("Company Name");
        VBox nameVBox = new VBox();
        nameVBox.setAlignment(Pos.CENTER);
        VBox companyVBox = new VBox();
        companyVBox.setAlignment(Pos.CENTER);
        nameVBox.getChildren().add(nameLabel);
        companyVBox.getChildren().add(companyLabel);
        hBox1.getChildren().addAll(nameVBox, companyVBox);

        HBox hBox2 = new HBox();
        hBox2.setSpacing(15);
        usernameTextField1 = new TextField();
        usernameTextField1.setPromptText("Username");
        passwordTextField1 = new TextField();
        passwordTextField1.setPromptText("Password");
        VBox usernameVBox = new VBox();
        usernameVBox.setAlignment(Pos.CENTER);
        VBox passwordVBox = new VBox();
        passwordVBox.setAlignment(Pos.CENTER);
        usernameVBox.getChildren().add(usernameLabel);
        passwordVBox.getChildren().add(passwordLabel);
        hBox2.getChildren().addAll(usernameVBox, passwordVBox);

        HBox hBox3 = new HBox();
        producerTextField = new TextField();
        producerTextField.setPromptText("Producer");
        VBox producerVBox = new VBox();
        producerVBox.setAlignment(Pos.CENTER);
        producerVBox.getChildren().add(producerLabel);
        hBox3.getChildren().add(producerVBox);

        // Button hBox
        HBox hBox4 = new HBox();
        StackPane buttonStackPane = new StackPane();
        hBox4.getChildren().add(buttonStackPane);
        Rectangle buttonRectangle = new Rectangle();
        buttonRectangle.setArcHeight(40);
        buttonRectangle.setArcWidth(40);
        buttonRectangle.setHeight(35);
        buttonRectangle.setWidth(100);
        buttonRectangle.setFill(Info.accentGradient);
        Label buttonLabel = new Label("SUBMIT");
        buttonLabel.setTextFill(Color.WHITE);
        buttonStackPane.getChildren().addAll(buttonRectangle, buttonLabel);
        // Action method for submit button
        buttonStackPane.setCursor(Cursor.HAND);

        // Adding SearchField to function as text field, with proper styling.
        SearchField nameField = createSearchField(SearchStatus.notSearchable,
                "",
                300,
                NodeOrientation.LEFT_TO_RIGHT);
        nameVBox.getChildren().add(nameField.getStackPane());
        styleSearchResults(nameField);
        nameField.getTextField().setPromptText("Name");

        SearchField companyField = createSearchField(SearchStatus.productionCompanies,
                "",
                300,
                NodeOrientation.LEFT_TO_RIGHT);
        companyVBox.getChildren().add(companyField.getStackPane());
        styleSearchResults(companyField);
        companyField.getTextField().setPromptText("Associated company name");
        if (systemFacade.currentUser.getUser().getAccessRole() != User.AccessRole.admin) {
            companyField.getTextField().setText(systemFacade.currentUser.getUser().getName());
            companyField.getTextField().setEditable(false);
            companyField.getTextField().setCursor(Cursor.DEFAULT);
            companyLabel.setText("YOUR COMPANY");
        }

        SearchField usernameField = createSearchField(SearchStatus.notSearchable,
                "",
                300,
                NodeOrientation.LEFT_TO_RIGHT);
        usernameVBox.getChildren().add(usernameField.getStackPane());
        styleSearchResults(usernameField);
        usernameField.getTextField().setPromptText("Username");

        SearchField passwordField = createSearchField(SearchStatus.notSearchable,
                "",
                300,
                NodeOrientation.LEFT_TO_RIGHT);
        passwordVBox.getChildren().add(passwordField.getStackPane());
        styleSearchResults(passwordField);
        passwordField.getTextField().setPromptText("Password");

        SearchField producerField = createSearchField(SearchStatus.producers,
                "",
                300,
                NodeOrientation.LEFT_TO_RIGHT);
        producerVBox.getChildren().add(producerField.getStackPane());
        styleSearchResults(producerField);
        producerField.getTextField().setPromptText("Associated producer ID");

        // Creating method for getting TextField and submitting to Database
        buttonStackPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String addType = getAddType();
                int userID = 0;
                switch (addType) {
                    // TODO KODE BODY FOR METODER
                    case "production":
                        String currentName = nameField.getTextField().getText();
                        systemFacade.productionLogic.createProduction(currentName);

                        if (!producerField.getTextField().getText().isBlank()) {
                            //Links production to responsible producer
                            userID = systemFacade.userLogic.getIDFromName(producerField.getTextField().getText());
                            systemFacade.productionLogic.linkProductionToUser(systemFacade.productionLogic.getProduction(currentName).getId(), userID);
                            System.out.println(userID + " has been linked to production: " + currentName);

                            //Links production-Company to responsible producer
                            userID = systemFacade.userLogic.getIDFromName(companyField.getTextField().getText());
                            systemFacade.productionLogic.linkProductionToUser(systemFacade.productionLogic.getProduction(currentName).getId(), userID);
                            System.out.println(userID + " has been linked to production: " + currentName);
                        }

                        System.out.println(userID + " is the ID of Responsible Producer");
                        System.out.println(nameField.getTextField().getText() + " has been added to the system!");

                        break;
                    case "company":

                        break;
                    case "producer":

                        break;
                    default:
                        System.out.println("Something went wrong...");
                        break;
                }
            }
        });

        // Switch statement to add proper HBoxes depending on addType.
        switch (addType) {
            case "production":
                descriptionVBox.getChildren().addAll(hBox1, hBox3, hBox4);
                break;
            case "administrator":
            case "company":
            case "producer":
                descriptionVBox.getChildren().addAll(hBox1, hBox2, hBox4);
                break;
            default:
                System.out.println("Something went wrong...");
                break;

        }
    }

    @FXML
    private void handleLogin() {
        if (systemFacade.userLogic.userLogin(usernameTextField.getText(), passwordTextField.getText())) {
            enableElements(systemFacade.currentUser.getUser().getAccessRoleInt());

            // Sets myProductions
            systemFacade.currentUser.setMyProductions(systemFacade.productionLogic.getProductionsLinkedToUser(systemFacade.currentUser.getUser().getId()));

            setNameAndRole();
            sidePanelBackground.getChildren().remove(loginAP);
            sidePanelBackground.getChildren().add(logoutAP);
            handleDeselect();
            updateProductionList();
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
            editOptionsHBox.getChildren().removeAll(cancelEditProductionButton, saveEditProductionButton, editProductionButton);
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
                sidePanelBackground.getChildren().add(mineProduktioner);
                checkCanEdit();
                break;
            case 2:
                nameAndRoleAP.getChildren().add(nameAndRole);
                nameAndRoleAP.getChildren().add(sortingBorderPane);
                sidePanelBackground.getChildren().add(mineProduktionerProductionCompany);
                checkCanEdit();
                break;
            case 3:
                nameAndRoleAP.getChildren().add(nameAndRole);
                nameAndRoleAP.getChildren().add(sortingBorderPane);
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

        SearchField creditSearchField = createSearchField(SearchStatus.credits,
                "",
                300,
                NodeOrientation.RIGHT_TO_LEFT);
        hBox.getChildren().add(creditSearchField.getStackPane());
        styleSearchResults(creditSearchField);

        hBox.getChildren().add(createRemoveCreditElement());

        SearchField roleSearchField = createSearchField(SearchStatus.roles,
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

    // Holds the last updated text in the search bar
    private String tempUserSearch = "";
    private boolean selectBlank = true;

    // Updates and displays the search results
    private void updateSearchResultList(SearchField searchField) {
        String userSearch = searchField.getTextField().getText().toLowerCase();
        // Only updates when a change is made in the textField
        if (!userSearch.equals(tempUserSearch)) {
            tempUserSearch = userSearch;
            if (!userSearch.isBlank()) {
                searchField.getvBoxResults().getChildren().clear();
                for (int i = 0; i < searchField.getSearchResults().size(); i++) {

                    AnchorPane ap = new AnchorPane();
                    Label titleText = new Label();

                    // displays the searchResult
                    Object searchResult = searchField.getSearchResults().get(i);
                    titleText.setText(searchResult.toString());

                    searchField.getvBoxResults().getChildren().add(ap);
                    ap.getChildren().add(titleText);
                    titleText.setAlignment(Pos.CENTER_RIGHT);
                }

                if (searchField.getvBoxResults().getChildren().size() == 0) {
                    searchField.getRectangle().setHeight(searchField.getAnchorPaneBackground().getHeight());
                }

                styleSearchResults(searchField);

            } else {
                // triggers if the search bar becomes empty while the using is typing
                if (searchField.getTextField().getUserData().equals(SearchStatus.productions) && systemFacade.currentUser.getSearchHistory().size() > 0) {
                    displaySearchHistory();
                    styleSearchResults(searchField);
                } else {
                    searchField.getvBoxResults().getChildren().clear();
                    searchField.getRectangle().setHeight(searchField.getAnchorPaneBackground().getHeight());
                }
            }
        } else if (userSearch.isBlank()) {
            // triggers if the search bar is empty and just focused
            if (selectBlank) {
                selectBlank = false;
                if (searchField.getTextField().getUserData().equals(SearchStatus.productions)) {
                    displaySearchHistory();
                    styleSearchResults(searchField);
                }
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
            titleText.setFocusTraversable(true);

            if (searchField.getTextField().getUserData().equals(SearchStatus.productions)) {
                titleText.setPadding(new Insets(0, 0, 0, mainSearchResultTextPadding));
            } else if (searchField.getTextField().getUserData().equals(SearchStatus.credits)) {
                AnchorPane.setRightAnchor(titleText, (double) 0);
                titleText.setPadding(new Insets(0, searchResultTextPadding, 0, 0));
            } else if (searchField.getTextField().getUserData().equals(SearchStatus.roles)) {
                AnchorPane.setLeftAnchor(titleText, (double) 0);
                titleText.setPadding(new Insets(0, 0, 0, searchResultTextPadding));
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

    // Makes the search text white when focused and clicking ENTER searches the focused text
    private void handleSearchFocus() {
        if (!focusedSearchField.getTextField().getUserData().equals(SearchStatus.notSearchable)) {
            ArrayList search = new ArrayList();

            if (focusedSearchField.getTextField().getUserData().equals(SearchStatus.productions)) {
                search = systemFacade.productionLogic.getProductions(focusedTextField.getText());
            } else if (focusedSearchField.getTextField().getUserData().equals(SearchStatus.credits)) {
                search = systemFacade.creditLogic.getCredits(focusedTextField.getText());
            } else if (focusedSearchField.getTextField().getUserData().equals(SearchStatus.roles)) {
                search = systemFacade.roleLogic.getRoles(focusedTextField.getText());
            } else if (focusedSearchField.getTextField().getUserData().equals(SearchStatus.producers)) {
                search = systemFacade.userLogic.getUsersFromAccessRole(1);
            } else if (focusedSearchField.getTextField().getUserData().equals(SearchStatus.productionCompanies)) {
                search = systemFacade.userLogic.getUsersFromAccessRole(2);
            } else {
                System.out.println("SearchStatus error.");
            }

            focusedSearchField.setSearchResults(search);
            updateSearchResultList(focusedSearchField);

            for (int i = 0; i < focusedSearchField.getvBoxResults().getChildren().size(); i++) {
                AnchorPane ap = (AnchorPane) focusedSearchField.getvBoxResults().getChildren().get(i);
                Label titleText = (Label) ap.getChildren().get(0);

                if (titleText.isHover()) {
                    titleText.setStyle("-fx-text-fill: white; -fx-font-size: " + Info.fontSizeDefault + ";");
                } else {
                    if (titleText.isFocused()) {
                        titleText.setOnKeyPressed(e -> {
                            if (e.getCode() == KeyCode.ENTER) {
                                focusedSearchField.getTextField().setText(titleText.getText());
                                handleSearch(focusedSearchField);
                                getFocusedSearchField();
                            }
                        });
                        titleText.setStyle("-fx-text-fill: white; -fx-font-size: " + Info.fontSizeDefault + ";"); // same as hover
                    } else {
                        titleText.setStyle("-fx-text-fill: " + Info.forgroundColor + "; -fx-font-size: " + Info.fontSizeDefault + ";");
                    }
                }
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
        handleSearch(productionSearchField);
    }

    private void handleSearch(SearchField searchField) { // TODO: REWORK EVERYTHING

        if (searchField != null && !searchField.getTextField().getText().isBlank()) {
            if (searchField.getTextField().getUserData().equals(SearchStatus.productions)) {
                if (!searchField.getTextField().getText().isBlank() && systemFacade.productionLogic.getProduction(searchField.getTextField().getText()) != null) {
                    // if active production is null (home screen) -> load elements
                    if (systemFacade.getActiveProduction() == null) {
                        loadTitleAndDescriptionElements();
                    }
                    // set the active production to be the current production
                    systemFacade.setActiveProduction(systemFacade.productionLogic.getProduction(searchField.getTextField().getText()));
                    // add title to title text element
                    descriptionTitleText.setText(systemFacade.productionLogic.getProduction(searchField.getTextField().getText()).getTitle());
                    // add production to search history
                    systemFacade.currentUser.addToSearchHistory(systemFacade.productionLogic.getProduction(searchField.getTextField().getText()));
                    showCreditList();
                    calculateSearchBarAnchors();
                    checkCanEdit();

                } else {
                    System.out.println("Production doesn't exist in the database");
                }
            }

            textFieldSearchBar.clear();
            handleDeselect();
            updateProperties();
        }
    }

    private SearchField createSearchField(SearchStatus userData, String textFieldText, int width, NodeOrientation orientation) {
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
        //searchField.getAnchorPaneBackground().setNodeOrientation(orientation);

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
                SearchField creditSearchField = createSearchField(SearchStatus.credits,
                        creditWithRoleObject.getKey().toString(),
                        searchFieldLength,
                        NodeOrientation.RIGHT_TO_LEFT);
                hBox.getChildren().add(creditSearchField.getStackPane());
                styleSearchResults(creditSearchField);

                // add delete-credit element
                hBox.getChildren().add(createRemoveCreditElement());

                // Create credit role searchField
                SearchField roleSearchField = createSearchField(SearchStatus.roles,
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

    private void updateProductionList() {
        programList.getChildren().clear();
        programListProductionCompany.getChildren().clear();
        for (int i = 0; i < systemFacade.currentUser.getMyProductions().size(); i++) {

            HBox hb = new HBox();
            Circle circle = new Circle(4);
            AnchorPane ap = new AnchorPane();
            VBox vb = new VBox();
            Label title = new Label(systemFacade.currentUser.getMyProductions().get(i).getTitle());
            Label deadline = new Label(systemFacade.currentUser.getMyProductions().get(i).getDeadlineString());

            if (systemFacade.currentUser.getUser().getAccessRoleInt() == 1) {
                programList.getChildren().add(hb);
            } else if (systemFacade.currentUser.getUser().getAccessRoleInt() == 2) {
                programListProductionCompany.getChildren().add(hb);
            }

            hb.getChildren().addAll(circle, ap);
            ap.getChildren().add(vb);
            vb.getChildren().addAll(title, deadline);
            title.setWrapText(true);
            ap.setUserData(SearchStatus.productions);
            HBox.setHgrow(ap, Priority.ALWAYS);

            hb.setSpacing(25);
            hb.setAlignment(Pos.CENTER_LEFT);
            ap.setCursor(Cursor.HAND);

            if (systemFacade.currentUser.getMyProductions().get(i).getStatusInt() == 0) {
                circle.setFill(Paint.valueOf(Info.statusRed)); // DATABASE
            } else if (systemFacade.currentUser.getMyProductions().get(i).getStatusInt() == 1) {
                circle.setFill(Paint.valueOf(Info.statusYellow)); // DATABASE
            } else {
                circle.setFill(Paint.valueOf(Info.statusGreen)); // DATABASE
            }

            ap.setOnMouseClicked(e -> {
                AnchorPane source = (AnchorPane) e.getSource();
                VBox vBox = (VBox) source.getChildren().get(0);
                Label productionTitle = (Label) vBox.getChildren().get(0);
                if (source.getUserData().equals(SearchStatus.productions)) {
                    getFocusedSearchField();
                    focusedSearchField = new SearchField(searchBarStackPane, searchRectangleBG, searchBarVBox, searchBarBackground, searchBarHBox, textFieldSearchBar, searchResultScrollPane, searchResults);
                    focusedSearchField.setSearchResults(systemFacade.productionLogic.getProductions(focusedTextField.getText()));
                    focusedSearchField.getTextField().setText(systemFacade.productionLogic.getProduction(productionTitle.getText()).getTitle());
                    handleSearch(focusedSearchField);

                }
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
        if (systemFacade.currentUser.sortMyProductionsByName()) {
            topSortingLabel.setText("A");
            bottomSortingLabel.setText("Z");
        } else {
            topSortingLabel.setText("Z");
            bottomSortingLabel.setText("A");
        }
        updateProductionList();
    }

    @FXML
    private void sortByDeadline() {
        systemFacade.currentUser.sortMyProductionsByDeadline();
        updateProductionList();
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

    private void loadTitleAndDescriptionElements() {
        backgroundAP.getChildren().remove(searchBarBP);
        backgroundAP.getChildren().add(2, titleAndDescriptionBP);
        descriptionVBox.setAlignment(Pos.TOP_CENTER);
        descriptionTitleText.applyCss();
        backgroundAP.getChildren().add(3, searchBarBP);
        searchBarBP.getChildren().remove(logoVBox);
    }

    @FXML
    private void homeButtonAction() {
        systemFacade.setActiveProduction(null);
        backgroundAP.getChildren().remove(titleAndDescriptionBP);
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
        editOptionsHBox.getChildren().addAll(cancelEditProductionButton, saveEditProductionButton);
        titleAndDescriptionVBox.getChildren().add(1, vBoxHeader);
        descriptionBodyVBox.getChildren().add(creditBorderPane);
        editProduction();
    }

    @FXML
    private void handleCancelEditProductionClick(MouseEvent event) {
        System.out.println("canceled edit");
        descriptionTitleVBox.getChildren().remove(editOptionsHBox);
        descriptionTitleVBox.getChildren().add(0, editOptionsHBox);
        editOptionsHBox.getChildren().removeAll(cancelEditProductionButton, saveEditProductionButton);
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
        editOptionsHBox.getChildren().removeAll(cancelEditProductionButton, saveEditProductionButton);
        editOptionsHBox.getChildren().add(editProductionButton);
        titleAndDescriptionVBox.getChildren().remove(vBoxHeader); //Hey_ho
        descriptionBodyVBox.getChildren().remove(creditBorderPane);
        showCreditList();
    }

    @FXML
    private void handleSearchMenuHoveringClicked(MouseEvent event) {
        AnchorPane ap = ((AnchorPane) event.getSource());
        Label titleText = (Label) ap.getChildren().get(0);
        focusedSearchField.getTextField().setText(titleText.getText());
        handleSearch(focusedSearchField);
    }

    @FXML
    private void handleSearchMenuHoveringEnter(MouseEvent event) {
        AnchorPane ap = ((AnchorPane) event.getSource());
        Label titleText = (Label) ap.getChildren().get(0);
        ap.setCursor(Cursor.HAND);
        titleText.setStyle("-fx-text-fill: white; -fx-font-size: " + Info.fontSizeDefault + ";");
        //ap.setStyle("-fx-background-color: rgba(0,0,0,0.20) ; -fx-background-radius: " + Info.roundAmount + "; -fx-border-radius: " + Info.roundAmount + ";");
        // TODO: Få ap til at blive inden for "searchRectangleBG"
    }

    @FXML
    private void handleSearchMenuHoveringExit(MouseEvent event) {
        AnchorPane ap = ((AnchorPane) event.getSource());
        Label titleText = (Label) ap.getChildren().get(0);
        titleText.setStyle("-fx-text-fill: " + Info.forgroundColor + "; -fx-font-size: " + Info.fontSizeDefault + ";");
        //ap.setStyle("-fx-background-color: transparent;");

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
    }

    @FXML
    private void closeButtonAction() {
        Platform.exit();
    }
}

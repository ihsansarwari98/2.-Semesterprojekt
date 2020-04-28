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
    private HBox descriptionHBox;

    // idk what im doing
    private SystemFacade systemFacade = new SystemFacade();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateProperties();
        enableElements(User.AccessRole.publicUser);
        checkCanEdit();
        homeButtonAction();
        Info.sidePanelOn = true;
        sidePanelAction();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), (ActionEvent event) -> {
            // this code will be called every second
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

        closeRectangle1.setFill(Info.accentGradient);
        closeRectangle2.setFill(Info.accentGradient);
        maximizeRectangle.setFill(Info.accentGradient);
        minimizeRectangle.setFill(Info.accentGradient);
        homeText.setFill(Info.accentGradient);
        editProductionRectangle.setFill(Info.accentGradient);
        cancelEditProductionRectangle.setFill(Info.accentGradient);
        saveEditProductionRectangle.setFill(Info.accentGradient);
//        editProductionText.setText("Rediger Produktion");
//        cancelEditProductionText.setText("Fortryd");
//        saveEditProductionText.setText("Gem");

        // -- SEARCH BAR
        // Set the color and round the corners of the search bar 
        searchBarBackground.setStyle("-fx-background-radius: " + Info.roundAmount + "; -fx-border-radius: " + Info.roundAmount + "; -fx-background-color: " + Info.forgroundColor + ";");
        searchRectangleBG.setFill(Info.accentGradient);

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

        // -- DESCRIPTION
        descriptionScrollPane.getStyleClass().add("dark-thumb");
        descriptionTitleText.setStyle("-fx-text-fill: " + Info.forgroundColor + ";");

        logoText.setFill(Info.accentGradient);
        rectangleLogoSplitter.setFill(Info.accentGradient);
    }

    // Is getting called many times a second
    private void updateEverySecond() {
        if (textFieldSearchBar.isFocused()) {
            updateSearchResultList(searchResults, textFieldSearchBar);
        }

        handleSearchFocus(searchResults, textFieldSearchBar);
    }

    @FXML
    private void handleLogin() {
        if (systemFacade.userLogic.userLogin(usernameTextField.getText(), passwordTextField.getText())) {
            enableElements(systemFacade.currentUser.getUser().getAccessRole());

            // Sets myProductions
            if (systemFacade.currentUser.getUser().getAccessRole() == User.AccessRole.producer) {
                systemFacade.currentUser.setMyProductions(systemFacade.productionLogic.getProductionsLinkedToProducer(systemFacade.currentUser.getUser().getId()));

            } else if (systemFacade.currentUser.getUser().getAccessRole() == User.AccessRole.productionCompany) {
                systemFacade.currentUser.setMyProductions(systemFacade.productionLogic.getProductionsLinkedToProductionCompany(systemFacade.currentUser.getUser().getId()));
            }

            setNameAndRole();
            sidePanelBackground.getChildren().remove(loginAP);
            sidePanelBackground.getChildren().add(logoutAP);
            handleDeselect();
            updateProductionList();

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

    private boolean checkCanEdit() {
        // Checks if the active production isn't the home screen and the User is logged in
        // As well as if the User has access to the active production
        if (systemFacade.getActiveProduction() != null &
                systemFacade.currentUser.getUser() != null &&
                ((systemFacade.currentUser.getUser().getAccessRole().equals(User.AccessRole.producer) &&
                        systemFacade.productionLogic.isProductionLinkedToProducer(systemFacade.getActiveProduction().getId(), systemFacade.currentUser.getUser().getId())) ||
                        (systemFacade.currentUser.getUser().getAccessRole().equals(User.AccessRole.productionCompany) &&
                                systemFacade.productionLogic.isProductionLinkedToProductionCompany(systemFacade.getActiveProduction().getId(), systemFacade.currentUser.getUser().getId())) ||
                        (systemFacade.currentUser.getUser().getAccessRole().equals(User.AccessRole.admin)))) {
            loadEditElement(true);
            return true;
        } else {
            loadEditElement(false);
            return false;
        }
    }

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

    private void enableElements(User.AccessRole accessRole) {
        switch (accessRole) {
            case publicUser:
                sidePanelBackground.getChildren().clear();
                sidePanelBackground.getChildren().add(nameAndRoleAP);
                sidePanelBackground.getChildren().add(loginAP);

                nameAndRoleAP.getChildren().remove(nameAndRole);
                nameAndRoleAP.getChildren().remove(sortingBorderPane);

                usernameTextField.clear();
                passwordTextField.clear();

                checkCanEdit();
                break;
            case producer:
                nameAndRoleAP.getChildren().add(nameAndRole);
                nameAndRoleAP.getChildren().add(sortingBorderPane);
                sidePanelBackground.getChildren().add(mineProduktioner);
                checkCanEdit();
                break;
            case productionCompany:
                nameAndRoleAP.getChildren().add(nameAndRole);
                nameAndRoleAP.getChildren().add(sortingBorderPane);
                sidePanelBackground.getChildren().add(mineProduktionerProductionCompany);
                checkCanEdit();
                break;
            case admin:
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
        enableElements(User.AccessRole.publicUser);
    }

    // Shows the list of credits connected to a production
    private void showCreditList(Production production) {
        descriptionHBox.getChildren().remove(descriptionVBoxRight);
        descriptionVBox.getChildren().clear();
        descriptionHBox.setSpacing(0);
        descriptionVBox.setAlignment(Pos.TOP_CENTER);
        for (int i = 0; i < systemFacade.creditLogic.getCredits(production.getId()).size(); i++) {

            // gets the role of the credit
            Text roleText = new Text(systemFacade.roleHandler.getRoleFromCredit(production.getId(), systemFacade.creditLogic.getCredits(production.getId()).get(i).getId()).getTitle());
            Label name = new Label(systemFacade.creditLogic.getCredits(production.getId()).get(i).getName());
            VBox vb = new VBox();

            roleText.setFill(Info.accentGradient);
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

                    if (systemFacade.roleHandler.getRoleFromCredit(production.getId(), systemFacade.creditLogic.getCredits(production.getId()).get(i).getId()).getTitle().equals(role.getText())) {
                        vbox.getChildren().add(name);
                        foundRole = true;
                    }
                }
                if (!foundRole) {
                    descriptionVBox.getChildren().add(vb);
                    vb.getChildren().add(roleText);
                    vb.getChildren().add(name);
                }
            }
        }
    }

    // Holds the last updated text in the search bar
    private String tempUserSearch = "";
    private boolean selectBlank = true;

    // Updates and displays the search results
    private void updateSearchResultList(VBox vbox, TextField textField) {
        String userSearch = textField.getText().toLowerCase();







        // TODO: : : : : : : : : : : : : Make methods more flexible/dynamic










        // TODO: Fix select/deselect and writing visibility
        // Only updates when a change is made in the textField
        if (!userSearch.equals(tempUserSearch)) {
            tempUserSearch = userSearch;
            if (!userSearch.isBlank()) {
                vbox.getChildren().clear();
                for (int i = 0; i < systemFacade.productionLogic.getProductions(userSearch).size(); i++) {
                    // if the text written in the search bar is equal to any result in the production list // DATABASE
                    AnchorPane ap = new AnchorPane();
                    Label titleText = new Label(systemFacade.productionLogic.getProductions(userSearch).get(i).getTitle());

                    vbox.getChildren().add(ap);
                    ap.getChildren().addAll(titleText);
                }

                if (vbox.getChildren().size() == 0) {
                    searchRectangleBG.setHeight(searchBarBackground.getHeight());
                }

                styleSearchResults(vbox);

            } else {
                // triggers if the search bar becomes empty while the using is typing
                if (systemFacade.currentUser.getSearchHistory().size() > 0) {
                    displaySearchHistory();
                    styleSearchResults(vbox);
                } else {
                    vbox.getChildren().clear();
                    searchRectangleBG.setHeight(searchBarBackground.getHeight());
                }
            }
        } else if (userSearch.isBlank()) {
            // triggers if the search bar is empty and just focused
            if (selectBlank) {
                selectBlank = false;
                displaySearchHistory();
                styleSearchResults(vbox);
            }
        }
    }

    private void styleSearchResults(VBox vbox) {
        int searchResultSpacing = 10;
        int searchResultTextPadding = 50;

        for (int i = 0; i < vbox.getChildren().size(); i++) {
            AnchorPane ap = (AnchorPane) vbox.getChildren().get(i);
            Label titleText = (Label) ap.getChildren().get(0);

            vbox.setAlignment(Pos.TOP_LEFT);
            vbox.setSpacing(searchResultSpacing);
            ap.setCursor(Cursor.HAND);

            titleText.setPadding(new Insets(0, 0, 0, searchResultTextPadding));
            titleText.setStyle("-fx-text-fill: " + Info.fontColor2 + "; -fx-font-size: " + Info.fontSizeDefault + ";");
            titleText.setAlignment(Pos.CENTER_LEFT);
            titleText.setFocusTraversable(true);

            ap.setOnMouseEntered(this::handleSearchMenuHoveringEnter);
            ap.setOnMouseExited(this::handleSearchMenuHoveringExit);
            ap.setOnMouseClicked(this::handleSearchMenuHoveringClicked);

            titleText.applyCss();
            calculateSearchResultsHeight(vbox, titleText);
        }
    }

    // Calculates and sets the height of the search result tab
    private void calculateSearchResultsHeight(VBox vbox, Label titleText) {
        int searchResultSize = vbox.getChildren().size();

        if (searchResultSize == 0) {
            searchRectangleBG.setHeight(searchBarBackground.prefHeight(-1)); // no results
        } else if (searchResultSize < Info.visibleResults) { // Maximum amount of search results shown
            searchRectangleBG.setHeight(searchBarBackground.prefHeight(-1) + (titleText.prefHeight(-1) + vbox.getSpacing()) * (searchResultSize));
        } else {
            searchRectangleBG.setHeight(searchBarBackground.prefHeight(-1) + (titleText.prefHeight(-1) + vbox.getSpacing()) * (Info.visibleResults));
        }
    }

    // Makes the search text white when focused and clicking ENTER searches the focused text
    private void handleSearchFocus(VBox vbox, TextField textField) {
        for (int i = 0; i < vbox.getChildren().size(); i++) {
            AnchorPane ap = (AnchorPane) vbox.getChildren().get(i);
            Label titleText = (Label) ap.getChildren().get(0);
            if (titleText.isHover()) {
                titleText.setStyle("-fx-text-fill: white; -fx-font-size: " + Info.fontSizeDefault + ";");
            } else {
                if (titleText.isFocused()) {
                    titleText.setOnKeyPressed(e -> {
                        if (e.getCode() == KeyCode.ENTER) {
                            textField.setText(titleText.getText());
                            handleSearch();
                        }
                    });
                    titleText.setStyle("-fx-text-fill: white; -fx-font-size: " + Info.fontSizeDefault + ";"); // same as hover
                } else {
                    titleText.setStyle("-fx-text-fill: " + Info.forgroundColor + "; -fx-font-size: " + Info.fontSizeDefault + ";");
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
    // TODO
    @FXML
    private void handleSearch() { // TODO: REWORK EVERYTHING
        if (!textFieldSearchBar.getText().isBlank() && systemFacade.productionLogic.getProduction(textFieldSearchBar.getText()) != null) {
            // if active production is null (home screen) -> load elements
            if (systemFacade.getActiveProduction() == null) {
                loadTitleAndDescriptionElements();
            }
            // set the active production to be the current production
            systemFacade.setActiveProduction(systemFacade.productionLogic.getProduction(textFieldSearchBar.getText()));
            // add title to title text element
            descriptionTitleText.setText(systemFacade.productionLogic.getProduction(textFieldSearchBar.getText()).getTitle());
            // add production to search history
            systemFacade.currentUser.addToSearchHistory(systemFacade.productionLogic.getProduction(textFieldSearchBar.getText()));
            showCreditList(systemFacade.productionLogic.getProduction(textFieldSearchBar.getText()));
            calculateSearchBarAnchors();
            checkCanEdit();

        } else {
            System.out.println("Production doesn't exist in the database");
        }

        textFieldSearchBar.clear();
        handleDeselect();
        updateProperties();
    }

    private void editProduction() {
        if (systemFacade.getActiveProduction() != null) {
            descriptionHBox.getChildren().add(descriptionVBoxRight);
            descriptionHBox.setSpacing(10);
            descriptionVBox.getChildren().clear();
            descriptionVBoxRight.getChildren().clear();

            VBox creditNameVBox = new VBox();
            VBox creditRoleVBox = new VBox();
            VBox descriptionVBoxNameCaption = new VBox();
            VBox descriptionVBoxRoleCaption = new VBox();

            Text nameCaption = new Text("Credit name");
            Text roleCaption = new Text("Credit role");

            nameCaption.setStyle("-fx-font-weight: bold; -fx-font-size: " + Info.fontSizeDefault + ";");
            roleCaption.setStyle("-fx-font-weight: bold; -fx-font-size: " + Info.fontSizeDefault + ";");
            nameCaption.setFill(Info.accentGradient);
            roleCaption.setFill(Info.accentGradient);

            descriptionVBox.getChildren().addAll(descriptionVBoxNameCaption, creditNameVBox);
            descriptionVBoxRight.getChildren().addAll(descriptionVBoxRoleCaption, creditRoleVBox);

            descriptionVBoxNameCaption.getChildren().add(nameCaption);
            descriptionVBoxRoleCaption.getChildren().add(roleCaption);

            descriptionVBoxNameCaption.setAlignment(Pos.TOP_RIGHT);
            descriptionVBoxRoleCaption.setAlignment(Pos.TOP_LEFT);

            for (int i = 0; i < systemFacade.creditLogic.getCredits(systemFacade.getActiveProduction().getId()).size(); i++) {

                // gets the role of the credit
                TextField roleText = new TextField(systemFacade.roleHandler.getRoleFromCredit(systemFacade.getActiveProduction().getId(), systemFacade.creditLogic.getCredits(systemFacade.getActiveProduction().getId()).get(i).getId()).getTitle());
                TextField name = new TextField(systemFacade.creditLogic.getCredits(systemFacade.getActiveProduction().getId()).get(i).getName());

                creditNameVBox.getChildren().add(name);
                creditRoleVBox.getChildren().add(roleText);

                creditNameVBox.setAlignment(Pos.TOP_RIGHT);
                creditRoleVBox.setAlignment(Pos.TOP_LEFT);

                name.setAlignment(Pos.TOP_RIGHT);
                roleText.setAlignment(Pos.TOP_LEFT);

                roleText.setStyle("-fx-font-size: " + Info.fontSizeDefault + "; -fx-text-fill: " + Info.forgroundColor + ";");
                name.setStyle("-fx-font-size: " + Info.fontSizeDefault + "; -fx-text-fill: " + Info.forgroundColor + ";");
            }
        }
    }

    private void updateProductionList() {
        programList.getChildren().clear();
        programListProductionCompany.getChildren().clear();
        for (Production production : systemFacade.currentUser.getMyProductions()) {
            HBox hb = new HBox();
            Circle circle = new Circle(4);
            AnchorPane ap = new AnchorPane();
            VBox vb = new VBox();
            Label title = new Label(production.getTitle());
            Label deadline = new Label(production.getDeadlineString());

            if (systemFacade.currentUser.getUser().getAccessRole() == User.AccessRole.producer) {
                programList.getChildren().add(hb);
            } else if (systemFacade.currentUser.getUser().getAccessRole() == User.AccessRole.productionCompany) {
                programListProductionCompany.getChildren().add(hb);
            }

            hb.getChildren().addAll(circle, ap);
            ap.getChildren().add(vb);
            vb.getChildren().addAll(title, deadline);
            title.setWrapText(true);
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

    // Handles when the user clicks on the background to deselect what was previously focused
    @FXML
    private void handleDeselect() {
        backgroundAP.requestFocus();
        searchRectangleBG.setHeight(searchBarBackground.getHeight());
        searchResults.getChildren().clear();
        selectBlank = true;
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

    public void calculateSearchBarAnchors() {
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
        System.out.println(systemFacade.getActiveProduction().getTitle());
        descriptionTitleVBox.getChildren().remove(editOptionsHBox);
        descriptionTitleVBox.getChildren().add(0, editOptionsHBox);
        editOptionsHBox.getChildren().remove(editProductionButton);
        editOptionsHBox.getChildren().addAll(cancelEditProductionButton, saveEditProductionButton);
        editProduction();
    }

    @FXML
    private void handleCancelEditProductionClick(MouseEvent event) {
        System.out.println("canceled edit");
        descriptionTitleVBox.getChildren().remove(editOptionsHBox);
        descriptionTitleVBox.getChildren().add(0, editOptionsHBox);
        editOptionsHBox.getChildren().removeAll(cancelEditProductionButton, saveEditProductionButton);
        editOptionsHBox.getChildren().add(editProductionButton);
        showCreditList(systemFacade.getActiveProduction());
    }

    @FXML
    private void handleSaveEditProductionClick(MouseEvent event) {
        System.out.println("saving");
        descriptionTitleVBox.getChildren().remove(editOptionsHBox);
        descriptionTitleVBox.getChildren().add(0, editOptionsHBox);
        editOptionsHBox.getChildren().removeAll(cancelEditProductionButton, saveEditProductionButton);
        editOptionsHBox.getChildren().add(editProductionButton);
        showCreditList(systemFacade.getActiveProduction());
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

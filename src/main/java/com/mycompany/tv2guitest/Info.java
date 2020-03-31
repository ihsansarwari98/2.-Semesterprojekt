package com.mycompany.tv2guitest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.*;

public class Info {

    public static String username = "Admin";
    public static String password = "123";

    public static String statusGreen = "#50C878";
    public static String statusYellow = "#ffe746";
    public static String statusRed = "#d21e1e";

    public static String fontColor1 = "#FFFFFF";
    public static String fontColor2 = "#242424";

    public static String accentStartColor = "#F25C54"; //  #F25C54 mint green ish      #57ffcd
    public static String accentEndColor = "#F7B267"; //  #F7B267   light blue ish      #74c5ff

    public static LinearGradient accentGradient;

    public static String backgroundColor = "#FFFFFF"; // #FFFFFF
    public static String backgroundShadeColor = "#F7F7F7"; //F7F7F7

    public static String forgroundColor = "#242424"; // #242424

    public static int sideBarWidth = 320;
    public static double sideBarAnimation = 0.5;
    public static int roundAmount = 25;
    public static double scaleAmount = 0.15;

    public static int fontSizeDefault = 16;

    public static boolean sidePanelOn;

    public static ObservableList<Production> productions = FXCollections.observableArrayList(); // DATABASE

    public static Production getProgram(String title) {
        Production temp = null;
        for (Production production : productions) {
            if (title.equals(production.getTitle())) {
                temp = production;
            }
        }
        return temp;
    }

    public static void updateColors() {
        // Create the stops


        Stop[] stop = {new Stop(0, Color.valueOf(accentStartColor)), new Stop(1, Color.valueOf(accentEndColor))};
        // create a Linear gradient object 
        accentGradient = new LinearGradient(0, 1, 1, 0, true, CycleMethod.NO_CYCLE, stop);
    }

}

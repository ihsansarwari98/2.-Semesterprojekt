package com.mycompany.tv2guitest;

import javafx.scene.input.MouseEvent;

public interface BaseController {

    void handleButtonHoveringEnter(MouseEvent event);

    void handleButtonHoveringExit(MouseEvent event);

    void handleTopDragDragged(MouseEvent event);

    void handleTopDragClicked(MouseEvent event);

    void handleTopDragPressed(MouseEvent event);

    void minimizeButtonAction();

    void maximizeButtonAction();

    void closeButtonAction();

}

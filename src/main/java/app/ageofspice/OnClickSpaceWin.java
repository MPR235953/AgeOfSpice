package app.ageofspice;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class OnClickSpaceWin extends Pane{
    public Button buildButton = new Button();
    public Button occupyButton = new Button();
    public Button closeButton = new Button();

    ///TODO: podlaczenie kolejnych zdarzen pod przyciski

    public void makeWin(int x, int y){
        this.setPrefWidth(100);
        this.setPrefHeight(120);
        this.setStyle("-fx-background-color: white;" +
                "-fx-border-color: orange;" +
                "-fx-border-width: 5;" +
                "-fx-view-order: -10;");
        this.getChildren().addAll(buildButton, occupyButton, closeButton);
        this.relocate(x + MapController.TILE_SIZE, y);

        buildButton.setText("Buduj");
        buildButton.relocate(10, 10);
        buildButton.setPrefWidth(80);

        occupyButton.setText("Zajmij");
        occupyButton.relocate(10, 40);
        occupyButton.setPrefWidth(80);

        closeButton.setText("Zamknij");
        closeButton.relocate(10, 70);
        closeButton.setPrefWidth(80);
        closeButton.setOnAction(this::closeWin);
    }

    public void closeWin(ActionEvent event){
        this.relocate(9999,9999);
    }
}

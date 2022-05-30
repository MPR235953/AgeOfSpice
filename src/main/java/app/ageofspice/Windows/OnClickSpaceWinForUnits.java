package app.ageofspice.Windows;

import app.ageofspice.MapController;
import app.ageofspice.Species.SpeciesColors;
import app.ageofspice.Tile;
import app.ageofspice.TileType;
import app.ageofspice.units_classes.unit;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import static app.ageofspice.GameLoop.playerNumber;
import static app.ageofspice.GameLoop.playerResources;
import static app.ageofspice.MapController.*;
import static app.ageofspice.TileType.*;

public class OnClickSpaceWinForUnits extends Pane{
    public Button moveButton = new Button();
    public Button occupyButton = new Button();
    public Button closeButton = new Button();
    public static boolean flagToMove =false;
    public Tile parentTile;
    public static unit unitToMove;

    public OnClickSpaceWinForUnits(unit unit1){
        this.unitToMove = unit1;
    }

    public void setParentTile(Tile tile){ parentTile = tile; }      //funkcja zrobiona po to aby stylizowac kafelek ktory zostal klikniety

    ///TODO: podlaczenie kolejnych zdarzen pod przyciski

    public void makeWin(int x, int y){
        MapController.staticPane.getChildren().add(this);   //wyswietlenie okienka na mapie
        parentTile.setStroke(Color.RED);

        //Konfiguracja glownego Pane
        this.setPrefWidth(100);
        this.setPrefHeight(120);
        this.setStyle("-fx-background-color: white;" +
                "-fx-border-color: orange;" +
                "-fx-border-width: 5;" +
                "-fx-view-order: -10;");
        this.getChildren().addAll(moveButton, occupyButton, closeButton);
        this.relocate(x + MapController.TILE_SIZE, y);

        //Konfiguracja buttonow
        moveButton.setText("Ruch");
        moveButton.relocate(10, 10);
        moveButton.setPrefWidth(80);
        moveButton.setOnAction(this::movementWin);

        occupyButton.setText("Zajmij");
        occupyButton.relocate(10, 40);
        occupyButton.setPrefWidth(80);

        closeButton.setText("Zamknij");
        closeButton.relocate(10, 70);
        closeButton.setPrefWidth(80);
        closeButton.setOnAction(this::closeWin);
    }



    public void closeWin(ActionEvent event){
        MapController.staticPane.getChildren().remove(this);
        if(parentTile.getTileType() == TileType.EMPTY_SPACE)
            parentTile.setStroke(Color.TRANSPARENT);
        parentTile.active = false;
    }
    public void movementWin(ActionEvent event) {

    /// TODO: 29.05.2022 Ustawienie grubości linii zobaczyć tu
        for (int i = unitToMove.position.x-unitToMove.movementSpeedleft; i<=unitToMove.position.x+unitToMove.movementSpeedleft;i++){
            for (int j = unitToMove.position.y-unitToMove.movementSpeedleft; j<=unitToMove.position.y+unitToMove.movementSpeedleft;j++) {
                if (!((i < 0 || i >= HORIZONTAL_TILE_COUNT) || (j < 0 || j >= VERTICAL_TILE_COUNT))  &&  (board[i][j].getTileType() == EMPTY_SPACE || board[i][j].getTileType() == DESTROYER_SHIP
                 || board[i][j].getTileType() ==DRED_SHIP || board[i][j].getTileType() == SCOUT_SHIP || board[i][j].getTileType() == EXPLORER_SHIP) ) {

                    switch (playerResources[playerNumber].getSpeciesType()) {
                        case JAVALERZY -> board[i][j].setStroke(SpeciesColors.javColor);
                        case LUDZIE -> board[i][j].setStroke(SpeciesColors.ludColor);
                        case SZRUNGALE -> board[i][j].setStroke(SpeciesColors.szrColor);
                    }
                    board[i][j].setStrokeWidth(3);
                    board[i][j].active = true;
                }
            }
        }
        flagToMove = true;
        closeWin(event);
        }



}

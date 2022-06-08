package app.ageofspice.Windows;

import app.ageofspice.*;
import app.ageofspice.Species.SpeciesColors;
import app.ageofspice.UnitandBuildingStorage.UnitsStorage;
import app.ageofspice.units_classes.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Map;

import static app.ageofspice.GameLoop.*;
import static app.ageofspice.MapController.*;
import static app.ageofspice.TileType.*;

public class OnClickSpaceWinForUnits extends Pane{
    public Button moveButton = new Button();
    public Button occupyButton = new Button();
    public Button closeButton = new Button();
    public static boolean flagToMove =false;
    public Tile parentTile;
    public static unit unitToMove;

    public Pane statisticsPane = new Pane();
    public Label healthLabel = new Label("HP: ");
    public Label damageLabel = new Label("DMG: ");
    public Label speedLabel = new Label("SP: ");

    public OnClickSpaceWinForUnits(){arrayOnClickSpaceWinForUnits.add(this);}
    public OnClickSpaceWinForUnits(unit unit1){
        this.unitToMove = unit1;
        arrayOnClickSpaceWinForUnits.add(this);
    }

    public void setParentTile(Tile tile){ parentTile = tile; }      //funkcja zrobiona po to aby stylizowac kafelek ktory zostal klikniety



    public void makeWin(int x, int y){
        MapController.staticPane.getChildren().add(this);   //wyswietlenie okienka na mapie
        parentTile.setStroke(Colors.tileLight);

        //Konfiguracja glownego Pane
        this.setPrefWidth(100);
        this.setPrefHeight(105);
        this.setStyle(Colors.winBackground +
                "-fx-border-width: 5;" +
                "-fx-view-order: -10;" +
                "-fx-border-color:" + SpeciesColors.ColorCSS[playerNumber]);
        this.getChildren().addAll(moveButton, occupyButton, closeButton);
        this.relocate(x + MapController.TILE_SIZE, y);

        //Konfiguracja buttonow
        moveButton.setText("Move");
        moveButton.relocate(10, 10);
        moveButton.setPrefWidth(80);
        moveButton.setOnAction(this::movementWin);

        occupyButton.setText("Occupy");
        occupyButton.relocate(10, 40);
        occupyButton.setPrefWidth(80);
        occupyButton.setOnAction(this::takePlanetWin);

        closeButton.setText("Close");
        closeButton.relocate(10, 70);
        closeButton.setPrefWidth(80);
        closeButton.setOnAction(this::closeWin);

        makeStatisticWin(x, y);
    }

    public void makeStatisticWin(int x, int y){
        //Konfiguracja Pane ze statami jednostki
        MapController.staticPane.getChildren().add(statisticsPane);

        statisticsPane.setPrefWidth(100);
        statisticsPane.setPrefHeight(105);
        statisticsPane.setStyle(Colors.winBackground +
                "-fx-border-width: 5;" +
                "-fx-view-order: -10;" +
                "-fx-border-color:" + SpeciesColors.ColorCSS[playerNumber]);
        statisticsPane.getChildren().addAll(healthLabel, damageLabel, speedLabel);
        statisticsPane.relocate(x - statisticsPane.getPrefWidth(), y);

        //Konfiguracja labelow do statystyk
        healthLabel.relocate(10, 15);
        healthLabel.setTextFill(Color.WHITE);
        healthLabel.setStyle("-fx-font-size: 15;");

        damageLabel.relocate(10, 45);
        damageLabel.setTextFill(Color.WHITE);
        damageLabel.setStyle("-fx-font-size: 15;");

        speedLabel.relocate(10, 75);
        speedLabel.setTextFill(Color.WHITE);
        speedLabel.setStyle("-fx-font-size: 15;");

        unit newUnit = playerResources[playerNumber].getUnitBuilData().searchforunit(parentTile.boardX, parentTile.boardY);

        healthLabel.setText(healthLabel.getText() + newUnit.actualHP);
        damageLabel.setText(damageLabel.getText() + (newUnit.baseDMG + playerResources[playerNumber].getUnitBuilData().bonusAttack));
        speedLabel.setText(speedLabel.getText() + newUnit.movementSpeedleft);
    }

    public void closeWin(ActionEvent event){
        MapController.staticPane.getChildren().remove(statisticsPane);
        MapController.staticPane.getChildren().remove(this);
        if(parentTile.getTileType() == TileType.EMPTY_SPACE)
            parentTile.setStroke(Color.TRANSPARENT);
        parentTile.active = false;
    }
    public void movementWin(ActionEvent event) {


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

        public void takePlanetWin(ActionEvent event) {

            int i  = searchForPlanets();
            if (i == -1){
                return;
            }
            allPlanetStorage.get(i).owner = playerResources[playerNumber].getSpeciesType();

            switch (playerResources[playerNumber].getSpeciesType()) {
                case JAVALERZY ->   board[allPlanetStorage.get(i).planetPosition.x][allPlanetStorage.get(i).planetPosition.y].setStroke(SpeciesColors.javColor);
                case LUDZIE ->   board[allPlanetStorage.get(i).planetPosition.x][allPlanetStorage.get(i).planetPosition.y].setStroke(SpeciesColors.ludColor);
                case SZRUNGALE ->   board[allPlanetStorage.get(i).planetPosition.x][allPlanetStorage.get(i).planetPosition.y].setStroke(SpeciesColors.szrColor);
            }
            board[allPlanetStorage.get(i).planetPosition.x][allPlanetStorage.get(i).planetPosition.y].setStrokeWidth(3);
            board[allPlanetStorage.get(i).planetPosition.x][allPlanetStorage.get(i).planetPosition.y].active = true;
            unitToMove.movementSpeedleft = 0;
            closeWin(event);
        }
    public int searchForPlanets(){
        Planet planet;
        for (int i =0; i< allPlanetStorage.size();i++){
            planet = allPlanetStorage.get(i);
            if (Math.abs(unitToMove.position.x - planet.planetPosition.x) <=1 &&  Math.abs(unitToMove.position.y - planet.planetPosition.y) <=1 && planet.owner != playerResources[playerNumber].getSpeciesType())
              return i;
        }
      return -1;
    }

}

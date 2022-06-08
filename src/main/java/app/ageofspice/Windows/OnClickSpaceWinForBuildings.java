package app.ageofspice.Windows;

import app.ageofspice.Buildings.absBuilding;
import app.ageofspice.Colors;
import app.ageofspice.MapController;
import app.ageofspice.Species.SpeciesColors;
import app.ageofspice.Tile;
import app.ageofspice.TileType;
import app.ageofspice.units_classes.unit;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import static app.ageofspice.GameLoop.*;
import static app.ageofspice.GameLoop.playerNumber;

public class OnClickSpaceWinForBuildings extends Pane{
    public Button closeButton = new Button();
    public Tile parentTile;

    public Pane statisticsPane = new Pane();
    public Label healthLabel = new Label("HP: ");
    public Label typeLabel = new Label("????");

    public OnClickSpaceWinForBuildings(){arrayOnClickSpaceWinForBuildings.add(this);}

    public void setParentTile(Tile tile){ parentTile = tile; }

    public void makeStatisticWin(int x, int y){
        MapController.staticPane.getChildren().add(statisticsPane);

        statisticsPane.setPrefWidth(100);
        statisticsPane.setPrefHeight(105);
        statisticsPane.setStyle(Colors.winBackground +
                "-fx-border-width: 5;" +
                "-fx-view-order: -10;" +
                "-fx-border-color:" + SpeciesColors.ColorCSS[playerNumber]);
        statisticsPane.getChildren().addAll(healthLabel, typeLabel, closeButton);
        statisticsPane.relocate(x - statisticsPane.getPrefWidth(), y);

        //Konfiguracja labelow do statystyk
        typeLabel.relocate(10, 10);
        typeLabel.setTextFill(Color.WHITE);
        typeLabel.setStyle("-fx-font-size: 20;");
        if(parentTile.getTileType() == TileType.MINE_STATION) typeLabel.setText("MINE");
        else if(parentTile.getTileType() == TileType.WAR_STATION) typeLabel.setText("FACTORY");

        healthLabel.relocate(10, 40);
        healthLabel.setTextFill(Color.WHITE);
        healthLabel.setStyle("-fx-font-size: 15;");

        closeButton.setText("Close");
        closeButton.relocate(10, 70);
        closeButton.setPrefWidth(80);
        closeButton.setOnAction(this::closeWin);

        absBuilding newBuilding = null;
        for(int i = 0; i < 3; i++){
            newBuilding = playerResources[i].getUnitBuilData().searchForBuilding(parentTile.boardX, parentTile.boardY);
            if(newBuilding != null) break;
        }

        assert newBuilding != null;
        healthLabel.setText(healthLabel.getText() + newBuilding.actualHP);

    }

    public void closeWin(ActionEvent event){
        MapController.staticPane.getChildren().remove(statisticsPane);
        MapController.staticPane.getChildren().remove(this);
        if(parentTile.getTileType() == TileType.EMPTY_SPACE)
            parentTile.setStroke(Color.TRANSPARENT);
        parentTile.active = false;
    }
}

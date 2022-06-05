package app.ageofspice.Windows;

import app.ageofspice.Colors;
import app.ageofspice.MapController;
import app.ageofspice.Species.SpeciesColors;
import app.ageofspice.Tile;
import app.ageofspice.TileType;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import static app.ageofspice.GameLoop.*;

public class OnClickSpaceWin extends Pane{
    public Button buildButton = new Button();
    public Button closeButton = new Button();
    public Tile parentTile;

    public OnClickSpaceWin(){arrayOnClickSpaceWin.add(this);}

    public void setParentTile(Tile tile){ parentTile = tile; }      //funkcja zrobiona po to aby stylizowac kafelek ktory zostal klikniety

    ///TODO: podlaczenie kolejnych zdarzen pod przyciski

    public void makeWinForBuildings(int x, int y){
        MapController.staticPane.getChildren().add(this);   //wyswietlenie okienka na mapie
        parentTile.setStroke(Colors.tileLight);

        //Konfiguracja glownego Pane
        this.setPrefWidth(100);
        this.setPrefHeight(75);
        this.setStyle(Colors.winBackground +
                "-fx-border-width: 5;" +
                "-fx-view-order: -10;" +
                "-fx-border-color:" + SpeciesColors.ColorCSS[playerNumber]);
        this.getChildren().addAll(buildButton, closeButton);
        this.relocate(x + MapController.TILE_SIZE, y);

        //Konfiguracja buttonow
        buildButton.setText("Build");
        buildButton.relocate(10, 10);
        buildButton.setPrefWidth(80);
        buildButton.setOnAction(this::showBuildWinForBuildings);

        closeButton.setText("Close");
        closeButton.relocate(10, 40);
        closeButton.setPrefWidth(80);
        closeButton.setOnAction(this::closeWin);
    }

    //Makewin dla stacji tylko by rekrutowac jednostki
    public void makeWinForStation(int x, int y){
        MapController.staticPane.getChildren().add(this);   //wyswietlenie okienka na mapie
        parentTile.setStroke(Colors.tileLight);

        //Konfiguracja glownego Pane
        this.setPrefWidth(100);
        this.setPrefHeight(75);
        this.setStyle(Colors.winBackground +
                "-fx-border-width: 5;" +
                "-fx-view-order: -10;" +
                "-fx-border-color:" + SpeciesColors.ColorCSS[playerNumber]);
        this.getChildren().addAll(buildButton, closeButton);
        this.relocate(x + MapController.TILE_SIZE, y);

        //Konfiguracja buttonow
        buildButton.setText("Recruit");
        buildButton.relocate(10, 10);
        buildButton.setPrefWidth(80);
        buildButton.setOnAction(this::showBuildWinForStation);

        closeButton.setText("Close");
        closeButton.relocate(10, 40);
        closeButton.setPrefWidth(80);
        closeButton.setOnAction(this::closeWin);
    }

    //funkcja tworzaca kolejne sub okienko z wyborem jednostek w stacji do budowania
    public void showBuildWinForStation(ActionEvent event){
        BuildWinForParentalStation buildWin = new BuildWinForParentalStation();
        buildWin.setParentTile(parentTile);     //przekazanie kliknietego kafelka glebiej do kolejnej klasy
        buildWin.makeWin();                     //tworzenie nowego okienka
        MapController.staticPane.getChildren().remove(this);    //jako ze mamy nowe okienko, to to aktualne powinno zniknac z mapy
    }

    public void closeWin(ActionEvent event){
        MapController.staticPane.getChildren().remove(this);
        if(parentTile.getTileType() == TileType.EMPTY_SPACE)
            parentTile.setStroke(Color.TRANSPARENT);
        parentTile.active = false;
    }

    //funkcja tworzaca kolejne sub okienko z wyborem stacji do zbudowania
    public void showBuildWinForBuildings(ActionEvent event){
        BuildWinForBuildings buildWin = new BuildWinForBuildings();
        buildWin.setParentTile(parentTile);     //przekazanie kliknietego kafelka glebiej do kolejnej klasy
        buildWin.makeWin();                     //tworzenie nowego okienka
        MapController.staticPane.getChildren().remove(this);    //jako ze mamy nowe okienko, to to aktualne powinno zniknac z mapy
    }



}

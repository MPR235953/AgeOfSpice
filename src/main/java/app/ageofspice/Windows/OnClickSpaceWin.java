package app.ageofspice.Windows;

import app.ageofspice.MapController;
import app.ageofspice.Tile;
import app.ageofspice.TileType;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class OnClickSpaceWin extends Pane{
    public Button buildButton = new Button();
    public Button occupyButton = new Button();
    public Button closeButton = new Button();
    public Tile parentTile;

    public void setParentTile(Tile tile){ parentTile = tile; }      //funkcja zrobiona po to aby stylizowac kafelek ktory zostal klikniety

    ///TODO: podlaczenie kolejnych zdarzen pod przyciski

    public void makeWin(int x, int y){
        MapController.staticPane.getChildren().add(this);   //wyswietlenie okienka na mapie
        parentTile.setStroke(Color.RED);

        //Konfiguracja glownego Pane
        this.setPrefWidth(100);
        this.setPrefHeight(120);
        this.setStyle("-fx-background-color: white;" +
                "-fx-border-color: green;" +
                "-fx-border-width: 5;" +
                "-fx-view-order: -10;");
        this.getChildren().addAll(buildButton, occupyButton, closeButton);
        this.relocate(x + MapController.TILE_SIZE, y);

        //Konfiguracja buttonow
        buildButton.setText("Buduj");
        buildButton.relocate(10, 10);
        buildButton.setPrefWidth(80);
        buildButton.setOnAction(this::showBuildWin);

        occupyButton.setText("Zajmij");
        occupyButton.relocate(10, 40);
        occupyButton.setPrefWidth(80);

        closeButton.setText("Zamknij");
        closeButton.relocate(10, 70);
        closeButton.setPrefWidth(80);
        closeButton.setOnAction(this::closeWin);
    }

    //Makewin dla stacji tylko by rekrutowac jednostki
    public void makeWinForStation(int x, int y){
        MapController.staticPane.getChildren().add(this);   //wyswietlenie okienka na mapie
        parentTile.setStroke(Color.RED);

        //Konfiguracja glownego Pane
        this.setPrefWidth(100);
        this.setPrefHeight(120);
        this.setStyle("-fx-background-color: white;" +
                "-fx-border-color: green;" +
                "-fx-border-width: 5;" +
                "-fx-view-order: -10;");
        this.getChildren().addAll(buildButton, closeButton);
        this.relocate(x + MapController.TILE_SIZE, y);

        //Konfiguracja buttonow
        buildButton.setText("Rekrutuj");
        buildButton.relocate(10, 10);
        buildButton.setPrefWidth(80);
        buildButton.setOnAction(this::showBuildWinForStation);

        closeButton.setText("Zamknij");
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
    public void showBuildWin(ActionEvent event){
        BuildWin buildWin = new BuildWin();
        buildWin.setParentTile(parentTile);     //przekazanie kliknietego kafelka glebiej do kolejnej klasy
        buildWin.makeWin();                     //tworzenie nowego okienka
        MapController.staticPane.getChildren().remove(this);    //jako ze mamy nowe okienko, to to aktualne powinno zniknac z mapy
    }



}

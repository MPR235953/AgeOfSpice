package app.ageofspice.Windows;

import app.ageofspice.*;
import app.ageofspice.Species.SpeciesType;
import app.ageofspice.movement.ActualPosition;
import app.ageofspice.units_classes.ScoutShip;
import app.ageofspice.units_classes.unit;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.Map;

import static app.ageofspice.GameLoop.playerNumber;
import static app.ageofspice.GameLoop.playerResources;
import static app.ageofspice.MapController.*;

//klasa robocza. Możliwe błędy
//Będę grzebał przy niej jeszcze


public class BuildWinForStation extends Pane{
    public Button closeButton = new Button("Zamknij");
    public Button[] buildButtons = {new Button("Rekrutuj"), new Button("Rekrutuj"), new Button("Rekrutuj"),new Button("Rekrutuj")};
    public ImageView[] imgines = {new ImageView(), new ImageView(), new ImageView(),new ImageView()};
    public Label[] labels = {new Label("Statek zwiadowczy"), new Label("Statek eksploracyjny"), new Label("Pancernik"), new Label("Niszczyciel")};
    public Pane[] subPanes = {new Pane(), new Pane(), new Pane(),new Pane()};
    public Tile parentTile;
    public ImageView imageToUpload = new ImageView();

    public void setParentTile(Tile tile){ parentTile = tile; }

    public void makeWin(){

        //konfiguracja glownego Pane okienka
        this.setPrefWidth(1200);
        this.setPrefHeight(150);
        this.setStyle("-fx-background-color: white;" +
                "-fx-border-color: orange;" +
                "-fx-border-width: 5;" +
                "-fx-view-order: -10;");
        this.relocate(AgeOfSpiceApp.SCREEN_WIDTH / 2 - this.getPrefWidth() / 2, AgeOfSpiceApp.SCREEN_HEIGHT / 2 - this.getPrefHeight() / 2);

        //konfiguracja przycisku zgaszenia okienka
        closeButton.setPrefWidth(70);
        closeButton.setPrefHeight(70);
        closeButton.relocate(this.getPrefWidth() - 100 + closeButton.getPrefWidth() / 5, this.getPrefHeight() / 2 - closeButton.getPrefHeight() / 2);
        closeButton.setOnAction(this::closeWin);

        //konfiguracja 3 subPane'ow, imageViews, labelow i przyciskow
        //okienko to 1 glowny Pane w ktorym sa 3 subPany, ktore zawieraja imageView, label i przycisk
        for(int i = 0; i < subPanes.length; i++) {
            subPanes[i].setPrefHeight(150);
            subPanes[i].setPrefWidth(300);
            subPanes[i].setStyle("-fx-border-color: black;");
            subPanes[i].relocate(i * subPanes[i].getPrefWidth(), 0);
            subPanes[i].getChildren().addAll(imgines[i], labels[i], buildButtons[i]);   //dodanie do subPane img, label i button
            imgines[i].setFitWidth(70);
            imgines[i].setFitHeight(70);
            imgines[i].relocate(subPanes[i].getPrefWidth() / 2 - imgines[i].getFitWidth() / 2, 10);
            labels[i].relocate(0, 90);
            labels[i].layoutXProperty().bind(subPanes[i].widthProperty().subtract(labels[i].widthProperty()).divide(2));
            buildButtons[i].relocate(0, 120);
            buildButtons[i].layoutXProperty().bind(subPanes[i].widthProperty().subtract(buildButtons[i].widthProperty()).divide(2));
        }
        //Kazdy przycisk ma ustawiona inna akcje na metode
        buildButtons[0].setOnAction(this::buildScout);
        buildButtons[1].setOnAction(this::buildExpl);
        buildButtons[2].setOnAction(this::buildDred);
        buildButtons[3].setOnAction(this::buildDest);
        //TODO: Napisac klasy dla stacji i zaprojektowac grafike
        //Narazie roboczo tylko dla playera nr 0, pozniej nalezy to powiazac z aktualnym
        //graczem w petli np robiac statyczna zmienna gracza i po kazdej iteracji nadpisywac ta zmienna
        switch(playerResources[playerNumber].getSpeciesType()){//speciesChoiceController.player[0].getSpeciesType()){
            //grafika tez narazie roboczo
            case JAVALERZY -> {
                imgines[0].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/alga_planet100.png").toURI().toString()));
                imgines[1].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Javalerzy_textures/Javalerzy_explorer_ship.png").toURI().toString()));
                imgines[2].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Javalerzy_textures/Javalerzy_tank_ship.png").toURI().toString()));
                imgines[3].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Javalerzy_textures/Javalerzy_sniper_ship.png").toURI().toString()));
            }
            case LUDZIE -> {
                imgines[0].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Ludzie_textures/Ludzie_scout.png").toURI().toString()));
                imgines[1].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Ludzie_textures/Ludzie_explorer_ship.png").toURI().toString()));
                imgines[2].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Ludzie_textures/Ludzie_dreadnought.png").toURI().toString()));
                imgines[3].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Ludzie_textures/Ludzie_destroyer.png").toURI().toString()));

            }
            case SZRUNGALE -> {
                imgines[0].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/alga_planet100.png").toURI().toString()));
                imgines[1].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Javalerzy_textures/Javalerzy_explorer_ship.png").toURI().toString()));
                imgines[2].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Javalerzy_textures/Javalerzy_tank_ship.png").toURI().toString()));
                imgines[3].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Javalerzy_textures/Javalerzy_sniper_ship.png").toURI().toString()));

            }
        }

        this.getChildren().addAll(subPanes[0], subPanes[1], subPanes[2],subPanes[3], closeButton);      //dodanie subPanow i przycisku do glownego Pane
        MapController.staticPane.getChildren().add(this);   //dodanie glownego Pane do staticPane tak aby mozna bylo wyswietlic okienko na mapie


        //Centrowanie obrazka stacji na mapie
        imageToUpload.setFitHeight(MapController.TILE_SIZE);
        imageToUpload.setFitWidth(MapController.TILE_SIZE);
        imageToUpload.relocate(parentTile.x, parentTile.y);
    }

    public ActualPosition seekFreeSpace(SpeciesType type){
        switch (type){
            case JAVALERZY -> {
                for (int i = 0; i< 2; i++ ){
                    for (int j = 0; j< 3; j++ ){
                        if (board[JAV_X+i][JAV_Y-1+j].getTileType() == TileType.EMPTY_SPACE)
                            return new ActualPosition(JAV_X+i,JAV_Y-1+j);
                    }
                }
            }
            case LUDZIE -> {
                for (int i = 0; i< 3; i++ ){
                    for (int j = 0; j< 2; j++ ){
                        if (board[LUD_X+i-1][LUD_Y-1+j].getTileType() == TileType.EMPTY_SPACE)
                            return new ActualPosition(LUD_X+i-1,LUD_Y-1+j);
                    }
                }
            }
            case SZRUNGALE -> {
                for (int i = 0; i< 2; i++ ){
                    for (int j = 0; j< 3; j++ ){
                        if (board[SZR_X+i-1][SZR_Y-1+j].getTileType() == TileType.EMPTY_SPACE)
                            return new ActualPosition(SZR_X+i-1,SZR_Y-1+j);
                    }
                }
            }
        }


        return  null;
    }

    public void buildScout(ActionEvent event){
        ActualPosition actualPosition = new ActualPosition();
        switch(playerResources[playerNumber].getSpeciesType()){
            case JAVALERZY -> {
                actualPosition = seekFreeSpace(SpeciesType.JAVALERZY);
            }
            case LUDZIE -> {
                actualPosition = seekFreeSpace(SpeciesType.LUDZIE);
            }
            case SZRUNGALE -> {
                actualPosition = seekFreeSpace(SpeciesType.SZRUNGALE);
            }
            default -> {
                break;
            }
        }
        if (actualPosition == null) {
            closeWin(event);
            return;
        }
        if (playerResources[playerNumber].buyunits(TileType.SCOUT_SHIP, actualPosition) == -1){
            closeWin(event);
            return;
        }
        board[actualPosition.x][actualPosition.y].setTileType(TileType.SCOUT_SHIP);
        MapController.staticPane.getChildren().add(imageToUpload);      //wyswietlenie nowego obiektu na mapie
        closeWin(event);
    }

    public void buildExpl(ActionEvent event){
        ActualPosition actualPosition = new ActualPosition();
        switch(playerResources[playerNumber].getSpeciesType()){
            case JAVALERZY -> {
                actualPosition = seekFreeSpace(SpeciesType.JAVALERZY);
            }
            case LUDZIE -> {
                actualPosition = seekFreeSpace(SpeciesType.LUDZIE);
            }
            case SZRUNGALE -> {
                actualPosition = seekFreeSpace(SpeciesType.SZRUNGALE);
            }
            default -> {
                break;
            }
        }
        if (actualPosition == null) {
            closeWin(event);
            return;
        }
        if (playerResources[playerNumber].buyunits(TileType.EXPLORER_SHIP, actualPosition) == -1){
            closeWin(event);
            return;
        }
        board[actualPosition.x][actualPosition.y].setTileType(TileType.EXPLORER_SHIP);
        MapController.staticPane.getChildren().add(imageToUpload);      //wyswietlenie nowego obiektu na mapie
        closeWin(event);
    }

    public void buildDred(ActionEvent event){
        ActualPosition actualPosition = new ActualPosition();
        switch(playerResources[playerNumber].getSpeciesType()){
            case JAVALERZY -> {
                actualPosition = seekFreeSpace(SpeciesType.JAVALERZY);
            }
            case LUDZIE -> {
                actualPosition = seekFreeSpace(SpeciesType.LUDZIE);
            }
            case SZRUNGALE -> {
                actualPosition = seekFreeSpace(SpeciesType.SZRUNGALE);
            }
            default -> {
                break;
            }
        }
        if (actualPosition == null) {
            closeWin(event);
            return;
        }
        if (playerResources[playerNumber].buyunits(TileType.DRED_SHIP, actualPosition) == -1){
            closeWin(event);
            return;
        }
        board[actualPosition.x][actualPosition.y].setTileType(TileType.DRED_SHIP);
        MapController.staticPane.getChildren().add(imageToUpload);      //wyswietlenie nowego obiektu na mapie
        closeWin(event);
    }

    public void buildDest(ActionEvent event){
        ActualPosition actualPosition = new ActualPosition();
        switch(playerResources[playerNumber].getSpeciesType()){
            case JAVALERZY -> {
                actualPosition = seekFreeSpace(SpeciesType.JAVALERZY);
            }
            case LUDZIE -> {
                actualPosition = seekFreeSpace(SpeciesType.LUDZIE);
            }
            case SZRUNGALE -> {
                actualPosition = seekFreeSpace(SpeciesType.SZRUNGALE);
            }
            default -> {
                break;
            }
        }
        if (actualPosition == null) {
            closeWin(event);
            return;
        }
        if (playerResources[playerNumber].buyunits(TileType.DESTROYER_SHIP, actualPosition) == -1){
            closeWin(event);
            return;
        }
        board[actualPosition.x][actualPosition.y].setTileType(TileType.DESTROYER_SHIP);
        MapController.staticPane.getChildren().add(imageToUpload);      //wyswietlenie nowego obiektu na mapie
        closeWin(event);
    }


    public void closeWin(ActionEvent event){
        parentTile.active = false;
        if(parentTile.getTileType() == TileType.EMPTY_SPACE)
            parentTile.setStroke(Color.TRANSPARENT);
        MapController.staticPane.getChildren().remove(this);
    }
}

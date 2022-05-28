package app.ageofspice.Windows;

import app.ageofspice.*;
import app.ageofspice.Species.SpeciesType;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.File;

import static app.ageofspice.GameLoop.playerNumber;
import static app.ageofspice.GameLoop.playerResources;

public class BuildWin extends Pane{
    public Button closeButton = new Button("Zamknij");
    public Button[] buildButtons = {new Button("Buduj"), new Button("Buduj"), new Button("Buduj")};
    public ImageView[] imgines = {new ImageView(), new ImageView(), new ImageView()};
    public Label[] labels = {new Label("Kopalnia"), new Label("Fabryka"), new Label("Laboratorium")};
    public Pane[] subPanes = {new Pane(), new Pane(), new Pane()};
    public Tile parentTile;
    public ImageView imageToUpload = new ImageView();

    public void setParentTile(Tile tile){ parentTile = tile; }

    public void makeWin(){

        //konfiguracja glownego Pane okienka
        this.setPrefWidth(700);
        this.setPrefHeight(150);
        this.setStyle("-fx-background-color: white;" +
                "-fx-border-color: green;" +
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
            subPanes[i].setPrefWidth(200);
            subPanes[i].setStyle("-fx-border-color: green;");
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
        buildButtons[0].setOnAction(this::buildKop);
        buildButtons[1].setOnAction(this::buildFab);
        buildButtons[2].setOnAction(this::buildLab);

        //TODO: Napisac klasy dla stacji i zaprojektowac grafike
        //Narazie roboczo tylko dla playera nr 0, pozniej nalezy to powiazac z aktualnym
        //graczem w petli np robiac statyczna zmienna gracza i po kazdej iteracji nadpisywac ta zmienna
        switch(playerResources[playerNumber].getSpeciesType()){//speciesChoiceController.player[0].getSpeciesType()){
            //grafika tez narazie roboczo
            case JAVALERZY -> {
                imgines[0].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/alga_planet100.png").toURI().toString()));
                imgines[1].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/crystal_planet100.png").toURI().toString()));
                imgines[2].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/vibranium_planet100.png").toURI().toString()));
            }
            case LUDZIE -> {
                imgines[1].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/alga_planet100.png").toURI().toString()));
                imgines[2].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/crystal_planet100.png").toURI().toString()));
                imgines[0].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/vibranium_planet100.png").toURI().toString()));
            }
            case SZRUNGALE -> {
                imgines[2].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/alga_planet100.png").toURI().toString()));
                imgines[0].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/crystal_planet100.png").toURI().toString()));
                imgines[1].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/vibranium_planet100.png").toURI().toString()));
            }
        }

        this.getChildren().addAll(subPanes[0], subPanes[1], subPanes[2], closeButton);      //dodanie subPanow i przycisku do glownego Pane
        MapController.staticPane.getChildren().add(this);   //dodanie glownego Pane do staticPane tak aby mozna bylo wyswietlic okienko na mapie


        //Centrowanie obrazka stacji na mapie
        imageToUpload.setFitHeight(MapController.TILE_SIZE);
        imageToUpload.setFitWidth(MapController.TILE_SIZE);
        imageToUpload.relocate(parentTile.x, parentTile.y);
    }

    public void buildKop(ActionEvent event){
        switch(playerResources[playerNumber].getSpeciesType()){
            case JAVALERZY -> {
                parentTile.setTileType(TileType.ALGA_PLANET);       //oznaczenie ze obiekt znajduje sie na mapie
                imageToUpload.setImage(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/alga_planet100.png").toURI().toString()));
                playerResources[playerNumber].getResources().algi.quantity -= 10;
            }
            case LUDZIE -> {
                parentTile.setTileType(TileType.VIBRANIUM_PLANET);       //oznaczenie ze obiekt znajduje sie na mapie
                imageToUpload.setImage(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/vibranium_planet100.png").toURI().toString()));
                playerResources[playerNumber].getResources().przyprawa.quantity -= 10;
            }
            case SZRUNGALE -> {
                parentTile.setTileType(TileType.CRYSTAL_PLANET);       //oznaczenie ze obiekt znajduje sie na mapie
                imageToUpload.setImage(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/crystal_planet100.png").toURI().toString()));
                playerResources[playerNumber].getResources().krysztal.quantity -= 10;
            }
        }
        MapController.staticPane.getChildren().add(imageToUpload);      //wyswietlenie nowego obiektu na mapie
        closeWin(event);
    }

    public void buildFab(ActionEvent event){
        switch(SpeciesType.JAVALERZY){
            case JAVALERZY -> {

            }
        }
    }

    public void buildLab(ActionEvent event){
        switch(SpeciesType.JAVALERZY){
            case JAVALERZY -> {

            }
        }
    }

    public void closeWin(ActionEvent event){
        parentTile.active = false;
        parentTile.setStroke(Color.TRANSPARENT);
        MapController.staticPane.getChildren().remove(this);
    }
}

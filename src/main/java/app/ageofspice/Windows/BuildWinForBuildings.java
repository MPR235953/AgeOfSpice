package app.ageofspice.Windows;

import app.ageofspice.*;
import app.ageofspice.Buildings.MineStation;
import app.ageofspice.Buildings.WarStation;
import app.ageofspice.Buildings.absBuilding;
import app.ageofspice.Species.SpeciesColors;
import app.ageofspice.Species.SpeciesType;
import app.ageofspice.movement.ActualPosition;
import app.ageofspice.units_classes.ScoutShip;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.File;

import static app.ageofspice.GameLoop.*;
import static app.ageofspice.MapController.SAS_SCALE_POS;

public class BuildWinForBuildings extends Pane{
    public Button closeButton = new Button("Close");
    public Pane closePane = new Pane();
    public Button[] buildButtons = {new Button("Build"), new Button("Build")};
    public ImageView[] imgines = {new ImageView(), new ImageView()};
    public Label[] labels = {new Label("Mine"), new Label("Factory")};
    public Pane[] subPanes = {new Pane(), new Pane()};
    public Tile parentTile;
    public ImageView imageToUpload = new ImageView();
    public Pane[][] materialPanes = {   {new Pane(), new Pane(), new Pane(), new Pane()},
                                        {new Pane(), new Pane(), new Pane(), new Pane()},
    };
    public ImageView[][] materialImageView = {  {new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/spice50.png").toURI().toString())), new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/crystal50.png").toURI().toString())), new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/alga50.png").toURI().toString())), new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/vibranium50.png").toURI().toString()))},
                                                {new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/spice50.png").toURI().toString())), new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/crystal50.png").toURI().toString())), new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/alga50.png").toURI().toString())), new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/vibranium50.png").toURI().toString()))},
    };
    public Label[][] materialLabel = {  {new Label(String.valueOf(MineStation.staticBaseCost.przyprawa.quantity)),new Label(String.valueOf(MineStation.staticBaseCost.krysztal.quantity)),new Label(String.valueOf(MineStation.staticBaseCost.algi.quantity)),new Label(String.valueOf(MineStation.staticBaseCost.wibranium.quantity))},
                                        {new Label(String.valueOf(WarStation.staticBaseCost.przyprawa.quantity)),new Label(String.valueOf(WarStation.staticBaseCost.krysztal.quantity)),new Label(String.valueOf(WarStation.staticBaseCost.algi.quantity)),new Label(String.valueOf(WarStation.staticBaseCost.wibranium.quantity))}
    };

    public BuildWinForBuildings(){arrayBuildWinForBuildings.add(this);}

    public void setParentTile(Tile tile){ parentTile = tile; }

    public void makeWin(){

        //konfiguracja glownego Pane okienka
        this.setPrefWidth(500);
        this.setPrefHeight(370);
        this.setStyle(Colors.winBackground +
                "-fx-border-width: 5;" +
                "-fx-view-order: -10;" +
                "-fx-border-color:" + SpeciesColors.ColorCSS[playerNumber]);
        this.relocate(AgeOfSpiceApp.SCREEN_WIDTH / 2 - this.getPrefWidth() / 2 - MapController.MARGIN, AgeOfSpiceApp.SCREEN_HEIGHT / 2 - this.getPrefHeight() / 2);

        //konfiguracja przycisku zgaszenia okienka
        closeButton.setPrefWidth(70);
        closeButton.setPrefHeight(70);
        closeButton.relocate(this.getPrefWidth() - 100 + closeButton.getPrefWidth() / 5, this.getPrefHeight() / 2 - closeButton.getPrefHeight() / 2);
        closeButton.setOnAction(this::closeWin);

        //konfiguracja 3 subPane'ow, imageViews, labelow i przyciskow
        //okienko to 1 glowny Pane w ktorym sa 3 subPany, ktore zawieraja imageView, label i przycisk
        for(int i = 0; i < subPanes.length; i++) {
            subPanes[i].setPrefHeight(this.getPrefHeight());
            subPanes[i].setPrefWidth(200);
            subPanes[i].setStyle("-fx-border-color:" + SpeciesColors.ColorCSS[playerNumber]);
            subPanes[i].relocate(i * subPanes[i].getPrefWidth(), 0);
            subPanes[i].getChildren().addAll(imgines[i], labels[i], buildButtons[i]);   //dodanie do subPane img, label i button
            imgines[i].setFitWidth(70);
            imgines[i].setFitHeight(70);
            imgines[i].relocate(subPanes[i].getPrefWidth() / 2 - imgines[i].getFitWidth() / 2, 10);
            labels[i].relocate(0, 90);
            labels[i].setTextFill(Color.WHITE);
            labels[i].layoutXProperty().bind(subPanes[i].widthProperty().subtract(labels[i].widthProperty()).divide(2));

            //ustawienie obiektow zwiazanych z surowcami
            for(int j = 0; j < 4; j++) {
                subPanes[i].getChildren().addAll(materialPanes[i][j]);
                materialPanes[i][j].setPrefWidth(130);
                materialPanes[i][j].setPrefHeight(50);
                materialPanes[i][j].relocate(0, 120 + (j * 50));
                materialPanes[i][j].layoutXProperty().bind(subPanes[i].widthProperty().subtract(materialPanes[i][j].widthProperty()).divide(2));
                materialPanes[i][j].getChildren().addAll(materialImageView[i][j], materialLabel[i][j]);
                materialLabel[i][j].relocate(60, 15);
                materialLabel[i][j].setTextFill(Color.WHITE);
            }

            //wylaczanie przyciskow rekrutacji w przypadku braku surowcow
            if(playerResources[playerNumber].getResources().przyprawa.quantity < Integer.parseInt(materialLabel[i][0].getText()) ||
                    playerResources[playerNumber].getResources().krysztal.quantity < Integer.parseInt(materialLabel[i][1].getText()) ||
                    playerResources[playerNumber].getResources().algi.quantity < Integer.parseInt(materialLabel[i][2].getText()) ||
                    playerResources[playerNumber].getResources().wibranium.quantity < Integer.parseInt(materialLabel[i][3].getText())){
                buildButtons[i].setDisable(true);
            }else buildButtons[i].setDisable(false);

            buildButtons[i].relocate(0, subPanes[i].getPrefHeight() - 40);
            //buildButtons[i].setStyle("-fx-background-color: #030303;" + "-fx-text-fill: white;");
            buildButtons[i].layoutXProperty().bind(subPanes[i].widthProperty().subtract(buildButtons[i].widthProperty()).divide(2));
        }
        //konfiguracja zamykajacego pane
        closePane.setPrefHeight(this.getPrefHeight());
        closePane.setPrefWidth(100);
        closePane.relocate(2 * subPanes[1].getPrefWidth(), 0);
        closePane.getChildren().add(closeButton);
        closePane.setStyle(SpeciesColors.ColorCSS[playerNumber]);
        //Kazdy przycisk ma ustawiona inna akcje na metode
        buildButtons[0].setOnAction(this::buildKop);
        buildButtons[1].setOnAction(this::buildFab);

        switch(playerResources[playerNumber].getSpeciesType()){
            case JAVALERZY -> {
                imgines[0].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Javalerzy_textures/Javalerzy_mine_station.png").toURI().toString()));
                imgines[1].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Javalerzy_textures/Javalerzy_war_station.png").toURI().toString()));
            }
            case LUDZIE -> {
                imgines[0].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Ludzie_textures/Ludzie_mine.png").toURI().toString()));
                imgines[1].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Ludzie_textures/Ludzie_war_station.png").toURI().toString()));
            }
            case SZRUNGALE -> {
                imgines[0].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Szrungale_textures/Szrungale_mine.png").toURI().toString()));
                imgines[1].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Szrungale_textures/Szrungale_war_station.png").toURI().toString()));
            }
        }

        this.getChildren().addAll(subPanes[0], subPanes[1], closePane, closeButton);      //dodanie subPanow i przycisku do glownego Pane
        MapController.staticPane.getChildren().add(this);   //dodanie glownego Pane do staticPane tak aby mozna bylo wyswietlic okienko na mapie


        //Centrowanie obrazka stacji na mapie
        imageToUpload.setFitWidth(MapController.TILE_SIZE * MapController.SAS_SCALE);
        imageToUpload.setFitHeight(MapController.TILE_SIZE * MapController.SAS_SCALE);
        imageToUpload.relocate(parentTile.x + SAS_SCALE_POS, parentTile.y + SAS_SCALE_POS);
    }

    public void buildKop(ActionEvent event) {

        //sprawdzenie czy można zbudować budynek + utworzenie
        absBuilding building = playerResources[playerNumber].buyBuilding(TileType.MINE_STATION, new ActualPosition(parentTile.boardX, parentTile.boardY));
        if (building != null) {
            parentTile.setTileType(TileType.MINE_STATION);          //oznaczenie ze obiekt znajduje sie na mapie
            switch (playerResources[playerNumber].getSpeciesType()) {
                case JAVALERZY -> {
                    imageToUpload.setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Javalerzy_textures/Javalerzy_mine_station.png").toURI().toString()));
                }
                case LUDZIE -> {
                    imageToUpload.setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Ludzie_textures/Ludzie_mine.png").toURI().toString()));
                }
                case SZRUNGALE -> {
                    imageToUpload.setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Szrungale_textures/Szrungale_mine.png").toURI().toString()));
                }
            }
            building.imageView = imageToUpload;
            MapController.staticPane.getChildren().add(imageToUpload);      //wyswietlenie nowego obiektu na mapie
        }
        closeWin(event);
    }

    public void buildFab(ActionEvent event){

        //sprawdzenie czy można zbudować budynek + utworzenie
        absBuilding building = playerResources[playerNumber].buyBuilding(TileType.WAR_STATION, new ActualPosition(parentTile.boardX, parentTile.boardY));
        if (building != null) {
            parentTile.setTileType(TileType.WAR_STATION);          //oznaczenie ze obiekt znajduje sie na mapie
            switch (playerResources[playerNumber].getSpeciesType()) {
                case JAVALERZY -> {
                    imageToUpload.setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Javalerzy_textures/Javalerzy_war_station.png").toURI().toString()));
                }
                case LUDZIE -> {
                    imageToUpload.setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Ludzie_textures/Ludzie_war_station.png").toURI().toString()));
                }
                case SZRUNGALE -> {
                    imageToUpload.setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Szrungale_textures/Szrungale_war_station.png").toURI().toString()));
                }
            }
            building.imageView = imageToUpload;
            MapController.staticPane.getChildren().add(imageToUpload);      //wyswietlenie nowego obiektu na mapie
        }
        closeWin(event);
    }


    public void closeWin(ActionEvent event){
        parentTile.active = false;
        parentTile.setStroke(Color.TRANSPARENT);
        MapController.staticPane.getChildren().remove(this);
    }
}

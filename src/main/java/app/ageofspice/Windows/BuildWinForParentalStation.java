package app.ageofspice.Windows;

import app.ageofspice.*;
import app.ageofspice.Species.SpeciesColors;
import app.ageofspice.Species.SpeciesType;
import app.ageofspice.movement.ActualPosition;
import app.ageofspice.units_classes.DestroyerShip;
import app.ageofspice.units_classes.DredShip;
import app.ageofspice.units_classes.ExplorerShip;
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
import static app.ageofspice.MapController.*;

//klasa robocza. Możliwe błędy
//Będę grzebał przy niej jeszcze


public class BuildWinForParentalStation extends Pane{
    public Button closeButton = new Button("Close");
    public Pane closePane = new Pane();
    public Button[] buildButtons = {new Button("Recruit"), new Button("Recruit"), new Button("Recruit"),new Button("Recruit")};
    public ImageView[] imgines = {new ImageView(), new ImageView(), new ImageView(),new ImageView()};
    public Label[] labels = {new Label("Scout"), new Label("Explorer"), new Label("Tank"), new Label("Destroyer")};
    public Pane[] subPanes = {new Pane(), new Pane(), new Pane(),new Pane()};
    public Tile parentTile;
    public ImageView imageToUpload = new ImageView();
    public Pane[][] materialPanes = {   {new Pane(), new Pane(), new Pane(), new Pane()},
                                        {new Pane(), new Pane(), new Pane(), new Pane()},
                                        {new Pane(), new Pane(), new Pane(), new Pane()},
                                        {new Pane(), new Pane(), new Pane(), new Pane()},
    };
    public ImageView[][] materialImageView = {  {new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/spice50.png").toURI().toString())), new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/crystal50.png").toURI().toString())), new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/alga50.png").toURI().toString())), new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/vibranium50.png").toURI().toString()))},
                                                {new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/spice50.png").toURI().toString())), new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/crystal50.png").toURI().toString())), new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/alga50.png").toURI().toString())), new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/vibranium50.png").toURI().toString()))},
                                                {new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/spice50.png").toURI().toString())), new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/crystal50.png").toURI().toString())), new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/alga50.png").toURI().toString())), new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/vibranium50.png").toURI().toString()))},
                                                {new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/spice50.png").toURI().toString())), new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/crystal50.png").toURI().toString())), new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/alga50.png").toURI().toString())), new ImageView(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/vibranium50.png").toURI().toString()))},
    };
    public Label[][] materialLabel = {  {new Label(String.valueOf(ScoutShip.staticBaseCost.przyprawa.quantity)),new Label(String.valueOf(ScoutShip.staticBaseCost.krysztal.quantity)),new Label(String.valueOf(ScoutShip.staticBaseCost.algi.quantity)),new Label(String.valueOf(ScoutShip.staticBaseCost.wibranium.quantity))},
                                        {new Label(String.valueOf(ExplorerShip.staticBaseCost.przyprawa.quantity)),new Label(String.valueOf(ExplorerShip.staticBaseCost.krysztal.quantity)),new Label(String.valueOf(ExplorerShip.staticBaseCost.algi.quantity)),new Label(String.valueOf(ExplorerShip.staticBaseCost.wibranium.quantity))},
                                        {new Label(String.valueOf(DredShip.staticBaseCost.przyprawa.quantity)),new Label(String.valueOf(DredShip.staticBaseCost.krysztal.quantity)),new Label(String.valueOf(DredShip.staticBaseCost.algi.quantity)),new Label(String.valueOf(DredShip.staticBaseCost.wibranium.quantity))},
                                        {new Label(String.valueOf(DestroyerShip.staticBaseCost.przyprawa.quantity)),new Label(String.valueOf(DestroyerShip.staticBaseCost.krysztal.quantity)),new Label(String.valueOf(DestroyerShip.staticBaseCost.algi.quantity)),new Label(String.valueOf(DestroyerShip.staticBaseCost.wibranium.quantity))}
    };

    public BuildWinForParentalStation(){arrayBuildWinForParentalStation.add(this);}

    public void setParentTile(Tile tile){ parentTile = tile; }

    public void makeWin(){

        //konfiguracja glownego Pane okienka
        this.setPrefWidth(900);
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
        closePane.relocate(4 * subPanes[2].getPrefWidth(), 0);
        closePane.getChildren().add(closeButton);
        closePane.setStyle(SpeciesColors.ColorCSS[playerNumber]);
        //Kazdy przycisk ma ustawiona inna akcje na metode
        buildButtons[0].setOnAction(this::buildScout);
        buildButtons[1].setOnAction(this::buildExpl);
        buildButtons[2].setOnAction(this::buildDred);
        buildButtons[3].setOnAction(this::buildDest);

        //Narazie roboczo tylko dla playera nr 0, pozniej nalezy to powiazac z aktualnym
        //graczem w petli np robiac statyczna zmienna gracza i po kazdej iteracji nadpisywac ta zmienna
        switch(playerResources[playerNumber].getSpeciesType()){//speciesChoiceController.player[0].getSpeciesType()){
            //grafika tez narazie roboczo
            case JAVALERZY -> {
                imgines[0].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Javalerzy_textures/Javalerzy_scout_ship.png").toURI().toString()));
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
                imgines[0].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Szrungale_textures/Szrungale_scout.png").toURI().toString()));
                imgines[1].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Szrungale_textures/Szrungale_explorer.png").toURI().toString()));
                imgines[2].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Szrungale_textures/Szrungale_dreadnought.png").toURI().toString()));
                imgines[3].setImage(new Image(new File("src/main/resources/app/ageofspice/arts/Szrungale_textures/Szrungale_destroyer.png").toURI().toString()));

            }
        }

        this.getChildren().addAll(subPanes[0], subPanes[1], subPanes[2], subPanes[3] , closePane, closeButton);      //dodanie subPanow i przycisku do glownego Pane
        MapController.staticPane.getChildren().add(this);   //dodanie glownego Pane do staticPane tak aby mozna bylo wyswietlic okienko na mapie



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

package app.ageofspice;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.GenericDeclaration;
import java.util.Map;

public class SceneController {

    public static Pane shadowPane;
    public static Pane warningPane;
    public static Button exitYesButton;
    public static Button exitNoButton;
    public static Label warningLabel;
    public static boolean flag = false;

    public static void switchToFXML(String FXML) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AgeOfSpiceApp.class.getResource(FXML));

        Scene scene = new Scene(fxmlLoader.load(), AgeOfSpiceApp.SCREEN_WIDTH, AgeOfSpiceApp.SCREEN_HEIGHT);
        scene.getStylesheets().add(String.valueOf(SceneController.class.getResource("style.css")));
        AgeOfSpiceApp.stage.setTitle("Age of Spice");
        AgeOfSpiceApp.stage.setScene(scene);
        AgeOfSpiceApp.stage.getIcons().add(new Image(new File("src/main/resources/app/ageofspice/arts/resources_and_planets/spiceplanet.png").toURI().toString()));
        AgeOfSpiceApp.stage.show();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                if(keyEvent.getCode() == KeyCode.ESCAPE){
                    if(!flag) {
                        flag = true;

                        shadowPane = new Pane();
                        warningPane = new Pane();
                        exitYesButton = new Button("YES");
                        exitNoButton = new Button("NO");
                        warningLabel = new Label("Are  you  sure  you  want  to  exit ?");

                        if(StartController.staticAnchorPane != null) StartController.staticAnchorPane.getChildren().add(shadowPane);
                        if(SpeciesChoiceController.staticAnchorPane != null) SpeciesChoiceController.staticAnchorPane.getChildren().add(shadowPane);
                        if(MapController.staticAnchorPane != null) MapController.staticAnchorPane.getChildren().add(shadowPane);
                        if(EndController.staticAnchorPane != null) EndController.staticAnchorPane.getChildren().add(shadowPane);

                        shadowPane.setPrefHeight(AgeOfSpiceApp.SCREEN_HEIGHT);
                        shadowPane.setPrefWidth(AgeOfSpiceApp.SCREEN_WIDTH);
                        shadowPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.75);");
                        shadowPane.relocate(0, 0);

                        warningPane.setPrefHeight(100);
                        warningPane.setPrefWidth(260);
                        warningPane.relocate(shadowPane.getPrefWidth() / 2 - warningPane.getPrefWidth() / 2, shadowPane.getPrefHeight() / 2 - warningPane.getPrefHeight() / 2);
                        warningPane.setStyle("-fx-border-color: red; -fx-background-color: black; -fx-border-width: 3;");
                        shadowPane.getChildren().add(warningPane);

                        warningLabel.setPrefWidth(warningPane.getPrefWidth() - 20);
                        warningLabel.setTextAlignment(TextAlignment.CENTER);
                        warningLabel.relocate(20, 20);
                        warningLabel.setStyle("-fx-text-fill: red; -fx-font-size: 15;");

                        exitYesButton.relocate(25, 55);
                        exitYesButton.setPrefWidth(80);
                        exitNoButton.relocate(155, 55);
                        exitNoButton.setPrefWidth(80);
                        warningPane.getChildren().addAll(exitNoButton, exitYesButton, warningLabel);

                        exitYesButton.setOnAction((ActionEvent event) -> {
                            if(PlayerFrameController.timer != null)
                                PlayerFrameController.timer.cancel();
                            Platform.exit();
                        });

                        exitNoButton.setOnAction((ActionEvent event) -> closeShadowPane());
                    }
                    else closeShadowPane();
                }
            }
        });
    }

    public static void closeShadowPane(){
        flag = false;
        if(StartController.staticAnchorPane != null) StartController.staticAnchorPane.getChildren().remove(shadowPane);
        if(SpeciesChoiceController.staticAnchorPane != null) SpeciesChoiceController.staticAnchorPane.getChildren().remove(shadowPane);
        if(MapController.staticAnchorPane != null) MapController.staticAnchorPane.getChildren().remove(shadowPane);
        if(EndController.staticAnchorPane != null) EndController.staticAnchorPane.getChildren().remove(shadowPane);
    }
}

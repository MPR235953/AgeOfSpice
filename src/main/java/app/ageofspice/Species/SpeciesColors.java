package app.ageofspice.Species;

import javafx.scene.paint.Color;

//Interface z kolorkami klas
public interface SpeciesColors {
    Color javColor = Color.rgb(0,230,250);
    Color ludColor = Color.rgb(221, 44,0);
    Color szrColor = Color.rgb(233,30,98);

    String[] ColorCSS = {   "-fx-border-color: rgb(0,230,250);" ,
                            "-fx-border-color: rgb(221,44,0);",
                            "-fx-border-color: rgb(233,30,98);" };
}

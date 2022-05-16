package app.ageofspice.Resourcesandcosts;

/**
 * -Podstawowa klasa zawierająca koszty.
 * -Składa sie z kosztów w poszczególnych surowcach. Ułatwi to dokopywanie się
 * do wartości poszczególnych surowców
 */


public class Cost {
    AlgiRes algi;
    SpiceRes przyprawa;
    VibraniumRes wibranium;
    CrystalRes krysztal;
    public Cost(AlgiRes A,SpiceRes P,VibraniumRes W,CrystalRes K){
        algi = A;
        przyprawa = P;
        wibranium = W;
        krysztal = K;
    }

}

package app.ageofspice.Resourcesandcosts;

/**
 * -Podstawowa klasa zawierająca koszty.
 * -Składa sie z kosztów w poszczególnych surowcach. Ułatwi to dokopywanie się
 * do wartości poszczególnych surowców
 */


public class Cost {
    public AlgiRes algi;
    public SpiceRes przyprawa;
    public VibraniumRes wibranium;
    public CrystalRes krysztal;

    public Cost(AlgiRes A,SpiceRes P,VibraniumRes W,CrystalRes K){
        algi = A;
        przyprawa = P;
        wibranium = W;
        krysztal = K;
    }

}

package app.ageofspice.Resourcesandcosts;

public class ResourceStorage {

        public static int BASE_ALGI_GROW = 10;
        public static int BASE_SPICE_GROW = 5;
        public static int BASE_VIBRANIUM_GROW = 10;
        public static int BASE_CRYSTAL_GROW = 10;


        public AlgiRes algi = new AlgiRes(20);
        public SpiceRes przyprawa = new SpiceRes(20);
        public VibraniumRes wibranium = new VibraniumRes(20);
        public CrystalRes krysztal = new CrystalRes(20);



        public void resourcesaction(){
                algi.quantity += BASE_ALGI_GROW;
                przyprawa.quantity +=BASE_SPICE_GROW;
                wibranium.quantity += BASE_VIBRANIUM_GROW;
                krysztal.quantity += BASE_CRYSTAL_GROW;
        }


}

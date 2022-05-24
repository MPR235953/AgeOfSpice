package app.ageofspice.Resourcesandcosts;

public class ResourceStorage {

        public static int BASE_ALGI_GROW = 50;
        public static int BASE_SPICE_GROW = 50;
        public static int BASE_VIBRANIUM_GROW = 50;
        public static int BASE_CRYSTAL_GROW = 50;


        public AlgiRes algi = new AlgiRes(1000);
        public SpiceRes przyprawa = new SpiceRes(1000);
        public VibraniumRes wibranium = new VibraniumRes(1000);
        public CrystalRes krysztal = new CrystalRes(1000);



        public void resourcesaction(){
                algi.quantity += BASE_ALGI_GROW;
                przyprawa.quantity +=BASE_SPICE_GROW;
                wibranium.quantity += BASE_VIBRANIUM_GROW;
                krysztal.quantity += BASE_CRYSTAL_GROW;
        }


}

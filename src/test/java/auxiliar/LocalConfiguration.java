package auxiliar;

public class LocalConfiguration {
    public static class web {
        public final static String navegador = "chrome";
        public final static String extension = "speedTest";

        public final static boolean habilitarPantallaModificable=false;
        public final static int  ancho=1360;
        public final static int alto=768;
        public final static boolean habilitarPantallaModificableAleatoria=false;
        public final static int[][] resoluciones ={{1360,768},{1024,768},{600,768}};
    }

    public static class mobileCapabilities{
        public final static String environment = "Android";
        /**
         * Local device
         */
        public final static String deviceName = "Iphone Angel";
        public final static String udId = "192.168.1.3:5555";
        public final static String platformVersion = "10";
        public final static String appPackage = "com.poqstudio.app.platform.missguided";
        public final static String appActivity = "com.poqstudio.app.platform.presentation.splash.view.SplashActivity";
        public final static String bundleId = "com.poqstudio.missguided";
        public final static String xcodeOrgId = "HURZ6CC95B";

        /**
         * Kobiton device
         */
        public final static String kobitonServerUrl = "https://angeltorre:e56548c1-274d-4f3e-9528-577872eabda8@api.kobiton.com/wd/hub";
        public final static String sessionName = "Automation test session";
        public final static String sessionDescription = "Description";
        public final static boolean captureScreenshots = false;
        public final static String app = "<APP_URL> o <App ID example kobiton-store:v223145>";
        public final static int groupId = 513; // Group: CognitsKony
        public final static String deviceGroup = "KOBITON";
        public final static String kobitonDeviceName = "Galaxy A10e";
        public final static String kobitonPlatformVersion = "10";
        public final static String kobitonPlatformName = "Android";
    }
}

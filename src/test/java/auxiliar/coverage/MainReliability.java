package auxiliar.coverage;

import java.util.ArrayList;
import java.util.List;

public class MainReliability {
    public static void main(String[] args) {
        List<String> dataProviders=WordFinder.parser("value","src/test/resources");
        //System.out.println(dataProviders);
        List<String> paquetes=WordFinder.parser("package name","src/test/resources");
        //System.out.println(paquetes);

        List<List<String>> navegadoresPaquetesRead= new ArrayList<>();
        List<String> auxiliar= new ArrayList<>();
        int posicionPaqueteActual=0;
        for (String dataProvider:dataProviders){
            if(dataProvider.contains(".xml")){
                auxiliar= new ArrayList<>();
            }else{
                auxiliar.add(dataProvider);
                while (posicionPaqueteActual<paquetes.size()){
                    String paquete= paquetes.get(posicionPaqueteActual);
                    if(paquete.contains(".xml")){
                        posicionPaqueteActual=posicionPaqueteActual+1;
                        break;
                    }else{
                        auxiliar.add(paquete);
                        navegadoresPaquetesRead.add(auxiliar);
                        auxiliar=new ArrayList<>();
                        auxiliar.add(dataProvider);
                    }
                    posicionPaqueteActual=posicionPaqueteActual+1;
                }
            }
        }
        //System.out.println(navegadoresPaquetesRead);

        Ponderador ponderador=new Ponderador(navegadoresPaquetesRead);
        ponderador.go();
        ponderador.imprimirResultado();
    }
}

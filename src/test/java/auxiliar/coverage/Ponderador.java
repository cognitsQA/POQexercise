package auxiliar.coverage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ponderador {
    String[][] browsersWeight={{"chrome","0.6"},{"android","1.0"},{"firefox","0.4"}};


    List<List<String>> packageWeight;
    List<String> allnamePackages;
    List<String> allPackageWeight;
    List<String> allPackageWeightTotal;
    public Ponderador(List<List<String>> navegadoresXML){
        this.packageWeight =navegadoresXML;
        allnamePackages=new ArrayList<>();
        allPackageWeight=new ArrayList<>();
        allPackageWeightTotal= new ArrayList<>();
    }

    public void go(){
        getWeightAndNames();
    }

    public void getWeightAndNames(){
        for(int i = 0; i< packageWeight.size(); i++){
            for(int j = 0; j< packageWeight.get(i).size(); j++){
                if(j==1){
                    allnamePackages.add(packageWeight.get(i).get(j));
                }else{
                    allPackageWeight.add(calcularValorLista(packageWeight.get(i).get(j).replace("\"","")));
                }
            }
        }
        allnamePackages = allnamePackages.stream().distinct().collect(Collectors.toList());
        sumarValorLista();
    }
    public String calcularValorLista(String browser){
        String valorCalculado=null;
        for(int i = 0; i< browsersWeight.length; i++){
            if(browser.equals(browsersWeight[i][0])){
                valorCalculado=browsersWeight[i][1];
            }
        }
        return valorCalculado;
    }

    public void imprimirListaWeight(){
        System.out.println("PACKAGE | VALUE |");
        for(int i = 0; i< packageWeight.size(); i++){
            for(int j = 0; j< packageWeight.get(i).size(); j++){
                System.out.print(packageWeight.get(i).get(j)+" | ");
            }
            System.out.println();
        }
    }

    public void sumarValorLista(){
        for(String browserActual:allnamePackages){
            Double total=0.00;
            for(int i = 0; i< packageWeight.size(); i++){
                if(packageWeight.get(i).get(1).equals(browserActual)){
                    total=total+Double.parseDouble(allPackageWeight.get(i));
                }
            }
            allPackageWeightTotal.add((total*100)+"");
        }
    }

    public void imprimirResultado(){
        System.out.println(allnamePackages);
        System.out.println(allPackageWeightTotal);
    }
}

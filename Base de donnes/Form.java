package affiche;
import java.io.*;
import relation.*;
public class Form{
    public void affiche(Table valiny) throws IOException{
        System.out.println(valiny.getAttribute().get(0)[4]+"----"+valiny.getAttribute().get(0)[1]+"----"+valiny.getAttribute().get(0)[2]);
         for(int y = 0; y<valiny.getDonnes().size(); y++){
            System.out.println(valiny.getDonnes().get(y)[0]+"----"+valiny.getDonnes().get(y)[1]+"----"+valiny.getDonnes().get(y)[2]);
         }
    }
    public void affiches(Table valiny) throws IOException{
        String val = valiny.getAttribute().get(0)[0];
        System.out.println(val);
        for(int i =1; i< valiny.getAttribute().get(0).length;i++){
            val +="----"+valiny.getAttribute().get(0)[i];
        }
        System.out.println(val);
        for(int y = 0; y<valiny.getDonnes().size(); y++){
             String valu = valiny.getDonnes().get(y)[0];
           
            for(int u =1; u<valiny.getDonnes().get(y).length; u++){
                valu += "------"+  valiny.getDonnes().get(y)[u];
            }
            System.out.println( valu);
        }
    }
}
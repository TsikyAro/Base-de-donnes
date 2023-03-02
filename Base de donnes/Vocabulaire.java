package affiche;
import relation.*;
import java.io.*;
public class Vocabulaire{
    public String[] splite(String requete){
        String [] valiny = requete.split(" ",0);
        return valiny;    
    }

    public void select(String requete){
        Base relat = new Base();
        try{
            Table valiny = relat.SelectAll(requete);
            Form form = new Form();
            form.affiche(valiny);
        }catch(IOException io){ 
        }
    }

    public void manakambana(String requete){
        String[] splitee = splite(requete);
        Fonction f = new Fonction();
        Base relat = new Base();
        try{
            Table tab1 = relat.SelectAll("Select * from " + splitee[1]);
            Table tab2 = relat.SelectAll("Select * from " + splitee[3]);
             Form form = new Form();
            if(splitee[0].equalsIgnoreCase("Manakambana")){
                f.UNION(tab1,tab2);
                form.affiche(tab1);
            }
        }catch(IOException io){ 
        }
    }

    /*public void MakaColonne(String requete){
        String[] splitee = splite(requete);
        Fonction f = new Fonction();
        try{
            String[] colonne = f.PROJECTION(requete);
            System.out.println("----"+splitee[1]+"------");
            for(int i =0; i<colonne.length ; i++){
                System.out.println("----"+colonne[i]+"------");
            }
        }catch (IOException IO){}
    }*/


    

    public void Fifandraisana(String requete){
        String[] splitee = splite(requete);
        Fonction f = new Fonction();
        Base relat = new Base();
        try{
            Table tab1 = relat.SelectAll("Select * from " + splitee[0]);
            Table tab2 = relat.SelectAll("Select * from " + splitee[2]);
            if(splitee[1].equalsIgnoreCase("inter")){
                f.l_inter(tab1,tab2);   
            }
        }catch (IOException IO){}

    }
    public void TsyMitovy(String requete){
        Table reps;
        String[] splitee = splite(requete);
        Fonction f = new Fonction();
        Form form = new Form();
        Base relat = new Base();
        try{
            Table tab1 = relat.SelectAll("Select * from " + splitee[0]);
            Table tab2 = relat.SelectAll("Select * from " + splitee[2]);
            if(splitee[1].equalsIgnoreCase("difference")){
               reps = f.DIFFERENCE(tab1,tab2); 
               form.affiches(reps);  
            }
        }catch (IOException IO){}
    }
}
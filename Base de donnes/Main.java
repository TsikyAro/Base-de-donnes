package affiche;
import java.io.IOException;
import java.util.ArrayList;
import relation.*;
import java.util.Scanner;
import javax.swing.text.BadLocationException;
public class Main {
    public static void main(String [] args) throws IOException, BadLocationException,Exception{
        // Table 1
        Table tab=new Table();
        tab.SetName("Perso");
// ----------Attribut---------------
        ArrayList<String[]> attributs=new ArrayList<String[]>();
        String [] attr = new String [3];
        attr[0] = "id";
        attr[1] = "nom";
        attr[2] = "Prenom";
        attributs.add(attr);
        tab.SetAttribute(attributs);
// ----------Donnes-----------------
        String [] done = new String [3];
        done[0] = "5";
        done[1] = "Andrianna";
        done[2] = "Mioty";
        ArrayList<String[]> donne=new ArrayList<String[]>();
        donne.add(done);
        tab.SetDonnes(donne);

        // Insert Base
        Base relat=new Base();
        // relat.addTable(tab);
        // relat.addAttribut(tab);
        // relat.addFile(tab);
        Table valiny = relat.SelectAll("SELECT * FROM Perso");
        Form form = new Form();
        Fonction f =new Fonction();
        Table test = relat.SelectAll("SELECT * FROM Rak") ;
        // test.getDonnes().add(done);
        // form.affiches(test);
        Table prod = f.produitCartesien(valiny,test);
        // f.UNION(valiny, tab);
        // form.affiches(prod);
        // f.l_inter(valiny,test);

        Table huhu = f.division(test,valiny);
        Table ko  = f.produitCartesien(huhu, valiny);

            // Table val = f.projection("SELECT ages FROM Rak");
            form.affiches(ko);
            // System.out.println(huhu.getAttribute().get(0).length);
            // form.affiches(val);
       
        /*Scanf */
        // Boolean huhu = true;
        // Vocabulaire voica = new Vocabulaire();
        // while(huhu = true){
        //     System.out.println("");
        //     Scanner sc = new Scanner(System.in);
        //     String requete = sc.nextLine();
        //     // System.out.println(requete);
        //     voica.TsyMitovy(requete);
        // }
    }
}
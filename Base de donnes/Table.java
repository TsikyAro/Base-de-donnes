package relation;
import java.util.ArrayList;
public class Table {
        
    String nom;
    ArrayList<String[]> attributs;
    ArrayList <String[]> donnes;
    public String getName(){
        return nom;
    }
    public void SetName(String name){
        nom=name;
    }
    public ArrayList<String[]> getAttribute(){
        return attributs;
    }
    public void SetAttribute(ArrayList<String []> attr){
        attributs=attr;
    }
    public ArrayList<String[]> getDonnes(){
        return donnes;
    }
    public void SetDonnes(ArrayList<String[]> donnese){
        donnes=donnese;
    }
}
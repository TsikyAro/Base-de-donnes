package relation;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
public class Fonction{
    /*---------UNION----------*/
    public  void UNION(Table tab1, Table tab2){
        ArrayList<String[]> v =new ArrayList<String[]>();
        for(int i=0; i< tab1.getDonnes().size(); i++){
            v.add(tab1.getDonnes().get(i));
            v.add(tab2.getDonnes().get(i));
        }

        tab1.SetDonnes(v);
    }
    /*---------PROJECTION-------*/
    public String[] PROJECTION(String sql,Table table) throws IOException{
       ArrayList<String[]> liste = new ArrayList<String[]> ();
       Base b = new Base();
        int colonne = 0;
        if(table.getAttribute().size()==0){
            String[] attributes = b.getAttributes(table);
            for(int i = 0; i<attributes.length; i++){
                if(sql.equalsIgnoreCase(attributes[i])){
                    liste = b.detailDonness(table);
                    colonne = i;
                }
            }
        }else{
            String[] attributes = b.getAttributes(table);
            for(int i = 0; i<attributes.length; i++){
                if(sql.equalsIgnoreCase(attributes[i])){
                    liste = table.getDonnes();
                    colonne = i;
                }
            }
        }
       String [] valiny = new String [liste.size()];
            for(int o = 0; o<liste.size(); o++){
                    valiny[o] = new String();
                    valiny[o] = liste.get(o)[colonne];
                } 
        
        return valiny;
        
    }
    public Table projection(String sql,Table tab) {
        Table table = new Table();
        Base b = new Base();
        try{
            // ArrayList<String> requete =b.split(sql);
            table.SetName(tab.getName());
            ArrayList<String[]> dones = new ArrayList<>();
            String [] donnes = PROJECTION(sql,tab);
            for(int i =0; i<donnes.length; i++){
                String [] be= new String[1];
                be[0] = donnes[i];
                dones.add(be);
            }
            
            String [] attr = new String[1];
            // attr[0] = requete.get(1);
            attr[0] = sql;
            ArrayList<String []> attributs = new ArrayList<>();
            attributs.add(attr);
            table.SetAttribute(attributs);
            table.SetDonnes(dones);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return table;
    }
    public ArrayList<String> compare(Table tab1, Table tab2){
        String reps = null;
        String val1 = new String();
        String val2 = new String();
        ArrayList<String> aray = new ArrayList<String > ();
        if(tab1.getAttribute().get(0).length==tab2.getAttribute().get(0).length){
            for(int i =0 ; i<tab1.getDonnes().size(); i++){
                for(int j = 0; j<tab1.getDonnes().get(0).length; j++){
                val1 += ","+ tab1.getDonnes().get(i)[j];
                val2 +=","+ tab2.getDonnes().get(i)[j];
                
                }
                if(val1.equalsIgnoreCase(val2)){
                    reps = val1;
                    aray.add(reps);
                }
                    val1 = " ";
                    val2 = " ";
            }
        }
        return aray;
    }
    /*----------INTERSECTION--------------*/
    public void l_inter(Table tab1, Table tab2){
        ArrayList<String> val = compare(tab1,tab2);
        String[] [] co = new String[val.size()][];
        for(int i =0 ; i< val.size(); i++){
            co[i] = val.get(i).split(",");
        }
        for(int j =0 ; j< co.length ; j ++){
            System.out.println(co[j][1]+"--------"+co[j][2]+"----------"+co[j][3]);
        }
    }
    /*---------PRODUIT CARTESIEN----------*/
    public Table produitCartesien(Table tab1, Table tab2){
        Table valiny = new Table();
       valiny.SetName(tab1.getName()+"x"+tab2.getName());
        ArrayList<String[]> attrib1 = tab1.getAttribute();
        ArrayList<String[]> attrib2 = tab2.getAttribute();
        ArrayList<String[]> attr = new ArrayList<String[]>();
        ArrayList<String[]> donnes = new ArrayList<String[]>();
        String[] attribe = new String[attrib1.get(0).length+attrib2.get(0).length];
        /* Attributs */
        int o =0;
        for(int i =0; i< tab1.getAttribute().size(); i++){
            if(!attrib1.get(i)[i].equalsIgnoreCase(null)){
                attribe[o] = attrib1.get(i)[i];
                // attribe[o+1] = attrib2.get(i)[i];
                // System.out.println(attribe[o]);
                o= o+1;
            }
        }
        attr.add(attribe);
        valiny.SetAttribute(attr);
        System.out.println(attr.get(0)[0]+"uiui");

        /*Donnes*/
        String[] done = new String[tab1.getDonnes().get(0).length+tab2.getDonnes().get(0).length];
        int u=0;
        int g =done.length;
        for(int i =0 ; i<tab1.getDonnes().size(); i++){
            u=0;
            for(int j =0; j<3; j++ ){
                if(u<g){
                    if(!tab2.getDonnes().get(i)[j].equalsIgnoreCase(null)){
                        // done[u] = tab1.getDonnes().get(i)[j];
                        done[u+1] = tab2.getDonnes().get(i)[j];
                        u = u+2;
                    }
                }
            
            }
            donnes.add(done);
        }
        valiny.SetDonnes(donnes);

        return valiny;
    }
    /*---------DIFFERENCE-----------------*/
    public Table DIFFERENCE(Table tab1, Table tab2){
        String reps = null;
        String val1 = new String();
        String val2 = new String();
        if(tab1.getAttribute().get(0).length==tab2.getAttribute().get(0).length){
            for(int i =0 ; i<tab1.getDonnes().size(); i++){
                for(int j = 0; j<tab1.getDonnes().get(0).length; j++){
                val1 += ","+ tab1.getDonnes().get(i)[j];
                val2 +=","+ tab2.getDonnes().get(i)[j];
                
                }
                if(val1.equalsIgnoreCase(val2)){
                    tab1.getDonnes().remove(i);
                    tab2.getDonnes().remove(i);
                }
                    val1 = " ";
                    val2 = " ";
            }
              UNION(tab1,tab2);  
        }
        return tab1;
    }
    /*Valeur absolue*/
    public int abs(int i ){
        int result = i;
        if(i < 0){
            result = i*(-1);
        }
        return result;
    }

    /* difference entre colonne */
    public String[] differenceColonne(Table tab1, Table tab2) throws Exception{
        Base base = new Base();
        String [] attrib1 = base.getAttributes(tab1);
        String [] attrib2 = base.getAttributes(tab2);
        // Arrays.sort(b);
        int taille = abs(attrib1.length - attrib2.length);
        String [] reste = new String[ taille ] ;
        int indiceReste =0 ;
        for (int i = 0; i < attrib1.length; i++) {
            int var = Arrays.binarySearch(attrib2,attrib1[i]);
            if(var<0){
                reste[indiceReste] = attrib1[i] ;
                indiceReste++;
            }
        }
        return reste ;
    }
    /*------------DIVISION----------------*/
    /*  Otranzao le anaovana anle division
    *   public Relation division (Relation r) throws Exception{
    *   String [] c1 = this.differenceBetweenColumn(r); alaina aloha le tsy mampitovy anle table anakiroa
    *   Relation t1 = this.projection(c1); de avy eo atsofoka anaty table ray le izy
    *   Relation t2 = ((r.produitCartesien(t1)).difference(this)).projection(c1); de ty otrany code kalalao be tsy azoko
    *   return t1.difference(t2) ;
    }*/
    public Table division(Table tab1,Table tab2)throws Exception{
        Table table ,t1, t2,cart = new Table();
        t1 = null;
        String [] diffTable = this.differenceColonne(tab1,tab2); //izay ao anaty tab1 ka tsy ao anaty tab2 (le tab2 zany no tsy feno)
        for(int i =0; i< diffTable.length; i++){
            // System.out.println(diffTable[0]);
            t1 = this.projection(diffTable[i],tab1);
            
            // cart = (produitCartesien(tab2,t1)); //atambatra amle tab2 le collone ray niotra t@le tab1
            // Table dif = DIFFERENCE(cart,tab1); 
            // t2= projection(diffTable[i],dif);
            // t2 = ((produitCartesien(tab1,tab2)).DIFFERENCE(tab1,tab2)).projection(diffTable[i]);
        }
        return t1;
    }
   
}
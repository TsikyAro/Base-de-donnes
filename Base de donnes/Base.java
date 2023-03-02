package relation;
import java.io.*;
import javax.swing.text.BadLocationException;
import java.util.ArrayList;
public class Base{
    // INSERTION TABLE
    public void addTable( Table texte) throws FileNotFoundException, IOException{
        File fichier=new File("Table"+".txt");
        FileOutputStream file=new FileOutputStream(fichier,true);
        file.write(texte.getName().getBytes());
        String mizara=",";
        file.write(mizara.getBytes());
        file.close();
    }
    public String[] getTable() throws FileNotFoundException, IOException{
        File file = new File("Table"+".txt");
        BufferedReader read=new BufferedReader(new FileReader(file));
        String akana;
        while((akana=read.readLine())!=null){
            String[] vobject=akana.split(",",0);
            return vobject;
        }
        return new String[0];
    }
       
    public String get(String nom) throws IOException{
        String[] nomTable=getTable();
        String val = null;
        for(int i=0; i<nomTable.length; i++ ){
            if(nomTable[i].equalsIgnoreCase(nom)){
                val = nom;
            } 
            
        }
        return val;
    }
     public boolean checkTable(String nom) throws IOException{
        String[] nomTable=getTable();
        for(int i=0; i<nomTable.length; i++ ){
            if(nomTable[i].equalsIgnoreCase(nom)){
                return true;
            } 
            
        }
        return false;
        
    }

    //INSERT ATTRIBUT
      public void addAttribut(Table table)throws IOException{
        File e = new File(table.getName()+"attribut.txt");
        FileOutputStream file=new FileOutputStream(e,true);
        boolean tableExiste=this.checkTable(table.getName());
        if(tableExiste==true){
            for(int i=0;i<table.getAttribute().size();i++){        
                file.write(table.getAttribute().get(i)[i].getBytes());
                String sep=";;";
                file.write(sep.getBytes());
            }
        }else{
            System.out.println("Table inexistante");
        }
        file.close();
    }
    public String[] getAttributes(Table table)throws IOException{
        // System.out.println(table.getName());
        File file = new File(table.getName()+"attribut.txt");
        BufferedReader isp=new BufferedReader(new FileReader(file));
        String d;
        String[] vobject=null;
        while((d=isp.readLine())!=null){
            vobject=d.split(";;",0);   
        }
        isp.close();
        return vobject;
    }
    public int checkAttribut(Table table)throws IOException{
        String[]alltab=getAttributes(table);
        int allchecked=alltab.length;
        for(int i=0; i<alltab.length; i++){
            
            if(alltab[i].equalsIgnoreCase(table.getAttribute().get(i)[i])){
                allchecked=allchecked-1;    
            }
        }
        return allchecked;
    }

    // INSERT DONNES
       public void addFile(Table table) throws IOException, BadLocationException{
        File e = new File(table.getName()+".txt");
        FileOutputStream file=new FileOutputStream(e,true);
        for(int i=0;i<table.getDonnes().size();i++){
            for(int j=0;j<table.getDonnes().get(0).length;j++){
                    file.write(table.getDonnes().get(i)[j].getBytes());
                    String sep=";";
                    file.write(sep.getBytes());
            }
            String separateur="/";
            file.write(separateur.getBytes());
        }
        file.close();
    }
    public String[] readFile(Table table) throws IOException{
        File file = new File(table.getName()+".txt");
        if(file == null){
            System.out.println("Error 4: Relation innexistante");
            return null;
        }
        BufferedReader read=new BufferedReader(new FileReader(file));
        
        String mamaky;
        while((mamaky=read.readLine())!=null){
            String[] object=mamaky.split("/",0);
            return object;
        }
        return new String[0];
    }
    public ArrayList<String []> detailDonness(Table table)throws IOException{
       String[] donnees=readFile(table);
       String [] detail= null;
       ArrayList<String []> det = new ArrayList<> ();
       for(int i=0; i<donnees.length; i++){
            detail=donnees[i].split(";",0);
            det.add(detail);
       }  
       return det;

    }
    // SELECT ------
     public ArrayList<String>split(String requete){
        ArrayList<String>split=new ArrayList<String>();
        String[]req=requete.split(" ",0);
        for(int i=0; i<req.length; i++){
            split.add(req[i]);
        }
        return split;
    }
    //  --------------------SELECT FITAMBARANY-----------------------------
    public Table SelectAll(String sql) throws IOException{
        Table table=new Table();
        ArrayList<String> requete = split(sql);
        int colonne;
        String nomTabl=get(requete.get(3));
        table.SetName(nomTabl);
        String[]attributes = null;
        ArrayList<String []>a = new ArrayList<String []>();
        
        if(requete.get(1).equalsIgnoreCase("*")){
            attributes=getAttributes(table);
        }
        for(int i=0; i<attributes.length; i++){
            a.add(attributes);
        }        
        ArrayList<String []>donnees=detailDonness(table);
        table.SetDonnes(donnees);
        table.SetAttribute(a);
        return table;
        
    }


}
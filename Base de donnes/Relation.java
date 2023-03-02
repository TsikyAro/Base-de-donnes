// package relation;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Collections;

// public class Relation {
//     String name ;
//     String [] columnName ;
//     ArrayList <String [] > values ;
//     int lineSize ;
//     int columnSize;
//     public Relation (String nom , String [] column ){
//         setColumnName(column);
//         setColumnSize(column.length);
//         setName(nom);        
//         values =  new ArrayList<String []>(); 
//     }
//             // ny operations rehetra traitena eto efa tokony mandalo traitement syntaxique manokana daolo
//             // tsy asiana exception intsony 
//     public void add(String [] elements){
//         values.add(elements);
//         lineSize++;
//     }
// //-------------------------------------------------------------------
//     /*
//      *  fonction union :
//      * testena alony hoe azo atao ve ny UNION -> mitovy ny isan'ny column sns
//      * inserer_na anaty Relation vaovao ny donnnes rehetra anatinle relation hafa (au moins 2)
//      * trier_na : MERGESORT (Stable) : timeComplexity : nlog(n)
//      * esorina ny doublon amn le avy trier 
//      * vita ilay relation vaovao
//      */
//     public Relation union(Relation[] other){ 
//             Relation newRelation = new Relation(name, columnName.clone());
//             newRelation.values = copyAllValues(other); // manao anle copy de donnees ;
//             // manala doublon : tri de aveo manala anle doublon 
//             removeAllDuplicateValues(newRelation);
//             return newRelation ;
//     }

//     public Relation projection(String [] colmns){
//         Relation newRelation = new Relation(name, colmns.clone());
//         int sizecol = colmns.length ;
//         int [] indice = new int[sizecol] ;
//         for (int i = 0; i <sizecol ; i++) {
//             for (int j = 0; j < columnSize  ; j++) {
//                 if ( columnName [j].equalsIgnoreCase(colmns[i])){
//                     indice [i]= j ; 
//                     break ;
//                 }
//             }
//         }        
//         for (int i = 0; i < lineSize; i++){
//             String[] adds = new String [sizecol] ;
//             for (int j = 0; j <sizecol; j++) adds [j] = values.get(i)[indice [j]] ; 
//             newRelation.add(adds);
//         }        
//         removeAllDuplicateValues(newRelation);
//         return newRelation;
//     }

//     /*
//      * ampidirina daholo lony datas rehetra sady esorina ihany ny duplicate isaky nyy relation sao fausse ny resultat
//      * 
//      * avy eo trier_na 
//      * izay doublon no OK
//      * ---------------------------------
//      * 
//      *  raha mampiasa anle difference iny indray de :
//      *      public Relation intersection(Relation  r) throws Exception{
//      *         Relation one = this.difference(r);
//      *          return this.difference(one);
//      *      }
//      *          tsy maharesy anle manaraka eto :
//      */


//     public Relation intersection(Relation[] r){
//         Relation newRelation = new Relation(name, columnName.clone());
//         ArrayList<String []> copy = copyAllValues();
//         newRelation.values = copy ;
//         removeAllDuplicateValues(newRelation);
//         for (int i = 0; i < r.length; i++) {
//             removeAllDuplicateValues(r[i]);
//         }
//         newRelation.values = newRelation.copyAllValues(r) ; 
//         keepDuplicateValues(newRelation, r.length);
//         return newRelation ;
//     }


//     /*
//      *  fonction difference
//      *  esorina ao anatinle mi appelle izay ao anatinle r 
//      *  trier_na izy anakiroa 
//      *  tetezina (satria le izy efa trier de mihena be ny fijerevana)  
//      */
//     public Relation difference(Relation r) throws Exception{
//         Relation newRelation = new Relation(name, columnName.clone()) ;
//         ArrayList<String[]> ato = copyAllValues();
//         ArrayList<String[]> ivelany = copyAllValuesStatic(new Relation []{r});
//         Collections.sort(ato, new Relation_Values_Comparator());
//         Collections.sort(ivelany , new Relation_Values_Comparator());
//         int pointerAto =0 ,pointerIve =0 ,  temp =0 ;
//         while ( pointerAto<  ato.size() && pointerIve <ivelany.size() ){
//             temp = binarySearch(ato, ivelany.get(pointerIve), pointerAto,  ato.size());
//             if(temp >=0){
//                 ato.remove(temp) ; ivelany.remove(pointerIve) ;
//             }else{
//                 pointerAto = -1* temp -1 ;
//                 pointerIve++ ;
//                 if(pointerAto>=  ato.size() || pointerIve >=ivelany.size() ) break ;
//                 temp = binarySearch(ivelany, ato.get(pointerAto), pointerIve, ivelany.size());
//                 if(temp >0){
//                     ato.remove(pointerAto) ; ivelany.remove(temp) ;
//                 }else{
//                     pointerIve = -1* temp -1 ;
//                     pointerAto++ ;
//                 }
//             }
//         }
//         newRelation.values = ato ;
//         newRelation.lineSize = ato.size(); 
//         return newRelation;
//     }

//     public Relation produitCartesien (Relation r){
//         Relation newRelation= new Relation(r.name + name, addColumnsName(r.columnName)) ;
        
//         for (int i = 0; i < lineSize; i++) {
//             for (int j = 0; j < r.lineSize; j++) {
//                 String [] contenus = new String [newRelation.columnSize];
//                 for (int k = 0; k < columnSize; k++) {
//                     contenus[k] = values.get(i)[k];
//                 }
//                 for (int k2 = 0; k2 < r.columnSize; k2++) {
//                     contenus[k2+columnSize]= r.values.get(j)[k2];
//                 }
//                 newRelation.add(contenus);
//             }
//         }
//         return newRelation ;
//     }
//     public Relation division (Relation r) throws Exception{
//         String [] c1 = this.differenceBetweenColumn(r);
//         Relation t1 = this.projection(c1);
//         Relation t2 = ((r.produitCartesien(t1)).difference(this)).projection(c1);
//         return t1.difference(t2) ;
//     }

// //-------------------------------------------------------------------------------------------------------------------------
//     public Relation selection_equals_operation (Integer [] indices , String [] valeurs ) throws Exception{
//         Relation newRelation = new Relation(name, columnName.clone());
//         newRelation.values = copyAllValues();
//         Collections.sort(newRelation.values , new Relation_Values_Comparator(indices));        
//         int pointer= binarySearch(newRelation.values, valeurs, 0, newRelation.values.size(), indices);
//         ArrayList<String []> news = new ArrayList<String []>();
//         if(pointer>=0){ 
//             news.add(newRelation.values.get(pointer));
//             pointer+=1;
//             try {
//                 while (compare(newRelation.values.get(pointer), valeurs, indices)==0){
//                     news.add(newRelation.values.get(pointer));
//                     pointer+=1;
//                 }
//             } catch (java.lang.IndexOutOfBoundsException e){};//do nothing
//         }
//         newRelation.values = news ;

//         return newRelation ;
//     }

//     public Relation selection_sup_operation(Integer indices , String valeurs)throws Exception{
//         Relation newRelation = new Relation(name, columnName.clone());
//         newRelation.values = copyAllValues();
//         Collections.sort(newRelation.values , new Relation_Values_Comparator(indices));
//         int pointer= binarySearch(newRelation.values, valeurs, 0, newRelation.values.size(), indices);
//         ArrayList<String []> news = new ArrayList<String []>();
//         if(pointer>=0){
//             try {
//                 pointer+=1;
//                 while( news.add(newRelation.values.get(pointer))){
//                     pointer+=1;
//                 }
//             } catch (java.lang.IndexOutOfBoundsException e) {}; // doo nothing
//         }else{
//             pointer= -1*(pointer) -1;
//             try {
//                 while( news.add(newRelation.values.get(pointer))){
//                     pointer+=1;
//                 }
//             } catch (java.lang.IndexOutOfBoundsException e) {}; // do nothing
//         }
//         newRelation.values = news ;
//         return newRelation ;
//     }

//     public Relation selection_inf_operation(Integer indices , String valeurs)throws Exception{
//         Relation newRelation = new Relation(name, columnName.clone());
//         newRelation.values = copyAllValues();
//         Collections.sort(newRelation.values , new Relation_Values_Comparator(indices));
//         int pointer= binarySearch(newRelation.values, valeurs, 0, newRelation.values.size(), indices);
//         ArrayList<String []> news = new ArrayList<String []>();
//         if(pointer>=0){ 
//             try {
//                 pointer-=1;
//                 while( news.add(newRelation.values.get(pointer))){
//                     pointer-=1;
//                 }
//             } catch (java.lang.IndexOutOfBoundsException e) {}; // do nothing
//         }else{
//             pointer= -1*(pointer) -1;
//             try {
//                 while( news.add(newRelation.values.get(pointer))){
//                     pointer-=1;
//                 }
//             } catch (java.lang.IndexOutOfBoundsException e) {}; // do nothing
//         }
//         newRelation.values = news ;
//         return newRelation ;
//     }


//     String [] differenceBetweenColumn(Relation r){
//         String [] a = columnName.clone() ;
//         String [] b = r.columnName.clone();
//         Arrays.sort(b);
//         String [] reste = new String[ a.length - b.length ] ;
//         int indiceReste =0 ;
//         for (int i = 0; i < a.length; i++) {
//             int var = Arrays.binarySearch(b,a[i]);
//             if(var<0){
//                 reste[indiceReste] = a[i] ;
//                 indiceReste++;
//             }
//         }
//         return reste ;
//     }

//     String [] addColumnsName(String [] added){
//         String[] rep = new String [ added.length + columnSize ];
//         for (int i = 0; i < columnSize; i++) {
//             rep[i] = columnName[i] ;
//         }
//         for (int i = 0; i < rep.length-columnSize; i++) {
//             rep[i+columnSize]= added[i];
//         }
//         return rep ;
//     }
//     ArrayList<String[]> copyAllValues(){
//         ArrayList<String[]> copy = new ArrayList<String[]>() ;
//         for (int i = 0; i < values.size(); i++){
//                 copy.add( values.get(i).clone()) ;
//         }
//         return copy;
//     }
//     ArrayList<String[]> copyAllValues(Relation [] relations){
//         ArrayList<String[]> copy = copyAllValues() ;
//         for (int i = 0; i < relations.length; i++) {
//             for (int j = 0; j < relations[i].lineSize; j++) {
//                 copy.add( relations[i].values.get(j).clone() );
//             }
//         }
//         return copy;
//     }
//     static ArrayList<String[]> copyAllValuesStatic(Relation [] relations){
//         ArrayList<String[]> copy = new ArrayList<String []>();
//         for (int i = 0; i < relations.length; i++) {
//             for (int j = 0; j < relations[i].lineSize; j++) {
//                 copy.add( relations[i].values.get(j).clone() );
//             }
//         }
//         return copy;
//     }

//     static void removeAllDuplicateValues( Relation r){
//         Collections.sort(r.values , new Relation_Values_Comparator() ) ;
//         int i =1, finals = r.lineSize ;
//         ArrayList<String [] > values =  r.values ;
//         while(i < finals){
//             if(Arrays.equals(values.get(i-1) , values.get(i))){
//                 values.remove(i-1);
//                 r.lineSize -=1;
//                 finals-=1 ;
//             }
//             else i++ ;
//         }
//     }
//     static void keepDuplicateValues( Relation r , int limitDuplicate){
//         Collections.sort(r.values, new Relation_Values_Comparator());
//         int i = 1 , finals = r.values.size() , best =0 ; 
//         ArrayList <String [] > values = new ArrayList<String []>();
//         while(i<finals ){
//             if( !(Arrays.equals(r.values.get(i-1) , r.values.get(i)))) i++ ;
//             else{
//                 i+=1 ;best =1 ;
//                 for (int j = 1; j < limitDuplicate; j++) {
//                     if((Arrays.equals(r.values.get(i-1) , r.values.get(i)))){
//                         i++ ; best++;
//                     }
//                     else break ;
//                 }
//                 if(best==limitDuplicate){
//                     values.add(r.values.get(i-1)) ; // mila feno amnle limit zay vao hoe tena duplicate 
//                     i++ ;
//                 } 
//             }
//         } 
//         r.values =values ;
//         r.lineSize = r.values.size();
//     }
//     public static int binarySearch(ArrayList<String []> a ,String [] key , int fromIndex , int toIndex ) throws Exception{
//         rangeCheck(a.size(), fromIndex, toIndex); // check exception
//         int low = fromIndex;
//         int high = toIndex - 1;
//         while (low <= high) {
//             int mid = (low + high) >>> 1;
//             int cmp = compare(a.get(mid), key) ; 
//             if (cmp < 0)
//                 low = mid + 1;
//             else if (cmp > 0)
//                 high = mid - 1;
//             else
//                 return mid; // key found
//         }
//         return -(low + 1);  // key not found.
//     }
//     // compare
//     static int compare(String[] o1, String[] o2) {
//         String temp1 ="";
//         String temp2 ="" ;
//         for (int i = 0; i < o2.length; i++) {
//             temp1 = temp1 + o1[i];
//             temp2 = temp2 + o2[i];
//         }
//         return temp1.compareToIgnoreCase(temp2);
//     }
//     // version 2
//     public static int binarySearch(ArrayList<String []> a ,String [] key , int fromIndex , int toIndex , Integer[] references) throws Exception{
//         rangeCheck(a.size(), fromIndex, toIndex); // check exception
//         int low = fromIndex;
//         int high = toIndex - 1;
//         while (low <= high) {
//             int mid = (low + high) >>> 1;
//             int cmp = compare(a.get(mid), key , references) ; 
//             if (cmp < 0)
//                 low = mid + 1;
//             else if (cmp > 0)
//                 high = mid - 1;
//             else
//                 return mid; // key found
//         }
//         return -(low + 1);  // key not found.
//     }
//     static int compare (String[] o1, String[] o2 ,Integer[] indices){
//         String temp1 ="";
//         String temp2 ="" ;
//         for (int i = 0; i < indices.length; i++) {
//             temp1 = temp1 + o1[indices[i]];
//             temp2 = temp2 + o2[i];
//         }
//         return temp1.compareToIgnoreCase(temp2);
//     }
//     // version 3 
//     public static int binarySearch(ArrayList<String []> a ,String key , int fromIndex , int toIndex , Integer references) throws Exception{
//         rangeCheck(a.size(), fromIndex, toIndex); // check exception
//         int low = fromIndex;
//         int high = toIndex - 1;
//         while (low <= high) {
//             int mid = (low + high) >>> 1;
//             int cmp = compare(a.get(mid), key , references) ; 
//             if (cmp < 0)
//                 low = mid + 1;
//             else if (cmp > 0)
//                 high = mid - 1;
//             else
//                 return mid; // key found
//         }
//         return -(low + 1);  // key not found.
//     }

//     static int compare (String[] o1, String o2 ,Integer indices){
//         return o1[indices].compareToIgnoreCase(o2);
//     }

//     static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
//         if (fromIndex > toIndex) {
//             throw new IllegalArgumentException(
//                 "fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
//         }
//         if (fromIndex < 0) {
//             throw new ArrayIndexOutOfBoundsException(fromIndex);
//         }
//         if (toIndex > arrayLength) {
//             throw new ArrayIndexOutOfBoundsException(toIndex);
//         }
//     }

// //-----------------------------------------------------------------------------------------------------------------------------------
//     public String getName() {
//         return name;
//     }
//     public void setName(String name) {
//         this.name = name;
//     }
//     public String[] getColumnName() {
//         return columnName;
//     }
//     public void setColumnName(String[] columnName) {
//         this.columnName = columnName;
//     }
//     public ArrayList<String[]> getValues() {
//         return values;
//     }
//     public void setValues(ArrayList<String[]> values) {
//         this.values = values;
//     }
//     public int getLineSize() {
//         return lineSize;
//     }
//     public void setLineSize(int lineSize) {
//         this.lineSize = lineSize;
//     }
//     public int getColumnSize() {
//         return columnSize;
//     }
//     public void setColumnSize(int columnSize) {
//         this.columnSize = columnSize;
//     }
// }

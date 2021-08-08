//import java.util.List;
//import java.util.ArrayList;
//
//
//public class SOULUTION2  implements MyInterface{
//    AVL<ObjectWithCoordinatesImpl> XAvl  = new AVL<>() ;
//    AVL<ObjectWithCoordinatesImpl> YAvl = new AVL<>();
//
//    @Override
//    public void insertDataFromDBFile(String objectName, int objectX, int objectY) {
//        // load data
//        ObjectWithCoordinatesImpl TheCharacter = new ObjectWithCoordinatesImpl(objectName,objectX,objectY);
//        this.XAvl.insert(TheCharacter.getX(),TheCharacter);
//        this.YAvl.insert(TheCharacter.getY(),TheCharacter);
//    }
//
//    private ArrayList<ObjectWithCoordinatesImpl> FindRange(AVLNode<ObjectWithCoordinatesImpl> Node,int a,int b, ArrayList<ObjectWithCoordinatesImpl> Path)
//    { // finds values in range
//        if (Node == null) {
//            return Path; }
//        if (a < Node.getKey()) {
//            FindRange(Node.getLeftChild(),a,b,Path);
//        }
//        if (a <= Node.getKey() && b >= Node.getKey())
//        {Path.add(Node.getData());}
//
//        if (b > Node.getKey()) {
//            FindRange(Node.getRightChild(),a,b,Path);
//        }
//        return Path;
//    }
//
//    private ArrayList<ObjectWithCoordinatesImpl> FindRange2(AVLNode<ObjectWithCoordinatesImpl> Node,int a,int b,int c,int d, ArrayList<ObjectWithCoordinatesImpl> Path)
//    { // finds values in range
//        if (Node == null) {
//            return Path; }
//        if (a < Node.getKey()) {
//            FindRange2(Node.getLeftChild(),a,b,c,d,Path);
//        }
//        if (a <= Node.getKey() && b >= Node.getKey() && c <= Node.getData().getY() && d >= Node.getData().getY())
//        {Path.add(Node.getData());}
//
//        if (b > Node.getKey()) {
//            FindRange2(Node.getRightChild(),a,b,c,d,Path);
//        }
//        return Path;
//    }
//
//    public String[] firstSolution(int leftTopX, int leftTopY, int rightBottomX, int rightBottomY) {
//        String[] Arr = new String[0]; //empty array.
//        ArrayList<ObjectWithCoordinatesImpl> PathRangeX = new ArrayList<>();
//        ArrayList<ObjectWithCoordinatesImpl> PathRangeY = new ArrayList<>();
//        ArrayList<ObjectWithCoordinatesImpl> FindRangeX = FindRange(this.XAvl.getRoot(), leftTopX, rightBottomX, PathRangeX); // finds x in range
//        ArrayList<ObjectWithCoordinatesImpl> FindRangeY = FindRange(this.YAvl.getRoot(), rightBottomY, leftTopY, PathRangeY); // finds y in range
//        if (FindRangeX ==  null || FindRangeY == null) {return Arr;} // in case there is no x or y there is no intersection for sure
//        HashTable Table = new HashTable(Math.max(FindRangeX.size(), FindRangeY.size()));
//        int i =0;
//        List<String> Answer = new ArrayList<>();
//        while (i <= FindRangeX.size()-1){ // add values to Table
//            Table.insert(FindRangeX.get(i));
//            i++;
//        }
//        i=0;
//        while (i <= FindRangeY.size()-1){ // search y path at hash. if found, add it to the solution
//            if (Table.search(FindRangeY.get(i).getX(),FindRangeY.get(i).getY()) != null){ // found point
//                Answer.add(FindRangeY.get(i).toString());
//            }
//            i++;}
//        // convert final answer to regular array
//        String[] FinalAnswer = new String[Answer.size()];
//        for (int j=0; j<=Answer.size()-1; j++){
//            FinalAnswer[j] = Answer.get(j);
//        }
//        return FinalAnswer; }
//
//    public String[] secondSolution(int leftTopX, int leftTopY, int rightBottomX, int rightBottomY) {
//        String[] Arr = new String[0]; //empty array.
//        ArrayList<ObjectWithCoordinatesImpl> PathRangeX = new ArrayList<>();
//        ArrayList<ObjectWithCoordinatesImpl> PathRangeY = new ArrayList<>();
//        ArrayList<ObjectWithCoordinatesImpl> FindRangeX = FindRange(this.XAvl.getRoot(), leftTopX, rightBottomX, PathRangeX); // finds x in range  O(logn)
//        ArrayList<ObjectWithCoordinatesImpl> FindRangeY = FindRange(this.YAvl.getRoot(), rightBottomY, leftTopY, PathRangeY); // finds y in range O(logn)
//        if (FindRangeX ==  null || FindRangeY == null) {return Arr;} // search y path at hash. if found, add it to the solution
//        int Size = Math.max(FindRangeX.size(), FindRangeY.size()); // to decide which range we will insert to the avl tree m1 + m2
//        AVL<ObjectWithCoordinatesImpl> Solution1Avl = new AVL<>();
//        int i = 0;
//        ArrayList<String> Answer2= new ArrayList<>();
//        if (FindRangeX.size() == Size)
//        {
//            while (i<=Size-1){
//                Solution1Avl.insert(FindRangeX.get(i).getX(),FindRangeX.get(i));
//                i++; }
//            i = 0;
//            while (i <= FindRangeY.size()-1){
//                if (Solution1Avl.search(FindRangeY.get(i).getX()) != null){
//                    Answer2.add(FindRangeY.get(i).toString());
//                }
//                i++;
//            }
//        }
//        else
//        {
//            while (i<=Size-1){
//                Solution1Avl.insert(FindRangeY.get(i).getY(),FindRangeY.get(i));
//                i++; }
//            i = 0;
//            while (i <= FindRangeX.size()-1){
//                if (Solution1Avl.search(FindRangeX.get(i).getY()) != null){
//                    Answer2.add(FindRangeX.get(i).toString());
//                }
//                i++;
//            }
//        }
//        String[] FinalAnswer = new String[Answer2.size()];
//        for (int j=0; j<=Answer2.size()-1; j++){
//            FinalAnswer[j] = Answer2.get(j);
//        }
//        return FinalAnswer;
//    }
//}

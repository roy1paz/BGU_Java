import java.util.List;
import java.util.ArrayList;





public class StudentSolution  implements MyInterface{
	AVL<ObjectWithCoordinatesImpl> XAvl  = new AVL<>() ;
	AVL<ObjectWithCoordinatesImpl> YAvl = new AVL<>();

	@Override
	public void insertDataFromDBFile(String objectName, int objectX, int objectY) {
		// load data
		ObjectWithCoordinatesImpl TheCharacter = new ObjectWithCoordinatesImpl(objectName,objectX,objectY);
		this.XAvl.insert(TheCharacter.getX(),TheCharacter);
		this.YAvl.insert(TheCharacter.getY(),TheCharacter);
	}

	private ArrayList<ObjectWithCoordinatesImpl> FindRange(AVLNode<ObjectWithCoordinatesImpl> Node,int a,int b, ArrayList<ObjectWithCoordinatesImpl> Path)
	{ // finds values in range
		if (Node == null) {
			return Path; }
		if (a < Node.getKey()) {
			FindRange(Node.getLeftChild(),a,b,Path);
		}
		if (a <= Node.getKey() && b >= Node.getKey())
		{Path.add(Node.getData());}

		if (b > Node.getKey()) {
			FindRange(Node.getRightChild(),a,b,Path);
		}
		return Path;
	}

	public String[] firstSolution(int leftTopX, int leftTopY, int rightBottomX, int rightBottomY) {
		String[] Arr = new String[0]; //empty array.
		ArrayList<ObjectWithCoordinatesImpl> PathRangeX = new ArrayList<>();
		ArrayList<ObjectWithCoordinatesImpl> PathRangeY = new ArrayList<>();
		ArrayList<ObjectWithCoordinatesImpl> FindRangeX = FindRange(this.XAvl.getRoot(), leftTopX, rightBottomX, PathRangeX); // finds x in range
		ArrayList<ObjectWithCoordinatesImpl> FindRangeY = FindRange(this.YAvl.getRoot(), rightBottomY, leftTopY, PathRangeY); // finds y in range
		if (FindRangeX ==  null || FindRangeY == null) {return Arr;} // in case there is no x or y there is no intersection for sure
		HashTable Table = new HashTable(Math.max(FindRangeX.size(), FindRangeY.size()));
		int i =0;
		List<String> Answer = new ArrayList<>();
		while (i <= FindRangeX.size()-1){ // add values to Table
			Table.insert(FindRangeX.get(i));
			i++;
		}
		i=0;
		while (i <= FindRangeY.size()-1){ // search y path at hash. if found, add it to the solution
			if (Table.search(FindRangeY.get(i).getX(),FindRangeY.get(i).getY()) != null){ // found point
				Answer.add(FindRangeY.get(i).toString());
			}
			i++;}
		// convert final answer to regular array
		String[] FinalAnswer = new String[Answer.size()];
		for (int j=0; j<=Answer.size()-1; j++){
			FinalAnswer[j] = Answer.get(j);
		}
		return FinalAnswer; }

    // binary search by Y in range for second solution
	private ObjectWithCoordinatesImpl binarySearchByY(ObjectWithCoordinatesImpl Point, int left, int right, ArrayList<ObjectWithCoordinatesImpl> Array1) {
		while (left <= right) {
			int mid = (right+left) / 2;
			if (Point.getY() > Array1.get(mid).getY())
			{
				return this.binarySearchByY(Point, mid+1, right,Array1);
			}
			if (Point.getY() <  Array1.get(mid).getY())
			{
				return this.binarySearchByY(Point, left, mid-1,Array1);
			}
			else {
				return Point;
			}
		}
		return null;
	}
    // binary search by X in range for second solution
	private ObjectWithCoordinatesImpl binarySearchByX(ObjectWithCoordinatesImpl Point, int left, int right, ArrayList<ObjectWithCoordinatesImpl> Array1) {
		while (left <= right) {
			int mid = (right+left) / 2;
			if (Point.getX() > Array1.get(mid).getX())
			{
				return this.binarySearchByX(Point, mid+1, right,Array1);
			}
			if (Point.getX() <  Array1.get(mid).getX())
			{
				return this.binarySearchByX(Point, left, mid-1,Array1);
			}
			else {
				return Point;
			}
		}
		return null;
	}

	public String[] secondSolution(int leftTopX, int leftTopY, int rightBottomX, int rightBottomY) {
		String[] Arr = new String[0]; //empty array.
		ArrayList<ObjectWithCoordinatesImpl> PathRangeX = new ArrayList<>();
		ArrayList<ObjectWithCoordinatesImpl> PathRangeY = new ArrayList<>();
		ArrayList<ObjectWithCoordinatesImpl> FindRangeX = FindRange(this.XAvl.getRoot(), leftTopX, rightBottomX, PathRangeX); // finds x in range  O(logn)
		ArrayList<ObjectWithCoordinatesImpl> FindRangeY = FindRange(this.YAvl.getRoot(), rightBottomY, leftTopY, PathRangeY); // finds y in range O(logn)
		if (FindRangeX ==  null ) {return Arr;} // search y path at hash. if found, add it to the solution
		int Size = Math.max(FindRangeX.size(), FindRangeY.size()); // to decide which range we will insert to the avl tree m1 + m2
		ArrayList<ObjectWithCoordinatesImpl> ArrayOfPoints = new ArrayList<>();
		if (FindRangeX.size() == Size)
        // do binary search
        {	int LeftSearch = 0;
			int RightSearch = FindRangeX.size()-1;
			for (int j=0; j<=FindRangeY.size()-1; j++){
                // and find the intersection between X and Y range
                if (binarySearchByX(FindRangeY.get(j),LeftSearch,RightSearch,FindRangeX)!=null){
				ArrayOfPoints.add(FindRangeY.get(j));
			}
			}
		}
		else {
		    // do binary search
            int LeftSearch = 0;
			int RightSearch = FindRangeY.size()-1;
			for (int j=0; j<=FindRangeX.size()-1; j++){
			    // and find the intersection between X and Y range
				if (binarySearchByY(FindRangeX.get(j),LeftSearch,RightSearch,FindRangeY)!=null){
					ArrayOfPoints.add(FindRangeX.get(j));

				}

			}

		}
		// convert from array list into array
		String[] FinalAnswer = new String[ArrayOfPoints.size()];
		for (int j=0; j<=ArrayOfPoints.size()-1; j++){
			FinalAnswer[j] = ArrayOfPoints.get(j).toString();
		}
		return FinalAnswer;
	}
}

import java.util.*;

public class HashTable {

	protected int SizeTable; // size of hash table
	protected LinkedList<ObjectWithCoordinatesImpl>[] HashTable; // array of linked lists

	public HashTable(int SizeTable) {
		this.SizeTable = SizeTable;
		this.HashTable = new  LinkedList[SizeTable]; // initiate it
		int i=0;
		while (i < SizeTable)
		{
			HashTable[i] = new LinkedList<>();
			i+=1;
		}

	}

	public int HashFunc(int X,int Y) {return (X+Y) % SizeTable; }

	public void insert(ObjectWithCoordinates object)
	{
		ObjectWithCoordinatesImpl ThePoint = new ObjectWithCoordinatesImpl(object.getData(),object.getX(),object.getY());
		int PlaceToInsert = HashFunc(object.getX(),object.getY());
		this.HashTable[PlaceToInsert].add(ThePoint);
	}

	public ObjectWithCoordinatesImpl search(int x, int y)
	{
		int FindPlace = HashFunc(x,y);
		LinkedList<ObjectWithCoordinatesImpl> PlaceSearch = this.HashTable[FindPlace];
		int EachPoint = 0;
		while (EachPoint< PlaceSearch.size())
		{
			ObjectWithCoordinatesImpl Pointer = PlaceSearch.get(EachPoint);
			if (x == Pointer.getX() && y == Pointer.getY()){
				return Pointer;
			}
			else
				EachPoint+=1;
		}

		return null;
	}
}


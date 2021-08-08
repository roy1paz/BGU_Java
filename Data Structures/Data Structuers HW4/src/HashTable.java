public class HashTable {
	final double MAX_LOAD_FACTOR = 0.7;
	private HashTableNode[] Cells;
	private int size;
	private int hashFactor;

	public HashTable(){
		hashFactor = 11;
		Cells = new HashTableNode[hashFactor];
		size = 0;
	}

	private void rehash(){
		HashTableNode[] oldTable = Cells;
		Cells = new HashTableNode[hashFactor * 2];
		hashFactor *= 2;
		size = 0;
		for(int i = 0; i < oldTable.length; i++){
			HashTableNode current = oldTable[i];
			while (current != null){
				ObjectWithCoordinates p = current.value;
				insert(p);
				current = current.next;
			}

		}
	}

	public void insert(ObjectWithCoordinates object){
		HashTableNode newNode = new HashTableNode(object, findIndex(object));
		int cellIndex = findIndex(object);
		HashTableNode currentCell = Cells[cellIndex];

		if (currentCell == null) {
			Cells[cellIndex] = newNode;
			size += 1;
		}
		// else, search if already exists
		else {
			ObjectWithCoordinates result = search(object.getX(),object.getY());

			// if exist
			if (result == null) {
				newNode.setNext(Cells[cellIndex]);
				Cells[cellIndex] = newNode;
				size += 1;
			}
			else
				return;
		}
		double crntLoadFactor = (size * 1.0) / hashFactor;
		System.out.println("current load factor: "+ crntLoadFactor +'\n');
		if (crntLoadFactor > MAX_LOAD_FACTOR){
			// Current load factor is too high
			// Rehash
			rehash();
		}

	}

	private int findIndex(ObjectWithCoordinates p){
		int hash = hashFunction(p.getX(), p.getY());
		return hash > 0 ? hash : -1 * hash;
	}

	private int hashFunction(int x, int y){
		return (x + y) % hashFactor;
	}

	public ObjectWithCoordinates search(int x, int y){
		int cellIndex = hashFunction(x,y);
		HashTableNode currentCell = Cells[cellIndex];

		while (currentCell != null){
			if (currentCell.value.getX() == x){
				return currentCell.value;
			}
			currentCell = currentCell.next;
		}
		return null;
	}

	public void delete(int x, int y){
		ObjectWithCoordinates res = search(x, y);
		if(res == null)
			return;
		int i = findIndex(res);
		if(Cells[i].value == res){
			HashTableNode temp = Cells[i];
			Cells[i] = temp.next;
		}
		else{
			HashTableNode last = Cells[i];
			HashTableNode crnt = Cells[i].next;
			while(crnt != null){
				if (crnt.value == res){
					last.setNext(crnt.next);
					return;
				}
				last = last.next;
				crnt = crnt.next;
			}
		}
	}
}


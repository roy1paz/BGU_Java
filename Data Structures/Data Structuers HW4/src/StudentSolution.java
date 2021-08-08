public class StudentSolution implements MyInterface{
	AVL<ObjectWithCoordinatesImpl> treeX;
	AVL<ObjectWithCoordinatesImpl> treeY;

	public StudentSolution(){
		treeX = new AVL<ObjectWithCoordinatesImpl>();
		treeY = new AVL<ObjectWithCoordinatesImpl>();

	}
	@Override
	public void insertDataFromDBFile(String objectName, int objectX, int objectY) {
		ObjectWithCoordinatesImpl node = new ObjectWithCoordinatesImpl(objectName, objectX, objectY);
		treeX.insert(node.x, node); // O(log(n))
		treeY.insert(node.y, node); // O(log(n))
	}

	@Override
	public String[] firstSolution(int leftTopX, int leftTopY, int rightBottomX, int rightBottomY) {
		//
		// [a,b]= [3,12]: [1,2,|4,7,8,9|,13] --> [4,7,8,9]
		HashTableNode resX = new HashTableNode(new ObjectWithCoordinatesImpl(0,0), 0);
		HashTableNode resY;
		HashTableNode[] hashT;
		String[] res;

		AVLNode<ObjectWithCoordinatesImpl> ndX = treeX.root;
		AVLNode<ObjectWithCoordinatesImpl> ndY= treeY.root;

		resX = nodesInRange(resX, ndX, leftTopX, rightBottomX); // O(log(n) + m1)
		int sizeX = resX.key; // count of objects in range X

		resY = nodesInRange(resX, ndY, rightBottomY, leftTopY); // O(log(n) + m2)

		int sizeXY = resY.key;

		//initialize structures
		hashT = new HashTableNode[sizeXY];   // O(m1+m2)
		res = new String[(Math.min(sizeXY - sizeX, sizeX))];            // O(m1)

		int index = 0; // counts duplicates

		HashTableNode crnt = resY.next; // skip the first cell(stores the size)

		while (crnt != null){                // O(m1+m2)
			// Find duplicates in linkedList
			int flag = hash_for_dup(hashT, crnt, 0);
			if(flag > 0){
				// crnt is a duplicate! add to result
				res[index++] = crnt.value.toString();
			}
			crnt = crnt.next;
		}

		String[] newRes;
		if(index < Math.min(sizeXY - sizeX, sizeX)) {
			// if array size is too big
			newRes = new String[index];
			int t = 0;

			for (String i : res)  // without extra cells - O(min(m1,m2))
				if (i != null)
					newRes[t++] = i;
				else
					break;
		} else
			newRes = res;

		return newRes; // min(m1, m2) + m1 + m2 + m1 + m1+ m2 + 2*log(n) + m1 + m2 =  4*m1 + 4*m2 + 2*log(n) = m1 + m2 + log(n)
	}

	private int hash_for_dup(HashTableNode[] hashT, HashTableNode node, int i){
		// return 1 if duplicate else 0

		int password = hashMe(node, i, hashT.length);
		//found a match in hashtable
		if (hashT[password] != null)
			if (hashT[password].value.getX() != node.value.getX()) // Not the same node
				return hash_for_dup(hashT, node, i+1); // Search again with next hash
			else
				return 1; // Found a duplicate
		else
			hashT[password] = node; // Add node to HashTable

		return 0; // No match!
	}

	public int hashMe(HashTableNode nd, int i, int size) {
		return (nd.key + i) % size;
	}

	private HashTableNode nodesInRange(HashTableNode head, AVLNode<ObjectWithCoordinatesImpl> nd, int limitA, int limitB){

		//  |________________________________________|   (|size|--|1|--|2|).add(3)
		//  | || first cell keeps track of count ||  |
		//  |----------------------------------------|   |size|--|3|--|1|--|2|

		if (nd == null)
			return head;

		if (limitA < nd.key)
			head = nodesInRange(head, nd.left, limitA, limitB);

		if (limitA <= nd.key && limitB >= nd.key) {
			// Current node between limits

			// Create a new HT-Node for node in range
			HashTableNode newND = new HashTableNode(nd.data, nd.data.x);

			// New nodes tail is the same as the head's
			newND.setNext(head.next);

			// Heads tail is the new node
			head.setNext(newND);

			// Increase size counter
			head.key++;

			//Print ME
//			HashTableNode tempND = head.next;
//			while (tempND != null){
//				System.out.println("list " + tempND.value.getX());
//				tempND = tempND.next;
//			}
		}
		if(limitB > nd.key)
			head = nodesInRange(head, nd.right, limitA, limitB);

		return head;
	}

	@Override
	public String[] secondSolution(int leftTopX, int leftTopY, int rightBottomX, int rightBottomY) {
		// O(m1 + m2 + log(n) + min(m1, m2) * log(max(m1, m2)))
		HashTableNode resX = new HashTableNode(new ObjectWithCoordinatesImpl(0,0), 0);
		HashTableNode resY = new HashTableNode(new ObjectWithCoordinatesImpl(0,0), 0);;
		HashTableNode[] hashT;
		String[] res;

		AVLNode<ObjectWithCoordinatesImpl> ndX = treeX.root;
		AVLNode<ObjectWithCoordinatesImpl> ndY= treeY.root;

		resX = nodesInRange(resX, ndX, leftTopX, rightBottomX); // O(log(n) + m1)
		resY = nodesInRange(resY, ndY, rightBottomY, leftTopY); // O(log(n) + m2)

		int sizeX = resX.key; // count of objects in range X
		int sizeY = resY.key; // count of objects in range Y

		int maxSize = Math.max(sizeX, sizeY);
		int minSize = Math.min(sizeX, sizeY);

		HashTableNode max_head = sizeX >= sizeY ? resX.next : resY.next;
		HashTableNode min_head = sizeX >= sizeY ? resY.next : resX.next;

		ObjectWithCoordinatesImpl[] max_lis = new ObjectWithCoordinatesImpl[maxSize]; // O(max(m1, m2))

		for (int i=maxSize-1; i>=0; i--){ //  O(max(m1, m2))
			max_lis[i] = (ObjectWithCoordinatesImpl) max_head.value;
			max_head = max_head.next;
		}

		String[] result_arr = new String[minSize]; // min(m1, m2)
		int index = 0;

		while (min_head != null){ // O(min(m1, m2) * log(max(m1, m2)))
			int isDuplicate = bin_search(max_lis, 0, maxSize-1, sizeX >= sizeY ? min_head.value.getX() : min_head.value.getY() , sizeX >= sizeY ? 1 : 0); // O(log(max(m1, m2)))
			if(isDuplicate == 1)
				result_arr[index++] = min_head.value.toString();
			min_head = min_head.next;
		}

		// Print ME
//		HashTableNode tempND = resX.next;
//		while (tempND != null){
//			System.out.println("list second sol " + " " + tempND);
//			tempND = tempND.next;
//		}
		String[] newRes;
		if(index < minSize) {
			// array size is too big
			newRes = new String[index];
			int t = 0;
			for (String i : result_arr) {  // without extra cells - O(min(m1,m2))
				if (i != null)
					newRes[t++] = i;
				else
					break;
			}
		} else
			newRes = result_arr;
		return newRes; // 2*min(m1, m2) + 2*max(m1, m2) + 2*m1 + 2*m2 + log(n) + min(m1, m2)*log(max(m1, m2)) = O(m1 + m2 + log(n) + min(m1, m2) * log(max(m1, m2)))
	}


	private int bin_search(ObjectWithCoordinatesImpl[] arr, int start, int end, int target , int byX){
		while (start <= end){
			int mid = (start + end) / 2;
			int current = byX == 1 ? arr[mid].getX() : arr[mid].getY(); // according to X or Y?
			if(current < target)
				start = mid + 1;
			else if (current == target)
				return 1;
			else
				end = mid - 1;
		}

		return 0;
	}
}

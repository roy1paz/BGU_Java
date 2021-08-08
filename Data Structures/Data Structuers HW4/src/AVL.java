public class AVL<T> {
	AVLNode<T> root;

	public AVL(){
	}

	public AVL(AVLNode<T> root) {
		this.root = root;
	}
	private AVLNode<T> rightRotation(AVLNode<T> k2){
		AVLNode<T> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;

		// update heights
		k2.height = max(height(k2.left), height(k2.right)) + 1;
		k1.height = max(height(k1.left), height(k1.right)) + 1;

		return k1;
	}

	private AVLNode<T> leftRotation(AVLNode<T> k2){
		AVLNode<T> k1 = k2.right;
		k2.right = k1.left;
		k1.left = k2;

		// update heights
		k2.height = max(height(k2.left), height(k2.right)) + 1;
		k1.height = max(height(k1.left), height(k1.right)) + 1;

		return k1;
	}


	private int max(int a, int b){
		return Math.max(a, b);
	}
	private int height(AVLNode<T> node){
		if (node == null)
			return 0;
		return node.height;
	}
	int getBalanceStat(AVLNode<T> node) {
		if (node == null)
			return 0;
		return height(node.left) - height(node.right);
	}


	public void insert(int key){
		AVLNode<T> newNode = new AVLNode<T>(key, null);
		root = recInsert(root, newNode);
	}


	public void insert(int key, T data){
		AVLNode<T> newNode = new AVLNode<T>(key, data);
		root = recInsert(root, newNode);
	}

	private AVLNode<T> recInsert(AVLNode<T> currentNode, AVLNode<T> nodeToAdd) {
		if (currentNode == null)
			return nodeToAdd;

		// does not support identical keys
		if (nodeToAdd.key < currentNode.key) {
			currentNode.left = recInsert(currentNode.left, nodeToAdd);
		}
		else if  (nodeToAdd.key > currentNode.key) {
			currentNode.right = recInsert(currentNode.right, nodeToAdd);
		}
		else {
			return currentNode;
		}

		// update height
		currentNode.height = 1 + max(height(currentNode.left),height(currentNode.right));

		//
		int balanceStat = getBalanceStat(currentNode);

		//four cases
		// left left
		if (balanceStat > 1 && nodeToAdd.key < currentNode.left.key)
			return rightRotation(currentNode);

		// Right Right Case
		if (balanceStat < -1 && nodeToAdd.key > currentNode.right.key)
			return leftRotation(currentNode);

		// Left Right Case
		if (balanceStat > 1 && nodeToAdd.key > currentNode.left.key) {
			currentNode.left = leftRotation(currentNode.left);
			return rightRotation(currentNode);
		}

		// Right Left Case
		if (balanceStat < -1 && nodeToAdd.key < currentNode.right.key) {
			currentNode.right = rightRotation(currentNode.right);
			return leftRotation(currentNode);
		}

		return currentNode;
	}

	public T search(int key){
		AVLNode<T> res = recSearch(root, key);
		if (res != null) {
			return res.data;
		}
		return null;
	}

	private AVLNode<T> recSearch(AVLNode<T> nd, int key){
		if (nd == null)
			return null;
		if(nd.key == key)
			return nd;
		if (key < nd.key)
			return recSearch(nd.left, key);
		return recSearch(nd.right, key);
	}

	public AVLNode<T> getRoot(){
		return root;
	}

}


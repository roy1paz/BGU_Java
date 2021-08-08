public class AVLNode<T> {

	int key;
	T data;
	AVLNode<T> p;
	int height;
	AVLNode<T> left, right;



	public AVLNode(int key, T value){
		this.key = key;
		this.data = value;
		p = null;
		height = 1;
	}

	public AVLNode<T> getLeftChild(){
		return left;
	}

	public AVLNode<T> getRightChild(){
		return right;
	}

	public AVLNode<T> getFather(){
		return p;
	}

	public int getKey(){
		return key;
	}

	public T getData(){
		return (T)data;
	}

	@Override
	public String toString() {

		String s = "";

		if (getLeftChild() != null){
			s+="(";
			s+=getLeftChild().toString();
			s+=")";
		}
		s+=getKey();

		if (getRightChild() != null){
			s+="(";
			s+=getRightChild().toString();
			s+=")";
		}

		return s;
	}
}


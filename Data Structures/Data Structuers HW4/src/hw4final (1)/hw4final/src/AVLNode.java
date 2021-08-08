public class AVLNode<T> {
	protected int key;
	protected T data;
	protected AVLNode<T> leftchild;
	protected AVLNode<T> rightchild;
	protected AVLNode<T> parent;
	protected int height;

	public AVLNode(int key, T data){
		this.key = key;
		this.data = data;
		leftchild=null;
		rightchild=null;
		parent=null;
		height=0;

	}

	public AVLNode<T> getLeftChild(){
			return this.leftchild;

	}
	
	public AVLNode<T> getRightChild(){
			return this.rightchild;
	}
	
	public AVLNode<T> getFather(){
		return this.parent;
	}
	
	public int getKey(){
			return this.key;
	}
	
	public T getData(){
			return this.data;
	}

	public int getHeight(){
		return this.height;
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


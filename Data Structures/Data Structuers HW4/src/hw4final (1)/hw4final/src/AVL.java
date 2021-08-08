//import Math;
public class AVL<T> {
    private AVLNode<T> rootNode;

    public AVL() {
        rootNode = null;
    }

    /* get Max height of left and right node */
    public int getBalance(AVLNode<T> node) {
        if (node == null)
            return 0;
        return ((checkNullReturnHeight(node.getLeftChild()) - checkNullReturnHeight(node.getRightChild())));

    }

    public void updateHeight(AVLNode<T> node){
        node.height = 1 + MaxHeight((node.getLeftChild().height), (node.getRightChild().height));

    }

    public int checkNullReturnHeight(AVLNode<T> node)
    {
        if (node == null)
            return -1;
        return node.height;

    }


    /* insert to avl tree recursive function */
    public AVLNode<T> RecInsert(AVLNode<T> node, int key, T data) {
        if (node.getLeftChild() == null && node.getKey() > key) {
            AVLNode<T> NewNode = new AVLNode<T>(key, data);
            node.leftchild = NewNode;
            node.height++;
            NewNode.parent = node;
            return NewNode;
        } else if (node.getRightChild() == null && node.getKey() < key) {
            AVLNode<T> NewNode = new AVLNode<T>(key, data);
            node.rightchild = NewNode;
            NewNode.parent = node;
            node.height++;
            return NewNode; }
        else if (node.getKey() < key)
        {
            node.height++;
            return RecInsert(node.getRightChild(), key, data);
        }
        else //if (node.getKey() > key){
            node.height++;
        return RecInsert(node.getLeftChild(), key, data);
    }



    /* insert to avl tree*/
    public void insert(int key, T data) {
        AVLNode<T> node = new AVLNode<T>(key, data);
        if (this.getRoot() == null)
            this.rootNode = node;
        else {
            node = RecInsert(this.rootNode, key, data);
        }
        int countleft = 0, countright = 0;
        // while there is balance keep going up to root till there is no balance
        while (node != this.rootNode && (Math.abs(getBalance(node.getFather())) >= 1)) {
            if (node.getFather().leftchild == node)
                countleft++;
            else
                countright++;
            node = node.getFather();
        }

        if (!(getBalance(node) < 1 && getBalance(node) > -1)){
            // Rotate for all options left-left , left-right, right-right, right-left
            //left left
            if (getBalance(node) > 1 && node.key > node.getLeftChild().getKey() && countleft > 0 && countright == 0)
                RotainRightChild(node);

            //right right
            if (getBalance(node) < -1 && node.key < node.getRightChild().getKey())
                RotainLeftChild(node);

            // right left
            if (getBalance(node) < -1 && node.key > node.getRightChild().getKey())
                RotainRightLeftChild(node);

            //left right
            if (getBalance(node) > 1 && node.key > node.getLeftChild().getKey() && countleft > 0 && countright > 0)
                RotainLeftRightChild(node);


        }
    }


    /*binary search */
    public T search(int key){
        if(this.getRoot()== null)
            return null;
        if(this.getRoot().key == key)
            return this.getRoot().data;
        return rec_search(this.getRoot(), key);

    }

    public T rec_search(AVLNode<T> node,int key){
        if(node.getKey()==key)
            return node.data;
        if (node.getKey() < key && node.getRightChild()!=null)
            return rec_search(node.getRightChild(), key);
        if (node.getKey() > key && node.getLeftChild()!=null)
            return rec_search(node.getLeftChild(), key);

        return null;

    }



    public AVLNode<T> getRoot(){
        return this.rootNode;
    }


    private int MaxHeight(int LeftNodeHeight, int RightNodeHeight) {
        if(LeftNodeHeight > RightNodeHeight)
            return LeftNodeHeight;
        else
            return RightNodeHeight;

    }

    private AVLNode<T> RotainRightChild(AVLNode<T> node) {
        AVLNode<T> SonOriginal,Original;
        SonOriginal = node.getLeftChild();
        Original = SonOriginal.getRightChild();
        SonOriginal.rightchild = node;
        if(node == this.rootNode)
            this.rootNode = SonOriginal;
        node.parent = SonOriginal;
        node.leftchild = Original;
        if(Original!=null)
            Original.parent = node;

        node.height = 1 + MaxHeight(checkNullReturnHeight(node.getLeftChild()), (checkNullReturnHeight(node.getRightChild())));
        if(SonOriginal!=null)
            SonOriginal.height = 1 + MaxHeight(checkNullReturnHeight(node.getLeftChild()), (checkNullReturnHeight(node.getRightChild())));

        return SonOriginal;
    }

    private AVLNode<T> RotainLeftChild(AVLNode<T> node) {
        AVLNode<T> Original, SonOriginal;
        SonOriginal = node.getRightChild();
        Original = SonOriginal.getLeftChild();
        SonOriginal.leftchild = node;
        if(node == this.rootNode)
            this.rootNode = SonOriginal;
        node.parent = SonOriginal;
        node.rightchild = Original;
        if(Original!=null)
            Original.parent = node;

        node.height = 1+ MaxHeight(checkNullReturnHeight(node.getLeftChild()), checkNullReturnHeight(node.getRightChild())); // update height
        if(SonOriginal!=null)
            SonOriginal.height =1+ MaxHeight(checkNullReturnHeight(SonOriginal.getLeftChild()), checkNullReturnHeight(SonOriginal.getRightChild())); // update height

        return SonOriginal;


    }

    private void RotainRightLeftChild(AVLNode < T > node) {
        AVLNode<T> NewNode;
        NewNode =RotainRightChild(node.getRightChild());
        node.rightchild=NewNode;
        RotainLeftChild(node);

    }
    private void  RotainLeftRightChild(AVLNode < T > node) {
        AVLNode<T> NewNode;
        NewNode =RotainLeftChild(node.getLeftChild());
        node.leftchild = (NewNode);
        RotainRightChild(node);

    }
}
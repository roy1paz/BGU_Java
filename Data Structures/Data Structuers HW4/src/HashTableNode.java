public class HashTableNode {
    int key;
    HashTableNode next;
    ObjectWithCoordinates value;

    public HashTableNode(ObjectWithCoordinates v, int k){
        key = k;
        value = v;
    }

    public int getKey() {
        return key;
    }

    public ObjectWithCoordinates getValue() {
        return value;
    }

    public void setNext(HashTableNode node) {
        next = node;
    }

    public HashTableNode getNext() {
        return next;
    }
}
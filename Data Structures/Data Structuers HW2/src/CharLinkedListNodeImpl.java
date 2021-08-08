public class CharLinkedListNodeImpl implements ICharLinkedListNode{
    private char val;
    private CharLinkedListNodeImpl next;

    CharLinkedListNodeImpl(char val) {
        if((val >= 'a' && val <= 'z') || (val >= 'A' && val <= 'Z') || val == '$')
            this.val = Character.toLowerCase(val);
        else
            throw new IllegalArgumentException("illegal Character entered: |" + val + "| this is not an alphabetic char!");
    }
    public char getChar() {
        return val;
    }
    public ICharLinkedListNode getNext() {
        return next;
    }
    public void setNext(ICharLinkedListNode next) {
        this.next = (CharLinkedListNodeImpl) next;
    }
}
public class CharLinkedListImpl extends CharLinkedList {

    public void add(char c) {
        /*
          Adds a new character to the end of the list

          @param c Character to add
         */
        //2DL: check data
        ICharLinkedListNode nd = new CharLinkedListNodeImpl(c);
        if (first==null)
            first = nd;
        else
            last.setNext(nd);
        last = nd;
    }
    public char firstChar(){
        /*
          Getter for the first character

          @return The first character in the list
         */
        return first.getChar();
    }

    public int size() {
        int count = 1;
        ICharLinkedListNode temp;
        if(first==null)
            return 0;
        temp = first.getNext();
        while (temp != null) {
            temp = temp.getNext();
            count++;
        }
        return count;
    }

    public void append(CharLinkedList toAppend) {
        for (char c : toAppend) {
            this.add(c);
        }
    }
}


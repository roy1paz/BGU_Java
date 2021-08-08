public class SuffixTreeNodeImpl extends SuffixTreeNode {

    public SuffixTreeNodeImpl() {
        super();
        descendantLeaves = 1;
        this.chars = new CharLinkedListImpl();
    }

    public SuffixTreeNodeImpl(CharLinkedList chars, SuffixTreeNode parent) {
        super(chars, parent);
    }

    public SuffixTreeNode search(char c) {
        /*
          Wrapper for the binary search method
          @param c Character to search for
         * @return A child node with c as his first character
         */
        if (numOfChildren <= 0)
            return null;
        return binarySearch(c, 0, numOfChildren - 1);
    }

    public SuffixTreeNode binarySearch(char target, int left, int right) {
        /*
          Finds and returns the node's child with target as his first character, using the binary search operation.
          @param target Character to search for
         * @param left Left boundary index for searching in the children array
         * @param right Right boundary index for searching in the children array
         * @return A child node with c as his first character, or null if no such child exists
         */
        target = Character.toLowerCase(target);
        if (left <= right && left >= 0 && right < children.length) {
            int nextIndex = (left + right) / 2;
            SuffixTreeNode guess = children[nextIndex];
            char firstCH = guess.getChars().firstChar();
            if (firstCH == target) {
                return guess;
            }
            int targetInt = target;
            if (target == '$')
                targetInt = (int) 'z' + 1;
            if (targetInt > (int) firstCH)
                return binarySearch(target, nextIndex + 1, right);
            return binarySearch(target, left, nextIndex - 1);
        }
        return null;
    }

    public void shiftChildren(int until) {
        /*
          Shifts all elements one cell to the right, until the "until" index, including.
          Assume the array is big enough even after the shifting
          @param until Left boundary index of shifting
         */
        if (until < 0)
            throw new IllegalArgumentException("until: |" + until + "| is out of bound!");
        for (int i = numOfChildren - 1; i >= until; i--) {
            children[i + 1] = children[i];
        }
        children[until] = null;
    }

    private int indexBinarySearch(char target) {
        if (numOfChildren <= 0)
            return 0;
        if (target == '$')
            return numOfChildren;
        int left = 0;
        int right = numOfChildren - 1;

        while (left <= right) {
            int nextIndex = (left + right) / 2;
            SuffixTreeNode guess = children[nextIndex];
            char firstCH = guess.getChars().firstChar();

            if (firstCH == target)
                return nextIndex;
            if ((int) target > (int) firstCH)
                left = nextIndex + 1;
            else
                right = nextIndex - 1;
        }
        return right + 1;
    }

    public void addChild(SuffixTreeNode node) {
        /*
          Add a new node as a child to this node.
          To preserve the lexicographic order, shifting some of the elements in the array might be needed.
          Note: To compare two siblings in this tree, you need to compare only their first character, as they are surely different
          @param node node to add
         */
        if(numOfChildren== 0)
            descendantLeaves = 0;
        char target = node.getChars().firstChar();
        int res = indexBinarySearch(target);
        shiftChildren(res);
        children[res] = node;
        if(node.parent != this)
            node.parent = this;
        this.numOfChildren++;

    }


    public void addSuffix(char[] word, int from) {
        /*
          Adds the suffix word[from:] to the node and recursively to its children.
          Since this method is called before the compression method, we can assume all nodes contain only one character each
          @param word The tree's full word
         * @param from Suffix index
         */

        // End statement
        if (from == word.length) {

            this.descendantLeaves++;
            SuffixTreeNode tempNode = this.getParent();

            while (tempNode != null) {
                tempNode.descendantLeaves++;
                tempNode = tempNode.getParent();
            }
            return;
        }

        char crntCH = Character.toLowerCase(word[from]);
        SuffixTreeNode searchRes = this.search(crntCH);

        // found a match
        if (searchRes != null) {
            searchRes.addSuffix(word, from + 1);
            return;
        }

        CharLinkedList linkedLisChar = new CharLinkedListImpl();
        linkedLisChar.add(crntCH);

        SuffixTreeNode newNode = new SuffixTreeNodeImpl(linkedLisChar, this);
        this.addChild(newNode);
        newNode.addSuffix(word, from + 1);
    }


    public int numOfOccurrences(char[] subword, int from) {
        /*
          Calculates the number of occurrences of subword[from:] in the tree's word
          Examples: new SuffixTree("mississippi").getRoot().numOfOccurrences(new Char[]{'s', 's', 'i'}, 0) -> 2,
          new SuffixTree("mississippi").getRoot().numOfOccurrences(new Char[]{'s', 's', 'i'}, 1) -> 2,
          new SuffixTree("mississippi").getRoot().numOfOccurrences(new Char[]{'s'}, 0) -> 4,
          new SuffixTree("mississippi").getRoot().numOfOccurrences(new Char[]{'m', 's'}, 0) -> 0,

          @param subword Char array representing string to calculate the number of its occurrences in tree's word
         * @param from
         * @return Number of occurrences of subword in the tree's word (0 or more)
         */
        if (from < subword.length) {
            SuffixTreeNode res = search(subword[from]);

            //if char in children
            if (res != null) {

                //success
                if (from == subword.length - 1)
                    return res.descendantLeaves;

                // compressed node
                if (res.getChars().size() > 1) {
                    ICharLinkedListNode resCh = res.chars.first;

                    int counter = 0;
                    for (int i = from; i < subword.length; i++) {
                        if (resCh == null)
                            break;
                        if (subword[i] == resCh.getChar()) {
                            counter++;
                            resCh = resCh.getNext();
                        }
                        else
                            break;
                    }

                    if (counter == subword.length - from) {
                        //subword in node
                        return res.descendantLeaves;
                    }
                    else if (counter == res.chars.size()) {
                        //node contains subword
                        from += counter;
                        return res.numOfOccurrences(subword, from);
                    }
                    else {
                        //no match
                        return 0;
                    }
                }
                // node with matching single char
                return res.numOfOccurrences(subword, from + 1);
            }
        }
        return 0;
    }

    public void compress() {
        /*
         * Compress the node and its descendants using the following rule:
         * For each node, if it has only 1 child - merge it with his (only) child and concatenate their chars;
         * For graphic examples, see the PDF
         */

        if (numOfChildren == 0) {
            return;
        }
        if (numOfChildren == 1) {
            for (SuffixTreeNode child : this.children[0].children) {
                if (child == null)
                    break;
                child.parent = this;
            }
            this.chars.append(this.children[0].getChars());
            this.totalWordLength += this.children[0].getChars().size();
            this.numOfChildren = this.children[0].numOfChildren;
            this.children = this.children[0].getChildren();
            if (numOfChildren == 1)
                this.compress();
        }
        for (SuffixTreeNode crntNode : this.getChildren()) {
            if (crntNode == null)
                break;
            crntNode.compress();
        }
    }
}
public class longestRepeatedSuffixTreeImpl extends longestRepeatedSuffixTree {

    public longestRepeatedSuffixTreeImpl(String word) {
        /**
         * Constructs a suffix tree containing all suffices of a single word.
         * A dollar sign is appended to the end of the word
         *
         * @param word The word to create a tree of all its suffices
         */
        super(word);
    }

    private void recPathSearch(SuffixTreeNodeImpl crntNode, SuffixTreeNodeImpl[] res) {
        if (crntNode.descendantLeaves == crntNode.numOfChildren) {
            // insert node to result
            for (int i = 0; i < res.length; i++) {
                if (res[i] == null) {
                    res[i] = crntNode;
                    break;
                }
            }
            return;
        }
        else if (crntNode.descendantLeaves == 1)
            return;

        for (SuffixTreeNode tempNode : crntNode.children) {
            if (tempNode != null){
                recPathSearch((SuffixTreeNodeImpl) tempNode, res);
            }
            else
                break;
        }
    }

    public void createLongestRepeatedSubstring() {
        /**
         * Calculates and returns the longest repeated substring in the tree's word.
         * In case of multiple longest Repeated Substring we choose the longest Repeated Substring which comes 1st lexicographically.
         * This function preforms placement to the maxLength and substringStartNode members.
         * Examples: new longestRepeatedSuffixTreeImpl("mississippi").getLongestRepeatedSubstring() -> "issi",
         * new longestRepeatedSuffixTreeImpl("ioiosbdbd").getLongestRepeatedSubstring() -> "bd",
         * new longestRepeatedSuffixTreeImpl("bdioiosbd").getLongestRepeatedSubstring() -> "bd",
         * new longestRepeatedSuffixTreeImpl("abcd").getLongestRepeatedSubstring() -> "X"  - no repeated substring,
         * new longestRepeatedSuffixTreeImpl("a").getLongestRepeatedSubstring() -> "X"  - no repeated substring,
         * new longestRepeatedSuffixTreeImpl("aaaaaaaaaa").getLongestRepeatedSubstring() -> "aaaaaaaaa"
         */
        SuffixTreeNode winner = null;
        SuffixTreeNodeImpl[] matchNodes = new SuffixTreeNodeImpl[this.root.descendantLeaves];
        this.recPathSearch((SuffixTreeNodeImpl) this.root, matchNodes);
        int maxPathLen = 0;
        CharLinkedList minCh = new CharLinkedListImpl();
        int flag = 0;
        int flag2 = 0;
        for (SuffixTreeNode crntNode:matchNodes) {
            flag = 1;
            if (crntNode == this.root || crntNode == null)
                break;
            int crntLen = crntNode.totalWordLength;
            if (crntLen >= maxPathLen) {

                if (crntLen == maxPathLen) {
                    ICharLinkedListNode minFirstCh = minCh.first;
                    ICharLinkedListNode crntNdFirstCh = crntNode.chars.first;
                    int j = 0;
                    for (int i = 0; i < crntNode.chars.size(); i++) {
                        // iterate on shared chars
                        if (minFirstCh == null)
                            break;
                        int minINT = minFirstCh.getChar();
                        int crntND = crntNdFirstCh.getChar();
                        if (minINT > crntND) {
                            maxPathLen = crntNode.totalWordLength;
                            CharLinkedList newMin = new CharLinkedListImpl();
                            newMin.append(crntNode.chars);
                            minCh = newMin;
                            winner = crntNode;
                            break;
                        }
                        if (minINT < crntND) {
                            flag2 = 1;
                            break;
                        }
                        crntNdFirstCh.getNext();
                        minFirstCh.getNext();
                        j = i;
                    }
                    // if crnt node was equal all along but shorter than winner
                    if (j == crntNode.chars.size() - 1 && flag2 == 0  ) {
                        maxPathLen = crntNode.totalWordLength;
                        CharLinkedList newMin = new CharLinkedListImpl();
                        newMin.append(crntNode.chars);
                        minCh = newMin;
                        winner = crntNode;
                        break;
                    }
                }
                if (flag2 == 1)
                    break;
                maxPathLen = crntLen;
                CharLinkedList newMin;
                newMin = crntNode.chars;
                minCh = newMin;
                winner = crntNode;
            }
        }
        if(flag == 1) {
            this.substringStartNode = winner;
            this.maxLength = maxPathLen;
        }
    }

    public String getLongestRepeatedSubstring() {
        /**
         * Getter for the longest repeated substring
         * You should use the substringStartNode you already found in createLongestRepeatedSubstring function.
         * @return the longest repeated substring
         */
        char[] result = new char[this.maxLength];
        SuffixTreeNode tempNode = this.substringStartNode;
        int i = 0;
        int size = 0;
        if(this.substringStartNode == null)
            return "X";
        while(tempNode != this.root){ //until root
            size += tempNode.chars.size();
            for(char c:tempNode.chars){
                result[maxLength - size + i] = c;
                i++;
            }
            i = i - tempNode.chars.size();
            tempNode = tempNode.getParent();
        }
//        System.out.println("the winner is: " +new String(result));
        return new String(result);
    }
}
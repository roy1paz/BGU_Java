public class SuffixTreeImpl extends SuffixTree{

    public SuffixTreeImpl(String word) {
        /**
         * Constructs a regular suffix tree containing all suffices of a single word.
         * A dollar sign is appended to the end of the word to mark it.
         *
         * @param word The word to create a tree of all its suffices
         */
        super(word);
    }

    public boolean contains(String subword) {
        /**
         * Returns true if and only if the tree's word contains the specified subword.
         * Examples: new SuffixTree("mississippi").contains("ssi") -> true,
         * new SuffixTree("mississippi").contains("ms") -> false
         *
         * @param subword String to check if contained in tree's word
         * @return True if and only if the tree's word contains the specified subword
         */
        return this.numOfOccurrences(subword) > 0;

    }


    public int numOfOccurrences(String subword) {
        /**
         * Calculates the number of occurrences of subword in the tree's word
         * Examples: new SuffixTree("mississippi").numOfOccurrences("ssi") -> 2,
         * new SuffixTree("mississippi").numOfOccurrences("s") -> 4,
         * new SuffixTree("mississippi").numOfOccurrences("ms") -> 0
         *
         * @param subword String to calculate the number of its occurrences in tree's word
         * @return Number of occurrences of subword in the tree's word (0 or more)
         */

        return getRoot().numOfOccurrences(subword.toCharArray(), 0);
    }
}

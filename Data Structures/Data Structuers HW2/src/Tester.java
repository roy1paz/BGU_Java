/**
 * This is a testing framework. Use it extensively to verify that your code is working
 * properly.
 */
public class Tester {

	private static boolean testPassed = true;
	private static int testNum = 0;

	/**
	 * This entry function will test all classes created in this assignment.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {

		// CharLinkedListNode and input validation
		testCharLinkedListNode();

		// CharLinkedList
		testCharLinkedList();

		// SuffixTreeNode
		testSuffixTreeNode();

		// SuffixTree
		testSuffixTree();

		// longestRepeatedSuffixTree
		testLongestRepeatedSuffixTree();



		// Notifying the user that the code have passed all tests.
		if (testPassed) {
			System.out.println("All " + testNum + " tests passed!");
		}
	}

	/**
	 * This utility function will count the number of times it was invoked.
	 * In addition, if a test fails the function will print the error message.
	 * @param exp The actual test condition
	 * @param msg An error message, will be printed to the screen in case the test fails.
	 */
	private static void test(boolean exp, String msg) {
		testNum++;

		if (!exp) {
			testPassed = false;
			System.out.println("Test " + testNum + " failed: "  + msg);
		}
	}

	/**
	 * Checks the CharLinkedListNode class.
	 */
	private static void testCharLinkedListNode(){
		ICharLinkedListNode node = new CharLinkedListNodeImpl('a');
		ICharLinkedListNode nodeToAdd = new CharLinkedListNodeImpl('b');
		node.setNext(nodeToAdd);
		test(node.getChar() == 'a', "The char should be 'a'");
		test(node.getNext().getChar() == 'b', "The char should be 'b'");
		char c = 'G';
		CharLinkedList test;
		test = CharLinkedList.from(c);
		test(test.firstChar() == Character.toLowerCase(c), "Linked list node should contain " + c);
		testInType();
	}

	/**
	 * check validation of input
	 */
	private static void testInType(){
		char[] iCharLis_test = {'G', '?', '1', '$', ' '};
		for(char iChar:iCharLis_test) {

			if ((iChar >= 'a' && iChar <= 'z') || (iChar >= 'A' && iChar <= 'Z') || iChar == '$'){
				ICharLinkedListNode testNode = new CharLinkedListNodeImpl(iChar);
				test(testNode.getChar() == Character.toLowerCase(iChar), "Expected to encounter a lowercase char but received: " + iChar);
			}
			else{
				int flag = 0;
				try {
					char ignore = new CharLinkedListNodeImpl(iChar).getChar();
				} catch (Exception ex){
					flag = 1;}
				test(flag == 1, "Expected to encounter an exception when handling illegal char: |" + iChar + "| .");
			}
		}
	}

	/**
	 * Checks the CharLinkedList class.
	 */
	private static void testCharLinkedList(){
		CharLinkedList list = new CharLinkedListImpl();
		test(list.size() == 0, "The size of the list should be 0");
		list.add('a');
		test(list.size() == 1, "The size of the list should be 1");
		test(list.firstChar() == 'a', "The first char should be 'a'");

		CharLinkedList listToAppend = new CharLinkedListImpl();
		listToAppend.add('b');
		listToAppend.add('c');
		list.append(listToAppend);
		test(list.getLast().getChar() == 'c', "The last char should be 'c'");
		test(list.getFirst().getNext().getChar() == 'b', "The second char should be 'c'");
		test(list.getLast().getNext() == null, "The next char of the last char should be null'");
	}

	/**
	 * Checks the SuffixTreeNode class.
	 */
	private static void testSuffixTreeNode() {
		// test empty root
		SuffixTreeNode node = new SuffixTreeNodeImpl();
		test(node.getTotalWordLength() == 0, "node word length should be 0");
		test(node.getNumOfChildren() == 0, "node num of children should be 0");
		test(node.descendantLeaves == 1, "descendantLeaves should equal 1");
		test(node.getChars().getLast() == null, "empty nodes last char should equal null");

		// test search, binary search, shiftChildren and addChild
		SuffixTreeNode child1 = new SuffixTreeNodeImpl(CharLinkedList.from("abc"), node);
		SuffixTreeNode child2 = new SuffixTreeNodeImpl(CharLinkedList.from("bcd"), node);
		SuffixTreeNode child3 = new SuffixTreeNodeImpl(CharLinkedList.from("hello"), node);
		SuffixTreeNode child4 = new SuffixTreeNodeImpl(CharLinkedList.from("mmmmm"), node);
		node.setChildren(new SuffixTreeNode[]{child1, child2, child3, child4, null, null, null, null}, 4);

		// binary search
		test(node.binarySearch('b', 0, 3) == child2, "search for 'b' should find child2");
		test(node.binarySearch('b', -2, 3) == null, "search with illegal boundaries should return null");
		test(node.binarySearch('b', 0, 30) == null, "search with illegal boundaries should return null");
		test(node.binarySearch('B', 0, 3) == child2, "search for 'B' should find child2");


		// search
		test(node.search('a') == child1, "search for 'a' should return child1");
		test(node.search('x') == null, "search for 'x' should fail");

		// add child
		SuffixTreeNode child5 = new SuffixTreeNodeImpl(CharLinkedList.from("dog"), node);
		node.addChild(child5);
		test(node.getChildren()[2] == child5, "3rd child should be child5");
		test(node.getChildren()[2].parent == node, "node should be the parent of child5");
		test(node.numOfChildren == 5, "number of children should be 5");

		// compress
		SuffixTreeNode node1 = new SuffixTreeNodeImpl();
		SuffixTreeNode childNode1 = new SuffixTreeNodeImpl(CharLinkedList.from("abcde"), node1);
		SuffixTreeNode childNode2 = new SuffixTreeNodeImpl(CharLinkedList.from("bcdft"), node1);
		node1.setChildren(new SuffixTreeNode[]{childNode1, childNode2, null, null, null, null}, 2);
		int pastLeaves = node1.getDescendantLeaves();
		int pastChildren = node1.numOfChildren;
		node1.compress();
		test(node1.getDescendantLeaves() == pastLeaves, "Descendant Leaves should be "+ pastLeaves);
		test(node1.numOfChildren == pastChildren, "Number of children should be "+ pastChildren);

	}

	/**
	 * Checks the SuffixTree class.
	 */
	private static void testSuffixTree(){
		/*  checks compress,numOfOccurrences,addSuffix, contains, shiftChildren */
		SuffixTree tree1 = new SuffixTreeImpl("mississi");
		SuffixTree tree2 = new SuffixTreeImpl("baNANAnNAnANA");
		char[] charList = {'p','p','i'};

		//check contains
		test(tree1.contains("sis"), "The tree should contains 'sis'");

		//check addSuffix
		tree1.addSuffix(charList,0);
		test(tree1.getRoot().getChildren()[2].getChildren()[0].getChildren()[0].getChars().getLast().getChar() == 'i', "The leaf should hold char i");
		test(tree1.getRoot().getChildren()[2].getChildren()[0].getChildren()[0].descendantLeaves == 1, "descendantLeaves should be equal 1");
		test(tree1.contains("pp"), "The tree should contains 'pp'");

		// check numOfOccurrences
		test(tree1.numOfOccurrences("issi") == 2, "numOfOccurrences should return 2");
		test(tree2.numOfOccurrences("na") == 5, "numOfOccurrences should return 5");
		test(tree2.numOfOccurrences("an") == 5, "numOfOccurrences should return 5");
		test(tree2.numOfOccurrences("nn") == 1, "numOfOccurrences should return 1");
		test(tree2.numOfOccurrences("SWEET") == 0, "numOfOccurrences should return 0");

		//check shift children
		SuffixTreeNode root = new SuffixTreeNodeImpl();
		SuffixTreeNode child1 = new SuffixTreeNodeImpl(CharLinkedList.from("abc"), root);
		SuffixTreeNode child2 = new SuffixTreeNodeImpl(CharLinkedList.from("def"), root);

		root.setChildren(new SuffixTreeNode[]{child1, child2, null, null},2);
		root.shiftChildren(0);
		test(root.children[0] == null, "should be return null");
		test(root.children[1].chars.toString().equals("abc"), "should be return abc");
		test(root.children[2].chars.toString().equals("def"), "should be return def");
		test(tree2.getRoot().descendantLeaves == 14, "descendantLeaves of root should equal 14");
		root.shiftChildren(0);
		test(root.children[0] == null, "should be return null");
		test(root.children[1] == null, "should be return null");

		// check illegal arguments 'until' in shiftChildren until < 0
		int[] untilList = {-1,-10,-30,-400};
		for (int untilArg: untilList) {
			int flag = 0;
			try {
				root.shiftChildren(untilArg);
			} catch (Exception ex) {
				flag = 1;
			}
			test(flag == 1, "Expected to encounter an exception when handling illegal argument: |" + untilArg + "| .");
		}
	}

	/**
	 * Checks the LongestRepeatedSuffixTree class.
	 */
	private static void testLongestRepeatedSuffixTree(){

		testLongestRepeated("Mississippi", "issi");
		testLongestRepeated("abc", "X");
		testLongestRepeated("a","X");
		testLongestRepeated("abbc", "b");
		testLongestRepeated("baNANAnNAnANA","anana");
		testLongestRepeated("oooo","ooo");

	}

	/**
	 * utility function for testLongestRepeatedSuffixTree
	 */
	private static void testLongestRepeated(String word, String expected){
		test(new longestRepeatedSuffixTreeImpl(word).getLongestRepeatedSubstring().equals(expected), "Longest repeated substring should be " + expected);
	}

}
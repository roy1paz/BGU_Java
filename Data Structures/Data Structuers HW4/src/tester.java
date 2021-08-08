public class tester {

    private static boolean testPassed = true;
    private static int testNum = 0;

    public static void main(String[] args){
        AVL<String> tree1 = new AVL<>();
        AVL<String> tree2 = new AVL<>();

        tree1.insert(10,"r");                   //           20
        tree1.insert(20,"r");                  //           /   \
        tree1.insert(15,"r");                 //           15    25
        tree1.insert(25, "YOU FOUND ME");//      /  \      \
        tree1.insert(30,"r");               //           10   18     30
        tree1.insert(16,"r");              //                 / \
        tree1.insert(18,"r");             //                 16 19
        tree1.insert(19,"r");
        System.out.println("Preorder traversal" +
                " of constructed tree1 is : ");
        preOrder(tree1.root);
        System.out.println(tree1.search(25));

        tree2.insert(12,"r");         //                8
        tree2.insert(8,"r");          //              /   \
        tree2.insert(16,"r");         //             4     12
        tree2.insert(14,"r");        //             / \    / \
        tree2.insert(10,"r");        //            2   6  10  15
        tree2.insert(4,"r");         //           /   /      /  \
        tree2.insert(6,"r");         //          1   5      14  16
        tree2.insert(2,"r");
        tree2.insert(1,"r");
        tree2.insert(5,"r");
        tree2.insert(15,"r");


        System.out.println("Preorder traversal" +
                " of constructed tree2 is : ");
        preOrder(tree2.root);

        testHeight(tree2.root);

        testHashTable();

        testStudentSolution();

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


    public static void preOrder(AVLNode node) {
        if (node != null) {
            System.out.print("\n" + "     " + node.key + " \n");
            if (node.left != null && node.right != null) {
                for (int i = 0; i < 5; i++)
                    System.out.println("            ".substring(0, 4 - i + 1) + '/' + "            ".substring(0, i) + "            ".substring(0, i) + '\\');
                System.out.println("(" + node.left.key + ",      " + node.right.key + ") ");

            } else if (node.left != null || node.right != null) {
                for (int i = 0; i < 5; i++)
                    System.out.println("            ".substring(0, 4 - i+1) + '/' + "            ".substring(0, i) + "            ".substring(0, i) + '\\');
                String res = (node.left != null) ? "(" + node.left.key + ",      null)" : ("(null,     " + node.right.key + ")\n");
                System.out.println(res);
            }
            preOrder(node.left);
            preOrder(node.right);
        }
    }


    public static void testHeight(AVLNode node) {
        if (node.left == null && node.right == null) {
            return;
        }
        else if (node.left != null && node.right == null) {
            int height_d = Math.abs(node.left.height - node.height);
            test(height_d <= 2, "Height difference should be less than or equal to 2");
            testHeight(node.left);
        }
        else if (node.left == null && node.right != null) {
            int height_d = Math.abs(node.right.height - node.height);
            test(height_d <= 2, "Height difference should be less than or equal to 2");
            testHeight(node.right);
        }
        else {
            int height_d = Math.abs(node.left.height - node.right.height);
            test(height_d <= 1, "Height difference should be less than or equal to 1");
            testHeight(node.left);
            testHeight(node.right);

        }
    }


    public static void testHashTable(){
        HashTable hashTable1 = new HashTable();
        ObjectWithCoordinatesImpl point1 = new ObjectWithCoordinatesImpl(4,3);
        ObjectWithCoordinatesImpl point2 = new ObjectWithCoordinatesImpl(20, 5);
        ObjectWithCoordinatesImpl point3 = new ObjectWithCoordinatesImpl(3, 4);
        ObjectWithCoordinatesImpl point4 = new ObjectWithCoordinatesImpl(10, 1);


        hashTable1.insert(point1);
        hashTable1.insert(point2);
        hashTable1.insert(point3);
        hashTable1.insert(point4);


        // test point 1
        test(hashTable1.search(4,3).getX() == 4, "X should be 4");
        test(hashTable1.search(4,3).getY() == 3, "Y should be 3");
        // test point 2
        test(hashTable1.search(3,4).getX() == 3, "X should be 3");
        test(hashTable1.search(3,4).getY() == 4, "Y should be 4");

        hashTable1.delete(4,3);
        test(hashTable1.search(4,3) == null, "Should return null");

        // test nodes
        HashTableNode nd1 = new HashTableNode(point1,4);
        HashTableNode nd2 = new HashTableNode(point2,3);
        HashTableNode nd3 = new HashTableNode(point3,4);
        HashTableNode nd4 = new HashTableNode(point4,0);

        nd1.setNext(nd3);

        test(nd1.value.getX() == 4, "X should be 4");
        test(nd1.next.value.getX() == 3, "X should be 3");
        test(nd1.key == 4, "Key should be 4");
        test(nd2.key == 3, "Key should be 3");
        test(nd3.key == 4, "Key should be 4");
        test(nd4.key == 0, "Key should be 0");
    }


    public static void testStudentSolution() {
        ObjectWithCoordinatesImpl point1 = new ObjectWithCoordinatesImpl("Pickle Rick",4,3);
        ObjectWithCoordinatesImpl point2 = new ObjectWithCoordinatesImpl("Teresa Mendoza",20, 5);
        ObjectWithCoordinatesImpl point3 = new ObjectWithCoordinatesImpl("Mooncake",3, 4);
        ObjectWithCoordinatesImpl point4 = new ObjectWithCoordinatesImpl("Avocato",10, 1);

        ObjectWithCoordinatesImpl[] pointRes = {point1, point3, point4};

        StudentSolution studentS = new StudentSolution();
        studentS.insertDataFromDBFile("Pickle Rick", 4, 3);
        studentS.insertDataFromDBFile("Teresa Mendoza", 20, 5);
        studentS.insertDataFromDBFile("Mooncake", 3, 4);
        studentS.insertDataFromDBFile("Avocato", 10, 1);

        //leftTopY is minimum y
        String[] solutionRes1 = studentS.firstSolution(2,6,12,0);
        String[] solutionRes2 = studentS.secondSolution(2,6,12,0);

        int count1 = 0;

        // solution 1
        for (String res: solutionRes1) {
            for (ObjectWithCoordinatesImpl pointResult : pointRes){
                if (res.equals(pointResult.toString())) {
                    count1++;
                }
            }
        }

        test(count1 == pointRes.length, "Should be " + pointRes.length + " points");
        count1 = 0;

        // solution 2
        for (String res: solutionRes2) {
            for (ObjectWithCoordinatesImpl pointResult : pointRes){
                if (res.equals(pointResult.toString())) {
                    count1++;
                }
            }
        }

        test(count1 == pointRes.length, "Should be " + pointRes.length + " points");
    }
}

public class MyTests {

    private static boolean testPassed = true;
    private static int testNum = 0;

    /**
     * This entry function will test all classes created in this assignment.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        myTests();

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


    private static void myTests() {
        ObjectWithCoordinatesImpl p1 = new ObjectWithCoordinatesImpl("4", 4, 1);
        ObjectWithCoordinatesImpl p2 = new ObjectWithCoordinatesImpl("11", 11, 2);
        ObjectWithCoordinatesImpl p3 = new ObjectWithCoordinatesImpl("5", 5, 3);
        ObjectWithCoordinatesImpl p4 = new ObjectWithCoordinatesImpl("6", 6, 0);
        ObjectWithCoordinatesImpl p5 = new ObjectWithCoordinatesImpl("14", 14, 4);
        ObjectWithCoordinatesImpl p6 = new ObjectWithCoordinatesImpl("22", 22, 5);

        AVLNode<ObjectWithCoordinatesImpl> A = new AVLNode<ObjectWithCoordinatesImpl>(p1.getX(), p1);
        AVL<ObjectWithCoordinatesImpl> tree = new AVL<ObjectWithCoordinatesImpl>();
        tree.insert(p2.getX(), p2);
        tree.insert(p3.getX(), p3);
        tree.insert(p4.getX(), p4);
        tree.insert(p5.getX(), p5);
        tree.insert(p6.getX(), p6);

        test(tree.getRoot().getKey() == 11, "root should be p1"); // test1
        test(tree.search(4) == p1, "should return p1"); // test 2
        test(tree.search(22) == p6, "should return p6");//test3
        test(tree.search(15) == null, "should return null");//test4
        test(tree.getRoot().getRightChild().getKey() == p5.getX(), "should be true");//test5


//        ObjectWithCoordinatesImpl p7 = new ObjectWithCoordinatesImpl("4", 4, 6);
//        ObjectWithCoordinatesImpl p8 = new ObjectWithCoordinatesImpl("2", 2, 7);
//        ObjectWithCoordinatesImpl p9 = new ObjectWithCoordinatesImpl("3", 3, 8);
//        ObjectWithCoordinatesImpl p10 = new ObjectWithCoordinatesImpl("3", 3, 9);
//        ObjectWithCoordinatesImpl p11 = new ObjectWithCoordinatesImpl("15", 15, 11);
//        ObjectWithCoordinatesImpl p12 = new ObjectWithCoordinatesImpl("20", 20, 12);
//        ObjectWithCoordinatesImpl p13 = new ObjectWithCoordinatesImpl("13", 13, 13);
//        ObjectWithCoordinatesImpl p14 = new ObjectWithCoordinatesImpl("10", 10, 14);
//        ObjectWithCoordinatesImpl p15 = new ObjectWithCoordinatesImpl("5", 5, 15);
//        ObjectWithCoordinatesImpl p16 = new ObjectWithCoordinatesImpl("3", 3, 161);
//        ObjectWithCoordinatesImpl p17 = new ObjectWithCoordinatesImpl("16", 16, 17);
//
//        AVLNode<ObjectWithCoordinatesImpl> B = new AVLNode<ObjectWithCoordinatesImpl>(p7.getX(), p7);
//        AVL<ObjectWithCoordinatesImpl> tree2 = new AVL<ObjectWithCoordinatesImpl>();
//        tree2.insert(p8.getX(), p8);
//        tree2.insert(p9.getX(), p9);
//        tree2.insert(p10.getX(), p10);
//        tree2.insert(p11.getX(), p11);
//        tree2.insert(p12.getX(), p12);
//        tree2.insert(p13.getX(), p13);
//        tree2.insert(p14.getX(), p14);
//        tree2.insert(p15.getX(), p15);
//        tree2.insert(p16.getX(), p16);
//        tree2.insert(p17.getX(), p17);
//
//        test(tree2.getRoot().getKey() == 4, "root should be 4"); // test8
//        test(tree2.search(4) == p7, "should return p7"); // test 9
//        test(tree2.search(22) == null, "should return null");//test 10
//        test(tree2.search(15) == p11, "should return p11");//test 11
//        test(tree2.getRoot().getRightChild().getKey() == p11.getX(), "should be true");//test 12
//
//        HashTable hash = new HashTable(11);
//        ObjectWithCoordinatesImpl[] pts = {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16};
//
//        for(ObjectWithCoordinatesImpl pt : pts) {
//            hash.insert(pt);
//        }
//        test(hash.search(p1.getX(), p1.getY()) == p1, "should be p1"); // test 15
//        test(hash.search(p11.getX(), p11.getY()) == p11, "should be p11"); // test 16
//        test(hash.search(p17.getX(), p17.getY()) == null, "should be null"); // test 17
//        test(hash.search(p1.getX(), p2.getY()) == null, "should be null"); // test 18
//        test(hash.search(p12.getX(), p12.getY()) == p12, "should be p12"); // test 19
//        // ***m=11***
//
//
//        ObjectWithCoordinatesImpl p18 = new ObjectWithCoordinatesImpl("1", 1, 6);
//        ObjectWithCoordinatesImpl p19 = new ObjectWithCoordinatesImpl("3", 3, 7);
//        ObjectWithCoordinatesImpl p20 = new ObjectWithCoordinatesImpl("7", 7, 8);
//        ObjectWithCoordinatesImpl p21 = new ObjectWithCoordinatesImpl("5", 5, 9);
//        ObjectWithCoordinatesImpl p22 = new ObjectWithCoordinatesImpl("11", 11, 11);
//        ObjectWithCoordinatesImpl p23 = new ObjectWithCoordinatesImpl("15", 15, 12);
//        ObjectWithCoordinatesImpl p24 = new ObjectWithCoordinatesImpl("20", 20, 13);
//        ObjectWithCoordinatesImpl p25 = new ObjectWithCoordinatesImpl("8", 8, 14);
//        ObjectWithCoordinatesImpl p26 = new ObjectWithCoordinatesImpl("12", 12, 15);
//        ObjectWithCoordinatesImpl p27 = new ObjectWithCoordinatesImpl("13", 13, 161);
//        AVLNode<ObjectWithCoordinatesImpl> C = new AVLNode<ObjectWithCoordinatesImpl>(p18.getX(), p18);
//        AVL<ObjectWithCoordinatesImpl> tree3 = new AVL<ObjectWithCoordinatesImpl>();
//        tree3.insert(p19.getX(), p19);
//        tree3.insert(p20.getX(), p20);
//        tree3.insert(p21.getX(), p21);
//        tree3.insert(p22.getX(), p22);
//        tree3.insert(p23.getX(), p23);
//        tree3.insert(p24.getX(), p24);
//        tree3.insert(p25.getX(), p25);
//        tree3.insert(p26.getX(), p26);
//        tree3.insert(p27.getX(), p27);
//
//        test(tree3.getRoot().getKey() == 7, "root should be 4"); // test22
//        test(tree3.search(4) == null, "should return null"); // test 23
//        test(tree3.search(22) == null, "should return null");//test 24
//        test(tree3.search(15) == p23, "should return p23");//test 25
//        test(tree3.getRoot().getRightChild().getKey() == p26.getX(), "should be true");//test 26
//
//    }
//
//
}}
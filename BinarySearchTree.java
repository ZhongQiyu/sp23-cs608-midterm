package project;

/*
A Java class that implements the binary search tree
so that an RPN expression would be able to get stored
and implemented.
This can be used to convert the non-RPN xpressions.
The raw type of data is also supported, which allows
floating-point numbers to get involved when compute.
Ref: https://www.javatpoint.com/binary-tree-java
*/
public class BinarySearchTree<T> {
    
    /*
    The default class of Node that needs to be
    used to represent the node of binary tree.
    */
    public class Node{
        T data;
        Node left;
        Node right;
        public Node(T data){
            //Assign data to the new node, set left and right children to null
            this.data = data;
            this.left = null;
            this.right = null;
            }
        }
    
    //Represent the root of binary tree
    public Node root;
    public BinarySearchTree(){
        root = null;
    }
    
    //factorial() will calculate the factorial of given number
    public int factorial(int num) {
        int fact = 1;
        if(num == 0)
            return 1;
        else {
            while(num > 1) {
                fact = fact * num;
                num--;
            }
            return fact;
        }
    }
    
    //numOfBST() will calculate the total number of possible BST by calculating Catalan Number for given key
    public int numOfBST(int key) {
        int catalanNumber = factorial(2 * key)/(factorial(key + 1) * factorial(key));
        return catalanNumber;
    }

    /*
    A Java main method that creates a BST.
    */
    public static void main(String[] args) {
        BinarySearchTree bt = new BinarySearchTree();
        //Display total number of possible binary search tree with key 5
        System.out.println("Total number of possible Binary Search Trees with given key: " + bt.numOfBST(5));
    }

}

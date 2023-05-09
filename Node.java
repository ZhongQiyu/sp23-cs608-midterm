/*
The default class of Node that needs to be
used to represent the node of binary tree.
*/
public class Node<T>{
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

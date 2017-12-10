package controller;

public class Node {
	
	private int data;
	private Node left;
	private Node right;
	
	Node(int data){
		this.data = data;
		left = null;
		right = null;
	}
	
	public void setLeft(Node n) {
		left = n;
	}
	
	public void setRight(Node n) {
		right = n;
	}
	
	public Node getLeft() {
		return left;
	}
	
	public Node getRight() {
		return right;
	}
	
	public int getData() {
		return data;
	}
}

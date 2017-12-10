package controller;

public class Tree {
	private Node root;
	
	public Tree() {
		this.root = null;
	}
	
	public Node getRoot() {
		return root;
	}
	
	public boolean find(int id) {
		Node current = root;
		
		while(current != null) {
			if(current.getData() == id) {
				return true;
			} else if(current.getData() > id) {
				current = current.getLeft();
			} else {
				current = current.getRight();
			}
		}
		return false;
	}
	
	public boolean in_order_search(Node p, int val)
	{
	    if (p == null)
	    {
	        return false;
	    }
	    in_order_search(p.getLeft(), val);
	    if(p.getData() == val)
	    {
	        return true;
	    }
	    return in_order_search(p.getRight(), val);
	}
	
	public void insert(int id) {
		Node newNode = new Node(id);
		if(root == null) {
			root = newNode;
			return;
		}
		
		Node current = root;
		Node parent = null;
		
		while(true) {
			
			parent = current;
			
			if(id < current.getData()){				
				current = current.getLeft();
				if(current==null){
					parent.setLeft(newNode);
					return;
				}
			} else {
				current = current.getRight();
				if(current == null) {
					parent.setRight(newNode);
					return;
				}
			}
		}
	}
}

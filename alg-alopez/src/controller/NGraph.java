package controller;

public class NGraph {
	
	private int data;
	private NGraph next;
	
	NGraph(int data){
		this.data = data;
		next = null;
	}
	
	public void setNext(NGraph n) {
		next = n;
	}
	
	public NGraph getNext() {
		return next;
	}
	
	public int getData() {
		return data;
	}
}

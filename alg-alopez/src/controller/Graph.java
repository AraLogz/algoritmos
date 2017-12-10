package controller;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	private List<NGraph> data = new ArrayList<NGraph>();
	private String rel = "";
	
	Graph(int from, int to){
		//insert(from, to);//this.data.add(new NGraph(data));
		NGraph n = new NGraph(from);
		n.setNext(new NGraph(to));
		data.add(n);
		
		NGraph m = new NGraph(to);
		m.setNext(new NGraph(from));
		data.add(m);
		
		Sort();
	}
	
	public boolean insert(int id, Integer next) {
		int i = searchValue(next, data, 0, data.size()-1); //Exists->next value
		int j = searchValue(id, data, 0, data.size()-1); //!Exists->new value
		if(i != -1 && j == -1) {
			NGraph n = new NGraph(id);
			n.setNext(new NGraph(next));
			data.add(n);
			NGraph aux = data.get(i); //Add arc to next element
			addNArc(aux, id);
			Sort();
			return true;
		} else if(i != -1 && j != -1) { //Both values exists, first check if the relation doesn't exist
			NGraph aux = data.get(i); //Add arc to from
			if(!checkRelExist(aux.getNext(), id)) {
				addNArc(aux, id);
				aux = data.get(j);
				addNArc(aux, next);
				return true;
			}
		} else if(i == -1) {
			NGraph n = new NGraph(next);
			n.setNext(new NGraph(id));
			data.add(n);
			NGraph aux = data.get(j); //Add arc to next element
			addNArc(aux, next);
			Sort();
			return true;
		}
		return false;
	}
	
	public String showAllRel() {
		String relations = "";
		for(int i = 0; i < data.size(); i++) {
			relations += (showRel(data.get(i).getData()) + "\n");
		}
		return relations;
	}
	
	public boolean delN(int n) {
		int i = searchValue(n, data, 0, data.size() - 1); //get index of node
		NGraph node = data.get(i);
		if(deleteNode(node, i)) {
			return true;
		}
		return false;
	}
	
	public boolean delRel(int to, int from) {
		int i = searchValue(to, data, 0, data.size()-1); //Exists->next value
		int j = searchValue(from, data, 0, data.size()-1); //!Exists->new value
		NGraph n = data.get(i);
		if(checkRelExist(n, from)) {
			deleteRel(n, from);
			n = data.get(j);
			deleteRel(n, to);
			return true;
		}
		return false;
	}
	
	private boolean deleteNode(NGraph n, int id) {
		if(n.getNext() != null) {
			delRel(n.getData(), n.getNext().getData());
			return deleteNode(n, id);
		} else {
			data.remove(id);
			return true;
		}
	}
	
	private void deleteRel(NGraph n, int from) {
		if(n.getNext().getData() == from) {
			n.setNext(n.getNext().getNext());
		} else {
			deleteRel(n.getNext(), from);
		}
	}
	
	public String showRel(int id) {
		int i = searchValue(id, data, 0, data.size()-1); //Get id index
		if(i != -1) {
			NGraph n = data.get(i);
			rel = "rel->(" + (n.getData()) + ")";
			searchRel(n.getNext());
			return rel;
		}
		return "";
	}
	
	private boolean checkRelExist(NGraph n, int from) {
		if(n == null) {
			return false;
		}
		if(n.getData() == from) {
			return true;
		}
		else {
			return checkRelExist(n.getNext(), from);
		}
	}
	
	private void searchRel(NGraph n) {
		if(n != null) {
			rel += " -> " + n.getData();
			searchRel(n.getNext());
		}
	}
	
	private void addNArc(NGraph n, int to) {
		if(n.getNext() != null) {
			addNArc(n.getNext(), to);
		}
		else {
			n.setNext(new NGraph(to));
		}
	}
	
	private void Sort() {
		NGraph auxA;
		
		int i, j, min_idx;
		int n = data.size();

        // One by one move boundary of unsorted 
        for (i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            min_idx = i;
            for (j = i+1; j < n; j++) {
                if (data.get(j).getData() < data.get(min_idx).getData()) {
                    min_idx = j;
                }
            }

            // Swap the found minimum element with the first element
            auxA = data.get(min_idx);
            data.set(min_idx, data.get(i));
            data.set(i, auxA);
        }
	}
	
	private int searchValue(int value, List<NGraph> values, int start, int end) {
		if(values.get(end).getData() == value || values.get(start).getData() == value) {
			return values.get(end).getData() == value ? end : start;
		}
		if(start == (end-1) || start == end) {
			return -1; //Not found
		}
		//Check before changing index -> Checks if changing value is equal to the one that we are searching
		if(values.get(start + (end - start)/2).getData() == value || values.get((end - start)/2).getData() == value){
			return values.get((end - start)/2).getData() == value ? (end - start)/2 : start + (end - start)/2;
		}
		else if(value > values.get(start).getData()) {//start + (end - start)/2)) {
			//Avoid moving wrong index
			if(value > values.get(start + (end - start)/2).getData()) {
				start = start + (end - start)/2;
			}
			else {
				end = start + (end - start)/2;
			}
			return searchValue(value, values, start, end);
		}
		else {
			int index = (end - start)/2;
			//Keep max and min index
			if(index > start) {
				return searchValue(value, values, start, index);
			}
			else {
				return searchValue(value, values, index, end);
			}
		}
	}
}

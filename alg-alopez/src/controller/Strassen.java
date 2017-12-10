package controller;

import java.util.ArrayList;
import java.util.List;

public class Strassen {
	
	public List<List<Integer>> multiply(List<List<Integer>> A, List<List<Integer>> B)
    {        
        int n = A.size();
        List<List<Integer>> R = new ArrayList<List<Integer>>();
        //check dim
        if (n == 1) {
            R.add(0, new ArrayList<>());
            R.get(0).add(A.get(0).get(0) * B.get(0).get(0));
        }
        else
        {
            List<List<Integer>> A11 = new ArrayList<List<Integer>>();
            List<List<Integer>> A12 = new ArrayList<List<Integer>>();
            List<List<Integer>> A21 = new ArrayList<List<Integer>>();
            List<List<Integer>> A22 = new ArrayList<List<Integer>>();
            List<List<Integer>> B11 = new ArrayList<List<Integer>>();
            List<List<Integer>> B12 = new ArrayList<List<Integer>>();
            List<List<Integer>> B21 = new ArrayList<List<Integer>>();
            List<List<Integer>> B22 = new ArrayList<List<Integer>>();
 
            //Split A
            split(A, A11, 0 , 0);
            split(A, A12, 0 , n/2);
            split(A, A21, n/2, 0);
            split(A, A22, n/2, n/2);
            
            //Split B
            split(B, B11, 0 , 0);
            split(B, B12, 0 , n/2);
            split(B, B21, n/2, 0);
            split(B, B22, n/2, n/2);
            
            //Calculate P
            List<List<Integer>> P1 = multiply(add(A11, A22), add(B11, B22));
            List<List<Integer>> P2 = multiply(add(A21, A22), B11);
            List<List<Integer>> P3 = multiply(A11, sub(B12, B22));
            List<List<Integer>> P4 = multiply(A22, sub(B21, B11));
            List<List<Integer>> P5 = multiply(add(A11, A12), B22);
            List<List<Integer>> P6 = multiply(sub(A21, A11), add(B11, B12));
            List<List<Integer>> P7 = multiply(sub(A12, A22), add(B21, B22));
            
            //Calculate C
            List<List<Integer>> C11 = add(sub(add(P1, P4), P5), P7);
            List<List<Integer>> C12 = add(P3, P5);
            List<List<Integer>> C21 = add(P2, P4);
            List<List<Integer>> C22 = add(sub(add(P1, P3), P2), P6);
 
            //Join
            join(C11, R, 0 , 0);
            join(C12, R, 0 , n/2);
            join(C21, R, n/2, 0);
            join(C22, R, n/2, n/2);
        }
           
        return R;
    }
    //Sub of two mt
    public List<List<Integer>> sub(List<List<Integer>> A, List<List<Integer>> B)
    {
        int n = A.size();
        List<List<Integer>> C = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
        	C.add(i, new ArrayList<>());
            for (int j = 0; j < n; j++) {
                C.get(i).add(j, A.get(i).get(j) - B.get(i).get(j));
            }
        }
        return C;
    }
    //Join two
    public List<List<Integer>> add(List<List<Integer>> A, List<List<Integer>> B)
    {
        int n = A.size();
        List<List<Integer>> C = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
        	C.add(i, new ArrayList<>());
            for (int j = 0; j < n; j++) {
                C.get(i).add(j, A.get(i).get(j) + B.get(i).get(j));
            }
        }
        return C;
    }
    //Split
    public void split(List<List<Integer>> P, List<List<Integer>> C, int iB, int jB) 
    {
    	int n = (C.size() != 0 ? C.size() : P.size()/2);
        for(int i1 = 0, i2 = iB; i1 < n; i1++, i2++) {
        	C.add(i1, new ArrayList<>());
            for(int j1 = 0, j2 = jB; j1 < n; j1++, j2++) {
                C.get(i1).add(j1, P.get(i2).get(j2));
            }
        }
    }
    //Join all
    public void join(List<List<Integer>> C, List<List<Integer>> P, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.size(); i1++, i2++) {
        	if(P.size() < i2 + 1) {
        		P.add(i2, new ArrayList<>());
        	}
            for(int j1 = 0, j2 = jB; j1 < C.size(); j1++, j2++) {
                P.get(i2).add(j2, C.get(i1).get(j1));
            }
        }
    }    
}

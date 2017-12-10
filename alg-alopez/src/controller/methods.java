package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import javafx.scene.chart.XYChart;

public class methods {

	@SuppressWarnings("rawtypes")
	private XYChart.Series data;
	private long time;
	private List<String> arrayValue;
	private int cont;
	private List<Integer> sortedElements;
	private double z = 2;
	private int n = 3;
	
	private Graph gr;
	
	private Tree treeElements = new Tree();
	
	@SuppressWarnings("rawtypes")
	methods(){
		data = new XYChart.Series();
	}
	
	@SuppressWarnings("rawtypes")
	public XYChart.Series getData(){
		return this.data;
	}
	
	public List<Integer> getsortedElements(){
		return this.sortedElements;
	}
	
	public Tree gettreeElements() {
		return this.treeElements;
	}
	
	//Calculate prime factors of n
	public List<String> primeFactors(long n) {
		String value = "";
		
		this.data.setName("Factores Primos");
		int count = 1;
		
		time = getTime();
		arrayValue = new ArrayList<String>();
		
		//Start
        while (n%2==0){
        	value += "2 * ";
        	addDataToChart(this.data, time, count++);
            n /= 2;
        }
        for (int i = 3; i <= Math.sqrt(n); i+= 2){
            while (n%i == 0){
            	value += i + " * ";
            	addDataToChart(this.data, time, count++);
                n /= i;
            }
        }
        if (n > 2){
        	value += n + "";
        	addDataToChart(this.data, time, count++);
        }
        //End
        
        int len = value.length();
        String sub = value.substring(len - 2, len - 1);
        if(sub.equals("*")) {
        	arrayValue.add(value.substring(0,len-2)); //Remove extra *
        }
        else {
        	arrayValue.add(value);
        }
        
        double elapsed = getElapsed(time, getTime());
        
        arrayValue.add(String.valueOf(elapsed + "\n"));
        arrayValue.add(String.valueOf(count));
        return arrayValue;
	}
	
	public List<String> primeNumbers(long n) {
		String value = "";
		
		this.data.setName("Números Primos");
		int count = 1;
		boolean isPrime = true;
		
		arrayValue = new ArrayList<String>();
		
		time = getTime();
		if(n >= 2) {
			addDataToChart(this.data, time, count++);
			value += "2";
		}
		for(int i = 3; i < n; i+=2) {
			for(int j = 3; j <= Math.floor(Math.sqrt(i)); j+=2){
				if(i%j == 0) {
					isPrime = false;
				}
			}
			if(isPrime) {
				addDataToChart(this.data, time, count++);
				value += ", " + i;
			}
			else {
				isPrime = true;
			}
		}
		arrayValue.add(value);		
		
		double elapsed = getElapsed(time, getTime());
		arrayValue.add(String.valueOf(elapsed + "\n"));
		arrayValue.add(String.valueOf(count));
		return arrayValue;
	}
	
	public List<String> insertionSort(String name) throws IOException{
		
		List<Integer> values = readFile(name);
		
		this.data.setName("Insertion");
		
		arrayValue = new ArrayList<String>();
		arrayValue.add("Entrada: " + values.toString() + "\n");
		
		time = getTime();
		int count = 0;
		
		int tmp;
	  	int i, j;
	  	int n = values.size();
 
	  	for(i=1; i<n; i++) {	
	  		tmp = values.get(i);
	  		for(j=i; (j>0 && (tmp < values.get(j-1))); j--) {
	  			addDataToChart(this.data, time, count++);
	  			values.set(j, values.get(j-1));
	  		}
	  		addDataToChart(this.data, time, count++);
	  		values.set(j, tmp);
	  	}
	  	
	  	double elapsed = getElapsed(time, getTime());
	  	arrayValue.add("Salida: " + values.toString()  + "\n");
	  	arrayValue.add(String.valueOf(elapsed));
	  	arrayValue.add(String.valueOf(count));
	  	
	  	return arrayValue;
	}
	
	public List<String> selectonSort(String name) throws IOException {
		List<Integer> values = readFile(name);
		
		this.data.setName("Selection");
		
		arrayValue = new ArrayList<String>();
		arrayValue.add("Entrada: " + values.toString() + "\n");
		
		time = getTime();
		int count = 0;
		int auxA;
		
		int i, j, min_idx;
		int n = values.size();

        // One by one move boundary of unsorted 
        for (i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            min_idx = i;
            addDataToChart(this.data, time, count++);
            for (j = i+1; j < n; j++) {
            	addDataToChart(this.data, time, count++);
                if (values.get(j) < values.get(min_idx)) {
                    min_idx = j;
                }
            }

            // Swap the found minimum element with the first element
            auxA = values.get(min_idx);
            values.set(min_idx, values.get(i));
            values.set(i, auxA);
        }
	  	
	  	double elapsed = getElapsed(time, getTime());
	  	arrayValue.add("Salida: " + values.toString()  + "\n");
	  	arrayValue.add(String.valueOf(elapsed));
	  	arrayValue.add(String.valueOf(count));
	  	
	  	return arrayValue;
	}
	
	public List<String> bubbleSort(String name) throws IOException {
		List<Integer> values = readFile(name);
		
		this.data.setName("Bubble");
		
		arrayValue = new ArrayList<String>();
		arrayValue.add("Entrada: " + values.toString() + "\n");
		
		time = getTime();
		int count = 0;
		int auxA;
		
		int n = values.size();
		
		int i, j;
        for (i = 0; i < n-1; i++) {
        	addDataToChart(this.data, time, count++);
            // Last i elements are already in place
            for (j = 0; j < n-i-1; j++) {
            	addDataToChart(this.data, time, count++);
                //Check elements until condition
                if (values.get(j) > values.get(j+1)) {
               // Swap the found minimum element with the first element
                  auxA = values.get(j);
                  values.set(j, values.get(j+1));
                  values.set(j+1, auxA);
                }
            }
        }
	  	
	  	double elapsed = getElapsed(time, getTime());
	  	arrayValue.add("Salida: " + values.toString()  + "\n");
	  	arrayValue.add(String.valueOf(elapsed));
	  	arrayValue.add(String.valueOf(count));
	  	
	  	return arrayValue;
	}
	
	public List<String> heapSort(String name) throws IOException {
		List<Integer> values = readFile(name);
		
		this.data.setName("Heap");
		
		arrayValue = new ArrayList<String>();
		arrayValue.add("Entrada: " + values.toString() + "\n");
		
		time = getTime();
		int count = 0;
		int auxA;
		
		int n = values.size();
		
		// Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
        	addDataToChart(this.data, time, count++);
            heapify(values, n, i);
        }

        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--) {
        	addDataToChart(this.data, time, count++);
            // Move current root to end
            auxA = values.get(0);
            values.set(0, values.get(i));
            values.set(i, auxA);

            // call max  on the reduced heap
            heapify(values, i, 0);
        }
	  	
	  	double elapsed = getElapsed(time, getTime());
	  	arrayValue.add("Salida: " + values.toString()  + "\n");
	  	arrayValue.add(String.valueOf(elapsed));
	  	arrayValue.add(String.valueOf(count));
	  	
	  	return arrayValue;
	}
	
	public List<String> quickSort(String name) throws IOException {
		List<Integer> values = readFile(name);
		
		this.data.setName("Quick");
		
		arrayValue = new ArrayList<String>();
		arrayValue.add("Entrada: " + values.toString() + "\n");
		
		time = getTime();
		cont = 0;
		
		int n = values.size();
		
		quickSort(values, 0, n - 1);
	  	
	  	double elapsed = getElapsed(time, getTime());
	  	arrayValue.add("Salida: " + values.toString()  + "\n");
	  	arrayValue.add(String.valueOf(elapsed));
	  	arrayValue.add(String.valueOf(cont));
	  	
	  	return arrayValue;
	}
	
	public List<String> mergeSort(String name, int type) throws IOException {
		List<Integer> values;
		
		values = type == 0 ? readFile(name) : generateAlea(1000);
		
		this.data.setName("Heap");
		
		arrayValue = new ArrayList<String>();
		arrayValue.add("Entrada: " + values.toString() + "\n");
		
		time = getTime();
		cont = 0;
		
		int n = values.size();
		
		mergeSortAlgorithm(values, 0, n - 1);
		
		sortedElements = new ArrayList<Integer>();
		sortedElements = values;
	  	
	  	double elapsed = getElapsed(time, getTime());
	  	arrayValue.add("Salida: " + values.toString()  + "\n");
	  	
	  	arrayValue.add(String.valueOf(elapsed));
	  	arrayValue.add(String.valueOf(cont));
	  	
	  	return arrayValue;
	}
	
	public List<String> newton(String value){
		arrayValue = new ArrayList<String>();
		List<String> values = readValues(value);
		
		double n = Double.valueOf(values.get(0));			//Position for A
		double guess = Double.valueOf(values.get(1));		//Position for guess
		double maxError = Double.valueOf(values.get(2));	//Position for max error
		
		double xi = guess;
		double ei = maxError;
		
		this.data.setName("Newton");
		int steps = 0;
		time = getTime();
		while(ei>=maxError) {
			xi = (xi + (n/xi))/2.0;
			ei = Math.abs(xi - (n/xi));
			addDataToChart(this.data, time, steps++);
		}
		double elapsed = getElapsed(time, getTime());
		arrayValue.add(String.valueOf("Raíz: " + xi	+ "\n"));
		arrayValue.add(String.valueOf(steps));
		arrayValue.add(String.valueOf(elapsed));
		
		return arrayValue;
	}
	
	public List<String> bisecc(String value) throws ScriptException{
		arrayValue = new ArrayList<String>();
		List<String> values = readValues(value);
		
		ScriptEngineManager manager = new ScriptEngineManager(); 
	    ScriptEngine interprete = manager.getEngineByName("js"); 
		
		String fx = values.get(0);
		double T = Double.valueOf(values.get(1));
		double a = Double.valueOf(values.get(2));
		double b = Double.valueOf(values.get(3));
		double maxError = Double.valueOf(values.get(4));
		double z = 0;
		
		int steps = 0;
		time = getTime();
		this.data.setName("Bisección");
		
		String regEx = "(?![\\+\\-\\*\\/\\(\\)\\^\\s])([a-zA-Z0-9]+)\\^([a-zA-Z0-9]+)"; //Parser ^ to Math.pow
		String subs = "Math.pow($1,$2)";
		
		fx = fx.replaceAll(regEx, subs);
		
		interprete.put("x", a);
		if((double)interprete.eval(fx)<T) {
			interprete.put("x", b);
			if((double)interprete.eval(fx)>T) {
				z = (a+b)/2;
				while(Math.abs(z-a)>=maxError) {
					interprete.put("x", z);
					if((double)interprete.eval(fx)<=T) {
						a = z;
					}
					if((double)interprete.eval(fx)>=T) {
						b = z;
					}
					z = (a+b)/2;
					addDataToChart(this.data, time, steps++);
				}
			}
		}
		
		double elapsed = getElapsed(time, getTime());
		arrayValue.add("x: " + z + "\n");
		arrayValue.add(String.valueOf(steps));
		arrayValue.add(String.valueOf(elapsed));
		arrayValue.add(String.valueOf("f(x): " + (double)interprete.eval(fx) + "\n"));
		
		return arrayValue;
	}
	
	public List<String> generateAlea() throws IOException{
		
		mergeSort("", 1);
		
		return arrayValue;
	}
	
	public List<String> searchElement(int value, List<Integer> values) {
		cont = 0;
		int pos = searchValue(value, values, 0, values.size() - 1);
		arrayValue = new ArrayList<String>();
		arrayValue.add("Posición: " + ((pos != -1) ? pos : "No encontrado") + "\n");
		arrayValue.add("" + cont);
		return arrayValue;
	}
	
	public String searchInTree(int value, Tree t) {
		return t.find(value) ? "Elemento existente\n" : "Elemento inexistente\n";
	}
	
	public List<String> createGraph(String value) {
		arrayValue = new ArrayList<String>();
		if(value.matches("(^[0-9]+)*\\->([0-9]+)*$")) { //Insert relations
			Pattern p = Pattern.compile("(^[0-9]+)*\\->([0-9]+)*$");
			Matcher m = p.matcher(value);
			m.find();
			String v1 = m.group(1);
			String v2 = m.group(2);
			
			arrayValue.add(insertGraph(Integer.parseInt(v1), Integer.parseInt(v2)) ? "Relación " + v1 + "-" + v2 + " creada\n" : "Relación " + v1 + "-" + v2 + " existente\n");
		} else if(value.matches("rel:([0-9]+)*$")) { //Show relations
			Pattern p = Pattern.compile("rel:([0-9]+)*$");
			Matcher m = p.matcher(value);
			m.find();
			if(gr != null) {
				String rel = gr.showRel(Integer.parseInt(m.group(1)));
				arrayValue.add(rel == "" ? "Nodo " + m.group(1) + " no encontrado\n" : rel + "\n");
			}
		} else if(value.matches("delrel:([0-9]+)*\\->([0-9]+)*$")) {
			Pattern p = Pattern.compile("del:([0-9]+)*\\->([0-9]+)*$");
			Matcher m = p.matcher(value);
			m.find();
			if(gr != null) {
				arrayValue.add(gr.delRel(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))) ? "Relación " + m.group(1) + "-" + m.group(2) + " eliminada\n" : "Relación " + m.group(1) + "-" + m.group(2) + " no eliminada\n");
			}
		} else if(value.matches("deln:([0-9]+)*$")) {
			Pattern p = Pattern.compile("deln:([0-9]+)*$");
			Matcher m = p.matcher(value);
			m.find();
			if(gr != null) {
				arrayValue.add(gr.delN(Integer.parseInt(m.group(1))) ? "Nodo " + m.group(1) + " eliminado\n" : "Nodo " + m.group(1) + " no eliminado\n");
			}
		} else if(value.matches("relall")) {
			if(gr != null) {
				arrayValue.add(gr.showAllRel());
			}
		}
		return arrayValue;
	}
	
	public boolean insertGraph(int value, int next) {
		if(gr == null) {
			gr = new Graph(value, next);
			return true;
		} else {
			return gr.insert(value, next);
		}
	}
	
	public double francoisPi() {
		z = Math.pow(2, n-(1/2)) * Math.sqrt(1-Math.sqrt(1-(Math.pow(4, 1-n)) * (z * z)));
		n++;
		return z;
	}

	public String horner(String poli, double z) {
		String val[] = poli.split(",");
		double tot = 0;
		for(int i = 0; i < val.length; i++) {
			tot = tot * z + Double.parseDouble(val[i]);
		}
		return "Resultado: " + tot + "\n";
	}
	
	List<String> arrayNH;
	//Method Newton-Hörner
	public String nhorner(String poli, double x0, int ni) {
		String sub[] = poli.split(" ");
		double ee = Double.parseDouble(sub[1]);
		List<String> val = Arrays.asList(sub[0].split(","));
		List<Double> xy;
		List<Double> roots = new ArrayList<Double>();
		double x, error, xnew;
		boolean rn = false;
		int i;
		n = val.size() - 1;
		for(int iter = 0; iter < n; iter++) {
			x = x0;
			for(i =0; i<ni; i++) {
				xy = derHoner(val, x);
				xnew = x - xy.get(0)/xy.get(1);
				error = Math.abs((x-xnew)/x);
				if(error <= ee) {
					break;
				}
				x = xnew;
			}
			if(!rn && i >= ni) {
				rn = true;	//Change to true if there are imaginary roots
			}
			
			//Deflation
			//xy = derHoner(val, x);
			roots.add(x); //Save roots
			val = arrayNH;
		}
		return "Raíces: " + roots.toString() + ((rn) ? " Existen raíces negativas" : "");
	}
	
	public String liMethod(String elem) {
		
		String data[] = elem.split(";");
		String value[];
		double b[] = new double[data.length];
		List<List<Double>> array = new ArrayList<List<Double>>();
		for(int i = 0; i < data.length; i++) {
			value = data[i].split(" ");
			array.add(i, new ArrayList<>());
			for(int j = 0; j < value.length - 1; j++) {
				array.get(i).add(Double.parseDouble(value[j]));
			}
			b[i] = Double.parseDouble(value[data.length]);
		}

		List<List<Double>> U = new ArrayList<List<Double>>();
		List<List<Double>> L = new ArrayList<List<Double>>();
		
		int n = array.size();
		U.add(0, array.get(0)); //Copy first row
		
		//First column and row
		double val;
		double u11 = array.get(0).get(0);
		for(int i = 0; i < n; i++) {
			L.add(i, new ArrayList<>());
			if(i!=0) {
				U.add(i, new ArrayList<>());
			}
			for(int j = 0; j < n; j++) {
				if(j==i) {
					L.get(i).add(j, 1.0);
				}else if(j==0) {
					val = array.get(i).get(0) / u11;
					L.get(i).add(j, val);
				}else {
					L.get(i).add(j, 0.0);
				}

				if(i!=0) {
					U.get(i).add(0.0);
				}
			}
		}
		
		double sig = 0;
		for(int i = 1; i < n; i++) {
			for(int j = i + 1; j < n; j++) {
				sig = 0;
				for(int k = 0; k < i; k++)
				{
					sig += L.get(i).get(k) * U.get(k).get(i);
				}
				val = array.get(i).get(i) - sig;
				U.get(i).set(i, val);
				
				sig = 0;
				for(int k = 0; k < i; k++)
				{
					sig += L.get(i).get(k) * U.get(k).get(j);
				}
				val = array.get(i).get(j) - sig;
				U.get(i).set(j, val);
				
				sig = 0;
				for(int k = 0; k < i; k++)
				{
					sig += L.get(j).get(k) * U.get(k).get(i);
				}
				val = 1/U.get(i).get(i)*(array.get(j).get(i) - sig);
				L.get(j).set(i, val);
			}
		}
		sig = 0;
		for(int k = 0; k < n; k++)
		{
			sig += L.get(n-1).get(k)*U.get(k).get(n-1);
		}
		val = array.get(n-1).get(n-1) - sig;
		U.get(n-1).set(n-1, val);
		
		//Calculate y[i]
		double y[] = new double[n];
		y[0] = 1/L.get(0).get(0) * b[0];
		for(int i = 1; i < n; i++) {
			sig = 0;
			for(int j = 0; j < i; j++) {
				sig += L.get(i).get(j) * y[j];
			}
			y[i] = 1/L.get(i).get(i) * (b[i] - sig);
		}
		
		//Calculate x[i]
		double x[] = new double[n];
		x[n-1] = 1/U.get(n-1).get(n-1) * y[n-1];
		String resx = x[n-1] + " ]";
		for(int i = n-2; i >= 0; i--) {
			sig = 0;
			for(int j = i + 1; j < n; j++) {
				sig += U.get(i).get(j) * x[j];
			}
			x[i] = 1/U.get(i).get(i) * (y[i] - sig);
			resx = x[i] + " " + resx;
		}
				
		return "Matriz: " + array.toString() + "\nU: " + U.toString() + "\nL: " + L.toString() + "\nSol.: [ " + resx;
	}
	
	public String QR(String var) {
		//Create array
		String ret = "";
		String data[] = var.split(";");
		String value[];
		double b[] = new double[data.length];
		List<List<Double>> array = new ArrayList<List<Double>>();
		
		double norm = 0.0;
		
		List<List<Double>> U = new ArrayList<List<Double>>();
		List<List<Double>> e = new ArrayList<List<Double>>();
		
		for(int i = 0; i < data.length; i++) {
			value = data[i].split(" ");
			array.add(i, new ArrayList<>());
			for(int j = 0; j < value.length - 1; j++) {
				array.get(i).add(Double.parseDouble(value[j]));
				if(j == 0) {
					U.add(i, new ArrayList<>());
					U.get(j).add(Double.parseDouble(value[j]));// u1 = v1
					norm += Math.pow(U.get(j).get(i),2);
				}				
			}
			b[i] = Double.parseDouble(value[data.length]);
		}
		norm = Math.sqrt(norm);

		e.add(new ArrayList<>());
		for(int j = 0; j < n; j++) {
			e.get(0).add(U.get(0).get(j)/norm);
		}
		
		//Gram Schmidt
		int n = array.size();
		List<Double> aux = new ArrayList<Double>();
		
		double sig, vkuj;
		//Create e1
		for(int i = 1; i < n; i++) {
			sig = 0.0;
			aux = new ArrayList<Double>();
			for(int j = 0; j < i; j++) {
				
				vkuj = 0;
				norm = 0.0;
				for(int v = 0; v < n; v++) {
					double ujv = U.get(j).get(v);
					vkuj += array.get(v).get(i) * ujv;  //vk,uj
					norm += Math.pow(ujv, 2); //Get norm 
				}
				for(int utimesdiv = 0; utimesdiv < n; utimesdiv++) {
					if(j == 0) {
						aux.add(utimesdiv, vkuj/norm * U.get(j).get(utimesdiv));
					} else {
						sig = vkuj/norm * U.get(j).get(utimesdiv);
						aux.set(utimesdiv, aux.get(utimesdiv) + sig);
					}
				}
			}
			
			sig = 0;
			for(int k = 0; k < n; k++) {
				norm = array.get(k).get(i) - aux.get(k);
				U.get(i).add(norm);
				sig += Math.pow(U.get(i).get(k), 2);
			}
			
			sig = Math.sqrt(sig);
			e.add(new ArrayList<>());
			for(int k = 0; k < n; k++) {
				e.get(i).add(U.get(i).get(k) / sig);
			}
		}
		//Calculate R
		List<List<Double>> R = new ArrayList<List<Double>>();
		List<Double> c = new ArrayList<Double>();
		List<Double> x = new ArrayList<Double>();
		for(int k = 0; k < n; k++) {
			R.add(new ArrayList<>());
			sig = 0.0;
			for(int i = 0; i < n; i++) {
				norm = 0.0;
				for(int j = 0; j < n && i >= k; j++) {
					norm += array.get(j).get(i) * e.get(k).get(j);
				}
				R.get(k).add(norm);
				//Get c
				sig += e.get(k).get(i) * b[i];
			}
			c.add(sig);
			x.add(sig);
		}
		
		//Calculate x
		for(int i = n - 1; i >= 0; i--) {
			sig = 0;
			for(int j = i + 1; j < n; j++) {
				sig += R.get(i).get(j) * x.get(j);
			}
			x.set(i, (c.get(i) - sig)/R.get(i).get(i));
		}
		
		ret = "Q': " + e.toString() + "\nR: " + R.toString() + "\nC: " + c.toString() + "\nSol.: " + x.toString();
		
		return ret;
	}
	
	public String strassen(String var){
		//Create array

		Pattern p = Pattern.compile("\\[([; 0-9]+)\\]\\[([; 0-9]+)\\]*$");
		Matcher mat = p.matcher(var);
		mat.find();
		
		String dataA[] = mat.group(1).split(";");
		String dataB[] = mat.group(2).split(";");
		
		String valueA[];
		String valueB[];
		
		List<List<Integer>> A = new ArrayList<List<Integer>>();
		List<List<Integer>> B = new ArrayList<List<Integer>>();
		int r1 = dataA.length; //Rows of first array
		
		int n = ((dataA.length > dataB.length) ? dataA.length : dataB.length);
		if(n%2 != 0) { //Add even number of elements
			n++;
		}
		valueA = dataA[0].split(" ");
		valueB = dataB[0].split(" ");
		int c2 = valueB.length; //Columns of second array
		int m = ((valueA.length > valueB.length) ? valueA.length : valueB.length);
		if(m%2 != 0) { //Add even number of elements
			m++;
		}
		for(int i = 0; i < n; i++) {
			valueA = dataA.length > i ? dataA[i].split(" ") : null;
			valueB = dataB.length > i ? dataB[i].split(" ") : null;
			A.add(i, new ArrayList<>());
			B.add(i, new ArrayList<>());
			for(int j = 0; j < m; j++) {
				A.get(i).add(j, Integer.parseInt(valueA != null && valueA.length > j ? valueA[j] : "0")); //Fill with zero if there are no more values -> Square matrix
				B.get(i).add(j, Integer.parseInt(valueB != null && valueB.length > j ? valueB[j] : "0"));
			}
		}
		
		Strassen s = new Strassen();
		
		List<List<Integer>> C = s.multiply(A, B);
		String res = "Resultado: [";
		//Remove zeros if any
		for(int i = 0; i < r1; i++) {
			res += "[";
			for(int j = 0; j < c2; j++) {
				res += C.get(i).get(j)+ ((j == (c2 - 1) ? "" : ", "));
			}
			res += "]";
		}
		res += "]";
		return res;
	}
	
	private List<Double> derHoner(List<String> x, double x0) {
		double y = Double.parseDouble(x.get(0)); //P(x0)
		double z = Double.parseDouble(x.get(0)); //P'(x0)->Q(x0)
		List<Double> xy = new ArrayList<Double>();
		arrayNH = new ArrayList<String>();
		arrayNH.add(String.valueOf(y));
		for(int j = 1; j < x.size() - 1; j++) {
			y = x0*y + Double.parseDouble(x.get(j));
			z = x0*z + y;
			arrayNH.add(String.valueOf(y));
		}
		y = x0*y + Double.parseDouble(x.get(x.size()-1));
		xy.add(y);
		xy.add(z);
		return xy;
	}
	
	private List<Integer> generateAlea(int total) throws IOException {
		List<Integer> values = new ArrayList<Integer>();
		for(int i = 0; i < total; i++) {
			int ran = (int)(Math.random() * 500);
			values.add(ran);
			treeElements.insert(ran);
		}
		return values;
	}
	
	private int searchValue(int value, List<Integer> values, int start, int end) {
		cont++;
		if(values.get(end) == value || values.get(start) == value) {
			return values.get(end) == value ? end : start;
		}
		if(start == (end-1)) {
			return -1; //Not found
		}
		//Check before changing index -> Checks if changing value is equal to the one that we are searching
		if(values.get(start + (end - start)/2)== value || values.get((end - start)/2) == value){
			return values.get((end - start)/2) == value ? (end - start)/2 : start + (end - start)/2;
		}
		else if(value > values.get(start)) {//start + (end - start)/2)) {
			//Avoid moving wrong index
			if(value > values.get(start + (end - start)/2)) {
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
	
	private List<Integer> L = new ArrayList<Integer>();
    private List<Integer> R = new ArrayList<Integer>();
	private void merge(List<Integer> arr, int l, int m, int r) {
        int i, j, k;
        int n1 = m - l + 1;
        int n2 =  r - m;

        /* Copy data to  arrays L[] and R[]*/
        for (i = 0; i < n1; i++) {
        	addDataToChart(this.data, time, cont++);
            L.add(i, arr.get(l + i));
        }
        for (j = 0; j < n2; j++) {
        	addDataToChart(this.data, time, cont++);
            R.add(j, arr.get(m + 1 + j));
        }

        /* Merge the  arrays back into [l..r]*/
        i = 0; // Initial index of first 
        j = 0; // Initial index of second 
        k = l; // Initial index of merged 
        while (i < n1 && j < n2) {
        	addDataToChart(this.data, time, cont++);
            if (L.get(i) <= R.get(j)) {
                arr.set(k, L.get(i));
                i++;
            }
            else {
                arr.set(k, R.get(j));
                j++;
            }
            k++;
        }

        /* Copy the remaining elements of L[], if there
           are any */
        while (i < n1) {
        	addDataToChart(this.data, time, cont++);
        	arr.set(k, L.get(i));
            i++;
            k++;
        }

        /* Copy the remaining elements of R[], if there
           are any */
        while (j < n2) {
        	addDataToChart(this.data, time, cont++);
        	arr.set(k, R.get(j));
            j++;
            k++;
        }
    }
	
	private void quickSort(List<Integer> arr, int low, int high) {
        if (low < high) {
            /* pi is partitioning index, arr[p] is now
               at right place */
            int pi = partition(arr, low, high);
            addDataToChart(this.data, time, cont++);
            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
	
	private int partition (List<Integer> arr, int low, int high) {
        int pivot = arr.get(high);    // pivot
        int i = (low - 1);  // Index of smaller element
        int aux;

        for (int j = low; j <= high- 1; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr.get(j) <= pivot) {
                i++;    // increment index of smaller element
                aux = arr.get(j);
                arr.set(j, arr.get(i));
                arr.set(i, aux);
            }
            addDataToChart(this.data, time, cont++);
        }
        aux = arr.get(high);
        arr.set(high, arr.get(i + 1));
        arr.set(i + 1, aux);
        
        return (i + 1);
    }
	
	private void mergeSortAlgorithm(List<Integer> A, int l, int r) {
        if (l < r) {
            // Same as (l+r)/2, but avoids overflow for
            // large l and h
            int m = l+(r-l)/2;
            addDataToChart(this.data, time, cont++);
            // Sort first and second halves
            mergeSortAlgorithm(A, l, m);
            mergeSortAlgorithm(A, m+1, r);

            merge(A, l, m, r);
        }
    }
	
	// To  a subtree rooted with node i
    private void heapify(List<Integer> arr, int n, int i) {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr.get(l) > arr.get(largest)) {
            largest = l;
        }

        // If right child is larger than largest so far
        if (r < n && arr.get(r) > arr.get(largest)) {
            largest = r;
        }

        // If largest is not root
        if (largest != i) {
            int auxA = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, auxA);

            // Recursively  the affected sub-tree
            heapify(arr, n, largest);
        }
    }
	
	private List<Integer> readFile(String fName) throws FileNotFoundException {
		
		List<Integer> values = new ArrayList<Integer>();
		BufferedReader br = new BufferedReader(new FileReader(fName));
		
		try {
			
			String line = br.readLine();
			
			do {
				values.add(Integer.valueOf(line));
				line = br.readLine();
			} while(line != null);


		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return values;
	}

	private List<String> readValues(String n){
		List<String> values = new ArrayList<String>();
		values = Arrays.asList(n.split("\t"));
		return values;
	}
	
	private long getTime() {
		long time = System.currentTimeMillis();
		return time;
	}
	
	private double getElapsed(long start, long end) {
		double time = (end - start);
		return time;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addDataToChart(XYChart.Series data, long time, long n) {
		data.getData().add(new XYChart.Data(String.valueOf(n), getElapsed(time, getTime())));
	}
	
}

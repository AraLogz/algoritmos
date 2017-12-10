package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

import javax.script.ScriptException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class mainController implements Initializable{

	methods algorithm = new methods();
	String working = "Calculando";
	String newInstruction = "Esperando Instrucción";
	
	@FXML
	private Button btnChoose;
	@FXML
	private Button btnExec;
	
	@FXML
	private TextField txtFilePath;
	@FXML
	private TextField txtTime;
	@FXML
	private TextField txtSteps;
	
	@FXML
	private TextArea txtareaInputData;
	@FXML
	private TextArea txtareaOutputData;
	
	@FXML
	private RadioButton rbtnReadFile;
	@FXML
	private RadioButton rbtnEnterData;
	
	@FXML
	private Label lblInput;
	@FXML
	private Label lblStatus;
	
	@FXML
	private LineChart<String,Number> lchartGraphics;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtareaInputData.requestFocus();
		txtTime.setEditable(false);
		txtareaOutputData.setEditable(false);
		txtSteps.setEditable(false);
		txtFilePath.setEditable(false);
		lchartGraphics.setAnimated(false);
		lchartGraphics.setCreateSymbols(false);
		lchartGraphics.setHorizontalGridLinesVisible(false);
		lchartGraphics.setVerticalGridLinesVisible(false);
	}
	
	public void closeApp(ActionEvent event) {
		Stage stage = (Stage) btnChoose.getScene().getWindow();
		stage.close();
	}
	
	public void fileChoose(ActionEvent event) {			
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(new JFrame());
		
		if(result == JFileChooser.APPROVE_OPTION) {
			txtFilePath.setText(chooser.getSelectedFile().getAbsolutePath());
		}
	}
	
	public void showRElements(ActionEvent event) {
		if(rbtnReadFile.isSelected()) {
			txtareaInputData.setVisible(false);
			lblInput.setVisible(false);
			btnChoose.setVisible(true);
			txtFilePath.setVisible(true);
		}
	}
	
	public void showEElements(ActionEvent event) {
		if(rbtnEnterData.isSelected()) {
			txtareaInputData.setVisible(true);
			lblInput.setVisible(true);
			btnChoose.setVisible(false);
			txtFilePath.setVisible(false);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void primeFactors(ActionEvent event) {
		algorithm = new methods();
		
		if(!txtareaInputData.getText().isEmpty()) {
			
			long n = Long.valueOf(txtareaInputData.getText().toString());
			
			Task getFPrimes = new Task<Void>() {
				@Override public Void call() {
					
					List<String> values = algorithm.primeFactors(n);
					txtareaOutputData.setText(values.get(0));
					txtSteps.setText(values.get(2));
					txtTime.setText(values.get(1));
					
					Platform.runLater(new Runnable() {
						public void run() {
							
							XYChart.Series data = algorithm.getData();
							lchartGraphics.setTitle("Factores Primos");
							lchartGraphics.getData().addAll(data);
							lblStatus.setText(newInstruction);
							
						}
					});
					
					return null;
				}
			};
			lblStatus.setText(working);
			new Thread(getFPrimes).start();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void primeNumbers(ActionEvent e) {
		algorithm = new methods();
		
		if(!txtareaInputData.getText().isEmpty()) {
			
			long n = Long.valueOf(txtareaInputData.getText().toString());
			
			Task getPrimes = new Task<Void>() {
				@Override public Void call() {

					List<String> values = algorithm.primeNumbers(n);
					txtareaOutputData.setText(values.get(0));
					txtSteps.setText(values.get(2));
					txtTime.setText(values.get(1));
					
					Platform.runLater(new Runnable() {
						public void run() {
							
							XYChart.Series data = algorithm.getData();
							lchartGraphics.setTitle("Num. Primos");
							lchartGraphics.getData().addAll(data);
							lblStatus.setText(newInstruction);
							
						}
					});
					
					return null;
				}
			};
			lblStatus.setText(working);
			new Thread(getPrimes).start();
		}
	}
	
	public void insertionSort(ActionEvent e) throws FileNotFoundException {
		algorithm = new methods();
		
		if(!txtFilePath.getText().isEmpty()) {
			Task<Void> sort = new Task<Void>() {
				@Override public Void call() throws IOException {
	
					List<String> values = algorithm.insertionSort(txtFilePath.getText());
					txtareaOutputData.setText(values.get(0));
					txtareaOutputData.appendText(values.get(1));
					txtSteps.setText(values.get(3));
					txtTime.setText(values.get(2));
					
					Platform.runLater(new Runnable() {
						@SuppressWarnings("unchecked")
						public void run() {
							
							@SuppressWarnings("rawtypes")
							XYChart.Series data = algorithm.getData();
							lchartGraphics.setTitle("Insertion");
							lchartGraphics.getData().addAll(data);
							lblStatus.setText(newInstruction);
							
						}
					});
					
					return null;
				}
			};
			lblStatus.setText(working);
			new Thread(sort).start();
		}
		
	}
	
	public void selectionSort(ActionEvent e) throws FileNotFoundException {
		algorithm = new methods();
		
		if(!txtFilePath.getText().isEmpty()) {
			Task<Void> sort = new Task<Void>() {
				@Override public Void call() throws IOException {
	
					List<String> values = algorithm.selectonSort(txtFilePath.getText());
					txtareaOutputData.setText(values.get(0));
					txtareaOutputData.appendText(values.get(1));
					txtSteps.setText(values.get(3));
					txtTime.setText(values.get(2));
					
					Platform.runLater(new Runnable() {
						@SuppressWarnings("unchecked")
						public void run() {
							
							@SuppressWarnings("rawtypes")
							XYChart.Series data = algorithm.getData();
							lchartGraphics.setTitle("Selection");
							lchartGraphics.getData().addAll(data);
							lblStatus.setText(newInstruction);
							
						}
					});
					
					return null;
				}
			};
			lblStatus.setText(working);
			new Thread(sort).start();
		}
		
	}
	
	public void bubbleSort(ActionEvent e) throws FileNotFoundException {
		algorithm = new methods();
		
		if(!txtFilePath.getText().isEmpty()) {
			Task<Void> sort = new Task<Void>() {
				@Override public Void call() throws IOException {
	
					List<String> values = algorithm.bubbleSort(txtFilePath.getText());
					txtareaOutputData.setText(values.get(0));
					txtareaOutputData.appendText(values.get(1));
					txtSteps.setText(values.get(3));
					txtTime.setText(values.get(2));
					
					Platform.runLater(new Runnable() {
						@SuppressWarnings("unchecked")
						public void run() {
							
							@SuppressWarnings("rawtypes")
							XYChart.Series data = algorithm.getData();
							lchartGraphics.setTitle("Bubble");
							lchartGraphics.getData().addAll(data);
							lblStatus.setText(newInstruction);
							
						}
					});
					
					return null;
				}
			};
			lblStatus.setText(working);
			new Thread(sort).start();
		}
		
	}
	
	public void heapSort(ActionEvent e) throws FileNotFoundException {
		algorithm = new methods();
		
		if(!txtFilePath.getText().isEmpty()) {
			Task<Void> sort = new Task<Void>() {
				@Override public Void call() throws IOException {
	
					List<String> values = algorithm.heapSort(txtFilePath.getText());
					txtareaOutputData.setText(values.get(0));
					txtareaOutputData.appendText(values.get(1));
					txtSteps.setText(values.get(3));
					txtTime.setText(values.get(2));
					
					Platform.runLater(new Runnable() {
						@SuppressWarnings("unchecked")
						public void run() {
							
							@SuppressWarnings("rawtypes")
							XYChart.Series data = algorithm.getData();
							lchartGraphics.setTitle("Heap");
							lchartGraphics.getData().addAll(data);
							lblStatus.setText(newInstruction);
							
						}
					});
					
					return null;
				}
			};
			lblStatus.setText(working);
			new Thread(sort).start();
		}
		
	}
	
	public void mergeSort(ActionEvent e) throws FileNotFoundException {
		algorithm = new methods();
		
		if(!txtFilePath.getText().isEmpty()) {
			Task<Void> sort = new Task<Void>() {
				@Override public Void call() throws IOException {
	
					List<String> values = algorithm.mergeSort(txtFilePath.getText(), 0);
					txtareaOutputData.setText(values.get(0));
					txtareaOutputData.appendText(values.get(1));
					txtSteps.setText(values.get(3));
					txtTime.setText(values.get(2));
					
					Platform.runLater(new Runnable() {
						@SuppressWarnings("unchecked")
						public void run() {
							
							@SuppressWarnings("rawtypes")
							XYChart.Series data = algorithm.getData();
							lchartGraphics.setTitle("Merge");
							lchartGraphics.getData().addAll(data);
							lblStatus.setText(newInstruction);
							
						}
					});
					
					return null;
				}
			};
			lblStatus.setText(working);
			new Thread(sort).start();
		}
		
	}
	
	public void quickSort(ActionEvent e) throws FileNotFoundException {
		algorithm = new methods();
		
		if(!txtFilePath.getText().isEmpty()) {
			Task<Void> sort = new Task<Void>() {
				@Override public Void call() throws IOException {
	
					List<String> values = algorithm.quickSort(txtFilePath.getText());
					txtareaOutputData.setText(values.get(0));
					txtareaOutputData.appendText(values.get(1));
					txtSteps.setText(values.get(3));
					txtTime.setText(values.get(2));
					
					Platform.runLater(new Runnable() {
						@SuppressWarnings("unchecked")
						public void run() {
							
							@SuppressWarnings("rawtypes")
							XYChart.Series data = algorithm.getData();
							lchartGraphics.setTitle("Merge");
							lchartGraphics.getData().addAll(data);
							lblStatus.setText(newInstruction);
							
						}
					});
					
					return null;
				}
			};
			lblStatus.setText(working);
			new Thread(sort).start();
		}
		
	}
	
	public void newtonMethod(ActionEvent e) {
		algorithm = new methods();
		
		if(!txtareaInputData.getText().isEmpty()) {
			Task<Void> newton = new Task<Void>() {
				@Override public Void call() throws IOException {
	
					List<String> values = algorithm.newton(txtareaInputData.getText().toString());
					txtareaOutputData.setText(values.get(0));
					txtSteps.setText(values.get(1));
					txtTime.setText(values.get(2));
					
					Platform.runLater(new Runnable() {
						@SuppressWarnings("unchecked")
						public void run() {
							
							@SuppressWarnings("rawtypes")
							XYChart.Series data = algorithm.getData();
							lchartGraphics.setTitle("Newton");
							lchartGraphics.getData().addAll(data);
							lblStatus.setText(newInstruction);
							
						}
					});
					
					return null;
				}
			};
			lblStatus.setText(working);
			new Thread(newton).start();
		}
	}
	
	public void biseccMethod(ActionEvent e) throws ScriptException {
		algorithm = new methods();
		
		if(!txtareaInputData.getText().isEmpty()) {
			Task<Void> bisecc = new Task<Void>() {
				@Override public Void call() throws IOException, ScriptException {
	
					List<String> values = algorithm.bisecc(txtareaInputData.getText().toString());
					txtareaOutputData.setText(values.get(0));
					txtSteps.setText(values.get(1));
					txtareaOutputData.appendText(values.get(3));
					txtTime.setText(values.get(2));
					
					Platform.runLater(new Runnable() {
						@SuppressWarnings("unchecked")
						public void run() {
							
							@SuppressWarnings("rawtypes")
							XYChart.Series data = algorithm.getData();
							lchartGraphics.setTitle("Bisección");
							lchartGraphics.getData().addAll(data);
							lblStatus.setText(newInstruction);
							
						}
					});
					
					return null;
				}
			};
			lblStatus.setText(working);
			new Thread(bisecc).start();
		}
	}
	methods aSearch = new methods();
	public void alea(ActionEvent e) throws IOException {

		Task<Void> alea = new Task<Void>() {
			@Override public Void call() throws IOException, ScriptException {

				List<String> values = aSearch.generateAlea();
				txtareaOutputData.setText(values.get(0));
				txtareaOutputData.appendText(values.get(1));
				
				Platform.runLater(new Runnable() {
					public void run() {
						lblStatus.setText(newInstruction);
						
					}
				});
				//lblStatus.setText(newInstruction);
				//txtareaOutputData.appendText(values.get(2));
				//txtSteps.setText(values.get(3));
				return null;
			}
		};
		lblStatus.setText(working);
		new Thread(alea).start();
	}
	
	public void search(ActionEvent e) throws IOException {
		algorithm = new methods();
		if(!txtareaInputData.getText().isEmpty()) {
			Task<Void> search = new Task<Void>() {
				@Override public Void call() throws IOException, ScriptException {
					List<Integer> sortedElements = aSearch.getsortedElements();
					List<String> values = aSearch.searchElement(Integer.parseInt(txtareaInputData.getText()), sortedElements);
					txtareaOutputData.appendText(values.get(0));
					txtSteps.setText(values.get(1));
					Platform.runLater(new Runnable() {
						public void run() {
							lblStatus.setText(newInstruction);
							
						}
					});
					return null;
				}
			};
			lblStatus.setText(working);
			new Thread(search).start();
		}
	}
	
	public void binarySearch(ActionEvent e) throws IOException {

		if(!txtareaInputData.getText().isEmpty()) {
			Task<Void> search = new Task<Void>() {
				@Override public Void call() throws IOException, ScriptException {
					Tree sortedElements = aSearch.gettreeElements();
					String status = aSearch.searchInTree(Integer.parseInt(txtareaInputData.getText()), sortedElements);
					txtareaOutputData.appendText(status);
					Platform.runLater(new Runnable() {
						public void run() {
							lblStatus.setText(newInstruction);
							
						}
					});
					return null;
				}
			};
			lblStatus.setText(working);
			new Thread(search).start();
		}
	}
	public void createGraph(ActionEvent e) {
		if(!txtareaInputData.getText().isEmpty()) {
			Task<Void> createG = new Task<Void>() {
				@Override public Void call() throws IOException, ScriptException {
					
					List<String> values = algorithm.createGraph(txtareaInputData.getText());
					txtareaOutputData.appendText(values.get(0));
					Platform.runLater(new Runnable() {
						public void run() {
							lblStatus.setText(newInstruction);
							txtareaInputData.setText("");
						}
					});
					return null;
				}
			};
			lblStatus.setText(working);
			new Thread(createG).start();
		}
	}
	
	public void calcPi(ActionEvent e) {
		if(!txtareaInputData.getText().isEmpty()) {
			Task<Void> createG = new Task<Void>() {
				@Override public Void call() throws IOException, ScriptException {
					int tot = Integer.parseInt(txtareaInputData.getText());
					for(int i = 0; i < tot; i++) {
						txtareaOutputData.appendText("Valor("+ i +"): " + algorithm.francoisPi() + "\n");
					}
					Platform.runLater(new Runnable() {
						public void run() {
							lblStatus.setText(newInstruction);
							txtareaInputData.setText("");
						}
					});
					return null;
				}
			};
			lblStatus.setText(working);
			new Thread(createG).start();
		}
	}
	
	public void calcHorner(ActionEvent e) {
		if(!txtareaInputData.getText().isEmpty()) {
			Task<Void> createG = new Task<Void>() {
				@Override public Void call() throws IOException, ScriptException {
					String elem[] = txtareaInputData.getText().split(" ");
					String value = algorithm.horner(elem[0], Double.parseDouble(elem[1]));
					txtareaOutputData.setText(value);
					Platform.runLater(new Runnable() {
						public void run() {
							lblStatus.setText(newInstruction);
							txtareaInputData.setText("");
						}
					});
					return null;
				}
			};
			lblStatus.setText(working);
			new Thread(createG).start();
		}
	}
	
	public void calcNHorner(ActionEvent e) {
		if(!txtareaInputData.getText().isEmpty()) {
			Task<Void> createG = new Task<Void>() {
				@Override public Void call() throws IOException, ScriptException {
					String value = algorithm.nhorner(txtareaInputData.getText(), 0, 100);;
					txtareaOutputData.setText(value);
					Platform.runLater(new Runnable() {
						public void run() {
							lblStatus.setText(newInstruction);
							txtareaInputData.setText("");
						}
					});
					return null;
				}
			};
			lblStatus.setText(working);
			new Thread(createG).start();
		}
	}
	
	public void LUMethod(ActionEvent e) {
		algorithm = new methods();
		if(!txtareaInputData.getText().isEmpty()) {
			Task<Void> createG = new Task<Void>() {
				@Override public Void call() throws IOException, ScriptException {
					String value = algorithm.liMethod(txtareaInputData.getText());
					txtareaOutputData.setText(value);
					Platform.runLater(new Runnable() {
						public void run() {
							lblStatus.setText(newInstruction);
							txtareaInputData.setText("");
						}
					});
					return null;
				}
			};
			lblStatus.setText(working);
			new Thread(createG).start();
		}
	}
	
	public void QRMethod(ActionEvent e) {
		algorithm = new methods();
		if(!txtareaInputData.getText().isEmpty()) {
			Task<Void> createG = new Task<Void>() {
				@Override public Void call() throws IOException, ScriptException {
					String value = algorithm.QR(txtareaInputData.getText());
					txtareaOutputData.setText(value);
					Platform.runLater(new Runnable() {
						public void run() {
							lblStatus.setText(newInstruction);
							txtareaInputData.setText("");
						}
					});
					return null;
				}
			};
			lblStatus.setText(working);
			new Thread(createG).start();
		}
	}
	
	public void StrassenMethod(ActionEvent e) {
		algorithm = new methods();
		if(!txtareaInputData.getText().isEmpty()) {
			Task<Void> createG = new Task<Void>() {
				@Override public Void call() throws IOException, ScriptException {
					String value = algorithm.strassen(txtareaInputData.getText());
					txtareaOutputData.setText(value);
					Platform.runLater(new Runnable() {
						public void run() {
							lblStatus.setText(newInstruction);
							txtareaInputData.setText("");
						}
					});
					return null;
				}
			};
			lblStatus.setText(working);
			new Thread(createG).start();
		}
	}
	
	public void restart() {
		txtareaInputData.setText("");
		txtareaOutputData.setText("");
		txtTime.setText("");
		lchartGraphics.getData().clear();
		txtSteps.setText("");
	}
}

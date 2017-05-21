package it.polito.tdp.porto;

import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Author> boxPrimo;

    @FXML
    private ComboBox<Author> boxSecondo;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCoautori(ActionEvent event) {
    	//model.provaCreaGrafo();
    	model.provaCreaGrafo();
    	List<Author> allAutori = model.getAllAutori();
    	List<Author> autori = model.trovaCoAutori(boxPrimo.getValue());
    	allAutori.removeAll(autori);
    	boxSecondo.getItems().addAll(allAutori);
    	for(Author a :autori){
    		txtResult.appendText(a.toString()+"\n");
    	}	
    }

    @FXML
    void handleSequenza(ActionEvent event) {
    	txtResult.appendText(model.trovaSequenza(boxPrimo.getValue(),boxSecondo.getValue()).toString()+"\n");
    	

    }
    
    @FXML
    void imposta2Box(ActionEvent event) {
//    	if(boxPrimo.getValue()== null){
//    		return;
//    	}
//    	model.provaCreaGrafo();
//    	List<Author> allAutori = model.getAllAutori();
//    	List<Author> autori = model.trovaCoAutori(boxPrimo.getValue());
//    	allAutori.removeAll(autori);
//    	boxSecondo.getItems().addAll(allAutori);
    	
    	
    }

    @FXML
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		boxPrimo.getItems().addAll(model.getAllAutori());
	}
}

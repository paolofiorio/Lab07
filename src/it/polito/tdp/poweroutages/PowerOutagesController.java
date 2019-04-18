package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PowerOutagesController {

	private Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private ComboBox<Nerc> cmbNerc;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtHours;

    @FXML
    private Button btnCalcola;

    @FXML
    void doCalcola(ActionEvent event) {
    	txtResult.clear();
    	
    	try {
    		Nerc selezionato= cmbNerc.getSelectionModel().getSelectedItem();
    		if(selezionato==null) {
    			txtResult.appendText("Errore: selezionare un Nerc valido");
    		}
    		int y=(Integer.parseInt(txtYears.getText()));
    		int anni= model.getAnniList().size();
    		if(y<=0 ||y>anni) {
    			txtResult.appendText("Errore: selezionare un anno tra 1 e "+anni+" ");
    		}
    		
    		int h=(Integer.parseInt(txtHours.getText()));
    		if(h<=0) {
    			txtResult.appendText("Errore: selezionare un numero di ore >0");
    		}
    		
    		
    		
    		
    		
    		
    	}catch(NumberFormatException e) {
    		txtResult.appendText("Errore: inserire un formato numerico valido");
    	}
    	
    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert cmbNerc != null : "fx:id=\"cmbNerc\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'PowerOutages.fxml'.";

        
        txtResult.setStyle("-fx-font-family: monospace");
    }
    public void setModel(Model model) {
    	
    	this.model=model;
    	cmbNerc.getItems().addAll(model.getNercList());
    	
    }
}


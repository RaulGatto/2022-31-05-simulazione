/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.nyc;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.nyc.model.CittaVicina;
import it.polito.tdp.nyc.model.City;
import it.polito.tdp.nyc.model.Model;
import it.polito.tdp.nyc.model.Provider;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAdiacenti"
    private Button btnAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaLista"
    private Button btnCreaLista; // Value injected by FXMLLoader

    @FXML // fx:id="cmbProvider"
    private ComboBox<Provider> cmbProvider; // Value injected by FXMLLoader

    @FXML // fx:id="cmbQuartiere"
    private ComboBox<City> cmbQuartiere; // Value injected by FXMLLoader

    @FXML // fx:id="txtMemoria"
    private TextField txtMemoria; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML // fx:id="clQuartiere"
    private TableColumn<CittaVicina, String> clQuartiere; // Value injected by FXMLLoader
 
    @FXML // fx:id="clDistanza"
    private TableColumn<CittaVicina, Double> clDistanza; // Value injected by FXMLLoader
    
    @FXML // fx:id="tblQuartieri"
    private TableView<CittaVicina> tblQuartieri; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	String res = this.model.creaGrafo(cmbProvider.getValue());
    	txtResult.appendText(res);
    	this.cmbQuartiere.getItems().addAll(this.model.getVertici());
    }

    @FXML
    void doQuartieriAdiacenti(ActionEvent event) {
    	
    	List<CittaVicina> cittaVicine = this.model.quartieriAdiacenti(this.cmbQuartiere.getValue());
    	txtResult.setText(String.format("La citta %s e' connessa alle seguenti citta:\n", this.cmbQuartiere.getValue()));
//    	for(CittaVicina c : cittaVicine) {
//    		txtResult.appendText(String.format("%s a distanza %f\n", c.getCity().getName(), c.getDistanza()));
//    	}
    	this.tblQuartieri.setItems(FXCollections.observableArrayList(cittaVicine));
    }

    @FXML
    void doSimula(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAdiacenti != null : "fx:id=\"btnAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaLista != null : "fx:id=\"btnCreaLista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbProvider != null : "fx:id=\"cmbProvider\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbQuartiere != null : "fx:id=\"cmbQuartiere\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMemoria != null : "fx:id=\"txtMemoria\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clDistanza != null : "fx:id=\"clDistanza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clQuartiere != null : "fx:id=\"clQuartiere\" was not injected: check your FXML file 'Scene.fxml'.";
        this.clDistanza.setCellValueFactory(new PropertyValueFactory<CittaVicina, Double>("distanza"));
        this.clQuartiere.setCellValueFactory(new PropertyValueFactory<CittaVicina, String>("city"));
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbProvider.getItems().addAll(this.model.getProviders());
    }

}

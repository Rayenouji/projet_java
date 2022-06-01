package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.connexion.Connexion;
import application.entities.Employes;
import application.entities.Salarié;
import application.entities.Vendeur;
import application.service.SalService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class salariecontroller implements Initializable {

	@FXML
	private TextField idmatricule;

	@FXML
	private TableView<Salarié> salaires;

	@FXML
	private TableColumn<Salarié, Integer> matrCol;

	@FXML
	private TableColumn<Salarié, String> nomCol;

	@FXML
	private TableColumn<Salarié, String> emailCol;

	@FXML
	private TableColumn<Salarié, Double> salaireCol;

	@FXML
	private TableColumn<Salarié, String> catCol;

	@FXML
	private TextField idnom;

	@FXML
	private TextField idemail;

	@FXML
	private TextField idannee;

	@FXML
	private TextField idsalaire;

	@FXML
	private RadioButton rbemploye;

	@FXML
	private RadioButton rbvendeur;

	@FXML
	private Button addBtn;
	@FXML
	private Button lister;
	@FXML
	private Button removeBtn;
	@FXML
	private Button update;
	@FXML
	private TextField hsupp;
    @FXML
    private TextField p; 
    @FXML
    private TextArea idarea;
    @FXML
    private Button ancien;
    @FXML
    private TextField min;

    @FXML
    private TextField max;

    @FXML
    private Button minmax;
    @FXML
    private Button ll;
    @FXML
    private Button vv;
    @FXML
    private Button qqbtn;
    @FXML
    private Button exporter;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		matrCol.setCellValueFactory(new PropertyValueFactory<>("Matricule"));
		nomCol.setCellValueFactory(new PropertyValueFactory<>("Nom"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
		salaireCol.setCellValueFactory(new PropertyValueFactory<>("Salaire"));
		catCol.setCellValueFactory(new PropertyValueFactory<>("role"));
		SalService AS = new SalService();
		salaires.getItems().addAll(AS.listerEmploye());
		salaires.getItems().addAll(AS.listerVendeur());
		for(Salarié s: salaires.getItems()) {
			System.out.println(s.getRole());
		}

	}

public void Add() {
    	
    	if(rbemploye.isSelected()) {
    		Employes emp = new Employes(Integer.parseInt(idmatricule.getText()), idnom.getText(), idemail.getText(),"E",Double.parseDouble(idannee.getText()), 0.1,Double.parseDouble(hsupp.getText()),0);
    		System.out.println(emp.toString());
    		SalService S = new SalService();
    		boolean result = S.createEmploye(emp);
    		salaires.getItems().add(S.getSalarie(emp.getMatricule()));
    	} else if(rbvendeur.isSelected()) {
    		Vendeur vdr =new Vendeur(Integer.parseInt(idmatricule.getText()), idnom.getText(), idemail.getText(),"V",Double.parseDouble(idannee.getText()), 0.1,Double.parseDouble(hsupp.getText()), 0);
    		System.out.println(vdr.toString());
    		SalService S = new SalService();
    		boolean result = S.createVendeur(vdr);
    		salaires.getItems().add(S.getSalarie(vdr.getMatricule()));
    	}
    }


public void delete() {
	SalService S = new SalService();
	
	S.delete(salaires.getSelectionModel().getSelectedItem().getMatricule());
	
	salaires.getItems().remove(salaires.getSelectionModel().getSelectedItem());
}
public void modifier() {
	if(rbemploye.isSelected()) {
		SalService S = new SalService();

		Employes e = new Employes(Integer.parseInt(idmatricule.getText()), idnom.getText(), idemail.getText(),"E",Double.parseDouble(idannee.getText()), 0.1,Double.parseDouble(hsupp.getText()), 0);
        S.modifieremp(e);
        salaires.getItems().remove(salaires.getSelectionModel().getSelectedItem());
		salaires.getItems().add(S.getSalarie(e.getMatricule()));
	}
	
}

public void details() {
	if (salaires.getSelectionModel().getSelectedItem().getRole().equals("Employe")) {
		SalService ss = new SalService();
		Employes e = ss.findEmployeById(salaires.getSelectionModel().getSelectedItem().getMatricule());
		System.out.println(e.toString());
		idarea.setText(e.toString());
	} else if (salaires.getSelectionModel().getSelectedItem().getRole().equals("Vendeur")) {
		SalService s = new SalService();
		Vendeur v = s.findVendeurById(salaires.getSelectionModel().getSelectedItem().getMatricule());
		System.out.println(v.toString());
		idarea.setText(v.toString());
	}
}
public void listerEmployee() {
	SalService s = new SalService();
	List<Salarié> E = s.listerEmploye();
	salaires.getItems().clear();
	salaires.getItems().addAll(E);
}
public void listerVendeur() {
	SalService s = new SalService();
	List<Salarié> E = s.listerVendeur();
	salaires.getItems().clear();
	salaires.getItems().addAll(E);
}
public void listAnciennete() {
	SalService s = new SalService();
	List<Salarié> listAnciennete = s.anciennete();
	salaires.getItems().clear();
	salaires.getItems().addAll(listAnciennete);
}
public void maxMin() {
	SalService S = new SalService();
	List<Salarié> salaries = S.SalariesMinMax(Double.parseDouble(min.getText()), Double.parseDouble(max.getText()));
	salaires.getItems().clear();
	salaires.getItems().addAll(salaries);
}
	
public void exporter(ActionEvent event) {
		File expFile = new File("C:\\files\\projet.txt");
		try {
			FileWriter expFileReader = new FileWriter(expFile);
			expFileReader.write(salaires.getItems().toString());
			expFileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

@FXML
public void handleCloseButtonAction(ActionEvent event) {
	Stage stage = (Stage) qqbtn.getScene().getWindow();
    stage.close();
}
}

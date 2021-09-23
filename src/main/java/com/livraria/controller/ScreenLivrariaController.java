package com.livraria.controller;

import com.livraria.entity.Genero;
import com.livraria.entity.Livro;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ScreenLivrariaController implements Initializable {

    private ArrayList<Livro> livros = new ArrayList<Livro>();
    private ArrayList<Genero> generos = new ArrayList<Genero>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generos.add(new Genero(0, "Romance"));
        generos.add(new Genero(1, "Ação"));
        generos.add(new Genero(2, "Aventura"));
        generos.add(new Genero(3, "Suspense"));

        List<String> genString = new ArrayList<>();


        for(Genero genero: generos){
            genString.add(new String(genero.titulo));
            cmbGenero.setValue(genString.get(genero.getId()));
        }
        tbcID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tbcNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tbcAutor.setCellValueFactory(new PropertyValueFactory<>("Autor"));

    }

    @FXML
    private Pane panePrincipal;
    @FXML
    private AnchorPane paneGenero;
    @FXML
    private TextField txfGeneroAdd;
    @FXML
    private Button btnCancelGen;
    @FXML
    private Button btnSaveGen;
    @FXML
    private TextField txfTitulo;
    @FXML
    private TextField txfAutor;
    @FXML
    private AnchorPane paneAddGen;
    @FXML
    private TextField txfGeneroAdd1;
    @FXML
    private Button btnCancelGen1;
    @FXML
    private Button btnSaveGen1;
    @FXML
    private Pane paneNormGen;
    @FXML
    private ComboBox<String> cmbGenero;
    @FXML
    private Button btnAddG;
    @FXML
    private Button btnAdicionar;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnExclud;
    @FXML
    private TextField txfID;
    @FXML
    private Button btnPesquise;
    @FXML
    private TableView<?> tbcView;
    @FXML
    private TableColumn<?, ?> tbcID;
    @FXML
    private TableColumn<?, ?> tbcNome;
    @FXML
    private TableColumn<?, ?> tbcAutor;

    public void HabilitaCampos () {
        if(operacao.equals("adicionar"))
            btnExclud.setDisable(true);
        else
            btnExclud.setDisable(false);

        btnAdicionar.setVisible(false);

        btnPesquise.setDisable(true);
        btnCancel.setDisable(false);
        btnSalvar.setDisable(false);

        txfID.setDisable(true);

        panePrincipal.setVisible(true);
    }

    public void DesabilitaCampos () {
        btnAdicionar.setVisible(true);

        btnPesquise.setDisable(false);
        btnExclud.setDisable(true);
        btnCancel.setDisable(true);
        btnSalvar.setDisable(true);

        txfID.setDisable(false);
        txfTitulo.clear();
        txfAutor.clear();

        panePrincipal.setVisible(false);
    }

    public void HabilitaCamposGen () {
        paneAddGen.setVisible(true);
        paneNormGen.setVisible(false);
    }

    public void DesabilitaCamposGen () {
        paneAddGen.setVisible(false);
        paneNormGen.setVisible(true);
    }

    public void adcionar() {

        int Id = 0;
        if(livros.isEmpty()) {
            txfID.setText(String.valueOf(Id));
        }else {
            for(Livro livro : livros) {
                if(livro.id == Id) {
                    Id++;
                }
            }

        }
        txfID.setText(String.valueOf(Id));
        txfTitulo.requestFocus();
        HabilitaCampos();
    }

    public void pesquisar() {
        try {
            int Id = Integer.parseInt(txfID.getText());

            for(Livro livro : livros) {
                if(livro.id == Id) {
                    HabilitaCampos();
                    txfTitulo.setText(livro.titulo);
                    txfAutor.setText(livro.autor);
                    operacao = "pesquisar";
                    return;
                }
            }
            throw new IOException();

        }catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ops");
            alert.setHeaderText("A operacao nao pode ser realizada.");
            alert.setContentText("Digite um ID valido para pesquisar!!");
            alert.showAndWait();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ops");
            alert.setHeaderText("A operacao nao pode ser realizada.");
            alert.setContentText("Verifique se digitou corretamente!!");
            alert.showAndWait();
        }
    }

    public void salvar(){
        try{
            int Id = Integer.parseInt(txfID.getText());
            String Titulo = txfTitulo.getText(), Autor = txfAutor.getText(), Genero = cmbGenero.toString();

            if(Titulo.isEmpty() || Autor.isEmpty() || Genero.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ops");
                alert.setHeaderText("A operacao nao pode ser realizada.");
                alert.setContentText("Verifique se todos os campos foram inseridos!!");
                alert.showAndWait();
            }else{
                if(operacao.equals("adicionar")){
                    livros.add(new Livro(Id, Titulo, Autor, Genero));
                    DesabilitaCampos();
                }else{
                    for(Livro livro : livros) {
                        if(livro.id == Id) {
                            livro.titulo = Titulo;
                            livro.autor = Autor;
                            livro.genero = Genero;
                            DesabilitaCampos();
                            return;
                        }
                    }
                }

            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ops");
            alert.setHeaderText("A operacao nao pode ser realizada.");
            alert.setContentText("Verifique se todos os campos foram inseridos!!");
            alert.showAndWait();
        }
    }

    public String operacao;
    @FXML
    public void btnPesquise_click(ActionEvent actionEvent) {
        pesquisar();

    }

    @FXML
    public void btnAdicionar_click(ActionEvent actionEvent) {
        operacao = "adicionar";
        adcionar();
    }

    @FXML
    public void btnCancel_click(ActionEvent actionEvent) {
        DesabilitaCampos();
    }

    @FXML
    public void btnSalvar_click(ActionEvent actionEvent) {
        salvar();
    }

    @FXML
    public void btnExclud_click(ActionEvent actionEvent) {
        livros.remove(Integer.parseInt(txfID.getText()));
        DesabilitaCampos();
    }

    @FXML
    void btnAddGen_click(ActionEvent event) {
        HabilitaCamposGen ();
    }

    @FXML
    void btnSaveGen_click(ActionEvent event) {
        DesabilitaCamposGen ();
    }

    @FXML
    void btnCancelGen_click(ActionEvent event) {
        DesabilitaCamposGen ();
    }
}
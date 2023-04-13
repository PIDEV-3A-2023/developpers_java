/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog;

import Entities.Blog;
import Service.ServiceBlog;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class FXMLBlogController implements Initializable {

    private Label label;

    @FXML
    private DatePicker datec;
    @FXML
    private Button stat;
    @FXML
    private TextField tftitre;
    @FXML
    private TextField tfdescription;
    @FXML
    private TableColumn<Blog, Integer> idt;
    @FXML
    private TableColumn<Blog, Date> datet;
    @FXML
    private TableColumn<Blog, String> titret;
    @FXML
    private TableColumn<Blog, String> descriptiont;
    @FXML
    private TableView<Blog> tablec;
    @FXML
    private TextField idsup;
    @FXML
    private TextField tfsearch;
    private int vartri = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    @FXML
    private void AjouterBlog(ActionEvent event) {
        ServiceBlog sc = new ServiceBlog();
        Blog c = new Blog();
        c.setDate(java.sql.Date.valueOf(datec.getValue()));
        c.setTitre(tftitre.getText());
        c.setDescription_b(tfdescription.getText());

        sc.AjouterBlog(c);
        this.AfficherBlog(event);
    }

    @FXML
    private void AfficherBlog(ActionEvent event) {
        ServiceBlog sc = new ServiceBlog();
        ObservableList<Blog> blogs = sc.AfficherBlog();

        idt.setCellValueFactory(new PropertyValueFactory<Blog, Integer>("id"));
        datet.setCellValueFactory(new PropertyValueFactory<Blog, Date>("date"));
        titret.setCellValueFactory(new PropertyValueFactory<Blog, String>("titre"));
        descriptiont.setCellValueFactory(new PropertyValueFactory<Blog, String>("description_b"));
        tablec.setItems(blogs);

    }

    @FXML
    private void selectionner(MouseEvent event) {

        Blog c = tablec.getSelectionModel().getSelectedItem();
        java.sql.Date dateget = convertUtilToSql(c.getDate());
        idsup.setText(String.valueOf(c.getId()));
        datec.setValue(dateget.toLocalDate());
        tftitre.setText(c.getTitre());
        tfdescription.setText(c.getDescription_b());
    }

    @FXML
    private void SupprimerBlog(ActionEvent event) {
        ServiceBlog sc = new ServiceBlog();
        sc.supprimerBlog(Integer.parseInt(idsup.getText()));
        this.AfficherBlog(event);

    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;

    }

    @FXML
    private void ModifierBlog(ActionEvent event) {
        ServiceBlog sc = new ServiceBlog();
        Blog c = new Blog();
        c.setId(Integer.parseInt(idsup.getText()));
        c.setDate(java.sql.Date.valueOf(datec.getValue()));
        c.setTitre(tftitre.getText());
        c.setDescription_b(tfdescription.getText());
        sc.ModifierBlog(c);
    }

    @FXML
    private void searchkey(KeyEvent event) {
        ServiceBlog sc = new ServiceBlog();
        ObservableList<Blog> blogs = sc.search(tfsearch.getText());
        idt.setCellValueFactory(new PropertyValueFactory<Blog, Integer>("id"));
        datet.setCellValueFactory(new PropertyValueFactory<Blog, Date>("date"));
        titret.setCellValueFactory(new PropertyValueFactory<Blog, String>("titre"));
        descriptiont.setCellValueFactory(new PropertyValueFactory<Blog, String>("description_b"));
        tablec.setItems(blogs);
    }

    @FXML
    private void tributton(MouseEvent event) {
        ServiceBlog sc = new ServiceBlog();
        ObservableList<Blog> blogs;
        if (vartri == 1) {
            vartri = 0;
            blogs = sc.triasc();
        idt.setCellValueFactory(new PropertyValueFactory<Blog, Integer>("id"));
        datet.setCellValueFactory(new PropertyValueFactory<Blog, Date>("date"));
        titret.setCellValueFactory(new PropertyValueFactory<Blog, String>("titre"));
        descriptiont.setCellValueFactory(new PropertyValueFactory<Blog, String>("description_b"));
        tablec.setItems(blogs);

        } else {
            vartri = 1;
            blogs = sc.triadsc();
        idt.setCellValueFactory(new PropertyValueFactory<Blog, Integer>("id"));
        datet.setCellValueFactory(new PropertyValueFactory<Blog, Date>("date"));
        titret.setCellValueFactory(new PropertyValueFactory<Blog, String>("titre"));
        descriptiont.setCellValueFactory(new PropertyValueFactory<Blog, String>("description_b"));
        tablec.setItems(blogs);

        }

    }

}

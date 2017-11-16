package sample.controladores;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sample.Main;


import java.sql.*;
import java.util.Observable;


public class Controller {

    public TextField txtpais;
    public ListView<String> lvciudad;
    public ListView<String> lvpais;
    public Label lblerror;

    private ObservableList<String> paises = FXCollections.observableArrayList();
    private ObservableList<String> ciudades = FXCollections.observableArrayList();

    public void initialize() {
        lvpais.setItems(paises);
        lvciudad.setItems(ciudades);
        lvpais.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            }
        });
    }


    public void buscarPais(KeyEvent keyevent) {

//        if (keyevent.getCode() == KeyCode.ENTER){
//            paises.add(txtpais.getText());
//            txtpais.clear();
//        }


        paises.clear();

        String nombreBusqueda = txtpais.getText().trim();
        if (nombreBusqueda.length() >= 1) {
            Connection con = Main.getConexion();

            try {
                Statement stml = con.createStatement();
                String sql = "SELECT Name FROM country where Name like '" + nombreBusqueda + "%'";
                ResultSet resultado = stml.executeQuery(sql);
                while (resultado.next()) {

                    paises.add(resultado.getString("Name"));

                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }


}

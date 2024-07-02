package poov.controledoacaosangue.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import poov.controledoacaosangue.dao.DoadorDAO;
import poov.controledoacaosangue.dao.core.DAOFactory;
import poov.controledoacaosangue.model.Doador;
import poov.controledoacaosangue.model.RH;
import poov.controledoacaosangue.model.TipoSanguineo;

public class TelaCriarCadastroController implements Initializable{

    private DAOFactory factory;

    @FXML
    private Button cadastrarButton;

    @FXML
    private TextField contatoTextField;

    @FXML
    private TextField cpfTextField;

    @FXML
    private Button fecharButton;

    @FXML
    private Button limparButton;

    @FXML
    private TextField nomeTextField;

    @FXML
    private ListView<RH> rhListView;

    @FXML
    private ListView<TipoSanguineo> tipoSanguineoListView;

    @FXML
    void cadastrarClicado(ActionEvent event) throws SQLException{
        if(ehValido()){
            Doador selecionado = new Doador();
            try {
                factory.abrirConexao();
                DoadorDAO dao = factory.getDAO(DoadorDAO.class);
                selecionado.setNome(nomeTextField.getText());
                selecionado.setCpf(cpfTextField.getText());
                selecionado.setContato(contatoTextField.getText());
                selecionado.setRh(rhListView.getSelectionModel().getSelectedItem());
                selecionado.setTipoSanguineo(tipoSanguineoListView.getSelectionModel().getSelectedItem());
                dao.create(selecionado);
                
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText("Sucesso na Alteração");
                alert.setContentText("Doador alterado com sucesso! ");
                alert.showAndWait();

                fecharClicado(event);

            } finally {
                factory.fecharConexao();
            }
        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Problema");
            alert.setHeaderText("Problema na Alteração");
            alert.setContentText("Preencha todos os campos para poder alterar.");
            alert.showAndWait();
        }
    }

    @FXML
    void fecharClicado(ActionEvent event) {
        ((Button) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    void limparClicado(ActionEvent event) {
        nomeTextField.setText("");
        cpfTextField.setText("");
        contatoTextField.setText("");
        tipoSanguineoListView.getSelectionModel().clearSelection();
        rhListView.getSelectionModel().clearSelection();
    }
    public void setDAOFactory (DAOFactory factory) {
        this.factory = factory;
    }
    public boolean ehValido(){
        return  !nomeTextField.getText().isBlank() &&
                !cpfTextField.getText().isBlank() &&
                !contatoTextField.getText().isBlank() &&
                !rhListView.getSelectionModel().isEmpty() &&
                !tipoSanguineoListView.getSelectionModel().isEmpty();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rhListView.getItems().clear();
        rhListView.getItems().add(RH.POSITIVO);
        rhListView.getItems().add(RH.NEGATIVO);
        rhListView.getItems().add(RH.DESCONHECIDO);
        tipoSanguineoListView.getItems().clear();
        tipoSanguineoListView.getItems().add(TipoSanguineo.A);
        tipoSanguineoListView.getItems().add(TipoSanguineo.B);
        tipoSanguineoListView.getItems().add(TipoSanguineo.AB);
        tipoSanguineoListView.getItems().add(TipoSanguineo.O);
        tipoSanguineoListView.getItems().add(TipoSanguineo.DESCONHECIDO);

        TextFormatter<String> formatterApenasDigitos = new TextFormatter<>(change -> {
            if (!change.isContentChange()) {
                return change;
            }
            String text = change.getControlNewText();
            System.out.println(text);
            if (text.length() == 0) { // permite campo vazio
                return change;
            } else { // verifica se o texto, com a mudança, é um long válido
                try {
                    Long.parseLong(text);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return change;
        });
        cpfTextField.setTextFormatter(formatterApenasDigitos);
    }
}

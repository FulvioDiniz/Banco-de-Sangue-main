package poov.controledoacaosangue.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import poov.controledoacaosangue.dao.DoadorDAO;
import poov.controledoacaosangue.dao.core.DAOFactory;
import poov.controledoacaosangue.model.Doador;
import poov.controledoacaosangue.model.RH;
import poov.controledoacaosangue.model.TipoSanguineo;

public class TelaAlterarCadastroController implements Initializable {

    private Doador selecionado;
    private DAOFactory factory;
    private Parent parentRoot;

    @FXML
    private Button alterarButton;

    @FXML
    private TextField codigoTextField;

    @FXML
    private TextField contatoTextField;

    @FXML
    private TextField cpfTextField;

    @FXML
    private Button fecharButton;

    @FXML
    private TextField nomeTextField;

    @FXML
    private ListView<RH> rhListView;

    @FXML
    private ListView<TipoSanguineo> tipoSanguineoListView;

    @FXML
    private CheckBox tudoCertoVerificado;

    @FXML
    void alterarClicado(ActionEvent event) {
        if (ehValido()) {
            try {
                factory.abrirConexao();
                DoadorDAO dao = factory.getDAO(DoadorDAO.class);
                selecionado.setNome(nomeTextField.getText());
                selecionado.setCpf(cpfTextField.getText());
                selecionado.setContato(contatoTextField.getText());
                selecionado.setRh(rhListView.getSelectionModel().getSelectedItem());
                selecionado.setTipoSanguineo(tipoSanguineoListView.getSelectionModel().getSelectedItem());
                selecionado.setTipoERhCorretos(tudoCertoVerificado.isSelected());
                dao.update(selecionado);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText("Sucesso na Alteração");
                alert.setContentText("Doador alterado com sucesso!");
                alert.showAndWait();

                fecharClicado(event);

            } catch (SQLException e) {
                exibirErro("Erro ao alterar doador", "Não foi possível atualizar o doador.", e);
            } finally {
                factory.fecharConexao();
            }
        } else {
            exibirAlertaErro("Problema na Alteração", "Preencha todos os campos para poder alterar.");
        }
    }

    @FXML
    void fecharClicado(ActionEvent event) {
        alterarButton.getScene().getWindow().hide();
    }

    public void setDoador(Doador selecionado) {
        this.selecionado = selecionado;
        codigoTextField.setText(String.valueOf(selecionado.getCodigo()));
        nomeTextField.setText(selecionado.getNome());
        cpfTextField.setText(selecionado.getCpf());
        contatoTextField.setText(selecionado.getContato());
        rhListView.getSelectionModel().select(selecionado.getRh());
        tipoSanguineoListView.getSelectionModel().select(selecionado.getTipoSanguineo());
        tudoCertoVerificado.setSelected(selecionado.isTipoERhCorretos());
    }

    public void setDAOFactory(DAOFactory factory) {
        this.factory = factory;
    }

    public boolean ehValido() {
        return !codigoTextField.getText().isBlank() &&
                !nomeTextField.getText().isBlank() &&
                !cpfTextField.getText().isBlank() &&
                !contatoTextField.getText().isBlank() &&
                rhListView.getSelectionModel().getSelectedItem() != null &&
                tipoSanguineoListView.getSelectionModel().getSelectedItem() != null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rhListView.getItems().addAll(RH.POSITIVO, RH.NEGATIVO, RH.DESCONHECIDO);
        tipoSanguineoListView.getItems().addAll(TipoSanguineo.A, TipoSanguineo.B, TipoSanguineo.AB, TipoSanguineo.O, TipoSanguineo.DESCONHECIDO);
        codigoTextField.setEditable(false);

        cpfTextField.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.isContentChange()) {
                return change;
            }
            String text = change.getControlNewText();
            if (text.matches("\\d*")) {
                return change;
            }
            return null;
        }));
    }

    public void setParentRoot(Parent parentRoot) {
        this.parentRoot = parentRoot;
    }

    public Parent getParentRoot() {
        return parentRoot;
    }

    private void exibirErro(String titulo, String cabecalho, Exception ex) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(ex.getMessage());
        alert.showAndWait();
    }

    private void exibirAlertaErro(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}

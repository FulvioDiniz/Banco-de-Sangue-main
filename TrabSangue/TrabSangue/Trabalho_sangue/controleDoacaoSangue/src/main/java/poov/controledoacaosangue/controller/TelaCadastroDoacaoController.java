package poov.controledoacaosangue.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import poov.controledoacaosangue.dao.DoacaoDAO;
import poov.controledoacaosangue.dao.core.DAOFactory;
import poov.controledoacaosangue.model.Doacao;
import poov.controledoacaosangue.model.Doador;
import poov.controledoacaosangue.model.Situacao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TelaCadastroDoacaoController {

    @FXML
    private TextField codigoDoadorField;
    @FXML
    private TextField nomeDoadorField;
    @FXML
    private TextField cpfDoadorField;
    @FXML
    private TextField volumeField;
    @FXML
    private TextField dataField;
    @FXML
    private TextField horaField;

    private Doador doadorSelecionado;
    private DAOFactory factory;

    public void setDoador(Doador doador) {
        this.doadorSelecionado = doador;
        codigoDoadorField.setText(String.valueOf(doador.getCodigo()));
        nomeDoadorField.setText(doador.getNome());
        cpfDoadorField.setText(doador.getCpf());
    }

    public void setDAOFactory(DAOFactory factory) {
        this.factory = factory;
    }

    @FXML
    public void handleCadastrar(ActionEvent event) {
        if (volumeField.getText().isEmpty() || dataField.getText().isEmpty() || horaField.getText().isEmpty()) {
            mostrarAlertaErro("Erro", "Campos obrigatórios", "Por favor, preencha todos os campos.");
        } else {
            try {
                factory.abrirConexao();
                DoacaoDAO dao = factory.getDAO(DoacaoDAO.class);
                Doacao nova = new Doacao();
                nova.setDoador(doadorSelecionado);
                nova.setVolume(Double.parseDouble(volumeField.getText()));
                nova.setData(LocalDate.parse(dataField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                nova.setHora(LocalTime.parse(horaField.getText(), DateTimeFormatter.ofPattern("HH:mm")));
                nova.setSituacao(Situacao.ATIVO);
                dao.create(nova);

                mostrarAlertaInfo("Sucesso", "Doação cadastrada", "A doação foi cadastrada com sucesso.");

                fecharJanela();
            } catch (NumberFormatException e) {
                mostrarAlertaErro("Erro", "Volume inválido", "Insira um valor numérico válido para o volume.");
            } catch (DateTimeParseException e) {
                mostrarAlertaErro("Erro", "Formato de data ou hora inválido", "Use o formato correto para data (yyyy-MM-dd) e hora (HH:mm).");
            } catch (SQLException e) {
                mostrarAlertaErro("Erro", "Erro no banco de dados", "Ocorreu um erro ao acessar o banco de dados.");
            } finally {
                factory.fecharConexao();
            }
        }
    }

    @FXML
    public void handleCancelar(ActionEvent event) {
        fecharJanela();
    }

    private void mostrarAlertaErro(String titulo, String cabecalho, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarAlertaInfo(String titulo, String cabecalho, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void fecharJanela() {
        Stage stage = (Stage) codigoDoadorField.getScene().getWindow();
        stage.close();
    }
}

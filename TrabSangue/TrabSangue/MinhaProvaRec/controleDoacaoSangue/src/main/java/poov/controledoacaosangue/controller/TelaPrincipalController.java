package poov.controledoacaosangue.controller;
import javafx.beans.property.SimpleStringProperty;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import poov.controledoacaosangue.App;
import poov.controledoacaosangue.dao.ConexaoFactoryPostgreSQL;
import poov.controledoacaosangue.dao.DoacaoDAO;
import poov.controledoacaosangue.dao.DoadorDAO;
import poov.controledoacaosangue.dao.core.DAOFactory;
import poov.controledoacaosangue.model.Doacao;
import poov.controledoacaosangue.model.Doador;
import poov.controledoacaosangue.model.RH;
import poov.controledoacaosangue.model.Situacao;
import poov.controledoacaosangue.model.TipoSanguineo;

public class TelaPrincipalController implements Initializable {

    @FXML
    private RadioButton RHRADIONegativo;

    @FXML
    private RadioButton RHRAdioPositivo;

    @FXML
    private RadioButton RHRadioQuaqluer;

    @FXML
    private RadioButton RadioA;

    @FXML
    private RadioButton RadioAB;

    @FXML
    private RadioButton RadioB;

    @FXML
    private RadioButton RadioO;

    @FXML
    private RadioButton RadioQualquer;

    @FXML
    private TextField TexCodigoDoação;

    @FXML
    private TextField TextCPF;

    @FXML
    private TextField TextCodigoDoador;

    @FXML
    private TextField TextDataFinal;

    @FXML
    private TextField TextDataInicial;

    @FXML
    private TextField TextHoraFInal;

    @FXML
    private TextField TextHoraInicial;

    @FXML
    private TextField TextNome;

    @FXML
    private TextField TextVolumeFinal;

    @FXML
    private TextField TextVolumeInicial;

    @FXML
    private TableColumn<Doacao, String> ViewCPFdoDoador;

    @FXML
    private TableColumn<Doador, Long> ViewCodigo;

    @FXML
    private TableColumn<Doacao, String> ViewContatoDoDOador;

    @FXML
    private TableColumn<Doador, String> ViewData;

    @FXML
    private TableColumn<Doador, String> ViewHora;

    @FXML
    private TableColumn<Doacao, String> ViewNomeDoDoador;

    @FXML
    private TableColumn<Doador, String> ViewVolume;

    @FXML
    private Button alterarDoadorButton;

    @FXML
    private Button buscarButton;

    @FXML
    private Button buscarButtonDoacao;

    @FXML
    private Button cadastrarDoacaoButton;

    @FXML
    private Button cadastrarDoadorButton;

    @FXML
    private TableColumn<Doador, Long> codigoColumn;

    @FXML
    private TextField codigoTextField;

    @FXML
    private TableColumn<Doador, String> contatoColumn;

    @FXML
    private TextField contatoTextField;

    @FXML
    private TableColumn<Doador, String> cpfColumn;

    @FXML
    private TextField cpfTextField;

    @FXML
    private TableView<Doador> doadoresTableView;

    @FXML
    private TableView<Doacao> doacoesTableView;

    @FXML
    private Button fecharButton;

    @FXML
    private Button limparButton;

    @FXML
    private Button limparButtonDoacao;

    @FXML
    private TableColumn<Doador, String> nomeColumn;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField TextContato;

    @FXML
    private Button removerDoadorButton;

    @FXML
    private TableColumn<Doador, String> rhColumn;

    @FXML
    private RadioButton rhNegativoButton;

    @FXML
    private RadioButton rhPositivoButton;

    @FXML
    private RadioButton rhQualquerButton;

    @FXML
    private ToggleGroup rhToggleGroup;

    @FXML
    private RadioButton tipoABButton;

    @FXML
    private RadioButton tipoAButton;

    @FXML
    private RadioButton tipoBButton;

    @FXML
    private RadioButton tipoOButton;

    @FXML
    private RadioButton tipoQualquerButton;

    @FXML
    private TableColumn<Doador, String> tipoSanguineoColumn;

    @FXML
    private ToggleGroup tipoSanguineoToggleGroup;

    @FXML
    private Button verDoacoesButton;

    private DAOFactory factory;
    private Stage stageCadastro;
    private Stage stageAlterar;
    private Stage stageCadastroDoacao;
    private TelaAlterarCadastroController telaAlterarCadastroController;
    private TelaCriarCadastroController telaCriarCadastroController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        factory = new DAOFactory(new ConexaoFactoryPostgreSQL("localhost:5432/poov", "postgres", "230199"));// 230199
        // Inicializar tabela e outros componentes
        // Inicializar colunas da TableView
        ViewCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        codigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        ViewContatoDoDOador.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDoador().getContato()));
        contatoColumn.setCellValueFactory(new PropertyValueFactory<>("contato"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        ViewNomeDoDoador.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDoador().getNome()));
        ViewCPFdoDoador.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDoador().getCpf()));
        cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        rhColumn.setCellValueFactory(new PropertyValueFactory<>("rh"));
        tipoSanguineoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoSanguineo"));
        ViewData.setCellValueFactory(new PropertyValueFactory<>("data"));
        ViewHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        ViewVolume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        tipoOButton.setUserData(TipoSanguineo.O);
        tipoAButton.setUserData(TipoSanguineo.A);
        tipoABButton.setUserData(TipoSanguineo.AB);
        tipoBButton.setUserData(TipoSanguineo.B);
        tipoQualquerButton.setUserData(null);
        rhPositivoButton.setUserData(RH.POSITIVO);
        rhNegativoButton.setUserData(RH.NEGATIVO);
        rhQualquerButton.setUserData(null);
        Parent parent;
        stageAlterar = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/poov/cadastrovacina/TelaAlterarCadastro.fxml"));
        try {
            parent = fxmlLoader.load();
            telaAlterarCadastroController = fxmlLoader.getController();
            telaAlterarCadastroController.setDAOFactory(factory);
            Scene sceneAlterar = new Scene(parent);
            stageAlterar.setScene(sceneAlterar);
            stageAlterar.setTitle("Alteração de Cadastro");
            stageAlterar.setResizable(false);
            stageAlterar.initModality(Modality.WINDOW_MODAL);
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("Erro");
            alert.setContentText("Erro carregando a aplicação!");
            alert.showAndWait();
        }

        stageCadastro = new Stage();
        fxmlLoader = new FXMLLoader(App.class.getResource("/poov/cadastrovacina/TelaCriarCadastro.fxml"));
        try {
            parent = fxmlLoader.load();
            telaCriarCadastroController = fxmlLoader.getController();
            telaCriarCadastroController.setDAOFactory(factory);
            Scene sceneAlterar = new Scene(parent);
            stageCadastro.setScene(sceneAlterar);
            stageCadastro.setTitle("Novo Cadastro");
            stageCadastro.setResizable(false);
            stageCadastro.initModality(Modality.WINDOW_MODAL);
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("Erro");
            alert.setContentText("Erro carregando a aplicação!");
            alert.showAndWait();
        }

        stageCadastroDoacao = new Stage();
        fxmlLoader = new FXMLLoader(App.class.getResource("/poov/cadastrovacina/TelaCadastroDoacao.fxml"));
        try {
            parent = fxmlLoader.load();
            TelaCadastroDoacaoController controller = fxmlLoader.getController();
            controller.setDAOFactory(factory);
            Scene sceneCadastroDoacao = new Scene(parent);
            stageCadastroDoacao.setScene(sceneCadastroDoacao);
            stageCadastroDoacao.setTitle("Cadastro de Doação");
            stageCadastroDoacao.setResizable(false);
            stageCadastroDoacao.initModality(Modality.WINDOW_MODAL);
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("Erro");
            alert.setContentText("Erro carregando a aplicação!");
            alert.showAndWait();
        }

    }

    @FXML
    void alterarDoador(ActionEvent event) {
        if (doadoresTableView.getSelectionModel().isEmpty()) {
            Alert info = new Alert(Alert.AlertType.ERROR);
            info.setTitle("Erro");
            info.setHeaderText("Você deve selecionar algum doador!");
            info.showAndWait();
        } else {
            Doador selected = doadoresTableView.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader loader = new FXMLLoader(
                        App.class.getResource("/poov/cadastrovacina/TelaAlterarCadastro.fxml"));
                Parent root = loader.load();

                TelaAlterarCadastroController controller = loader.getController();
                controller.setDAOFactory(factory);
                controller.setDoador(selected);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Alteração de Cadastro");
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Button) event.getSource()).getScene().getWindow());
                stage.showAndWait();

                buscarDoador(event);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setHeaderText("Erro");
                alert.setContentText("Erro carregando a aplicação!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void buscarDoador(ActionEvent event) {
        Doador filtro = new Doador();
        if (!codigoTextField.getText().isEmpty()) {
            filtro.setCodigo(Long.parseLong(codigoTextField.getText()));
        }
        if (!nomeTextField.getText().isEmpty()) {
            filtro.setNome(nomeTextField.getText());
        }
        if (!cpfTextField.getText().isEmpty()) {
            filtro.setCpf(cpfTextField.getText());
        }
        if (!contatoTextField.getText().isEmpty()) {
            filtro.setContato(contatoTextField.getText());
        }

        // Captura o tipo sanguíneo selecionado
        RadioButton selectedTipoSanguineo = (RadioButton) tipoSanguineoToggleGroup.getSelectedToggle();
        if (selectedTipoSanguineo != null) {
            filtro.setTipoSanguineo((TipoSanguineo) selectedTipoSanguineo.getUserData());
        }

        // Captura o RH selecionado
        RadioButton selectedRH = (RadioButton) rhToggleGroup.getSelectedToggle();
        if (selectedRH != null) {
            filtro.setRh((RH) selectedRH.getUserData());
        }

        try {
            try {
                factory.abrirConexao();
            } catch (SQLException e) {
                // Handle the exception here
                e.printStackTrace();
            }
            DoadorDAO dao = factory.getDAO(DoadorDAO.class);
            List<Doador> doadores = dao.pesquisar(filtro);
            doadoresTableView.getItems().clear();
            doadoresTableView.getItems().addAll(doadores);
        } finally {
            factory.fecharConexao();
        }
    }

    @FXML
    void cadastrarDoacao(ActionEvent event) {
        if (doadoresTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum doador selecionado");
            alert.setContentText("Por favor, selecione um doador para cadastrar uma doação.");
            alert.showAndWait();
        } else {
            Doador doadorSelecionado = doadoresTableView.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader loader = new FXMLLoader(
                        App.class.getResource("/poov/cadastrovacina/TelaCadastroDoacao.fxml"));
                Parent root = loader.load();

                TelaCadastroDoacaoController controller = loader.getController();
                controller.setDoador(doadorSelecionado);
                controller.setDAOFactory(factory);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Cadastrar Doação");
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Button) event.getSource()).getScene().getWindow());
                stage.showAndWait();

                buscarDoador(event);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setHeaderText("Erro");
                alert.setContentText("Erro ao carregar a tela de cadastro de doação.");
                alert.showAndWait();
                e.printStackTrace();
            }
        }
    }

    public boolean ehValido() {
        return !doadoresTableView.getSelectionModel().isEmpty();
    }

    @FXML
    void cadastrarDoador(ActionEvent event) {
        if (stageCadastro.getOwner() == null) {
            stageCadastro.initOwner(((Button) event.getSource()).getScene().getWindow());
        }
        stageCadastro.showAndWait();
        buscarDoador(event);
    }

    @FXML
    void fechar(ActionEvent event) {
        ((Button) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void limparCampos(ActionEvent event) {
        codigoTextField.clear();
        nomeTextField.clear();
        cpfTextField.clear();
        contatoTextField.clear();
    }

    @FXML
    void removerDoador(ActionEvent event) {
        if (doadoresTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum doador selecionado");
            alert.setContentText("Por favor, selecione um doador para remover.");
            alert.showAndWait();
        } else {
            Doador selectedDoador = doadoresTableView.getSelectionModel().getSelectedItem();
            try {
                factory.abrirConexao();
                DoadorDAO dao = factory.getDAO(DoadorDAO.class);
                selectedDoador.setSituacao(Situacao.INATIVO); // Altera a situação do doador para INATIVO
                dao.update(selectedDoador);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText("Doador Removido");
                alert.setContentText("O doador foi removido com sucesso.");
                alert.showAndWait();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Erro ao remover doador");
                alert.setContentText("Houve um erro ao tentar remover o doador.");
                alert.showAndWait();
                e.printStackTrace(); // Considere remover em produção e logar o erro de outra forma
            } finally {
                buscarDoador(event); // Atualiza a lista de doadores na tabela
                factory.fecharConexao();
            }
        }
    }

    @FXML
    void verDoacoes(ActionEvent event) {
        // Não implementado
    }

    @FXML
    void buscarDoacao(ActionEvent event) {
        Doador filtro_doador = new Doador();
        Doacao filtro_doacao_min = new Doacao();
        Doacao filtro_doacao_max = new Doacao();

        if (!TextCodigoDoador.getText().isEmpty()) {
            filtro_doador.setCodigo(Long.parseLong(TextCodigoDoador.getText()));
        }
        if (!TextNome.getText().isEmpty()) {
            filtro_doador.setNome(TextNome.getText());
        }
        if (!TextCPF.getText().isEmpty()) {
            filtro_doador.setCpf(TextCPF.getText());
        }
        if (!TextContato.getText().isEmpty()) {
            filtro_doador.setContato(TextContato.getText());
        }

        filtro_doador.setRh(null);

        if (RHRadioQuaqluer.isSelected()) {
            filtro_doador.setRh(null);
        } else {
            if (RHRAdioPositivo.isSelected()) {
                filtro_doador.setRh(RH.POSITIVO);
            } else {
                if (RHRADIONegativo.isSelected()) {
                    filtro_doador.setRh(RH.NEGATIVO);
                }
            }
        }
        filtro_doador.setTipoSanguineo(null);
        if (RadioQualquer.isSelected()) {
            filtro_doador.setTipoSanguineo(null);
        } else {
            if (RadioA.isSelected()) {
                filtro_doador.setTipoSanguineo(TipoSanguineo.A);
            } else {
                if (RadioB.isSelected()) {
                    filtro_doador.setTipoSanguineo(TipoSanguineo.B);
                } else {
                    if (RadioAB.isSelected()) {
                        filtro_doador.setTipoSanguineo(TipoSanguineo.AB);
                    } else {
                        if (RadioO.isSelected()) {
                            filtro_doador.setTipoSanguineo(TipoSanguineo.O);
                        }
                    }
                }
            }
        }

        System.out.println(filtro_doador);

        if (!TexCodigoDoação.getText().isEmpty()) {
            filtro_doacao_min.setCodigo(Long.parseLong(TexCodigoDoação.getText()));
            filtro_doacao_max.setCodigo(Long.parseLong(TexCodigoDoação.getText()));
        }
        if (!TextVolumeInicial.getText().isEmpty()) {
            filtro_doacao_min.setVolume(Double.parseDouble(TextVolumeInicial.getText()));
        }
        if (!TextDataInicial.getText().isEmpty()) {
            if (isValidDate(TextDataInicial.getText())) {
                filtro_doacao_min.setData(parseDate(TextDataInicial.getText()));
            }
        }
        if (!TextHoraInicial.getText().isEmpty()) {
            if (isValidTime(TextHoraInicial.getText())) {
                filtro_doacao_min.setHora(parseTime(TextHoraInicial.getText()));
            }
        }
        if (!TextVolumeFinal.getText().isEmpty()) {
            double volumeFinal = Double.parseDouble(TextVolumeFinal.getText());
            filtro_doacao_max.setVolume(volumeFinal);
        }
        if (!TextDataFinal.getText().isEmpty()) {
            if (isValidDate(TextDataFinal.getText())) {
                LocalDate dataFinal = parseDate(TextDataFinal.getText());
                filtro_doacao_max.setData(dataFinal);
            }
        }
        if (!TextHoraFInal.getText().isEmpty()) {
            if (isValidTime(TextHoraFInal.getText())) {
                LocalTime horaFinal = parseTime(TextHoraFInal.getText());
                filtro_doacao_max.setHora(horaFinal);
            }
        }
        System.out.println(filtro_doacao_min);
        System.out.println(filtro_doacao_max);

        try {
            try {
                factory.abrirConexao();
            } catch (SQLException e) {
                // Handle the exception here
                e.printStackTrace();
            }
            DoacaoDAO dao = factory.getDAO(DoacaoDAO.class);
            DoadorDAO dao2 = factory.getDAO(DoadorDAO.class);
            List<Doacao> doacoes = dao.pesquisar(filtro_doacao_min, filtro_doacao_max);
            List<Doador> doadores = dao2.pesquisar(new Doador());

            doacoesTableView.getItems().clear();
            for (Doacao atual : doacoes) {
                for (Doador atual2 : doadores) {
                    if (atual.getDoador().getCodigo() == atual2.getCodigo()) {
                        atual.setDoador(atual2);
                        // doacoesTableView.getItems().add(atual);
                        System.out.println(atual2.toString());
                    }
                }
            }
            doacoesTableView.getItems().addAll(doacoes);
        } finally {
            factory.fecharConexao();
        }
    }

    @FXML
    void limparCamposDoacao(ActionEvent event) {
        TextCodigoDoador.clear();
        TextNome.clear();
        TextCPF.clear();
        TextContato.clear();
        TexCodigoDoação.clear();
        TextVolumeInicial.clear();
        TextVolumeFinal.clear();
        TextDataInicial.clear();
        TextDataFinal.clear();
        TextHoraInicial.clear();
        TextHoraFInal.clear();
        RHRadioQuaqluer.setSelected(true);
        RadioQualquer.setSelected(true);
    }

    private boolean isValidDate(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(text, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private LocalDate parseDate(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(text, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private boolean isValidTime(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime.parse(text, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private LocalTime parseTime(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            return LocalTime.parse(text, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}

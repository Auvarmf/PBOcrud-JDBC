package pbocrud;

import Entity.Mahasiswa;
import JDBC.MahasiswaSQL;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TambahMahasiswaController {

    @FXML
    private Button btnKembali;

    @FXML
    private Button btnSimpan;

    @FXML
    private TextField fieldFakultas;

    @FXML
    private TextField fieldJurusan;

    @FXML
    private TextField fieldNama;

    @FXML
    private TextField fieldNpm;

    @FXML
    private TextField fieldUniversitas;

    @FXML
    void TambahKeDataMahasiswa(ActionEvent event) throws IOException {
        Mahasiswa mhs = new Mahasiswa(fieldNpm.getText(), fieldNama.getText(), fieldJurusan.getText(),
            fieldFakultas.getText(), fieldUniversitas.getText());

        MahasiswaSQL mahasiswaSQL = new MahasiswaSQL();
        mahasiswaSQL.addMahasiswa(mhs);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataMahasiswa.fxml"));
        Parent root = loader.load();

        DataMahasiswaController dataMahasiswaController = loader.getController();

        Stage stage = (Stage) btnSimpan.getScene().getWindow();
        stage.setScene(new Scene(root));
    }


    @FXML
    void kembaliKeDataMahasiswa(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataMahasiswa.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) btnKembali.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

}

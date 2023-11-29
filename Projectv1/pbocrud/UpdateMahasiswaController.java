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

public class UpdateMahasiswaController {

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

    private MahasiswaSQL mahasiswaSQL;

    private Mahasiswa selectedMahasiswa;

    @FXML
    void initialize() {
        mahasiswaSQL = new MahasiswaSQL();
    }

    public void initData(Mahasiswa mahasiswa) {
        selectedMahasiswa = mahasiswa;
        // Set data to the fields
        fieldNpm.setText(mahasiswa.getNpm());
        fieldNama.setText(mahasiswa.getNama());
        fieldJurusan.setText(mahasiswa.getJurusan());
        fieldFakultas.setText(mahasiswa.getFakultas());
        fieldUniversitas.setText(mahasiswa.getUniversitas());
    }

    @FXML
    void UpdateKeDataMahasiswa(ActionEvent event) throws IOException {
        // Update Mahasiswa data
        selectedMahasiswa.setNama(fieldNama.getText());
        selectedMahasiswa.setJurusan(fieldJurusan.getText());
        selectedMahasiswa.setFakultas(fieldFakultas.getText());
        selectedMahasiswa.setUniversitas(fieldUniversitas.getText());

        // Perform SQL update
        mahasiswaSQL.updateMahasiswa(selectedMahasiswa);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataMahasiswa.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) btnKembali.getScene().getWindow();
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

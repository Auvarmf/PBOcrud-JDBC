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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class DataMahasiswaController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnTambah;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<Mahasiswa> mahasiswaTable;

    @FXML
    private TableColumn<Mahasiswa, String> npmColumn;

    @FXML
    private TableColumn<Mahasiswa, String> namaColumn;

    @FXML
    private TableColumn<Mahasiswa, String> jurusanColumn;

    @FXML
    private TableColumn<Mahasiswa, String> fakultasColumn;

    @FXML
    private TableColumn<Mahasiswa, String> universitasColumn;

    private MahasiswaSQL mahasiswaSQL;

    @FXML
    void deleteMahasiswa(ActionEvent event) {
        // Get selected Mahasiswa from the table
        Mahasiswa selectedMahasiswa = mahasiswaTable.getSelectionModel().getSelectedItem();

        if (selectedMahasiswa != null) {
            // Perform SQL delete
            mahasiswaSQL.deleteMahasiswa(selectedMahasiswa.getNpm());

            // Refresh the table
            mahasiswaTable.getItems().remove(selectedMahasiswa);
            System.out.println("Data Mahasiswa berhasil dihapus");
        } else {
            JOptionPane.showMessageDialog(null, "Tidak ada Mahasiswa yang dipilih untuk update", "Pilih Mahasiswa", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("No Mahasiswa selected for delete.");
        }
    }
    
    @FXML
    void initialize() {
        mahasiswaSQL = new MahasiswaSQL();
        // Initialize columns
        npmColumn.setCellValueFactory(new PropertyValueFactory<>("npm"));
        namaColumn.setCellValueFactory(new PropertyValueFactory<>("nama"));
        jurusanColumn.setCellValueFactory(new PropertyValueFactory<>("jurusan"));
        fakultasColumn.setCellValueFactory(new PropertyValueFactory<>("fakultas"));
        universitasColumn.setCellValueFactory(new PropertyValueFactory<>("universitas"));

        // Load data into the table
        mahasiswaTable.setItems(mahasiswaSQL.getAllMahasiswa());
    }

    @FXML
    void tambahMahasiswa(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TambahMahasiswa.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) btnTambah.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @FXML
    void updateMahasiswa(ActionEvent event) throws IOException {
        // Get selected Mahasiswa from the table
    Mahasiswa selectedMahasiswa = mahasiswaTable.getSelectionModel().getSelectedItem();

    if (selectedMahasiswa != null) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateMahasiswa.fxml"));
        Parent root = loader.load();

        UpdateMahasiswaController updateMahasiswaController = loader.getController();
        updateMahasiswaController.initData(selectedMahasiswa);

        Stage stage = (Stage) btnUpdate.getScene().getWindow();
        stage.setScene(new Scene(root));
    } else {
        JOptionPane.showMessageDialog(null, "Tidak ada Mahasiswa yang dipilih untuk update", "Pilih Mahasiswa", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("No Mahasiswa selected for update.");
    }
    }

}

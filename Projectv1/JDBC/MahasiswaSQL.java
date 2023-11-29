package JDBC;

import db.DBHelper;
import Entity.Mahasiswa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class MahasiswaSQL {
    private final Connection CONN;
    public boolean status;

    public MahasiswaSQL() {
        this.CONN = DBHelper.getConnection();
    }

    // addMahasiswa
    public void addMahasiswa(Mahasiswa mhs) {
        String insert = "INSERT INTO mahasiswa(npm, nama_mahasiswa, jurusan, fakultas, universitas) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = CONN.prepareStatement(insert)) {
            preparedStatement.setString(1, mhs.getNpm());
            preparedStatement.setString(2, mhs.getNama());
            preparedStatement.setString(3, mhs.getJurusan());
            preparedStatement.setString(4, mhs.getFakultas());
            preparedStatement.setString(5, mhs.getUniversitas());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Berhasil Memasukkan Data");
                status = true;
                // Display JOptionPane message
                JOptionPane.showMessageDialog(null, "Data Berhasil ditambahkan", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Gagal Memasukkan Data");
                status = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MahasiswaSQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal Memasukkan Data");
            status = false;
        }
    }
    
    // updateMahasiswa
    public void updateMahasiswa(Mahasiswa mhs) {
        String update = "UPDATE mahasiswa SET npm=?, nama_mahasiswa=?, jurusan=?, fakultas=?, universitas=? WHERE npm=?";

        try (PreparedStatement preparedStatement = CONN.prepareStatement(update)) {
            preparedStatement.setString(1, mhs.getNpm()); // Updated npm value
            preparedStatement.setString(2, mhs.getNama());
            preparedStatement.setString(3, mhs.getJurusan());
            preparedStatement.setString(4, mhs.getFakultas());
            preparedStatement.setString(5, mhs.getUniversitas());
            preparedStatement.setString(6, mhs.getNpm()); // Original npm value

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Berhasil Update Data");
                status = true;
                JOptionPane.showMessageDialog(null, "Data Berhasil diupdate", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Gagal Update Data");
                status = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MahasiswaSQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal Update Data");
            status = false;
        }
    }
    
    // deleteMahasiswa
    public void deleteMahasiswa(String npm) {
        String delete = "DELETE FROM mahasiswa WHERE npm=?";

        try (PreparedStatement preparedStatement = CONN.prepareStatement(delete)) {
            preparedStatement.setString(1, npm);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Berhasil Hapus Data");
                status = true;
            } else {
                System.out.println("Gagal Hapus Data");
                status = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MahasiswaSQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal Hapus Data");
            status = false;
        }
    }
    
    // getAllMahasiswa
    public ObservableList<Mahasiswa> getAllMahasiswa() {
        ObservableList<Mahasiswa> mahasiswaList = FXCollections.observableArrayList();
        String query = "SELECT * FROM mahasiswa";

        try (PreparedStatement preparedStatement = CONN.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String npm = resultSet.getString("npm");
                String nama = resultSet.getString("nama_mahasiswa");
                String jurusan = resultSet.getString("jurusan");
                String fakultas = resultSet.getString("fakultas");
                String universitas = resultSet.getString("universitas");

                Mahasiswa mhs = new Mahasiswa(npm, nama, jurusan, fakultas, universitas);
                mahasiswaList.add(mhs);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MahasiswaSQL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mahasiswaList;
    }
}

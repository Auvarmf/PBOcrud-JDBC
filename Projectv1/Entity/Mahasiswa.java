// Mahasiswa.java
package Entity;

public class Mahasiswa {
    private String npm;
    private String nama;
    private String jurusan;
    private String fakultas;
    private String universitas;

    public Mahasiswa(String npm, String nama, String jurusan, String fakultas, String universitas) {
        this.npm = npm;
        this.nama = nama;
        this.jurusan = jurusan;
        this.fakultas = fakultas;
        this.universitas = universitas;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public String getUniversitas() {
        return universitas;
    }

    public void setUniversitas(String universitas) {
        this.universitas = universitas;
    }

    
}

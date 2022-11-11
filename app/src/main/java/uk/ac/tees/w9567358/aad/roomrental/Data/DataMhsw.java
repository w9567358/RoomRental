package uk.ac.tees.w9567358.aad.roomrental.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class DataMhsw {
    @PrimaryKey
    public int npm;

    @ColumnInfo(name = "nama_mhsw")
    public String nama;

    @ColumnInfo(name = "kelas_mhsw")
    public String kelas;
    @ColumnInfo(name = "prodi_mhsw")
    public String prodi;


    public DataMhsw GenerateObject(int npm, String nama, String kelas, String prodi){
        DataMhsw temp = new DataMhsw();
        temp.nama = nama;
        temp.kelas = kelas;
        temp.npm = npm;
        temp.prodi = prodi;
        return temp;
    }

}


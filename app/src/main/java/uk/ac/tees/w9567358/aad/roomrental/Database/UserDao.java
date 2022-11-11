package uk.ac.tees.w9567358.aad.roomrental.Database;

import androidx.room.Delete;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import uk.ac.tees.w9567358.aad.roomrental.Data.DataMhsw;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM DataMhsw")
    List<DataMhsw> getAll();

    @Query("SELECT * FROM DataMhsw WHERE npm IN (:npm)")
    List<DataMhsw> loadAllByIds(int[] npm);


    @Query("SELECT * FROM DataMhsw WHERE npm = :npm")
    DataMhsw searchById(int npm);

    @Query("INSERT INTO DataMhsw (npm, nama_mhsw, kelas_mhsw, prodi_mhsw) VALUES (:npm,:nama,:kelas,:prodi)")
    void insertAll(String npm, String nama, String kelas, String prodi);

    @Insert(entity = DataMhsw.class, onConflict = OnConflictStrategy.IGNORE)
    void insert(DataMhsw... users);

    @Delete
    void delete(DataMhsw user);
}

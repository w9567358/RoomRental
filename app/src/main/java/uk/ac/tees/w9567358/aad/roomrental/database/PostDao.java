package uk.ac.tees.w9567358.aad.roomrental.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import uk.ac.tees.w9567358.aad.roomrental.models.Post;

@Dao
public interface PostDao {

    @Insert
     void insert(Post post);

    @Delete
    void delete(Post post);

    @Update
    void update(Post post);

    @Query("Select * from posts")
    LiveData<List<Post>> getAllPost();

    @Query("SELECT * FROM posts where attribute LIKE :query")
    LiveData<List<Post>> search(String query);




}

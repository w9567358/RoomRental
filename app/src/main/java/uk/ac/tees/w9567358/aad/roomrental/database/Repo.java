package uk.ac.tees.w9567358.aad.roomrental.database;

import androidx.lifecycle.LiveData;

import java.util.List;

import uk.ac.tees.w9567358.aad.roomrental.models.Post;

public interface Repo {

    LiveData<List<Post>> getAll();
    void insert(Post post);
    void delete(Post post);
   LiveData<List<Post>>search(String query);

}

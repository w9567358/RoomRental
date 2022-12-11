package uk.ac.tees.w9567358.aad.roomrental.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import uk.ac.tees.w9567358.aad.roomrental.models.Post;

public class RepoImpl implements Repo {

    PostDao postDao;

    public RepoImpl(Application application){
        DatabaseHelper db = DatabaseHelper.getInstance(application);
        postDao = db.postDao();
    }

    @Override
    public LiveData<List<Post>> getAll() {
        return postDao.getAllPost();
    }

    @Override
    public void insert(Post post) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                postDao.insert(post);

            }
        }).start();
    }

    @Override
    public void delete(Post post) {
        postDao.insert(post);
    }

    @Override
    public LiveData<List<Post>> search(String query) {
        return postDao.search(query);

    }

}


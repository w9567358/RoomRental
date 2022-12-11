package uk.ac.tees.w9567358.aad.roomrental.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import uk.ac.tees.w9567358.aad.roomrental.database.RepoImpl;
import uk.ac.tees.w9567358.aad.roomrental.models.Post;


public class HomeFragmentViewModel extends AndroidViewModel {


    private RepoImpl repo;
    private MutableLiveData<List<Post>> postList;

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
        repo = new RepoImpl(application);
    }


    public void insert(Post post){
        repo.insert(post);
    }
    public void delete(Post post){
        AsyncTask.execute(() -> delete(post));
                //repo.delete(post);
    }

    public LiveData<List<Post>> getAll(){
        return repo.getAll();
    }
     public  LiveData<List<Post>> search(String q){
        return  repo.search(q);
   }

}


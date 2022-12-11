package uk.ac.tees.w9567358.aad.roomrental.util;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class LiveDataConverter {
    interface Confirmer<T> {
        boolean test(T thingy);
    }
    private LiveDataConverter(){}

    @MainThread
    public static <X> LiveData<X> filter(@NonNull LiveData<X> source,
                                  @NonNull final Confirmer<X> confirmer) {
        final MediatorLiveData<X> result=new MediatorLiveData<>();

        result.addSource(source, new Observer<X>() {
            @Override
            public void onChanged(@Nullable X x) {
                if (confirmer.test(x)) {
                    result.setValue(x);
                }
            }
        });

        return(result);
    }
}
package com.ttn.rxjava.viewmodel;

/*
 * Created by Naveen Verma on 25/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ttn.rxjava.model.repository.Repository;

import java.util.concurrent.Future;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;

public class MainViewModel extends ViewModel {

    private Repository repository;

    public MainViewModel() {
        repository = Repository.getInstance();
    }

    public Future<Observable<ResponseBody>> makeFutureQuery(){
        return repository.makeFutureQuery();
    }

    /**
     * getting the LiveData result
     */
    public LiveData<ResponseBody> makeQuery(){
        return repository.makeReactiveQuery();
    }
}

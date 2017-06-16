package chenyijie.mvvm_people_tutorial.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import chenyijie.mvvm_people_tutorial.R;
import chenyijie.mvvm_people_tutorial.data.ApiManager;
import chenyijie.mvvm_people_tutorial.data.PeopleInfo;
import chenyijie.mvvm_people_tutorial.model.People;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chenyijie on 2017/6/14.
 */

public class PeopleViewModel extends Observable {
    public ObservableInt peopleProgress;
    public ObservableInt peopleRecycler;
    public ObservableInt peopleLabel;
    public ObservableField<String> msgLabel;

    private List<People> peopleList;
    private Context context;
    private CompositeDisposable compositeDisposable;

    public PeopleViewModel(@NonNull Context context){
        this.context = context;
        this.peopleList = new ArrayList<>();
        this.peopleProgress = new ObservableInt(View.GONE);
        this.peopleRecycler = new ObservableInt(View.GONE);
        this.peopleLabel = new ObservableInt(View.VISIBLE);
        this.msgLabel = new ObservableField<>(context.getString(R.string.default_loading_people));

        compositeDisposable = new CompositeDisposable();
    }

    public void onFabClick(View view){
        initViews();
        fetchData();
    }

    //It is "public" to show an example of test
    public void initViews(){
        peopleLabel.set(View.GONE);
        peopleRecycler.set(View.GONE);
        peopleProgress.set(View.VISIBLE);
    }

    private void fetchData(){
        Disposable disposable = ApiManager.getInstance().getPeople().getPeople(ApiManager.RANDOM_USER_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PeopleInfo>() {
                    @Override
                    public void accept(PeopleInfo peopleInfo) throws Exception {
                        changePeopleList(peopleInfo.getPeopleList());
                        peopleProgress.set(View.GONE);
                        peopleLabel.set(View.GONE);
                        peopleRecycler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        msgLabel.set(context.getString(R.string.error_loading_people));
                        peopleProgress.set(View.GONE);
                        peopleLabel.set(View.VISIBLE);
                        peopleRecycler.set(View.GONE);
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void changePeopleList(List<People> peopleList){
        this.peopleList.addAll(peopleList);
        setChanged();
        notifyObservers(); // 自動去更改data binding的資料
    }

    public List<People> getPeopleList(){
        return this.peopleList;
    }

    private void unSubscribe(){
        if(compositeDisposable != null && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }

    public void onStop(){
        unSubscribe();
        compositeDisposable = null;
        context = null;
    }
}

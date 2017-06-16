package chenyijie.mvvm_people_tutorial.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Observable;
import java.util.Observer;

import chenyijie.mvvm_people_tutorial.R;
import chenyijie.mvvm_people_tutorial.data.ApiManager;
import chenyijie.mvvm_people_tutorial.databinding.ActivityPeopleBinding;
import chenyijie.mvvm_people_tutorial.viewmodel.PeopleViewModel;

public class PeopleActivity extends AppCompatActivity implements Observer {
    private ActivityPeopleBinding binding;
    private PeopleViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setSupportActionBar(binding.toolbar);
        setupListPeopleView(binding.listPeople);
        setObserver(viewModel);
    }

    private void initBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_people);
        viewModel = new PeopleViewModel(this);
        binding.setMainViewModel(viewModel);
    }

    private void setupListPeopleView(RecyclerView listPeople){
        PeopleAdapter adapter = new PeopleAdapter();
        listPeople.setAdapter(adapter);
        listPeople.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setObserver(Observable observable){
        observable.addObserver(this);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_github) {
            startActivityActionView();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startActivityActionView() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ApiManager.PROJECT_URL)));
    }

    // 研究一下java.util.Observer是幹啥的
    @Override
    public void update(Observable observable, Object data) {
        if(observable instanceof PeopleViewModel){
            PeopleAdapter adapter = (PeopleAdapter) binding.listPeople.getAdapter();
            PeopleViewModel viewModel = (PeopleViewModel) observable;
            adapter.setPeopleList(viewModel.getPeopleList());
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        viewModel.onStop();
    }
}

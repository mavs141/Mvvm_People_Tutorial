package chenyijie.mvvm_people_tutorial.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import chenyijie.mvvm_people_tutorial.R;
import chenyijie.mvvm_people_tutorial.databinding.ActivityDetailBinding;
import chenyijie.mvvm_people_tutorial.model.People;
import chenyijie.mvvm_people_tutorial.viewmodel.PeopleDetailViewModel;

/**
 * Created by chenyijie on 2017/6/15.
 */

public class PeopleDetailActivity extends AppCompatActivity {
    private static final String EXTRA_PEOPLE = "EXTRA_PEOPLE";
    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initBinding();
        setSupportActionBar(binding.toolbar);
        displayHomeAsEnabled();
        getExtraFromIntent();
    }

    private void initBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
    }

    private void displayHomeAsEnabled(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void getExtraFromIntent(){
        People people = (People) getIntent().getParcelableExtra(EXTRA_PEOPLE);
//        People people = (People) getIntent().getSerializableExtra(EXTRA_PEOPLE);
        PeopleDetailViewModel viewModel = new PeopleDetailViewModel(people);
        binding.setDetailViewModel(viewModel);
        setTitle(people.name.title + "." + people.name.firstName + "." +people.name.lastName);
    }

    // 這個method是用來讓PeopleActivity可以傳遞people參數用的
    // 之所以要把People設定為Serializable也是為了讓bundle可以傳遞（？
    public static Intent launchDetailActivity(Context context, People people){
        Intent intent = new Intent(context, PeopleDetailActivity.class);
        intent.putExtra(EXTRA_PEOPLE, people);
        return intent;
    }
}

package chenyijie.mvvm_people_tutorial.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import chenyijie.mvvm_people_tutorial.model.People;
import chenyijie.mvvm_people_tutorial.view.PeopleDetailActivity;

/**
 * Created by chenyijie on 2017/6/15.
 */

public class ItemPeopleViewModel extends BaseObservable {
    private People people;
    private Context context;

    public ItemPeopleViewModel(People people, Context context){
        this.people = people;
        this.context = context;
    }

    public String getFullName(){
        people.fullName = people.name.title + "."
                + people.name.firstName + "." + people.name.lastName;
        return people.fullName;
    }

    public String getCell(){
        return people.cell;
    }

    public String getMail(){
        return people.email;
    }

    public String getPictureProfile(){
        return people.picture.medium;
    }

    public void setPeople(People people){
        this.people = people;
        notifyChange();
    }

    public void onItemClick(View view){
        context.startActivity(PeopleDetailActivity.launchDetailActivity(view.getContext(), people));
    }

    // 研究一下BindingAdapter
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url){
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }
}

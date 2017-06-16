package chenyijie.mvvm_people_tutorial.viewmodel;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import chenyijie.mvvm_people_tutorial.model.People;

/**
 * Created by chenyijie on 2017/6/15.
 */

public class PeopleDetailViewModel {
    private People people;

    public PeopleDetailViewModel(People people){
        this.people = people;
    }

    public String getFullUserName(){
        people.fullName = people.name.title + "." + people.name.firstName + "." +people.name.lastName;
        return people.fullName;
    }

    public String getUserName(){
        return people.login.userName;
    }

    public String getEmail(){
        return people.email;
    }

    public int getEmailVisibility(){
        return (people.hasEmail() ? View.VISIBLE : View.INVISIBLE);
    }

    public String getCell(){
        return people.cell;
    }

    public String getPictureProfile(){
        return people.picture.large;
    }

    public String getAddress(){
        return people.location.street + "  " + people.location.city + "  " +people.location.state;
    }

    public String getGender(){
        return people.gender;
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String imageUrl){
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .into(imageView);
    }

}

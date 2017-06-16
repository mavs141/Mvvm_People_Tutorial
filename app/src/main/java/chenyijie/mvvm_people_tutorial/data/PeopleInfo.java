package chenyijie.mvvm_people_tutorial.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import chenyijie.mvvm_people_tutorial.model.People;

/**
 * Created by chenyijie on 2017/6/14.
 */

public class PeopleInfo {
    @SerializedName("results")
    private List<People> peopleList;

    public List<People> getPeopleList(){
        return this.peopleList;
    }

    public void setPeopleList(List<People> peopleList){
        this.peopleList = peopleList;
    }
}

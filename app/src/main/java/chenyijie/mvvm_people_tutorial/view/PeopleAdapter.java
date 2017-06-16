package chenyijie.mvvm_people_tutorial.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import chenyijie.mvvm_people_tutorial.R;
import chenyijie.mvvm_people_tutorial.databinding.ItemPeopleBinding;
import chenyijie.mvvm_people_tutorial.model.People;
import chenyijie.mvvm_people_tutorial.viewmodel.ItemPeopleViewModel;

/**
 * Created by chenyijie on 2017/6/15.
 */

public class PeopleAdapter extends RecyclerView.Adapter {
    private List<People> peopleList;

    public void setPeopleList(List<People> peopleList){
        this.peopleList = peopleList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPeopleBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext())
                        , R.layout.item_people
                        , parent
                        , false);
        return new PeopleHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        People selectedPeople = peopleList.get(position);
        PeopleHolder peopleHolder = (PeopleHolder) holder;

        peopleHolder.bindPeople(selectedPeople);
    }

    @Override
    public int getItemCount() {
        if(peopleList == null) return 0;
        return peopleList.size();
    }

    public static class PeopleHolder extends RecyclerView.ViewHolder{
        ItemPeopleBinding binding;

        public PeopleHolder(ItemPeopleBinding binding) {
            super(binding.itemPeople);
            this.binding = binding;
        }

        public void bindPeople(People people){
            if(binding.getItemPeopleViewModel() == null){
                binding.setItemPeopleViewModel(new ItemPeopleViewModel(people, itemView.getContext()));
            }else{
                binding.getItemPeopleViewModel().setPeople(people);
            }
        }
    }
}

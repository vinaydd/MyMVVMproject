package com.example.mymvvmproject.repositories;

import android.arch.lifecycle.MutableLiveData;

import com.example.mymvvmproject.models.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class ItemPlaceRepository {
  private  static  ItemPlaceRepository   instance;
  private ArrayList<ItemModel> dataset  = new ArrayList<>();

  public  static  ItemPlaceRepository getInstance(){
    if(instance==null){
      instance = new ItemPlaceRepository();
    }
    return instance;
  }
  public MutableLiveData<List<ItemModel>> getItemdata(){
    setdata();
    MutableLiveData<List<ItemModel>> listMutableLiveData  = new MutableLiveData<List<ItemModel>>();
    listMutableLiveData.setValue(dataset);
    return listMutableLiveData;
  }

  private void  setdata(){
    dataset.add(new ItemModel("vinay","Singh"));
    dataset.add(new ItemModel("vinay","Singh"));
    dataset.add(new ItemModel("vinay","Singh"));
    dataset.add(new ItemModel("vinay","Singh"));
    dataset.add(new ItemModel("vinay","Singh"));

  }



}

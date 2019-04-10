package com.example.mymvvmproject.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.example.mymvvmproject.models.ItemModel;
import com.example.mymvvmproject.repositories.ItemPlaceRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
  private MutableLiveData<List<ItemModel>>  listMutableLiveData;
  private ItemPlaceRepository itemPlaceRepository ;
  private  MutableLiveData<Boolean>  isProgressUpdate = new MutableLiveData<>();
  public  void  init(){
    if(listMutableLiveData !=null){
      return;
    }
    itemPlaceRepository = ItemPlaceRepository.getInstance();
    listMutableLiveData =  itemPlaceRepository.getItemdata();
  }
  public LiveData<List<ItemModel>> getItem(){
    return  listMutableLiveData;
  }

  public   void  AddNewValue(final ItemModel  itemModel){
    isProgressUpdate.setValue(true);
    new AsyncTask<Void,Void,Void>(){

      @Override
      protected Void doInBackground(Void... voids) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

        return null;
      }

      @Override
      protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        List<ItemModel> data = listMutableLiveData.getValue();
        data.add(itemModel);
        listMutableLiveData.postValue(data);
        isProgressUpdate.postValue(false);
      }

    }.execute();

  }

  public  LiveData<Boolean> getUpdating(){
    return isProgressUpdate;
  }

}

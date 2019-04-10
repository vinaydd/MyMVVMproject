package com.example.mymvvmproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mymvvmproject.adpter.NotificationListAdapter;
import com.example.mymvvmproject.models.ItemModel;
import com.example.mymvvmproject.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  RecyclerView  recyclerView;
  ProgressBar  progressBar;
  NotificationListAdapter  adapter;
  private MainActivityViewModel  mainActivityViewModel  ;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    recyclerView = findViewById(R.id.recycle);
    progressBar = findViewById(R.id.progressBar);
    mainActivityViewModel  = ViewModelProviders.of(this).get(MainActivityViewModel.class);
    mainActivityViewModel.init();
    mainActivityViewModel.getItem().observe(this, new Observer<List<ItemModel>>() {
      @Override
      public void onChanged(@Nullable List<ItemModel> itemModels) {
        adapter.notifyDataSetChanged();
      }
    });
    mainActivityViewModel.getUpdating().observe(this,new Observer<Boolean>() {
      @Override
      public void onChanged(@Nullable Boolean aBoolean) {
        if(aBoolean){
         showProgressBar();
        }else {
          hideProgressBar();
          recyclerView.smoothScrollToPosition(mainActivityViewModel.getItem().getValue().size()-1);
        }
      }
    });

    findViewById(R.id.addNew).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mainActivityViewModel.AddNewValue(new ItemModel(
          "sandeep","Singh"
        ));
      }
    });
    initRecycleView();
  }

  private void initRecycleView() {
   adapter  = new NotificationListAdapter(MainActivity.this,mainActivityViewModel.getItem().getValue());
   RecyclerView.LayoutManager layoutManager  = new LinearLayoutManager(this);
   recyclerView.setLayoutManager(layoutManager);
   recyclerView.setAdapter(adapter);
  }

  private  void  showProgressBar(){
    progressBar.setVisibility(View.VISIBLE);

  }


  private  void  hideProgressBar(){
    progressBar.setVisibility(View.GONE);

  }
}

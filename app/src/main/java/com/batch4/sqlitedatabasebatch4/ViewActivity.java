package com.batch4.sqlitedatabasebatch4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.batch4.sqlitedatabasebatch4.databinding.ActivityViewBinding;

public class ViewActivity extends AppCompatActivity {

    ActivityViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view);
//
        binding = ActivityViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DataBaseHelper obj = new DataBaseHelper(this);
        String data = obj.getData();
        binding.viewData.setText(data);
    }
}
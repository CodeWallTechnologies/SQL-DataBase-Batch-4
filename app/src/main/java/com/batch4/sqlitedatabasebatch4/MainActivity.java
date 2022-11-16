package com.batch4.sqlitedatabasebatch4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.batch4.sqlitedatabasebatch4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        DataBaseHelper obj = new DataBaseHelper(this);


        binding.save.setOnClickListener(v -> {
//            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
            String id = binding.id.getText().toString();
            String name = binding.name.getText().toString();
            String email = binding.email.getText().toString();
            String mobile = binding.mobile.getText().toString();
            if(id.equals(" ") | name.equals(" ")|email.equals(" ")|
            mobile.equals(" ")
            ){
                Toast.makeText(this, "Please fill the blanks..", Toast.LENGTH_SHORT).show();
            }else{
                if(email.endsWith("@gmail.com")){
                    obj.saveData(id,name,email, mobile);
                    Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Your email is not correct", Toast.LENGTH_SHORT).show();
                }
                //                if ( obj.saveData(id,name,email, mobile)==-1){
//                    Toast.makeText(this, "Please check errors", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
//                }

            }
        });

        binding.view.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this,ViewActivity.class);
                startActivity(intent);
        });

        binding.deleteBtn.setOnClickListener(v->{
            String id = binding.id.getText().toString();
            long id1 = Long.parseLong(id);
            obj.deleteData(id1);
        });



    }
}
package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todo.helper.Database;

public class EditorActivity extends AppCompatActivity {

    private EditText edittitle, editdecription;
    private Button btnsave;
    private Database db = new Database(this);
    private String id,title,description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

     edittitle = findViewById(R.id.titleinput);
     editdecription = findViewById(R.id.descriptioninput);
     btnsave = findViewById(R.id.savebtn);

     id = getIntent().getStringExtra("id");
     title = getIntent().getStringExtra("title");
     description = getIntent().getStringExtra("desription");

     if (id == null || id.equals("")){
         setTitle("Add Todo");
         edittitle.setText(title);
         editdecription.setText(description);
     }else{
         setTitle("Edit Todo");
         edittitle.setText(title);
         editdecription.setText(description);
     }
     btnsave.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             try {
                 if (id == null || id.equals("")){
                        save();
                 } else{
                    edit();
                 }

             } catch (Exception e){
                 Log.e("Save As", e.getMessage());
             }
         }
     });
    }
    private  void save() {
        if (String.valueOf(edittitle.getText()).equals("") || String.valueOf(editdecription.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Please fill in all your data!", Toast.LENGTH_SHORT).show();
        } else {
            db.insert(edittitle.getText().toString(), editdecription.getText().toString());
            finish();
        }
    }
    private  void edit() {
        if (String.valueOf(edittitle.getText()).equals("") || String.valueOf(editdecription.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Please fill in all your data!", Toast.LENGTH_SHORT).show();
        } else {
            db.update(Integer.parseInt(id), edittitle.getText().toString(), editdecription.getText().toString());
            finish();
        }
    }
}
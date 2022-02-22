package com.example.myapplication01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText editTextID, editName, editDept, editcommet, editip, editdate, editkorea, editacrobat, editcad, editphotoshop, editsolidworks, editcommnet_2;
    Button buttonInsert, buttonUpdate, buttonDelete, buttonView;
    String typespinner;
    TextView textView;

    String[] items = {"데스크탑","노트북","기타"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Spinner spinner = findViewById(R.id.typeSpinner);
        textView = findViewById(R.id.typeTextView);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //textView.setText(items[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //textView.setText("선택 : ");

            }

        });
        myDB = new DatabaseHelper(this);

        editTextID = findViewById(R.id.idEditText);
        editName = findViewById(R.id.nameEditText);
        editDept = findViewById(R.id.teamEditText);
        typespinner = spinner.getSelectedItem().toString();
        editcommet = findViewById(R.id.commentEditText);
        editip = findViewById(R.id.ipEditText);
        editdate = findViewById(R.id.dateEditText);
        editkorea = findViewById(R.id.textEditText);
        editacrobat = findViewById(R.id.acrobatEditText);
        editcad = findViewById(R.id.cadEditText);
        editphotoshop = findViewById(R.id.photoshopEditText);
        editsolidworks = findViewById(R.id.etcEditText);
        editcommnet_2 = findViewById(R.id.commentEditText2);

        buttonInsert = findViewById(R.id.saveBtn);
        buttonUpdate = findViewById(R.id.updateBtn);
        buttonDelete = findViewById(R.id.delBtn);
        buttonView = findViewById(R.id.viewBtn);


        AddData();
        viewAll();
        updateData();
        DeleteData();
    }

    //데이터 추가하기
    public void AddData() {
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDB.InsertData(editName.getText().toString(), editDept.getText().toString(), typespinner, editcommet.getText().toString(), editip.getText().toString(),
                        editdate.getText().toString(), editkorea.getText().toString(), editacrobat.getText().toString(),
                        editcad.getText().toString(), editphotoshop.getText().toString(), editsolidworks.getText().toString(), editcommnet_2.getText().toString());


                if (isInserted == true)
                    ShowMessage("저장","저장되었습니다");
                    //Toast.makeText(MainActivity.this, "데이터추가 성공", Toast.LENGTH_LONG).show();
                else
                    ShowMessage("실패","데이터저장 실패");
                    //Toast.makeText(MainActivity.this, "데이터추가 실패", Toast.LENGTH_LONG).show();
            }
        });
    }


    //데이터베이스 읽어오기
   public void viewAll()
    {
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.getAllData();
                if(res.getCount() == 0){
                    ShowMessage("실패", "데이터를 찾을 수 없습니다.");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID :" +res.getString(0)+"\n");
                    buffer.append("사원 :" +res.getString(1)+"\n");
                    buffer.append("부서 :" +res.getString(2)+"\n");
                    buffer.append("구분 :" +res.getString(3)+"\n");
                    buffer.append("IP :" +res.getString(5)+"\n");
                    //buffer.append("기타 :" +res.getString(3)+"\n");
                }

                ShowMessage("데이터",buffer.toString());
            }
        });
    }


    //데이터 수정하기
    public void updateData(){
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate = myDB.updateData(editTextID.getText().toString(),editName.getText().toString(), editDept.getText().toString(), typespinner, editcommet.getText().toString(), editip.getText().toString(),
                        editdate.getText().toString(), editkorea.getText().toString(), editacrobat.getText().toString(),
                        editcad.getText().toString(), editphotoshop.getText().toString(), editsolidworks.getText().toString(), editcommnet_2.getText().toString());

                if (isUpdate == true)
                    Toast.makeText(MainActivity.this, "데이터 수정 성공", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "데이터 수정 실패", Toast.LENGTH_LONG).show();
            }

        });
    }


    //데이터 삭제하기
     public void DeleteData() {
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletRows = myDB.deleteData(editTextID.getText().toString());
                if(deletRows>0)
                    Toast.makeText(MainActivity.this, "데이터 수정 성공", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "데이터 수정 실패", Toast.LENGTH_LONG).show();
            }
        });
     }


     public void ShowMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
     }


}
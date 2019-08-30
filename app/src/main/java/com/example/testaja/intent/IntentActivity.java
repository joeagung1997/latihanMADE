package com.example.testaja.intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testaja.R;

public class IntentActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMoveActivity;
    Button btnMoveWithDataActivity;
    Button btnMoveWithObject;
    Button btnDialPhone;

    Button btnMoveForResult;
    TextView tvResult;

    private int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        btnMoveActivity = findViewById(R.id.btn_move_activity);
        btnMoveActivity.setOnClickListener(this);

        btnMoveWithDataActivity = findViewById(R.id.btn_move_activity_data);
        btnMoveWithDataActivity.setOnClickListener(this);

        btnMoveWithObject = findViewById(R.id.btn_move_activity_object);
        btnMoveWithObject.setOnClickListener(this);

        btnDialPhone = findViewById(R.id.btn_dial_number);
        btnDialPhone.setOnClickListener(this);

        btnMoveForResult = findViewById(R.id.btn_move_for_result);
        btnMoveForResult.setOnClickListener(this);

        tvResult = findViewById(R.id.tv_result);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_move_activity:
                Intent moveActivity = new Intent(IntentActivity.this, MoveActivity.class);
                startActivity(moveActivity);
                break;


            case R.id.btn_move_activity_data:
                Intent moveWithDataIntent = new Intent(IntentActivity.this, MoveWithDataActivity.class);
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "DicodngAcademy");
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 0);
                startActivity(moveWithDataIntent);
                break;

            case R.id.btn_move_activity_object:
                ModelPerson modelPerson = new ModelPerson();
                modelPerson.setName("Agung");
                modelPerson.setAge(5);
                modelPerson.setEmail("joeagung456@gmail.com");
                modelPerson.setCity("Jakarta");

                Intent moveWithObjectIntent = new Intent(IntentActivity.this, MoveWithDataObject.class);
                moveWithObjectIntent.putExtra(MoveWithDataObject.EXTRA_PERSON, modelPerson);
                startActivity(moveWithObjectIntent);
                break;

            case R.id.btn_dial_number:
                String phoneNumber = "081210841382";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(dialPhoneIntent);
                break;

            case R.id.btn_move_for_result:
                Intent moveForResultIntent = new Intent(IntentActivity.this,MoveForResultActivity.class);
                startActivityForResult(moveForResultIntent, REQUEST_CODE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == MoveForResultActivity.RESULT_CODE) {

                int selectedValue = data.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE,0);
                tvResult.setText(String.format("Hasil :  %s", selectedValue));
            }
        }
    }
}

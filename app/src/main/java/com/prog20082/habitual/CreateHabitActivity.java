package com.prog20082.habitual;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView.OnItemClickListener;

public class CreateHabitActivity extends AppCompatActivity implements OnItemSelectedListener {

    EditText edtHabitName;
    EditText edtGoal;
    Spinner spnGoalUnits;
    CheckBox chkBadHabit;
    CheckBox chkGoodHabit;
    CheckBox chkNotify;
    Button btnSetTime;

    private static final int MAIN_ACTIVITY = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_habit);

        //Receive intent from MainActivity
        Intent intent = getIntent();
        //Receive a toast message
        Toast.makeText(getApplicationContext(), "You're on the second page dorks", Toast.LENGTH_LONG).show();

        //Getting all the widgets from activity_create_habit.xml
        edtHabitName = (EditText) findViewById(R.id.edtHabitName);
        edtGoal = (EditText) findViewById(R.id.edtGoal);
        chkBadHabit = (CheckBox) findViewById(R.id.chkBadHabit);
        chkGoodHabit = (CheckBox) findViewById(R.id.chkGoodHabit);
        chkNotify = (CheckBox) findViewById(R.id.chkNotify);
        btnSetTime = (Button) findViewById(R.id.btnSetTime);

        spnGoalUnits = (Spinner) findViewById(R.id.spnGoalUnits);

        //Create an ArrayAdapter using the string array and default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.goals_array, android.R.layout.simple_spinner_item);
        //Choose what the list looks like when its selected
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //apply adapter to spinner
        spnGoalUnits.setAdapter(adapter);

        spnGoalUnits.setOnItemSelectedListener(this);


        //When the user selects to get notified, the set time button is enabled to be clicked
        chkNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chkNotify.isChecked()) {
                    btnSetTime.setEnabled(true);
                }
            }
        });

    }





    public void submitHabit(View view) {

        String habitName = edtHabitName.getText().toString();
        String goal = edtGoal.getText().toString();

        //Have to work on spnGoalUnits forgot that mo; onSelected junk

        //Send a new parcelable habit for testing purposes to see if it goes to next screen
        Habit parcelableHabit = new Habit(habitName, true, 3, goal, false);
        Intent intent = new Intent(this, CreateHabitActivity.class);
        intent.putExtra("habit", parcelableHabit);
        setResult(RESULT_OK, intent);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
        parent.getItemAtPosition(pos);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void notifyMe(View view) {

        //Create a dialog fragment that will initiate the TimePicker Fragement
        DialogFragment timeFragment = new TimePickerFragment();
        timeFragment.show(getFragmentManager(), "TimePicker");




    }
}

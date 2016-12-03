package com.prog20082.habitual;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.provider.AlarmClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity  {

    private static final int CREATE_HABIT_ACTIVITY = 992;

    ListView habitsListView;
    ArrayList<Habit> habitsArray;
    ArrayAdapter<Habit> adapter;
    TextView txtName;
    TextView txtGoals;
    Bundle b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        habitsListView = (ListView) findViewById(R.id.habitsListView);
        txtName = (TextView) findViewById(R.id.txtName);
        txtGoals = (TextView) findViewById(R.id.txtGoals);
        habitsArray = new ArrayList<Habit>();

        Intent notifyIntent = new Intent(this, NotificationPublisher.class);
        notifyIntent.getStringExtra("hour");
        notifyIntent.getStringExtra("minute");


        long futureTimeInMilliSec = System.currentTimeMillis();

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.set(AlarmManager.RTC, futureTimeInMilliSec, pendingIntent);

    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        Toast.makeText(this, "Coming from the createHabit Page", Toast.LENGTH_LONG).show();


        if (resultCode == RESULT_OK) {
            b = data.getExtras();
            if (b != null) {
                Habit h = data.getParcelableExtra("habit");
                txtName.setText(h.getName().toString());
                txtGoals.setText(h.getGoalUnits().toString());
                //Habit h = (Habit) data.getParcelableExtra("habit");
                habitsArray.add(h);
                habitsListView.setAdapter(adapter);
            }
        }

    }

    public void addHabit(View view) {

        Intent intent = new Intent(this, CreateHabitActivity.class);
        startActivityForResult(intent, CREATE_HABIT_ACTIVITY);


    }



}

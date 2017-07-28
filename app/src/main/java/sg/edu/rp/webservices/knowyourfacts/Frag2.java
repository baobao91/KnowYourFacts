package sg.edu.rp.webservices.knowyourfacts;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag2 extends Fragment {

    Button btnChange, btnReadLater;
    TextView tvColor2;

    int reqCode = 12345;
    AlarmManager am;

    public Frag2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag2, container, false);

        btnChange = (Button)v.findViewById(R.id.buttonChange);
        btnReadLater = (Button)v.findViewById(R.id.buttonRead);

        tvColor2 = (TextView) v.findViewById(R.id.textViewFrag2);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvColor2.setBackgroundColor(RandomColor());
            }
        });

        btnReadLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 10);

                Intent intent = new Intent(getActivity(), MyReceiver.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        getActivity(), reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);
                // A good to have id, in case you want to cancel it
                // programmatically later on

                am = (AlarmManager)getActivity().getSystemService(Activity.ALARM_SERVICE);

                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
            }
        });
        return v;
    }

    public int RandomColor() {
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

}
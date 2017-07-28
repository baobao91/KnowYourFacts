package sg.edu.rp.webservices.knowyourfacts;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.ULocale;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag1 extends Fragment {

    Button btnChange, btnReadLater;
    TextView tvColor1;

    int reqCode = 12345;
    AlarmManager am;

    public Frag1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag1, container, false);

        btnChange = (Button)v.findViewById(R.id.buttonChange);
        btnReadLater = (Button)v.findViewById(R.id.buttonRead);

        tvColor1 = (TextView) v.findViewById(R.id.textViewFrag1);

       btnChange.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               tvColor1.setBackgroundColor(RandomColor());
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

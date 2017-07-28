package sg.edu.rp.webservices.knowyourfacts;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> al;
    MyFragmentPagerAdapter adapter;
    ViewPager vPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vPager = (ViewPager) findViewById(R.id.viewPager1);

        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add(new Frag1());
        al.add(new Frag2());
        al.add(new Frag3());

        adapter = new MyFragmentPagerAdapter(fm, al);

        vPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
         if (id == R.id.next) {
             int max = vPager.getChildCount();

             if (vPager.getCurrentItem() < max-1) {
                 int nextPage = vPager.getCurrentItem() + 1;
                 vPager.setCurrentItem(nextPage, true);
             }
         } else if (id == R.id.previous) {
             int previousPage = vPager.getCurrentItem() - 1;
             vPager.setCurrentItem(previousPage, true);

         } else if (id == R.id.random) {

             Random ran = new Random();
             int any = vPager.getChildCount();
             int ranAny = ran.nextInt(any);
             vPager.setCurrentItem(ranAny, true);
         }
        return super.onOptionsItemSelected(item);
    }
}

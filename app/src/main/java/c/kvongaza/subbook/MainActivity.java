package c.kvongaza.subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Subscription> subscriptionList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu mainMenu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainmenu,mainMenu);

        return super.onCreateOptionsMenu(mainMenu);
    }

    // Taken from https://developer.android.com/guide/topics/ui/menus.html
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.addSub:
                Intent i = new Intent(getApplicationContext(), SubscriptionActivity.class);
                i.putExtra("name", "" );
                i.putExtra("date", new Date() );
                i.putExtra("amount", 0 );
                i.putExtra("comment", "" );
                startActivityForResult(i, 100);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Switch between activities, ref: https://www.androidhive.info/2011/08/how-to-switch-between-activities-in-android/
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 100){
            this.subscriptionList.add(new Subscription(data.getExtras().getString("name"),
                    new Date(data.getExtras().getString("date")),
                    data.getExtras().getDouble("amount"),
                    data.getExtras().getString("comment")));

        }

    }
}

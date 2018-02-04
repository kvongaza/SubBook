package c.kvongaza.subbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

// Controller for the view subscription

public class SubscriptionActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscription);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu subMenu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.submenu,subMenu);

        return super.onCreateOptionsMenu(subMenu);
    }

    // Taken from https://developer.android.com/guide/topics/ui/menus.html
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.deleteSub:
                return true;
            case R.id.saveSub:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

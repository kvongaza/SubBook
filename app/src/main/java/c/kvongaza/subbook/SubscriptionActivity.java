package c.kvongaza.subbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// Controller for the view subscription

public class SubscriptionActivity extends AppCompatActivity {


    private TextView nameText;
    private TextView dateText;
    private TextView priceText;
    private TextView commentText;
    private Integer index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscription);
        Intent i = getIntent();
        index = i.getIntExtra("index", 0);

        nameText = findViewById(R.id.subName);
        nameText.setText(i.getStringExtra("name"));

        dateText = findViewById(R.id.subDate);
        dateText.setText(i.getStringExtra("date"));

        priceText = findViewById(R.id.subCost);
        priceText.setText(String.valueOf(i.getDoubleExtra("amount", 0.0))); // removed "$" + ...

        commentText = findViewById(R.id.subComment);
        commentText.setText(i.getStringExtra("comment"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu subMenu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.submenu,subMenu);

        return super.onCreateOptionsMenu(subMenu);
    }

//     Taken from https://developer.android.com/guide/topics/ui/menus.html
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Intent i = new Intent();
        switch (item.getItemId()) {
            // delete not yet implemented
            case R.id.deleteSub:
                i.putExtra("index", index);
                setResult(Activity.RESULT_OK,i);
                finish();
                return true;
            case R.id.saveSub:
                i.putExtra("name", nameText.getText());
                i.putExtra("date", dateText.getText());
                i.putExtra("amount", Double.parseDouble(priceText.getText().toString().replace("$", "")));
                i.putExtra("comment", commentText.getText());
                i.putExtra("index", index);
                setResult(Activity.RESULT_OK,i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

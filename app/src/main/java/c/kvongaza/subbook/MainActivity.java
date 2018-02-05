package c.kvongaza.subbook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Subscription> subscriptionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subscriptionList = new ArrayList<>();
        subscriptionList.add(new Subscription("Netflix", new Date(), 8.99, "test item in list"));

        ArrayAdapter<Subscription> adapter = new MyListAdapter();

        ListView list = findViewById(R.id.mainListView);
        list.setAdapter(adapter);
        registerClickCallback();
    }


    private void registerClickCallback() {
        ListView list = findViewById(R.id.mainListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Subscription currentSub = subscriptionList.get(position);
                Intent i = new Intent(getApplicationContext(), SubscriptionActivity.class);
                i.putExtra("name", currentSub.getName() );
                i.putExtra("date", new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA).format(currentSub.getDateStart()));
                i.putExtra("amount", currentSub.getMonthlyCharge() );
                i.putExtra("comment", currentSub.getComment() );
                startActivityForResult(i, 101);

            }
        });
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
                i.putExtra("date", new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA).format(new Date()));
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
        super.onActivityResult(requestCode, resultCode, data); // the default shit

        // new sub
        if(requestCode == 100 && data != null){

//            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aa"); // Alternate date formatter method.
//            ParsePosition pp = new ParsePosition(0);
//            formatter.parse("05/11/1994 05:30:10 AM", pp);
//            Date date = formatter.parse("2018-02-05", new ParsePosition(0));
            subscriptionList.add(new Subscription(data.getStringExtra("name"),
//                    new Date(), // test date
                    new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA).parse(data.getStringExtra("date"), new ParsePosition(0)), // Something wrong with format?
                    data.getDoubleExtra("amount", 0),
                    data.getStringExtra("comment")));
            subscriptionList.add(new Subscription("string", new Date(), 0.0, "what")); // demo
        }
        // editing sub
        else if(requestCode == 101) {
            String name = data.getStringExtra("name");
            if (name == null) {
                subscriptionList.remove(data.getExtras().getInt("index"));
            } else {
                Subscription s = subscriptionList.get(data.getExtras().getInt("index"));
                s.setName(data.getStringExtra("name"));
                s.setDate(new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA).parse(data.getExtras().getString("date"), new ParsePosition(0)));
                s.setMonthlyCharge(data.getExtras().getDouble("amount", 0));
                s.setComment(data.getExtras().getString("comment"));
            }
        }

    }

    // taken from https://www.youtube.com/watch?v=WRANgDgM2Zg&feature=youtu.be&t=24m24s
    private class MyListAdapter extends ArrayAdapter<Subscription> {
        public MyListAdapter() {
            super(MainActivity.this, R.layout.subitem, subscriptionList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.subitem, parent, false);
            }

            Subscription currentSub = subscriptionList.get(position);

            TextView nameText = itemView.findViewById(R.id.itemName);
            nameText.setText(currentSub.getName());

            TextView dateText = itemView.findViewById(R.id.itemDate);
            dateText.setText(new SimpleDateFormat ("yyyy-MM-dd", Locale.CANADA).format(currentSub.getDateStart()));

            TextView priceText = itemView.findViewById(R.id.itemPrice);
            priceText.setText(String.valueOf(currentSub.getMonthlyCharge())); // removed "$" + ...

            return itemView;
        }

    }



}

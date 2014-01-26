package net.wtfitio.listofcars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import net.wtfitio.listofcars.car_class.Car;

/**
 * Created by plamend on 1/23/14.
 */
public class ViewAndShareActivity extends ActionBarActivity {
    public static String TOSHARE = "to_share";
    TextView name,make,model,y_prod,color;
    private StringBuilder sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_and_share_layout);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Car car_to_share;
        this.name = (TextView)findViewById(R.id.car_name_share);
        this.make = (TextView)findViewById(R.id.car_make_share);
        this.model = (TextView)findViewById(R.id.car_model_share);
        this.y_prod = (TextView)findViewById(R.id.car_prod_year_share);
        this.color = (TextView)findViewById(R.id.car_color_share);
        Intent intent = getIntent();
        car_to_share = (Car) intent.getSerializableExtra(TOSHARE);
        String name_temp = car_to_share.getName();
        String make_temp=car_to_share.getMake();
        String model_temp=car_to_share.getModel();
        String year_temp=car_to_share.getProdYear();
        String color_temp=car_to_share.getColor();



        name.setText(name_temp);
        make.setText(make_temp);
        model.setText(model_temp);
        y_prod.setText(year_temp);
        color.setText(color_temp);
        this.sb = new StringBuilder();
        sb.append(getString(R.string.car_name));
        sb.append(name_temp);
        sb.append("\n");
        sb.append(getString(R.string.car_make));
        sb.append(make_temp);
        sb.append("\n");
        sb.append(getString(R.string.car_model));
        sb.append(model_temp);
        sb.append("\n");
        sb.append(getString(R.string.car_pro_year));
        sb.append(year_temp);
        sb.append("\n");
        sb.append(getString(R.string.car_color));
        sb.append(color_temp);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu_share,menu);

        initActionProvider(menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void initActionProvider(Menu menu) {
        MenuItem item_share =menu.findItem(R.id.action_share);
        android.support.v7.widget.ShareActionProvider shareProvider = (android.support.v7.widget.ShareActionProvider) MenuItemCompat.getActionProvider(item_share);
        Intent shareIntent = defaultIntent(sb);
        shareProvider.setShareIntent(shareIntent);

    }

    private Intent defaultIntent(StringBuilder sb) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,sb.toString());
        return intent;
    }
}

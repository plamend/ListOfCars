package net.wtfitio.listofcars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.wtfitio.listofcars.car_class.Car;

/**
 * Created by plamend on 1/17/14.
 */
public class EditActivity extends ActionBarActivity {
    public static String POSITION="pos";
    public static String OBJECT = "car";
    EditText name_imput;
    EditText model_imput;
    EditText make_imput;
    EditText prod_year;
    EditText col;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layput);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent_in = getIntent();
        Car car_in;
        car_in = (Car) intent_in.getSerializableExtra(OBJECT);
        this.position = intent_in.getIntExtra(POSITION,0);

        this.name_imput = (EditText)findViewById(R.id.car_name_edit);
        this.make_imput = (EditText)findViewById(R.id.car_make_edit);
        this.model_imput = (EditText)findViewById(R.id.car_model_edit);
        this.prod_year = (EditText)findViewById(R.id.car_prod_year_edit);
        this.col = (EditText)findViewById(R.id.car_color_edit);
        Button edit = (Button)findViewById(R.id.edit_butoon);
        name_imput.setText(car_in.getName());
        make_imput.setText(car_in.getMake());
        model_imput.setText(car_in.getModel());
        prod_year.setText(car_in.getProdYear());
        col.setText(car_in.getColor());


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=name_imput.getText().toString();
                String make=make_imput.getText().toString();
                String model=model_imput.getText().toString();
                String p_year=prod_year.getText().toString();
                String color=col.getText().toString();

                if (!name.equals("")&&!make.equals("")&&!model.equals("")&&!p_year.equals("")&&!color.equals("")){

                    Intent intent= CreateIntent();
                    intent.putExtra(MainActivity.ACT,"EDIT");
                    intent.putExtra(POSITION,position);
                    Car car_out = new Car();
                    car_out.setName(name);
                    car_out.setMake(make);
                    car_out.setModel(model);
                    car_out.setProdYear(p_year);
                    car_out.setColor(color);
                    intent.putExtra(OBJECT,car_out);
                    setResult(RESULT_OK,intent);
                    finish();


                }
                else {
                    ShowMessage("All info is mandatory");

                }


            }
        });
    }

    private void ShowMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private Intent CreateIntent() {
        Intent intent= new Intent(this,MainActivity.class);
        return intent;
    }


}

package net.wtfitio.listofcars;

import android.app.ActionBar;
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
public class AddActivity extends ActionBarActivity {
    public static String OBJECT="car";
    EditText name_imput;
    EditText model_imput;
    EditText make_imput;
    EditText prod_year;
    EditText col;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);

        this.name_imput = (EditText)findViewById(R.id.car_name_input);
        this.make_imput = (EditText)findViewById(R.id.car_make_input);
        this.model_imput = (EditText)findViewById(R.id.car_model_input);
        this.prod_year = (EditText)findViewById(R.id.car_prod_year_input);
        this.col = (EditText)findViewById(R.id.car_color_input);
        Button add = (Button)findViewById(R.id.add_butoon);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=name_imput.getText().toString();
                String make=make_imput.getText().toString();
                String model=model_imput.getText().toString();
                String p_year=prod_year.getText().toString();
                String color=col.getText().toString();

                if (!name.equals("")&&!make.equals("")&&!model.equals("")&&!p_year.equals("")&&!color.equals("")){

                    Intent intent= CreateIntent();
                    intent.putExtra(MainActivity.ACT,"ADD");
                    Car new_car = new Car();
                    new_car.setName(name);
                    new_car.setMake(make);
                    new_car.setModel(model);
                    new_car.setProdYear(p_year);
                    new_car.setColor(color);
                    intent.putExtra(OBJECT,new_car);
                    setResult(RESULT_OK,intent);
                    finish();


                }
                else {
                    ShowMessage("All info is mandatory");

                }
            }
        });
    }

    private Intent CreateIntent() {
        Intent intent= new Intent(this,MainActivity.class);
        return intent;
    }

    private void ShowMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}

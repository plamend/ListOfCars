package net.wtfitio.listofcars;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SearchViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import net.wtfitio.listofcars.adapter.CarsAdapter;
import net.wtfitio.listofcars.car_class.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by plamend on 1/17/14.
 */
public class MainActivity extends ActionBarActivity {
    List<Car> cars;
    ListView list;
    CarsAdapter adapter;
    public static String ACT="ACT";
    public static String CAR_NAME="CAR_NAME";
    public static String CAR_MAKE="CAR_MAKE";
    public static String CAR_MODEL="CAR_MODEL";
    public static String CAR_P_YEAR="CAR_P_YEAR";
    public static String CAR_COLOR="CAR_COLOR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);







            this.list = (ListView)findViewById(R.id.list);
            this.cars =new ArrayList<Car>();
            cars = genCars();
            this.adapter = new CarsAdapter(this,R.layout.cars_list_layout,cars);
            list.setAdapter(adapter);
            registerForContextMenu(list);







    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if(resultCode==RESULT_OK){
                Car car1;
                car1= (Car) data.getSerializableExtra(AddActivity.OBJECT);
                cars.add(car1);
                list.setAdapter(adapter);

            }
        }
            if(requestCode==2){
                if(resultCode==RESULT_OK){
                int pos = data.getIntExtra(EditActivity.POSITION,0);
                Car car1;
                car1 = (Car) data.getSerializableExtra(EditActivity.OBJECT);
                cars.set(pos,car1);
                list.setAdapter(adapter);
                }
            }




        }



    private List<Car> genCars() {
        List<Car> cars = new ArrayList<Car>();
        Car tm1 = new Car();
        tm1.setName("Pepi");
        tm1.setMake("Ford");
        tm1.setModel("Fiesta");
        tm1.setProdYear("2010");
        tm1.setColor("Green");
        cars.add(tm1);

        Car tm2 = new Car();
        tm2.setName("Pupi");
        tm2.setMake("Suzuki");
        tm2.setModel("Liana");
        tm2.setProdYear("2006");
        tm2.setColor("Blue");
        cars.add(tm2);

        return cars;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        MenuItem SeachItem =  menu.findItem(R.id.car_search);
        View SeachView =  MenuItemCompat.getActionView(SeachItem);
        DoSearch(SeachView);

        return true;

    }

    private void DoSearch(View seachView) {
        seachView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.car_add:
                AddNewCar();
                return true;
            case R.id.car_search:
                SearchCar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contex_menu,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.car_edit:
                if (info != null) {
                    EditCar(info.position);
                }
                return true;
            case R.id.car_delete:
                if (info != null) {
                    DeleteCar(info.position);


                }
        }
        return super.onContextItemSelected(item);
    }

    private void DeleteCar(int position) {
    cars.remove(position);
        list.setAdapter(adapter);


    }

    private void EditCar(int position) {
        Intent intent = new Intent(this,EditActivity.class);
        intent.putExtra(EditActivity.POSITION,position);
        intent.putExtra(EditActivity.OBJECT,cars.get(position));
        startActivityForResult(intent, 2);

    }

    private void SearchCar() {

    }

    private void AddNewCar() {
        Intent intent = new Intent(this,AddActivity.class);
        startActivityForResult(intent, 1);
    }
}

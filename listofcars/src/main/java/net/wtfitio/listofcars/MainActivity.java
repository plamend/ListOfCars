package net.wtfitio.listofcars;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import net.wtfitio.listofcars.adapter.CarsAdapter;
import net.wtfitio.listofcars.car_class.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by plamend on 1/17/14.
 */
public class MainActivity extends Activity {
    List<Car> cars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        ListView list = (ListView)findViewById(R.id.list);
        this.cars =new ArrayList<Car>();
        this.cars = genCars();
        CarsAdapter adapter = new CarsAdapter(this,R.layout.cars_list_layout,cars);
        list.setAdapter(adapter);
        registerForContextMenu(list);


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
        return true;

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

    }

    private void EditCar(int position) {

    }

    private void SearchCar() {

    }

    private void AddNewCar() {
    }
}

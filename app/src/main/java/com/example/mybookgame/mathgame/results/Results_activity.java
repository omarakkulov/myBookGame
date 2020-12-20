package com.example.mybookgame.mathgame.results;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mybookgame.R;
import com.example.mybookgame.mathgame.database_settings.DBHelper;
import com.example.mybookgame.mathgame.database_settings.DB_Template;

import java.util.List;

public class Results_activity extends AppCompatActivity {
    // listview, дб, лист и адаптер для удобства
    ListView lv_results;

    DBHelper dbHelper;
    List<DB_Template> viewAll;
    ArrayAdapter<DB_Template> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        lv_results = findViewById(R.id.lv_results);

        dbHelper = new DBHelper(this);
        viewAll = dbHelper.getResult();
        showResults();

        // при нажатии на пункт listview под определенным id, то будет удаляться этот пункт с результатом
        lv_results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DB_Template template = (DB_Template) parent.getItemAtPosition(position);

                dbHelper.delete(template);
                viewAll = dbHelper.getResult();
                showResults();
            }
        });
    }

    //обновляем адаптер
    private void showResults() {
        adapter = new ArrayAdapter<DB_Template>(this, android.R.layout.simple_list_item_1, viewAll);
        lv_results.setAdapter(adapter);
    }
}
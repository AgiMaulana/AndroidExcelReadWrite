package lab.agimaulana.excelreadwrite.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import jxl.write.WriteException;
import lab.agimaulana.excelreadwrite.R;
import lab.agimaulana.excelreadwrite.adapter.PeopleRecyclerAdapter;
import lab.agimaulana.excelreadwrite.excel.ExcelUtils;
import lab.agimaulana.excelreadwrite.model.Directory;
import lab.agimaulana.excelreadwrite.model.People;

/**
 * Created by root on 8/13/16.
 */
public class ExportActivity extends AppCompatActivity {

    private PeopleRecyclerAdapter peopleRecyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
        peopleRecyclerAdapter = new PeopleRecyclerAdapter();
        peopleRecyclerAdapter.add(
                new People("Agi Maulana", "Mahasiswa", "085783834545", "aggysuck@gmail.com", "Bogor"));
        peopleRecyclerAdapter.add(
                new People("Asep Surasep", "Montir", "087349830298", "asepsur@gmail.com", "Dayeuhkolot, Bandung"));
        peopleRecyclerAdapter.add(
                new People("Sanusi", "Pedagang Buah", "0878734546323", "sun.oosy@gmail.com", "Cikarang, Bekasi"));
        peopleRecyclerAdapter.add(
                new People("Zaenal Abidin", "Mahasiswa", "085786475928", "zabidin@gmail.com", "Ciawi, Bogor"));
        peopleRecyclerAdapter.add(
                new People("Paijo", "Manager", "0857462883749", "paijo.mgr@gmail.com", "Surabaya"));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(peopleRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_export, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_export);
            export();
        return true;
    }

    private void export(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        new AsyncTask<Void, Void, String >() {
            @Override
            protected String doInBackground(Void... params) {
                File file = Directory.getExcelFile();
                try {
                    ExcelUtils.write(file, peopleRecyclerAdapter.getPeoples());
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(ExportActivity.this, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    return null;
                } catch (WriteException e) {
                    e.printStackTrace();
                    Toast.makeText(ExportActivity.this, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    return null;
                }
                return file.getAbsolutePath();
            }

            @Override
            protected void onPostExecute(String path) {
                progressDialog.dismiss();
                if(TextUtils.isEmpty(path))
                    Toast.makeText(ExportActivity.this, "Gagal mengekspor", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ExportActivity.this, "Berhasil mengekspor" + path, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}

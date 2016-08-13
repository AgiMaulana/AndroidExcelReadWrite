package lab.agimaulana.excelreadwrite.activity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TableLayout;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import lab.agimaulana.excelreadwrite.R;
import lab.agimaulana.excelreadwrite.adapter.ExcelSheetAdapter;
import lab.agimaulana.excelreadwrite.model.Directory;

/**
 * Created by root on 8/14/16.
 */
public class ReadExcelActivity extends AppCompatActivity {
    public static final String FILE_PATH = "filePath";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_excel);

        String path = getIntent().getStringExtra(FILE_PATH);
        try {
            ExcelSheetAdapter excelSheetAdapter =
                    new ExcelSheetAdapter(getSupportFragmentManager(), path);
            ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
            viewPager.setAdapter(excelSheetAdapter);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ReadExcelActivity.this, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

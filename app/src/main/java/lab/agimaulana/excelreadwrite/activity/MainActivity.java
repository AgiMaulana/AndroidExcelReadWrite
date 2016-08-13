package lab.agimaulana.excelreadwrite.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import lab.agimaulana.excelreadwrite.R;
import lab.agimaulana.excelreadwrite.model.Directory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int CHOOSE_FILE_REQ = 000001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatButton btnExportActivity =
                (AppCompatButton) findViewById(R.id.button_export);
        btnExportActivity.setOnClickListener(this);
        AppCompatButton btnReadFileActivity =
                (AppCompatButton) findViewById(R.id.button_read_file);
        btnReadFileActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_export:
                startActivity(new Intent(this, ExportActivity.class));
                break;
            case R.id.button_read_file:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "Pilih file Excel/XLS"), CHOOSE_FILE_REQ);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CHOOSE_FILE_REQ && resultCode == RESULT_OK){
            Intent intent = new Intent(this, ReadExcelActivity.class);
            String path = Directory.getPath(this, data.getData());
            if(TextUtils.isEmpty(path)){
                Toast.makeText(MainActivity.this, "File tidak ditemukan", Toast.LENGTH_SHORT).show();
                return;
            }

            String filenameArray[] = path.split("\\.");
            String extension = filenameArray[filenameArray.length-1];
            if(extension.equalsIgnoreCase("xls")){
                intent.putExtra(ReadExcelActivity.FILE_PATH, path);
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this, "Pilih file dengan ekstensi .xls", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

package lab.agimaulana.excelreadwrite.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import lab.agimaulana.excelreadwrite.R;
import lab.agimaulana.excelreadwrite.model.Directory;

/**
 * Created by root on 8/14/16.
 */
public class ExcelSheetFragment extends Fragment {
    private static final String FILE_PATH = "filePath";
    private static final String SHEET_POSITION = "sheetPosition";
    private String filePath;
    private int sheetPosition;
    private String sheetTitle;

    private TableLayout tableLayout;

    private ExcelSheetFragment(){};

    public static ExcelSheetFragment newInstance(String path, int sheetPosition){
        ExcelSheetFragment fragment = new ExcelSheetFragment();
        Bundle args = new Bundle();
        args.putString(FILE_PATH, path);
        args.putInt(SHEET_POSITION, sheetPosition);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filePath = getArguments().getString(FILE_PATH);
        sheetPosition = getArguments().getInt(SHEET_POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_excel_sheet, container, false);
        this.tableLayout = (TableLayout) v.findViewById(R.id.table_layout);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        File file = new File(filePath);
        if(!file.exists()) {
            Toast.makeText(getContext(), "File tidak ditemukan", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Workbook workbook = Workbook.getWorkbook(file);
            Sheet sheet = workbook.getSheet(sheetPosition);
            this.sheetTitle = sheet.getName();

            TableRow.LayoutParams rowlayoutParams =
                    new TableRow.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            rowlayoutParams.setMargins(2,2,2,2);

            TableRow.LayoutParams labelLayoutParams =
                    new TableRow.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            labelLayoutParams.setMargins(5,5,5,5);

            for (int i = 0; i < sheet.getRows(); i++){
                TableRow tableRow = new TableRow(getContext());
                tableRow.setLayoutParams(rowlayoutParams);

                Cell[] cells = sheet.getRow(i);
                for (Cell cell : cells){
                    TextView label = new TextView(getContext());
                    label.setLayoutParams(labelLayoutParams);
                    label.setPadding(5,0,5,0);
                    label.setText(cell.getContents());
                    tableRow.addView(label);
                }
                tableLayout.addView(tableRow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

    public String getSheetTitle() {
        return sheetTitle;
    }
}

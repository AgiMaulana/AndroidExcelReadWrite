package lab.agimaulana.excelreadwrite.excel;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import lab.agimaulana.excelreadwrite.model.People;

/**
 * Created by root on 8/13/16.
 */
public class ExcelUtils {
    public static void write(File file, List<People> peoples) throws IOException, WriteException {
        if(!file.exists())
            file.createNewFile();

        WritableWorkbook workbook = Workbook.createWorkbook(file);
        WritableSheet sheet = workbook.createSheet("Sensus Warga", 0);
        try {

            /**
             * new Label(0 , 0, Nama)
             * membuat teks "Nama" di kolom pertama baris ke pertama
             * angka pertama menunjukkan kolom
             * angka kedua menunjukkan baris
             */

            sheet.addCell(new Label(0, 0, "Nama"));
            sheet.addCell(new Label(1, 0, "Pekerjaan"));
            sheet.addCell(new Label(2, 0, "Telepon"));
            sheet.addCell(new Label(3, 0, "Email"));
            sheet.addCell(new Label(4, 0, "Alamat"));

            for (int i = 0; i < peoples.size(); i++){
                People people = peoples.get(i);
                sheet.addCell(new Label(0, i, people.getName()));
                sheet.addCell(new Label(1, i, people.getJob()));
                sheet.addCell(new Label(2, i, people.getPhone()));
                sheet.addCell(new Label(3, i, people.getEmail()));
                sheet.addCell(new Label(4, i, people.getAddress()));
            }
        } catch (WriteException e) {
            e.printStackTrace();
        }

        workbook.write();
        workbook.close();

    }
}

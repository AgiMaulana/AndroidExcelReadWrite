package lab.agimaulana.excelreadwrite.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import lab.agimaulana.excelreadwrite.fragment.ExcelSheetFragment;

/**
 * Created by root on 8/14/16.
 */
public class ExcelSheetAdapter extends FragmentPagerAdapter {
    private List<ExcelSheetFragment> fragments;
    public ExcelSheetAdapter(FragmentManager fm, String path) throws Exception {
        super(fm);
        fragments = new ArrayList<>();
        File file = new File(path);
        if (!file.exists())
            throw new Exception();

        Workbook workbook = Workbook.getWorkbook(file);
        for (int i = 0; i < workbook.getSheets().length; i++){
            ExcelSheetFragment sheet = ExcelSheetFragment.newInstance(file.getAbsolutePath(), i);
            fragments.add(sheet);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getSheetTitle();
    }
}

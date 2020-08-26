import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.self.learn.google.api.GoogleSheetConfiguration;
import com.self.learn.google.api.service.BaseSheetService;
import com.self.learn.google.api.service.SheetService;
import com.self.learn.importer.impl.FileInputImporter;
import com.self.learn.importer.type.Type;
import com.self.learn.transaction.reader.Importer;
import com.self.learn.exporter.impl.GoogleSheetExporter;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class TestIO {


    @Test
    public void testImport() throws FileNotFoundException {
        Importer importer = new FileInputImporter(Type.TEXT, new GoogleSheetExporter());
        importer.importFrom(new FileInputStream(new File("test.txt")));
        Assert.assertNotNull(importer);
    }


    @Test
    public void testGgSheets() throws GeneralSecurityException, IOException {
        GoogleSheetConfiguration configuration = GoogleSheetConfiguration.getInstance("google-sheet.properties");
        GoogleSheetConfiguration.getSheetService();
    }


    @Test
    public void testReading() throws GeneralSecurityException, IOException {
        GoogleSheetConfiguration.getInstance("google-sheet.properties");
        final Sheets sheetService = GoogleSheetConfiguration.getSheetService();
        ValueRange range = sheetService.spreadsheets().values().get("12TqYhXjfbVDt6C8zUjyBbgUbGJmhNJ4Mly1Lgi8gsgk", "Tháng 8!A2:D10").execute();
        for (List row : range.getValues()) {
            System.out.println(row);
        }

    }


    @Test
    public void testReadService() throws IOException {
        SheetService sheetService = new BaseSheetService();
        List<List<Object>> objs = sheetService.read("12TqYhXjfbVDt6C8zUjyBbgUbGJmhNJ4Mly1Lgi8gsgk", "Tháng 8", "A2:D10");
        objs.stream().forEach(System.out::println);
        Assert.assertNotNull(objs);
    }


    @Test
    public void testAppend() throws IOException {
        SheetService sheetService = new BaseSheetService();
        List<List<Object>> values = Arrays.asList(Arrays.asList("Test", "From", "Thực phẩm/Ăn uống","10"));
        sheetService.append(values, "12TqYhXjfbVDt6C8zUjyBbgUbGJmhNJ4Mly1Lgi8gsgk","Tháng 8");
    }

}

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import com.self.learn.google.api.GoogleSheetConfiguration;
import com.self.learn.importer.impl.FileInputImporter;
import com.self.learn.importer.type.Type;
import com.self.learn.transaction.reader.Importer;
import com.self.learn.exporter.impl.GoogleSheetExporter;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class TestIO {


    @Test
    public void testImport() throws FileNotFoundException {
        Importer importer = new FileInputImporter(Type.TEXT, new GoogleSheetExporter());
        importer.importFrom(new FileInputStream(new File("test.txt")));
        Assert.assertNotNull(importer);
    }


    @Test
    public void testGgSheets() throws GeneralSecurityException, IOException {
        GoogleSheetConfiguration configuration = GoogleSheetConfiguration.setUp("google-sheet.properties");
        GoogleSheetConfiguration.getSheetService();
    }





}

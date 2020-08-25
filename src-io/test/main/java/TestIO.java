import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsRequestInitializer;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import com.self.learn.reader.impl.FileInputImporter;
import com.self.learn.reader.type.Type;
import com.self.learn.transaction.reader.Importer;
import com.self.learn.writer.impl.GoogleSheetExporter;
import com.self.learn.writer.impl.JsonExporter;
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
        JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        InputStream in = new FileInputStream(new File("credentials.json"));

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY))
                .setAccessType("offline")
                .build();

        Spreadsheet spreadsheet = new Spreadsheet().setProperties(new SpreadsheetProperties().setTitle("Test spreadsheet"));


        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, null).build();
        service.spreadsheets().create(spreadsheet).execute();
    }


}

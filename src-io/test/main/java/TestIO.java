import com.self.learn.reader.impl.FileInputImporter;
import com.self.learn.reader.type.Type;
import com.self.learn.transaction.reader.Importer;
import com.self.learn.writer.JsonExporter;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestIO {


    @Test
    public void testImport() throws FileNotFoundException {
        Importer importer = new FileInputImporter(Type.TEXT,new JsonExporter());
        importer.importFrom(new FileInputStream(new File("test.txt")));
        Assert.assertNotNull(importer);
    }


}

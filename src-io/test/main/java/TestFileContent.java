import com.self.learn.caching.state.Modification;
import com.self.learn.caching.state.Create;
import org.junit.Assert;
import org.junit.Test;

public class TestFileContent {

    @Test
    public void testCreateRange() {
        Modification mod = new Create(50, "31/08");
        Assert.assertEquals(mod.getRange('B'), "B50:E54");
        Assert.assertArrayEquals(mod.getContent(), new java.lang.String[]{"31/08", "", "", ""});

        Modification mod2 = new Create(50, "- as 50");
        Assert.assertArrayEquals(mod2.getContent(), new java.lang.String[]{"", "- as ", "", "50"});
    }
}

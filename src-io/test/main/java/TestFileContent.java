import com.self.learn.state.ModificationState;
import com.self.learn.state.Create;
import com.self.learn.state.Update;
import org.junit.Assert;
import org.junit.Test;

public class TestFileContent {

    @Test
    public void testCreateRange() {
        ModificationState mod = new Create(50, "31/08");
        Assert.assertEquals(mod.getRange('B'), "B50:E54");
        Assert.assertArrayEquals(mod.getContent(), new java.lang.String[]{"31/08", "", "", ""});

        ModificationState mod2 = new Create(50, "- as 50");
        Assert.assertArrayEquals(mod2.getContent(), new java.lang.String[]{"", "- as ", "", "50"});
    }

    @Test
    public void testModification(){
        ModificationState mod = new Update(10," +a -b +d -2 0 +2");
        Assert.assertEquals(mod.getContent(), new String[]{"","ad","","02"});
    }
}

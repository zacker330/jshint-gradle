import com.zjzhai.gradle.jshint.JshintVersion
import org.junit.Assert
import org.junit.Test

/**
 * Created by zjzhai on 7/5/14.
 */
class JshintVersionTest {

    @Test
    public void test(){

        Assert.assertEquals(JshintVersion.V2_1_9, JshintVersion.get("2.1.9"))

    }


}

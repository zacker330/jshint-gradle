import com.zjzhai.gradle.jshint.Jshint
import com.zjzhai.gradle.jshint.JshintResult
import com.zjzhai.gradle.jshint.JshintResults
import com.zjzhai.gradle.jshint.JshintVersion
import com.zjzhai.gradle.jshint.internal.JshintRhino
import static org.junit.Assert.*;
import org.junit.Test

/**
 * Created by zhai on 6/30/14.
 */
class JshintRhinoTest {

    @Test
    void testVerify(){
        Jshint jshint = new JshintRhino(JshintVersion.V2_4_3);
        String jshintLoad = getClass().getResourceAsStream("/jshint/jshint-2.4.1.js").text

        String option = '''
    {
        "undef": true,
        "unused": true,
    }
        '''

        JshintResults results = jshint.verify(get_javascript(), option);
        assertTrue(results.size() == 4)
    }


    private String get_javascript(){
        getClass().getResourceAsStream("/javascript.js").text
    }

}

import com.zjzhai.gradle.jshint.JshintPlugin
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

/**
 * Created by zhai on 6/29/14.
 */
class JshintPluginTest {

    @Test
    public void testJshint() {
        Project project = ProjectBuilder.builder().build()
        project.apply plugin: 'jshint-gradle'

        JshintPlugin plugin = project.plugins.getPlugin(JshintPlugin.class)

    }


}

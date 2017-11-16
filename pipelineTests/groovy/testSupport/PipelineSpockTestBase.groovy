package testSupport

import com.lesfurets.jenkins.unit.RegressionTest
import spock.lang.Specification

/**
 * A base class for Spock testing using the pipeline helper
 */
class PipelineSpockTestBase extends Specification  implements RegressionTest {

    /**
     * Delegate to the test helper
     */
    @Delegate PipelineTestHelper pipelineTestHelper

    /**
     * Values that can be configured in configureSetup() and passed onto the @Delegate PipelineTestHelper
     */
    String[] scriptRoots
    String scriptExtension
    Map<String, String> imports
    String baseScriptRoot

    /**
     * Configure the @Delegate PipelineTestHelper after creatation and before setUp() is callled
     */
    def configureSetup() {}

    /**
     * Do the common setup
     */
    def setup() {

        // Set callstacks path for RegressionTest
        callStackPath = 'pipelineTests/groovy/tests/callstacks/'

        // Create and config the helper
        pipelineTestHelper = new PipelineTestHelper()
        def helper = pipelineTestHelper.getHelper()
        helper.with {
            this.scriptRoots = it.scriptRoots
            this.scriptExtension = it.scriptExtension
            this.imports = it.imports
            this.baseScriptRoot = it.baseScriptRoot
            return it
        }
        configureSetup()
        helper.with {
            it.scriptRoots = this.scriptRoots
            it.scriptExtension = this.scriptExtension
            it.imports += this.imports
            it.baseScriptRoot = this.baseScriptRoot
            return it
        }
        setUp()
    }
}

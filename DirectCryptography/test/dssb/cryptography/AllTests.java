package dssb.cryptography;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite for all the tests.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
@RunWith(Suite.class)
@SuiteClasses({
    TestSerializableType.class,
    TestAes.class,
    TestPassword.class,
    TestRsa.class,
    TestRsaAes.class,
    TestSha1Rsa.class
})
public class AllTests {
    
}

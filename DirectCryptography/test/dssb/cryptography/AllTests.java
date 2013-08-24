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
    TestAes.class,
    TestBase64.class,
    TestHex.class,
    TestPassword.class,
    TestRsa.class,
    TestRsaAes.class,
    TestSerializableType.class,
    TestSha1Rsa.class,
    TestUBytes.class
})
public class AllTests {
    
}

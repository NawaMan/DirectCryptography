package dssb.cryptography;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    TestAes.class,
    TestPassword.class,
    TestRsa.class,
    TestRsaAes.class,
    TestSha1Rsa.class
})
public class AllTests {
    
}

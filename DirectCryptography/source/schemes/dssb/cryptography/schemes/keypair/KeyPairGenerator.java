package dssb.cryptography.schemes.keypair;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public interface KeyPairGenerator {
    
    public KeyPair generate();
    
    static public class Simple implements KeyPairGenerator {
        
        private static final String DEFAULT_RANDOM_ALGORITHM = "SHA1PRNG";
        private static final String DEFAULT_RANDOM_PROVIDER = "SUN";
        private static final String DEFAULT_SEED = "seed";
        
        private String algorithm;
        private int keysize;
        private SecureRandom random;
        private byte[] seed;
        private String randomAlgorithm = null;
        private String randomProvider = null;
        
        public Simple(
                final String algorithm,
                final int keysize) {
            this(algorithm, keysize, (SecureRandom)null);
        }
        
        public Simple(
                final String algorithm,
                final int keysize,
                final byte[] seed) {
            this(algorithm, keysize, null, seed);
        }
        
        public Simple(
                final String algorithm,
                final int keysize,
                final SecureRandom random) {
            this(algorithm, keysize, random, null);
        }
        
        protected Simple(
                final String algorithm,
                final int keysize,
                final SecureRandom random,
                final byte[] seed) {
            if (algorithm == null) {
                throw new NullPointerException("algorithm");
            }
            this.algorithm = algorithm;
            this.keysize = keysize;
            this.random = random;
            this.seed = (seed != null)
                    ? seed.clone()
                    : DEFAULT_SEED.getBytes().clone();
        }
        
        @Override
        public KeyPair generate() {
            final SecureRandom random = (this.random != null)
                    ? this.random
                    : this.prepareSecureRandom();
            final KeyPair keyPair = generateKeyPair(this.algorithm, this.keysize, random);
            return keyPair;
        }
        
        protected KeyPair generateKeyPair(
                final String algorithm,
                final int keysize,
                final SecureRandom random) {
            try {
                final java.security.KeyPairGenerator keyGen = java.security.KeyPairGenerator.getInstance(algorithm);
                keyGen.initialize(keysize, random);
                final KeyPair keyPair = keyGen.genKeyPair();
                return keyPair;
            } catch (final NoSuchAlgorithmException problem) {
                throw new RuntimeException(problem);
            }
        }
        
        protected void setRandomeAlgorithm(final String randomAlgorithm) {
            this.randomAlgorithm = randomAlgorithm;
        }
        
        protected void setRandomProvider(final String randomProvider) {
            this.randomProvider = randomProvider;
        }
        
        protected SecureRandom prepareSecureRandom() {
            try {
                final byte[] seed = ((this.seed != null) ? this.seed : DEFAULT_SEED.getBytes()).clone();
                final String randomAlgorithm = (this.randomAlgorithm != null) ? this.randomAlgorithm : DEFAULT_RANDOM_ALGORITHM;
                final String randomProvider = (this.randomProvider != null) ? this.randomProvider : DEFAULT_RANDOM_PROVIDER;
                final SecureRandom random = SecureRandom.getInstance(randomAlgorithm, randomProvider);
                random.setSeed(seed);
                return random;
            } catch (final NoSuchProviderException problem) {
                throw new RuntimeException(problem);
            } catch (final NoSuchAlgorithmException problem) {
                throw new RuntimeException(problem);
            }
        }
        
    }
    
}
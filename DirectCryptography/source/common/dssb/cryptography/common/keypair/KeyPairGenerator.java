package dssb.cryptography.common.keypair;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * Classes implements this interface can create a key pair.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 **/
public interface KeyPairGenerator {
    
    /**
     * Generate a key pair.
     * 
     * @return a newly created key pair.
     **/
    public KeyPair generate();
    
    // == Sub classes ==================================================================================================
    
    /** This implementation for {@link KeyPairGenerator} use Java Cryptography to generate a key pair. **/
    public static class JavaCrypto implements KeyPairGenerator {
        
        /** The default random algorithm name. */
        public static final String DEFAULT_RANDOM_ALGORITHM = "SHA1PRNG";
        /** The default random algorithm provider. */
        public static final String DEFAULT_RANDOM_PROVIDER = "SUN";
        /** The default seed. */
        public static final String DEFAULT_SEED = "seed";
        
        /** The algorithm name. */
        private String algorithm;
        /** The key size. */
        private int keysize;
        /** The randomizer. */
        private SecureRandom random;
        /** The seed value. */
        private byte[] seed;
        /** The random algorithm. */
        private String randomAlgorithm = null;
        /** The random algorithm provider. */
        private String randomProvider = null;
        
        /**
         * Constructor.
         * 
         * @param algorithm
         *            the algorithm name.
         * @param keysize
         *            the key size.
         * 
         **/
        public JavaCrypto(
                final String algorithm,
                final int keysize) {
            this(algorithm, keysize, (SecureRandom) null);
        }
        
        /**
         * Constructor.
         * 
         * @param algorithm
         *            the algorithm name.
         * @param keysize
         *            the key size.
         * @param seed
         *            the seed value.
         */
        public JavaCrypto(
                final String algorithm,
                final int keysize,
                final byte[] seed) {
            this(algorithm, keysize, null, seed);
        }
        
        /**
         * Constructor.
         * 
         * @param algorithm
         *            the algorithm name.
         * @param keysize
         *            the key size.
         * @param random
         *            the randomizer.
         */
        public JavaCrypto(
                final String algorithm,
                final int keysize,
                final SecureRandom random) {
            this(algorithm, keysize, random, null);
        }
        
        /**
         * Constructor.
         * 
         * @param algorithm
         *            the algorithm name.
         * @param keysize
         *            the key size.
         * @param random
         *            the randomizer.
         * @param seed
         *            the seed value.
         */
        protected JavaCrypto(
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
        
        /** {@inheritDoc} */
        @Override
        public KeyPair generate() {
            final SecureRandom randomizer = (this.random != null)
                    ? this.random
                    : this.prepareSecureRandom();
            final KeyPair keyPair = generateKeyPair(this.algorithm, this.keysize, randomizer);
            return keyPair;
        }
        
        /**
         * Generate a key pair from parameters.
         * 
         * @param algorithm
         *            the algorithm name.
         * @param keysize
         *            the key size.
         * @param random
         *            the randomizer.
         * @return the generated key pair.
         * 
         **/
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
        
        /**
         * Change the random algorithm name.
         * 
         * @param randomAlgorithm
         *            the random algorithm name.
         **/
        protected void setRandomAlgorithm(
                final String randomAlgorithm) {
            this.randomAlgorithm = randomAlgorithm;
        }
        
        /**
         * Change the random algorithm provider.
         * 
         * @param randomProvider
         *            the random algorithm provider.
         **/
        protected void setRandomProvider(
                final String randomProvider) {
            this.randomProvider = randomProvider;
        }
        
        /**
         * Prepare the secure randomizer.
         * 
         * @return the secure randomizer.
         **/
        protected SecureRandom prepareSecureRandom() {
            try {
                final byte[] seedValue = ((this.seed != null)
                        ? this.seed
                        : DEFAULT_SEED.getBytes()).clone();
                final String randomAlgorithmNAme = (this.randomAlgorithm != null)
                        ? this.randomAlgorithm
                        : DEFAULT_RANDOM_ALGORITHM;
                final String randomAlgorithmProvider = (this.randomProvider != null)
                        ? this.randomProvider
                        : DEFAULT_RANDOM_PROVIDER;
                final SecureRandom randomizer = SecureRandom.getInstance(randomAlgorithmNAme, randomAlgorithmProvider);
                randomizer.setSeed(seedValue);
                return randomizer;
            } catch (final NoSuchProviderException problem) {
                throw new RuntimeException(problem);
            } catch (final NoSuchAlgorithmException problem) {
                throw new RuntimeException(problem);
            }
        }
        
    }
    
}

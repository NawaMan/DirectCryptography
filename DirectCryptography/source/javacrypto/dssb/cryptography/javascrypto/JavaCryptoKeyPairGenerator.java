package dssb.cryptography.javascrypto;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import dssb.cryptography.common.keypair.KeyPairGenerator;

/**
 * This implementation for {@link KeyPairGenerator} use Java Cryptography to generate a key pair.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 **/
public class JavaCryptoKeyPairGenerator implements KeyPairGenerator {
    
    /** The default random algorithm name. */
    public static final String DEFAULT_RANDOM_ALGORITHM = "SHA1PRNG";
    /** The default random algorithm provider. */
    public static final String DEFAULT_RANDOM_PROVIDER = "SUN";
    /** The default seed. */
    public static final String DEFAULT_SEED = "seed";
    
    /** The algorithm name. */
    private String algorithm;
    /** The key size. */
    private int keySize;
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
    public JavaCryptoKeyPairGenerator(
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
    public JavaCryptoKeyPairGenerator(
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
    public JavaCryptoKeyPairGenerator(
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
    public JavaCryptoKeyPairGenerator(
            final String algorithm,
            final int keysize,
            final SecureRandom random,
            final byte[] seed) {
        if (algorithm == null) {
            throw new NullPointerException("algorithm");
        }
        this.algorithm = algorithm;
        this.keySize = keysize;
        this.random = random;
        this.seed = (seed != null)
                ? seed.clone()
                : DEFAULT_SEED.getBytes().clone();
    }
    
    /**
     * Returns the algorithm name.
     * 
     * @return the algorithm name.
     **/
    public String getAlgorithm() {
        return this.algorithm;
    }
    
    /**
     * Change the algorithm name.
     * 
     * @param algorithm
     *            the new algorithm name.
     **/
    protected void setAlgorithm(
            final String algorithm) {
        this.algorithm = algorithm;
    }
    
    /**
     * Returns the key size.
     * 
     * @return the key size.
     **/
    public int getKeySize() {
        return this.keySize;
    }
    
    /**
     * Change the key size.
     * 
     * @param keySize
     *            the key size.
     **/
    protected void setKeySize(
            int keySize) {
        this.keySize = keySize;
    }
    
    /**
     * Returns the secure random.
     * 
     * @return the secure random.
     **/
    protected SecureRandom getSecureRandom() {
        return this.random;
    }
    
    /**
     * Change the secure random.
     * 
     * @param random
     *            the new random.
     **/
    protected void setSecureRandom(
            final SecureRandom random) {
        this.random = random;
    }
    
    /**
     * Returns the name of the seed value.
     * 
     * @return the name of the seed value.
     **/
    public byte[] getSeed() {
        return (this.seed == null)
                ? null
                : this.seed.clone();
    }
    
    /**
     * Change the seed value.
     * 
     * @param seed
     *            the seed value.
     **/
    protected void setSeed(
            final byte[] seed) {
        this.seed = (seed == null)
                ? null
                : seed.clone();
    }
    
    /**
     * Returns the name of the random algorithm.
     * 
     * @return the name of the random algorithm.
     **/
    public String getRandomAlgorithm() {
        return this.randomAlgorithm;
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
     * Returns the name of the random algorithm provider.
     * 
     * @return the name of the random algorithm provider.
     **/
    public String getRandomProvider() {
        return this.randomProvider;
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
    
    /** {@inheritDoc} */
    @Override
    public KeyPair generate() {
        final SecureRandom randomizer = (this.random != null)
                ? this.random
                : this.prepareSecureRandom();
        final KeyPair keyPair = generateKeyPair(this.algorithm, this.keySize, randomizer);
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
            // TODO - Make a proper exception.
            throw new RuntimeException(problem);
        }
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
            // TODO - Make a proper exception.
            throw new RuntimeException(problem);
        } catch (final NoSuchAlgorithmException problem) {
            // TODO - Make a proper exception.
            throw new RuntimeException(problem);
        }
    }
    
    // == Sub class ================================================================================================
    
    /**
     * This implementation of {@link JavaCryptoKeyPairGenerator} have setting methods public.
     **/
    public static class Public extends JavaCryptoKeyPairGenerator {
        
        /**
         * Constructor.
         * 
         * @param algorithm
         *            the algorithm name.
         * @param keysize
         *            the key size.
         * 
         **/
        public Public(
                final String algorithm,
                final int keysize) {
            super(algorithm, keysize);
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
        public Public(
                final String algorithm,
                final int keysize,
                final byte[] seed) {
            super(algorithm, keysize, seed);
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
        public Public(
                final String algorithm,
                final int keysize,
                final SecureRandom random) {
            super(algorithm, keysize, random);
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
        protected Public(
                final String algorithm,
                final int keysize,
                final SecureRandom random,
                final byte[] seed) {
            super(algorithm, keysize, random, seed);
        }
        
        /** {@inheritDoc} **/
        @Override
        public void setAlgorithm(
                final String algorithm) {
            super.setAlgorithm(algorithm);
        }
        
        /** {@inheritDoc} **/
        @Override
        public void setKeySize(
                int keySize) {
            super.setKeySize(keySize);
        }
        
        /** {@inheritDoc} **/
        @Override
        public SecureRandom getSecureRandom() {
            return super.getSecureRandom();
        }
        
        /** {@inheritDoc} **/
        @Override
        public void setSecureRandom(
                final SecureRandom random) {
            this.setSecureRandom(random);
        }
        
        /** {@inheritDoc} **/
        @Override
        public void setSeed(
                final byte[] seed) {
            super.setSeed(seed);
        }
        
        /** {@inheritDoc} **/
        @Override
        public void setRandomAlgorithm(
                final String randomAlgorithm) {
            super.setRandomProvider(randomAlgorithm);
        }
        
        /** {@inheritDoc} **/
        @Override
        public void setRandomProvider(
                final String randomProvider) {
            super.setRandomProvider(randomProvider);
        }
    }
}
package dssb.cryptography.javascrypto;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import dssb.cryptography.common.secretkey.SecretKeyGenerator;

/**
 * This implementation for {@link SecretKeyGenerator} use Java Cryptography to generate a key pair.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 **/
public class JavaCryptoSecretKeyGenerator implements SecretKeyGenerator {
    
    /** The algorithm name. */
    private String algorithm;
    /** The provider name. */
    private String provider;
    /** The key size. */
    private int keysize = -1;
    
    /**
     * Constructor.
     **/
    public JavaCryptoSecretKeyGenerator() {
        
    }
    
    /**
     * Constructor.
     * 
     * @param algorithm
     *            the algorithm name.
     * @param provider
     *            the provider name.
     * @param keysize
     *            the key size.
     **/
    public JavaCryptoSecretKeyGenerator(
            final String algorithm,
            final String provider,
            final int keysize) {
        this.algorithm = algorithm;
        this.provider = provider;
        this.keysize = keysize;
    }
    
    /**
     * Constructor.
     * 
     * @param algorithm
     *            the algorithm name.
     * @param keysize
     *            the key size.
     **/
    public JavaCryptoSecretKeyGenerator(
            final String algorithm,
            final int keysize) {
        this(algorithm, null, keysize);
    }
    
    /**
     * Returns the algorithm name.
     * 
     * @return the algorithm name.
     **/
    public String getAlgorithm() {
        return algorithm;
    }
    
    /**
     * Change the algorithm name,
     * 
     * @param algorithm
     *            the algorithm name.
     **/
    protected void setAlgorithm(
            final String algorithm) {
        this.algorithm = algorithm;
    }
    
    /**
     * Returns the algorithm provider name.
     * 
     * @return the algorithm provider name.
     **/
    public String getProvider() {
        return provider;
    }
    
    /**
     * Change the algorithm provider name,
     * 
     * @param provider
     *            the algorithm provider name.
     **/
    protected void setProvider(
            String provider) {
        this.provider = provider;
    }
    
    /**
     * Returns the key size.
     * 
     * @return the key size.
     **/
    public int getKeysize() {
        return keysize;
    }
    
    /**
     * Change the key size,
     * 
     * @param keysize
     *            the key size.
     **/
    protected void setKeysize(
            int keysize) {
        this.keysize = keysize;
    }
    
    /** {@inheritDoc} */
    @Override
    public SecretKey generate() {
        if (this.algorithm == null) {
            throw new NullPointerException();
        }
        
        try {
            final KeyGenerator keyGenerator = (provider == null)
                    ? KeyGenerator.getInstance(this.algorithm)
                    : KeyGenerator.getInstance(this.algorithm, this.provider);
            keyGenerator.init(keysize);
            final SecretKey secretKey = keyGenerator.generateKey();
            return secretKey;
        } catch (final NoSuchAlgorithmException problem) {
            // TODO - Make a proper exception.
            throw new RuntimeException(problem);
        } catch (final NoSuchProviderException problem) {
            // TODO - Make a proper exception.
            throw new RuntimeException(problem);
        }
    }
    
    // == Sub class ====================================================================================================
    
    /**
     * This implementation of {@link JavaCryptoSecretKeyGenerator} have setting methods public.
     **/
    public static class Public extends JavaCryptoSecretKeyGenerator {
        
        /**
         * Constructor.
         **/
        public Public() {
            super();
        }
        
        /**
         * Constructor.
         * 
         * @param algorithm
         *            the algorithm name.
         * @param provider
         *            the provider name.
         * @param keysize
         *            the key size.
         **/
        public Public(
                final String algorithm,
                final String provider,
                final int keysize) {
            super(algorithm, provider, keysize);
        }
        
        /**
         * Constructor.
         * 
         * @param algorithm
         *            the algorithm name.
         * @param keysize
         *            the key size.
         **/
        public Public(
                final String algorithm,
                final int keysize) {
            this(algorithm, null, keysize);
        }
        
        /** {@inheritDoc} */
        @Override
        public void setAlgorithm(
                final String algorithm) {
            super.setAlgorithm(algorithm);
        }
        
        /** {@inheritDoc} */
        @Override
        public void setProvider(
                final String provider) {
            super.setProvider(provider);
        }
        
        /** {@inheritDoc} */
        @Override
        public void setKeysize(
                final int keysize) {
            super.setKeysize(keysize);
        }
    }
    
}

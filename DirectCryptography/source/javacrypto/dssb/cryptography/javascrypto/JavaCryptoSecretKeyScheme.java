package dssb.cryptography.javascrypto;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import dssb.cryptography.Cryptography.Feature;
import dssb.cryptography.cipher.DecyptionException;
import dssb.cryptography.cipher.EncyptionException;
import dssb.cryptography.common.secretkey.CryptographySecretKeyGenerator;
import dssb.cryptography.common.secretkey.SecretKeyCryptography;
import dssb.cryptography.common.secretkey.SecretKeyCryptographyBuilder;
import dssb.cryptography.common.secretkey.SecretKeyGenerator;
import dssb.cryptography.javascrypto.JavaCryptoSecretKeyScheme.Cryptography.Builder;

/**
 * Scheme for JavaCrypto's key pair cryptography.
 * 
 * @param <_Scheme_>
 *            the scheme.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface JavaCryptoSecretKeyScheme<_Scheme_ extends JavaCryptoSecretKeyScheme<_Scheme_>>
    extends JavaCryptoScheme {
    
    /**
     * Creates and return a new cryptography.
     * 
     * @param <_Cryptography_>
     *            the cryptography.
     * @param builder
     *            the builder.
     * 
     * @return a new cryptography.
     **/
    public <_Cryptography_ extends Cryptography<_Scheme_>> _Cryptography_ newCryptography(
            final Cryptography.Builder<_Scheme_> builder);
    
    /**
     * Creates a new cipher.
     * 
     * @param <_Cipher_>
     *            the cipher.
     * @param cryptography
     *            the cryptography.
     * @return a newly created cipher.
     */
    public <_Cipher_ extends Cipher<_Scheme_>> _Cipher_ newCipher(
            final Cryptography<_Scheme_> cryptography);
    
    /**
     * Perform encryption.
     * 
     * @param secretKey
     *            the secret key.
     * @param clearData
     *            the input data.
     * @return the encrypted data.
     * @throws EncyptionException
     *             when there is a problem encrypting the data.
     * 
     **/
    public byte[] encrypt(
            final SecretKey secretKey,
            final byte[] clearData)
            throws EncyptionException;
    
    /**
     * Perform decryption.
     * 
     * @param secretKey
     *            the secret key.
     * @param encryptedData
     *            the input encrypted data.
     * @return the clear decrypted data.
     * @throws DecyptionException
     *             when there is a problem decrypting the data.
     */
    public byte[] decrypt(
            final SecretKey secretKey,
            final byte[] encryptedData)
            throws DecyptionException;
    
    // == Simple =======================================================================================================
    
    /**
     * Simple implementation.
     * 
     * @param <_Scheme_>
     *            the scheme.
     */
    public abstract static class Simple<_Scheme_ extends JavaCryptoSecretKeyScheme<_Scheme_>> implements
            JavaCryptoSecretKeyScheme<_Scheme_> {
        
        /** {@inheritDoc} */
        @Override
        public <_Cryptography_ extends Cryptography<_Scheme_>> _Cryptography_ newCryptography(
                final Builder<_Scheme_> builder) {
            @SuppressWarnings("unchecked")
            final _Cryptography_ cryptography = (_Cryptography_) new Cryptography<_Scheme_>(builder);
            return cryptography;
        }
        
        /** {@inheritDoc} */
        @Override
        public <_Cipher_ extends Cipher<_Scheme_>> _Cipher_ newCipher(
                final Cryptography<_Scheme_> cryptography) {
            @SuppressWarnings("unchecked")
            final _Cipher_ cipher = (_Cipher_) new Cipher<_Scheme_>(cryptography);
            return cipher;
        }
        
    }
    
    // == Cryptography =================================================================================================
    
    /**
     * Cryptography for JavaCryptography's KeyPair cryptography.
     * 
     * @param <_Scheme_>
     *            the scheme.
     */
    public static class Cryptography<_Scheme_ extends JavaCryptoSecretKeyScheme<_Scheme_>> extends
            SecretKeyCryptography {
        
        /**
         * Constructor.
         * 
         * @param builder
         *            the cryptography builder.
         */
        public Cryptography(
                final Builder<_Scheme_> builder) {
            super(builder.getScheme(), builder.getSecretKey());
        }
        
        /** {@inheritDoc} **/
        @Override
        protected SecretKey getSecretKey() {
            return super.getSecretKey();
        }
        
        /** {@inheritDoc} */
        @SuppressWarnings("unchecked")
        @Override
        public _Scheme_ getScheme() {
            return (_Scheme_) super.getScheme();
        }
        
        /** {@inheritDoc} */
        @Override
        protected Cipher<_Scheme_> newCipher() {
            final _Scheme_ scheme = this.getScheme();
            final Cipher<_Scheme_> cipher = scheme.newCipher(this);
            return cipher;
        }
        
        // == Builder ==================================================================================================
        
        /**
         * Cryptography builder.
         * 
         * @param <_Scheme_>
         *            the scheme.
         */
        public abstract static class Builder<_Scheme_ extends JavaCryptoSecretKeyScheme<_Scheme_>> extends
                SecretKeyCryptographyBuilder {
            
            /** The scheme. */
            private final _Scheme_ scheme;
            
            /**
             * Constructor.
             * 
             * @param scheme
             *            the scheme.
             */
            public Builder(
                    final _Scheme_ scheme) {
                this.scheme = scheme;
            }
            
            /**
             * Returns the scheme.
             * 
             * @return the scheme.
             */
            public _Scheme_ getScheme() {
                return this.scheme;
            }
            
            @Override
            public Cryptography<_Scheme_> newCryptography() {
                final _Scheme_ theScheme = this.getScheme();
                final Cryptography<_Scheme_> cryptography = theScheme.newCryptography(this);
                return cryptography;
            }
            
            /** {@inheritDoc} */
            @Override
            @SuppressWarnings("unchecked")
            public Builder<_Scheme_> setSecretKey(
                    final SecretKey secretKey) {
                return super.setSecretKey(secretKey);
            }
            
            /** {@inheritDoc} */
            @Override
            public void useNewKey() {
                super.useNewKey();
            }
            
            /** {@inheritDoc} */
            @Override
            public void useNewKey(
                    final KeyGenerator generator) {
                super.useNewKey(generator);
            }
            
            /**
             * Creates and return a new secret key generator.
             * 
             * @return a new sSecret key generator.
             **/
            protected abstract SecretKeyGenerator newKeyGenerator();
            
            /** {@inheritDoc} */
            @Override
            protected CryptographySecretKeyGenerator newCryptographySecretKeyGenerator() {
                final SecretKeyGenerator generator = this.newKeyGenerator();
                return new CryptographySecretKeyGenerator(this, generator) {
                };
            }
            
            // == Public ===============================================================================================
            
            /**
             * Builder with all setting method public.
             * 
             * @param <_Scheme_>
             *            the scheme.
             */
            public abstract static class Public<_Scheme_ extends JavaCryptoSecretKeyScheme<_Scheme_>> extends
                    Builder<_Scheme_> {
                
                /**
                 * Constructor.
                 * 
                 * @param scheme
                 *            the scheme.
                 */
                public Public(
                        final _Scheme_ scheme) {
                    super(scheme);
                }
                
                /** {@inheritDoc} */
                @Override
                public Public<_Scheme_> setSecretKey(
                        final SecretKey secretKey) {
                    return (Public<_Scheme_>) super.setSecretKey(secretKey);
                }
                
                /** {@inheritDoc} **/
                @Override
                public void useNewKey() {
                    super.useNewKey();
                }
                
                /** {@inheritDoc} **/
                @Override
                public void useNewKey(
                        final KeyGenerator generator) {
                    super.useNewKey(generator);
                }
            }
        }
    }
    
    // == Cipher =======================================================================================================
    
    /**
     * The cipher.
     * 
     * @param <_Scheme_>
     *            the scheme.
     */
    public static class Cipher<_Scheme_ extends JavaCryptoSecretKeyScheme<_Scheme_>> extends
            dssb.cryptography.cipher.Cipher.Simple implements Feature<dssb.cryptography.cipher.Cipher> {
        
        /**
         * Constructor.
         * 
         * @param cryptography
         *            the cryptography.
         */
        public Cipher(
                final Cryptography<_Scheme_> cryptography) {
            super(cryptography);
        }
        
        /** {@inheritDoc} **/
        @SuppressWarnings("unchecked")
        @Override
        public Cryptography<_Scheme_> getCryptography() {
            return (Cryptography<_Scheme_>) super.getCryptography();
        }
        
        /** {@inheritDoc} */
        @Override
        public byte[] encrypt(
                final byte[] clearData)
                throws EncyptionException {
            final Cryptography<_Scheme_> cryptography = this.getCryptography();
            final _Scheme_ scheme = cryptography.getScheme();
            final SecretKey secretKey = cryptography.getSecretKey();
            final byte[] bytes = scheme.encrypt(secretKey, clearData);
            return bytes;
        }
        
        /** {@inheritDoc} */
        @Override
        public byte[] decrypt(
                final byte[] encryptedData) {
            final Cryptography<_Scheme_> cryptography = this.getCryptography();
            final _Scheme_ scheme = cryptography.getScheme();
            final SecretKey secretKey = cryptography.getSecretKey();
            final byte[] bytes = scheme.decrypt(secretKey, encryptedData);
            return bytes;
        }
        
    }
}

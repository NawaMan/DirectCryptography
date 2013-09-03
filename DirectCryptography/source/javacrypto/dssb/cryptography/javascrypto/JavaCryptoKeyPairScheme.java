package dssb.cryptography.javascrypto;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import dssb.cryptography.Cryptography.Feature;
import dssb.cryptography.cipher.DecyptionException;
import dssb.cryptography.cipher.EncyptionException;
import dssb.cryptography.common.keypair.AbstractCommonKeyPairCryptography;
import dssb.cryptography.common.keypair.AbstractCommonKeyPairCryptographyBuilder;
import dssb.cryptography.common.keypair.CryptographyKeyPairGenerator;
import dssb.cryptography.common.keypair.KeyPairGenerator;
import dssb.cryptography.javascrypto.JavaCryptoKeyPairScheme.Cryptography.Builder;

/**
 * Scheme for JavaCrypto's key pair cryptography.
 * 
 * @param <_Scheme_>
 *            the scheme.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface JavaCryptoKeyPairScheme<_Scheme_ extends JavaCryptoKeyPairScheme<_Scheme_>> extends JavaCryptoScheme {
    
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
     * @param publicKey
     *            the public key.
     * @param clearData
     *            the input data.
     * @return the encrypted data.
     * @throws EncyptionException
     *             when there is a problem encrypting the data.
     * 
     **/
    public byte[] encrypt(
            final PublicKey publicKey,
            final byte[] clearData)
            throws EncyptionException;
    
    /**
     * Perform decryption.
     * 
     * @param privateKey
     *            the private key.
     * @param encryptedData
     *            the input encrypted data.
     * @return the clear decrypted data.
     * @throws DecyptionException
     *             when there is a problem decrypting the data.
     */
    public byte[] decrypt(
            final PrivateKey privateKey,
            final byte[] encryptedData)
            throws DecyptionException;
    
    // == Simple =======================================================================================================
    
    /**
     * Simple implementation.
     * 
     * @param <_Scheme_>
     *            the scheme.
     */
    public abstract static class Simple<_Scheme_ extends JavaCryptoKeyPairScheme<_Scheme_>> implements
            JavaCryptoKeyPairScheme<_Scheme_> {
        
        @Override
        public <_Cryptography_ extends Cryptography<_Scheme_>> _Cryptography_ newCryptography(
                final Builder<_Scheme_> builder) {
            @SuppressWarnings("unchecked")
            final _Cryptography_ cryptography = (_Cryptography_) new Cryptography<_Scheme_>(builder);
            return cryptography;
        }
        
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
    public static class Cryptography<_Scheme_ extends JavaCryptoKeyPairScheme<_Scheme_>> extends
            AbstractCommonKeyPairCryptography {
        
        /**
         * Constructor.
         * 
         * @param builder
         *            the cryptography builder.
         */
        public Cryptography(
                final Builder<_Scheme_> builder) {
            super(builder.getScheme(), builder.getPrivateKey(), builder.getPublicKey());
        }
        
        /** {@inheritDoc} **/
        @Override
        protected PrivateKey getPrivateKey() {
            return super.getPrivateKey();
        }
        
        /** {@inheritDoc} **/
        @Override
        protected PublicKey getPublicKey() {
            return super.getPublicKey();
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
        public abstract static class Builder<_Scheme_ extends JavaCryptoKeyPairScheme<_Scheme_>> extends
                AbstractCommonKeyPairCryptographyBuilder {
            
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
            public Builder<_Scheme_> setKeyPair(
                    final KeyPair keyPair) {
                return super.setKeyPair(keyPair);
            }
            
            /** {@inheritDoc} */
            @Override
            @SuppressWarnings("unchecked")
            public Builder<_Scheme_> setPrivateKey(
                    final PrivateKey privateKey) {
                return super.setPrivateKey(privateKey);
            }
            
            /** {@inheritDoc} */
            @Override
            @SuppressWarnings("unchecked")
            public Builder<_Scheme_> setPublicKey(
                    final PublicKey publicKey) {
                return super.setPublicKey(publicKey);
            }
            
            /** {@inheritDoc} */
            @Override
            public void useNewKeyPair() {
                super.useNewKeyPair();
            }
            
            /** {@inheritDoc} */
            @Override
            public void useNewKeyPair(
                    final KeyPairGenerator generator) {
                super.useNewKeyPair(generator);
            }
            
            /**
             * Creates and return a new key pair generator.
             * 
             * @return a new key pair generator.
             **/
            protected abstract KeyPairGenerator newKeyPairGenerator();
            
            /** {@inheritDoc} */
            @Override
            protected CryptographyKeyPairGenerator newCryptographyKeyPairGenerator() {
                final KeyPairGenerator generator = this.newKeyPairGenerator();
                return new CryptographyKeyPairGenerator(this, generator) {
                };
            }
            
            // == Public ===============================================================================================
            
            /**
             * Builder with all setting method public.
             * 
             * @param <_Scheme_>
             *            the scheme.
             */
            public abstract static class Public<_Scheme_ extends JavaCryptoKeyPairScheme<_Scheme_>> extends
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
                public Public<_Scheme_> setKeyPair(
                        final KeyPair keyPair) {
                    return (Public<_Scheme_>) super.setKeyPair(keyPair);
                }
                
                /** {@inheritDoc} **/
                @Override
                public Public<_Scheme_> setPrivateKey(
                        final PrivateKey privateKey) {
                    return (Public<_Scheme_>) super.setPrivateKey(privateKey);
                }
                
                /** {@inheritDoc} **/
                @Override
                public Public<_Scheme_> setPublicKey(
                        final PublicKey publicKey) {
                    return (Public<_Scheme_>) super.setPublicKey(publicKey);
                }
                
                /** {@inheritDoc} **/
                @Override
                public void useNewKeyPair() {
                    super.useNewKeyPair();
                }
                
                /** {@inheritDoc} **/
                @Override
                public void useNewKeyPair(
                        final KeyPairGenerator generator) {
                    super.useNewKeyPair(generator);
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
    public static class Cipher<_Scheme_ extends JavaCryptoKeyPairScheme<_Scheme_>> extends
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
        public Encryptor newEncryptor() {
            final Cryptography<_Scheme_> cryptography = this.getCryptography();
            final PublicKey publicKey = cryptography.getPublicKey();
            if (publicKey == null) {
                return null;
            } else {
                return super.newEncryptor();
            }
        }
        
        /** {@inheritDoc} */
        @Override
        public Decryptor newDecryptor() {
            final Cryptography<_Scheme_> cryptography = this.getCryptography();
            final PrivateKey privateKey = cryptography.getPrivateKey();
            if (privateKey == null) {
                return null;
            } else {
                return super.newDecryptor();
            }
        }
        
        /** {@inheritDoc} */
        @Override
        public byte[] encrypt(
                final byte[] clearData)
                throws EncyptionException {
            final Cryptography<_Scheme_> cryptography = this.getCryptography();
            final _Scheme_ scheme = cryptography.getScheme();
            final PublicKey publicKey = cryptography.getPublicKey();
            final byte[] bytes = scheme.encrypt(publicKey, clearData);
            return bytes;
        }
        
        /** {@inheritDoc} */
        @Override
        public byte[] decrypt(
                final byte[] encryptedData) {
            final Cryptography<_Scheme_> cryptography = this.getCryptography();
            final _Scheme_ scheme = cryptography.getScheme();
            final PrivateKey privateKey = cryptography.getPrivateKey();
            final byte[] bytes = scheme.decrypt(privateKey, encryptedData);
            return bytes;
        }
        
    }
}

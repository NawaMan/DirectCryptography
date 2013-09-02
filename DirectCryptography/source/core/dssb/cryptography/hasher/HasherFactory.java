package dssb.cryptography.hasher;

import dssb.cryptography.Cryptography;
import dssb.cryptography.Cryptography.Feature;

/**
 * This feature of a {@link Cryptography} can create a {@link Hasher} used to calculate hash value of data.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface HasherFactory extends Feature<HasherFactory> {
    
    /**
     * Returns the hasher.
     * 
     * @return the hasher.
     */
    public Hasher getHasher();
    
    // == Sub classes ==================================================================================================
    
    /** This class provide a simple implementation common to most {@code Hasher}. */
    public abstract static class Simple implements HasherFactory {
        
        /** This {@link Hasher} implementation is used with {@code HasherFactory.Simple}. */
        public static class Hasher extends dssb.cryptography.hasher.Hasher.Simple {
            
            /**
             * Constructs an encryptor for the given {@code HasherFactory}.
             * 
             * @param hasherFactory
             *            factory the hasher factory.
             */
            public Hasher(
                    final HasherFactory.Simple hasherFactory) {
                super(hasherFactory);
            }
            
            /** {@inheritDoc} */
            @Override
            public byte[] hash(
                    final byte[] data) {
                final byte[] hash = ((HasherFactory.Simple) this.getHasherFactory()).hash(data);
                return hash;
            }
            
        }
        
        /** The {@code Cryptography}. */
        private final Cryptography cryptography;
        
        /** The {@code Hasher}. */
        private volatile Hasher hasher = null;
        
        /**
         * Constructor.
         * 
         * @param cryptography
         *            the {@code Cryptography} used by this {@code Cipher}.
         */
        public Simple(
                final Cryptography cryptography) {
            this.cryptography = cryptography;
        }
        
        /** {@inheritDoc} **/
        @Override
        public Cryptography getCryptography() {
            return this.cryptography;
        }
        
        /** {@inheritDoc} **/
        @Override
        public Hasher getHasher() {
            if (this.hasher != null) {
                return this.hasher;
            }
            
            synchronized (this) {
                if (this.hasher != null) {
                    return this.hasher;
                }
                
                this.hasher = this.newHasher();
                return this.hasher;
            }
        }
        
        /**
         * Create a new {@code Hasher}.
         * 
         * @return a new {@code Hasher}.
         */
        public Hasher newHasher() {
            return new Hasher(this);
        }
        
        /**
         * Calculate the hash value.
         * 
         * @param data
         *            the data in bytes.
         * @return the hash value.
         */
        public abstract byte[] hash(
                final byte[] data);
        
    }
    
}

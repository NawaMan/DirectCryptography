package dssb.cryptography.hasher;

import dssb.cryptography.Data;

/**
 * Classes implementing this interface can calculate a hash value from the given data.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface Hasher {
    
    /**
     * The factory that build this hasher.
     * 
     * @return the factory.
     */
    public HasherFactory getHasherFactory();
    
    /**
     * Calculate the hash value.
     * 
     * @param <_Type_>
     *            the data type.
     * @param data
     *            the data.
     * @return the byte value of the hash.
     */
    public <_Type_> byte[] hash(
            Data<_Type_> data);
    
    /**
     * Calculate the hash value.
     * 
     * @param data
     *            the data in bytes.
     * @return the byte value of the hash.
     */
    public byte[] hash(
            byte[] data);
    
    // == Sub classes ==================================================================================================
    
    /** This class provide a simple implementation common to most {@code Hasher}. */
    public abstract static class Simple implements Hasher {
        
        /** The {@code Cryptography}. */
        private final HasherFactory hasherFactory;
        
        /**
         * Constructor.
         * 
         * @param hasherFactory
         *            the {@code HasherFactory} used by this {@code Hasher}.
         */
        public Simple(
                final HasherFactory hasherFactory) {
            this.hasherFactory = hasherFactory;
        }
        
        /** {@inheritDoc} **/
        @Override
        public HasherFactory getHasherFactory() {
            return this.hasherFactory;
        }
        
        /**
         * Calculate the hash value.
         * 
         * @param <_Type_>
         *            the data type.
         * @param data
         *            the data in bytes.
         * @return the byte value of the hash.
         */
        public <_Type_> byte[] hash(
                final Data<_Type_> data) {
            final byte[] bytes = data.toBytes();
            final byte[] hash = this.hash(bytes);
            return hash;
        }
    }
    
}

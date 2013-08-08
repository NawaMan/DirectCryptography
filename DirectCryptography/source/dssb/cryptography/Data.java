package dssb.cryptography;

/**
 * Data wrapper. This wrapper enable the actual data to be convert back and fort to bytes which are often used in
 * cryptography.
 * 
 * @param <_Type_>
 *            the data type.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public interface Data<_Type_> {
    
    /**
     * The type of data.
     * 
     * @return the type.
     */
    public Type<_Type_> getType();
    
    /**
     * Returns the wrapped data (the actual data).
     * 
     * @return the wrapped data (the actual data).
     */
    public _Type_ getData();
    
    /**
     * Returns the byte array equivalent of the data.
     * 
     * @return the bytes.
     */
    public byte[] toBytes();
    
    /**
     * Simple implementation of {@link Data}.
     * 
     * @param <_Type_>
     *            the data type.
     */
    public static class Simple<_Type_> implements Data<_Type_> {
        
        /** The type. */
        private Type<_Type_> type;
        
        /** The data. */
        private _Type_ data;
        
        /**
         * Constructor.
         * 
         * @param dataType
         *            the data type.
         * @param actualData
         *            the data.
         */
        public Simple(
                final Type<_Type_> dataType,
                final _Type_ actualData) {
            this.type = dataType;
            this.data = actualData;
        }
        
        /** {@inheritDoc} **/
        @Override
        public Type<_Type_> getType() {
            return this.type;
        }
        
        /** {@inheritDoc} **/
        @Override
        public _Type_ getData() {
            return this.data;
        }
        
        /** {@inheritDoc} **/
        @Override
        public byte[] toBytes() {
            return this.getType().toBytes(this);
        }
        
    }
    
}

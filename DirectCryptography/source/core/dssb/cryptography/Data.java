package dssb.cryptography;

import dssb.cryptography.datatypes.SerializableType;
import dssb.cryptography.util.ConvertToBytesException;

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
     * @throws ConvertToBytesException
     *             when the conversion failed.
     */
    public byte[] toBytes()
            throws ConvertToBytesException;
    
    //== Sub classes ===================================================================================================
    
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
        
        /**
         * Constructor.
         * 
         * @param dataClass
         *            the data class.
         * @param actualData
         *            the data.
         */
        @SuppressWarnings({ "unchecked", "rawtypes" })
        public Simple(
                final Class<_Type_> dataClass,
                final _Type_ actualData) {
            this(new SerializableType(dataClass), actualData);
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
        public byte[] toBytes()
                throws ConvertToBytesException {
            return this.getType().toBytes(this);
        }
        
    }
    
}
package dssb.cryptography.schemes.hex;

import dssb.cryptography.CryptographyBuilder;

/**
 * Builder for {@link HexCryptography}.
 * 
 * This builder will take an algorithm name.
 * 
 * @author Nawapunth Manusitthipol <nawa@dssbsoft.com>
 */
public class HexCryptographyBuilder implements CryptographyBuilder {
    
    private int bytePerColumn = -1;
    
    public void setBytePerColumn(final int bytePerColumn) {
        this.bytePerColumn = bytePerColumn;
    }
    
    public int getBytePerColumn() {
        return this.bytePerColumn;
    }
    
    /** {@inheritDoc} */
    @Override
    public HexCryptography newCryptography() {
        return new HexCryptography(bytePerColumn);
    }

}

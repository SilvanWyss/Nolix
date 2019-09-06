//package declaration
package ch.nolix.common.commonTypeWrappers;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link WrapperByte} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2019-09
 * @lines 100
 */
public final class WrapperByte {
	
	//attribute
	/**
	 * A {@link WrapperByte} stores its value in an unsigned int
	 * because for any transformation it would be needed to transform the value to an int anyway.
	 */
	private final int mByte;
	
	//constructor
	/**
	 * Creates a new {@link WrapperByte} with the given pByte.
	 * 
	 * @param pByte
	 */
	public WrapperByte(final byte pByte) {
		mByte = pByte & 0xFF;
	}
	
	//constructor
	/**
	 * Creates a new {@link WrapperByte} with the given bits.
	 * 
	 * @param bit1
	 * @param bit2
	 * @param bit3
	 * @param bit4
	 * @param bit5
	 * @param bit6
	 * @param bit7
	 * @param bit8
	 */
	public WrapperByte(
		final int bit1,
		final int bit2,
		final int bit3,
		final int bit4,
		final int bit5,
		final int bit6,
		final int bit7,
		final int bit8
	) {
		mByte = 128 * bit1 + 64 * bit2 + 32 * bit3 + 16 * bit4 + 8 * bit5 + 4 * bit6 + 2 * bit7 + bit8;
	}
	
	//method
	/**
	 * @param index
	 * @return the bit at the given index from the current {@link WrapperByte}.
	 */
	public boolean getBitAt(final int index) {
		return (getBitAtAsInt(index) == 1);
	}
	
	//method
	/**
	 * @param index
	 * @return the bit at the given index from the current {@link WrapperByte} as int.
	 */
	public int getBitAtAsInt(final int index) {
		
		Validator.suppose(index).thatIsNamed(VariableNameCatalogue.INDEX).isBetween(1, 8);
		
		return (mByte >> ( 8 - index)) & 1;
	}
	
	//method
	/**
	 * @return a {@link String} with the bits of the current {@link WrapperByte}.
	 */
	public String toBitString() {
		return String.format(
			"%d%d%d%d%d%d%d%d",
			getBitAtAsInt(1),
			getBitAtAsInt(2),
			getBitAtAsInt(3),
			getBitAtAsInt(4),
			getBitAtAsInt(5),
			getBitAtAsInt(6),
			getBitAtAsInt(7),
			getBitAtAsInt(8)
		);
	}
	
	//method
	/**
	 * @return the current {@link WrapperByte} as int.
	 */
	public int toInt() {
		return mByte;
	}
}

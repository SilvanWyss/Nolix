//package declaration
package ch.nolix.element._3DGUI;

//own imports
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.element.FPNData.Height;
import ch.nolix.element.FPNData.Length;
import ch.nolix.element.FPNData.Width;
import ch.nolix.element.core.PositiveFloatingPointNumber;

//class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 190
 */
public final class Cuboid extends BaseShape<Cuboid> {

	//default values	
	public static final double DEFAULT_X_LENGTH = 1.0;
	public static final double DEFAULT_Y_LENGTH = 1.0;
	public static final double DEFAULT_Z_LENGTH = 1.0;
	
	//constants
	private static final String X_LENGTH_HEADER = "XLength";
	private static final String Y_LENGTH_HEADER = "YLength";
	private static final String Z_LENGTH_HEADER = "ZLength";
	
	//attribute
	private final MutableProperty<PositiveFloatingPointNumber> xLength =
	new MutableProperty<>(
		X_LENGTH_HEADER,
		xl -> setXLength(xl.getValue()),
		s -> PositiveFloatingPointNumber.createFromSpecification(s)
	);
	
	//attribute
	private final MutableProperty<PositiveFloatingPointNumber> yLength =
	new MutableProperty<>(
		Y_LENGTH_HEADER,
		yl -> setYLength(yl.getValue()),
		s -> PositiveFloatingPointNumber.createFromSpecification(s)
	);
	
	//attribute
	private final MutableProperty<PositiveFloatingPointNumber> zLength =
	new MutableProperty<>(
		Z_LENGTH_HEADER,
		zl -> setZLength(zl.getValue()),
		s -> PositiveFloatingPointNumber.createFromSpecification(s)
	);
	
	//constructor
	/**
	 * Creates new cuboid.
	 */
	public Cuboid() {
		resetConfiguration();
	}
	
	//method
	/**
	 * @return the x-length of this cuboid.
	 */
	public double getXLength() {
		return xLength.getValue().getValue();
	}
	
	//method
	/**
	 * @return the x-length of this cuboid as float.
	 */
	public float getXLengthAsFloat() {
		return (float)getXLength();
	}
	
	//method
	/**
	 * @return the y-length of this cuboid.
	 */
	public double getYLength() {
		return yLength.getValue().getValue();
	}
	
	//method
	/**
	 * @return the y-length of thus cuboid as float.
	 */
	public float getYLengthAsFloat() {
		return (float)getYLength();
	}
	
	//method
	/**
	 * @return the z-length of this cuboid.
	 */
	public double getZLength() {
		return zLength.getValue().getValue();
	}
	
	//method
	/**
	 * @return the z-length of this cuboid as float.
	 */
	public float getZLengthAsFloat() {
		return (float)getZLength();
	}
	
	//method
	/**
	 * Resets this cuboid.
	 */
	public void reset() {
		
		//Calls method of the base class.
		super.reset();
		
		setXLength(DEFAULT_X_LENGTH);
		setZLength(DEFAULT_Y_LENGTH);
		setYLength(DEFAULT_Z_LENGTH);
	}
	
	//method
	/**
	 * Sets the x-length of this cuboid.
	 * 
	 * @param xLength
	 * @return this cuboid.
	 * @throws NonPositiveArgumentException
	 * if the given x-length is not positive.
	 */
	public Cuboid setXLength(final double xLength) {
		
		this.xLength.setValue(new Length(xLength));
		
		return this;
	}
	
	//method
	/**
	 * Sets the y-length of this cuboid.
	 * 
	 * @param yLength
	 * @return this cuboid.
	 * @throws NonPositiveArgumentException
	 * if the given y-length is not positive.
	 */
	public Cuboid setYLength(final double yLength) {
		
		this.yLength.setValue(new Width(yLength));
		
		return this;
	}
	
	//method
	/**
	 * Sets the z-length of this cuboid.
	 * 
	 * @param zLength
	 * @return this cuboid.
	 * @throws NonPositiveArgumentException
	 * if the given z-length is not positive.
	 */
	public Cuboid setZLength(final double zLength) {
		
		this.zLength.setValue(new Height(zLength));
		
		return this;
	}
}

//package declaration
package ch.nolix.element.shapes;

//own imports
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element._3D_GUI.BaseShape;
import ch.nolix.element.base.MutableProperty;

//class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 200
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
	private final MutableProperty<Double> xLength =
	new MutableProperty<>(
		X_LENGTH_HEADER,
		xl -> setXLength(xl),
		s -> s.getOneAttributeAsDouble(),
		xl -> Node.withOneAttribute(xl)
	);
	
	//attribute
	private final MutableProperty<Double> yLength =
	new MutableProperty<>(
		Y_LENGTH_HEADER,
		yl -> setYLength(yl),
		s -> s.getOneAttributeAsDouble(),
		yl -> Node.withOneAttribute(yl)
	);
	
	//attribute
	private final MutableProperty<Double> zLength =
	new MutableProperty<>(
		Z_LENGTH_HEADER,
		zl -> setZLength(zl),
		s -> s.getOneAttributeAsDouble(),
		zl -> Node.withOneAttribute(zl)
	);
	
	//constructor
	/**
	 * Creates a new {@link Cuboid}.
	 */
	public Cuboid() {
		reset();
	}
	
	//method
	/**
	 * @return the x-length of the current {@link Cuboid}.
	 */
	public double getXLength() {
		return xLength.getValue();
	}
	
	//method
	/**
	 * @return the x-length of the current {@link Cuboid} as float.
	 */
	public float getXLengthAsFloat() {
		return (float)getXLength();
	}
	
	//method
	/**
	 * @return the y-length of the current {@link Cuboid}.
	 */
	public double getYLength() {
		return yLength.getValue();
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
	 * @return the z-length of the current {@link Cuboid}.
	 */
	public double getZLength() {
		return zLength.getValue();
	}
	
	//method
	/**
	 * @return the z-length of the current {@link Cuboid} as float.
	 */
	public float getZLengthAsFloat() {
		return (float)getZLength();
	}
	
	//method
	/**
	 * Resets the current {@link Cuboid}.
	 * 
	 * @return the current {@link Cuboid}.
	 */
	@Override
	public Cuboid reset() {
		
		//Calls method of the base class.
		super.reset();
		
		setXLength(DEFAULT_X_LENGTH);
		setYLength(DEFAULT_Y_LENGTH);
		setZLength(DEFAULT_Z_LENGTH);
		
		return this;
	}
	
	//method
	/**
	 * Sets the size of the current {@link Cuboid}.
	 * 
	 * @param xLength
	 * @param yLength
	 * @param zLength
	 * @return the current {@link Cuboid}.
	 * @throws NonPositiveArgumentException if the given x-length is not positive.
	 * @throws NonPositiveArgumentException if the given y-length is not positive.
	 * @throws NonPositiveArgumentException if the given z-length is not positive.
	 */
	public Cuboid setSize(
		final double xLength,
		final double yLength,
		final double zLength
	) {
		
		setXLength(xLength);
		setYLength(yLength);
		setZLength(zLength);
		
		return this;
	}
	
	//method
	/**
	 * Sets the x-length of the current {@link Cuboid}.
	 * 
	 * @param xLength
	 * @return the current {@link Cuboid}.
	 * @throws NonPositiveArgumentException if the given x-length is negative.
	 */
	public Cuboid setXLength(final double xLength) {
		
		Validator.suppose(xLength).thatIsNamed("x length").isNotNegative();
		
		this.xLength.setValue(xLength);
		
		return this;
	}
	
	//method
	/**
	 * Sets the y-length of the current {@link Cuboid}.
	 * 
	 * @param yLength
	 * @return the current {@link Cuboid}.
	 * @throws NonPositiveArgumentException if the given y-length is negative.
	 */
	public Cuboid setYLength(final double yLength) {
		
		Validator.suppose(yLength).thatIsNamed("y length").isNotNegative();
		
		this.yLength.setValue(yLength);
		
		return this;
	}
	
	//method
	/**
	 * Sets the z-length of the current {@link Cuboid}.
	 * 
	 * @param zLength
	 * @return the current {@link Cuboid}.
	 * @throws NonPositiveArgumentException if the given z-length is negative.
	 */
	public Cuboid setZLength(final double zLength) {
		
		Validator.suppose(zLength).thatIsNamed("z length").isNotNegative();
		
		this.zLength.setValue((zLength));
		
		return this;
	}
}

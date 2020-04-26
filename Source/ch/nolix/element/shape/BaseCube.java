//package declaration
package ch.nolix.element.shape;

//own imports
import ch.nolix.common.node.BaseNode;
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
public final class BaseCube extends BaseShape<BaseCube> {
	
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
		BaseNode::getOneAttributeAsDouble,
		xl -> Node.withOneAttribute(xl)
	);
	
	//attribute
	private final MutableProperty<Double> yLength =
	new MutableProperty<>(
		Y_LENGTH_HEADER,
		yl -> setYLength(yl),
		BaseNode::getOneAttributeAsDouble,
		yl -> Node.withOneAttribute(yl)
	);
	
	//attribute
	private final MutableProperty<Double> zLength =
	new MutableProperty<>(
		Z_LENGTH_HEADER,
		zl -> setZLength(zl),
		BaseNode::getOneAttributeAsDouble,
		zl -> Node.withOneAttribute(zl)
	);
	
	//constructor
	/**
	 * Creates a new {@link BaseCube}.
	 */
	public BaseCube() {
		reset();
	}
	
	//method
	/**
	 * @return the x-length of the current {@link BaseCube}.
	 */
	public double getXLength() {
		return xLength.getValue();
	}
	
	//method
	/**
	 * @return the x-length of the current {@link BaseCube} as float.
	 */
	public float getXLengthAsFloat() {
		return (float)getXLength();
	}
	
	//method
	/**
	 * @return the y-length of the current {@link BaseCube}.
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
	 * @return the z-length of the current {@link BaseCube}.
	 */
	public double getZLength() {
		return zLength.getValue();
	}
	
	//method
	/**
	 * @return the z-length of the current {@link BaseCube} as float.
	 */
	public float getZLengthAsFloat() {
		return (float)getZLength();
	}
	
	//method
	/**
	 * Resets the current {@link BaseCube}.
	 * 
	 * @return the current {@link BaseCube}.
	 */
	@Override
	public BaseCube reset() {
		
		//Calls method of the base class.
		super.reset();
		
		setXLength(DEFAULT_X_LENGTH);
		setYLength(DEFAULT_Y_LENGTH);
		setZLength(DEFAULT_Z_LENGTH);
		
		return this;
	}
	
	//method
	/**
	 * Sets the size of the current {@link BaseCube}.
	 * 
	 * @param xLength
	 * @param yLength
	 * @param zLength
	 * @return the current {@link BaseCube}.
	 * @throws NonPositiveArgumentException if the given x-length is not positive.
	 * @throws NonPositiveArgumentException if the given y-length is not positive.
	 * @throws NonPositiveArgumentException if the given z-length is not positive.
	 */
	public BaseCube setSize(
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
	 * Sets the x-length of the current {@link BaseCube}.
	 * 
	 * @param xLength
	 * @return the current {@link BaseCube}.
	 * @throws NonPositiveArgumentException if the given x-length is not positive.
	 */
	public BaseCube setXLength(final double xLength) {
		
		Validator.assertThat(xLength).thatIsNamed("x length").isPositive();
		
		this.xLength.setValue(xLength);
		
		return this;
	}
	
	//method
	/**
	 * Sets the y-length of the current {@link BaseCube}.
	 * 
	 * @param yLength
	 * @return the current {@link BaseCube}.
	 * @throws NonPositiveArgumentException if the given y-length is not positive.
	 */
	public BaseCube setYLength(final double yLength) {
		
		Validator.assertThat(yLength).thatIsNamed("y length").isPositive();
		
		this.yLength.setValue(yLength);
		
		return this;
	}
	
	//method
	/**
	 * Sets the z-length of the current {@link BaseCube}.
	 * 
	 * @param zLength
	 * @return the current {@link BaseCube}.
	 * @throws NonPositiveArgumentException if the given z-length is not positive.
	 */
	public BaseCube setZLength(final double zLength) {
		
		Validator.assertThat(zLength).thatIsNamed("z length").isPositive();
		
		this.zLength.setValue((zLength));
		
		return this;
	}
}

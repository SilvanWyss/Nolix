//package declaration
package ch.nolix.element.planarShapes;

//own imports
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element._3D_GUI.BaseShape;
import ch.nolix.element.base.MutableProperty;

//class
/**
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 140
 */
public final class Rectangle extends BaseShape<Rectangle> {
	
	//default values	
	public static final double DEFAULT_X_LENGTH = 1.0;
	public static final double DEFAULT_Y_LENGTH = 1.0;
	
	//constants
	private static final String X_LENGTH_HEADER = "XLength";
	private static final String Y_LENGTH_HEADER = "YLength";

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
	
	//method
	/**
	 * @return the x-length of the current {@link Rectangle}.
	 */
	public double getXLength() {
		return xLength.getValue();
	}
	
	//method
	/**
	 * @return the x-length of the current {@link Rectangle} as float.
	 */
	public float getXLengthAsFloat() {
		return (float)getXLength();
	}
	
	//method
	/**
	 * @return the y-length of the current {@link Rectangle}.
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
	 * Resets the current {@link Rectangle}.
	 * 
	 * @return the current {@link Rectangle}.
	 */
	@Override
	public Rectangle reset() {
		
		//Calls method of the base class.
		super.reset();
		
		setXLength(DEFAULT_X_LENGTH);
		setYLength(DEFAULT_Y_LENGTH);
		
		return this;
	}
	
	//method
	/**
	 * Sets the size of the current {@link Rectangle}.
	 * 
	 * @param xLength
	 * @param yLength
	 * @return the current {@link Rectangle}.
	 * @throws NonPositiveArgumentException if the given x-length is not positive.
	 * @throws NonPositiveArgumentException if the given y-length is not positive.
	 */
	public Rectangle setSize(
		final double xLength,
		final double yLength
	) {
		
		setXLength(xLength);
		setYLength(yLength);
		
		return this;
	}
	
	//method
	/**
	 * Sets the x-length of the current {@link Rectangle}.
	 * 
	 * @param xLength
	 * @return the current {@link Rectangle}.
	 * @throws NegativeArgumentException if the given x-length is negative.
	 */
	public Rectangle setXLength(final double xLength) {
		
		Validator.assertThat(xLength).thatIsNamed("x length").isNotNegative();
		
		this.xLength.setValue(xLength);
		
		return this;
	}
	
	//method
	/**
	 * Sets the y-length of the current {@link Rectangle}.
	 * 
	 * @param yLength
	 * @return the current {@link Rectangle}.
	 * @throws NegativeArgumentException if the given y-length is negative.
	 */
	public Rectangle setYLength(final double yLength) {
		
		Validator.assertThat(yLength).thatIsNamed("y length").isNotNegative();
		
		this.yLength.setValue(yLength);
		
		return this;
	}
}

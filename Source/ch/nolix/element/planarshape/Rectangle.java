//package declaration
package ch.nolix.element.planarshape;

//own imports
import ch.nolix.common.invalidargumentexception.NegativeArgumentException;
import ch.nolix.common.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.gui3d.AtomicShape;

//class
/**
 * @author Silvan Wyss
 * @date 2018-09-23
 * @lines 140
 */
public final class Rectangle extends AtomicShape<Rectangle> {
	
	//constants	
	public static final double DEFAULT_X_LENGTH = 1.0;
	public static final double DEFAULT_Y_LENGTH = 1.0;
	
	//constants
	private static final String X_LENGTH_HEADER = "XLength";
	private static final String Y_LENGTH_HEADER = "YLength";

	//attribute
	private final MutableValue<Double> xLength =
	new MutableValue<>(
		X_LENGTH_HEADER,
		DEFAULT_X_LENGTH,
		this::setXLength,
		BaseNode::getOneAttributeAsDouble,
		Node::withAttribute
	);
	
	//attribute
	private final MutableValue<Double> yLength =
	new MutableValue<>(
		Y_LENGTH_HEADER,
		DEFAULT_Y_LENGTH,
		this::setYLength,
		BaseNode::getOneAttributeAsDouble,
		Node::withAttribute
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
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetShape() {
		setXLength(DEFAULT_X_LENGTH);
		setYLength(DEFAULT_Y_LENGTH);
	}
}

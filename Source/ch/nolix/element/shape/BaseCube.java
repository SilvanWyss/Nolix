//package declaration
package ch.nolix.element.shape;

import ch.nolix.common.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.gui3d.AtomicShape;

//class
/**
 * @author Silvan Wyss
 * @date 2017-11-11
 * @lines 190
 */
public final class BaseCube extends AtomicShape<BaseCube> {
	
	//constants	
	public static final double DEFAULT_X_LENGTH = 1.0;
	public static final double DEFAULT_Y_LENGTH = 1.0;
	public static final double DEFAULT_Z_LENGTH = 1.0;
	
	//constants
	private static final String X_LENGTH_HEADER = "XLength";
	private static final String Y_LENGTH_HEADER = "YLength";
	private static final String Z_LENGTH_HEADER = "ZLength";
	
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
	
	//attribute
	private final MutableValue<Double> zLength =
	new MutableValue<>(
		Z_LENGTH_HEADER,
		DEFAULT_Z_LENGTH,
		this::setZLength,
		BaseNode::getOneAttributeAsDouble,
		Node::withAttribute
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
	public BaseCube setSize(final double xLength, final double yLength,	final double zLength) {
		
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
		
		Validator.assertThat(xLength).thatIsNamed("x-length").isPositive();
		
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
		
		Validator.assertThat(yLength).thatIsNamed("y-length").isPositive();
		
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
		
		Validator.assertThat(zLength).thatIsNamed("z-length").isPositive();
		
		this.zLength.setValue((zLength));
		
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
		setZLength(DEFAULT_Z_LENGTH);
	}
}

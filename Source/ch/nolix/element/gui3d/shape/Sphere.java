//package declaration
package ch.nolix.element.gui3d.shape;

import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.gui3d.base.AtomicShape;

//class
/**
 * @author Silvan Wyss
 * @date 2017-11-11
 */
public final class Sphere extends AtomicShape<Sphere> {
	
	//constants
	public static final double DEFAULT_RADIUS = 0.5;
	
	//attribute	
	private final MutableValue<Double> radius =
	new MutableValue<>(
		PascalCaseCatalogue.RADIUS,
		DEFAULT_RADIUS,
		this::setRadius,
		BaseNode::getOneAttributeAsDouble,
		 Node::withAttribute
	);
	
	//constructor
	/**
	 * Creates a new {@link Sphere} with a default radius.
	 */
	public Sphere() {
		reset();
	}
	
	//method
	/**
	 * @return the diameter of the current {@link Sphere}.
	 */
	public double getDiameter() {
		return 2 * getRadius();
	}
	
	//method
	/**
	 * @return the diameter of the current {@link Sphere} as float.
	 */
	public float getDiameterAsFloat() {
		return (float)getDiameter();
	}
	
	//method
	/**
	 * @return the radius of the current {@link Sphere}.
	 */
	public double getRadius() {
		return radius.getValue();
	}
	
	//method
	/**
	 * @return the radius of the current {@link Sphere} as float.
	 */
	public float getRadiusAsFloat() {
		return (float)getRadius();
	}
	
	//method
	/**
	 * Sets the diameter of the current {@link Sphere}.
	 * 
	 * @param diameter
	 * @return the current {@link Sphere}.
	 * @throws NonPositiveArgumentException if the given diameter is not positive.
	 */
	public Sphere setDiameter(final double diameter) {
		
		//Asserts that the given diameter is positive.
		Validator.assertThat(diameter).thatIsNamed("diameter").isPositive();
		
		return setRadius(0.5 * diameter);
	}
	
	//method
	/**
	 * Sets the radius of the current {@link Sphere}.
	 * 
	 * @param radius
	 * @return the current {@link Sphere}.
	 * @throws NonPositiveArgumentException if the given radius is not positive.
	 */
	public Sphere setRadius(final double radius) {
		
		Validator.assertThat(radius).thatIsNamed(LowerCaseCatalogue.RADIUS).isPositive();
		
		this.radius.setValue(radius);
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetShape() {
		setRadius(DEFAULT_RADIUS);
	}
}

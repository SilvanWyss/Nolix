//package declaration
package ch.nolix.element.shape;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element._3D_GUI.AtomicShape;
import ch.nolix.element.base.MutableValue;

//class
/**
 * @author Silvan Wyss
 * @date 2017-11-11
 * @lines 120
 */
public final class Sphere extends AtomicShape<Sphere> {
	
	//constants
	public static final double DEFAULT_RADIUS = 0.5;
	
	//attribute	
	private final MutableValue<Double> radius =
	new MutableValue<>(
		PascalCaseNameCatalogue.RADIUS,
		this::setRadius,
		BaseNode::getOneAttributeAsDouble,
		 Node::withOneAttribute
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
	 * Resets the current {@link Sphere}.
	 * 
	 * @return the current {@link Sphere}.
	 */
	@Override
	public Sphere reset() {
	
		setRadius(DEFAULT_RADIUS);
		
		//Calls method of the base class.
		return super.reset();
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
		
		Validator.assertThat(radius).thatIsNamed(VariableNameCatalogue.RADIUS).isPositive();
		
		this.radius.setValue(radius);
		
		return this;
	}
}

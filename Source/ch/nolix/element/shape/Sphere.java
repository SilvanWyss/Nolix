//package declaration
package ch.nolix.element.shape;

import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element._3D_GUI.BaseShape;
import ch.nolix.element.base.MutableProperty;

//class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 130
 */
public final class Sphere extends BaseShape<Sphere> {
	
	//default values
	public static final double DEFAULT_RADIUS = 1.0;
	public static final double DEFAULT_DIAMETER = 2.0 * DEFAULT_RADIUS;
	
	//attribute	
	private final MutableProperty<Double> radius =
	new MutableProperty<>(
		PascalCaseNameCatalogue.RADIUS,
		r -> setRadius(r),
		s -> s.getOneAttributeAsDouble(),
		r -> Node.withOneAttribute(r)
	);
	
	//constructor
	/**
	 * Creates a new sphere with a default radius.
	 */
	public Sphere() {
		reset();
	}
	
	//constructor
	/**
	 * Creates a new sphere with the given diameter.
	 * 
	 * @param diameter
	 * @throws NonPositiveArgumentException if the given diameter is not positive.
	 */
	public Sphere(final double diameter) {
		
		//Calls other constructor.
		this();
		
		setDiameter(diameter);
	}
	
	//method
	/**
	 * @return the diameter of this sphere.
	 */
	public double getDiameter() {
		return 2 * getRadius();
	}
	
	//method
	/**
	 * @return the diameter of this sphere as float.
	 */
	public float getDiameterAsFloat() {
		return 2 * getRadiusAsFloat();
	}
	
	//method
	/**
	 * @return the radius of this sphere.
	 */
	public double getRadius() {
		return radius.getValue();
	}
	
	//method
	/**
	 * @return the radius of this sphere as float.
	 */
	public float getRadiusAsFloat() {
		return (float)getRadius();
	}
	
	//method
	/**
	 * Resets this sphere.
	 * 
	 * @return this sphere.
	 */
	@Override
	public Sphere reset() {
	
		setRadius(DEFAULT_RADIUS);
		
		//Calls method of the base class.
		return super.reset();
	}
	
	//method
	/**
	 * Sets the diameter of this sphere.
	 * 
	 * @param diameter
	 * @return this sphere.
	 * @throws NonPositiveArgumentException if the given diameter is not positive.
	 */
	public Sphere setDiameter(final double diameter) {
		
		//Asserts that the given diameter is positive.
		Validator.assertThat(diameter).thatIsNamed("diameter").isPositive();
		
		return setRadius(0.5 * diameter);
	}
	
	//method
	/**
	 * Sets the radius of this sphere.
	 * 
	 * @param radius
	 * @return this sphere.
	 * @throws NonPositiveArgumentException if the given radius is not positive.
	 */
	public Sphere setRadius(final double radius) {
		
		Validator.assertThat(radius).thatIsNamed(VariableNameCatalogue.RADIUS).isPositive();
		
		this.radius.setValue(radius);
		
		return this;
	}
}

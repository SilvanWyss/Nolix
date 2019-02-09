//package declaration
package ch.nolix.element._3DGUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.core.PositiveFloatingPointNumber;

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
	private final MutableProperty<PositiveFloatingPointNumber> radius =
	new MutableProperty<PositiveFloatingPointNumber>(
		PascalCaseNameCatalogue.RADIUS,
		r -> setRadius(r.getValue()),
		s -> PositiveFloatingPointNumber.createFromSpecification(s),
		r -> r.getSpecification()
	);
	
	//constructor
	/**
	 * Creates a new sphere with a default radius.
	 */
	public Sphere() {
		reset();
		approveProperties();
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
		return radius.getValue().getValue();
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
		
		//Checks if the given diameter is positive.
		Validator.suppose(diameter).thatIsNamed("diameter").isPositive();
		
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
		
		this.radius.setValue(new PositiveFloatingPointNumber(radius));
		
		return this;
	}
}

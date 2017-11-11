//package declaration
package ch.nolix.element._3DGUI;

//own imports
import ch.nolix.core.entity.Property;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.FPNData.Radius;

//class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 140
 */
public final class Sphere extends BaseShape<Sphere> {
	
	//default values
	public static final double DEFAULT_RADIUS = Radius.DEFAULT_VALUE;
	public static final double DEFAULT_DIAMETER = 2.0 * DEFAULT_RADIUS;
	
	//attribute	
	private final Property<Radius> radius =
	new Property<Radius>(
		Radius.TYPE_NAME,
		a -> new Radius(a.getRefOne().toDouble()),
		new Radius()
	);
	
	//constructor
	/**
	 * Creates new sphere with a default radius.
	 */
	public Sphere() {}
	
	//constructor
	/**
	 * Creates a new sphere with the given diameter.
	 * 
	 * @param diameter
	 * @throws NonPositiveArgumentException if the given diameter is not positive.
	 */
	public Sphere(final double diameter) {
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
	 * @return the x-bound-length of this sphere.
	 */
	public double getXBoundLength() {
		return getDiameter();
	}

	//method
	/**
	 * @return the y-bound-length of this sphere.
	 */
	public double getYBoundLength() {
		return getDiameter();
	}

	//method
	/**
	 * @return the z-bound-length of this sphere.
	 */
	public double getZBoundLength() {
		return getDiameter();
	}
	
	//method
	/**
	 * Resets this sphere.
	 */
	public void reset() {
		
		//Calls method of the base class.
		super.reset();
		
		setRadius(DEFAULT_RADIUS);
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
		
		this.radius.setValue(new Radius(radius));
		
		return this;
	}
}

//package declaration
package ch.nolix.element._3D_GUI;

import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.core.PositiveFloatingPointNumber;

//class
public final class Cylinder extends Prisma<Cylinder> {
	
	//default value
	public static final double DEFAULT_RADIUS = 1.0;
	
	//attribute	
	private final MutableProperty<PositiveFloatingPointNumber> radius =
	new MutableProperty<>(
		PascalCaseNameCatalogue.RADIUS,
		r -> setRadius(r.getValue()),
		s -> PositiveFloatingPointNumber.fromSpecification(s),
		r -> r.getSpecification()
	);
	
	//constructor
	public Cylinder() {
		reset();
	}
	
	//method
	public double getDiameter() {
		return 2 * getRadius();
	}
	
	//method
	public double getRadius() {
		return radius.getValue().getValue();
	}
	
	//method
	public float getRadiusAsFloat() {
		return (float)getRadius();
	}
	
	//method
	@Override
	public Cylinder reset() {
		
		//Calls method of base class.
		super.reset();
		
		setRadius(DEFAULT_RADIUS);
		
		return this;
	}
	
	//method
	public Cylinder setDiameter(final double diameter) {
		
		//Checks if the given diameter is positive.
		Validator
		.suppose(diameter)
		.thatIsNamed(VariableNameCatalogue.DIAMETER)
		.isPositive();
		
		return setRadius(0.5 * diameter);
	}
	
	//method
	public Cylinder setRadius(final double radius) {
		
		this.radius.setValue(new PositiveFloatingPointNumber(radius));
		
		return this;
	}
}

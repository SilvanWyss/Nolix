//package declaration
package ch.nolix.element._3DGUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.element.core.PositiveFloatingPointNumber;
import ch.nolix.primitive.validator2.Validator;

//class
public final class Cylinder extends Prisma<Cylinder> {

	//attribute	
	private final MutableProperty<PositiveFloatingPointNumber> radius =
	new MutableProperty<PositiveFloatingPointNumber>(
		PascalCaseNameCatalogue.RADIUS,
		r -> setRadius(r.getValue()),
		s -> PositiveFloatingPointNumber.createFromSpecification(s),
		r -> r.getSpecification()
	);
	
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

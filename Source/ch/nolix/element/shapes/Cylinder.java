//package declaration
package ch.nolix.element.shapes;

//own imports
import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.MutableProperty;

//class
public final class Cylinder extends Prisma<Cylinder> {
	
	//default value
	public static final double DEFAULT_RADIUS = 1.0;
	
	//attribute	
	private final MutableProperty<Double> radius =
	new MutableProperty<>(
		PascalCaseNameCatalogue.RADIUS,
		r -> setRadius(r),
		s -> s.getOneAttributeAsDouble(),
		r -> Node.withOneAttribute(r)
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
		return radius.getValue();
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
		.assertThat(diameter)
		.thatIsNamed(VariableNameCatalogue.DIAMETER)
		.isPositive();
		
		return setRadius(0.5 * diameter);
	}
	
	//method
	public Cylinder setRadius(final double radius) {
		
		Validator.assertThat(radius).thatIsNamed(VariableNameCatalogue.RADIUS).isPositive();
		
		this.radius.setValue(radius);
		
		return this;
	}
}

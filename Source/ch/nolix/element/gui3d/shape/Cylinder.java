//package declaration
package ch.nolix.element.gui3d.shape;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.element.base.MutableValue;

//class
public final class Cylinder extends Prisma<Cylinder> {
	
	//constant
	public static final double DEFAULT_RADIUS = 1.0;
	
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
	public Cylinder setDiameter(final double diameter) {
		
		//Asserts that the given diameter is positive.
		Validator
		.assertThat(diameter)
		.thatIsNamed(LowerCaseCatalogue.DIAMETER)
		.isPositive();
		
		return setRadius(0.5 * diameter);
	}
	
	//method
	public Cylinder setRadius(final double radius) {
		
		Validator.assertThat(radius).thatIsNamed(LowerCaseCatalogue.RADIUS).isPositive();
		
		this.radius.setValue(radius);
		
		return this;
	}
	
	//method
	@Override
	protected void resetPrisma() {
		setRadius(DEFAULT_RADIUS);
	}
}

//package declaration
package ch.nolix.element.shape;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.gui3d.AtomicShape;

//class
public final class Pyramid extends AtomicShape<Pyramid> {
	
	//constants
	public static final double DEFAULT_SIDE_LENGTH = 1.0;
	public static final double DEFAULT_HEIGHT = 1.0;

	//attribute
	private final MutableValue<Double> sideLength =
	new MutableValue<>(
		PascalCaseNameCatalogue.SIDE_LENGTH,
		DEFAULT_SIDE_LENGTH,
		this::setSideLength,
		BaseNode::getOneAttributeAsDouble,
		Node::withAttribute
	);
	
	//attribute
	private final MutableValue<Double> height =
	new MutableValue<>(
		PascalCaseNameCatalogue.HEIGHT,
		DEFAULT_HEIGHT,
		this::setHeight,
		BaseNode::getOneAttributeAsDouble,
		Node::withAttribute
	);
	
	//constructor
	public Pyramid() {
		reset();
	}
	
	//method
	public double getHeight() {
		return height.getValue();
	}
	
	//method
	public float getHeightAsFloat() {
		return sideLength.getValue().floatValue();
	}
	
	//method
	public double getSideLength() {
		return sideLength.getValue();
	}
	
	//method
	public float getSideLengthAsFloat() {
		return sideLength.getValue().floatValue();
	}
	
	//method
	@Override
	public void reset() {
		
		//Calls method of the base class.
		super.reset();
		
		setSideLength(DEFAULT_SIDE_LENGTH);
		setHeight(DEFAULT_HEIGHT);
	}
	
	//method
	public Pyramid setHeight(final double height) {
		
		Validator.assertThat(height).thatIsNamed(VariableNameCatalogue.HEIGHT).isPositive();
		
		this.height.setValue(height);
		
		return this;
	}
	
	//method
	public Pyramid setSideLength(final double sideLength) {
		
		Validator.assertThat(sideLength).thatIsNamed(VariableNameCatalogue.SIDE_LENGTH).isPositive();
		
		this.sideLength.setValue(sideLength);
		
		return this;
	}
}

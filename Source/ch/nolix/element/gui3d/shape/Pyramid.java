//package declaration
package ch.nolix.element.gui3d.shape;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.gui3d.base.AtomicShape;

//class
public final class Pyramid extends AtomicShape<Pyramid> {
	
	//constants
	public static final double DEFAULT_SIDE_LENGTH = 1.0;
	public static final double DEFAULT_HEIGHT = 1.0;

	//attribute
	private final MutableValue<Double> sideLength =
	new MutableValue<>(
		PascalCaseCatalogue.SIDE_LENGTH,
		DEFAULT_SIDE_LENGTH,
		this::setSideLength,
		BaseNode::getOneAttributeAsDouble,
		Node::withAttribute
	);
	
	//attribute
	private final MutableValue<Double> height =
	new MutableValue<>(
		PascalCaseCatalogue.HEIGHT,
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
	public Pyramid setHeight(final double height) {
		
		Validator.assertThat(height).thatIsNamed(LowerCaseCatalogue.HEIGHT).isPositive();
		
		this.height.setValue(height);
		
		return this;
	}
	
	//method
	public Pyramid setSideLength(final double sideLength) {
		
		Validator.assertThat(sideLength).thatIsNamed(LowerCaseCatalogue.SIDE_LENGTH).isPositive();
		
		this.sideLength.setValue(sideLength);
		
		return this;
	}
	
	//method
	@Override
	protected void resetShape() {
		setSideLength(DEFAULT_SIDE_LENGTH);
		setHeight(DEFAULT_HEIGHT);
	}
}

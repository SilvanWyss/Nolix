//package declaration
package ch.nolix.system.gui3d.shape;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.system.gui3d.main.AtomicShape;

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
		INode::getSingleChildNodeAsDouble,
		Node::withChildNode
	);
	
	//attribute
	private final MutableValue<Double> height =
	new MutableValue<>(
		PascalCaseCatalogue.HEIGHT,
		DEFAULT_HEIGHT,
		this::setHeight,
		INode::getSingleChildNodeAsDouble,
		Node::withChildNode
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
		
		GlobalValidator.assertThat(height).thatIsNamed(LowerCaseCatalogue.HEIGHT).isPositive();
		
		this.height.setValue(height);
		
		return this;
	}
	
	//method
	public Pyramid setSideLength(final double sideLength) {
		
		GlobalValidator.assertThat(sideLength).thatIsNamed(LowerCaseCatalogue.SIDE_LENGTH).isPositive();
		
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

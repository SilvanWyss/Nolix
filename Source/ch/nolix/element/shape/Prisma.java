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
public abstract class Prisma<P extends Prisma<P>> extends AtomicShape<P> {
	
	//constant
	public static final double DEFAULT_HEIGHT = 1.0;
	
	//attribute
	private final MutableValue<Double> height =
	new MutableValue<>(
		PascalCaseNameCatalogue.HEIGHT,
		this::setHeight,
		BaseNode::getOneAttributeAsDouble,
		Node::withOneAttribute
	);
	
	//method
	public final double getHeight() {
		return height.getValue();
	}
	
	//method
	public final float getHeightAsFloat() {
		return (float)getHeight();
	}
	
	//method
	@Override
	public P reset() {
		
		//Calls method of base class.
		super.reset();
		
		setHeight(DEFAULT_HEIGHT);
		
		return asConcrete();
	}
	
	//method
	public final P setHeight(final double height) {
		
		Validator.assertThat(height).thatIsNamed(VariableNameCatalogue.HEIGHT).isPositive();
		
		this.height.setValue(height);
		
		return asConcrete();
	}
}

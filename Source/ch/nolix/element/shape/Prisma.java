//package declaration
package ch.nolix.element.shape;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.gui3d.AtomicShape;

//class
public abstract class Prisma<P extends Prisma<P>> extends AtomicShape<P> {
	
	//constant
	public static final double DEFAULT_HEIGHT = 1.0;
	
	//attribute
	private final MutableValue<Double> height =
	new MutableValue<>(
		PascalCaseCatalogue.HEIGHT,
		DEFAULT_HEIGHT,
		this::setHeight,
		BaseNode::getOneAttributeAsDouble,
		Node::withAttribute
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
	public final P setHeight(final double height) {
		
		Validator.assertThat(height).thatIsNamed(LowerCaseCatalogue.HEIGHT).isPositive();
		
		this.height.setValue(height);
		
		return asConcrete();
	}
	
	//method declaration
	protected abstract void resetPrisma();
	
	//method
	@Override
	protected final void resetShape() {
		
		setHeight(DEFAULT_HEIGHT);
		
		resetPrisma();
	}
}

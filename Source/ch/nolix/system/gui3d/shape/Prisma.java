//package declaration
package ch.nolix.system.gui3d.shape;

import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.system.element.MutableValue;
import ch.nolix.system.gui3d.main.AtomicShape;

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
		BaseNode::getSingleChildNodeAsDouble,
		Node::withChildNode
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
		
		GlobalValidator.assertThat(height).thatIsNamed(LowerCaseCatalogue.HEIGHT).isPositive();
		
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

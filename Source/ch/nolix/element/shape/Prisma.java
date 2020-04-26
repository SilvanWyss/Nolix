//package declaration
package ch.nolix.element.shape;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element._3D_GUI.BaseShape;
import ch.nolix.element.base.MutableProperty;

//class
public abstract class Prisma<P extends Prisma<P>> extends BaseShape<P> {
	
	//default value
	public static final double DEFAULT_HEIGHT = 2.0;

	//attribute
	private final MutableProperty<Double> height =
	new MutableProperty<>(
		PascalCaseNameCatalogue.HEIGHT,
		h -> setHeight(h),
		BaseNode::getOneAttributeAsDouble,
		h -> Node.withOneAttribute(h)
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

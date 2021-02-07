//package declaration
package ch.nolix.element.input;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.pair.IntPair;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.Value;

//class
public final class ResizeInput extends Element<ResizeInput> implements IInput<ResizeInput> {
	
	//static method
	public static ResizeInput fromSpecification(final BaseNode specification) {
		
		final var sizeSpecification = specification.getRefOneAttribute();
		
		return
		new ResizeInput(
			sizeSpecification.getRefAttributeAt(1).toInt(),
			sizeSpecification.getRefAttributeAt(2).toInt()
		);
	}
	
	//attribute
	private final Value<IntPair> size =
	new Value<>(
		PascalCaseNameCatalogue.SIZE,
		this::setViewAreaSize,
		s -> new IntPair(s.getRefAttributeAt(1).toInt(), s.getRefAttributeAt(2).toInt()),
		ip -> Node.withAttribute(ip.getValue1(), ip.getValue2())
	);
	
	//constructor
	public ResizeInput(final int viewAreaWidth, final int viewAreaHeight) {
		setViewAreaSize(viewAreaWidth, viewAreaHeight);
	}
	
	//method
	public int getViewAreaHeigh() {
		return size.getValue().getValue2();
	}
	
	//method
	public int getViewAreaWidth() {
		return size.getValue().getValue1();
	}
	
	//method
	@Override
	protected void fillUpElementAttributesInto(final LinkedList<Node> list) {}
	
	private void setViewAreaSize(final int viewAreaWidth, final int viewAreaHeight) {
		setViewAreaSize(new IntPair(viewAreaWidth, viewAreaHeight));
	}
	
	//method
	private void setViewAreaSize(final IntPair size) {
		
		Validator.assertThat(size.getValue1()).thatIsNamed("view area width").isNotNegative();
		Validator.assertThat(size.getValue2()).thatIsNamed("view area hegiht").isNotNegative();
		
		this.size.setValue(size);
	}
}

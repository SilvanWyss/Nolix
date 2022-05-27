//package declaration
package ch.nolix.system.gui.input;

//own imports
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.container.pair.IntPair;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.element.MutableElement;
import ch.nolix.system.element.Value;
import ch.nolix.systemapi.guiapi.inputapi.IInput;

//class
public final class ResizeInput extends MutableElement<ResizeInput> implements IInput<ResizeInput> {
	
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
		PascalCaseCatalogue.SIZE,
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
	public void reset() {}
	
	//method
	private void setViewAreaSize(final int viewAreaWidth, final int viewAreaHeight) {
		setViewAreaSize(new IntPair(viewAreaWidth, viewAreaHeight));
	}
	
	//method
	private void setViewAreaSize(final IntPair size) {
		
		GlobalValidator.assertThat(size.getValue1()).thatIsNamed("view area width").isNotNegative();
		GlobalValidator.assertThat(size.getValue2()).thatIsNamed("view area hegiht").isNotNegative();
		
		this.size.setValue(size);
	}
}

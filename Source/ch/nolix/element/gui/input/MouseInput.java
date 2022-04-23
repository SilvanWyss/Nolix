//package declaration
package ch.nolix.element.gui.input;

//own imports
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.element.base.MutableElement;
import ch.nolix.element.base.Value;
import ch.nolix.element.discretegeometry.Discrete2DPoint;
import ch.nolix.systemapi.guiapi.inputapi.IInput;

//class
public final class MouseInput extends MutableElement<MouseInput> implements IInput<MouseInput> {
	
	//constant
	private static final String INPUT_TYPE_HEADER = "InputType";
	
	//static method
	public static MouseInput fromSpecification(final BaseNode specification) {
		return
		new MouseInput(
			MouseInputType.fromSpecification(specification.getRefFirstAttribute(INPUT_TYPE_HEADER)),
			Discrete2DPoint.fromSpecification(
				specification.getRefFirstAttribute(PascalCaseCatalogue.CURSOR_POSITION)
			)
		);
	}
	
	//attribute
	private final Value<MouseInputType> inputType =
	new Value<>(
		INPUT_TYPE_HEADER,
		this::setInputType,
		MouseInputType::fromSpecification,
		MouseInputType::getSpecification
	);
	
	//attribute
	private final Value<Discrete2DPoint> cursorPosition =
	new Value<>(
		PascalCaseCatalogue.CURSOR_POSITION,
		this::setCursorPosition,
		Discrete2DPoint::fromSpecification,
		Discrete2DPoint::getSpecification
	);
	
	//constructor
	public MouseInput(final MouseInputType inputType, final int cursorXPosition, final int cursorYPosition) {
		setInputType(inputType);
		setCursorPosition(cursorXPosition, cursorYPosition);
	}
	
	//constructor
	private MouseInput(final MouseInputType inputType, final Discrete2DPoint cursorPosition) {
		setInputType(inputType);
		setCursorPosition(cursorPosition);
	}
	
	//method
	public int getCursorXPosition() {
		return cursorPosition.getValue().getX();
	}
	
	//method
	public int getCursorYPosition() {
		return cursorPosition.getValue().getY();
	}
	
	//method
	public MouseInputType getInputType() {
		return inputType.getValue();
	}
	
	//method
	@Override
	public void reset() {}
	
	//method
	private void setCursorPosition(final Discrete2DPoint cursorPosition) {
		this.cursorPosition.setValue(cursorPosition);
	}
	
	//method
	private void setCursorPosition(final int cursorXPosition, final int cursorYPosition) {
		cursorPosition.setValue(new Discrete2DPoint(cursorXPosition, cursorYPosition));
	}
	
	//method
	private void setInputType(final MouseInputType inputType) {
		this.inputType.setValue(inputType);
	}
}

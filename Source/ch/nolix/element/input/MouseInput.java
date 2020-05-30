//package declaration
package ch.nolix.element.input;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.Property;
import ch.nolix.element.discreteGeometry.Discrete2DPoint;

//class
public final class MouseInput extends Element<MouseInput> implements IInput<MouseInput> {
	
	//constant
	private static final String INPUT_TYPE_HEADER = "InputType";
	
	//static method
	public static MouseInput fromSpecification(final BaseNode specification) {
		return
		new MouseInput(
			Discrete2DPoint.fromSpecification(
				specification.getRefFirstAttribute(PascalCaseNameCatalogue.CURSOR_POSITION)
			),
			MouseInputType.fromSpecification(specification.getRefFirstAttribute(INPUT_TYPE_HEADER))
		);
	}
	
	//attribute
	private final Property<Discrete2DPoint> cursorPosition =
	new Property<>(
		PascalCaseNameCatalogue.CURSOR_POSITION,
		this::setCursorPosition,
		Discrete2DPoint::fromSpecification
	);
	
	//attribute
	private final Property<MouseInputType> inputType =
	new Property<>(INPUT_TYPE_HEADER, this::setInputType, MouseInputType::fromSpecification);
	
	//constructor
	public MouseInput(final int cursorXPosition, final int cursorYPosition, final MouseInputType inputType) {
		setCursorPosition(cursorXPosition, cursorYPosition);
		setInputType(inputType);
	}
	
	//constructor
	private MouseInput(final Discrete2DPoint cursorPosition, final MouseInputType inputType) {
		setCursorPosition(cursorPosition);
		setInputType(inputType);
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

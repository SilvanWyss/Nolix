//package declaration
package ch.nolix.system.gui.input;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.discretegeometry.Discrete2DPoint;
import ch.nolix.systemapi.guiapi.inputapi.IMouseInput;
import ch.nolix.systemapi.guiapi.processproperty.MouseInputType;

//class
public final class MouseInput implements IMouseInput<MouseInput> {
	
	//constant
	private static final String CURSOR_POSITION_HEADER = PascalCaseCatalogue.CURSOR_POSITION;
	
	//constant
	private static final String INPUT_TYPE_HEADER = "InputType";
	
	//static method
	public static MouseInput fromSpecification(final INode<?> specification) {
		return
		withCursorPositionAndInputType(
			Discrete2DPoint.fromSpecification(
				specification.getRefFirstChildNodeWithHeader(CURSOR_POSITION_HEADER)
			),
			MouseInputType.fromSpecification(specification.getRefFirstChildNodeWithHeader(INPUT_TYPE_HEADER))
		);
	}
	
	//static method
	public static MouseInput withCursorPositionAndInputType(
		final Discrete2DPoint cursorPosition,
		final MouseInputType inputType
	) {
		return new MouseInput(cursorPosition, inputType);
	}
	
	//static method
	public static MouseInput withCursorPositionAndInputType(
		final int cursorXPosition,
		final int cursorYPosition,
		final MouseInputType inputType
	) {
		return withCursorPositionAndInputType(new Discrete2DPoint(cursorXPosition, cursorYPosition), inputType);
	}
	
	//attribute
	private final Discrete2DPoint cursorPosition;
	
	//attribute
	private final MouseInputType inputType;
	
	//constructor
	private MouseInput(final Discrete2DPoint cursorPosition, final MouseInputType inputType) {
		
		GlobalValidator.assertThat(inputType).thatIsNamed("input type").isNotNull();
		GlobalValidator.assertThat(cursorPosition).thatIsNamed(LowerCaseCatalogue.CURSOR_POSITION).isNotNull();
		
		this.cursorPosition = cursorPosition;
		this.inputType = inputType;
	}
	
	//method
	@Override
	public void fillUpAttributesInto(final LinkedList<INode<?>> list) {
		list.addAtEnd(
			cursorPosition.getSpecificationWithHeader(CURSOR_POSITION_HEADER),
			inputType.getSpecificationWithHeader(INPUT_TYPE_HEADER)
		);
	}
	
	//method
	@Override
	public int getCursorXPosition() {
		return cursorPosition.getX();
	}
	
	//method
	@Override
	public int getCursorYPosition() {
		return cursorPosition.getY();
	}
	
	//method
	@Override
	public MouseInputType getMouseInputType() {
		return inputType;
	}
}

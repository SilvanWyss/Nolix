//package declaration
package ch.nolix.system.gui.input;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.discretegeometry.Discrete2DPoint;
import ch.nolix.system.element.main.Element;
import ch.nolix.systemapi.guiapi.inputapi.IMouseInput;
import ch.nolix.systemapi.guiapi.inputapi.MouseInputType;

//class
public final class MouseInput extends Element implements IMouseInput<MouseInput> {
	
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
	public IContainer<INode<?>> getAttributes() {
		return
		LinkedList.withElements(
			cursorPosition.getSpecificationWithHeader(CURSOR_POSITION_HEADER),
			Node.withHeaderAndChildNode(INPUT_TYPE_HEADER, inputType.name())
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

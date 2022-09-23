//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.multistateelement.NonCascadingProperty;
import ch.nolix.system.webgui.controlstyle.ExtendedControlStyle;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.ILinearContainerStyle;

//class
public final class LinearContainerStyle
extends ExtendedControlStyle<LinearContainerStyle>
implements ILinearContainerStyle<LinearContainerStyle> {
	
	//constant
	public static final int DEFAULT_CHILD_CONTROL_MARGIN = 0;
	
	//constant
	private static final String CHILD_CONTROL_MARGIN_HEADER = "ChildControlMargin";
	
	//attribute
	private final NonCascadingProperty<ControlState, Integer> childControlMargin =
	new NonCascadingProperty<>(
		CHILD_CONTROL_MARGIN_HEADER,
		ControlState.class,
		INode::getSingleChildNodeAsInt,
		Node::withChildNode,
		this::setChildControlMarginForState,
		DEFAULT_CHILD_CONTROL_MARGIN
	);
	
	//constructor
	public LinearContainerStyle() {
		initialize();
	}
	
	//method
	@Override
	public int getChildControlMargin() {
		return childControlMargin.getValue();
	}
	
	//method
	@Override
	public void removeCustomChildControlMargins() {
		childControlMargin.setUndefined();
	}
	
	//method
	@Override
	public LinearContainerStyle setChildControlMarginForState(final ControlState state, final int childControlMargin) {
		
		GlobalValidator.assertThat(childControlMargin).thatIsNamed("child contorl margin").isNotNegative();
		
		this.childControlMargin.setValueForState(state, childControlMargin);
		
		return this;
	}
}

//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.multistateconfiguration.NonCascadingProperty;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.controlstyle.ExtendedControlStyle;
import ch.nolix.systemapi.elementapi.multistateconfigurationapi.IMultiStateConfiguration;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.ILinearContainerStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public abstract class LinearContainerStyle<
	LCS extends ILinearContainerStyle<LCS> & IMultiStateConfiguration<LCS, ControlState>
>
extends ExtendedControlStyle<LCS>
implements ILinearContainerStyle<LCS> {
	
	//constant
	public static final int DEFAULT_CHILD_CONTROL_MARGIN = 0;
	
	//constant
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
	
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
	
	//method
	@Override
	public final int getChildControlMarginWhenHasState(final ControlState state) {
		return childControlMargin.getValueWhenHasState(state);
	}
	
	//method
	@Override
	public final IColor getDefaultTextColor() {
		return DEFAULT_TEXT_COLOR;
	}
	
	//method
	@Override
	public final void removeCustomChildControlMargins() {
		childControlMargin.setUndefined();
	}
	
	//method
	@Override
	public final LCS setChildControlMarginForState(final ControlState state, final int childControlMargin) {
		
		GlobalValidator.assertThat(childControlMargin).thatIsNamed("child control margin").isNotNegative();
		
		this.childControlMargin.setValueForState(state, childControlMargin);
		
		return asConcrete();
	}
}

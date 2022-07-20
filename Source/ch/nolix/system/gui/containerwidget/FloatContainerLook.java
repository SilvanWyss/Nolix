//package declaration
package ch.nolix.system.gui.containerwidget;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.gui.widget.BorderWidgetLook;
import ch.nolix.system.multistateelement.NonCascadingProperty;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//class
public final class FloatContainerLook extends BorderWidgetLook<FloatContainerLook> {
	
	//constant
	public static final int DEFAULT_ELEMENT_MARGIN = 0;
	
	//constant
	private static final String ELEMENT_MARGIN_HEADER = "ElementMargin";
	
	//attribute
	private final NonCascadingProperty<ControlState, Integer> elementMargin =
	new NonCascadingProperty<>(
		ELEMENT_MARGIN_HEADER,
		ControlState.class,
		INode::getSingleChildNodeAsInt,
		Node::withChildNode,
		this::setElementMarginForState,
		DEFAULT_ELEMENT_MARGIN
	);
	
	//method
	public int getElementMargin() {
		return elementMargin.getValue();
	}
	
	//method
	public void removeElementMargins() {
		elementMargin.setUndefined();
	}
	
	//method
	public FloatContainerLook setElementMarginForState(final ControlState state, final int elementMargin) {
		
		this.elementMargin.setValueForState(state, elementMargin);
		
		return asConcrete();
	}
}

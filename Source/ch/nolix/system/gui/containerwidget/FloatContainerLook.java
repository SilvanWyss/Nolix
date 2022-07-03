//package declaration
package ch.nolix.system.gui.containerwidget;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.formatelement.NonCascadingProperty;
import ch.nolix.system.gui.widget.BorderWidgetLook;
import ch.nolix.system.gui.widget.WidgetLookState;

//class
public final class FloatContainerLook extends BorderWidgetLook<FloatContainerLook> {
	
	//constant
	public static final int DEFAULT_ELEMENT_MARGIN = 0;
	
	//constant
	private static final String ELEMENT_MARGIN_HEADER = "ElementMargin";
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Integer> elementMargin =
	new NonCascadingProperty<>(
		ELEMENT_MARGIN_HEADER,
		WidgetLookState.class,
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
	public FloatContainerLook setElementMarginForState(final WidgetLookState state, final int elementMargin) {
		
		this.elementMargin.setValueForState(state, elementMargin);
		
		return asConcrete();
	}
}

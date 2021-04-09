//package declaration
package ch.nolix.element.gui.containerwidget;

//own imports
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.element.formatelement.NonCascadingProperty;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.widget.BorderWidgetLook;

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
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute,
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

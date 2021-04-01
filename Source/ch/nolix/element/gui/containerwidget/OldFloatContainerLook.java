//package declaration
package ch.nolix.element.gui.containerwidget;

import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.element.gui.base.ValueCatalogue;
import ch.nolix.element.gui.widget.OldBorderWidgetLook;
import ch.nolix.element.layerelement.LayerProperty;

//class
public final class OldFloatContainerLook extends OldBorderWidgetLook<OldFloatContainerLook> {
	
	//constant
	private static final int DEFAULT_PROPOSE_CONTENT_WIDTH = ValueCatalogue.BIG_WIDGET_WIDTH;
	private static final int DEFAULT_WIDGET_MARGIN = ValueCatalogue.SMALL_WIDGET_MARGIN;
	
	//constants
	private static final String PROPOSE_CONTENT_WIDTH_HEADER = "ProposeContentWidth";
	private static final String WIDGET_MARGIN_HEADER = "WidgetMargin";
	
	//attribute
	private final LayerProperty<Integer> proposeContentWidth =
	new LayerProperty<>(
		PROPOSE_CONTENT_WIDTH_HEADER,
		DEFAULT_PROPOSE_CONTENT_WIDTH,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute
	);
		
	//attribute
	private final LayerProperty<Integer> widgetMargin =
	new LayerProperty<>(
		WIDGET_MARGIN_HEADER,
		DEFAULT_WIDGET_MARGIN,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute
	);
	
	//method
	public int getRecursiveOrDefaultProposeContentWidth() {
		return proposeContentWidth.getRecursiveOrDefaultValue();
	}
	
	//method
	public int getRecursiveOrDefaultWidgetMargin() {
		return widgetMargin.getRecursiveOrDefaultValue();
	}
	
	//method
	public boolean hasRecursiveProposeContentWidth() {
		return proposeContentWidth.hasRecursiveValue();
	}
	
	//method
	public boolean hasRecursiveWidgetMargin() {
		return widgetMargin.hasRecursiveValue();
	}
	
	//method
	public OldFloatContainerLook setProposeContentWidth(final int proposeContentWidth) {
		
		this.proposeContentWidth.setValue(proposeContentWidth);
		
		return this;
	}
	
	//method
	public OldFloatContainerLook setWidgetMargin(final int widgetMargin) {
		
		this.widgetMargin.setValue(widgetMargin);
		
		return this;
	}
}

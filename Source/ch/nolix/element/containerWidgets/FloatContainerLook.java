//package declaration
package ch.nolix.element.containerWidgets;

//own imports
import ch.nolix.common.node.Node;
import ch.nolix.element.GUI.ValueCatalogue;
import ch.nolix.element.layerElement.LayerProperty;
import ch.nolix.element.widgets.BorderWidgetLook;

//class
public final class FloatContainerLook extends BorderWidgetLook<FloatContainerLook> {
	
	//default value
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
		s -> s.getOneAttributeAsInt(),
		pcw -> Node.withOneAttribute(pcw)
	);
		
	//attribute
	private final LayerProperty<Integer> widgetMargin =
	new LayerProperty<>(
		WIDGET_MARGIN_HEADER,
		DEFAULT_WIDGET_MARGIN,
		s -> s.getOneAttributeAsInt(),
		wm -> Node.withOneAttribute(wm)
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
	public FloatContainerLook setProposeContentWidth(final int proposeContentWidth) {
		
		this.proposeContentWidth.setValue(proposeContentWidth);
		
		return this;
	}
	
	//method
	public FloatContainerLook setWidgetMargin(final int widgetMargin) {
		
		this.widgetMargin.setValue(widgetMargin);
		
		return this;
	}
}

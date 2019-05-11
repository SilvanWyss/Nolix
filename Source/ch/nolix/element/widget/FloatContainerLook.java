//package declaration
package ch.nolix.element.widget;

import ch.nolix.core.layerEntity.LayerProperty;
import ch.nolix.element.core.PositiveInteger;

//class
public final class FloatContainerLook extends BorderWidgetLook<FloatContainerLook> {
	
	//default value
	private static final int DEFAULT_PROPOSE_CONTENT_WIDTH = ValueCatalogue.BIG_WIDGET_WIDTH;
	private static final int DEFAULT_WIDGET_MARGIN = ValueCatalogue.SMALL_WIDGET_MARGIN;
	
	//constants
	private static final String PROPOSE_CONTENT_WIDTH_HEADER = "ProposeContentWidth";
	private static final String WIDGET_MARGIN_HEADER = "WidgetMargin";
	
	//attribute
	private final LayerProperty<PositiveInteger> proposeContentWidth =
	new LayerProperty<PositiveInteger>(
		PROPOSE_CONTENT_WIDTH_HEADER,
		new PositiveInteger(DEFAULT_PROPOSE_CONTENT_WIDTH),
		s -> PositiveInteger.createFromSpecification(s),
		pcw -> pcw.getSpecification()
	);
		
	//attribute
	private final LayerProperty<PositiveInteger> widgetMargin =
	new LayerProperty<PositiveInteger>(
		WIDGET_MARGIN_HEADER,
		new PositiveInteger(DEFAULT_WIDGET_MARGIN),
		s -> PositiveInteger.createFromSpecification(s),
		wm -> wm.getSpecification()
	);
	
	//method
	public int getRecursiveOrDefaultProposeContentWidth() {
		return proposeContentWidth.getRecursiveOrDefaultValue().getValue();
	}
	
	//method
	public int getRecursiveOrDefaultWidgetMargin() {
		return widgetMargin.getRecursiveOrDefaultValue().getValue();
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
		
		this.proposeContentWidth.setValue(new PositiveInteger(proposeContentWidth));
		
		return this;
	}
	
	//method
	public FloatContainerLook setWidgetMargin(final int widgetMargin) {
		
		this.widgetMargin.setValue(new PositiveInteger(widgetMargin));
		
		return this;
	}
}

//package declaration
package ch.nolix.element.gui.containerwidget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.element.color.Color;
import ch.nolix.element.gui.base.ValueCatalogue;
import ch.nolix.element.layerelement.LayerProperty;
import ch.nolix.element.widget.BorderWidgetLook;

//class
public final class GridLook extends BorderWidgetLook<GridLook> {
	
	//constants
	public static final GridLineType DEFAULT_LINE_TYPE = GridLineType.INNER_LINES;
	public static final int DEFAULT_LINE_THICKNESS = ValueCatalogue.SMALL_LINE_THICKNESS;
	public static final Color DEFAULT_LINE_COLOR = Color.BLACK;
	public static final int DEFAULT_ELEMENT_MARGIN = ValueCatalogue.SMALL_WIDGET_MARGIN;
	
	//constant
	private static final String LINE_TYPE_HEADER = "LineType";
	
	//attribute
	private final LayerProperty<Integer> lineThickness =
	new LayerProperty<>(
		PascalCaseCatalogue.LINE_THICKNESS,
		DEFAULT_LINE_THICKNESS,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute
	);
	
	//attribute
	private final LayerProperty<Color> lineColor =
	new LayerProperty<>(
		PascalCaseCatalogue.COLOR,
		DEFAULT_LINE_COLOR,
		Color::fromSpecification,
		Color::getSpecification
	);
	
	//attribute
	private final LayerProperty<Integer> elementMargin =
	new LayerProperty<>(
		PascalCaseCatalogue.ELEMENT_MARGIN,
		DEFAULT_ELEMENT_MARGIN,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute
	);
	
	//attribute
	private final LayerProperty<GridLineType> lineType =
	new LayerProperty<>(
		LINE_TYPE_HEADER,
		DEFAULT_LINE_TYPE,
		GridLineType::fromSpecification,
		GridLineType::getSpecification
	);
	
	//method
	public int getRecursiveElementMarginOrDefault() {
		return elementMargin.getRecursiveOrDefaultValue();
	}
	
	//method
	public Color getRecursiveLineColorOrDefault() {
		return lineColor.getRecursiveOrDefaultValue();
	}
	
	//method
	public int getRecursiveLineThicknessOrDefault() {
		return lineThickness.getRecursiveOrDefaultValue();
	}
	
	//method
	public GridLineType getRecursiveLineTypeOrDefault() {
		return lineType.getRecursiveOrDefaultValue();
	}
	
	//method
	public boolean hasRecursiveElementMargin() {
		return elementMargin.hasRecursiveValue();
	}
	
	//method
	public boolean hasRecursiveLineColor() {
		return lineColor.hasRecursiveValue();
	}
	
	//method
	public boolean hasRecursiveLineThickness() {
		return lineThickness.hasRecursiveValue();
	}
	
	//method
	public boolean hasRecursiveLineType() {
		return lineType.hasRecursiveValue();
	}
	
	//method
	public GridLook setElementMargin(final int elementMargin) {
		
		this.elementMargin.setValue(elementMargin);
		
		return this;
	}
	
	//method
	public GridLook setLineColor(final Color lineColor) {
		
		this.lineColor.setValue(lineColor);
		
		return this;
	}
	
	//method
	public GridLook setLineThickness(final int lineThickness) {
		
		this.lineThickness.setValue(lineThickness);
		
		return this;
	}
	
	//method
	public GridLook setLineType(final GridLineType lineType) {
		
		this.lineType.setValue(lineType);
		
		return this;
	}
}

//package declaration
package ch.nolix.element.gui.containerwidget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.element.gui.base.ValueCatalogue;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.OldBorderWidgetLook;
import ch.nolix.element.layerelement.LayerProperty;

//class
public final class OldGridLook extends OldBorderWidgetLook<OldGridLook> {
	
	//constants
	public static final GridType DEFAULT_LINE_TYPE = GridType.INNER_LINES;
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
	private final LayerProperty<GridType> lineType =
	new LayerProperty<>(
		LINE_TYPE_HEADER,
		DEFAULT_LINE_TYPE,
		GridType::fromSpecification,
		GridType::getSpecification
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
	public GridType getRecursiveLineTypeOrDefault() {
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
	public OldGridLook setElementMargin(final int elementMargin) {
		
		this.elementMargin.setValue(elementMargin);
		
		return this;
	}
	
	//method
	public OldGridLook setLineColor(final Color lineColor) {
		
		this.lineColor.setValue(lineColor);
		
		return this;
	}
	
	//method
	public OldGridLook setLineThickness(final int lineThickness) {
		
		this.lineThickness.setValue(lineThickness);
		
		return this;
	}
	
	//method
	public OldGridLook setLineType(final GridType lineType) {
		
		this.lineType.setValue(lineType);
		
		return this;
	}
}

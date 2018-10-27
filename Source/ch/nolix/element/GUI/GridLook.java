//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.entity2.Property;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.PositiveInteger;

//class
public final class GridLook extends BorderWidgetLook<GridLook> {
	
	//default values
	public static final GridLineType DEFAULT_LINE_TYPE = GridLineType.InnerLines;
	public static final int DEFAULT_LINE_THICKNESS = ValueCatalogue.SMALL_LINE_THICKNESS;
	public static final Color DEFAULT_LINE_COLOR = Color.BLACK;
	public static final int DEFAULT_ELEMENT_MARGIN = ValueCatalogue.SMALL_WIDGET_MARGIN;
	
	//constant
	private static final String LINE_TYPE_HEADER = "LineType";
	
	//attribute
	private final Property<PositiveInteger> lineThickness =
	new Property<PositiveInteger>(
		PascalCaseNameCatalogue.LINE_THICKNESS,
		new PositiveInteger(DEFAULT_LINE_THICKNESS),
		s -> PositiveInteger.createFromSpecification(s),
		lt -> lt.getSpecification()
	);
	
	//attribute
	private final Property<Color> lineColor =
	new Property<Color>(
		PascalCaseNameCatalogue.COLOR,
		DEFAULT_LINE_COLOR,
		s -> Color.createFromSpecification(s),
		lc -> lc.getSpecification()
	);
	
	//attribute
	private final Property<PositiveInteger> elementMargin =
	new Property<PositiveInteger>(
		PascalCaseNameCatalogue.ELEMENT_MARGIN,
		new PositiveInteger(DEFAULT_ELEMENT_MARGIN),
		s -> PositiveInteger.createFromSpecification(s),
		em -> em.getSpecification()
	);
	
	//attribute
	private final Property<GridLineType> lineType =
	new Property<GridLineType>(
		LINE_TYPE_HEADER,
		DEFAULT_LINE_TYPE,
		s -> GridLineType.createFromSpecification(s),
		lt -> lt.getSpecification()
	);
	
	//method
	public int getRecursiveElementMarginOrDefault() {
		return elementMargin.getRecursiveOrDefaultValue().getValue();
	}
	
	//method
	public Color getRecursiveLineColorOrDefault() {
		return lineColor.getRecursiveOrDefaultValue();
	}
	
	//method
	public int getRecursiveLineThicknessOrDefault() {
		return lineThickness.getRecursiveOrDefaultValue().getValue();
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
		
		this.elementMargin.setValue(new PositiveInteger(elementMargin));
		
		return this;
	}
	
	//method
	public GridLook setLineColor(final Color lineColor) {
		
		this.lineColor.setValue(lineColor);
		
		return this;
	}
	
	//method
	public GridLook setLineThickness(final int lineThickness) {
		
		this.lineThickness.setValue(new PositiveInteger(lineThickness));
		
		return this;
	}
	
	//method
	public GridLook setLineType(final GridLineType lineType) {
		
		this.lineType.setValue(lineType);
		
		return this;
	}
}

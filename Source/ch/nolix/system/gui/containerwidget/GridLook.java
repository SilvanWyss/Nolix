//package declaration
package ch.nolix.system.gui.containerwidget;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.formatelement.NonCascadingProperty;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.widget.BorderWidgetLook;
import ch.nolix.system.gui.widget.WidgetLookState;

//class
public final class GridLook extends BorderWidgetLook<GridLook> {
	
	//constants
	public static final GridType DEFAULT_GRID_TYPE = GridType.INNER_LINES;
	public static final int DEFAULT_GRID_THICKNESS = 1;
	public static final Color DEFAULT_GRID_COLOR = Color.BLACK;
	public static final int DEFAULT_ELEMENT_MARGIN = 0;
	
	//constants
	private static final String GRID_TYPE_HEADER = "GridType";
	private static final String GRID_THICKNESS_HEADER = "GridThickness";
	private static final String GRID_COLOR_HEADER = "GridColor";
	private static final String ELEMENT_MARGIN_HEADER = "ElementMargin";
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, GridType> gridType =
	new NonCascadingProperty<>(
		GRID_TYPE_HEADER,
		WidgetLookState.class,
		GridType::fromSpecification,
		Node::fromEnum,
		this::setGridTypeForState,
		DEFAULT_GRID_TYPE
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Integer> gridThickness =
	new NonCascadingProperty<>(
		GRID_THICKNESS_HEADER,
		WidgetLookState.class,
		INode::getSingleChildNodeAsInt,
		Node::withChildNode,
		this::setGridThicknessForState,
		DEFAULT_GRID_THICKNESS
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Color> gridColor =
	new NonCascadingProperty<>(
		GRID_COLOR_HEADER,
		WidgetLookState.class,
		Color::fromSpecification,
		Color::getSpecification,
		this::setGridColorForState,
		DEFAULT_GRID_COLOR
	);
	
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
	public Color getGridColor() {
		return gridColor.getValue();
	}
	
	//method
	public int getElementMargin() {
		return elementMargin.getValue();
	}
	
	//method
	public int getGridThickness() {
		return gridThickness.getValue();
	}
	
	//method
	public GridType getGridType() {
		return gridType.getValue();
	}
	
	//method
	public void removeElementMargins() {
		elementMargin.setUndefined();
	}
	
	//method
	public void removeGridColors() {
		gridColor.setUndefined();
	}
	
	//method
	public void removeGridThicknesses() {
		gridThickness.setUndefined();
	}
	
	//method
	public void removeGridTypes() {
		gridType.setUndefined();
	}
	
	//method
	public GridLook setGridColorForState(final WidgetLookState state, final Color gridColor) {
		
		this.gridColor.setValueForState(state, gridColor);
		
		return asConcrete();
	}
	
	//method
	public GridLook setElementMarginForState(final WidgetLookState state, final int elementMargin) {
		
		GlobalValidator.assertThat(elementMargin).thatIsNamed(LowerCaseCatalogue.ELEMENT_MARGIN).isNotNegative();
		
		this.elementMargin.setValueForState(state, elementMargin);
		
		return asConcrete();
	}
	
	//method
	public GridLook setGridThicknessForState(final WidgetLookState state, final int gridThickness) {
		
		GlobalValidator.assertThat(gridThickness).thatIsNamed("grid thickness").isNotNegative();
		
		this.gridThickness.setValueForState(state, gridThickness);
		
		return asConcrete();
	}
	
	//method
	public GridLook setGridTypeForState(final WidgetLookState state, final GridType gridType) {
		
		this.gridType.setValueForState(state, gridType);
		
		return asConcrete();
	}
}

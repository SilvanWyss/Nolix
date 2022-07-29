//package declaration
package ch.nolix.system.gui.containerwidget;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.multistateelement.NonCascadingProperty;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.widget.BorderWidgetStyle;
import ch.nolix.systemapi.guiapi.controlproperty.GridType;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//class
public final class GridStyle extends BorderWidgetStyle<GridStyle> {
	
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
	private final NonCascadingProperty<ControlState, GridType> gridType =
	new NonCascadingProperty<>(
		GRID_TYPE_HEADER,
		ControlState.class,
		GridType::fromSpecification,
		Node::fromEnum,
		this::setGridTypeForState,
		DEFAULT_GRID_TYPE
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Integer> gridThickness =
	new NonCascadingProperty<>(
		GRID_THICKNESS_HEADER,
		ControlState.class,
		INode::getSingleChildNodeAsInt,
		Node::withChildNode,
		this::setGridThicknessForState,
		DEFAULT_GRID_THICKNESS
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Color> gridColor =
	new NonCascadingProperty<>(
		GRID_COLOR_HEADER,
		ControlState.class,
		Color::fromSpecification,
		Color::getSpecification,
		this::setGridColorForState,
		DEFAULT_GRID_COLOR
	);
	
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
	public GridStyle setGridColorForState(final ControlState state, final Color gridColor) {
		
		this.gridColor.setValueForState(state, gridColor);
		
		return asConcrete();
	}
	
	//method
	public GridStyle setElementMarginForState(final ControlState state, final int elementMargin) {
		
		GlobalValidator.assertThat(elementMargin).thatIsNamed(LowerCaseCatalogue.ELEMENT_MARGIN).isNotNegative();
		
		this.elementMargin.setValueForState(state, elementMargin);
		
		return asConcrete();
	}
	
	//method
	public GridStyle setGridThicknessForState(final ControlState state, final int gridThickness) {
		
		GlobalValidator.assertThat(gridThickness).thatIsNamed("grid thickness").isNotNegative();
		
		this.gridThickness.setValueForState(state, gridThickness);
		
		return asConcrete();
	}
	
	//method
	public GridStyle setGridTypeForState(final ControlState state, final GridType gridType) {
		
		this.gridType.setValueForState(state, gridType);
		
		return asConcrete();
	}
}

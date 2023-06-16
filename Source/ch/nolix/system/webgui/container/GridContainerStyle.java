//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.multistateconfiguration.NonCascadingProperty;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.controlstyle.ExtendedControlStyle;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.structureproperty.GridType;
import ch.nolix.systemapi.webguiapi.containerapi.IGridContainerStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class GridContainerStyle
extends ExtendedControlStyle<GridContainerStyle>
implements IGridContainerStyle<GridContainerStyle> {
	
	//constant
	public static final GridType DEFAULT_GRID_TYPE = GridType.INNER_LINES;
	
	//constant
	public static final int DEFAULT_GRID_THICKNESS = 1;
	
	//constant
	public static final Color DEFAULT_GRID_COLOR = Color.BLACK;
	
	//constant
	public static final int DEFAULT_CHILD_CONTROL_MARGIN = 0;
	
	//constant
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
	
	//constant
	private static final String GRID_TYPE_HEADER = "GridType";
	
	//constant
	private static final String GRID_THICKNESS_HEADER = "GridThickness";
	
	//constant
	private static final String GRID_COLOR_HEADER = "GridColor";
	
	//constant
	private static final String CHILD_CONTROL_MARGIN_HEADER = "ChildControlMargin";
	
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
	private final NonCascadingProperty<ControlState, IColor> gridColor =
	new NonCascadingProperty<>(
		GRID_COLOR_HEADER,
		ControlState.class,
		Color::fromSpecification,
		IColor::getSpecification,
		this::setGridColorForState,
		DEFAULT_GRID_COLOR
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Integer> childControlMargin =
	new NonCascadingProperty<>(
		CHILD_CONTROL_MARGIN_HEADER,
		ControlState.class,
		INode::getSingleChildNodeAsInt,
		Node::withChildNode,
		this::setChildControlMarginForState,
		DEFAULT_CHILD_CONTROL_MARGIN
	);
	
	//constructor
	public GridContainerStyle() {
		initialize();
	}
	
	//method
	@Override
	public int getChildControlMarginWhenHasState(final ControlState state) {
		return childControlMargin.getValueWhenHasState(state);
	}
	
	//method
	@Override
	public IColor getDefaultTextColor() {
		return DEFAULT_TEXT_COLOR;
	}
	
	//method
	@Override
	public IColor getGridColorWhenHasState(final ControlState state) {
		return gridColor.getValueWhenHasState(state);
	}
	
	//method
	@Override
	public int getGridThicknessWhenHasState(final ControlState state) {
		return gridThickness.getValueWhenHasState(state);
	}
	
	//method
	@Override
	public GridType getGridTypeWhenHasState(final ControlState state) {
		return gridType.getValueOfState(state);
	}
	
	//method
	@Override
	public void removeCustomChildControlMargins() {
		childControlMargin.setUndefined();
	}
	
	//method
	@Override
	public void removeCustomGridColors() {
		gridColor.setUndefined();
	}
	
	//method
	@Override
	public void removeCustomGridThicknesses() {
		gridThickness.setUndefined();
	}
	
	//method
	@Override
	public void removeCustomGridTypes() {
		gridType.setUndefined();
	}
	
	//method
	@Override
	public GridContainerStyle setChildControlMarginForState(final ControlState state, final int childControlMargin) {
		
		GlobalValidator.assertThat(childControlMargin).thatIsNamed("child control margin").isNotNegative();
		
		this.childControlMargin.setValueForState(state, childControlMargin);
		
		return this;
	}
	
	//method
	@Override
	public GridContainerStyle setGridColorForState(final ControlState state, final IColor gridColor) {
		
		this.gridColor.setValueForState(state, gridColor);
		
		return this;
	}
	
	//method
	@Override
	public GridContainerStyle setGridThicknessForState(final ControlState state, final int gridThickness) {
		
		GlobalValidator.assertThat(gridThickness).thatIsNamed("grid thickness").isNotNegative();
		
		this.gridThickness.setValueForState(state, gridThickness);
		
		return this;
	}
	
	//method
	@Override
	public GridContainerStyle setGridTypeForState(final ControlState state, final GridType gridType) {
		
		this.gridType.setValueForState(state, gridType);
		
		return this;
	}
}

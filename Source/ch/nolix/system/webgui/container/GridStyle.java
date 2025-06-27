package ch.nolix.system.webgui.container;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.element.multistateconfiguration.NonCascadingProperty;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.webguiapi.containerapi.GridType;
import ch.nolix.systemapi.webguiapi.containerapi.IGridStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class GridStyle extends AbstractControlStyle<IGridStyle> implements IGridStyle {

  public static final GridType DEFAULT_GRID_TYPE = GridType.INNER_LINES;

  public static final int DEFAULT_GRID_THICKNESS = 1;

  public static final Color DEFAULT_GRID_COLOR = X11ColorCatalog.BLACK;

  public static final int DEFAULT_CHILD_CONTROL_MARGIN = 0;

  private static final String GRID_TYPE_HEADER = "GridType";

  private static final String GRID_THICKNESS_HEADER = "GridThickness";

  private static final String GRID_COLOR_HEADER = "GridColor";

  private static final String CHILD_CONTROL_MARGIN_HEADER = "ChildControlMargin";

  private final NonCascadingProperty<ControlState, GridType> gridType = new NonCascadingProperty<>(
    GRID_TYPE_HEADER,
    ControlState.class,
    GridType::fromSpecification,
    Node::fromEnum,
    this::setGridTypeForState,
    DEFAULT_GRID_TYPE);

  private final NonCascadingProperty<ControlState, Integer> gridThickness = NonCascadingProperty
    .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
      GRID_THICKNESS_HEADER,
      ControlState.class,
      this::setGridThicknessForState,
      DEFAULT_GRID_THICKNESS);

  private final NonCascadingProperty<ControlState, IColor> gridColor = new NonCascadingProperty<>(
    GRID_COLOR_HEADER,
    ControlState.class,
    Color::fromSpecification,
    IColor::getSpecification,
    this::setGridColorForState,
    DEFAULT_GRID_COLOR);

  private final NonCascadingProperty<ControlState, Integer> childControlMargin = NonCascadingProperty
    .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
      CHILD_CONTROL_MARGIN_HEADER,
      ControlState.class,
      this::setChildControlMarginForState,
      DEFAULT_CHILD_CONTROL_MARGIN);

  public GridStyle() {
    initialize();
  }

  @Override
  public int getChildControlMarginWhenHasState(final ControlState state) {
    return childControlMargin.getValueWhenHasState(state);
  }

  @Override
  public IColor getGridColorWhenHasState(final ControlState state) {
    return gridColor.getValueWhenHasState(state);
  }

  @Override
  public int getGridThicknessWhenHasState(final ControlState state) {
    return gridThickness.getValueWhenHasState(state);
  }

  @Override
  public GridType getGridTypeWhenHasState(final ControlState state) {
    return gridType.getValueOfState(state);
  }

  @Override
  public void removeCustomChildControlMargins() {
    childControlMargin.setUndefined();
  }

  @Override
  public void removeCustomGridColors() {
    gridColor.setUndefined();
  }

  @Override
  public void removeCustomGridThicknesses() {
    gridThickness.setUndefined();
  }

  @Override
  public void removeCustomGridTypes() {
    gridType.setUndefined();
  }

  @Override
  public IGridStyle setChildControlMarginForState(final ControlState state, final int childControlMargin) {

    Validator.assertThat(childControlMargin).thatIsNamed("child control margin").isNotNegative();

    this.childControlMargin.setValueForState(state, childControlMargin);

    return this;
  }

  @Override
  public IGridStyle setGridColorForState(final ControlState state, final IColor gridColor) {

    this.gridColor.setValueForState(state, gridColor);

    return this;
  }

  @Override
  public IGridStyle setGridThicknessForState(final ControlState state, final int gridThickness) {

    Validator.assertThat(gridThickness).thatIsNamed("grid thickness").isNotNegative();

    this.gridThickness.setValueForState(state, gridThickness);

    return this;
  }

  @Override
  public IGridStyle setGridTypeForState(final ControlState state, final GridType gridType) {

    this.gridType.setValueForState(state, gridType);

    return this;
  }
}

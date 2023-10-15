//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.element.multistateconfiguration.NonCascadingProperty;
import ch.nolix.system.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.elementapi.multistateconfigurationapi.IMultiStateConfiguration;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.ILinearContainerStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public abstract class LinearContainerStyle<LCS extends ILinearContainerStyle<LCS> & IMultiStateConfiguration<LCS, ControlState>>
    extends ControlStyle<LCS>
    implements ILinearContainerStyle<LCS> {

  // constant
  public static final int DEFAULT_CHILD_CONTROL_MARGIN = 0;

  // constant
  private static final String CHILD_CONTROL_MARGIN_HEADER = "ChildControlMargin";

  // attribute
  private final NonCascadingProperty<ControlState, Integer> childControlMargin = NonCascadingProperty
      .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
          CHILD_CONTROL_MARGIN_HEADER,
          ControlState.class,
          this::setChildControlMarginForState,
          DEFAULT_CHILD_CONTROL_MARGIN);

  // method
  @Override
  public int getChildControlMarginWhenHasState(final ControlState state) {
    return childControlMargin.getValueWhenHasState(state);
  }

  // method
  @Override
  public void removeCustomChildControlMargins() {
    childControlMargin.setUndefined();
  }

  // method
  @Override
  public LCS setChildControlMarginForState(final ControlState state, final int childControlMargin) {

    GlobalValidator.assertThat(childControlMargin).thatIsNamed("child control margin").isNotNegative();

    this.childControlMargin.setValueForState(state, childControlMargin);

    return asConcrete();
  }
}

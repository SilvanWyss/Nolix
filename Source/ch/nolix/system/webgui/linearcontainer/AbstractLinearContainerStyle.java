package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.element.multistateconfiguration.NonCascadingProperty;
import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.elementapi.multistateconfigurationapi.IMultiStateConfiguration;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.ILinearContainerStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public abstract class AbstractLinearContainerStyle< //
S extends ILinearContainerStyle<S> & IMultiStateConfiguration<S, ControlState> //
>
extends AbstractControlStyle<S>
implements ILinearContainerStyle<S> {

  public static final int DEFAULT_CHILD_CONTROL_MARGIN = 0;

  private static final String CHILD_CONTROL_MARGIN_HEADER = "ChildControlMargin";

  private final NonCascadingProperty<ControlState, Integer> childControlMargin = NonCascadingProperty
    .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
      CHILD_CONTROL_MARGIN_HEADER,
      ControlState.class,
      this::setChildControlMarginForState,
      DEFAULT_CHILD_CONTROL_MARGIN);

  @Override
  public int getChildControlMarginWhenHasState(final ControlState state) {
    return childControlMargin.getValueWhenHasState(state);
  }

  @Override
  public void removeCustomChildControlMargins() {
    childControlMargin.setUndefined();
  }

  @Override
  public S setChildControlMarginForState(final ControlState state, final int childControlMargin) {

    Validator.assertThat(childControlMargin).thatIsNamed("child control margin").isNotNegative();

    this.childControlMargin.setValueForState(state, childControlMargin);

    return asConcrete();
  }
}

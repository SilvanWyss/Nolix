/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.linearcontainer;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.system.element.multistateconfiguration.NonCascadingProperty;
import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.control.linearcontainer.ILinearContainerStyle;
import ch.nolix.systemapi.element.multistateconfiguration.IMultiStateConfiguration;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 * @param <S> the type of a {@link AbstractLinearContainerStyle}.
 */
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

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeCustomChildControlMargins() {
    childControlMargin.setUndefined();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public S setChildControlMarginForState(final ControlState state, final int childControlMargin) {
    Validator.assertThat(childControlMargin).thatIsNamed("child control margin").isNotNegative();

    this.childControlMargin.setValueForState(state, childControlMargin);

    return asConcrete();
  }
}

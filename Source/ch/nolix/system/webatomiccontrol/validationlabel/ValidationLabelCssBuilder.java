package ch.nolix.system.webatomiccontrol.validationlabel;

import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.cssmodel.ICssProperty;
import ch.nolix.coreapi.web.cssmodel.ICssRule;
import ch.nolix.system.webgui.controltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.webatomiccontrol.validationlabel.IValidationLabel;
import ch.nolix.systemapi.webatomiccontrol.validationlabel.IValidationLabelStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

public final class ValidationLabelCssBuilder
extends AbstractControlCssBuilder<IValidationLabel, IValidationLabelStyle> {
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IValidationLabel control,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final IValidationLabel text,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final IValidationLabel text,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IValidationLabel text,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }
}

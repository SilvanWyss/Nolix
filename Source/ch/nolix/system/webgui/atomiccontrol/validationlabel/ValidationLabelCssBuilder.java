package ch.nolix.system.webgui.atomiccontrol.validationlabel;

import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.css.ICssProperty;
import ch.nolix.coreapi.web.css.ICssRule;
import ch.nolix.system.webgui.basecontroltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.validationlabelapi.IValidationLabel;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.validationlabelapi.IValidationLabelStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

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

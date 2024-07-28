//package declaration
package ch.nolix.system.webgui.atomiccontrol;

import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.basecontroltool.ControlCssBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IValidationLabel;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IValidationLabelStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class ValidationLabelCssBuilder
extends ControlCssBuilder<IValidationLabel, IValidationLabelStyle> {

  //method
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IValidationLabel control,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final IValidationLabel text,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final IValidationLabel text,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IValidationLabel text,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }
}

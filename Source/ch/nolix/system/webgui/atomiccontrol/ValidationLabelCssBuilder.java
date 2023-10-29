//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.basecontrolservice.ControlCssBuilder;
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
    final LinkedList<CssProperty> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final IValidationLabel text,
    final ControlState state,
    final LinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final IValidationLabel text,
    final LinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IValidationLabel text,
    final ControlState state,
    final LinkedList<ICssProperty> list) {
    //Does nothing.
  }
}

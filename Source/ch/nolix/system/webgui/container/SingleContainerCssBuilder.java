//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.basecontrolservice.ControlCssBuilder;
import ch.nolix.systemapi.webguiapi.containerapi.ISingleContainer;
import ch.nolix.systemapi.webguiapi.containerapi.ISingleContainerStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class SingleContainerCssBuilder
    extends ControlCssBuilder<ISingleContainer, ISingleContainerStyle> {

  // method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
      final ISingleContainer control,
      final LinkedList<? super ICssRule> list) {
    // Does nothing.
  }

  // method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
      final ISingleContainer control,
      final ControlState state,
      final LinkedList<? super ICssRule> list) {
    // Does nothing.
  }

  // method
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
      final ISingleContainer control,
      final LinkedList<CssProperty> list) {
    // Does nothing.
  }

  // method
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
      final ISingleContainer control,
      final ControlState state,
      final LinkedList<ICssProperty> list) {
    // Does nothing.
  }
}

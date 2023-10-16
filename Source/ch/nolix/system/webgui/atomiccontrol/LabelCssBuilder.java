//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.basecontrolservice.ControlCssBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILabel;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILabelStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class LabelCssBuilder extends ControlCssBuilder<ILabel, ILabelStyle> {

  //method
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
      final ILabel control,
      final LinkedList<CssProperty> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
      final ILabel label,
      final ControlState state,
      final LinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
      final ILabel label,
      final LinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
      final ILabel label,
      final ControlState state,
      final LinkedList<ICssProperty> list) {
    //Does nothing.
  }
}

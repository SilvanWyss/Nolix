//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.basecontrolservice.ControlCssBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILink;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILinkStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class LinkCssBuilder extends ControlCssBuilder<ILink, ILinkStyle> {

  //method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final ILink control,
    final LinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final ILink control,
    final ControlState state,
    final LinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final ILink control,
    final LinkedList<CssProperty> list) {
    list.addAtEnd(CssProperty.withNameAndValue("text-decoration", "none"));
  }

  //method
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final ILink control,
    final ControlState state,
    final LinkedList<ICssProperty> list) {
    //Does nothing.
  }
}

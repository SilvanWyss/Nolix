//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.basecontroltool.ControlCssBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextbox;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextboxStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class TextboxCssBuilder extends ControlCssBuilder<ITextbox, ITextboxStyle> {

  //method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final ITextbox textbox,
    final ControlState state,
    final LinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final ITextbox textbox,
    final LinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final ITextbox control,
    final LinkedList<CssProperty> list) {
    list.addAtEnd(CssProperty.withNameAndValue("outline", "none"));
  }

  //method
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final ITextbox textbox,
    final ControlState state,
    final LinkedList<ICssProperty> list) {
    //Does nothing.
  }
}

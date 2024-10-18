package ch.nolix.system.webgui.atomiccontrol;

import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.basecontroltool.ControlCssBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextbox;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextboxStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class TextboxCssBuilder extends ControlCssBuilder<ITextbox, ITextboxStyle> {

  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final ITextbox textbox,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final ITextbox textbox,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final ITextbox control,
    final ILinkedList<ICssProperty> list) {
    list.addAtEnd(CssProperty.withNameAndValue("outline", "none"));
  }

  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final ITextbox textbox,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }
}

package ch.nolix.system.webatomiccontrol.textbox;

import ch.nolix.core.web.cssmodel.CssProperty;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.cssmodel.ICssProperty;
import ch.nolix.coreapi.web.cssmodel.ICssRule;
import ch.nolix.system.webgui.basecontroltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.webatomiccontrol.textbox.ITextbox;
import ch.nolix.systemapi.webatomiccontrol.textbox.ITextboxStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

public final class TextboxCssBuilder extends AbstractControlCssBuilder<ITextbox, ITextboxStyle> {
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

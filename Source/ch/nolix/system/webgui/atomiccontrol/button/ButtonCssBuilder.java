package ch.nolix.system.webgui.atomiccontrol.button;

import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.basecontroltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.buttonapi.IButton;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.buttonapi.IButtonStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class ButtonCssBuilder extends AbstractControlCssBuilder<IButton, IButtonStyle> {

  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final IButton button,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final IButton button,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IButton control,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IButton button,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }
}

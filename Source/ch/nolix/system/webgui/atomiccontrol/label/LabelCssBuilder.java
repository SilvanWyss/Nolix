package ch.nolix.system.webgui.atomiccontrol.label;

import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.basecontroltool.ControlCssBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILabel;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILabelStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class LabelCssBuilder extends ControlCssBuilder<ILabel, ILabelStyle> {

  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final ILabel control,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final ILabel label,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final ILabel label,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final ILabel label,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }
}
package ch.nolix.system.webgui.atomiccontrol.label;

import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.cssmodel.ICssProperty;
import ch.nolix.coreapi.web.cssmodel.ICssRule;
import ch.nolix.system.webgui.basecontroltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.webgui.atomiccontrol.labelapi.ILabel;
import ch.nolix.systemapi.webgui.atomiccontrol.labelapi.ILabelStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

public final class LabelCssBuilder extends AbstractControlCssBuilder<ILabel, ILabelStyle> {
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

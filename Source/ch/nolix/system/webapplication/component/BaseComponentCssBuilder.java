package ch.nolix.system.webapplication.component;

import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.css.ICssProperty;
import ch.nolix.coreapi.web.css.ICssRule;
import ch.nolix.system.webgui.basecontroltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.webapplicationapi.componentapi.IComponent;
import ch.nolix.systemapi.webapplicationapi.componentapi.IComponentStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class BaseComponentCssBuilder extends AbstractControlCssBuilder<IComponent, IComponentStyle> {

  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final IComponent button,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final IComponent button,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IComponent control,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IComponent button,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }
}

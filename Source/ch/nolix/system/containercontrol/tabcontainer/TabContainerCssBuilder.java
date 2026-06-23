/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.tabcontainer;

import ch.nolix.baseapi.css.cssmodel.ICssProperty;
import ch.nolix.baseapi.css.cssmodel.ICssRule;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.system.webgui.controltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.containercontrol.tabcontainer.ITabContainer;
import ch.nolix.systemapi.containercontrol.tabcontainer.ITabContainerStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

/**
 * @author Silvan Wyss
 */
public final class TabContainerCssBuilder extends AbstractControlCssBuilder<ITabContainer, ITabContainerStyle> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final ITabContainer control,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final ITabContainer control,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final ITabContainer control,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final ITabContainer control,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }
}

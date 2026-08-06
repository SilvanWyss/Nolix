/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.tabcontainer;

import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.web.cssmodel.ICssProperty;
import ch.nolix.baseapi.web.cssmodel.ICssRule;
import ch.nolix.system.webgui.controltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.control.tabcontainer.ITabContainer;
import ch.nolix.systemapi.control.tabcontainer.ITabContainerStyle;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

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
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final ITabContainer control,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final ITabContainer control,
    final ILinkedList<ICssProperty> list) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final ITabContainer control,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    // Does nothing.
  }
}

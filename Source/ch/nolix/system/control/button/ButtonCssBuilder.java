/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.button;

import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.web.cssmodel.ICssProperty;
import ch.nolix.baseapi.web.cssmodel.ICssRule;
import ch.nolix.system.webgui.controltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.control.button.IButton;
import ch.nolix.systemapi.control.button.IButtonStyle;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 */
public final class ButtonCssBuilder extends AbstractControlCssBuilder<IButton, IButtonStyle> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final IButton button,
    final ILinkedList<? super ICssRule> list) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final IButton button,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IButton control,
    final ILinkedList<ICssProperty> list) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IButton button,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    // Does nothing.
  }
}

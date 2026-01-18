/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webatomiccontrol.button;

import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.cssmodel.ICssProperty;
import ch.nolix.coreapi.web.cssmodel.ICssRule;
import ch.nolix.system.webgui.controltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.webatomiccontrol.button.IButton;
import ch.nolix.systemapi.webatomiccontrol.button.IButtonStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

/**
 * @author Silvan Wyss
 */
public final class ButtonCssBuilder extends AbstractControlCssBuilder<IButton, IButtonStyle> {
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final IButton button,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final IButton button,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IButton control,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IButton button,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }
}

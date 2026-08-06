/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.textbox;

import ch.nolix.base.web.cssmodel.CssProperty;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.web.cssmodel.ICssProperty;
import ch.nolix.baseapi.web.cssmodel.ICssRule;
import ch.nolix.system.webgui.controltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.control.textbox.ITextbox;
import ch.nolix.systemapi.control.textbox.ITextboxStyle;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 */
public final class TextboxCssBuilder extends AbstractControlCssBuilder<ITextbox, ITextboxStyle> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final ITextbox textbox,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final ITextbox textbox,
    final ILinkedList<? super ICssRule> list) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final ITextbox control,
    final ILinkedList<ICssProperty> list) {
    list.addAtEnd(CssProperty.withNameAndValue("outline", "none"));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final ITextbox textbox,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    // Does nothing.
  }
}

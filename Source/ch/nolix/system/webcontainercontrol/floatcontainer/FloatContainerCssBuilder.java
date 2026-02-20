/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webcontainercontrol.floatcontainer;

import ch.nolix.base.container.linkedlist.LinkedList;
import ch.nolix.base.web.cssmodel.CssProperty;
import ch.nolix.base.web.cssmodel.CssRule;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.web.css.CssPropertyNameCatalog;
import ch.nolix.baseapi.web.cssmodel.ICssProperty;
import ch.nolix.baseapi.web.cssmodel.ICssRule;
import ch.nolix.baseapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.system.webgui.controltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.webgui.main.ControlState;

/**
 * @author Silvan Wyss
 */
public final class FloatContainerCssBuilder
extends AbstractControlCssBuilder<FloatContainer, FloatContainerStyle> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final FloatContainer floatContainer,
    final ILinkedList<? super ICssRule> list) {
    list.addAtEnd(
      CssRule.withSelectorAndProperties(
        "> "
        + HtmlElementTypeCatalog.DIV,
        LinkedList.withElement(
          CssProperty.withNameAndValue(
            CssPropertyNameCatalog.FLOAT,
            "left"))));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final FloatContainer floatContainer,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    list.addAtEnd(
      CssRule.withSelectorAndProperties(
        "> "
        + HtmlElementTypeCatalog.DIV,
        LinkedList.withElement(
          CssProperty.withNameAndValue(
            CssPropertyNameCatalog.MARGIN,
            floatContainer.getStoredStyle().getChildControlMarginWhenHasState(state) + "px"))));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final FloatContainer control,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final FloatContainer floatContainer,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }
}

/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.horizontalstack;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.web.cssmodel.CssProperty;
import ch.nolix.base.web.cssmodel.CssRule;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.web.csscatalog.CssPropertyNameCatalog;
import ch.nolix.baseapi.web.cssmodel.ICssProperty;
import ch.nolix.baseapi.web.cssmodel.ICssRule;
import ch.nolix.baseapi.web.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.system.webgui.controltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.control.horizontalstack.IHorizontalStack;
import ch.nolix.systemapi.control.horizontalstack.IHorizontalStackStyle;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 */
public final class HorizontalStackCssBuilder
extends AbstractControlCssBuilder<IHorizontalStack, IHorizontalStackStyle> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final IHorizontalStack horizontalStack,
    final ILinkedList<? super ICssRule> list) {
    list.addAtEnd(
      CssRule.withSelectorAndProperties(
        "> " + HtmlElementTypeCatalog.DIV,
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
    final IHorizontalStack horizontalStack,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    list.addAtEnd(
      CssRule.withSelectorAndProperties(
        "> " + HtmlElementTypeCatalog.DIV,
        LinkedList.withElement(
          CssProperty.withNameAndValue(
            CssPropertyNameCatalog.MARGIN_RIGHT,
            horizontalStack.getStoredStyle().getChildControlMarginWhenHasState(state) + "px"))));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IHorizontalStack control,
    final ILinkedList<ICssProperty> list) {
    list.addAtEnd(
      CssProperty.withNameAndValue("display", "flex"),
      CssProperty.withNameAndValue("overflow", "auto"),
      HorizontalStackCssBuilderHelper.createCssPropertyForContentAlignmentOfHorizontalStack(control));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IHorizontalStack horizontalStack,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    // Does nothing.
  }
}

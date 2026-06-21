/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.verticalstack;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.web.cssmodel.CssProperty;
import ch.nolix.base.web.cssmodel.CssRule;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.web.css.CssPropertyNameCatalog;
import ch.nolix.baseapi.web.cssmodel.ICssProperty;
import ch.nolix.baseapi.web.cssmodel.ICssRule;
import ch.nolix.baseapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.system.webgui.controltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.containercontrol.verticalstack.IVerticalStack;
import ch.nolix.systemapi.containercontrol.verticalstack.IVerticalStackStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

/**
 * @author Silvan Wyss
 */
public final class VerticalStackCssBuilder
extends AbstractControlCssBuilder<IVerticalStack, IVerticalStackStyle> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final IVerticalStack verticalStack,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    list.addAtEnd(
      CssRule.withSelectorAndProperties(
        "> " + HtmlElementTypeCatalog.DIV,
        LinkedList.withElement(
          CssProperty.withNameAndValue(
            CssPropertyNameCatalog.MARGIN_BOTTOM,
            verticalStack.getStoredStyle().getChildControlMarginWhenHasState(state) + "px"))));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final IVerticalStack verticalStack,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IVerticalStack control,
    final ILinkedList<ICssProperty> list) {
    list.addAtEnd(
      CssProperty.withNameAndValue("display", "flex"),
      CssProperty.withNameAndValue("flex-direction", "column"),
      VerticalStackCssBuilderHelper.createCssPropertyForContentAlignmentOfVerticalStack(control));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IVerticalStack verticalStack,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }
}

/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.itemmenu;

import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.web.cssmodel.ICssRule;
import ch.nolix.system.webgui.controltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.control.itemmenu.IItemMenu;
import ch.nolix.systemapi.control.itemmenu.IItemMenuStyle;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 * @param <M> the type of the {@link IItemMenu}s of a
 *            {@link AbstractItemMenuCssBuilder}.
 * @param <S> the type of the {@link IItemMenuStyle}s of the {@link IItemMenu}s
 *            of a {@link AbstractItemMenuCssBuilder}.
 */
public abstract class AbstractItemMenuCssBuilder<M extends IItemMenu<M, S>, S extends IItemMenuStyle<S>>
extends AbstractControlCssBuilder<M, S> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected final void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final M itemMenu,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected final void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final M itemMenu,
    final ILinkedList<? super ICssRule> list) {
    // Does nothing.
  }
}

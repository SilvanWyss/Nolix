/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.itemmenu;

import ch.nolix.baseapi.css.cssmodel.ICssRule;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.system.webgui.controltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.atomiccontrol.itemmenu.IItemMenu;
import ch.nolix.systemapi.atomiccontrol.itemmenu.IItemMenuStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

/**
 * @author Silvan Wyss
 * @param <M> is the type of the {@link IItemMenu}s of a
 *            {@link AbstractItemMenuCssBuilder}.
 * @param <S> is the type of the {@link IItemMenuStyle}s of the
 *            {@link IItemMenu}s of a {@link AbstractItemMenuCssBuilder}.
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
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected final void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final M itemMenu,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }
}

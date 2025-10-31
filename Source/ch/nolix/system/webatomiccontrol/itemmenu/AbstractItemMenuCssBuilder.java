package ch.nolix.system.webatomiccontrol.itemmenu;

import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.cssmodel.ICssRule;
import ch.nolix.system.webgui.basecontroltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.webatomiccontrol.itemmenu.IItemMenu;
import ch.nolix.systemapi.webatomiccontrol.itemmenu.IItemMenuStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

public abstract class AbstractItemMenuCssBuilder<M extends IItemMenu<M, S>, S extends IItemMenuStyle<S>>
extends AbstractControlCssBuilder<M, S> {
  @Override
  protected final void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final M itemMenu,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  @Override
  protected final void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final M itemMenu,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }
}

package ch.nolix.system.webgui.itemmenu.base;

import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.basecontroltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.baseapi.IItemMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.baseapi.IItemMenuStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

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

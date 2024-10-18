package ch.nolix.system.webgui.itemmenu;

import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.basecontroltool.ControlCssBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public abstract class ItemMenuCssBuilder<IM extends IItemMenu<IM, IMS>, IMS extends IItemMenuStyle<IMS>>
extends ControlCssBuilder<IM, IMS> {

  @Override
  protected final void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final IM itemMenu,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  @Override
  protected final void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final IM itemMenu,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }
}

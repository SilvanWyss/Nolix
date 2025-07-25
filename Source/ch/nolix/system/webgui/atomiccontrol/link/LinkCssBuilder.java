package ch.nolix.system.webgui.atomiccontrol.link;

import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.css.ICssProperty;
import ch.nolix.coreapi.web.css.ICssRule;
import ch.nolix.system.webgui.basecontroltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.webgui.atomiccontrol.linkapi.ILink;
import ch.nolix.systemapi.webgui.atomiccontrol.linkapi.ILinkStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

public final class LinkCssBuilder extends AbstractControlCssBuilder<ILink, ILinkStyle> {

  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final ILink control,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final ILink control,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final ILink control,
    final ILinkedList<ICssProperty> list) {
    list.addAtEnd(CssProperty.withNameAndValue("text-decoration", "none"));
  }

  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final ILink control,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }
}

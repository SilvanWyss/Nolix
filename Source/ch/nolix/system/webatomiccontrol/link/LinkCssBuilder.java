package ch.nolix.system.webatomiccontrol.link;

import ch.nolix.core.web.cssmodel.CssProperty;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.cssmodel.ICssProperty;
import ch.nolix.coreapi.web.cssmodel.ICssRule;
import ch.nolix.system.webgui.controltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.webatomiccontrol.link.ILink;
import ch.nolix.systemapi.webatomiccontrol.link.ILinkStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

/**
 * @author Silvan Wyss
 */
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

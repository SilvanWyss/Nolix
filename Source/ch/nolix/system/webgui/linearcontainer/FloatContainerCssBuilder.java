package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalog;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalog;
import ch.nolix.system.webgui.basecontroltool.ControlCssBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class FloatContainerCssBuilder
extends ControlCssBuilder<FloatContainer, FloatContainerStyle> {

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

  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final FloatContainer control,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final FloatContainer floatContainer,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }
}

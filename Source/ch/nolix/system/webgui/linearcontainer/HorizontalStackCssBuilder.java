package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.system.webgui.basecontroltool.ControlCssBuilder;
import ch.nolix.systemapi.guiapi.contentalignmentproperty.VerticalContentAlignment;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStack;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStackStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class HorizontalStackCssBuilder
extends ControlCssBuilder<IHorizontalStack, IHorizontalStackStyle> {

  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final IHorizontalStack horizontalStack,
    final ILinkedList<? super ICssRule> list) {
    list.addAtEnd(
      CssRule.withSelectorAndProperties(
        "> " + HtmlElementTypeCatalogue.DIV,
        LinkedList.withElement(
          CssProperty.withNameAndValue(
            CssPropertyNameCatalogue.FLOAT,
            "left"))));
  }

  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final IHorizontalStack horizontalStack,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    list.addAtEnd(
      CssRule.withSelectorAndProperties(
        "> " + HtmlElementTypeCatalogue.DIV,
        LinkedList.withElement(
          CssProperty.withNameAndValue(
            CssPropertyNameCatalogue.MARGIN_RIGHT,
            horizontalStack.getStoredStyle().getChildControlMarginWhenHasState(state) + "px"))));
  }

  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IHorizontalStack control,
    final ILinkedList<ICssProperty> list) {
    list.addAtEnd(
      CssProperty.withNameAndValue("display", "flex"),
      CssProperty.withNameAndValue("overflow", "auto"),
      createCssPropertyForContentAlignmentOfControl(control));
  }

  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IHorizontalStack horizontalStack,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  private CssProperty createCssPropertyForContentAlignment(final VerticalContentAlignment contentAlignment) {
    return switch (contentAlignment) {
      case TOP ->
        CssProperty.withNameAndValue(CssPropertyNameCatalogue.ALIGN_ITEMS, "start");
      case CENTER ->
        CssProperty.withNameAndValue(CssPropertyNameCatalogue.ALIGN_ITEMS, "center");
      case BOTTOM ->
        CssProperty.withNameAndValue(CssPropertyNameCatalogue.ALIGN_ITEMS, "end");
    };
  }

  private CssProperty createCssPropertyForContentAlignmentOfControl(final IHorizontalStack control) {

    final var contentAlignment = control.getContentAlignment();

    return createCssPropertyForContentAlignment(contentAlignment);
  }
}

package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalog;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalog;
import ch.nolix.system.webgui.basecontroltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.guiapi.contentalignmentproperty.HorizontalContentAlignment;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStack;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStackStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class VerticalStackCssBuilder
extends AbstractControlCssBuilder<IVerticalStack, IVerticalStackStyle> {

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

  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final IVerticalStack verticalStack,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IVerticalStack control,
    final ILinkedList<ICssProperty> list) {
    list.addAtEnd(
      CssProperty.withNameAndValue("display", "flex"),
      CssProperty.withNameAndValue("flex-direction", "column"),
      createCssPropertyForContentAlignmentOfControl(control));
  }

  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IVerticalStack verticalStack,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  private CssProperty createCssPropertyForContentAlignment(final HorizontalContentAlignment contentAlignment) {
    return switch (contentAlignment) {
      case LEFT ->
        CssProperty.withNameAndValue(CssPropertyNameCatalog.ALIGN_ITEMS, "start");
      case CENTER ->
        CssProperty.withNameAndValue(CssPropertyNameCatalog.ALIGN_ITEMS, "center");
      case RIGHT ->
        CssProperty.withNameAndValue(CssPropertyNameCatalog.ALIGN_ITEMS, "end");
    };
  }

  private CssProperty createCssPropertyForContentAlignmentOfControl(final IVerticalStack control) {

    final var contentAlignment = control.getContentAlignment();

    return createCssPropertyForContentAlignment(contentAlignment);
  }
}

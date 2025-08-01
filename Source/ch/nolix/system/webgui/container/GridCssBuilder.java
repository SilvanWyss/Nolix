package ch.nolix.system.webgui.container;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.css.ICssProperty;
import ch.nolix.coreapi.web.css.ICssRule;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.system.webgui.basecontroltool.AbstractControlCssBuilder;
import ch.nolix.system.webgui.controltool.ControlCssValueTool;
import ch.nolix.systemapi.webgui.container.IGrid;
import ch.nolix.systemapi.webgui.container.IGridStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

public final class GridCssBuilder extends AbstractControlCssBuilder<IGrid, IGridStyle> {

  private static final ControlCssValueTool CONTROL_CSS_VALUE_TOOL = new ControlCssValueTool();

  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IGrid control,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IGrid control,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final IGrid control,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {

    final var style = control.getStoredStyle();
    final var gridThickness = style.getGridThicknessWhenHasState(state);
    final var gridcolor = style.getGridColorWhenHasState(state);
    final var childControlMargin = style.getChildControlMarginWhenHasState(state);

    list.addAtEnd(
      CssRule.withSelectorAndProperties(
        "table, th, td",
        ImmutableList.withElement(
          CssProperty.withNameAndValue("border-collapse", "collapse"),
          CssProperty.withNameAndValue("border", "solid " + gridThickness + "px"),
          CssProperty.withNameAndValue("border-color",
            CONTROL_CSS_VALUE_TOOL.getCssValueFromColor(gridcolor)))));

    list.addAtEnd(
      CssRule.withSelectorAndProperties(
        HtmlElementTypeCatalog.TD,
        ImmutableList.withElement(
          CssProperty.withNameAndValue("padding", childControlMargin + "px"))));
  }

  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final IGrid control,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }
}

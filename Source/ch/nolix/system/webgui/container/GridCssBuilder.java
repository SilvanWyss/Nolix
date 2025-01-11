package ch.nolix.system.webgui.container;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalog;
import ch.nolix.system.webgui.basecontroltool.ControlCssBuilder;
import ch.nolix.system.webgui.controltool.ControlCssValueTool;
import ch.nolix.systemapi.webguiapi.containerapi.IGrid;
import ch.nolix.systemapi.webguiapi.containerapi.IGridStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class GridCssBuilder extends ControlCssBuilder<IGrid, IGridStyle> {

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

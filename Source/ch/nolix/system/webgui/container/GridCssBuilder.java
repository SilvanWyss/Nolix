//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.system.webgui.basecontrolservice.ControlCssBuilder;
import ch.nolix.system.webgui.controltool.ControlCssValueTool;
import ch.nolix.systemapi.webguiapi.containerapi.IGrid;
import ch.nolix.systemapi.webguiapi.containerapi.IGridStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class GridCssBuilder extends ControlCssBuilder<IGrid, IGridStyle> {

  //constant
  private static final ControlCssValueTool CONTROL_CSS_VALUE_TOOL = new ControlCssValueTool();

  //method
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IGrid control,
    final LinkedList<CssProperty> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IGrid control,
    final ControlState state,
    final LinkedList<ICssProperty> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final IGrid control,
    final ControlState state,
    final LinkedList<? super ICssRule> list) {

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
        HtmlElementTypeCatalogue.TD,
        ImmutableList.withElement(
          CssProperty.withNameAndValue("padding", childControlMargin + "px"))));
  }

  //method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final IGrid control,
    final LinkedList<? super ICssRule> list) {
    //Does nothing.
  }
}

//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.system.webgui.basecontrolservice.ControlCssBuilder;
import ch.nolix.systemapi.guiapi.structureproperty.HorizontalContentAlignment;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStack;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStackStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class VerticalStackCssBuilder
    extends ControlCssBuilder<IVerticalStack, IVerticalStackStyle> {

  //method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
      final IVerticalStack verticalStack,
      final ControlState state,
      final LinkedList<? super ICssRule> list) {
    list.addAtEnd(
        CssRule.withSelectorAndProperties(
            "> " + HtmlElementTypeCatalogue.DIV,
            LinkedList.withElement(
                CssProperty.withNameAndValue(
                    CssPropertyNameCatalogue.MARGIN_BOTTOM,
                    verticalStack.getStoredStyle().getChildControlMarginWhenHasState(state) + "px"))));
  }

  //method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
      final IVerticalStack verticalStack,
      final LinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
      final IVerticalStack control,
      final LinkedList<CssProperty> list) {
    list.addAtEnd(
        CssProperty.withNameAndValue("display", "flex"),
        CssProperty.withNameAndValue("flex-direction", "column"),
        createCssPropertyForContentAlignmentOfControl(control));
  }

  //method
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
      final IVerticalStack verticalStack,
      final ControlState state,
      final LinkedList<ICssProperty> list) {
    //Does nothing.
  }

  //method
  private CssProperty createCssPropertyForContentAlignment(final HorizontalContentAlignment contentAlignment) {
    return switch (contentAlignment) {
      case LEFT ->
        CssProperty.withNameAndValue(CssPropertyNameCatalogue.ALIGN_ITEMS, "start");
      case CENTER ->
        CssProperty.withNameAndValue(CssPropertyNameCatalogue.ALIGN_ITEMS, "center");
      case RIGHT ->
        CssProperty.withNameAndValue(CssPropertyNameCatalogue.ALIGN_ITEMS, "end");
    };
  }

  //method
  private CssProperty createCssPropertyForContentAlignmentOfControl(final IVerticalStack control) {

    final var contentAlignment = control.getContentAlignment();

    return createCssPropertyForContentAlignment(contentAlignment);
  }
}

//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.system.webgui.basecontroltool.ControlCssBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class FloatContainerCssBuilder
extends ControlCssBuilder<FloatContainer, FloatContainerStyle> {

  //method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final FloatContainer floatContainer,
    final ILinkedList<? super ICssRule> list) {
    list.addAtEnd(
      CssRule.withSelectorAndProperties(
        "> "
        + HtmlElementTypeCatalogue.DIV,
        LinkedList.withElement(
          CssProperty.withNameAndValue(
            CssPropertyNameCatalogue.FLOAT,
            "left"))));
  }

  //method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final FloatContainer floatContainer,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    list.addAtEnd(
      CssRule.withSelectorAndProperties(
        "> "
        + HtmlElementTypeCatalogue.DIV,
        LinkedList.withElement(
          CssProperty.withNameAndValue(
            CssPropertyNameCatalogue.MARGIN,
            floatContainer.getStoredStyle().getChildControlMarginWhenHasState(state) + "px"))));
  }

  //method
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final FloatContainer control,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final FloatContainer floatContainer,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }
}

//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.Css;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public final class WebGuiCssBuilder {

  //method
  public Css createCssForWebGui(final IWebGui<?> webGui) {

    final var cssRules = new LinkedList<ICssRule>();

    fillUpCssRulesOfWebGuiIntoList(webGui, cssRules);

    return Css.withRules(cssRules);
  }

  //method
  private void fillUpCssRulesOfWebGuiIntoList(
    final IWebGui<?> webGui,
    final LinkedList<ICssRule> cssRules) {

    cssRules.addAtEnd(
      CssRule.withSelectorAndProperties(
        HtmlElementTypeCatalogue.BODY,
        ImmutableList.withElement(CssProperty.withNameAndValue(CssPropertyNameCatalogue.MARGIN, "0px"))));

    cssRules.addAtEnd(
      CssRule.withSelectorAndProperty(
        "#root",
        CssProperty.withNameAndValue(CssPropertyNameCatalogue.MIN_HEIGHT, "100vh")));

    if (webGui.hasBackground()) {
      cssRules.addAtEnd(
        CssRule.withSelectorAndProperties(
          "#root",
          webGui.getBackground().toCssProperties()));
    }

    fillUpCssRulesOfLayersOfWebGuiIntoList(webGui, cssRules);
  }

  //method
  private void fillUpCssRulesOfLayersOfWebGuiIntoList(
    final IWebGui<?> webGui,
    final LinkedList<ICssRule> cssRules) {
    for (final var l : webGui.getStoredLayers()) {
      fillUpCssRulesOfLayerIntoList(l, cssRules);
    }
  }

  //method
  private void fillUpCssRulesOfLayerIntoList(final ILayer<?> layer, final LinkedList<ICssRule> cssRules) {

    cssRules.addAtEnd(layer.getCssRule());

    for (final var c : layer.getStoredControls()) {
      cssRules.addAtEnd(c.getCssRules());
    }
  }
}

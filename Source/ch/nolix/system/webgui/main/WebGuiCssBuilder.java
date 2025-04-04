package ch.nolix.system.webgui.main;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.Css;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalog;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalog;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

public final class WebGuiCssBuilder {

  public Css createCssForWebGui(final IWebGui<?> webGui) {

    final ILinkedList<ICssRule> cssRules = LinkedList.createEmpty();

    fillUpCssRulesOfWebGuiIntoList(webGui, cssRules);

    return Css.withRules(cssRules);
  }

  private void fillUpCssRulesOfWebGuiIntoList(
    final IWebGui<?> webGui,
    final ILinkedList<ICssRule> cssRules) {

    cssRules.addAtEnd(
      CssRule.withSelectorAndProperties(
        HtmlElementTypeCatalog.BODY,
        ImmutableList.withElement(CssProperty.withNameAndValue(CssPropertyNameCatalog.MARGIN, "0px"))));

    cssRules.addAtEnd(
      CssRule.withSelectorAndProperty(
        "#root",
        CssProperty.withNameAndValue(CssPropertyNameCatalog.MIN_HEIGHT, "100vh")));

    if (webGui.hasBackground()) {
      cssRules.addAtEnd(
        CssRule.withSelectorAndProperties(
          "#root",
          webGui.getBackground().toCssProperties()));
    }

    fillUpCssRulesOfLayersOfWebGuiIntoList(webGui, cssRules);
  }

  private void fillUpCssRulesOfLayersOfWebGuiIntoList(
    final IWebGui<?> webGui,
    final ILinkedList<ICssRule> cssRules) {
    for (final var l : webGui.getStoredLayers()) {
      fillUpCssRulesOfLayerIntoList(l, cssRules);
    }
  }

  private void fillUpCssRulesOfLayerIntoList(final ILayer<?> layer, final ILinkedList<ICssRule> cssRules) {

    cssRules.addAtEnd(layer.getCssRule());

    for (final var c : layer.getStoredControls()) {
      cssRules.addAtEnd(c.getCssRules());
    }
  }
}

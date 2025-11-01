package ch.nolix.system.webgui.main;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.cssmodel.Css;
import ch.nolix.core.web.cssmodel.CssProperty;
import ch.nolix.core.web.cssmodel.CssRule;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.css.CssPropertyNameCatalog;
import ch.nolix.coreapi.web.cssmodel.ICssRule;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.IWebGui;

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
        ImmutableList.withElements(CssProperty.withNameAndValue(CssPropertyNameCatalog.MARGIN, "0px"))));

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

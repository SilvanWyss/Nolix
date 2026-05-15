/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.main;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.container.linkedlist.LinkedList;
import ch.nolix.base.web.cssmodel.Css;
import ch.nolix.base.web.cssmodel.CssProperty;
import ch.nolix.base.web.cssmodel.CssRule;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.web.css.CssPropertyNameCatalog;
import ch.nolix.baseapi.web.cssmodel.ICssRule;
import ch.nolix.baseapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 */
public final class WebGuiCssBuilder {
  private WebGuiCssBuilder() {
  }

  public static Css createCssForWebGui(final IWebGui<?> webGui) {
    final ILinkedList<ICssRule> cssRules = LinkedList.createEmpty();

    fillUpCssRulesOfWebGuiIntoList(webGui, cssRules);

    return Css.withRules(cssRules);
  }

  private static void fillUpCssRulesOfWebGuiIntoList(
    final IWebGui<?> webGui,
    final ILinkedList<ICssRule> cssRules) {
    cssRules.addAtEnd(
      CssRule.withSelectorAndProperties(
        HtmlElementTypeCatalog.BODY,
        ImmutableList.withElements(CssProperty.withNameAndValue(CssPropertyNameCatalog.MARGIN, "0px"))));

    cssRules.addAtEnd(
      CssRule.withSelectorAndProperties(
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

  private static void fillUpCssRulesOfLayersOfWebGuiIntoList(
    final IWebGui<?> webGui,
    final ILinkedList<ICssRule> cssRules) {
    for (final var l : webGui.getStoredLayers()) {
      fillUpCssRulesOfLayerIntoList(l, cssRules);
    }
  }

  private static void fillUpCssRulesOfLayerIntoList(final ILayer layer, final ILinkedList<ICssRule> cssRules) {
    cssRules.addAtEnd(layer.getCssRule());

    for (final var c : layer.getStoredControls()) {
      cssRules.addAtEnd(c.getCssRules());
    }
  }
}

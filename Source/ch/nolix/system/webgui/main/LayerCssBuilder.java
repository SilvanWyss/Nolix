/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.main;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.foundation.math.NumberComparator;
import ch.nolix.base.web.cssmodel.CssProperty;
import ch.nolix.base.web.cssmodel.CssRule;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.web.csscatalog.CssPropertyNameCatalog;
import ch.nolix.baseapi.web.cssmodel.ICssProperty;
import ch.nolix.systemapi.gui.guiproperty.ContentAlignment;
import ch.nolix.systemapi.webgui.main.ILayer;

/**
 * @author Silvan Wyss
 */
public final class LayerCssBuilder {
  private LayerCssBuilder() {
  }

  public static CssRule getCssRuleForLayer(final ILayer layer) {
    return CssRule.withSelectorAndProperties(getCssSelectorForLayer(layer), getCssPropertiesForLayer(layer));
  }

  private static String getCssSelectorForLayer(final ILayer layer) {
    return ("#" + layer.getInternalId());
  }

  private static ExtendedIterable<ICssProperty> getCssPropertiesForLayer(final ILayer layer) {
    final ILinkedList<ICssProperty> cssProperties = LinkedList.createEmpty();

    if (layer.getStoredParentGui().getStoredTopLayer() == layer) {
      cssProperties.addAtEnd(CssProperty.withNameAndValue("position", "absolute"));
    } else {
      cssProperties.addAtEnd(CssProperty.withNameAndValue("position", "fixed"));
    }

    if (!NumberComparator.isOne(layer.getOpacity())) {
      cssProperties.addAtEnd(CssProperty.withNameAndValue(CssPropertyNameCatalog.OPACITY, layer.getOpacity()));
    }

    cssProperties.addAtEnd(
      getZIndexCssPropertyForLayer(layer),
      CssProperty.withNameAndValue(CssPropertyNameCatalog.MIN_WIDTH, "100vw"),
      CssProperty.withNameAndValue(CssPropertyNameCatalog.MIN_HEIGHT, "100vh"),
      CssProperty.withNameAndValue(CssPropertyNameCatalog.DISPLAY, "flex"),
      getJustifyContentCssPropertyForLayer(layer),
      getAlignItemsCssPropertyForLayer(layer));

    if (layer.hasBackground()) {
      cssProperties.addAtEnd(layer.getBackground().toCssProperties());
    }

    return cssProperties;
  }

  private static CssProperty getZIndexCssPropertyForLayer(final ILayer layer) {
    return CssProperty.withNameAndValue(CssPropertyNameCatalog.Z_INDEX, getCssZIndexForLayer(layer));
  }

  private static int getCssZIndexForLayer(final ILayer layer) {
    if (!layer.belongsToGui()) {
      return 0;
    }

    return layer.getStoredParentGui().getStoredLayers().getOneBasedIndexOfFirstOccurrenceOf(layer);
  }

  private static CssProperty getJustifyContentCssPropertyForLayer(final ILayer layer) {
    return getJustifyContentCssPropertyForContentAlignment(layer.getContentAlignment());
  }

  private static CssProperty getJustifyContentCssPropertyForContentAlignment(final ContentAlignment contentAlignment) {
    return switch (contentAlignment) {
      case TOP_LEFT, LEFT, BOTTOM_LEFT ->
        CssProperty.withNameAndValue(CssPropertyNameCatalog.JUSTIFY_CONTENT, "left");
      case TOP, CENTER, BOTTOM ->
        CssProperty.withNameAndValue(CssPropertyNameCatalog.JUSTIFY_CONTENT, "center");
      case TOP_RIGHT, RIGHT, BOTTOM_RIGHT ->
        CssProperty.withNameAndValue(CssPropertyNameCatalog.JUSTIFY_CONTENT, "right");
      default ->
        throw InvalidArgumentException.forArgument(contentAlignment);
    };
  }

  private static CssProperty getAlignItemsCssPropertyForLayer(final ILayer layer) {
    return getAlignItemsCssPropertyForContentAlignment(layer.getContentAlignment());
  }

  private static CssProperty getAlignItemsCssPropertyForContentAlignment(final ContentAlignment contentAlignment) {
    return switch (contentAlignment) {
      case BOTTOM, BOTTOM_LEFT, BOTTOM_RIGHT ->
        CssProperty.withNameAndValue(CssPropertyNameCatalog.ALIGN_ITEMS, "end");
      case CENTER, LEFT, RIGHT ->
        CssProperty.withNameAndValue(CssPropertyNameCatalog.ALIGN_ITEMS, "center");
      case TOP, TOP_LEFT, TOP_RIGHT ->
        CssProperty.withNameAndValue(CssPropertyNameCatalog.ALIGN_ITEMS, "start");
      default ->
        throw InvalidArgumentException.forArgument(contentAlignment);
    };
  }
}

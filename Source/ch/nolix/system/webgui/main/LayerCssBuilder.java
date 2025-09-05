package ch.nolix.system.webgui.main;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.independent.math.NumberComparator;
import ch.nolix.core.web.cssmodel.CssProperty;
import ch.nolix.core.web.cssmodel.CssRule;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.css.CssPropertyNameCatalog;
import ch.nolix.coreapi.web.cssmodel.ICssProperty;
import ch.nolix.systemapi.gui.box.ContentAlignment;
import ch.nolix.systemapi.webgui.main.ILayer;

public final class LayerCssBuilder {
  public CssRule getCssRuleForLayer(final ILayer<?> layer) {
    return CssRule.withSelectorAndProperties(getCssSelectorForLayer(layer), getCssPropertiesForLayer(layer));
  }

  private String getCssSelectorForLayer(final ILayer<?> layer) {
    return ("#" + layer.getInternalId());
  }

  private IContainer<ICssProperty> getCssPropertiesForLayer(final ILayer<?> layer) {
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

  private CssProperty getZIndexCssPropertyForLayer(final ILayer<?> layer) {
    return CssProperty.withNameAndValue(CssPropertyNameCatalog.Z_INDEX, getCssZIndexForLayer(layer));
  }

  private int getCssZIndexForLayer(final ILayer<?> layer) {
    if (!layer.belongsToGui()) {
      return 0;
    }

    return layer.getStoredParentGui().getStoredLayers().getOneBasedIndexOfFirstOccurrenceOf(layer);
  }

  private CssProperty getJustifyContentCssPropertyForLayer(final ILayer<?> layer) {
    return getJustifyContentCssPropertyForContentAlignment(layer.getContentAlignment());
  }

  private CssProperty getJustifyContentCssPropertyForContentAlignment(final ContentAlignment contentAlignment) {
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

  private CssProperty getAlignItemsCssPropertyForLayer(final ILayer<?> layer) {
    return getAlignItemsCssPropertyForContentAlignment(layer.getContentAlignment());
  }

  private CssProperty getAlignItemsCssPropertyForContentAlignment(final ContentAlignment contentAlignment) {
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

//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.systemapi.guiapi.structureproperty.ContentAlignment;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;

//class
public final class LayerCssBuilder {

  //method
  public CssRule getCssRuleForLayer(final ILayer<?> layer) {
    return CssRule.withSelectorAndProperties(getCssSelectorForLayer(layer), getCssPropertiesForLayer(layer));
  }

  //method
  private String getCssSelectorForLayer(final ILayer<?> layer) {
    return ("#" + layer.getInternalId());
  }

  //method
  private IContainer<ICssProperty> getCssPropertiesForLayer(final ILayer<?> layer) {

    final var cssProperties = new LinkedList<ICssProperty>();

    if (layer.getStoredParentGui().getStoredTopLayer() == layer) {
      cssProperties.addAtEnd(CssProperty.withNameAndValue("position", "absolute"));
    } else {
      cssProperties.addAtEnd(CssProperty.withNameAndValue("position", "fixed"));
    }

    if (layer.getOpacity() != 1.0) {
      cssProperties.addAtEnd(CssProperty.withNameAndValue(CssPropertyNameCatalogue.OPACITY, layer.getOpacity()));
    }

    cssProperties.addAtEnd(
        getZIndexCssPropertyForLayer(layer),
        CssProperty.withNameAndValue(CssPropertyNameCatalogue.MIN_WIDTH, "100vw"),
        CssProperty.withNameAndValue(CssPropertyNameCatalogue.MIN_HEIGHT, "100vh"),
        CssProperty.withNameAndValue(CssPropertyNameCatalogue.DISPLAY, "flex"),
        getJustifyContentCssPropertyForLayer(layer),
        getAlignItemsCssPropertyForLayer(layer));

    if (layer.hasBackground()) {
      cssProperties.addAtEnd(layer.getBackground().toCssProperties());
    }

    return cssProperties;
  }

  //method
  private CssProperty getZIndexCssPropertyForLayer(final ILayer<?> layer) {
    return CssProperty.withNameAndValue(CssPropertyNameCatalogue.Z_INDEX, getCssZIndexForLayer(layer));
  }

  //method
  private int getCssZIndexForLayer(final ILayer<?> layer) {

    if (!layer.belongsToGui()) {
      return 0;
    }

    return layer.getStoredParentGui().getStoredLayers().get1BasedIndexOfFirstOccuranceOf(layer);
  }

  //method
  private CssProperty getJustifyContentCssPropertyForLayer(final ILayer<?> layer) {
    return getJustifyContentCssPropertyForContentAlignment(layer.getContentAlignment());
  }

  //method
  private CssProperty getJustifyContentCssPropertyForContentAlignment(final ContentAlignment contentAlignment) {
    return switch (contentAlignment) {
      case TOP_LEFT, LEFT, BOTTOM_LEFT ->
        CssProperty.withNameAndValue(CssPropertyNameCatalogue.JUSTIFY_CONTENT, "left");
      case TOP, CENTER, BOTTOM ->
        CssProperty.withNameAndValue(CssPropertyNameCatalogue.JUSTIFY_CONTENT, "center");
      case TOP_RIGHT, RIGHT, BOTTOM_RIGHT ->
        CssProperty.withNameAndValue(CssPropertyNameCatalogue.JUSTIFY_CONTENT, "right");
      default ->
        throw InvalidArgumentException.forArgument(contentAlignment);
    };
  }

  //method
  private CssProperty getAlignItemsCssPropertyForLayer(final ILayer<?> layer) {
    return getAlignItemsCssPropertyForContentAlignment(layer.getContentAlignment());
  }

  //method
  private CssProperty getAlignItemsCssPropertyForContentAlignment(final ContentAlignment contentAlignment) {
    return switch (contentAlignment) {
      case BOTTOM, BOTTOM_LEFT, BOTTOM_RIGHT ->
        CssProperty.withNameAndValue(CssPropertyNameCatalogue.ALIGN_ITEMS, "end");
      case CENTER, LEFT, RIGHT ->
        CssProperty.withNameAndValue(CssPropertyNameCatalogue.ALIGN_ITEMS, "center");
      case TOP, TOP_LEFT, TOP_RIGHT ->
        CssProperty.withNameAndValue(CssPropertyNameCatalogue.ALIGN_ITEMS, "start");
      default ->
        throw InvalidArgumentException.forArgument(contentAlignment);
    };
  }
}

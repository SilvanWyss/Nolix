//package declaration
package ch.nolix.system.webgui.main;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.CssAlignItemsCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CssJustifyContentCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.systemapi.guiapi.structureproperty.ContentPosition;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;

//class
public final class LayerCssRuleCreator {
	
	//static attribute
	public static final LayerCssRuleCreator INSTANCE = new LayerCssRuleCreator();
	
	//constructor
	private LayerCssRuleCreator() {}
	
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
		
		if (layer.getOpacity() != 1.0) {
			cssProperties.addAtEnd(CssProperty.withNameAndValue(CssPropertyNameCatalogue.OPACITY, layer.getOpacity()));
		}
		
		cssProperties.addAtEnd(
			CssProperty.withNameAndValue("position", "absolute"),
			getZIndexCssPropertyForLayer(layer),
			CssProperty.withNameAndValue(CssPropertyNameCatalogue.MARGIN, "0px"),
			CssProperty.withNameAndValue(CssPropertyNameCatalogue.WIDTH, "100vw"),
			CssProperty.withNameAndValue(CssPropertyNameCatalogue.HEIGHT, "100vh"),
			CssProperty.withNameAndValue(CssPropertyNameCatalogue.DISPLAY, "flex"),
			getJustifyContentCssPropertyForLayer(layer),
			getAlignItemsCssPropertyForLayer(layer)
		);
		
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
		
		return layer.getOriParentGui().getOriLayers().get1BasedIndexOfFirstOccuranceOf(layer);
	}
	
	//method
	private CssProperty getJustifyContentCssPropertyForLayer(final ILayer<?> layer) {
		return getJustifyContentCssPropertyForContentPosition(layer.getContentPosition());
	}
	
	//method
	private CssProperty getJustifyContentCssPropertyForContentPosition(final ContentPosition contentPosition) {
		switch (contentPosition) {
			case TOP_LEFT, LEFT, BOTTOM_LEFT:
				return CssProperty.withNameAndValue(CssPropertyNameCatalogue.JUSTIFY_CONTENT, CssJustifyContentCatalogue.LEFT);
			case TOP, CENTER, BOTTOM:
				return CssProperty.withNameAndValue(CssPropertyNameCatalogue.JUSTIFY_CONTENT, CssJustifyContentCatalogue.CENTER);
			case TOP_RIGHT, RIGHT, BOTTOM_RIGHT:
				return CssProperty.withNameAndValue(CssPropertyNameCatalogue.JUSTIFY_CONTENT, CssJustifyContentCatalogue.RIGHT);
			default:
				throw InvalidArgumentException.forArgument(contentPosition);
		}
	}
	
	//method
	private CssProperty getAlignItemsCssPropertyForLayer(final ILayer<?> layer) {
		return getAlignItemsCssPropertyForContentPosition(layer.getContentPosition());
	}
	
	//method
	private CssProperty getAlignItemsCssPropertyForContentPosition(final ContentPosition contentPosition) {
		switch (contentPosition) {
			case BOTTOM, BOTTOM_LEFT, BOTTOM_RIGHT:
				return CssProperty.withNameAndValue(CssPropertyNameCatalogue.ALIGN_ITEMS, CssAlignItemsCatalogue.END);
			case CENTER,LEFT, RIGHT:
				return CssProperty.withNameAndValue(CssPropertyNameCatalogue.ALIGN_ITEMS, CssAlignItemsCatalogue.CENTER);
			case TOP, TOP_LEFT, TOP_RIGHT:
				return CssProperty.withNameAndValue(CssPropertyNameCatalogue.ALIGN_ITEMS, CssAlignItemsCatalogue.START);
			default:
				throw InvalidArgumentException.forArgument(contentPosition); 
		}
	}
}

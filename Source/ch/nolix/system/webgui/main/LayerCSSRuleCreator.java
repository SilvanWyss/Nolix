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
public final class LayerCSSRuleCreator {
	
	//static attribute
	public static final LayerCSSRuleCreator INSTANCE = new LayerCSSRuleCreator();
	
	//constructor
	private LayerCSSRuleCreator() {}
	
	//method
	public CssRule getCSSRuleForLayer(final ILayer<?> layer) {
		return CssRule.withSelectorAndProperties(getCSSSelectorForLayer(layer), getCSSPropertiesForLayer(layer));
	}
	
	//method
	private String getCSSSelectorForLayer(final ILayer<?> layer) {
		return ("#" + layer.getInternalId());
	}
	
	//method
	private IContainer<ICssProperty> getCSSPropertiesForLayer(final ILayer<?> layer) {
		
		final var lCSSProperties = new LinkedList<ICssProperty>();
		
		if (layer.getOpacity() != 1.0) {
			lCSSProperties.addAtEnd(CssProperty.withNameAndValue(CssPropertyNameCatalogue.OPACITY, layer.getOpacity()));
		}
		
		lCSSProperties.addAtEnd(
			CssProperty.withNameAndValue("position", "absolute"),
			getZIndexCSSPropertyForLayer(layer),
			CssProperty.withNameAndValue(CssPropertyNameCatalogue.MARGIN, "0px"),
			CssProperty.withNameAndValue(CssPropertyNameCatalogue.WIDTH, "100vw"),
			CssProperty.withNameAndValue(CssPropertyNameCatalogue.HEIGHT, "100vh"),
			CssProperty.withNameAndValue(CssPropertyNameCatalogue.DISPLAY, "flex"),
			getJustifyContentCSSPropertyForLayer(layer),
			getAlignItemsCSSPropertyForLayer(layer)
		);
		
		if (layer.hasBackground()) {
			lCSSProperties.addAtEnd(layer.getBackground().toCSSProperties());
		}
		
		return lCSSProperties;
	}
	
	//method
	private CssProperty getZIndexCSSPropertyForLayer(final ILayer<?> layer) {
		return CssProperty.withNameAndValue(CssPropertyNameCatalogue.Z_INDEX, getCSSZIndexForLayer(layer));
	}
	
	//method
	private int getCSSZIndexForLayer(final ILayer<?> layer) {
		
		if (!layer.belongsToGui()) {
			return 0;
		}
		
		return layer.getOriParentGui().getOriLayers().get1BasedIndexOfFirstOccuranceOf(layer);
	}
	
	//method
	private CssProperty getJustifyContentCSSPropertyForLayer(final ILayer<?> layer) {
		return getJustifyContentCSSPropertyForContentPosition(layer.getContentPosition());
	}
	
	//method
	private CssProperty getJustifyContentCSSPropertyForContentPosition(final ContentPosition contentPosition) {
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
	private CssProperty getAlignItemsCSSPropertyForLayer(final ILayer<?> layer) {
		return getAlignItemsCSSPropertyForContentPosition(layer.getContentPosition());
	}
	
	//method
	private CssProperty getAlignItemsCSSPropertyForContentPosition(final ContentPosition contentPosition) {
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

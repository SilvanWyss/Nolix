//package declaration
package ch.nolix.system.webgui.main;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.core.web.css.CSSRule;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.CSSAlignItemsCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CSSJustifyContentCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CSSPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICSSProperty;
import ch.nolix.systemapi.guiapi.structureproperty.ContentPosition;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;

//class
public final class LayerCSSRuleCreator {
	
	//static attribute
	public static final LayerCSSRuleCreator INSTANCE = new LayerCSSRuleCreator();
	
	//constructor
	private LayerCSSRuleCreator() {}
	
	//method
	public CSSRule getCSSRuleForLayer(final ILayer<?> layer) {
		return CSSRule.withSelectorAndProperties(getCSSSelectorForLayer(layer), getCSSPropertiesForLayer(layer));
	}
	
	//method
	private String getCSSSelectorForLayer(final ILayer<?> layer) {
		return ("#" + layer.getFixedId());
	}
	
	//method
	private IContainer<ICSSProperty> getCSSPropertiesForLayer(final ILayer<?> layer) {
		
		final var lCSSProperties = new LinkedList<ICSSProperty>();
		
		if (layer.getOpacity() != 1.0) {
			lCSSProperties.addAtEnd(CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.OPACITY, layer.getOpacity()));
		}
		
		lCSSProperties.addAtEnd(
			CSSProperty.withNameAndValue("position", "absolute"),
			getZIndexCSSPropertyForLayer(layer),
			CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.MARGIN, "0px"),
			CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.WIDTH, "100vw"),
			CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.HEIGHT, "100vh"),
			CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.DISPLAY, "flex"),
			getJustifyContentCSSPropertyForLayer(layer),
			getAlignItemsCSSPropertyForLayer(layer)
		);
		
		if (layer.hasBackground()) {
			lCSSProperties.addAtEnd(layer.getBackground().toCSSProperties());
		}
		
		return lCSSProperties;
	}
	
	//method
	private CSSProperty getZIndexCSSPropertyForLayer(final ILayer<?> layer) {
		return CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.Z_INDEX, getCSSZIndexForLayer(layer));
	}
	
	//method
	private int getCSSZIndexForLayer(final ILayer<?> layer) {
		
		if (!layer.belongsToGUI()) {
			return 0;
		}
		
		return layer.getRefParentGUI().getRefLayers().get1BasedIndexOfFirstOccuranceOf(layer);
	}
	
	//method
	private CSSProperty getJustifyContentCSSPropertyForLayer(final ILayer<?> layer) {
		return getJustifyContentCSSPropertyForContentPosition(layer.getContentPosition());
	}
	
	//method
	private CSSProperty getJustifyContentCSSPropertyForContentPosition(final ContentPosition contentPosition) {
		switch (contentPosition) {
			case TOP_LEFT, LEFT, BOTTOM_LEFT:
				return CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.JUSTIFY_CONTENT, CSSJustifyContentCatalogue.LEFT);
			case TOP, CENTER,BOTTOM:
				return CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.JUSTIFY_CONTENT, CSSJustifyContentCatalogue.CENTER);
			case TOP_RIGHT, RIGHT, BOTTOM_RIGHT:
				return CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.JUSTIFY_CONTENT, CSSJustifyContentCatalogue.RIGHT);
			default:
				throw InvalidArgumentException.forArgument(contentPosition);
		}
	}
	
	//method
	private CSSProperty getAlignItemsCSSPropertyForLayer(final ILayer<?> layer) {
		return getAlignItemsCSSPropertyForContentPosition(layer.getContentPosition());
	}
	
	//method
	private CSSProperty getAlignItemsCSSPropertyForContentPosition(final ContentPosition contentPosition) {
		switch (contentPosition) {
			case BOTTOM, BOTTOM_LEFT, BOTTOM_RIGHT:
				return CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.ALIGN_ITEMS, CSSAlignItemsCatalogue.END);
			case CENTER,LEFT, RIGHT:
				return CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.ALIGN_ITEMS, CSSAlignItemsCatalogue.CENTER);
			case TOP, TOP_LEFT, TOP_RIGHT:
				return CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.ALIGN_ITEMS, CSSAlignItemsCatalogue.START);
			default:
				throw InvalidArgumentException.forArgument(contentPosition); 
		}
	}
}

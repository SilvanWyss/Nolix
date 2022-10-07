//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.core.web.css.CSSRule;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.CSSAlignItems;
import ch.nolix.coreapi.webapi.cssapi.CSSJustifyContent;
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
		
		lCSSProperties.addAtEnd(
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
		
		return layer.getRefParentGUI().getRefLayers().getIndexOfFirstOccurrenceOf(layer);
	}
	
	//method
	private CSSProperty getJustifyContentCSSPropertyForLayer(final ILayer<?> layer) {
		return getJustifyContentCSSPropertyForContentPosition(layer.getContentPosition());
	}
	
	//method
	private CSSProperty getJustifyContentCSSPropertyForContentPosition(final ContentPosition contentPosition) {
		switch (contentPosition) {
			case TOP_LEFT:
			case LEFT:
			case BOTTOM_LEFT:
				return CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.JUSTIFY_CONTENT, CSSJustifyContent.LEFT);
			case TOP:
			case CENTER:
			case BOTTOM:
				return CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.JUSTIFY_CONTENT, CSSJustifyContent.CENTER);
			case TOP_RIGHT:
			case RIGHT:
			case BOTTOM_RIGHT:
				return CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.JUSTIFY_CONTENT, CSSJustifyContent.RIGHT);
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
			case BOTTOM:
			case BOTTOM_LEFT:
			case BOTTOM_RIGHT:
				return CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.ALIGN_ITEMS, CSSAlignItems.END);
			case CENTER:
			case LEFT:
			case RIGHT:
				return CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.ALIGN_ITEMS, CSSAlignItems.CENTER);
			case TOP:
			case TOP_LEFT:
			case TOP_RIGHT:
				return CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.ALIGN_ITEMS, CSSAlignItems.START);
			default:
				throw InvalidArgumentException.forArgument(contentPosition); 
		}
	}
}

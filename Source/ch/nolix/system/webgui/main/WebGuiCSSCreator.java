//package declaration
package ch.nolix.system.webgui.main;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CSS;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.core.web.css.CSSRule;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.CSSPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public final class WebGuiCSSCreator {
	
	//static attribute
	public static final WebGuiCSSCreator INSTANCE = new WebGuiCSSCreator();
	
	//method
	public CSS createCSSForWebGui(final IWebGui<?> webGui) {
		
		final var lCSSRules = new LinkedList<ICSSRule<?>>();
		
		fillUpCSSRulesOfWebGuiIntoList(webGui, lCSSRules);
		
		return CSS.withRules(lCSSRules);
	}
	
	//method
	private void fillUpCSSRulesOfWebGuiIntoList(
		final IWebGui<?> webGui,
		final LinkedList<ICSSRule<?>> lCSSRules
	) {
		
		lCSSRules.addAtEnd(
			CSSRule.withSelectorAndProperties(
				HtmlElementTypeCatalogue.BODY,
				getBodyCSSPropertiesFromWebGui(webGui)
			)	
		);
		
		fillUpCSSRulesOfLayersOfWebGuiIntoList(webGui, lCSSRules);
	}
	
	//method
	private IContainer<ICSSProperty> getBodyCSSPropertiesFromWebGui(final IWebGui<?> webGui) {
		
		final var bodyCSSProperties = new LinkedList<ICSSProperty>();
		
		bodyCSSProperties.addAtEnd(
			CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.MARGIN, "0px"),
			CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.WIDTH, "100vw"),
			CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.HEIGHT, "100vh")
		);
		
		if (webGui.hasBackground()) {
			bodyCSSProperties.addAtEnd(webGui.getBackground().toCSSProperties());
		}
		
		return bodyCSSProperties;
	}

	//method
	private void fillUpCSSRulesOfLayersOfWebGuiIntoList(
		final IWebGui<?> webGui,
		final LinkedList<ICSSRule<?>> lCSSRules
	) {
		for (final var l : webGui.getOriLayers()) {
			fillUpCSSRulesOfLayerIntoList(l, lCSSRules);
		}
	}
	
	//method
	private void fillUpCSSRulesOfLayerIntoList(final ILayer<?> layer, final LinkedList<ICSSRule<?>> lCSSRules) {
		
		lCSSRules.addAtEnd(layer.getCSSRule());
		
		for (final var c : layer.getOriControls()) {
			lCSSRules.addAtEnd(c.getCSSRules());
		}
	}
}

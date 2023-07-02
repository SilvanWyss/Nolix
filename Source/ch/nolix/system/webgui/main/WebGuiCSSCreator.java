//package declaration
package ch.nolix.system.webgui.main;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.Css;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public final class WebGuiCSSCreator {
	
	//static attribute
	public static final WebGuiCSSCreator INSTANCE = new WebGuiCSSCreator();
	
	//method
	public Css createCSSForWebGui(final IWebGui<?> webGui) {
		
		final var lCSSRules = new LinkedList<ICssRule<?>>();
		
		fillUpCSSRulesOfWebGuiIntoList(webGui, lCSSRules);
		
		return Css.withRules(lCSSRules);
	}
	
	//method
	private void fillUpCSSRulesOfWebGuiIntoList(
		final IWebGui<?> webGui,
		final LinkedList<ICssRule<?>> lCSSRules
	) {
		
		lCSSRules.addAtEnd(
			CssRule.withSelectorAndProperties(
				HtmlElementTypeCatalogue.BODY,
				getBodyCSSPropertiesFromWebGui(webGui)
			)	
		);
		
		fillUpCSSRulesOfLayersOfWebGuiIntoList(webGui, lCSSRules);
	}
	
	//method
	private IContainer<ICssProperty> getBodyCSSPropertiesFromWebGui(final IWebGui<?> webGui) {
		
		final var bodyCSSProperties = new LinkedList<ICssProperty>();
		
		bodyCSSProperties.addAtEnd(
			CssProperty.withNameAndValue(CssPropertyNameCatalogue.MARGIN, "0px"),
			CssProperty.withNameAndValue(CssPropertyNameCatalogue.WIDTH, "100vw"),
			CssProperty.withNameAndValue(CssPropertyNameCatalogue.HEIGHT, "100vh")
		);
		
		if (webGui.hasBackground()) {
			bodyCSSProperties.addAtEnd(webGui.getBackground().toCSSProperties());
		}
		
		return bodyCSSProperties;
	}

	//method
	private void fillUpCSSRulesOfLayersOfWebGuiIntoList(
		final IWebGui<?> webGui,
		final LinkedList<ICssRule<?>> lCSSRules
	) {
		for (final var l : webGui.getOriLayers()) {
			fillUpCSSRulesOfLayerIntoList(l, lCSSRules);
		}
	}
	
	//method
	private void fillUpCSSRulesOfLayerIntoList(final ILayer<?> layer, final LinkedList<ICssRule<?>> lCSSRules) {
		
		lCSSRules.addAtEnd(layer.getCSSRule());
		
		for (final var c : layer.getOriControls()) {
			lCSSRules.addAtEnd(c.getCSSRules());
		}
	}
}

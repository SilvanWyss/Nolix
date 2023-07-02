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
public final class WebGuiCssCreator {
	
	//static attribute
	public static final WebGuiCssCreator INSTANCE = new WebGuiCssCreator();
	
	//method
	public Css createCssForWebGui(final IWebGui<?> webGui) {
		
		final var cssRules = new LinkedList<ICssRule<?>>();
		
		fillUpCssRulesOfWebGuiIntoList(webGui, cssRules);
		
		return Css.withRules(cssRules);
	}
	
	//method
	private void fillUpCssRulesOfWebGuiIntoList(
		final IWebGui<?> webGui,
		final LinkedList<ICssRule<?>> cssRules
	) {
		
		cssRules.addAtEnd(
			CssRule.withSelectorAndProperties(
				HtmlElementTypeCatalogue.BODY,
				getBodyCssPropertiesFromWebGui(webGui)
			)	
		);
		
		fillUpCssRulesOfLayersOfWebGuiIntoList(webGui, cssRules);
	}
	
	//method
	private IContainer<ICssProperty> getBodyCssPropertiesFromWebGui(final IWebGui<?> webGui) {
		
		final var bodyCssProperties = new LinkedList<ICssProperty>();
		
		bodyCssProperties.addAtEnd(
			CssProperty.withNameAndValue(CssPropertyNameCatalogue.MARGIN, "0px"),
			CssProperty.withNameAndValue(CssPropertyNameCatalogue.WIDTH, "100vw"),
			CssProperty.withNameAndValue(CssPropertyNameCatalogue.HEIGHT, "100vh")
		);
		
		if (webGui.hasBackground()) {
			bodyCssProperties.addAtEnd(webGui.getBackground().toCssProperties());
		}
		
		return bodyCssProperties;
	}

	//method
	private void fillUpCssRulesOfLayersOfWebGuiIntoList(
		final IWebGui<?> webGui,
		final LinkedList<ICssRule<?>> cssRules
	) {
		for (final var l : webGui.getOriLayers()) {
			fillUpCssRulesOfLayerIntoList(l, cssRules);
		}
	}
	
	//method
	private void fillUpCssRulesOfLayerIntoList(final ILayer<?> layer, final LinkedList<ICssRule<?>> cssRules) {
		
		cssRules.addAtEnd(layer.getCssRule());
		
		for (final var c : layer.getOriControls()) {
			cssRules.addAtEnd(c.getCssRules());
		}
	}
}

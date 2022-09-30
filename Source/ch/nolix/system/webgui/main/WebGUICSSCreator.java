//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.css.CSS;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.core.web.css.CSSRule;
import ch.nolix.core.web.html.HTMLElementTypeCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.CSSPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGUI;

//class
public final class WebGUICSSCreator {
	
	//static attribute
	public static final WebGUICSSCreator INSTANCE = new WebGUICSSCreator();
	
	//method
	public CSS createCSSForWebGUI(final IWebGUI<?> webGUI) {
		
		final var lCSSRules = new LinkedList<ICSSRule<?>>();
		
		fillUpCSSRulesOfWebGUIIntoList(webGUI, lCSSRules);
		
		return CSS.withRules(lCSSRules);
	}
	
	//method
	private void fillUpCSSRulesOfWebGUIIntoList(
		final IWebGUI<?> webGUI,
		final LinkedList<ICSSRule<?>> lCSSRules
	) {
		
		lCSSRules.addAtEnd(
			CSSRule.withSelectorAndProperties(
				HTMLElementTypeCatalogue.BODY,
				getBodyCSSPropertiesFromWebGUI(webGUI)
			)	
		);
		
		fillUpCSSRulesFromControlsOfWebGUIIntoList(webGUI, lCSSRules);
	}
	
	//method
	private void fillUpCSSRulesFromControlsOfWebGUIIntoList(
		final IWebGUI<?> webGUI,
		final LinkedList<ICSSRule<?>> lCSSRules
	) {
		for (final var c : webGUI.getRefControls()) {
			fillUpCSSRulesFromControlIntoList(lCSSRules, c);
		}
	}
	
	//method
	private void fillUpCSSRulesFromControlIntoList(
		final LinkedList<ICSSRule<?>> lCSSRules,
		final IControl<?, ?> control
	) {
		
		final var lCSSRuleCreator = control.getCSSRuleCreator();
		
		lCSSRules.addAtEnd(lCSSRuleCreator.getCSSRuleForState(ControlState.BASE));
		lCSSRules.addAtEnd(lCSSRuleCreator.getCSSRuleForState(ControlState.HOVER));
		lCSSRules.addAtEnd(lCSSRuleCreator.getCSSRuleForState(ControlState.FOCUS));
	}
	
	//method
	private IContainer<ICSSProperty> getBodyCSSPropertiesFromWebGUI(final IWebGUI<?> webGUI) {
		
		final var bodyCSSProperties = new LinkedList<ICSSProperty>();
		
		bodyCSSProperties.addAtEnd(
			CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.MARGIN, "0px"),
			CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.WIDTH, "100vw"),
			CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.HEIGHT, "100vh")
		);
		
		bodyCSSProperties.addAtEnd(webGUI.getBackground().toCSSProperties());
		
		return bodyCSSProperties;
	}
}

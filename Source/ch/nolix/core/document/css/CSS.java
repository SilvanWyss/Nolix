//package declaration
package ch.nolix.core.document.css;

//own imports
import ch.nolix.core.document.html.HTMLAttribute;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.cssapi.ICSS;
import ch.nolix.coreapi.documentapi.cssapi.ICSSRule;

//class
public final class CSS implements ICSS<CSSRule, HTMLAttribute> {
	
	//static method
	public static CSS withRules(final IContainer<ICSSRule<?>> rules) {
		return new CSS(rules);
	}
	
	//multi-attribute
	private final IContainer<CSSRule> rules;
	
	//constructor
	private CSS(final IContainer<ICSSRule<?>> rules) {
		this.rules = rules.to(CSSRule::fromCSSRule);
	}
	
	//method
	@Override
	public IContainer<CSSRule> getRefRules() {
		return rules;
	}
}

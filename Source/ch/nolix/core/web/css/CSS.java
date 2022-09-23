//package declaration
package ch.nolix.core.web.css;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.ICSS;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;

//class
public final class CSS implements ICSS<CSSRule, CSSProperty> {
	
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
	
	//method
	@Override
	public String toString() {
		return GlobalStringHelper.getInBraces(getRefRules().toConcatenatedString());
	}
}

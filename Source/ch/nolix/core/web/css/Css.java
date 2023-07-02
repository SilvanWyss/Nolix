//package declaration
package ch.nolix.core.web.css;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.ICss;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;

//class
public final class Css implements ICss<CssRule, CssProperty> {
	
	//static method
	public static Css withRules(final IContainer<ICssRule<?>> rules) {
		return new Css(rules);
	}
	
	//multi-attribute
	private final IContainer<CssRule> rules;
	
	//constructor
	private Css(final IContainer<ICssRule<?>> rules) {
		this.rules = rules.to(CssRule::fromCssRule);
	}
	
	//method
	@Override
	public IContainer<CssRule> getOriRules() {
		return rules;
	}
	
	//method
	@Override
	public String toString() {
		return GlobalStringHelper.getInBraces(toStringWithoutEnclosingBrackets());
	}
	
	//method
	@Override
	public String toStringWithoutEnclosingBrackets() {
		return getOriRules().toConcatenatedString();
	}
}

//package declaration
package ch.nolix.core.web.css;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.ICSSProperty;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;

//class
public final class CSSRule implements ICSSRule<CSSProperty> {
	
	//static method
	public static CSSRule fromCSSRule(final ICSSRule<?> pCSSRule) {
		return withSelectorsAndProperties(pCSSRule.getSelectors(), pCSSRule.getRefProperties());
	}
	
	//static method
	public static CSSRule withSelectorAndProperties(
		final String selector,
		final IContainer<? extends ICSSProperty> properties
	) {
		return new CSSRule(ImmutableList.withElements(selector), properties);
	}
	
	//static method
	public static CSSRule withSelectorsAndProperties(
		final IContainer<String> selectors,
		final IContainer<? extends ICSSProperty> properties
	) {
		return new CSSRule(selectors, properties);
	}
	
	//multi-attribute
	private final IContainer<String> selectors;
	
	//multi-attribute
	private final IContainer<CSSProperty> properties;
	
	//constructor
	private CSSRule(
		final IContainer<String> selectors,
		final IContainer<? extends ICSSProperty> properties
	) {
		this.properties = properties.to(CSSProperty::fromCSSProperty);
		this.selectors = ImmutableList.forIterable(selectors);
	}
	
	//method
	@Override
	public IContainer<CSSProperty> getRefProperties() {
		return properties;
	}
	
	//method
	@Override
	public IContainer<String> getSelectors() {
		return selectors;
	}
	
	//method
	@Override
	public String toString() {
		return
		getSelectors().toString(',') + GlobalStringHelper.getInBraces(getRefProperties().toConcatenatedString());
	}
}

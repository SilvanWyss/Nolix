//package declaration
package ch.nolix.core.web.css;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;

//class
public final class CssRule implements ICssRule {
	
	//static method
	public static CssRule fromCssRule(final ICssRule cssRule) {
		return withSelectorsAndProperties(cssRule.getSelectors(), cssRule.getProperties());
	}
	
	//static method
	public static CssRule withSelectorAndProperties(
		final String selector,
		final IContainer<? extends ICssProperty> properties
	) {
		return new CssRule(ImmutableList.withElement(selector), properties);
	}
	
	//static method
	public static CssRule withSelectorsAndProperties(
		final IContainer<String> selectors,
		final IContainer<? extends ICssProperty> properties
	) {
		return new CssRule(selectors, properties);
	}
	
	//multi-attribute
	private final IContainer<String> selectors;
	
	//multi-attribute
	private final IContainer<CssProperty> properties;
	
	//constructor
	private CssRule(
		final IContainer<String> selectors,
		final IContainer<? extends ICssProperty> properties
	) {
		this.properties = properties.to(CssProperty::fromCssProperty);
		this.selectors = ImmutableList.forIterable(selectors);
	}
	
	//method
	@Override
	public IContainer<CssProperty> getProperties() {
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
		getSelectors().toStringWithSeparator(',')
		+ GlobalStringHelper.getInBraces(getProperties().toConcatenatedString());
	}
}

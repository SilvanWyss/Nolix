//package declaration
package ch.nolix.core.document.css;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.html.HTMLAttribute;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.cssapi.ICSSRule;
import ch.nolix.coreapi.documentapi.htmlapi.IHTMLAttribute;

//class
public final class CSSRule implements ICSSRule<HTMLAttribute> {
	
	//static method
	public static CSSRule fromCSSRule(final ICSSRule<?> pCSSRule) {
		return withSelectorsAndAttributes(pCSSRule.getSelectors(), pCSSRule.getRefAttributes());
	}
	
	//static method
	public static CSSRule withSelectorsAndAttributes(
		final IContainer<String> selectors,
		final IContainer<? extends IHTMLAttribute> attributes
	) {
		return new CSSRule(selectors, attributes);
	}
	
	//multi-attribute
	private final IContainer<String> selectors;
	
	//multi-attribute
	private final IContainer<HTMLAttribute> attributes;
	
	//constructor
	private CSSRule(
		final IContainer<String> selectors,
		final IContainer<? extends IHTMLAttribute> attributes
	) {
		this.attributes = attributes.to(HTMLAttribute::fromHTMLAttribute);
		this.selectors = ImmutableList.forIterable(selectors);
	}
	
	//method
	@Override
	public IContainer<HTMLAttribute> getRefAttributes() {
		return attributes;
	}
	
	//method
	@Override
	public IContainer<String> getSelectors() {
		return selectors;
	}
}

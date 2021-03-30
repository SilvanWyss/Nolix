//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.elementapi.IMutableElement;

//class
public abstract class BaseExtensionElement<EE extends IMutableElement<EE>> extends Property {
	
	//attributes
	private final String attributePrefix;
	private EE internalExtensionElement;
	
	//constructor
	BaseExtensionElement(
		final String attributePrefix,
		final EE internalExtensionElement
	) {
		
		Validator.assertThat(attributePrefix).thatIsNamed("attribute prefix").isNotBlank();
		Validator.assertThat(internalExtensionElement).thatIsNamed("intenral extension element").isNotNull();
		
		this.attributePrefix = attributePrefix;
		this.internalExtensionElement = internalExtensionElement;
	}
	
	//method
	public String getAttributePrefix() {
		return attributePrefix;
	}
	
	//method
	public EE getExtensionElement() {
		return internalExtensionElement;
	}
	
	//method declaration
	public abstract boolean isExchangable();
	
	//method
	@Override
	void addOrChangeAttribute(final BaseNode attribute) {
		internalExtensionElement.addOrChangeAttribute(
			Node.withHeaderAndAttributes(
				attribute.getHeader().substring(attributePrefix.length()),
				attribute.getRefAttributes()
			)
		);
	}
	
	//method
	@Override
	void fillUpAttributesInto(final LinkedList<Node> list) {
		for (final var a : internalExtensionElement.getAttributes()) {
			list.addAtEnd(
				Node.withHeaderAndAttributes(attributePrefix + a.getHeader(), a.getRefAttributes())
			);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	final String getCode() {
		return attributePrefix;
	}
	
	//visibility-reduced
	final void internalSetExtensionElement(final EE extensionElement) {
		
		if (!isExchangable()) {
			throw new InvalidArgumentException(this, "is not exchangable");
		}
		
		internalExtensionElement = extensionElement;
	}
}

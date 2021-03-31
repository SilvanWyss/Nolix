//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
public abstract class BaseExtensionElement<E extends Element<E>> extends Property {
	
	//attributes
	private final String attributePrefix;
	private E internalExtensionElement;
	
	//constructor
	BaseExtensionElement(
		final String attributePrefix,
		final E internalExtensionElement
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
	public E getExtensionElement() {
		return internalExtensionElement;
	}
	
	//method declaration
	public abstract boolean isExchangable();
	
	//method
	@Override
	protected final boolean addedOrChangedAttribute(final BaseNode attribute) {
		
		if (attribute.hasHeader() && attribute.getHeader().startsWith(attributePrefix)) {
			
			internalExtensionElement.addOrChangeAttribute(
				Node.withHeaderAndAttributes(
					attribute.getHeader().substring(attributePrefix.length()),
					attribute.getRefAttributes()
				)
			);
			
			return true;
		}
		
		return false;
	}
	
	//method
	@Override
	protected void fillUpAttributesInto(final LinkedList<Node> list) {
		for (final var a : internalExtensionElement.getAttributes()) {
			list.addAtEnd(
				Node.withHeaderAndAttributes(attributePrefix + a.getHeader(), a.getRefAttributes())
			);
		}
	}
	
	//method
	final void internalSetExtensionElement(final E extensionElement) {
		
		if (!isExchangable()) {
			throw new InvalidArgumentException(this, "is not exchangable");
		}
		
		internalExtensionElement = extensionElement;
	}
}

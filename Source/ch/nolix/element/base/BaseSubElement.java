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
public abstract class BaseSubElement<ME extends IMutableElement<ME>> extends Property {
	
	//attributes
	private final String attributePrefix;
	private ME internalSubElement;
	
	//constructor
	BaseSubElement(
		final String attributePrefix,
		final ME internalExtensionElement
	) {
		
		Validator.assertThat(attributePrefix).thatIsNamed("attribute prefix").isNotBlank();
		Validator.assertThat(internalExtensionElement).thatIsNamed("intenral extension element").isNotNull();
		
		this.attributePrefix = attributePrefix;
		this.internalSubElement = internalExtensionElement;
	}
	
	//method
	public String getAttributePrefix() {
		return attributePrefix;
	}
	
	//method
	public ME getSubElement() {
		return internalSubElement;
	}
	
	//method declaration
	public abstract boolean isExchangable();
	
	//method
	@Override
	protected final boolean addedOrChangedAttribute(final BaseNode attribute) {
		
		if (attribute.hasHeader() && attribute.getHeader().startsWith(attributePrefix)) {
			
			internalSubElement.addOrChangeAttribute(
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
		for (final var a : internalSubElement.getAttributes()) {
			list.addAtEnd(
				Node.withHeaderAndAttributes(attributePrefix + a.getHeader(), a.getRefAttributes())
			);
		}
	}
	
	//method
	final void internalSetSubElement(final ME subElement) {
		
		if (!isExchangable()) {
			throw new InvalidArgumentException(this, "is not exchangable");
		}
		
		internalSubElement = subElement;
	}
}

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
	protected BaseSubElement(
		final String attributePrefix,
		final ME internalSubElement
	) {
		
		Validator.assertThat(attributePrefix).thatIsNamed("attribute prefix").isNotBlank();
		
		this.attributePrefix = attributePrefix;
		internalSetSubElement(internalSubElement);
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
	protected final void internalSetSubElement(final ME internalSubElement) {
		
		Validator.assertThat(internalSubElement).thatIsNamed("sub element").isNotNull();
		
		if (this.internalSubElement != null && isExchangable()) {
			throw new InvalidArgumentException(this, "is not exchangable");
		}
		
		this.internalSubElement = internalSubElement;
	}
}

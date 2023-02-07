//package declaration
package ch.nolix.system.element.mutableelement;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.listapi.IMutableList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.mainuniversalapi.IMutableElement;

//class
public abstract class BaseSubElement<ME extends IMutableElement> extends Property {
	
	//attributes
	private final String attributePrefix;
	private ME internalSubElement;
	
	//constructor
	protected BaseSubElement(
		final String attributePrefix,
		final ME internalSubElement
	) {
		
		GlobalValidator.assertThat(attributePrefix).thatIsNamed("attribute prefix").isNotBlank();
		
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
	protected final boolean addedOrChangedAttribute(final INode<?> attribute) {
		
		if (attribute.hasHeader() && attribute.getHeader().startsWith(attributePrefix)) {
			
			internalSubElement.addOrChangeAttribute(
				Node.withHeaderAndChildNodes(
					attribute.getHeader().substring(attributePrefix.length()),
					attribute.getRefChildNodes()
				)
			);
			
			return true;
		}
		
		return false;
	}
	
	//method
	@Override
	protected void fillUpAttributesInto(final IMutableList<INode<?>> list) {
		for (final var a : internalSubElement.getAttributes()) {
			list.addAtEnd(
				Node.withHeaderAndChildNodes(attributePrefix + a.getHeader(), a.getRefChildNodes())
			);
		}
	}
	
	//method
	protected final void internalSetSubElement(final ME internalSubElement) {
		
		GlobalValidator.assertThat(internalSubElement).thatIsNamed("sub element").isNotNull();
		
		if (this.internalSubElement != null && !isExchangable()) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not exchangable");
		}
		
		this.internalSubElement = internalSubElement;
	}
}

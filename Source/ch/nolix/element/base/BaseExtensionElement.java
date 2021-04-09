//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.elementapi.IRespondingMutableElement;

//class
public abstract class BaseExtensionElement<E extends IRespondingMutableElement<E>> extends Property {
	
	//attribute
	private E internalExtensionElement;
	
	//constructor
	protected BaseExtensionElement(final E internalExtensionElement) {
		internalSetExtensionElement(internalExtensionElement);
	}
	
	//method
	public abstract boolean isExchangable();
	
	//method
	@Override
	protected final boolean addedOrChangedAttribute(final BaseNode attribute) {
		return internalExtensionElement.addedOrChangedAttribute(attribute);
	}
	
	//method
	@Override
	protected final void fillUpAttributesInto(LinkedList<Node> list) {
		internalExtensionElement.fillUpAttributesInto(list);
	}
	
	//method
	protected final void internalSetExtensionElement(final E internalExtensionElement) {
		
		Validator.assertThat(internalExtensionElement).thatIsNamed("extension element").isNotNull();
		
		if (this.internalExtensionElement != null && !isExchangable()) {
			throw new InvalidArgumentException(this, "is not exchangable");
		}
		
		this.internalExtensionElement = internalExtensionElement;
	}
}

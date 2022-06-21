//package declaration
package ch.nolix.system.element;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.elementapi.mainuniversalapi.IRespondingMutableElement;

//class
public abstract class BaseExtensionElement<E extends IRespondingMutableElement<E>> extends Property {
	
	//attribute
	private E internalExtensionElement;
	
	//constructor
	protected BaseExtensionElement(final E internalExtensionElement) {
		internalSetExtensionElement(internalExtensionElement);
	}
	
	//method
	public final E getExtensionElement() {
		return internalExtensionElement;
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
		
		GlobalValidator.assertThat(internalExtensionElement).thatIsNamed("extension element").isNotNull();
		
		if (this.internalExtensionElement != null && !isExchangable()) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not exchangable");
		}
		
		this.internalExtensionElement = internalExtensionElement;
	}
}

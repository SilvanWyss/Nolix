//package declaration
package ch.nolix.system.element.mutableelement;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.listapi.IMutableList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
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
	protected final boolean addedOrChangedAttribute(final INode<?> attribute) {
		return internalExtensionElement.addedOrChangedAttribute(attribute);
	}
	
	//method
	@Override
	protected final void fillUpAttributesInto(IMutableList<INode<?>> list) {
		list.addAtEnd(internalExtensionElement.getAttributes());
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

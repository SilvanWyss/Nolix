//package declaration
package ch.nolix.system.element.property;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IBooleanGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;

//class
public final class MutableOptionalSpecificationValueExtractor extends Property {
	
	//attribute
	private final String name;
	
	//attribute
	private final IElementTaker<INode<?>> setter;
	
	//attribute
	private final IBooleanGetter valuePresenceChecker;
	
	//attribute
	private final IElementGetter<Node> getter;
	
	//constructor
	public MutableOptionalSpecificationValueExtractor(
		final String name,
		final IElementTaker<INode<?>> setter,
		final IBooleanGetter valuePresenceChecker,
		final IElementGetter<Node> getter
	) {
		
		GlobalValidator.assertThat(name).thatIsNamed(PascalCaseCatalogue.NAME).isNotBlank();
		GlobalValidator.assertThat(setter).thatIsNamed("setter").isNotNull();
		GlobalValidator.assertThat(valuePresenceChecker).thatIsNamed("value presence checker").isNotNull();
		GlobalValidator.assertThat(getter).thatIsNamed("getter").isNotNull();
		
		this.name = name;
		this.setter = setter;
		this.valuePresenceChecker = valuePresenceChecker;
		this.getter = getter;
	}
	
	//method
	public String getName() {
		return name;
	}
	
	//method
	@Override
	public boolean addedOrChangedAttribute(final INode<?> attribute) {
		
		if (attribute.hasHeader(getName())) {
			setter.run(attribute);
			return true;
		}
		
		return false;
	}
	
	//method
	@Override
	public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
		if (valuePresenceChecker.getOutput()) {
			list.addAtEnd(getter.getOutput());
		}
	}
}

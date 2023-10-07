//package declaration
package ch.nolix.system.element.property;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IBooleanGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;

//class
public final class MutableOptionalValueExtractor<V> extends Property {
	
	//attribute
	private final String name;
	
	//attribute
	private final IElementTaker<V> setter;
	
	//attribute
	private final IBooleanGetter valuePresenceChecker;
	
	//attribute
	private final IElementGetter<V> getter;
	
	//attribute
	private final IElementTakerElementGetter<INode<?>, V> valueCreator;
	
	//attribute
	private final IElementTakerElementGetter<V, INode<?>> specificationCreator;
	
	//constructor
	public MutableOptionalValueExtractor(
		final String name,
		final IElementTaker<V> setter,
		final IBooleanGetter valuePresenceChecker,
		final IElementGetter<V> getter,
		final IElementTakerElementGetter<INode<?>, V> valueCreator,
		final IElementTakerElementGetter<V, INode<?>> specificationCreator
	) {
		
		GlobalValidator.assertThat(name).thatIsNamed(PascalCaseCatalogue.NAME).isNotBlank();
		GlobalValidator.assertThat(setter).thatIsNamed("setter").isNotNull();
		GlobalValidator.assertThat(valuePresenceChecker).thatIsNamed("value presence checker").isNotNull();
		GlobalValidator.assertThat(getter).thatIsNamed("getter").isNotNull();
		GlobalValidator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		GlobalValidator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();
		
		this.name = name;
		this.setter = setter;
		this.valuePresenceChecker = valuePresenceChecker;
		this.getter = getter;
		this.valueCreator = valueCreator;
		this.specificationCreator = specificationCreator;
	}
	
	//method
	public String getName() {
		return name;
	}
	
	//method
	@Override
	public boolean addedOrChangedAttribute(final INode<?> attribute) {
		
		if (attribute.hasHeader(getName())) {
			setter.run(valueCreator.getOutput(attribute));
			return true;
		}
		
		return false;
	}
	
	//method
	@Override
	public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
		if (valuePresenceChecker.getOutput()) {
			list.addAtEnd(specificationCreator.getOutput(getter.getOutput()));
		}
	}
}

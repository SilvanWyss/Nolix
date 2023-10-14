//package declaration
package ch.nolix.system.element.property;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;
import ch.nolix.systemapi.elementapi.propertyapi.IProperty;

//class
public final class CatchingProperty<V> implements IProperty, Named {
	
	//attribute
	private final String name;
	
	//attribute
	private final IElementTaker<V> setter;
	
	//attribute
	private final IElementTakerElementGetter<INode<?>, V> valueCreator;
	
	//constructor
	public CatchingProperty(
		final String name,
		final IElementTaker<V> setter,
		final IElementTakerElementGetter<INode<?>, V> valueCreator
	) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		GlobalValidator.assertThat(setter).thatIsNamed("setter").isNotNull();
		GlobalValidator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		
		this.name = name;
		this.setter = setter;
		this.valueCreator = valueCreator;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
	
	//method
	@Override
	public boolean addedOrChangedAttribute(INode<?> attribute) {
		
		if (hasName(attribute.getHeader())) {
			setter.run(valueCreator.getOutput(attribute));
			return true;
		}
		
		return false;
	}
	
	//method
	@Override
	public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
		//Does nothing.
	}
}

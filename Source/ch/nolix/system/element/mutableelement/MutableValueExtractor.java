//package declaration
package ch.nolix.system.element.mutableelement;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IMutableList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;

//class
public final class MutableValueExtractor<V> extends Property {
	
	//attributes
	private final String name;
	private final IElementTaker<V> setter;
	private final IElementGetter<V> getter;
	private final IElementTakerElementGetter<INode<?>, V> valueCreator;
	private final IElementTakerElementGetter<V, INode<?>> specificationCreator;
	
	//constructor
	public MutableValueExtractor(
		final String name,
		final IElementTaker<V> setter,
		final IElementGetter<V> getter,
		final IElementTakerElementGetter<INode<?>, V> valueCreator,
		final IElementTakerElementGetter<V, INode<?>> specificationCreator
	) {
		
		GlobalValidator.assertThat(name).thatIsNamed(PascalCaseCatalogue.NAME).isNotBlank();
		GlobalValidator.assertThat(setter).thatIsNamed("setter").isNotNull();
		GlobalValidator.assertThat(getter).thatIsNamed("getter").isNotNull();
		GlobalValidator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		GlobalValidator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();
		
		this.name = name;
		this.setter = setter;
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
	protected boolean addedOrChangedAttribute(final INode<?> attribute) {
		
		if (attribute.hasHeader(getName())) {
			setter.run(valueCreator.getOutput(attribute));
			return true;
		}
		
		return false;
	}
	
	//method
	@Override
	protected void fillUpAttributesInto(final IMutableList<INode<?>> list) {
		list.addAtEnd(specificationCreator.getOutput(getter.getOutput()));
	}
}

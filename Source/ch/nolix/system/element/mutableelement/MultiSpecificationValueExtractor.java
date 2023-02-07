//package declaration
package ch.nolix.system.element.mutableelement;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.IMutableList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;

//class
public final class MultiSpecificationValueExtractor extends Property {
	
	//attributes
	private final String name;
	private final IElementTaker<INode<?>> adder;
	private final IElementGetter<IContainer<INode<?>>> getter;
	
	//constructor
	public MultiSpecificationValueExtractor(
		final String name,
		final IElementTaker<INode<?>> adder,
		final IElementGetter<IContainer<INode<?>>> getter
	) {
		
		GlobalValidator.assertThat(name).thatIsNamed(PascalCaseCatalogue.NAME).isNotBlank();
		GlobalValidator.assertThat(adder).thatIsNamed("adder").isNotNull();
		GlobalValidator.assertThat(getter).thatIsNamed("getter").isNotNull();
				
		this.name = name;
		this.adder = adder;
		this.getter = getter;
	}
	
	//method
	public String getName() {
		return name;
	}
	
	//method
	@Override
	protected boolean addedOrChangedAttribute(final INode<?> attribute) {
		
		if (attribute.hasHeader(getName())) {
			adder.run(attribute);
			return true;
		}
		
		return false;
	}
	
	//method
	@Override
	protected void fillUpAttributesInto(final IMutableList<INode<?>> list) {
		list.addAtEnd(getter.getOutput());
	}
}

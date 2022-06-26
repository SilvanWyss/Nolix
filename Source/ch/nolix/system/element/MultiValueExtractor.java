//package declaration
package ch.nolix.system.element;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.coreapi.functionuniversalapi.IElementGetter;
import ch.nolix.coreapi.functionuniversalapi.IElementTaker;
import ch.nolix.coreapi.functionuniversalapi.IElementTakerElementGetter;

//class
public final class MultiValueExtractor<V> extends Property {
	
	//attributes
	private final String name;
	private final IElementTaker<V> adder;
	private final IElementGetter<IContainer<V>> getter;
	private final IElementTakerElementGetter<BaseNode<?>, V> valueCreator;
	private final IElementTakerElementGetter<V, Node> specificationCreator;
	
	//constructor
	public MultiValueExtractor(
		final String name,
		final IElementTaker<V> adder,
		final IElementGetter<IContainer<V>> getter,
		final IElementTakerElementGetter<BaseNode<?>, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		
		GlobalValidator.assertThat(name).thatIsNamed(PascalCaseCatalogue.NAME).isNotBlank();
		GlobalValidator.assertThat(adder).thatIsNamed("adder").isNotNull();
		GlobalValidator.assertThat(getter).thatIsNamed("getter").isNotNull();
		GlobalValidator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		GlobalValidator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();
		
		this.name = name;
		this.adder = adder;
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
	protected boolean addedOrChangedAttribute(final BaseNode<?> attribute) {
		
		if (attribute.hasHeader(getName())) {
			adder.run(valueCreator.getOutput(attribute));
			return true;
		}
		
		return false;
	}
	
	//method
	@Override
	protected void fillUpAttributesInto(final LinkedList<Node> list) {
		for (final var v : getter.getOutput()) {
			list.addAtEnd(specificationCreator.getOutput(v));
		}
	}
}

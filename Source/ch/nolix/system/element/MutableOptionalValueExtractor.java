//package declaration
package ch.nolix.system.element;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.functionuniversalapi.IBooleanGetter;
import ch.nolix.core.functionuniversalapi.IElementGetter;
import ch.nolix.core.functionuniversalapi.IElementTaker;
import ch.nolix.core.functionuniversalapi.IElementTakerElementGetter;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;

//class
public final class MutableOptionalValueExtractor<V> extends Property {
	
	//attributes
	private final String name;
	private final IElementTaker<V> setter;
	private final IBooleanGetter valuePresenceChecker;
	private final IElementGetter<V> getter;
	private final IElementTakerElementGetter<BaseNode, V> valueCreator;
	private final IElementTakerElementGetter<V, Node> specificationCreator;
	
	//constructor
	public MutableOptionalValueExtractor(
		final String name,
		final IElementTaker<V> setter,
		final IBooleanGetter valuePresenceChecker,
		final IElementGetter<V> getter,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
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
	protected boolean addedOrChangedAttribute(final BaseNode attribute) {
		
		if (attribute.hasHeader(getName())) {
			setter.run(valueCreator.getOutput(attribute));
			return true;
		}
		
		return false;
	}
	
	//method
	@Override
	protected void fillUpAttributesInto(final LinkedList<Node> list) {
		if (valuePresenceChecker.getOutput()) {
			list.addAtEnd(specificationCreator.getOutput(getter.getOutput()));
		}
	}
}

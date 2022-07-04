//package declaration
package ch.nolix.system.element;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IMutableList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionuniversalapi.IBooleanGetter;
import ch.nolix.coreapi.functionuniversalapi.IElementGetter;
import ch.nolix.coreapi.functionuniversalapi.IElementTaker;

//class
public final class MutableOptionalSpecificationValueExtractor extends Property {
	
	//attributes
	private final String name;
	private final IElementTaker<INode<?>> setter;
	private final IBooleanGetter valuePresenceChecker;
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
	protected boolean addedOrChangedAttribute(final INode<?> attribute) {
		
		if (attribute.hasHeader(getName())) {
			setter.run(attribute);
			return true;
		}
		
		return false;
	}
	
	//method
	@Override
	protected void fillUpAttributesInto(final IMutableList<INode<?>> list) {
		if (valuePresenceChecker.getOutput()) {
			list.addAtEnd(getter.getOutput());
		}
	}
}

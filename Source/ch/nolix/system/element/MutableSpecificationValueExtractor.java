//package declaration
package ch.nolix.system.element;

import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.functionuniversalapi.IElementGetter;
import ch.nolix.core.functionuniversalapi.IElementTaker;
import ch.nolix.core.name.PascalCaseCatalogue;

//class
public final class MutableSpecificationValueExtractor extends Property {
	
	//attributes
	private final String name;
	private final IElementTaker<BaseNode> setter;
	private final IElementGetter<Node> getter;
	
	//constructor
	public MutableSpecificationValueExtractor(
		final String name,
		final IElementTaker<BaseNode> setter,
		final IElementGetter<Node> getter
	) {
		
		GlobalValidator.assertThat(name).thatIsNamed(PascalCaseCatalogue.NAME).isNotBlank();
		GlobalValidator.assertThat(setter).thatIsNamed("setter").isNotNull();
		GlobalValidator.assertThat(getter).thatIsNamed("getter").isNotNull();
		
		this.name = name;
		this.setter = setter;
		this.getter = getter;
	}
	
	//method
	public String getName() {
		return name;
	}
	
	//method
	@Override
	protected boolean addedOrChangedAttribute(final BaseNode attribute) {
		
		if (attribute.hasHeader(getName())) {
			setter.run(attribute);
			return true;
		}
		
		return false;
	}
	
	//method
	@Override
	protected void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(getter.getOutput());
	}
}

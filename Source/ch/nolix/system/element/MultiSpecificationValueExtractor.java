//package declaration
package ch.nolix.system.element;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.functionuniversalapi.IElementGetter;
import ch.nolix.core.functionuniversalapi.IElementTaker;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;

//class
public final class MultiSpecificationValueExtractor extends Property {
	
	//attributes
	private final String name;
	private final IElementTaker<BaseNode> adder;
	private final IElementGetter<IContainer<Node>> getter;
	
	//constructor
	public MultiSpecificationValueExtractor(
		final String name,
		final IElementTaker<BaseNode> adder,
		final IElementGetter<IContainer<Node>> getter
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
	protected boolean addedOrChangedAttribute(final BaseNode attribute) {
		
		if (attribute.hasHeader(getName())) {
			adder.run(attribute);
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

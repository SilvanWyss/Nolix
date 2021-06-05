//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IElementGetter;
import ch.nolix.common.functionapi.IElementTaker;

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
		
		Validator.assertThat(name).thatIsNamed(PascalCaseCatalogue.NAME).isNotBlank();
		Validator.assertThat(adder).thatIsNamed("adder").isNotNull();
		Validator.assertThat(getter).thatIsNamed("getter").isNotNull();
				
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

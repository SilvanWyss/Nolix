//package declaration
package ch.nolix.system.element;

//own imports
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.functionuniversalapi.IElementGetter;
import ch.nolix.core.functionuniversalapi.IElementTaker;

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

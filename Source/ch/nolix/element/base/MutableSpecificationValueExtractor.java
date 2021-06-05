//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IElementGetter;
import ch.nolix.common.functionapi.IElementTaker;

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
		
		Validator.assertThat(name).thatIsNamed(PascalCaseCatalogue.NAME).isNotBlank();
		Validator.assertThat(setter).thatIsNamed("setter").isNotNull();
		Validator.assertThat(getter).thatIsNamed("getter").isNotNull();
		
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

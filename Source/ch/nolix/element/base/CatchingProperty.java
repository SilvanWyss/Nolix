//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.functionapi.IElementTakerElementGetter;

//class
public final class CatchingProperty<V> extends Property implements Named {
	
	//attributes
	private final String name;
	private final IElementTaker<V> setter;
	private final IElementTakerElementGetter<BaseNode, V> valueCreator;
	
	//constructor
	public CatchingProperty(
		final String name,
		final IElementTaker<V> setter,
		final IElementTakerElementGetter<BaseNode, V> valueCreator
	) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		Validator.assertThat(setter).thatIsNamed("setter").isNotNull();
		Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		
		this.name = name;
		this.setter = setter;
		this.valueCreator = valueCreator;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
	
	//method
	@Override
	protected boolean addedOrChangedAttribute(BaseNode attribute) {
		
		if (hasName(attribute.getHeader())) {
			setter.run(valueCreator.getOutput(attribute));
			return true;
		}
		
		return false;
	}
	
	//method
	@Override
	protected void fillUpAttributesInto(final LinkedList<Node> list) {}
}

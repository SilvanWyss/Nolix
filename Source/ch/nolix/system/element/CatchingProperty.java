//package declaration
package ch.nolix.system.element;

//own imports
import ch.nolix.core.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.IElementTaker;
import ch.nolix.core.functionapi.IElementTakerElementGetter;

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

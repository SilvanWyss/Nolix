//package declaration
package ch.nolix.system.element;

import ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi.Named;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.functionuniversalapi.IElementGetter;
import ch.nolix.core.functionuniversalapi.IElementTaker;
import ch.nolix.core.functionuniversalapi.IElementTakerElementGetter;

//class
public final class ForwardingMutableValue<V> extends Property implements Named {
	
	//static method
	public static ForwardingMutableValue<Boolean> forBoolean(
		final String name,
		final IElementTaker<Boolean> setter,
		final IElementGetter<Boolean> getter
	) {
		return
		new ForwardingMutableValue<>(name, setter, getter, BaseNode::getOneAttributeAsBoolean, Node::withAttribute);
	}
	
	//static method
	public static ForwardingMutableValue<Integer> forInt(
		final String name,
		final IElementTaker<Integer> setter,
		final IElementGetter<Integer> getter
	) {
		return new ForwardingMutableValue<>(name, setter, getter, BaseNode::getOneAttributeAsInt, Node::withAttribute);
	}
	
	//static method
	public static ForwardingMutableValue<String> forString(
		final String name,
		final IElementTaker<String> setter,
		final IElementGetter<String> getter
	) {
		return
		new ForwardingMutableValue<>(
			name,
			setter,
			getter,
			s -> s.getRefOneAttribute().getHeaderOrEmptyString(),
			s -> {
				
				if (s.isEmpty()) {
					return new Node();
				}
				
				return Node.withAttribute(s);
			}
		);
	}
	
	//attributes
	private final String name;
	private final IElementTaker<V> setter;
	private final IElementGetter<V> getter;
	private final IElementTakerElementGetter<BaseNode, V> valueCreator;
	private final IElementTakerElementGetter<V, Node> specificationCreator;
	
	//constructor
	public ForwardingMutableValue(
		final String name,
		final IElementTaker<V> setter,
		final IElementGetter<V> getter,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		GlobalValidator.assertThat(setter).thatIsNamed("setter").isNotNull();
		GlobalValidator.assertThat(getter).thatIsNamed("getter").isNotNull();
		GlobalValidator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		GlobalValidator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();
		
		this.name = name;
		this.setter = setter;
		this.getter = getter;
		this.valueCreator = valueCreator;
		this.specificationCreator = specificationCreator;
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
	protected void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(specificationCreator.getOutput(getter.getOutput()));
	}
}

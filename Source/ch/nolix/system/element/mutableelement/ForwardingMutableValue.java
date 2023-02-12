//package declaration
package ch.nolix.system.element.mutableelement;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Named;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;

//class
public final class ForwardingMutableValue<V> extends Property implements Named {
	
	//static method
	public static ForwardingMutableValue<Boolean> forBoolean(
		final String name,
		final IElementTaker<Boolean> setter,
		final IElementGetter<Boolean> getter
	) {
		return
		new ForwardingMutableValue<>(name, setter, getter, INode::getSingleChildNodeAsBoolean, Node::withChildNode);
	}
	
	//static method
	public static ForwardingMutableValue<Integer> forInt(
		final String name,
		final IElementTaker<Integer> setter,
		final IElementGetter<Integer> getter
	) {
		return new ForwardingMutableValue<>(name, setter, getter, INode::getSingleChildNodeAsInt, Node::withChildNode);
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
			s -> s.getRefSingleChildNode().getHeaderOrEmptyString(),
			(final String s) -> {
				
				if (s.isEmpty()) {
					return Node.EMPTY_NODE;
				}
				
				return Node.withChildNode(s);
			}
		);
	}
	
	//attributes
	private final String name;
	private final IElementTaker<V> setter;
	private final IElementGetter<V> getter;
	private final IElementTakerElementGetter<INode<?>, V> valueCreator;
	private final IElementTakerElementGetter<V, INode<?>> specificationCreator;
	
	//constructor
	public ForwardingMutableValue(
		final String name,
		final IElementTaker<V> setter,
		final IElementGetter<V> getter,
		final IElementTakerElementGetter<INode<?>, V> valueCreator,
		final IElementTakerElementGetter<V, INode<?>> specificationCreator
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
	protected boolean addedOrChangedAttribute(INode<?> attribute) {
		
		if (hasName(attribute.getHeader())) {
			setter.run(valueCreator.getOutput(attribute));
			return true;
		}
		
		return false;
	}
	
	//method
	@Override
	protected void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
		list.addAtEnd(specificationCreator.getOutput(getter.getOutput()));
	}
}

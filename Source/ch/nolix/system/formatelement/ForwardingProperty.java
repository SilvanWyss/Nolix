//package declaration
package ch.nolix.system.formatelement;

import ch.nolix.core.container.main.LinkedList;
//own imports
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;

//class
public final class ForwardingProperty<S extends Enum<S>, V> extends Property<S>{
	
	//multi-attribute
	private final IContainer<MaterializedProperty<S, V>> materializedProperties;
	
	//constructor
	@SafeVarargs
	public ForwardingProperty(final String name, final MaterializedProperty<S, V>... materializedProperties) {
		
		super(name);
		
		this.materializedProperties = LinkedList.withElements(materializedProperties);
	}
	
	//constructor
	public ForwardingProperty(final String name, final IContainer<MaterializedProperty<S, V>> materializedProperties) {
		
		super(name);
		
		this.materializedProperties = LinkedList.fromIterable(materializedProperties);
	}
	
	//method
	public void setValueForState(final S state, final V value) {
		for (final var mp : materializedProperties) {
			mp.setValueForState(state, value);
		}
	}
	
	//method
	@Override
	protected void fillUpValuesSpecificationInto(LinkedList<Node> list) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void setFrom(Property<S> property) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void setUndefined() {
		//Does nothing.
	}
	
	@Override
	protected void setValueFromSpecification(final BaseNode specification) {
		for (final var mp : materializedProperties) {
			mp.setValueFromSpecification(specification);
		}
	}
}

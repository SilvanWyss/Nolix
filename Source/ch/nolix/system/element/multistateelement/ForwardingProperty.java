//package declaration
package ch.nolix.system.element.multistateelement;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.containerapi.mainapi.IMutableList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

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
	protected void fillUpValuesSpecificationInto(IMutableList<INode<?>> list) {
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
	protected void setValueFromSpecification(final INode<?> specification) {
		for (final var mp : materializedProperties) {
			mp.setValueFromSpecification(specification);
		}
	}
}

//package declaration
package ch.nolix.system.element.multistateconfiguration;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.mainapi.IMutableList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//class
public final class ForwardingProperty<S extends Enum<S>, V> extends Property<S>{
	
	//multi-attribute
	private final IContainer<MaterializedProperty<S, V>> materializedProperties;
	
	//constructor
	@SafeVarargs
	public ForwardingProperty(
		final String name,
		final MaterializedProperty<S, V> materializedProperty,
		final MaterializedProperty<S, V>... materializedProperties
	) {
		
		super(name);
		
		this.materializedProperties = LinkedList.withElements(materializedProperty, materializedProperties);
	}
	
	//constructor
	public ForwardingProperty(final String name, final IContainer<MaterializedProperty<S, V>> materializedProperties) {
		
		super(name);
		
		this.materializedProperties = LinkedList.fromIterable(materializedProperties);
	}
	
	//method
	@Override
	public void setUndefined() {
		materializedProperties.forEach(MaterializedProperty::setUndefined);
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
	
	@Override
	protected void setValueFromSpecification(final INode<?> specification) {
		for (final var mp : materializedProperties) {
			mp.setValueFromSpecification(specification);
		}
	}
}
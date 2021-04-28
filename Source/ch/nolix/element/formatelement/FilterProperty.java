//package declaration
package ch.nolix.element.formatelement;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;

//class
public final class FilterProperty<S extends Enum<S>, V> extends Property<S>{
	
	//multi-attribute
	private final IContainer<MaterializedProperty<S, V>> materializedProperties;
	
	//constructor
	@SafeVarargs
	public FilterProperty(final String name, final MaterializedProperty<S, V>... materializedProperties) {
		
		super(name);
		
		this.materializedProperties = LinkedList.withElements(materializedProperties);
	}
	
	//constructor
	public FilterProperty(final String name, final IContainer<MaterializedProperty<S, V>> materializedProperties) {
		
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
	protected void fillUpValuesSpecificationInto(LinkedList<Node> list) {}
	
	//method
	@Override
	protected void setFrom(Property<S> property) {}
	
	//method
	@Override
	protected void setUndefined() {}
	
	@Override
	protected void setValueFromSpecification(final BaseNode specification) {
		for (final var mp : materializedProperties) {
			mp.setValueFromSpecification(specification);
		}
	}
}

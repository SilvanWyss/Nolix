//package declaration
package ch.nolix.common.valueCreator;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//package-visible class
final class SpecificValueCreator<V> {
	
	//attributes
	private final Class<V> type;
	private final IElementTakerElementGetter<String, V> fromStringCreator;
	private final IElementTakerElementGetter<Node, V> fromSpecificationCreator;
	private final CreateMediator<V> createMediator;
	
	//package-visible mediator
	SpecificValueCreator(
		final Class<V> type,
		final IElementTakerElementGetter<String, V> fromStringCreator,
		final IElementTakerElementGetter<Node, V> fromSpecificationCreator
	) {
		
		Validator.suppose(type).thatIsNamed(VariableNameCatalogue.TYPE).isNotNull();
		Validator.suppose(fromStringCreator).thatIsNamed("from-string-creator").isNotNull();
		Validator.suppose(fromSpecificationCreator).thatIsNamed("from-specification-creator").isNotNull();
		
		this.type = type;
		this.fromStringCreator = fromStringCreator;
		this.fromSpecificationCreator = fromSpecificationCreator;
		
		createMediator = new CreateMediator<>(this);
	}
	
	//method
	public V createFromSpecification(final Node specification) {
		return fromSpecificationCreator.getOutput(specification);
	}
	
	//method
	public V createFromString(final String string) {
		return fromStringCreator.getOutput(string);
	}
	
	//method
	public CreateMediator<V> getRefCreateMediator() {
		return createMediator;
	}
	
	//method
	public String getType() {
		return type.getSimpleName();
	}
	
	//method
	public Class<?> getValueClass() {
		return type;
	}
	
	//method
	public boolean hasType(final Class<?> type) {
		return (this.type == type);
	}
	
	//method
	public boolean hasType(final String type) {
		return this.type.getSimpleName().equals(type);
	}
}

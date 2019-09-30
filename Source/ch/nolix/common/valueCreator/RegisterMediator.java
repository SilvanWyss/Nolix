//package declaration
package ch.nolix.common.valueCreator;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//class
public final class RegisterMediator<V> {
	
	//attribute
	private final ValueCreator parentValueCreator;
	private final Class<V> type;
	
	//package-visible constructor
	RegisterMediator(final ValueCreator parentValueCreator, final Class<V> type) {
		
		Validator.suppose(parentValueCreator).thatIsNamed("parent value creator").isNotNull();
		Validator.suppose(type).thatIsNamed(VariableNameCatalogue.TYPE);
		
		this.parentValueCreator = parentValueCreator;
		this.type = type;
	}
	
	//method
	public boolean containsCreators() {
		return parentValueCreator.containsCreatorsForType(type);
	}
	
	//method
	public void registerCreators(
		final IElementTakerElementGetter<String, V> fromStringCreator,
		final IElementTakerElementGetter<Node, V> fromSpecificationCreator
	) {
		parentValueCreator.registerSpecificValueCreator(
			new SpecificValueCreator<>(type, fromStringCreator, fromSpecificationCreator)
		);
	}
}

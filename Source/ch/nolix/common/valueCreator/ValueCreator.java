//package declaration
package ch.nolix.common.valueCreator;

//own imports
import ch.nolix.common.containers.List;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;

//class
public final class ValueCreator {
	
	//multi-attribute
	private final List<SpecificValueCreator<?>> specificValueCreators = new List<>();
	
	//method
	public <V> RegisterMediator<V> forType(final Class<V> type) {
		return new RegisterMediator<>(this, type);
	}
	
	//method
	public CreateMediator<?> ofType(final String type) {
		return specificValueCreators.getRefFirst(svc -> svc.hasType(type)).getRefCreateMediator();
	}
	
	//method
	public <V> CreateMediator<V> ofType(final Class<V> type) {
		
		@SuppressWarnings("unchecked")
		final var specificValueCreator =
		(SpecificValueCreator<V>)specificValueCreators.getRefFirstOrNull(svc -> svc.hasType(type));
		
		if (specificValueCreator == null) {
			throw new InvalidArgumentException(this, "does not contain a creator for '" + type.getName() + "'");
		}
		
		return specificValueCreator.getRefCreateMediator();
	}
	
	//package-visible method
	boolean containsCreatorsForType(final Class<?> type) {
		return specificValueCreators.contains(svc -> svc.hasType(type));
	}

	//package-visible method
	void registerSpecificValueCreator(final SpecificValueCreator<?> specificValueCreator) {
		
		if (forType(specificValueCreator.getValueClass()).containsCreators()) {
			throw
			new InvalidArgumentException(
				this,
				"contains already creators for '" + specificValueCreator.getValueClass().getName() + "'"
			);
		}
		
		specificValueCreators.addAtEnd(specificValueCreator);
	}
}

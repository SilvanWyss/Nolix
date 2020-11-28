//package declaration
package ch.nolix.common.valuecreator;

import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;

//class
public final class ValueCreator<S> {
	
	//multi-attribute
	private final LinkedList<SpecificValueCreator<S, ?>> specificValueCreators = new LinkedList<>();
	
	//method
	public <V> RegisterMediator<S, V> forType(final Class<V> type) {
		return new RegisterMediator<>(this, type);
	}
	
	//method
	public CreateMediator<?, ?> ofType(final String type) {
		return specificValueCreators.getRefFirst(svc -> svc.canCreateValuesOf(type)).getRefCreateMediator();
	}
	
	//method
	public <V> CreateMediator<S, V> ofType(final Class<V> type) {
		
		@SuppressWarnings("unchecked")
		final var specificValueCreator =
		(SpecificValueCreator<S, V>)specificValueCreators.getRefFirstOrNull(svc -> svc.canCreateValuesOf(type));
		
		if (specificValueCreator == null) {
			throw new InvalidArgumentException(this, "cannot create values of '" + type.getName() + "'");
		}
		
		return specificValueCreator.getRefCreateMediator();
	}
	
	//method
	public ValueCreator<S> registerSpecificValueCreator(final SpecificValueCreator<S, ?> specificValueCreator) {
		
		if (forType(specificValueCreator.getRefValueClass()).canCreateValues()) {
			throw
			new InvalidArgumentException(
				this,
				"can already create values of '" + specificValueCreator.getRefValueClass().getName() + "'"
			);
		}
		
		specificValueCreators.addAtEnd(specificValueCreator);
		
		return this;
	}
	
	//method
	boolean canCreateValuesOf(final Class<?> type) {
		return specificValueCreators.contains(svc -> svc.canCreateValuesOf(type));
	}
}

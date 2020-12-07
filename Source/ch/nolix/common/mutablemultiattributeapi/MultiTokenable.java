//package declaration
package ch.nolix.common.mutablemultiattributeapi;

//own imports
import ch.nolix.common.generalskillapi.IFluentObject;
import ch.nolix.common.multiattributerequestapi.MultiTokened;

//interface
public interface MultiTokenable<MT extends MultiTokenable<MT>> extends IFluentObject<MT>, MultiTokened {
	
	//method declaration
	MT addToken(String token);
	
	//method
	default MT addToken(final String... tokens) {
		
		for (final var t : tokens) {
			addToken(t);
		}
		
		return asConcrete();
	}
	
	//method
	default MT addTokens(final Iterable<String> tokens) {
		
		tokens.forEach(this::addToken);
		
		return asConcrete();
	}
	
	//method declaration
	MT removeToken(String token);
	
	//method
	default MT removeToken(final String... tokens) {
		
		for (final var t : tokens) {
			removeToken(t);
		}
		
		return asConcrete();
	}
	
	//method declaration
	MT removeTokens();
	
	//method
	default MT removeTokens(final Iterable<String> tokens) {
		
		tokens.forEach(this::removeToken);
		
		return asConcrete();
	}
}

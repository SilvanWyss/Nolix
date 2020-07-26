//package declaration
package ch.nolix.common.mutableMultiAttributeAPI;

//own imports
import ch.nolix.common.generalSkillAPI.IFluentObject;
import ch.nolix.common.multiAttributeRequestAPI.MultiTokened;

//interface
public interface MultiTokenable<MT extends MultiTokenable<MT>> extends IFluentObject<MT>, MultiTokened {
	
	//method declaration
	public abstract MT addToken(String token);
	
	//method
	public default MT addToken(final String... tokens) {
		
		for (final var t : tokens) {
			addToken(t);
		}
		
		return asConcrete();
	}
	
	//method
	public default MT addTokens(final Iterable<String> tokens) {
		
		tokens.forEach(this::addToken);
		
		return asConcrete();
	}
	
	//method declaration
	public abstract MT removeToken(String token);
	
	//method
	public default MT removeToken(final String... tokens) {
		
		for (final var t : tokens) {
			removeToken(t);
		}
		
		return asConcrete();
	}
	
	//method declaration
	public abstract MT removeTokens();
	
	//method
	public default MT removeTokens(final Iterable<String> tokens) {
		
		tokens.forEach(this::removeToken);
		
		return asConcrete();
	}
}

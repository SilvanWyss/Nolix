//package declaration
package ch.nolix.element.bases;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.skillAPI.OptionalTokenable;
import ch.nolix.core.specificationAPI.Configurable;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * A {@link ConfigurableElement} is a {@link OptionalNamableElement}
 * that is configurable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 160
 */
public abstract class ConfigurableElement<CE extends ConfigurableElement<CE>>
extends OptionalNamableElement<CE>
implements Configurable<CE>, OptionalTokenable<CE> {
	
	//optional attribute
	private String token;
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case VariableNameCatalogue.TOKEN:
				setToken(attribute.getOneAttributeAsString());
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DocumentNode> getAttributes() {
		
		//Calls method of the base class.
		final var attributes = super.getAttributes();
		
		//Handles the case that the current configurbale element has token.
		if (hasToken()) {
			attributes.addAtEnd(
				new DocumentNode(PascalCaseNameCatalogue.TOKEN, getToken())
			);
		}
		return attributes;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getToken() {
		
		//Checks if the current configurable element has a token.
		supposeHasToken();
		
		return token;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasToken() {
		return (token != null);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasToken(String token) {
		return OptionalTokenable.super.hasToken(token);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isOfType(final String type) {
		
		//Calls method of the base class.
		return super.isOfType(type);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CE removeToken() {
		
		token = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CE reset() {
		
		//Calls method of the base class.
		super.reset();
		
		resetConfiguration();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the token of the current {@link ConfigurableElement}.
	 * 
	 * @param token
	 * @throws NullArgumentException if the given token is null.
	 * @throws EmptyArgumentException if the given token is empty.
	 */
	@Override
	public final CE setToken(final String token) {
		
		//Checks if the given token is not null and not empty.
		Validator
		.suppose(token)
		.thatIsNamed(VariableNameCatalogue.TOKEN)
		.isNotEmpty();
		
		//Sets the token of the current configurable element.
		this.token = token;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * @throws ArgumentMissesAttributeException
	 * if the current {@link ConfigurableElement} does not have a token.
	 */
	private void supposeHasToken() {
		
		//Checks if the current configurable element has a token.
		if (!hasToken()) {
			throw new ArgumentMissesAttributeException(this, VariableNameCatalogue.TOKEN);
		}
	}
}

//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.core.attributeAPI.OptionalNamable;
import ch.nolix.core.attributeAPI.OptionalTokenable;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.specificationAPI.Configurable;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.core.MutableElement;

//abstract class
/**
 * A {@link ConfigurableElement} is a {@link OptionalNamableElement}
 * that is configurable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 160
 */
public abstract class ConfigurableElement<CE extends ConfigurableElement<CE>> extends MutableElement<CE>
implements Configurable<CE>, OptionalNamable<CE>, OptionalTokenable<CE> {
	
	//optional attributes
	private String name;
	private String token;
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.NAME:
				setName(attribute.getOneAttributeAsString());
				break;
			case PascalCaseNameCatalogue.TOKEN:
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
		
		//Handles the case that the current configurbale element has a name.
		if (hasName()) {
			attributes.addAtEnd(new DocumentNode(PascalCaseNameCatalogue.NAME, name));
		}
		
		//Handles the case that the current configurbale element has a token.
		if (hasToken()) {
			attributes.addAtEnd(new DocumentNode(PascalCaseNameCatalogue.TOKEN, token));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getName() {
		return name;
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
	public final boolean hasName() {
		return (name != null);
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
	public final CE removeName() {
		
		name = null;
		
		return asConcreteType();
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
		
		removeName();
		resetConfiguration();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * @param name
	 * @return the current {@link ConfigurableElement}.
	 * @throws NullArgumentException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 */
	@Override
	public final CE setName(final String name) {
		
		this.name = Validator.suppose(name).thatIsNamed(VariableNameCatalogue.NAME).isNotBlank().andReturn();
		
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

//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.elementapi.IConfigurableElement;

//class
/**
 * A {@link ConfigurableElement} is configurable.
 * 
 * @author Silvan Wyss
 * @month 2016-01-01
 * @lines 220
 * @param <CE> is the type of a {@link ConfigurableElement}.
 */
public abstract class ConfigurableElement<CE extends ConfigurableElement<CE>> extends Element<CE>
implements IConfigurableElement<CE> {
	
	//optional attributes
	private String id;
	private String token;
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseCatalogue.ID:
				setId(attribute.getOneAttributeHeader());
				break;
			case PascalCaseCatalogue.TOKEN:
				setToken(attribute.getOneAttributeHeader());
				break;
			default:
				internalAddOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Calls method of the base class.
		super.fillUpAttributesInto(list);
		
		//Handles the case that the current ConfigurableElement has a id.
		if (hasId()) {
			list.addAtEnd(Node.withHeaderAndAttribute(PascalCaseCatalogue.ID, id));
		}
		
		//Handles the case that the current ConfigurableElement has a token.
		if (hasToken()) {
			list.addAtEnd(Node.withHeaderAndAttribute(PascalCaseCatalogue.TOKEN, token));
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getId() {
		
		supposeHasId();
		
		return id;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getToken() {
		
		//Asserts that the current configurable element has a token.
		supposeHasToken();
		
		return token;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasId() {
		return (id != null);
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
	public final CE removeId() {
		
		id = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CE removeToken() {
		
		token = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void reset() {
		
		removeId();
		removeToken();
		resetConfiguration();
		
		resetConfigurableElement();
	}
	
	//method
	/**
	 * @param id
	 * @return the current {@link ConfigurableElement}.
	 * @throws ArgumentIsNullException if the given id is null.
	 * @throws InvalidArgumentException if the given id is blank.
	 */
	@Override
	public final CE setId(final String id) {
		
		Validator.assertThat(id).thatIsNamed(LowerCaseCatalogue.ID).isNotBlank();
		
		this.id = id;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the token of the current {@link ConfigurableElement}.
	 * 
	 * @param token
	 * @throws ArgumentIsNullException if the given token is null.
	 * @throws EmptyArgumentException if the given token is empty.
	 */
	@Override
	public final CE setToken(final String token) {
		
		//Asserts that the given token is not null and not empty.
		Validator
		.assertThat(token)
		.thatIsNamed(LowerCaseCatalogue.TOKEN)
		.isNotEmpty();
		
		//Sets the token of the current configurable element.
		this.token = token;
		
		return asConcrete();
	}
	
	//method declaration
	/**
	 * Resets the current {@link ConfigurableElement}.
	 */
	protected abstract void resetConfigurableElement();
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link ConfigurableElement} does not have an id.
	 */
	private void supposeHasId() {
		
		//Asserts that the current configurable element has a token.
		if (!hasId()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.ID);
		}
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link ConfigurableElement} does not have a token.
	 */
	private void supposeHasToken() {
		
		//Asserts that the current configurable element has a token.
		if (!hasToken()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.TOKEN);
		}
	}
}

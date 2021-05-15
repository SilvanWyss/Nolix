//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.MutableOptionalValue;
import ch.nolix.element.elementapi.IConfigurableElement;

//class
/**
 * A {@link ConfigurableElement} is configurable.
 * 
 * @author Silvan Wyss
 * @date 2016-02-01-01
 * @lines 150
 * @param <CE> is the type of a {@link ConfigurableElement}.
 */
public abstract class ConfigurableElement<CE extends ConfigurableElement<CE>> extends Element<CE>
implements IConfigurableElement<CE> {
	
	//constants
	private static final String ID_HEADER = PascalCaseCatalogue.ID;
	private static final String TOKEN_HEADER = PascalCaseCatalogue.TOKEN;
	
	//attributes
	private final MutableOptionalValue<String> id = MutableOptionalValue.forString(ID_HEADER, this::setId);
	private final MutableOptionalValue<String> token = MutableOptionalValue.forString(TOKEN_HEADER, this::setToken);
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		internalAddOrChangeAttribute(attribute);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getId() {
		return id.getValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getToken() {
		return token.getValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasId() {
		return id.hasValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasToken() {
		return token.hasValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void removeId() {
		id.clear();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void removeToken() {
		token.clear();
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
		
		this.id.setValue(id);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the token of the current {@link ConfigurableElement}.
	 * 
	 * @param token
	 * @throws ArgumentIsNullException if the given token is null.
	 * @throws EmptyArgumentException if the given token is blank.
	 */
	@Override
	public final CE setToken(final String token) {
		
		Validator.assertThat(token).thatIsNamed(LowerCaseCatalogue.TOKEN).isNotBlank();
		
		this.token.setValue(token);
		
		return asConcrete();
	}
	
	//method declaration
	/**
	 * Resets the current {@link ConfigurableElement}.
	 */
	protected abstract void resetConfigurableElement();
}

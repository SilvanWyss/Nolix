//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.Freezable;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.Configurable;
import ch.nolix.element.bases.OptionalNamableElement;
import ch.nolix.element.core.NonEmptyText;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 710
 * @param <C> The type of a configuration.
 */
public abstract class Configuration<C extends Configuration<C>>
extends OptionalNamableElement<C>
implements Freezable<C> {
	
	//attribute headers
	private static final String SELECTOR_TYPE_HEADER = "SelectorType";
	private static final String SELECTOR_ROLE_HEADER = "SelectorRole";
	private static final String SELECTOR_TOKEN_HEADER = "SelectorToken";
	private static final String SELECTOR_NAME_HEADER = "SelectorName";
	
	//attribute
	private boolean frozen = false;
	
	//multiple attributes
	private final List<StandardSpecification> attachingAttributes
		= new List<StandardSpecification>();
	protected final List<Configuration<?>> configurations
		= new List<Configuration<?>>();
	
	//optional attribute
	private NonEmptyText selectorType;
	private NonEmptyText selectorToken;
	private NonEmptyText selectorName;
	
	//multi-attribute
	private final List<String> selectorRoles = new List<String>();
	
	//method
	/**
	 * Adds the given attaching attribute to this configuration.
	 * 
	 * @param attachingAttribute
	 * @return this configuration.
	 * @throws NullArgumentException if the given attaching attribute is null.
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public final C addAttachingAttribute(final Specification attachingAttribute) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
				
		attachingAttributes.addAtEnd(
			new StandardSpecification(
				attachingAttribute.getHeader(),
				attachingAttribute.getRefAttributes())
		);
		
		return getInstance();
	}
	
	//method
	/**
	 * Adds the given attaching attribute to this configuration.
	 * 
	 * @param attachingAttribute
	 * @return this configuration
	 * @throws InvalidArgumentException
	 * if the given attachingAttribute represents no standard specification.
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public final C addAttachingAttribute(final String attachingAttribute) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();			
		
		return addAttachingAttribute(new StandardSpecification(attachingAttribute));
	}
	
	//method
	/**
	 * Adds the given attaching attributes to this configuration.
	 * 
	 * @param attachingAttributes
	 * @return this configuration.
	 * @throws InvalidArgumentException
	 * if one of the given attaching attributes represents no standard specification.
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public final C addAttachingAttribute(final String... attachingAttributes) {

		//Checks if this configuration is not frozen.
		supposeNotFrozen();		
		
		//Iterates the given attaching attributes.
		for (final String aa : attachingAttributes) {
			addAttachingAttribute(aa);
		}
		
		return getInstance();
	}
	
	//method
	/**
	 * Adds the given configuration to this configuration.
	 * 
	 * @param configuration
	 * @return this configuration.
	 * @throws NullArgumentException if the given configuration is null.
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public final C addConfiguration(final Configuration<?> configuration) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		configurations.addAtEnd(configuration);
		
		return getInstance();
	}
	
	//method
	/**
	 * Adds the given configurations to this configuration.
	 * 
	 * @param configurations
	 * @return this configuration.
	 * @throws NullArgumentException if one of the given configurations is null.
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public final C addConfiguration(final Configuration<?>...configurations) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		this.configurations.addAtEnd(configurations);
		
		return getInstance();
	}
	
	//method
	/**
	 * Adds or changes the given attribute to this configuration.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.NAME:
				setName(attribute.getOneAttributeAsString());
				break;
			case SELECTOR_TYPE_HEADER:
				setSelectorType(attribute.getOneAttributeAsString());
				break;
			case SELECTOR_ROLE_HEADER:
				addSelectorRoles_(attribute.getAttributesToStrings());
				break;
			case SELECTOR_TOKEN_HEADER:
				setSelectorToken(attribute.getOneAttributeAsString());
				break;
			case SELECTOR_NAME_HEADER:
				setSelectorName(attribute.getOneAttributeAsString());
				break;
			case StandardConfiguration.TYPE_NAME:
				addConfiguration(new StandardConfiguration(attribute.getRefAttributes()));
				break;
			case DeepConfiguration.TYPE_NAME:
				addConfiguration(new DeepConfiguration(attribute.getRefAttributes()));
				break;
			default:
				addAttachingAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Adds the given selector role to this configuration.
	 * 
	 * @param selectorRole
	 * @return this configuration.
	 * @throws InvalidStateException if this configuration contains already the given selector role.
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public final C addSelectorRole(final Enum<?> selectorRole) {
		
		addSelectorRole(selectorRole.toString());
		
		return getInstance();
	}
	
	//method
	/**
	 * Adds the given selector roles to this configuration.
	 * 
	 * @param selectorRoles
	 * @return this configuration.
	 * @throws InvalidStateException if this configuration contains already one of the given selector role.
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public final C addSelectorRole(final Enum<?>... selectorRoles) {
		
		//Iterates the given selector roles.
		for (final var sr : selectorRoles) {
			addSelectorRole(sr);
		}
		
		return getInstance();
	}
	
	//method
	/**
	 * Adds the given selector roles to this configuration.
	 * 
	 * @param selectorRoles
	 * @return this configuration.
	 * @throws InvalidStateException if this configuration contains already one of the given selector role.
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public final C addSelectorRoles(final Iterable<Enum<?>> selectorRoles) {
		
		selectorRoles.forEach(sr -> addSelectorRole(sr));
		
		return getInstance();
	}
	
	//method
	/**
	 * @param selectorRole
	 * @return true if this configuration contains the given selector role.
	 */
	public final boolean containsSelectorRole(final String selectorRole) {
		return selectorRoles.containsEqualing(selectorRole);
	}
	
	//method
	/**
	 * @return true if this configuration contains selector roles.
	 */
	public final boolean containsSelectorRoles() {
		return selectorRoles.containsAny();
	}
	
	//abstract method
	/**
	 * Lets this configuration configure the given element.
	 * 
	 * @param element
	 */
	public abstract void configure(Configurable<?> element);
	
	//method
	/**
	 * Freezes this configuration.
	 * 
	 * @return this configuration.
	 */
	public final C freeze() {
		
		frozen = true;
		
		return getInstance();
	}
	
	//method
	/**
	 * @return the attributes of this configuration.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		//Handles the case that this configuration has a selector type.
		if (hasSelectorType()) {
			attributes.addAtEnd(selectorType.getSpecificationAs(SELECTOR_TYPE_HEADER));
		}
		
		//Handles the case that this configuration contains selector roles.		
		if (containsSelectorRoles()) {
			
			final var specification = new StandardSpecification(SELECTOR_ROLE_HEADER);
			getSelectorRoles().forEach(sr -> specification.addAttribute(sr));
			
			attributes.addAtEnd(specification);
		}
		
		//Handles the case that this configuration has a selector token.
		if (hasSelectorToken()) {
			attributes.addAtEnd(selectorToken.getSpecificationAs(SELECTOR_TOKEN_HEADER));
		}
		
		//Handles the case that this configuration has a selector name.
		if (hasSelectorName()) {
			attributes.addAtEnd(selectorName.getSpecificationAs(SELECTOR_NAME_HEADER));
		}
		
		attributes.addAtEnd(attachingAttributes);
		attributes.addAtEnd(configurations, c -> c.getSpecification());
		
		return attributes;
	}
	
	//method
	/**
	 * @return the selector name of this configuration.
	 * @throws UnexistingAttributeException if this configuration has no selector name.
	 */
	public final String getSelectorName() {
		
		//Checks if this configuration has a selector name.
		if (!hasSelectorName()) {
			throw new UnexistingAttributeException(this, "selector name");
		}
		
		return selectorName.getValue();
	}
	
	//method
	/**
	 * @return the selector roles of this configuration.
	 */
	public final IContainer<String> getSelectorRoles() {
		return selectorRoles;
	}
	
	//method
	/**
	 * @return the selector token of this configuration.
	 * @throws UnexistingAttributeException if this configuration has no selector token.
	 */
	public final String getSelectorToken() {
		
		//Checks if this configuration has a selector token.
		if (!hasSelectorToken()) {
			throw new UnexistingAttributeException(this, "selector token");
		}
		
		return selectorToken.getValue();
	}
	
	//method
	/**
	 * @return true if this configuration has attaching attributes.
	 */
	public final boolean hasAttachingAttributes() {
		return attachingAttributes.containsAny();
	}
	
	//method
	/**
	 * @return the selector type of this configuration.
	 * @throws UnexistingAttributeException if this configuration has no selector type.
	 */
	public final String getSelectorType() {
		
		//Checks if this configuration has a selector type.
		if (!hasSelectorType()) {
			throw new UnexistingAttributeException(this, "selector type");
		}
		
		return selectorType.getValue();
	}
	
	//method
	/**
	 * @return true if this configuration has a selector name.
	 */
	public final boolean hasSelectorName() {
		return (selectorName != null);
	}
	
	//method
	/**
	 * @param selectorName
	 * @return true if this configuration has the given selector name.
	 */
	public final boolean hasSelectorName(final String selectorName) {
		
		//Handles the case that this configuration has no selector name.
		if (!hasSelectorName()) {
			return false;
		}
		
		//Handles the case that this configuration has a selector name.
		return getSelectorName().equals(selectorName);
	}
	
	//method
	/**
	 * @return true if this configuration has a selector token.
	 */
	public final boolean hasSelectorToken() {
		return (selectorToken != null);
	}
	
	//method
	/**
	 * @param selectorToken
	 * @return true if this configuration has the given selector token.
	 */
	public final boolean hasSelectorToken(final String selectorToken) {
		
		//Handles the case that this configuration has no selector token.
		if (!hasSelectorToken()) {
			return false;
		}
		
		//Handles the case that this configuration as a selector token.
		return getSelectorToken().equals(selectorToken);
	}
	
	//method
	/**
	 * @return true if this configuration has a selector type.
	 */
	public final boolean hasSelectorType() {
		return (selectorType != null);
	}
	
	//method
	/**
	 * @param selectorType
	 * @return true if this configuration has the given selector type.
	 */
	public final boolean hasSelectorType(final String selectorType) {
		
		//Handles the case that this configuration has no selector type.
		if (!hasSelectorType()) {
			return false;
		}
		
		//Handles the case that htis configuration has a selector type.
		return getSelectorType().equals(selectorType);
	}
	
	//method
	/**
	 * @return true if this configuration is frozen.
	 */
	public final boolean isFrozen() {
		return frozen;
	}
	
	//method
	/**
	 * Removes the selector name of this configuration.
	 * 
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public final void removeSelectorName() {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		selectorName = null;
	}
	
	//method
	/**
	 * Removes the selector roles of this configuration.
	 * 
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public final void removeSelectorRoles() {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		selectorRoles.clear();
	}
	
	//method
	/**
	 * Removes the selector token of this configuration.
	 * 
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public final void removeSelectorToken() {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		selectorToken = null;
	}
	
	//method
	/**
	 * Removes the selector type of this configuration.
	 * 
	 * @throws Exception if this configuration is frozen
	 */
	public final void removeSelectorType() {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		selectorType = null;
	}
	
	//method
	/**
	 * Resets this configuration.
	 * 
	 * @return this configuration.
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public C reset() {

		removeSelectorType();
		removeSelectorRoles();
		removeSelectorToken();
		removeSelectorName();
		
		attachingAttributes.clear();
		configurations.clear();
		
		//Calls method of the base class.
		return super.reset();
	}
	
	//method
	/**
	 * @param element
	 * @return true if this configuration selects the given element.
	 */
	public final boolean selects(Configurable<?> element) {		
		
		//Handles the case that this configuration has a selector type.
		if (hasSelectorType() && !element.hasTypeOrSuperType(getSelectorType())) {
			return false;
		}
		
		//Handles the case that this configuration contains selector roles.
		if (containsSelectorRoles() && getSelectorRoles().containsNone(sr -> element.hasRole(sr))) {
			return false;
		}
		
		//Handles the case that this configuration has a selector token.
		if (hasSelectorToken() && !element.hasToken(getSelectorToken())) {
			return false;
		}
		
		//Handles the case that this configuration has a selector type.
		if (hasSelectorName() && !element.hasName(getSelectorName())) {
			return false;
		}

		return true;
	}
	
	//method
	/**
	 * Sets the given name to this configuration.
	 * 
	 * @param name
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public final C setName(final String name) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		//Calls method of the base class.
		super.setName(name);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the selector name of this configuration.
	 * 
	 * @param selectorName
	 * @return this configuration.
	 * @throws NullArgumentException if the given selector name is null.
	 * @throws EmptyArgumentException if the given selector name is empty.
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public final C setSelectorName(final String selectorName) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		this.selectorName = new NonEmptyText(selectorName);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the selector token of this configuration.
	 * 
	 * @param selectorToken
	 * @return this configuration.
	 * @throws NullArgumentException if the given selector token is null.
	 * @throws EmptyArgumentException if the given selector token is empty.
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public final C setSelectorToken(final String selectorToken) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		this.selectorToken = new NonEmptyText(selectorToken);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the selector type of this configuration.
	 * 
	 * @param selectorType
	 * @return this configuration.
	 * @throws NullArgumentException if the given type selector type is null.
	 * @throws EmptyArgumentException if the given selector type is empty.
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public final C setSelectorType(final String selectorType) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		this.selectorType = new NonEmptyText(selectorType);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the attaching attributes of this configuration to the given element.
	 * 
	 * @param element
	 * @throws InvalidArgumentException if an attaching attribute of this configuration
	 * is not valid for the given element.
	 */
	protected final void setAttachingAttributesTo(Configurable<?> element) {
		attachingAttributes.forEach(aa -> element.addOrChangeAttribute(aa));
	}
	
	//method
	/**
	 * @throws InvalidStateException if this configuration is frozen
	 */
	protected final void supposeNotFrozen() {
		
		//Checks if this configuration is not frozen.
		if (isFrozen()) {
			throw new InvalidStateException(this, "is frozen");
		}
	}
	
	//method
	/**
	 * Adds the given selector role to this configuration.
	 * 
	 * @param selectorRole
	 * @throws NullArgumentException if the given selector role is null.
	 * @throws EmptyArgumentException if the given selector role is empty.
	 * @throws InvalidStateException if this configuration contains already the given selector role.
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	private void addSelectorRole(final String selectorRole) {
		
		//Checks if this configuration contains the given selector role.
		if (containsSelectorRole(selectorRole)) {
			throw
			new InvalidStateException(
				this,
				"contains the given selector role '" + selectorRole + "'"
			);
		}
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		selectorRoles.addAtEnd(selectorRole);
	}
	
	//method
	/**
	 * Adds the given selector roles to this configuration.
	 * 
	 * @param selectorRoles
	 * @throws EmptyArgumentException if one of the given selector role is empty.
	 * @throws InvalidStateException if this configuration contains already one of the given selector roles.
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	private void addSelectorRoles_(final Iterable<String> selectorRoles) {
		selectorRoles.forEach(sr -> addSelectorRole(sr));
	}
}

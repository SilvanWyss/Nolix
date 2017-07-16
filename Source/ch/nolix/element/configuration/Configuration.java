//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.Freezable;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.Configurable;
import ch.nolix.element.basic.NonEmptyText;
import ch.nolix.element.basic.OptionalNamableElement;
import ch.nolix.element.data.Name;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 650
 * @param <C> The type of a configuration.
 */
public abstract class Configuration<C extends Configuration<C>>
extends OptionalNamableElement<C>
implements Freezable {

	//simple class name
	public static final String SIMPLE_CLASS_NAME = "Configuration";
	
	//attribute names
	private static final String SELECTOR_TYPE = "SelectorType";
	private static final String SELECTOR_ROLE = "SelectorRole";
	private static final String SELECTOR_TOKEN = "SelectorToken";
	private static final String SELECTOR_NAME = "SelectorName";
	
	//attribute
	private boolean frozen = false;
	
	//multiple attributes
	private final List<StandardSpecification> attachingAttributes
		= new List<StandardSpecification>();
	protected final List<Configuration<?>> configurations
		= new List<Configuration<?>>();
	
	//optional attributes
	private NonEmptyText selectorType;
	private NonEmptyText selectorRole;
	private NonEmptyText selectorToken;
	private NonEmptyText selectorName;
	
	//method
	/**
	 * Adds the given attaching attribute to this configuration.
	 * 
	 * @param attachingAttribute
	 * @return this configuration.
	 * @throws NullArgumentException if the given attaching attribute is null.
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	@SuppressWarnings("unchecked")
	public final C addAttachingAttribute(final StandardSpecification attachingAttribute) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
				
		attachingAttributes.addAtEnd(attachingAttribute);
		
		return (C)this;
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
	@SuppressWarnings("unchecked")
	public final C addAttachingAttribute(final String... attachingAttributes) {

		//Checks if this configuration is not frozen.
		supposeNotFrozen();		
		
		//Iterates the given attaching attributes.
		for (final String aa : attachingAttributes) {
			addAttachingAttribute(aa);
		}
		
		return (C)this;
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
	@SuppressWarnings("unchecked")
	public final C addConfiguration(final Configuration<?> configuration) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		configurations.addAtEnd(configuration);
		
		return (C)this;
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
	@SuppressWarnings("unchecked")
	public final C addConfiguration(final Configuration<?>...configurations) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		this.configurations.addAtEnd(configurations);
		
		return (C)this;
	}
	
	//method
	/**
	 * Adds or changes the given attribute to this configuration.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case Name.SIMPLE_CLASS_NAME:
				setName(attribute.getOneAttributeToString());
				break;
			case SELECTOR_TYPE:
				setSelectorType(attribute.getOneAttributeToString());
				break;
			case SELECTOR_ROLE:
				setSelectorRole(attribute.getOneAttributeToString());
				break;
			case SELECTOR_TOKEN:
				setSelectorToken(attribute.getOneAttributeToString());
				break;
			case SELECTOR_NAME:
				setSelectorName(attribute.getOneAttributeToString());
				break;
			case StandardConfiguration.SIMPLE_CLASS_NAME:
				addConfiguration(new StandardConfiguration(attribute.getRefAttributes()));
				break;
			case DeepConfiguration.SIMPLE_CLASS_NAME:
				addConfiguration(new DeepConfiguration(attribute.getRefAttributes()));
				break;
			default:
				addAttachingAttribute(attribute);
		}
	}
	
	//abstract method
	/**
	 * Lets this configuration configure the given element.
	 * 
	 * @param element
	 */
	public abstract void configure(Configurable element);
	
	//method
	/**
	 * Freezes this configuration.
	 */
	public final void freeze() {	
		frozen = true;
	}
	
	//method
	/**
	 * @return the attributes of this configuration.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		//Handles the option that this configuration has a selector type.
		if (hasSelectorType()) {
			attributes.addAtEnd(selectorType.getSpecificationAs(SELECTOR_TYPE));
		}
		
		//Handles the option that this configuration has a selector role.
		if (hasSelectorRole()) {
			attributes.addAtEnd(selectorRole.getSpecificationAs(SELECTOR_ROLE));
		}
		
		//Handles the option that this configuration has a selector token.
		if (hasSelectorToken()) {
			attributes.addAtEnd(selectorToken.getSpecificationAs(SELECTOR_TOKEN));
		}
		
		//Handles the option that this configuration has a selector name.
		if (hasSelectorName()) {
			attributes.addAtEnd(selectorName.getSpecificationAs(SELECTOR_NAME));
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
	 * @return the selector role of this configuration
	 * @throws UnexistingAttributeException if this configuration has no selector role.
	 */
	public final String getSelectorRole() {
		
		//Checks if this configuration has a selector role.
		if (!hasSelectorRole()) {
			throw new UnexistingAttributeException(this, "selector role");
		}
		
		return selectorRole.getValue();
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
		
		//Handles the case if this configuration has no selector name.
		if (!hasSelectorName()) {
			return false;
		}
		
		//Handles the case if this configuration has a selector name.
		return getSelectorName().equals(selectorName);
	}
	
	//method
	/**
	 * @return true if this configuration has a selector role.
	 */
	public final boolean hasSelectorRole() {
		return (selectorRole != null);
	}
	
	//method
	/**
	 * @param selectorRole
	 * @return true if this configuration has the given selector role.
	 */
	public final boolean hasSelectorRole(final String selectorRole) {
		
		//Handles the case if this configuration has no selector role.
		if (!hasSelectorRole()) {
			return false;
		}
		
		//Handles the case if this configuration has a selector role.
		return getSelectorRole().equals(selectorRole);
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
		
		//Handles the case if this configuration has no selector token.
		if (!hasSelectorToken()) {
			return false;
		}
		
		//Handles the case if this configuration as a selector token.
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
		
		//Handles the case if this configuration has no selector type.
		if (!hasSelectorType()) {
			return false;
		}
		
		//Handles the case if htis configuration has a selector type.
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
	 * Removes the selector role of this configuration.
	 * 
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public final void removeSelectorRole() {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		selectorRole = null;
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
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	public void reset() {

		removeSelectorType();
		removeSelectorRole();
		removeSelectorToken();
		removeSelectorName();
		
		attachingAttributes.clear();
		configurations.clear();
	}
	
	//method
	/**
	 * @param element
	 * @return true if this configuration selects the given element.
	 */
	public final boolean selects(Configurable element) {		
		
		//Handles the option that this configuration has a selector type.
		if (hasSelectorType() && !element.hasTypeOrSuperType(getSelectorType())) {
			return false;
		}
		
		//Handles the option that this configuration has a selector role.
		if (hasSelectorRole() && !element.hasRole(getSelectorRole())) {
			return false;
		}
		
		//Handles the option that this configuration has a selector token.
		if (hasSelectorToken() && !element.hasToken(getSelectorToken())) {
			return false;
		}
		
		//Handles the option that this configuration has a selector type.
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
	@SuppressWarnings("unchecked")
	public final C setName(final String name) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		//Calls method of the base class.
		super.setName(name);
		
		return (C)this;
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
	@SuppressWarnings("unchecked")
	public final C setSelectorName(final String selectorName) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		this.selectorName = new NonEmptyText(selectorName);
		
		return (C)this;
	}
	
	//method
	/**
	 * Sets the selector role of this configuration.
	 * @param selectorRole
	 * @return this configuration.
	 * @throws InvalidStateException if this configurtion is frozen.
	 */
	public final C setSelectorRole(final Enum<?> selectorRole) {
		return setSelectorRole(selectorRole.toString());
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
	@SuppressWarnings("unchecked")
	public final C setSelectorToken(final String selectorToken) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		this.selectorToken = new NonEmptyText(selectorToken);
		
		return (C)this;
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
	@SuppressWarnings("unchecked")
	public final C setSelectorType(final String selectorType) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		this.selectorType = new NonEmptyText(selectorType);
		
		return (C)this;
	}
	
	//method
	/**
	 * Sets the attaching attributes of this configuration to the given element.
	 * 
	 * @param element
	 * @throws InvalidArgumentException if an attaching attribute of this configuration
	 * is not valid for the given element.
	 */
	protected final void setAttachingAttributesTo(Configurable element) {
		attachingAttributes.forEach(aa -> element.addOrChangeAttribute(aa));
	}
	
	//method
	/**
	 * Sets the selector role of this configuration.
	 * 
	 * @param selectorRole
	 * @return this configuration.
	 * @throws NullArgumentException if the given selector role is null.
	 * @throws EmptyArgumentException if the given selector role is empty.
	 * @throws InvalidStateException if this configuration is frozen.
	 */
	@SuppressWarnings("unchecked")
	private final C setSelectorRole(final String selectorRole) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		this.selectorRole = new NonEmptyText(selectorRole);
		
		return (C)this;
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
}

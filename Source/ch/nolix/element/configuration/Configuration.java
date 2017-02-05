/*
 * file:	Configuration.java
 * author:	Silvan Wyss
 * month:	2016-01
 * lines:	530
 */

//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.interfaces.Freezable;
import ch.nolix.common.specification.Configurable;
import ch.nolix.common.specification.Specification;
import ch.nolix.element.basic.NamableElement;
import ch.nolix.element.basic.NonEmptyText;
import ch.nolix.element.data.Name;

//abstract class
public abstract class Configuration<C extends Configuration<C>>
extends NamableElement<C>
implements Freezable {

	//constant
	public static final String SIMPLE_CLASS_NAME = "Configuration";
	
	//attribute names
	private static final String SELECTOR_TYPE = "SelectorType";
	private static final String SELECTOR_ROLE = "SelectorRole";
	private static final String SELECTOR_TOKEN = "SelectorToken";
	private static final String SELECTOR_NAME = "SelectorName";
	
	//attributes
	private final List<Specification> attachingAttributes = new List<Specification>();
	protected final List<Configuration<?>> configurations = new List<Configuration<?>>();
	private boolean frozen = false;
	
	//optional attributes
	private NonEmptyText selectorType;
	private NonEmptyText selectorRole;
	private NonEmptyText selectorToken;
	private NonEmptyText selectorName;
	
	//package-visible constructor
	/**
	 * Creates new configuration.
	 */
	Configuration() {};
	
	//method
	/**
	 * Adds the given attaching attribute to this configuration.
	 * 
	 * @param attachingAttribute
	 * @return this configuration
	 * @throws Exception if:
	 * -The given attaching attributes is null.
	 * -This configuration is frozen.
	 */
	@SuppressWarnings("unchecked")
	public final C addAttachingAttribute(Specification attachingAttribute) {
		
		throwExceptionIfFrozen();
				
		this.attachingAttributes.addAtEnd(attachingAttribute);
		
		return (C)this;
	}
	
	//method
	/**
	 * Adds the given attaching attribute to this configuration.
	 * 
	 * @param attachingAttribute
	 * @return this configuration
	 * @throws Exception if:
	 * -The given attaching attribute is not valid
	 * -This configuratoin is frozen
	 */
	public final C addAttachingAttribute(String attachingAttribute) {
		
		throwExceptionIfFrozen();
		
		return addAttachingAttribute(new Specification(attachingAttribute));
	}
	
	//method
	/**
	 * Adds the given attaching attributes to this configuration.
	 * 
	 * @param attachingAttributes
	 * @return this configuration
	 * @throws Exception if:
	 * -One of the given attaching attributes is not valid.
	 * -This configuration is frozen.
	 */
	@SuppressWarnings("unchecked")
	public final C addAttachingAttribute(String... attachingAttributes) {

		throwExceptionIfFrozen();
		
		//Iterates the given attaching attributes.
		for (String aa: attachingAttributes) {
			addAttachingAttribute(aa);
		}
		
		return (C)this;
	}
	
	//method
	/**
	 * Adds the given configuration to this configuration.
	 * 
	 * @param configuration
	 * @return this configuration
	 * @throws 
	 * -Excetion if the given configuration is null.
	 * -This configuration is null.
	 */
	@SuppressWarnings("unchecked")
	public final C addConfiguration(Configuration<?> configuration) {
		
		throwExceptionIfFrozen();
		
		configurations.addAtEnd(configuration);
		
		return (C)this;
	}
	
	//method
	/**
	 * Adds the given configurations to this configuration.
	 * 
	 * @param configurations
	 * @return this configuration
	 * @throws Exception if:
	 * -One of the given configurations is null.
	 * -This configuration is frozen.
	 */
	@SuppressWarnings("unchecked")
	public final C addConfiguration(Configuration<?>...configurations) {
		
		throwExceptionIfFrozen();
		
		this.configurations.addAtEnd(configurations);
		
		return (C)this;
	}
	
	//abstract method
	/**
	 * Lets this configuration configure the given element.
	 * 
	 * @param element
	 */
	public abstract void configure(Configurable element);
	
	public final void freeze() {
		
		throwExceptionIfFrozen();
		
		frozen = true;
	}
	
	//method
	/**
	 * @return the attributes of this specifiable object
	 */
	public List<Specification> getAttributes() {
		
		final List<Specification> attributes = super.getAttributes();
		
		if (hasSelectorType()) {
			attributes.addAtEnd(selectorType.getSpecificationAs(SELECTOR_TYPE));
		}
		
		if (hasSelectorRole()) {
			attributes.addAtEnd(selectorRole.getSpecificationAs(SELECTOR_ROLE));
		}
		
		if (hasSelectorToken()) {
			attributes.addAtEnd(selectorToken.getSpecificationAs(SELECTOR_TOKEN));
		}
		
		if (hasSelectorName()) {
			attributes.addAtEnd(selectorName.getSpecificationAs(SELECTOR_NAME));
		}
				
		attributes.addAtEnd(attachingAttributes);
		configurations.forEach(c -> attributes.addAtEnd(c.getSpecification()));
		
		return attributes;
	}
	
	//method
	/**
	 * @return the selector name of this configuration.
	 * @throws Exception if this configuration has no selector name
	 */
	public final String getSelectorName() {
		
		if (!hasSelectorName()) {
			throw new UnexistingAttributeException("configuration", "selector name");
		}
		
		return selectorName.getValue();
	}
	
	//method
	/**
	 * @return the selector role of this configuration
	 * @throws Exception if this configuration has no selector role
	 */
	public final String getSelectorRole() {
		
		if (!hasSelectorRole()) {
			throw new UnexistingAttributeException(this, "selector role");
		}
		
		return selectorRole.getValue();
	}
	
	//method
	/**
	 * @return the selector token of this configuration
	 * @throws Exception if this configuration has no selector token
	 */
	public final String getSelectorToken() {
		
		if (!hasSelectorToken()) {
			throw new UnexistingAttributeException(this, "selector token");
		}
		
		return selectorToken.getValue();
	}
	
	public final boolean hasAttachingAttributes() {
		return attachingAttributes.containsAny();
	}
	
	//method
	/**
	 * @return the selector SIMPLE_CLASS_NAME of this configuration
	 * @throws Exception uf this configuration has no selector SIMPLE_CLASS_NAME
	 */
	public final String getSelectorType() {
		
		if (!hasSelectorType()) {
			throw new UnexistingAttributeException("configuration", "selector SIMPLE_CLASS_NAME");
		}
		
		return selectorType.getValue();
	}
	
	//method
	/**
	 * @return true if this configuration has a selector token
	 */
	public final boolean hasSelectorToken() {
		return (selectorToken != null);
	}
	
	//method
	/**
	 * @param selectorToken
	 * @return true if this configuration has the given selector token
	 */
	public final boolean hasSelectorToken(String selectorToken) {
		return (hasSelectorToken() && this.selectorToken.equals(selectorToken));
	}
	
	//method
	/**
	 * @return true if this configuration has a selector name
	 */
	public final boolean hasSelectorName() {
		return (selectorName != null);
	}
	
	//method
	/**
	 * @param selectorName
	 * @return true if this configuration has the given selector name
	 */
	public final boolean hasSelectorName(String selectorName) {
		return (hasSelectorName() && this.selectorName.equals(selectorName));
	}
	
	//method
	/**
	 * @return true if this configuration has a selector role
	 */
	public final boolean hasSelectorRole() {
		return (selectorRole != null);
	}
	
	public final boolean hasSelectorRole(String selectorRole) {
		return (hasSelectorName() && this.selectorRole.equals(selectorRole));
	}
	
	//method
	/**
	 * @return true if this configuration has a selector type
	 */
	public final boolean hasSelectorType() {
		return (selectorType != null);
	}
	
	//method
	/**
	 * @param selectorSIMPLE_CLASS_NAME
	 * @return true if this configuration has the given selector type
	 */
	public final boolean hasSelectorType(String selectorType) {
		return (hasSelectorType() && this.selectorType.equals(selectorType));
	}
	
	//method
	/**
	 * Removes the selector token of this configuration.
	 * 
	 * @throws Exception if this configuration is frozen
	 */
	public final void removeSelectorToken() {
		
		throwExceptionIfFrozen();
		
		selectorToken = null;
	}
	
	//method
	/**
	 * Removes the selector name of this configuration.
	 * 
	 * @throws Exception if this configuration is frozen
	 */
	public final void removeSelectorName() {
		
		throwExceptionIfFrozen();
		
		selectorName = null;
	}
	
	//method
	/**
	 * Removes the selector type of this configuration.
	 * 
	 * @throws Exception if this configuration is frozen
	 */
	public final void removeSelectorType() {
		
		throwExceptionIfFrozen();
		
		selectorType = null;
	}
	
	//method
	/**
	 * Resets this configuration.
	 * 
	 * @throws Exception if this configuration is frozen
	 */
	public void reset() {
		
		throwExceptionIfFrozen();
		
		removeSelectorType();
		removeSelectorToken();
		removeSelectorName();
		
		attachingAttributes.clear();
		configurations.clear();
	}
	
	//method
	/**
	 * @param element
	 * @return true if this configuration selects the given element
	 */
	public final boolean selects(Configurable element) {		
			
		if (hasSelectorType() && !element.hasTypeOrSuperType(getSelectorType())) {
			return false;
		}
		
		if (hasSelectorRole() && !element.hasRole(getSelectorRole())) {
			return false;
		}
		
		if (hasSelectorToken() && !element.hasToken(getSelectorToken())) {
			return false;
		}
		
		if (hasSelectorName() && !element.hasName(getSelectorName())) {
			return false;
		}

		return true;
	}
	
	//method
	/**
	 * Adds the given attribute.
	 * @param attribute
	 * @throws Exception if:
	 * -The given attribute is not valid
	 * -This configuration is frozen.
	 */
	public void addOrChangeAttribute(Specification attribute) {
		
		throwExceptionIfFrozen();
		
		switch (attribute.getHeader()) {
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
			case Name.SIMPLE_CLASS_NAME:
				setName(attribute.getOneAttributeToString());
				break;
			default:
				addAttachingAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Sets the selector role of this configuration.
	 * 
	 * @param selectorRole
	 * @return this configuration
	 * @throws Exception if:
	 * -The given selector role is null or an empty string.
	 * -This configuration is frozen.
	 */
	@SuppressWarnings("unchecked")
	public final C setSelectorRole(String selectorRole) {
		
		throwExceptionIfFrozen();
		
		this.selectorRole = new NonEmptyText(selectorRole);
		
		return (C)this;
	}
	
	//method
	/**
	 * Sets the selector token of this configuration.
	 * 
	 * @param selectorToken
	 * @return this configuration
	 * @throws Exception if:
	 * -The given selector token is null or an empty string.
	 * -This configuration is frozen.
	 */
	@SuppressWarnings("unchecked")
	public final C setSelectorToken(String selectorToken) {
		
		throwExceptionIfFrozen();
		
		this.selectorToken = new NonEmptyText(selectorToken);
		
		return (C)this;
	}
	
	//method
	/**
	 * Sets the selector name of this configuration.
	 * 
	 * @param selectorName
	 * @return this configuration
	 * @throws Exception if:
	 * -The given selector name is an empty string.
	 * -This configuration is frozen.
	 */
	@SuppressWarnings("unchecked")
	public final C setSelectorName(String selectorName) {
		
		throwExceptionIfFrozen();
		
		this.selectorName = new NonEmptyText(selectorName);
		
		return (C)this;
	}
	
	//method
	/**
	 * Sets the selector SIMPLE_CLASS_NAME of this configuration.
	 * 
	 * @param selectorSIMPLE_CLASS_NAME
	 * @return this configuration
	 * @throws Exception if:
	 * -The given selector type is null or an empty string.
	 * -This configuration is frozen.
	 */
	@SuppressWarnings("unchecked")
	public final C setSelectorType(String selectorSIMPLE_CLASS_NAME) {
		
		throwExceptionIfFrozen();
		
		this.selectorType = new NonEmptyText(selectorSIMPLE_CLASS_NAME);
		
		return (C)this;
	}
	
	//method
	/**
	 * Sets the attaching attributes of this configuration to the given element.
	 * 
	 * @param element
	 * @throws Exception if an attaching attribute of this configuration is not valid for the given element
	 */
	protected final void setAttachingAttributesTo(Configurable element) {
		for (Specification a: attachingAttributes) {
			element.addOrChangeAttribute(a);
		}
	}
	
	//method
	/**
	 * @throws Exception if this configuration is frozen
	 */
	protected final void throwExceptionIfFrozen() {
		if (frozen) {
			throw new RuntimeException("Configuration is frozen.");
		}
	}
}

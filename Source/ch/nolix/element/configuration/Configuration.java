//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.core.attributeAPI.OptionalNamable;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.skillAPI.Freezable;
import ch.nolix.core.specificationAPI.Configurable;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.core.MutableElement;
import ch.nolix.element.core.NonEmptyText;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 710
 * @param <C> The type of a configuration.
 */
public abstract class Configuration<C extends Configuration<C>> extends MutableElement<C>
implements Freezable<C>, OptionalNamable<C> {
	
	//attribute headers
	private static final String SELECTOR_TYPE_HEADER = "SelectorType";
	private static final String SELECTOR_ROLE_HEADER = "SelectorRole";
	private static final String SELECTOR_TOKEN_HEADER = "SelectorToken";
	private static final String SELECTOR_NAME_HEADER = "SelectorName";
	
	//attribute
	private boolean frozen = false;
	
	//optional attribute
	private String name;
	
	//multi-attributes
	private final List<DocumentNode> attachingAttributes
		= new List<DocumentNode>();
	protected final List<Configuration<?>> configurations
		= new List<Configuration<?>>();
	
	//optional attributes
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
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	public final C addAttachingAttribute(final DocumentNodeoid attachingAttribute) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
				
		attachingAttributes.addAtEnd(
			new DocumentNode(
				attachingAttribute.getHeader(),
				attachingAttribute.getRefAttributes())
		);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Adds the given attaching attribute to this configuration.
	 * 
	 * @param attachingAttribute
	 * @return this configuration
	 * @throws InvalidArgumentException
	 * if the given attachingAttribute does not represent a standard specification.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	public final C addAttachingAttribute(final String attachingAttribute) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		return addAttachingAttribute(new DocumentNode(attachingAttribute));
	}
	
	//method
	/**
	 * Adds the given attaching attributes to this configuration.
	 * 
	 * @param attachingAttributes
	 * @return this configuration.
	 * @throws InvalidArgumentException
	 * if one of the given attaching attributes does not represent a standard specification.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	public final C addAttachingAttribute(final String... attachingAttributes) {

		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		//Iterates the given attaching attributes.
		for (final String aa : attachingAttributes) {
			addAttachingAttribute(aa);
		}
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Adds the given configuration to this configuration.
	 * 
	 * @param configuration
	 * @return this configuration.
	 * @throws NullArgumentException if the given configuration is null.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	public final C addConfiguration(final Configuration<?> configuration) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		configurations.addAtEnd(configuration);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Adds the given configurations to this configuration.
	 * 
	 * @param configurations
	 * @return this configuration.
	 * @throws NullArgumentException if one of the given configurations is null.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	public final C addConfiguration(final Configuration<?>...configurations) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		this.configurations.addAtEnd(configurations);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Adds or changes the given attribute to this configuration.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
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
	 * @throws InvalidArgumentException if this configuration contains already the given selector role.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	public final C addSelectorRole(final Enum<?> selectorRole) {
		
		addSelectorRole(selectorRole.toString());
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Adds the given selector roles to this configuration.
	 * 
	 * @param selectorRoles
	 * @return this configuration.
	 * @throws InvalidArgumentException if this configuration contains already one of the given selector role.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	public final C addSelectorRole(final Enum<?>... selectorRoles) {
		
		//Iterates the given selector roles.
		for (final var sr : selectorRoles) {
			addSelectorRole(sr);
		}
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Adds the given selector roles to this configuration.
	 * 
	 * @param selectorRoles
	 * @return this configuration.
	 * @throws InvalidArgumentException if this configuration contains already one of the given selector role.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	public final C addSelectorRoles(final Iterable<Enum<?>> selectorRoles) {
		
		selectorRoles.forEach(sr -> addSelectorRole(sr));
		
		return asConcreteType();
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
	@Override
	public final C freeze() {
		
		frozen = true;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * @return the attributes of this configuration.
	 */
	@Override
	public List<DocumentNode> getAttributes() {
		
		//Calls method of the base class.
		final List<DocumentNode> attributes = super.getAttributes();
		
		//Handles the case that this configuration has a name
		if (hasName()) {
			attributes.addAtBegin(new DocumentNode(PascalCaseNameCatalogue.NAME, name));
		}
		
		//Handles the case that this configuration has a selector type.
		if (hasSelectorType()) {
			attributes.addAtEnd(selectorType.getSpecificationAs(SELECTOR_TYPE_HEADER));
		}
		
		//Handles the case that this configuration contains selector roles.		
		if (containsSelectorRoles()) {
			
			final var specification = new DocumentNode(SELECTOR_ROLE_HEADER);
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
	 * @return the name of this configuration.
	 * @throws ArgumentMissesAttributeException if this configuration does not have a name.
	 */
	public final String getName() {
		
		//Checks if this configuration has a a name.
		//For a better performance, this implementation does not use all comfortable methods.
		if (name == null) {
			throw new ArgumentMissesAttributeException(this, VariableNameCatalogue.NAME);
		}
		
		return name;
	}
	
	//method
	/**
	 * @return the selector name of this configuration.
	 * @throws ArgumentMissesAttributeException if this configuration does not have a selector name.
	 */
	public final String getSelectorName() {
		
		//Checks if this configuration has a selector name.
		if (!hasSelectorName()) {
			throw new ArgumentMissesAttributeException(this, "selector name");
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
	 * @throws ArgumentMissesAttributeException if this configuration does not have a selector token.
	 */
	public final String getSelectorToken() {
		
		//Checks if this configuration has a selector token.
		if (!hasSelectorToken()) {
			throw new ArgumentMissesAttributeException(this, "selector token");
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
	 * @throws ArgumentMissesAttributeException if this configuration does not have a selector type.
	 */
	public final String getSelectorType() {
		
		//Checks if this configuration has a selector type.
		if (!hasSelectorType()) {
			throw new ArgumentMissesAttributeException(this, "selector type");
		}
		
		return selectorType.getValue();
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
		
		//Handles the case that this configuration does not have a selector name.
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
		
		//Handles the case that this configuration does not have a selector token.
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
		
		//Handles the case that this configuration does not have a selector type.
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
	@Override
	public final boolean isFrozen() {
		return frozen;
	}
	
	//method
	/**
	 * Removes the selector name of this configuration.
	 * 
	 * @throws InvalidArgumentException if this configuration is frozen.
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
	 * @throws InvalidArgumentException if this configuration is frozen.
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
	 * @throws InvalidArgumentException if this configuration is frozen.
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
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	@Override
	public C reset() {
		
		removeName();
		
		removeSelectorType();
		removeSelectorRoles();
		removeSelectorToken();
		removeSelectorName();
		
		attachingAttributes.clear();
		configurations.clear();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public C removeName() {
		
		name = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * @param element
	 * @return true if this configuration selects the given element.
	 */
	public final boolean selects(Configurable<?> element) {
		
		//Handles the case that this configuration has a selector type.
		if (hasSelectorType() && !element.isOfType(getSelectorType())) {
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
	 * Sets the name of this configuration.
	 * 
	 * @param name
	 * @return this configuration.
	 * @throws NullArgumentException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	@Override
	public final C setName(final String name) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		this.name = Validator.suppose(name).thatIsNamed(VariableNameCatalogue.NAME).isNotBlank().andReturn();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the selector name of this configuration.
	 * 
	 * @param selectorName
	 * @return this configuration.
	 * @throws NullArgumentException if the given selector name is null.
	 * @throws EmptyArgumentException if the given selector name is empty.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	public final C setSelectorName(final String selectorName) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		this.selectorName = new NonEmptyText(selectorName);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the selector token of this configuration.
	 * 
	 * @param selectorToken
	 * @return this configuration.
	 * @throws NullArgumentException if the given selector token is null.
	 * @throws EmptyArgumentException if the given selector token is empty.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	public final C setSelectorToken(final String selectorToken) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		this.selectorToken = new NonEmptyText(selectorToken);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the selector type of this configuration.
	 * 
	 * @param selectorType
	 * @return this configuration.
	 * @throws NullArgumentException if the given type selector type is null.
	 * @throws EmptyArgumentException if the given selector type is empty.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	public final C setSelectorType(final String selectorType) {
		
		//Checks if this configuration is not frozen.
		supposeNotFrozen();
		
		this.selectorType = new NonEmptyText(selectorType);
		
		return asConcreteType();
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
	 * @throws InvalidArgumentException if this configuration is frozen
	 */
	protected final void supposeNotFrozen() {
		
		//Checks if this configuration is not frozen.
		if (isFrozen()) {
			throw new InvalidArgumentException(this, "is frozen");
		}
	}
	
	//method
	/**
	 * Adds the given selector role to this configuration.
	 * 
	 * @param selectorRole
	 * @throws NullArgumentException if the given selector role is null.
	 * @throws EmptyArgumentException if the given selector role is empty.
	 * @throws InvalidArgumentException if this configuration contains already the given selector role.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	private void addSelectorRole(final String selectorRole) {
		
		//Checks if this configuration contains the given selector role.
		if (containsSelectorRole(selectorRole)) {
			throw
			new InvalidArgumentException(
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
	 * @throws InvalidArgumentException if this configuration contains already one of the given selector roles.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	private void addSelectorRoles_(final Iterable<String> selectorRoles) {
		selectorRoles.forEach(sr -> addSelectorRole(sr));
	}
}

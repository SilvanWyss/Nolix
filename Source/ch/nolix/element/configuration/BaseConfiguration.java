//package declaration
package ch.nolix.element.configuration;

import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.mutableOptionalAttributeAPI.OptionalNamable;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.skillAPI.Freezable;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.elementAPI.IConfigurableElement;
import ch.nolix.element.elementAPI.IMutableElement;

//class
/**
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 800
 * @param <C> The type of a configuration.
 */
public abstract class BaseConfiguration<C extends BaseConfiguration<C>> extends Element<C>
implements Freezable<C>, OptionalNamable<C>, IMutableElement<C> {
	
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
	private final LinkedList<Node> attachingAttributes = new LinkedList<>();
	protected final LinkedList<BaseConfiguration<?>> configurations = new LinkedList<>();
	
	//optional attributes
	private String selectorType;
	private String selectorToken;
	private String selectorId;
	
	//multi-attribute
	private final LinkedList<String> selectorRoles = new LinkedList<>();
	
	//method
	/**
	 * Adds the given attaching attribute to this configuration.
	 * 
	 * @param attachingAttribute
	 * @return this configuration.
	 * @throws ArgumentIsNullException if the given attaching attribute is null.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	public final C addAttachingAttribute(final BaseNode attachingAttribute) {
		
		//Asserts that this configuration is not frozen.
		supposeNotFrozen();
				
		attachingAttributes.addAtEnd(
			new Node(
				attachingAttribute.getHeader(),
				attachingAttribute.getRefAttributes())
		);
		
		return asConcrete();
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
		
		//Asserts that this configuration is not frozen.
		supposeNotFrozen();
		
		return addAttachingAttribute(Node.fromString(attachingAttribute));
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

		//Asserts that this configuration is not frozen.
		supposeNotFrozen();
		
		//Iterates the given attaching attributes.
		for (final String aa : attachingAttributes) {
			addAttachingAttribute(aa);
		}
		
		return asConcrete();
	}
	
	//method
	/**
	 * Adds the given configuration to this configuration.
	 * 
	 * @param configuration
	 * @return this configuration.
	 * @throws ArgumentIsNullException if the given configuration is null.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	public final C addConfiguration(final BaseConfiguration<?> configuration) {
		
		//Asserts that this configuration is not frozen.
		supposeNotFrozen();
		
		configurations.addAtEnd(configuration);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Adds the given configurations to this configuration.
	 * 
	 * @param configurations
	 * @return this configuration.
	 * @throws ArgumentIsNullException if one of the given configurations is null.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	public final C addConfiguration(final BaseConfiguration<?>...configurations) {
		
		//Asserts that this configuration is not frozen.
		supposeNotFrozen();
		
		this.configurations.addAtEnd(configurations);
		
		return asConcrete();
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
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.NAME:
				setName(attribute.getOneAttributeHeader());
				break;
			case SELECTOR_TYPE_HEADER:
				setSelectorType(attribute.getOneAttributeHeader());
				break;
			case SELECTOR_ROLE_HEADER:
				addSelectorRoles2(attribute.getAttributesAsStrings());
				break;
			case SELECTOR_TOKEN_HEADER:
				setSelectorToken(attribute.getOneAttributeHeader());
				break;
			case SELECTOR_NAME_HEADER:
				setSelectorId(attribute.getOneAttributeHeader());
				break;
			case Configuration.TYPE_NAME:
				addConfiguration(new Configuration(attribute.getRefAttributes()));
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
		
		return asConcrete();
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
		
		return asConcrete();
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
		
		return asConcrete();
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
	
	//method declaration
	/**
	 * Lets this configuration configure the given element.
	 * 
	 * @param element
	 */
	public abstract void configure(IConfigurableElement<?> element);
	
	//method
	/**
	 * Freezes this configuration.
	 * 
	 * @return this configuration.
	 */
	@Override
	public final C freeze() {
		
		frozen = true;
		
		return asConcrete();
	}
	
	//method
	/**
	 * @return the attributes of this configuration.
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		
		//Calls method of the base class.
		final LinkedList<Node> attributes = super.getAttributes();
		
		//Handles the case that this configuration has a name
		if (hasName()) {
			attributes.addAtBegin(new Node(PascalCaseNameCatalogue.NAME, name));
		}
		
		//Handles the case that this configuration has a selector type.
		if (hasSelectorType()) {
			attributes.addAtEnd(new Node(SELECTOR_TYPE_HEADER, selectorType));
		}
		
		//Handles the case that this configuration contains selector roles.		
		if (containsSelectorRoles()) {
			
			final var specification = Node.fromString(SELECTOR_ROLE_HEADER);
			getSelectorRoles().forEach(sr -> specification.addAttribute(sr));
			
			attributes.addAtEnd(specification);
		}
		
		//Handles the case that this configuration has a selector token.
		if (hasSelectorToken()) {
			attributes.addAtEnd(new Node(SELECTOR_TOKEN_HEADER, selectorToken));
		}
		
		//Handles the case that this configuration has a selector id.
		if (hasSelectorId()) {
			attributes.addAtEnd(new Node(SELECTOR_NAME_HEADER, selectorId));
		}
		
		attributes.addAtEnd(attachingAttributes);
		attributes.addAtEnd(configurations, c -> c.getSpecification());
		
		return attributes;
	}
	
	//method
	/**
	 * @return the name of this configuration.
	 * @throws ArgumentDoesNotHaveAttributeException if this configuration does not have a name.
	 */
	public final String getName() {
		
		//Asserts that this configuration has a a name.
		//For a better performance, this implementation does not use all comfortable methods.
		if (name == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.NAME);
		}
		
		return name;
	}
	
	//method
	/**
	 * @return the selector id of this configuration.
	 * @throws ArgumentDoesNotHaveAttributeException if this configuration does not have a selector id.
	 */
	public final String getSelectorName() {
		
		//Asserts that this configuration has a selector id.
		if (!hasSelectorId()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "selector id");
		}
		
		return selectorId;
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
	 * @throws ArgumentDoesNotHaveAttributeException if this configuration does not have a selector token.
	 */
	public final String getSelectorToken() {
		
		//Asserts that this configuration has a selector token.
		if (!hasSelectorToken()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "selector token");
		}
		
		return selectorToken;
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
	 * @throws ArgumentDoesNotHaveAttributeException if this configuration does not have a selector type.
	 */
	public final String getSelectorType() {
		
		//Asserts that this configuration has a selector type.
		if (!hasSelectorType()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "selector type");
		}
		
		return selectorType;
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
	 * @return true if this configuration has a selector id.
	 */
	public final boolean hasSelectorId() {
		return (selectorId != null);
	}
	
	//method
	/**
	 * @param selectorId
	 * @return true if this configuration has the given selector id.
	 */
	public final boolean hasSelectorId(final String selectorId) {
		
		//Handles the case that this configuration does not have a selector id.
		if (!hasSelectorId()) {
			return false;
		}
		
		//Handles the case that this configuration has a selector id.
		return getSelectorName().equals(selectorId);
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
	 * Removes the selector id of this configuration.
	 * 
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	public final void removeSelectorName() {
		
		//Asserts that this configuration is not frozen.
		supposeNotFrozen();
		
		selectorId = null;
	}
	
	//method
	/**
	 * Removes the selector roles of this configuration.
	 * 
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	public final void removeSelectorRoles() {
		
		//Asserts that this configuration is not frozen.
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
		
		//Asserts that this configuration is not frozen.
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
		
		//Asserts that this configuration is not frozen.
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
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public C removeName() {
		
		name = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * @param element
	 * @return true if this configuration selects the given element.
	 */
	public final boolean selects(IConfigurableElement<?> element) {
		
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
		if (hasSelectorId() && !element.hasId(getSelectorName())) {
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
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	@Override
	public final C setName(final String name) {
		
		//Asserts that this configuration is not frozen.
		supposeNotFrozen();
		
		this.name = Validator.assertThat(name).thatIsNamed(VariableNameCatalogue.NAME).isNotBlank().andReturn();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the selector id of this configuration.
	 * 
	 * @param selectorId
	 * @return this configuration.
	 * @throws ArgumentIsNullException if the given selector id is null.
	 * @throws InvalidArgumentException if the given selector id is blank.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	public final C setSelectorId(final String selectorId) {
		
		//Asserts that the given selectorId is not null or blank.
		Validator.assertThat(selectorId).thatIsNamed("selectorId").isNotBlank();
		
		//Asserts that this configuration is not frozen.
		supposeNotFrozen();
		
		//Sets the selectorId of the current Configuration.
		this.selectorId = selectorId;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the selector token of this configuration.
	 * 
	 * @param selectorToken
	 * @return this configuration.
	 * @throws ArgumentIsNullException if the given selector token is null.
	 * @throws InvalidArgumentException if the given selector token is blank.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	public final C setSelectorToken(final String selectorToken) {
		
		//Asserts that the given selectorToken is not null or blank.
		Validator.assertThat(selectorToken).thatIsNamed("selectorToken").isNotBlank();
		
		//Asserts that this configuration is not frozen.
		supposeNotFrozen();
		
		//Sets the selectorToken of the current Configuration.
		this.selectorToken = selectorToken;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the selector type of the current {@link BaseConfiguration}.
	 * 
	 * @param selectorType
	 * @return the current {@link BaseConfiguration}.
	 * @throws ArgumentIsNullException if the given selectorType is null.
	 * @throws FrozenArgumentException if the current {@link BaseConfiguration} is frozen.
	 */
	public final C setSelectorType(final Class<?> selectorType) {
		
		//Asserts that the given selectorType is not null.
		Validator.assertThat(selectorType).thatIsNamed("selector type").isNotNull();
		
		return setSelectorType(selectorType.getSimpleName());
	}
	
	//method
	/**
	 * Sets the selector type of this configuration.
	 * 
	 * @param selectorType
	 * @return this configuration.
	 * @throws ArgumentIsNullException if the given type selector type is null.
	 * @throws InvalidArgumentException if the given selector type is blank.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	public final C setSelectorType(final String selectorType) {
		
		//Asserts that the given selectorType is not null or blank.
		Validator.assertThat(selectorType).thatIsNamed("selectorType").isNotBlank();
		
		//Asserts that this configuration is not frozen.
		supposeNotFrozen();
		
		//Sets the selectorType of the current Configuration.
		this.selectorType = selectorType;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the attaching attributes of this configuration to the given element.
	 * 
	 * @param element
	 * @throws InvalidArgumentException if an attaching attribute of this configuration
	 * is not valid for the given element.
	 */
	protected final void setAttachingAttributesTo(IConfigurableElement<?> element) {
		for (final var aa : attachingAttributes) {
			try {
				element.addOrChangeAttribute(aa);
			}
			catch (final Exception exception) {
				throw
				new InvalidArgumentException(
					"attaching attribute",
					aa,
					"could not be added to the given " + element.getType() + " '" + element.getSpecification() + "'"
				);
			}
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if this configuration is frozen
	 */
	protected final void supposeNotFrozen() {
		
		//Asserts that this configuration is not frozen.
		if (isFrozen()) {
			throw new InvalidArgumentException(this, "is frozen");
		}
	}
	
	//method
	/**
	 * Adds the given selector role to this configuration.
	 * 
	 * @param selectorRole
	 * @throws ArgumentIsNullException if the given selector role is null.
	 * @throws EmptyArgumentException if the given selector role is empty.
	 * @throws InvalidArgumentException if this configuration contains already the given selector role.
	 * @throws InvalidArgumentException if this configuration is frozen.
	 */
	private void addSelectorRole(final String selectorRole) {
		
		//Asserts that this configuration contains the given selector role.
		if (containsSelectorRole(selectorRole)) {
			throw
			new InvalidArgumentException(
				this,
				"contains the given selector role '" + selectorRole + "'"
			);
		}
		
		//Asserts that this configuration is not frozen.
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
	private void addSelectorRoles2(final Iterable<String> selectorRoles) {
		selectorRoles.forEach(sr -> addSelectorRole(sr));
	}
}

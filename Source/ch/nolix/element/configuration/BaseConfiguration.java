//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.elementapi.IConfigurableElement;
import ch.nolix.element.elementapi.IMutableElement;
import ch.nolix.element.gui.Widget;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 620
 * @param <C> The type of a {@link BaseConfiguration}.
 */
public abstract class BaseConfiguration<C extends BaseConfiguration<C>> extends Element<C>
implements IMutableElement<C> {
	
	//attribute headers
	private static final String SELECTOR_TYPE_HEADER = "SelectorType";
	private static final String SELECTOR_ROLE_HEADER = "SelectorRole";
	private static final String SELECTOR_TOKEN_HEADER = "SelectorToken";
	private static final String SELECTOR_ID_HEADER = "SelectorId";
		
	//optional attributes
	private String selectorType;
	private String selectorToken;
	private String selectorId;
	
	//multi-attributes
	private final LinkedList<String> selectorRoles = new LinkedList<>();
	private final LinkedList<Node> attachingAttributes = new LinkedList<>();
	protected final LinkedList<BaseConfiguration<?>> configurations = new LinkedList<>();
		
	//method
	/**
	 * Adds the given attachingAttribute to the current {@link BaseConfiguration}.
	 * 
	 * @param attachingAttribute
	 * @return the current {@link BaseConfiguration}.
	 * @throws ArgumentIsNullException if the given attachingAttribute is null.
	 */
	public final C addAttachingAttribute(final BaseNode attachingAttribute) {
		
		attachingAttributes.addAtEnd(
			Node.withHeaderAndAttributes(attachingAttribute.getHeader(), attachingAttribute.getRefAttributes())
		);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Adds the given attachingAttributee to the current {@link BaseConfiguration}.
	 * 
	 * @param attachingAttribute
	 * @return the current {@link BaseConfiguration}
	 * @throws InvalidArgumentException if the given attachingAttribute does not represent a {@link Node}.
	 */
	public final C addAttachingAttribute(final String attachingAttribute) {
		return addAttachingAttribute(Node.fromString(attachingAttribute));
	}
	
	//method
	/**
	 * Adds the given attachingAttributes to the current {@link BaseConfiguration}.
	 * 
	 * @param attachingAttributes
	 * @return the current {@link BaseConfiguration}.
	 * @throws InvalidArgumentException if one of the given attaching attributes does not represent a {@link Node}.
	 */
	public final C addAttachingAttribute(final String... attachingAttributes) {
		
		//Iterates the given attachingAttributes.
		for (final String aa : attachingAttributes) {
			addAttachingAttribute(aa);
		}
		
		return asConcrete();
	}
	
	//method
	/**
	 * Adds the given configuration to the current {@link BaseConfiguration}.
	 * 
	 * @param configuration
	 * @return the current {@link BaseConfiguration}.
	 * @throws ArgumentIsNullException if the given configuration is null.
	 */
	public final C addConfiguration(final BaseConfiguration<?> configuration) {
		
		configurations.addAtEnd(configuration);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Adds the given configurations to the current {@link BaseConfiguration}.
	 * 
	 * @param configurations
	 * @return the current {@link BaseConfiguration}.
	 * @throws ArgumentIsNullException if one of the given configurations is null.
	 */
	public final C addConfiguration(final BaseConfiguration<?>...configurations) {
		
		this.configurations.addAtEnd(configurations);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link BaseConfiguration}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case SELECTOR_TYPE_HEADER:
				setSelectorType(attribute.getOneAttributeHeader());
				break;
			case SELECTOR_ROLE_HEADER:
				addSelectorRolesFromStrings(attribute.getAttributesAsStrings());
				break;
			case SELECTOR_TOKEN_HEADER:
				setSelectorToken(attribute.getOneAttributeHeader());
				break;
			case SELECTOR_ID_HEADER:
				setSelectorId(attribute.getOneAttributeHeader());
				break;
			case Configuration.TYPE_NAME:
				addConfiguration(Configuration.fromSpecification(attribute));
				break;
			case DeepConfiguration.TYPE_NAME:
				//TODO: Create DeepConfiguration.fromSpecification static method.
				final var deepConfiguration = new DeepConfiguration();
				deepConfiguration.resetFrom(attribute);
				addConfiguration(deepConfiguration);
				break;
			default:
				addAttachingAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Adds the given selector role to the current {@link BaseConfiguration}.
	 * 
	 * @param selectorRole
	 * @return the current {@link BaseConfiguration}.
	 * @throws InvalidArgumentException if the current {@link BaseConfiguration} contains already the given selector role.
	 */
	public final C addSelectorRole(final Enum<?> selectorRole) {
		
		addSelectorRole(selectorRole.toString());
		
		return asConcrete();
	}
	
	//method
	/**
	 * Adds the given selector roles to the current {@link BaseConfiguration}.
	 * 
	 * @param selectorRoles
	 * @return the current {@link BaseConfiguration}.
	 * @throws InvalidArgumentException if the current {@link BaseConfiguration} contains already one of the given selector role.
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
	 * Adds the given selector roles to the current {@link BaseConfiguration}.
	 * 
	 * @param selectorRoles
	 * @return the current {@link BaseConfiguration}.
	 * @throws InvalidArgumentException if the current {@link BaseConfiguration} contains already one of the given selector role.
	 */
	public final C addSelectorRoles(final Iterable<Enum<?>> selectorRoles) {
		
		selectorRoles.forEach(this::addSelectorRole);
		
		return asConcrete();
	}
	
	//method
	/**
	 * @param selectorRole
	 * @return true if the current {@link BaseConfiguration} contains the given selector role.
	 */
	public final boolean containsSelectorRole(final String selectorRole) {
		return selectorRoles.containsEqualing(selectorRole);
	}
	
	//method
	/**
	 * @return true if the current {@link BaseConfiguration} contains selector roles.
	 */
	public final boolean containsSelectorRoles() {
		return selectorRoles.containsAny();
	}
	
	//method declaration
	/**
	 * Lets the current {@link BaseConfiguration} configure the given element.
	 * 
	 * @param element
	 */
	public abstract void configure(IConfigurableElement<?> element);
	
	//method
	/**
	 * @return the attributes of the current {@link BaseConfiguration}.
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		
		//Calls method of the base class.
		final LinkedList<Node> attributes = super.getAttributes();
				
		//Handles the case that the current BaseConfiguration has a selector type.
		if (hasSelectorType()) {
			attributes.addAtEnd(Node.withHeaderAndAttribute(SELECTOR_TYPE_HEADER, selectorType));
		}
		
		//Handles the case that the current BaseConfiguration contains selector roles.		
		if (containsSelectorRoles()) {
			
			final var specification = Node.fromString(SELECTOR_ROLE_HEADER);
			getSelectorRoles().forEach(specification::addAttribute);
			
			attributes.addAtEnd(specification);
		}
		
		//Handles the case that the current BaseConfiguration has a selector token.
		if (hasSelectorToken()) {
			attributes.addAtEnd(Node.withHeaderAndAttribute(SELECTOR_TOKEN_HEADER, selectorToken));
		}
		
		//Handles the case that the current BaseConfiguration has a selector id.
		if (hasSelectorId()) {
			attributes.addAtEnd(Node.withHeaderAndAttribute(SELECTOR_ID_HEADER, selectorId));
		}
		
		attributes.addAtEnd(attachingAttributes);
		attributes.addAtEnd(configurations, BaseConfiguration::getSpecification);
		
		return attributes;
	}
	
	//method
	/**
	 * @return the selector id of the current {@link BaseConfiguration}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseConfiguration} does not have a selector id.
	 */
	public final String getSelectorId() {
		
		//Asserts that the current BaseConfiguration has a selector id.
		if (!hasSelectorId()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "selector id");
		}
		
		return selectorId;
	}
	
	//method
	/**
	 * @return the selector roles of the current {@link BaseConfiguration}.
	 */
	public final IContainer<String> getSelectorRoles() {
		return selectorRoles;
	}
	
	//method
	/**
	 * @return the selector token of the current {@link BaseConfiguration}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseConfiguration} does not have a selector token.
	 */
	public final String getSelectorToken() {
		
		//Asserts that the current BaseConfiguration has a selector token.
		if (!hasSelectorToken()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "selector token");
		}
		
		return selectorToken;
	}
	
	//method
	/**
	 * @return true if the current {@link BaseConfiguration} has attaching attributes.
	 */
	public final boolean hasAttachingAttributes() {
		return attachingAttributes.containsAny();
	}
	
	//method
	/**
	 * @return the selector type of the current {@link BaseConfiguration}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseConfiguration} does not have a selector type.
	 */
	public final String getSelectorType() {
		
		//Asserts that the current BaseConfiguration has a selector type.
		if (!hasSelectorType()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "selector type");
		}
		
		return selectorType;
	}
	
	//method
	/**
	 * @return true if the current {@link BaseConfiguration} has a selector id.
	 */
	public final boolean hasSelectorId() {
		return (selectorId != null);
	}
	
	//method
	/**
	 * @param selectorId
	 * @return true if the current {@link BaseConfiguration} has the given selector id.
	 */
	public final boolean hasSelectorId(final String selectorId) {
		
		//Handles the case that the current BaseConfiguration does not have a selector id.
		if (!hasSelectorId()) {
			return false;
		}
		
		//Handles the case that the current BaseConfiguration has a selector id.
		return getSelectorId().equals(selectorId);
	}
	
	//method
	/**
	 * @return true if the current {@link BaseConfiguration} has a selector token.
	 */
	public final boolean hasSelectorToken() {
		return (selectorToken != null);
	}
	
	//method
	/**
	 * @param selectorToken
	 * @return true if the current {@link BaseConfiguration} has the given selector token.
	 */
	public final boolean hasSelectorToken(final String selectorToken) {
		
		//Handles the case that the current BaseConfiguration does not have a selector token.
		if (!hasSelectorToken()) {
			return false;
		}
		
		//Handles the case that the current BaseConfiguration as a selector token.
		return getSelectorToken().equals(selectorToken);
	}
	
	//method
	/**
	 * @return true if the current {@link BaseConfiguration} has a selector type.
	 */
	public final boolean hasSelectorType() {
		return (selectorType != null);
	}
	
	//method
	/**
	 * @param selectorType
	 * @return true if the current {@link BaseConfiguration} has the given selector type.
	 */
	public final boolean hasSelectorType(final String selectorType) {
		
		//Handles the case that the current BaseConfiguration} does not have a selector type.
		if (!hasSelectorType()) {
			return false;
		}
		
		//Handles the case that the current BaseConfiguration has a selector type.
		return getSelectorType().equals(selectorType);
	}
	
	//method
	/**
	 * Removes the selector id of the current {@link BaseConfiguration}.
	 */
	public final void removeSelectorName() {
		
		selectorId = null;
	}
	
	//method
	/**
	 * Removes the selector roles of the current {@link BaseConfiguration}.
	 */
	public final void removeSelectorRoles() {
		
		selectorRoles.clear();
	}
	
	//method
	/**
	 * Removes the selector token of the current {@link BaseConfiguration}.
	 */
	public final void removeSelectorToken() {
		
		selectorToken = null;
	}
	
	//method
	/**
	 * Removes the selector type of the current {@link BaseConfiguration}.
	 */
	public final void removeSelectorType() {
		
		selectorType = null;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void reset() {
		
		removeSelectorType();
		removeSelectorRoles();
		removeSelectorToken();
		removeSelectorName();
		
		attachingAttributes.clear();
		configurations.clear();
		
		resetStage2();
	}
	
	//method
	/**
	 * @param element
	 * @return true if the current {@link BaseConfiguration} selects the given element.
	 */
	public final boolean selects(IConfigurableElement<?> element) {
		
		//Handles the case that the current BaseConfiguration has a selector type.
		if (hasSelectorType() && !element.isOfType(getSelectorType())) {
			return false;
		}
		
		//Handles the case that the current BaseConfiguration contains selector roles.
		if (containsSelectorRoles() && getSelectorRoles().containsNone(element::hasRole)) {
			return false;
		}
		
		//Handles the case that the current BaseConfiguration has a selector token.
		if (hasSelectorToken() && !element.hasToken(getSelectorToken())) {
			return false;
		}
		
		//Handles the case that the current BaseConfiguration has a selector type.
		return !(hasSelectorId() && !element.hasId(getSelectorId()));
	}
	
	//method
	/**
	 * Sets the selector id of the current {@link BaseConfiguration}.
	 * 
	 * @param selectorId
	 * @return the current {@link BaseConfiguration}.
	 * @throws ArgumentIsNullException if the given selector id is null.
	 * @throws InvalidArgumentException if the given selector id is blank.
	 */
	public final C setSelectorId(final String selectorId) {
		
		//Asserts that the given selectorId is not null or blank.
		Validator.assertThat(selectorId).thatIsNamed("selectorId").isNotBlank();
		
		//Sets the selectorId of the current Configuration.
		this.selectorId = selectorId;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the selector token of the current {@link BaseConfiguration}.
	 * 
	 * @param selectorToken
	 * @return the current {@link BaseConfiguration}.
	 * @throws ArgumentIsNullException if the given selector token is null.
	 * @throws InvalidArgumentException if the given selector token is blank.
	 */
	public final C setSelectorToken(final String selectorToken) {
		
		//Asserts that the given selectorToken is not null or blank.
		Validator.assertThat(selectorToken).thatIsNamed("selectorToken").isNotBlank();
		
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
	 */
	public final C setSelectorType(final Class<?> selectorType) {
		
		//Asserts that the given selectorType is not null.
		Validator.assertThat(selectorType).thatIsNamed("selector type").isNotNull();
		
		return setSelectorType(selectorType.getSimpleName());
	}
	
	//method
	/**
	 * Sets the selector type of the current {@link BaseConfiguration}.
	 * 
	 * @param selectorType
	 * @return the current {@link BaseConfiguration}.
	 * @throws ArgumentIsNullException if the given type selector type is null.
	 * @throws InvalidArgumentException if the given selector type is blank.
	 */
	public final C setSelectorType(final String selectorType) {
		
		//Asserts that the given selectorType is not null or blank.
		Validator.assertThat(selectorType).thatIsNamed("selectorType").isNotBlank();
		
		//Sets the selectorType of the current Configuration.
		this.selectorType = selectorType;
		
		return asConcrete();
	}
	
	//method declaration
	/**
	 * Resets the current {@link Widget}.
	 */
	protected abstract void resetStage2();

	//method
	/**
	 * Sets the attaching attributes of the current {@link BaseConfiguration} to the given element.
	 * 
	 * @param element
	 * @throws InvalidArgumentException if an attaching attribute of the current {@link BaseConfiguration}
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
	 * Adds the given selector role to the current {@link BaseConfiguration}.
	 * 
	 * @param selectorRole
	 * @throws ArgumentIsNullException if the given selector role is null.
	 * @throws EmptyArgumentException if the given selector role is empty.
	 * @throws InvalidArgumentException if the current {@link BaseConfiguration} contains already the given selector role.
	 */
	private void addSelectorRole(final String selectorRole) {
		
		//Asserts that the current BaseConfiguration contains the given selector role.
		if (containsSelectorRole(selectorRole)) {
			throw
			new InvalidArgumentException(
				this,
				"contains the given selector role '" + selectorRole + "'"
			);
		}
		
		selectorRoles.addAtEnd(selectorRole);
	}
	
	//method
	/**
	 * Adds the given selectorRoles to the current {@link BaseConfiguration}.
	 * 
	 * @param selectorRoles
	 * @throws EmptyArgumentException if one of the given selector roles is empty.
	 * @throws InvalidArgumentException if the current {@link BaseConfiguration} contains already one of the given selector roles.
	 */
	private void addSelectorRolesFromStrings(final Iterable<String> selectorRoles) {
		selectorRoles.forEach(this::addSelectorRole);
	}
}

//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.MultiValue;
import ch.nolix.element.base.MutableOptionalValue;
import ch.nolix.element.elementapi.IConfigurableElement;
import ch.nolix.element.elementapi.IMutableElement;
import ch.nolix.element.gui.base.Widget;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 560
 * @param <C> is the type of a {@link BaseConfiguration}.
 */
public abstract class BaseConfiguration<C extends BaseConfiguration<C>> extends Element<C>
implements IMutableElement<C> {
	
	//constants
	private static final String SELECTOR_TYPE_HEADER = "SelectorType";
	private static final String SELECTOR_ID_HEADER = "SelectorId";
	private static final String SELECTOR_ROLE_HEADER = "SelectorRole";
	private static final String SELECTOR_TOKEN_HEADER = "SelectorToken";
	private static final String ATTACHING_ATTRIBUTES_HEADER = "Attach";
	private static final String CONFIGURATIONS_HEADER = "Configurations";
	
	//attribute
	private final MutableOptionalValue<String> selectorType =
	MutableOptionalValue.forString(SELECTOR_TYPE_HEADER, this::setSelectorType);
	
	//attribute
	private final MutableOptionalValue<String> selectorId =
	MutableOptionalValue.forString(SELECTOR_ID_HEADER, this::setSelectorId);
	
	//attribute
	private final MultiValue<String> selectorRoles =
	MultiValue.forStrings(SELECTOR_ROLE_HEADER, this::addSelectorRole);
	
	//attribute
	private final MultiValue<String> selectorTokens =
	MultiValue.forStrings(SELECTOR_TOKEN_HEADER, this::addSelectorToken);
	
	//attribute
	private final MultiValue<Node> attachingAttributes =
	new MultiValue<>(
		ATTACHING_ATTRIBUTES_HEADER,
		this::addAttachingAttribute,
		BaseNode::getCopy,
		BaseNode::getCopy
	);
	
	//attribute
	private final MultiValue<BaseConfiguration<?>> configurations =
	new MultiValue<>(
		CONFIGURATIONS_HEADER,
		this::addConfiguration,
		this::createConfigurationFromSpecification,
		BaseConfiguration::getSpecification
	);
	
	//method
	/**
	 * Adds the given attachingAttribute to the current {@link BaseConfiguration}.
	 * 
	 * @param attachingAttribute
	 * @return the current {@link BaseConfiguration}.
	 * @throws ArgumentIsNullException if the given attachingAttribute is null.
	 */
	public final C addAttachingAttribute(final BaseNode attachingAttribute) {
		
		attachingAttributes.add(attachingAttribute.getCopy());
		
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
		
		configurations.add(configuration);
		
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
		
		for (final var c : configurations) {
			addConfiguration(c);
		}
		
		return asConcrete();
	}
	
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
	 * Adds the given selectorToken to the current {@link BaseConfiguration}.
	 * 
	 * @param selectorToken
	 * @return the current {@link BaseConfiguration}.
	 * @throws ArgumentIsNullException if the given selectorToken is null.
	 * @throws InvalidArgumentException if the given selectorToken is blank.
	 */
	public final C addSelectorToken(final String selectorToken) {
		
		//Asserts that the given selectorToken is not null or blank.
		Validator.assertThat(selectorToken).thatIsNamed("selectorToken").isNotBlank();
				
		selectorTokens.add(selectorToken);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the selector roles from the current {@link BaseConfiguration}.
	 */
	public final void clearSelectorRoles() {
		selectorRoles.clear();
	}

	//method
	/**
	 * Removes the selector tokens from the current {@link BaseConfiguration}.
	 */
	public final void clearSelectorTokens() {
		selectorTokens.clear();
	}

	//method
	/**
	 * @param selectorRole
	 * @return true if the current {@link BaseConfiguration} contains the given selectorRole.
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
	
	//method
	/**
	 * @param selectorToken
	 * @return true if the current {@link BaseConfiguration} contains the given selectorToken.
	 */
	public final boolean containsSelectorToken(final String selectorToken) {
		return selectorTokens.containsEqualing(selectorToken);
	}
	
	//method
	/**
	 * @return true if the current {@link BaseConfiguration} contains selector tokens.
	 */
	public final boolean containsSelectorTokens() {
		return selectorTokens.containsAny();
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
	 * @return the selector id of the current {@link BaseConfiguration}.
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link BaseConfiguration} does not have a selector id.
	 */
	public final String getSelectorId() {
		return selectorId.getValue();
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
	 * @return the selector tokens of the current {@link BaseConfiguration}.
	 */
	public final IContainer<String> getSelectorTokens() {
		return selectorTokens;
	}
	
	//method
	/**
	 * @return the selector type of the current {@link BaseConfiguration}.
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link BaseConfiguration} does not have a selector type.
	 */
	public final String getSelectorType() {
		return selectorType.getValue();
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
	 * @return true if the current {@link BaseConfiguration} has a selector id.
	 */
	public final boolean hasSelectorId() {
		return selectorId.hasValue();
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
	 * @return true if the current {@link BaseConfiguration} has a selector type.
	 */
	public final boolean hasSelectorType() {
		return selectorType.hasValue();
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
	 * Removes the selector id from the current {@link BaseConfiguration}.
	 */
	public final void removeSelectorId() {
		selectorId.clear();
	}
	
	//method
	/**
	 * Removes the selector type from the current {@link BaseConfiguration}.
	 */
	public final void removeSelectorType() {
		selectorType.clear();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void reset() {
		
		removeSelectorType();
		removeSelectorId();
		clearSelectorRoles();
		clearSelectorTokens();
		
		attachingAttributes.clear();
		configurations.clear();
		
		resetBaseConfiguration();
	}
	
	//method
	/**
	 * @param element
	 * @return true if the current {@link BaseConfiguration} selects the given element.
	 */
	public final boolean selects(IConfigurableElement<?> element) {
		
		//Handles the case that the current BaseConfiguration has a selector id.
		if (hasSelectorId() && !element.hasId(getSelectorId())) {
			return false;
		}
		
		//Handles the case that the current BaseConfiguration has a selector type.
		if (hasSelectorType() && !element.isOfType(getSelectorType())) {
			return false;
		}
		
		//Handles the case that the current BaseConfiguration contains selector roles.
		if (containsSelectorRoles() && getSelectorRoles().containsNone(element::hasRole)) {
			return false;
		}
		
		//Handles the case that the current BaseConfiguration contains selector tokens.
		return !(containsSelectorTokens() && !getSelectorTokens().containsNone(element::hasToken));
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
		
		//Sets the selectorId of the current Configuration.
		this.selectorId.setValue(selectorId);
		
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
	 * Sets the selectorType of the current {@link BaseConfiguration}.
	 * 
	 * @param selectorType
	 * @return the current {@link BaseConfiguration}.
	 * @throws ArgumentIsNullException if the given type selectorType is null.
	 * @throws InvalidArgumentException if the given selectorType is blank.
	 */
	public final C setSelectorType(final String selectorType) {
		
		//Asserts that the given selectorType is not null or blank.
		Validator.assertThat(selectorType).thatIsNamed("selectorType").isNotBlank();
		
		//Sets the selectorType of the current BaseConfiguration.
		this.selectorType.setValue(selectorType);
		
		return asConcrete();
	}
	
	protected final IContainer<BaseConfiguration<?>> getRefConfigurations() {
		return configurations;
	}
	
	//method declaration
	/**
	 * Resets the current {@link Widget}.
	 */
	protected abstract void resetBaseConfiguration();

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
			} catch (final Exception exception) {
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
	 * @throws EmptyArgumentException if the given selectorRole is blank.
	 * @throws InvalidArgumentException if
	 * the current {@link BaseConfiguration} contains already the given selectorRole.
	 */
	private void addSelectorRole(final String selectorRole) {
		
		//Asserts that the current BaseConfiguration does not contain already the given selectorRole.
		assertDoesNotContainerSelectorRole(selectorRole);
		
		selectorRoles.add(selectorRole);
	}
	
	//method
	/**
	 * @param selectorRole
	 * @throws InvalidArgumentException if
	 * the current {@link BaseConfiguration} contains already the given selectorRole.
	 */
	private void assertDoesNotContainerSelectorRole(String selectorRole) {
		if (containsSelectorRole(selectorRole)) {
			throw
			new InvalidArgumentException(this, "contains already the given selector role '" + selectorRole + "'");
		}
	}
	
	//method
	/**
	 * @param specification
	 * @return a new {@link BaseConfiguration} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	private BaseConfiguration<?> createConfigurationFromSpecification(final BaseNode specification) {
		switch (specification.getHeader()) {
			case Configuration.TYPE_NAME:
				return Configuration.fromSpecification(specification);
			case DeepConfiguration.TYPE_NAME:
				return DeepConfiguration.fromSpecification(specification);
			default:
				throw new InvalidArgumentException(LowerCaseCatalogue.SPECIFICATION, specification, "is not valid");
		}
	}
}

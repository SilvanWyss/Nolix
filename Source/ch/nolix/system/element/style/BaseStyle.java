//package declaration
package ch.nolix.system.element.style;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.mutableelement.MultiValue;
import ch.nolix.system.element.mutableelement.MutableElement;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;
import ch.nolix.systemapi.elementapi.styleapi.IStyle;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <C> is the type of a {@link BaseStyle}.
 */
public abstract class BaseStyle<C extends BaseStyle<C>> extends MutableElement implements IStyle {
	
	//constant
	private static final String SELECTOR_TYPE_HEADER = "SelectorType";
	
	//constant
	private static final String SELECTOR_ID_HEADER = "SelectorId";
	
	//constant
	private static final String SELECTOR_ROLE_HEADER = "SelectorRole";
	
	//constant
	private static final String SELECTOR_TOKEN_HEADER = "SelectorToken";
	
	//constant
	private static final String ATTACHING_ATTRIBUTES_HEADER = "Attach";
	
	//constant
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
		Node::fromNode,
		Node::fromNode
	);
	
	//attribute
	private final MultiValue<BaseStyle<?>> configurations =
	new MultiValue<>(
		CONFIGURATIONS_HEADER,
		this::addConfiguration,
		this::createConfigurationFromSpecification,
		BaseStyle::getSpecification
	);
	
	//method
	/**
	 * Adds the given attachingAttribute to the current {@link BaseStyle}.
	 * 
	 * @param attachingAttribute
	 * @return the current {@link BaseStyle}.
	 * @throws ArgumentIsNullException if the given attachingAttribute is null.
	 */
	public final C addAttachingAttribute(final BaseNode<?> attachingAttribute) {
		
		attachingAttributes.add(Node.fromNode(attachingAttribute));
		
		return asConcrete();
	}
	
	//method
	/**
	 * Adds the given attachingAttributee to the current {@link BaseStyle}.
	 * 
	 * @param attachingAttribute
	 * @return the current {@link BaseStyle}
	 * @throws InvalidArgumentException if the given attachingAttribute does not represent a {@link Node}.
	 */
	public final C addAttachingAttribute(final String attachingAttribute) {
		return addAttachingAttribute(Node.fromString(attachingAttribute));
	}
	
	//method
	/**
	 * Adds the given attachingAttributes to the current {@link BaseStyle}.
	 * 
	 * @param attachingAttributes
	 * @return the current {@link BaseStyle}.
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
	 * Adds the given configuration to the current {@link BaseStyle}.
	 * 
	 * @param configuration
	 * @return the current {@link BaseStyle}.
	 * @throws ArgumentIsNullException if the given configuration is null.
	 */
	public final C addConfiguration(final BaseStyle<?> configuration) {
		
		configurations.add(configuration);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Adds the given configurations to the current {@link BaseStyle}.
	 * 
	 * @param configurations
	 * @return the current {@link BaseStyle}.
	 * @throws ArgumentIsNullException if one of the given configurations is null.
	 */
	public final C addConfiguration(final BaseStyle<?>...configurations) {
		
		for (final var c : configurations) {
			addConfiguration(c);
		}
		
		return asConcrete();
	}
	
	//method
	/**
	 * Adds the given selector role to the current {@link BaseStyle}.
	 * 
	 * @param selectorRole
	 * @return the current {@link BaseStyle}.
	 * @throws InvalidArgumentException if the current {@link BaseStyle} contains already the given selector role.
	 */
	public final C addSelectorRole(final Enum<?> selectorRole) {
		
		addSelectorRole(selectorRole.toString());
		
		return asConcrete();
	}
	
	//method
	/**
	 * Adds the given selector roles to the current {@link BaseStyle}.
	 * 
	 * @param selectorRoles
	 * @return the current {@link BaseStyle}.
	 * @throws InvalidArgumentException if the current {@link BaseStyle} contains already one of the given selector role.
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
	 * Adds the given selector roles to the current {@link BaseStyle}.
	 * 
	 * @param selectorRoles
	 * @return the current {@link BaseStyle}.
	 * @throws InvalidArgumentException if the current {@link BaseStyle} contains already one of the given selector role.
	 */
	public final C addSelectorRoles(final Iterable<Enum<?>> selectorRoles) {
		
		selectorRoles.forEach(this::addSelectorRole);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Adds the given selectorToken to the current {@link BaseStyle}.
	 * 
	 * @param selectorToken
	 * @return the current {@link BaseStyle}.
	 * @throws ArgumentIsNullException if the given selectorToken is null.
	 * @throws InvalidArgumentException if the given selectorToken is blank.
	 */
	public final C addSelectorToken(final String selectorToken) {
		
		//Asserts that the given selectorToken is not null or blank.
		GlobalValidator.assertThat(selectorToken).thatIsNamed("selectorToken").isNotBlank();
				
		selectorTokens.add(selectorToken);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the selector roles from the current {@link BaseStyle}.
	 */
	public final void clearSelectorRoles() {
		selectorRoles.clear();
	}

	//method
	/**
	 * Removes the selector tokens from the current {@link BaseStyle}.
	 */
	public final void clearSelectorTokens() {
		selectorTokens.clear();
	}

	//method
	/**
	 * @param selectorRole
	 * @return true if the current {@link BaseStyle} contains the given selectorRole.
	 */
	public final boolean containsSelectorRole(final String selectorRole) {
		return selectorRoles.getOriValues().containsEqualing(selectorRole);
	}
	
	//method
	/**
	 * @return true if the current {@link BaseStyle} contains selector roles.
	 */
	public final boolean containsSelectorRoles() {
		return selectorRoles.containsAny();
	}
	
	//method
	/**
	 * @param selectorToken
	 * @return true if the current {@link BaseStyle} contains the given selectorToken.
	 */
	public final boolean containsSelectorToken(final String selectorToken) {
		return selectorTokens.getOriValues().containsEqualing(selectorToken);
	}
	
	//method
	/**
	 * @return true if the current {@link BaseStyle} contains selector tokens.
	 */
	public final boolean containsSelectorTokens() {
		return selectorTokens.containsAny();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<? extends INode<?>> getAttachingAttributes() {
		return attachingAttributes.getOriValues();
	}
	
	//method
	/**
	 * @return the selector id of the current {@link BaseStyle}.
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link BaseStyle} does not have a selector id.
	 */
	public final String getSelectorId() {
		return selectorId.getValue();
	}
	
	//method
	/**
	 * @return the selector roles of the current {@link BaseStyle}.
	 */
	public final IContainer<String> getSelectorRoles() {
		return selectorRoles.getOriValues();
	}
	
	//method
	/**
	 * @return the selector tokens of the current {@link BaseStyle}.
	 */
	public final IContainer<String> getSelectorTokens() {
		return selectorTokens.getOriValues();
	}
	
	//method
	/**
	 * @return the selector type of the current {@link BaseStyle}.
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link BaseStyle} does not have a selector type.
	 */
	public final String getSelectorType() {
		return selectorType.getValue();
	}
	
	//method
	/**
	 * @return true if the current {@link BaseStyle} has attaching attributes.
	 */
	public final boolean hasAttachingAttributes() {
		return attachingAttributes.containsAny();
	}
	
	//method
	/**
	 * @return true if the current {@link BaseStyle} has a selector id.
	 */
	public final boolean hasSelectorId() {
		return selectorId.hasValue();
	}
	
	//method
	/**
	 * @param selectorId
	 * @return true if the current {@link BaseStyle} has the given selector id.
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
	 * @return true if the current {@link BaseStyle} has a selector type.
	 */
	public final boolean hasSelectorType() {
		return selectorType.hasValue();
	}
	
	//method
	/**
	 * @param selectorType
	 * @return true if the current {@link BaseStyle} has the given selector type.
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
	 * Removes the selector id from the current {@link BaseStyle}.
	 */
	public final void removeSelectorId() {
		selectorId.clear();
	}
	
	//method
	/**
	 * Removes the selector type from the current {@link BaseStyle}.
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
	}
	
	//method
	/**
	 * @param element
	 * @return true if the current {@link BaseStyle} selects the given element.
	 */
	public final boolean selectsElement(IStylableElement<?> element) {
		return
		selectorIdAllowsToSelectElement(element)
		&& selectorTypeAllowsToSelectElement(element)
		&& selectorRolesAllowToSelectElement(element)
		&& selectorTokensAllowToSelectElement(element);
	}
	
	//method
	/**
	 * Sets the selector id of the current {@link BaseStyle}.
	 * 
	 * @param selectorId
	 * @return the current {@link BaseStyle}.
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
	 * Sets the selector type of the current {@link BaseStyle}.
	 * 
	 * @param selectorType
	 * @return the current {@link BaseStyle}.
	 * @throws ArgumentIsNullException if the given selectorType is null.
	 */
	public final C setSelectorType(final Class<?> selectorType) {
		
		//Asserts that the given selectorType is not null.
		GlobalValidator.assertThat(selectorType).thatIsNamed("selector type").isNotNull();
		
		return setSelectorType(selectorType.getSimpleName());
	}
	
	//method
	/**
	 * Sets the selectorType of the current {@link BaseStyle}.
	 * 
	 * @param selectorType
	 * @return the current {@link BaseStyle}.
	 * @throws ArgumentIsNullException if the given type selectorType is null.
	 * @throws InvalidArgumentException if the given selectorType is blank.
	 */
	public final C setSelectorType(final String selectorType) {
		
		//Asserts that the given selectorType is not null or blank.
		GlobalValidator.assertThat(selectorType).thatIsNamed("selectorType").isNotBlank();
		
		//Sets the selectorType of the current BaseConfiguration.
		this.selectorType.setValue(selectorType);
		
		return asConcrete();
	}
	
	protected final IContainer<BaseStyle<?>> getOriConfigurations() {
		return configurations.getOriValues();
	}
	
	//method
	/**
	 * Sets the attaching attributes of the current {@link BaseStyle} to the given element.
	 * 
	 * @param element
	 * @throws InvalidArgumentException if an attaching attribute of the current {@link BaseStyle}
	 * is not valid for the given element.
	 */
	protected final void setAttachingAttributesTo(IStylableElement<?> element) {
		for (final var aa : attachingAttributes.getOriValues()) {
			try {
				element.addOrChangeAttribute(aa);
			} catch (final Throwable error) {
				
				final var invalidArgumentException =
				InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
					"attaching attribute",
					aa,
					"could not be added to the given " + element.getType() + " '" + element.getSpecification() + "'"
				);
				
				invalidArgumentException.initCause(error);
				
				throw invalidArgumentException;
			}
		}
	}
	
	//method
	/**
	 * Adds the given selector role to the current {@link BaseStyle}.
	 * 
	 * @param selectorRole
	 * @throws EmptyArgumentException if the given selectorRole is blank.
	 * @throws InvalidArgumentException if
	 * the current {@link BaseStyle} contains already the given selectorRole.
	 */
	private void addSelectorRole(final String selectorRole) {
		
		//Asserts that the current BaseConfiguration does not contain already the given selectorRole.
		assertDoesNotContainerSelectorRole(selectorRole);
		
		selectorRoles.add(selectorRole);
	}
	
	//method
	@SuppressWarnings("unchecked")
	private C asConcrete() {
		return (C)this;
	}
	
	//method
	/**
	 * @param selectorRole
	 * @throws InvalidArgumentException if
	 * the current {@link BaseStyle} contains already the given selectorRole.
	 */
	private void assertDoesNotContainerSelectorRole(String selectorRole) {
		if (containsSelectorRole(selectorRole)) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				this,
				"contains already the given selector role '" + selectorRole + "'"
			);
		}
	}
	
	//method
	/**
	 * @param specification
	 * @return a new {@link BaseStyle} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	private BaseStyle<?> createConfigurationFromSpecification(final INode<?> specification) {
		return
		switch (specification.getHeader()) {
			case Style.TYPE_NAME ->
				Style.fromSpecification(specification);
			case DeepSelectingStyle.TYPE_NAME ->
				DeepSelectingStyle.fromSpecification(specification);
			default ->
				throw
				InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.SPECIFICATION, specification);
		};
	}
	
	//method
	private boolean selectorIdAllowsToSelectElement(final IStylableElement<?> element) {
		return !hasSelectorId() || element.hasId(getSelectorId());
	}
	
	//method
	private boolean selectorRolesAllowToSelectElement(IStylableElement<?> element) {
		return !containsSelectorRoles() || getSelectorRoles().containsAny(element::hasRole);
	}
	
	//method
	private boolean selectorTokensAllowToSelectElement(final IStylableElement<?> element) {
		return !containsSelectorTokens() || getSelectorTokens().containsAnyOf(element.getTokens());
	}
	
	//method
	private boolean selectorTypeAllowsToSelectElement(final IStylableElement<?> element) {
		return !hasSelectorType() || element.isOfType(getSelectorType());
	}
}

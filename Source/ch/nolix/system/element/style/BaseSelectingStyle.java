//package declaration
package ch.nolix.system.element.style;

//Java imports
import java.util.Optional;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.styleapi.ISelectingStyle;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;

//class
public abstract class BaseSelectingStyle extends BaseStyle implements ISelectingStyle {

  //constant
  protected static final String SELECTOR_ID_HEADER = "SelectorId";

  //constant
  protected static final String SELECTOR_TYPE_HEADER = "SelectorType";

  //constant
  protected static final String SELECTOR_ROLE_HEADER = "SelectorRole";

  //constant
  protected static final String SELECTOR_TOKEN_HEADER = "SelectorToken";

  //optional attribute
  private final String selectorId;

  //optional attribute
  private final String selectorType;

  //multi-attribute
  private final ImmutableList<String> selectorRoles;

  //multi-attribute
  private final ImmutableList<String> selectorTokens;

  //constructor
  protected BaseSelectingStyle(
    final Optional<String> selectorIdContainer,
    final Optional<String> selectorTypeContainer,
    IContainer<String> selectorRoles,
    IContainer<String> selectorTokens,
    final IContainer<? extends INode<?>> attachingAttributes,
    final IContainer<BaseSelectingStyle> subStyles) {

    super(attachingAttributes, subStyles);

    GlobalValidator.assertThatTheStrings(selectorRoles).areNotBlank();
    GlobalValidator.assertThatTheStrings(selectorTokens).areNotBlank();

    if (selectorIdContainer.isEmpty()) {
      selectorId = null;
    } else {

      selectorId = selectorIdContainer.get();

      GlobalValidator.assertThat(selectorId).thatIsNamed("selector id").isNotBlank();
    }

    if (selectorTypeContainer.isEmpty()) {
      selectorType = null;
    } else {

      selectorType = selectorTypeContainer.get();

      GlobalValidator.assertThat(selectorType).thatIsNamed("selector type").isNotBlank();
    }

    this.selectorRoles = ImmutableList.forIterable(selectorRoles);
    this.selectorTokens = ImmutableList.forIterable(selectorTokens);
  }

  //method
  public final boolean containsSelectorRole(final String selectorRole) {
    return selectorRoles.containsEqualing(selectorRole);
  }

  //method
  public final boolean containsSelectorRoles() {
    return selectorRoles.containsAny();
  }

  //method
  public final boolean containsSelectorToken(final String selectorToken) {
    return selectorTokens.containsEqualing(selectorToken);
  }

  //method
  public final boolean containsSelectorTokens() {
    return selectorTokens.containsAny();
  }

  //method
  @Override
  public final IContainer<INode<?>> getAttributes() {

    final var attributes = new LinkedList<INode<?>>();

    if (hasSelectorId()) {
      attributes.addAtEnd(Node.withHeaderAndChildNode(SELECTOR_ID_HEADER, getSelectorId()));
    }

    if (hasSelectorType()) {
      attributes.addAtEnd(Node.withHeaderAndChildNode(SELECTOR_TYPE_HEADER, getSelectorType()));
    }

    for (final var aa : getAttachingAttributes()) {
      attributes.addAtEnd(Node.withHeaderAndChildNode(ATTACHING_ATTRIBUTE_HEADER, aa));
    }

    for (final var ss : getSubStyles()) {
      attributes.addAtEnd(ss.getSpecification());
    }

    return attributes;
  }

  //method
  public final String getSelectorId() {

    assertHasSelectorId();

    return selectorId;
  }

  //method
  public IContainer<String> getSelectorRoles() {
    return selectorRoles;
  }

  //method
  public IContainer<String> getSelectorTokens() {
    return selectorTokens;
  }

  //method
  public final String getSelectorType() {

    assertHasSelectorType();

    return selectorType;
  }

  //method
  public final boolean hasSelectorId() {
    return (selectorId != null);
  }

  //method
  public final boolean hasSelectorId(final String selectorId) {

    if (!hasSelectorId()) {
      return false;
    }

    return getSelectorId().equals(selectorId);
  }

  //method
  public final boolean hasSelectorType() {
    return (selectorType != null);
  }

  //method
  public final boolean hasSelectorType(final String selectorType) {

    if (!hasSelectorType()) {
      return false;
    }

    return getSelectorType().equals(selectorType);
  }

  //method
  @Override
  public final boolean selectsElement(IStylableElement<?> element) {
    return selectorIdAllowsToSelectElement(element)
    && selectorTypeAllowsToSelectElement(element)
    && selectorRolesAllowToSelectElement(element)
    && selectorTokensAllowToSelectElement(element);
  }

  //method
  private void assertHasSelectorId() {
    if (!hasSelectorId()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "selector id");
    }
  }

  //method
  private void assertHasSelectorType() {
    if (!hasSelectorType()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "selector type");
    }
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

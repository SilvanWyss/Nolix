//package declaration
package ch.nolix.system.element.stylebuilder;

//Java imports
import java.util.Optional;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//class
abstract class BaseSelectingStyleBuilder<SSB extends BaseSelectingStyleBuilder<SSB>> extends BaseStyleBuilder<SSB> {

  //optional attribute
  private String selectorId;

  //optional attribute
  private String selectorType;

  //multi-attribute
  private final LinkedList<String> selectorRoles = new LinkedList<>();

  //multi-attribute
  private final LinkedList<String> selectorTokens = new LinkedList<>();

  //method
  public final SSB addSelectorRole(final Enum<?> selectorRole, final Enum<?>... selectorRoles) {

    final var allSelectorRoles = ReadContainer.forElement(selectorRole, selectorRoles);

    return addSelectorRoles(allSelectorRoles);
  }

  //method
  public final SSB addSelectorRoles(final IContainer<? extends Enum<?>> selectorRoles) {

    final var selectorRolesAsStrings = selectorRoles.toString();

    this.selectorRoles.addAtEnd(selectorRolesAsStrings);

    return asConcrete();
  }

  //method
  public final SSB addSelectorToken(final String selectorToken, final String... selectorTokens) {

    final var allSelectorTokensAsNodes = ReadContainer.forElement(selectorToken, selectorTokens);

    return addSelectorTokens(allSelectorTokensAsNodes);
  }

  //method
  public final SSB addSelectorTokens(final IContainer<String> selectorTokens) {

    this.selectorTokens.addAtEnd(selectorTokens);

    return asConcrete();
  }

  //method
  public SSB setSelectorId(final String selectorId) {

    this.selectorId = selectorId;

    return asConcrete();
  }

  //method
  public SSB setSelectorType(final Class<?> selectorType) {

    this.selectorType = selectorType.getSimpleName();

    return asConcrete();
  }

  //method
  protected final Optional<String> getOptionalSelectorId() {
    return Optional.ofNullable(selectorId);
  }

  //method
  protected final Optional<String> getOptionalSelectorType() {
    return Optional.ofNullable(selectorType);
  }

  //method
  protected final IContainer<String> getSelectorRoles() {
    return selectorRoles;
  }

  //method
  protected final IContainer<String> getSelectorTokens() {
    return selectorTokens;
  }
}

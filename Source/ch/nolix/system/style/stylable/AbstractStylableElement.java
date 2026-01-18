/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.style.stylable;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.system.element.base.StructureSpecificationCreator;
import ch.nolix.system.element.mutableelement.AbstractMutableElement;
import ch.nolix.system.element.property.MultiValue;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.systemapi.element.base.IStructureElement;
import ch.nolix.systemapi.style.stylable.IStylableElement;

public abstract class AbstractStylableElement<E extends IStylableElement<E>>
extends AbstractMutableElement
implements IStylableElement<E> {
  private static final String ID_HEADER = PascalCaseVariableCatalog.ID;

  private static final String TOKEN_HEADER = PascalCaseVariableCatalog.TOKEN;

  private static final StructureSpecificationCreator STRUCTURE_SPECIFICATION_CREATOR = //
  new StructureSpecificationCreator();

  private final MutableOptionalValue<String> id = MutableOptionalValue.forString(ID_HEADER, this::setId);

  private final MultiValue<String> tokens = MultiValue.forStrings(TOKEN_HEADER, this::addToken);

  @Override
  public final E addToken(final String token) {
    Validator.assertThat(token).thatIsNamed(LowerCaseVariableCatalog.TOKEN).isNotBlank();

    tokens.add(token);

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<? extends IStructureElement> getChildStructureElements() {
    return getStoredChildStylableElements();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getId() {
    return id.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<String> getTokens() {
    return tokens.getStoredValues();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final INode<?> getStructureSpecification() {
    return STRUCTURE_SPECIFICATION_CREATOR.getStructureSpecificationOfElement(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasId() {
    return id.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeId() {
    id.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeToken(final String token) {
    tokens.remove(token);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeTokens() {
    tokens.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void reset() {
    removeId();
    removeTokens();

    resetStyleRecursively();

    resetStylableElement();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void resetStyleRecursively() {
    resetStyle();

    getStoredChildStylableElements().forEach(IStylableElement::resetStyleRecursively);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final E setId(final String id) {
    Validator.assertThat(id).thatIsNamed(LowerCaseVariableCatalog.ID).isNotBlank();

    this.id.setValue(id);

    return asConcrete();
  }

  @SuppressWarnings("unchecked")
  protected final E asConcrete() {
    return (E) this;
  }

  protected abstract void resetStylableElement();

  protected abstract void resetStyle();
}

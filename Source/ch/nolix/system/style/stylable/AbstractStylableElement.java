/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.style.stylable;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;
import ch.nolix.system.element.mutableelement.AbstractMutableElementWithProperties;
import ch.nolix.system.element.valueproperty.MultiValueProperty;
import ch.nolix.system.element.valueproperty.OptionalValueProperty;
import ch.nolix.systemapi.style.stylable.StylableElement;

/**
 * @author Silvan Wyss
 * @param <E> the type of a {@link AbstractStylableElement}.
 */
public abstract class AbstractStylableElement<E extends StylableElement<E>>
extends AbstractMutableElementWithProperties
implements StylableElement<E> {
  private static final String ID_HEADER = PascalCaseVariableNameCatalog.ID;

  private static final String TOKEN_HEADER = PascalCaseVariableNameCatalog.TOKEN;

  private final OptionalValueProperty<String> id = OptionalValueProperty.forStringWithNameAndSetter(ID_HEADER, this::setId);

  private final MultiValueProperty<String> tokens = MultiValueProperty.forStringsWithNameAndAdder(TOKEN_HEADER, this::addToken);

  @Override
  public final E addToken(final String token) {
    Validator.assertThat(token).thatIsNamed(LowerCaseVariableNameCatalog.TOKEN).isNotBlank();

    tokens.addValue(token);

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getId() {
    return id.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<String> getTokens() {
    return tokens.getStoredValues();
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
    tokens.removeAllOccurrencesOfValue(token);
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

    getStoredChildStylableElements().forEach(StylableElement::resetStyleRecursively);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final E setId(final String id) {
    Validator.assertThat(id).thatIsNamed(LowerCaseVariableNameCatalog.ID).isNotBlank();

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

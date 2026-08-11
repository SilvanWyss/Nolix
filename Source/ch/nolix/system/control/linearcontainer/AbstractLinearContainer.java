/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.linearcontainer;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.system.control.container.AbstractContainer;
import ch.nolix.system.element.valueproperty.MultiValueProperty;
import ch.nolix.system.webgui.main.ControlFactory;
import ch.nolix.system.webgui.main.ControlParent;
import ch.nolix.systemapi.control.linearcontainer.ILinearContainer;
import ch.nolix.systemapi.control.linearcontainer.ILinearContainerStyle;
import ch.nolix.systemapi.webgui.html.IHtmlElementEvent;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 * @param <C> the type of a {@link AbstractLinearContainer}.
 * @param <S> the type of the {@link ILinearContainerStyle}s of a
 *            {@link AbstractLinearContainer}.
 */
public abstract class AbstractLinearContainer<C extends ILinearContainer<C, S>, S extends ILinearContainerStyle<S>>
extends AbstractContainer<C, S>
implements ILinearContainer<C, S> {
  private static final String CHILD_CONTROL_HEADER = "ChildControl";

  private final MultiValueProperty<Control<?, ?>> childControls = //
  MultiValueProperty.forElementsWithNameAndAdderAndValueMapper(
    CHILD_CONTROL_HEADER,
    this::addControl,
    ControlFactory::createControlFromSpecification);

  @Override
  public final C addControl(final Control<?, ?> control) {
    final var controlParent = ControlParent.forControl(this);

    control.internalSetControlParent(controlParent);
    childControls.addValue(control);

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C addControls(final Control<?, ?>... controls) {
    for (final var c : controls) {
      addControl(c);
    }

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C addControls(final ExtendedIterable<? extends Control<?, ?>> controls) {
    controls.forEach(this::addControl);

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void clear() {
    childControls.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<Control<?, ?>> getStoredChildControls() {
    return childControls.getStoredValues();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<Control<?, ?>> getStoredStructureControls() {
    return getStoredChildControls();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isEmpty() {
    return childControls.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeControl(final Control<?, ?> control) {
    childControls.removeAllOccurrencesOfValue(control);
  }
}

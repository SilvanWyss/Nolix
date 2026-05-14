/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.linearcontainer;

import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.system.containercontrol.container.AbstractContainer;
import ch.nolix.system.property.value.MultiValue;
import ch.nolix.system.webgui.main.ControlFactory;
import ch.nolix.systemapi.containercontrol.linearcontainer.ILinearContainer;
import ch.nolix.systemapi.containercontrol.linearcontainer.ILinearContainerStyle;
import ch.nolix.systemapi.webgui.controlstyle.IControlStyle;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IHtmlElementEvent;

/**
 * @author Silvan Wyss
 * @param <C> is the type of a {@link AbstractLinearContainer}.
 * @param <S> is the type of the {@link ILinearContainerStyle}s of a
 *            {@link AbstractLinearContainer}.
 */
public abstract class AbstractLinearContainer<C extends ILinearContainer<C, S>, S extends ILinearContainerStyle<S>>
extends AbstractContainer<C, S>
implements ILinearContainer<C, S> {
  private static final String CHILD_CONTROL_HEADER = "ChildControl";

  private final MultiValue<IControl<?, ?>> childControls = //
  MultiValue.forElementsWithNameAndAdderAndValueMapper(
    CHILD_CONTROL_HEADER,
    this::addControl,
    ControlFactory::createControlFromSpecification);

  @Override
  public final C addControl(final IControl<?, ?> control) {
    control.internalSetParentControl(this);
    childControls.addValue(control);

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C addControls(final IControl<?, ?>... controls) {
    for (final var c : controls) {
      addControl(c);
    }

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C addControls(final IContainer<? extends IControl<?, ?>> controls) {
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
  public final IContainer<IControl<?, ?>> getStoredChildControls() {
    return childControls.getStoredValues();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public final <T extends IControl<T, X>, X extends IControlStyle<X>> IContainer<T> getStoredStructureControls() {
    return (IContainer<T>) getStoredChildControls();
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
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeControl(final IControl<?, ?> control) {
    childControls.removeAllOccurrencesOfValue(control);
  }
}

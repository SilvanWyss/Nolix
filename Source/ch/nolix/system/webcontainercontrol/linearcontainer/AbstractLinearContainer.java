/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webcontainercontrol.linearcontainer;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.system.element.property.MultiValue;
import ch.nolix.system.webcontainercontrol.container.AbstractContainer;
import ch.nolix.system.webgui.main.ControlFactory;
import ch.nolix.systemapi.webcontainercontrol.linearcontainer.ILinearContainer;
import ch.nolix.systemapi.webcontainercontrol.linearcontainer.ILinearContainerStyle;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IHtmlElementEvent;

public abstract class AbstractLinearContainer<C extends ILinearContainer<C, S>, S extends ILinearContainerStyle<S>>
extends AbstractContainer<C, S>
implements ILinearContainer<C, S> {
  private static final String CHILD_CONTROL_HEADER = "ChildControl";

  private final MultiValue<IControl<?, ?>> childControls = new MultiValue<>(
    CHILD_CONTROL_HEADER,
    this::addControl,
    ControlFactory::createControlFromSpecification,
    IControl::getSpecification);

  @Override
  public final C addControl(final IControl<?, ?> control) {
    control.internalSetParentControl(this);

    this.childControls.add(control);

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
    childControls.remove(control);
  }
}

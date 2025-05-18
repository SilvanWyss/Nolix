package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.core.container.linkedlist.ContainerView;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.system.element.property.MultiValue;
import ch.nolix.system.webgui.basecontainer.AbstractContainer;
import ch.nolix.system.webgui.main.ControlFactory;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.ILinearContainer;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.ILinearContainerStyle;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

public abstract class LinearContainer<C extends ILinearContainer<C, S>, S extends ILinearContainerStyle<S>>
extends AbstractContainer<C, S>
implements ILinearContainer<C, S> {

  private static final String CHILD_CONTROL_HEADER = "ChildControl";

  private final MultiValue<IControl<?, ?>> childControls = new MultiValue<>(
    CHILD_CONTROL_HEADER,
    this::addControl,
    ControlFactory::createControlFromSpecification,
    IControl::getSpecification);

  @Override
  public final C addControl(IControl<?, ?> control, final IControl<?, ?>... controls) {

    final var allControls = ContainerView.forElementAndArray(control, controls);

    return addControls(allControls);
  }

  @Override
  public final C addControls(final IContainer<? extends IControl<?, ?>> controls) {

    controls.forEach(this::addControl);

    return asConcrete();
  }

  @Override
  public final void clear() {
    childControls.clear();
  }

  @Override
  public final IContainer<IControl<?, ?>> getStoredChildControls() {
    return childControls.getStoredValues();
  }

  @Override
  public final boolean isEmpty() {
    return childControls.isEmpty();
  }

  @Override
  public final void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    //Does nothing.
  }

  @Override
  public final void removeControl(final IControl<?, ?> control) {
    childControls.remove(control);
  }

  private void addControl(final IControl<?, ?> control) {

    control.internalSetParentControl(this);

    this.childControls.add(control);
  }
}

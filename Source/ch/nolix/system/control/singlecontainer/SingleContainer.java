/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.singlecontainer;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.system.control.container.AbstractContainer;
import ch.nolix.system.element.valueproperty.OptionalValueProperty;
import ch.nolix.system.webgui.main.ControlFactory;
import ch.nolix.systemapi.control.singlecontainer.ISingleContainer;
import ch.nolix.systemapi.control.singlecontainer.ISingleContainerStyle;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.html.IHtmlElementEvent;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public final class SingleContainer
extends AbstractContainer<ISingleContainer, ISingleContainerStyle>
implements ISingleContainer {
  private static final String CONTROL_HEADER = "Control";

  private static final SingleContainerHtmlBuilder HTML_BUILDER = new SingleContainerHtmlBuilder();

  private static final SingleContainerCssBuilder CSS_BUILDER = new SingleContainerCssBuilder();

  private final OptionalValueProperty<Control<?, ?>> memberControl = //
  OptionalValueProperty.withNameAndSetterAndValueMapperAndSpecificationMapper(
    CONTROL_HEADER,
    this::setControl,
    ControlFactory::createControlFromSpecification,
    Control::getSpecification);

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    if (containsAny()) {
      unregisterChildControl(getStoredControl());
      memberControl.clear();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Control<?, ?>> getStoredChildControls() {
    if (isEmpty()) {
      return ImmutableList.createEmpty();
    }

    return ImmutableList.withElements(getStoredControl());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Control<?, ?>> getStoredStructureControls() {
    return getStoredChildControls();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Control<?, ?> getStoredControl() {
    return memberControl.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return !memberControl.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SingleContainer setControl(final Control<?, ?> control) {
    if (!containsControl(control)) {
      clear();
      registerChildControl(control);
      memberControl.setValue(control);
    }

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected SingleContainerStyle createStyle() {
    return new SingleContainerStyle();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlCssBuilder<ISingleContainer, ISingleContainerStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlHtmlBuilder<ISingleContainer> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void resetContainer() {
    clear();
  }

  /**
   * @param control
   * @return true if the current {@link SingleContainer} contains the given
   *         control, false otherwise
   */
  private boolean containsControl(final Control<?, ?> control) {
    return containsAny() && getStoredControl() == control;
  }
}

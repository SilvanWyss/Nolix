package ch.nolix.system.webcontainercontrol.singlecontainer;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.webcontainercontrol.container.AbstractContainer;
import ch.nolix.system.webgui.main.ControlFactory;
import ch.nolix.systemapi.webcontainercontrol.singlecontainer.ISingleContainer;
import ch.nolix.systemapi.webcontainercontrol.singlecontainer.ISingleContainerStyle;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IHtmlElementEvent;

/**
 * @author Silvan Wyss
 */
public final class SingleContainer
extends AbstractContainer<ISingleContainer, ISingleContainerStyle>
implements ISingleContainer {
  private static final String CONTROL_HEADER = "Control";

  private static final SingleContainerHtmlBuilder HTML_BUILDER = new SingleContainerHtmlBuilder();

  private static final SingleContainerCssBuilder CSS_BUILDER = new SingleContainerCssBuilder();

  private final MutableOptionalValue<IControl<?, ?>> control = new MutableOptionalValue<>(
    CONTROL_HEADER,
    this::setControl,
    ControlFactory::createControlFromSpecification,
    IControl::getSpecification);

  @Override
  public void clear() {
    control.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<IControl<?, ?>> getStoredChildControls() {
    if (isEmpty()) {
      return ImmutableList.createEmpty();
    }

    return ImmutableList.withElements(getStoredControl());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IControl<?, ?> getStoredControl() {
    return control.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return !control.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SingleContainer setControl(final IControl<?, ?> control) {
    control.internalSetParentControl(this);
    this.control.setValue(control);

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
}

package ch.nolix.system.webgui.container;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.webgui.basecontainer.AbstractContainer;
import ch.nolix.system.webgui.main.ControlFactory;
import ch.nolix.systemapi.webguiapi.containerapi.ISingleContainer;
import ch.nolix.systemapi.webguiapi.containerapi.ISingleContainerStyle;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

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

  @Override
  public IContainer<IControl<?, ?>> getStoredChildControls() {

    if (isEmpty()) {
      return ImmutableList.createEmpty();
    }

    return ImmutableList.withElement(getStoredControl());
  }

  @Override
  public IControl<?, ?> getStoredControl() {
    return control.getValue();
  }

  @Override
  public boolean isEmpty() {
    return !control.containsAny();
  }

  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    //Does nothing.
  }

  @Override
  public SingleContainer setControl(final IControl<?, ?> control) {

    control.internalSetParentControl(this);
    this.control.setValue(control);

    return this;
  }

  @Override
  protected SingleContainerStyle createStyle() {
    return new SingleContainerStyle();
  }

  @Override
  protected IControlCssBuilder<ISingleContainer, ISingleContainerStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  @Override
  protected IControlHtmlBuilder<ISingleContainer> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  @Override
  protected void resetContainer() {
    clear();
  }
}

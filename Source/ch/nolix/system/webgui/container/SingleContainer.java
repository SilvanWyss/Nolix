//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.webgui.basecontainer.Container;
import ch.nolix.system.webgui.main.GlobalControlFactory;
import ch.nolix.systemapi.webguiapi.containerapi.ISingleContainer;
import ch.nolix.systemapi.webguiapi.containerapi.ISingleContainerStyle;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public final class SingleContainer
extends Container<ISingleContainer, ISingleContainerStyle>
implements ISingleContainer {

  //constant
  private static final String CONTROL_HEADER = "Control";

  //constant
  private static final SingleContainerHtmlBuilder HTML_BUILDER = new SingleContainerHtmlBuilder();

  //constant
  private static final SingleContainerCssBuilder CSS_BUILDER = new SingleContainerCssBuilder();

  //attribute
  private final MutableOptionalValue<IControl<?, ?>> control = new MutableOptionalValue<>(
    CONTROL_HEADER,
    this::setControl,
    GlobalControlFactory::createControlFromSpecification,
    IControl::getSpecification);

  //method
  @Override
  public void clear() {
    control.clear();
  }

  //method
  @Override
  public IContainer<IControl<?, ?>> getStoredChildControls() {

    if (isEmpty()) {
      return new ImmutableList<>();
    }

    return ImmutableList.withElement(getStoredControl());
  }

  //method
  @Override
  public IControl<?, ?> getStoredControl() {
    return control.getValue();
  }

  //method
  @Override
  public boolean isEmpty() {
    return !control.containsAny();
  }

  //method
  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    //Does nothing.
  }

  //method
  @Override
  public SingleContainer setControl(final IControl<?, ?> control) {

    control.technicalSetParentControl(this);
    this.control.setValue(control);

    return this;
  }

  //method
  @Override
  protected SingleContainerStyle createStyle() {
    return new SingleContainerStyle();
  }

  //method
  @Override
  protected IControlCssBuilder<ISingleContainer, ISingleContainerStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  //method
  @Override
  protected IControlHtmlBuilder<ISingleContainer> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  //method
  @Override
  protected void resetContainer() {
    clear();
  }
}

//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.systemapi.guiapi.structureproperty.HorizontalContentAlignment;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStack;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStackStyle;

//class
public final class VerticalStack //NOSONAR: A VerticalStack is a LinearContainer.
extends LinearContainer<IVerticalStack, IVerticalStackStyle>
implements IVerticalStack {

  //constant
  public static final HorizontalContentAlignment DEFAULT_CONTENT_ALIGNMENT = HorizontalContentAlignment.LEFT;

  //constant
  private static final String CONTENT_ALIGNMENT_HEADER = "ContentAlignment";

  //constant
  private static final VerticalStackHtmlBuilder HTML_BUILDER = new VerticalStackHtmlBuilder();

  //constant
  private static final VerticalStackCssBuilder CSS_BUILDER = new VerticalStackCssBuilder();

  //attribute
  private final MutableValue<HorizontalContentAlignment> contentAlignment = new MutableValue<>(
    CONTENT_ALIGNMENT_HEADER,
    DEFAULT_CONTENT_ALIGNMENT,
    this::setContentAlignment,
    HorizontalContentAlignment::fromSpecification,
    Node::fromEnum);

  //method
  @Override
  public HorizontalContentAlignment getContentAlignment() {
    return contentAlignment.getValue();
  }

  //method
  @Override
  public IVerticalStack setContentAlignment(final HorizontalContentAlignment contentAlignment) {

    this.contentAlignment.setValue(contentAlignment);

    return this;
  }

  //method
  @Override
  protected IVerticalStackStyle createStyle() {
    return new VerticalStackStyle();
  }

  //method
  @Override
  protected IControlCssBuilder<IVerticalStack, IVerticalStackStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  //method
  @Override
  protected IControlHtmlBuilder<IVerticalStack> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  //method
  @Override
  protected void resetContainer() {
    setContentAlignment(DEFAULT_CONTENT_ALIGNMENT);
  }
}

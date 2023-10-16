//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.systemapi.guiapi.structureproperty.VerticalContentAlignment;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStack;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStackStyle;

//class
public final class HorizontalStack // NOSONAR: A HorizontalStack is a LinearContainer.
    extends LinearContainer<IHorizontalStack, IHorizontalStackStyle>
    implements IHorizontalStack {

  // constant
  public static final VerticalContentAlignment DEFAULT_CONTENT_ALIGNMENT = VerticalContentAlignment.TOP;

  // constant
  private static final String CONTENT_ALIGNMENT_HEADER = "ContentAlignment";

  // constant
  private static final HorizontalStackHtmlBuilder HTML_BUILDER = new HorizontalStackHtmlBuilder();

  // constant
  private static final HorizontalStackCssBuilder CSS_BUILDER = new HorizontalStackCssBuilder();

  // attribute
  private final MutableValue<VerticalContentAlignment> contentAlignment = new MutableValue<>(
      CONTENT_ALIGNMENT_HEADER,
      DEFAULT_CONTENT_ALIGNMENT,
      this::setContentAlignment,
      VerticalContentAlignment::fromSpecification,
      Node::fromEnum);

  // method
  @Override
  public VerticalContentAlignment getContentAlignment() {
    return contentAlignment.getValue();
  }

  // method
  @Override
  public IHorizontalStack setContentAlignment(final VerticalContentAlignment contentAlignment) {

    this.contentAlignment.setValue(contentAlignment);

    return this;
  }

  // method
  @Override
  protected IHorizontalStackStyle createStyle() {
    return new HorizontalStackStyle();
  }

  // method
  @Override
  protected IControlCssBuilder<IHorizontalStack, IHorizontalStackStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  // method
  @Override
  protected IControlHtmlBuilder<IHorizontalStack> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  // method
  @Override
  protected void resetContainer() {
    setContentAlignment(DEFAULT_CONTENT_ALIGNMENT);
  }
}

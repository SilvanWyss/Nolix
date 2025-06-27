package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.core.document.node.Node;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.systemapi.guiapi.contentalignmentproperty.HorizontalContentAlignment;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStack;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStackStyle;

public final class VerticalStack //NOSONAR: A VerticalStack is a LinearContainer.
extends AbstractLinearContainer<IVerticalStack, IVerticalStackStyle>
implements IVerticalStack {

  public static final HorizontalContentAlignment DEFAULT_CONTENT_ALIGNMENT = HorizontalContentAlignment.LEFT;

  private static final String CONTENT_ALIGNMENT_HEADER = "ContentAlignment";

  private static final VerticalStackHtmlBuilder HTML_BUILDER = new VerticalStackHtmlBuilder();

  private static final VerticalStackCssBuilder CSS_BUILDER = new VerticalStackCssBuilder();

  private final MutableValue<HorizontalContentAlignment> contentAlignment = new MutableValue<>(
    CONTENT_ALIGNMENT_HEADER,
    DEFAULT_CONTENT_ALIGNMENT,
    this::setContentAlignment,
    HorizontalContentAlignment::fromSpecification,
    Node::fromEnum);

  @Override
  public HorizontalContentAlignment getContentAlignment() {
    return contentAlignment.getValue();
  }

  @Override
  public IVerticalStack setContentAlignment(final HorizontalContentAlignment contentAlignment) {

    this.contentAlignment.setValue(contentAlignment);

    return this;
  }

  @Override
  protected IVerticalStackStyle createStyle() {
    return new VerticalStackStyle();
  }

  @Override
  protected IControlCssBuilder<IVerticalStack, IVerticalStackStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  @Override
  protected IControlHtmlBuilder<IVerticalStack> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  @Override
  protected void resetContainer() {
    setContentAlignment(DEFAULT_CONTENT_ALIGNMENT);
  }
}

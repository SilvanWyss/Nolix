/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.verticalstack;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.system.control.linearcontainer.AbstractLinearContainer;
import ch.nolix.system.element.valueproperty.ValueProperty;
import ch.nolix.systemapi.control.verticalstack.IVerticalStack;
import ch.nolix.systemapi.control.verticalstack.IVerticalStackStyle;
import ch.nolix.systemapi.gui.guiproperty.HorizontalContentAlignment;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

/**
 * @author Silvan Wyss
 */
public final class VerticalStack // NOSONAR: A VerticalStack is a LinearContainer.
extends AbstractLinearContainer<IVerticalStack, IVerticalStackStyle>
implements IVerticalStack {
  public static final HorizontalContentAlignment DEFAULT_CONTENT_ALIGNMENT = HorizontalContentAlignment.LEFT;

  private static final String CONTENT_ALIGNMENT_HEADER = "ContentAlignment";

  private static final VerticalStackHtmlBuilder HTML_BUILDER = new VerticalStackHtmlBuilder();

  private static final VerticalStackCssBuilder CSS_BUILDER = new VerticalStackCssBuilder();

  private final ValueProperty<HorizontalContentAlignment> contentAlignment = //
  ValueProperty.withNameAndDefaultValueAndSetterAndValueMapperAndSpecificationMapper(
    CONTENT_ALIGNMENT_HEADER,
    DEFAULT_CONTENT_ALIGNMENT,
    this::setContentAlignment,
    HorizontalContentAlignment::fromSpecification,
    ImmutableNode::fromEnum);

  @Override
  public HorizontalContentAlignment getContentAlignment() {
    return contentAlignment.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IVerticalStack setContentAlignment(final HorizontalContentAlignment contentAlignment) {
    this.contentAlignment.setValue(contentAlignment);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IVerticalStackStyle createStyle() {
    return new VerticalStackStyle();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlCssBuilder<IVerticalStack, IVerticalStackStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlHtmlBuilder<IVerticalStack> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void resetContainer() {
    setContentAlignment(DEFAULT_CONTENT_ALIGNMENT);
  }
}

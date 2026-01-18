/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webcontainercontrol.horizontalstack;

import ch.nolix.core.document.node.Node;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.system.webcontainercontrol.linearcontainer.AbstractLinearContainer;
import ch.nolix.systemapi.gui.box.VerticalContentAlignment;
import ch.nolix.systemapi.webcontainercontrol.horizontalstack.IHorizontalStack;
import ch.nolix.systemapi.webcontainercontrol.horizontalstack.IHorizontalStackStyle;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

/**
 * @author Silvan Wyss
 */
public final class HorizontalStack //NOSONAR: A HorizontalStack is a LinearContainer.
extends AbstractLinearContainer<IHorizontalStack, IHorizontalStackStyle>
implements IHorizontalStack {
  public static final VerticalContentAlignment DEFAULT_CONTENT_ALIGNMENT = VerticalContentAlignment.TOP;

  private static final String CONTENT_ALIGNMENT_HEADER = "ContentAlignment";

  private static final HorizontalStackHtmlBuilder HTML_BUILDER = new HorizontalStackHtmlBuilder();

  private static final HorizontalStackCssBuilder CSS_BUILDER = new HorizontalStackCssBuilder();

  private final MutableValue<VerticalContentAlignment> contentAlignment = new MutableValue<>(
    CONTENT_ALIGNMENT_HEADER,
    DEFAULT_CONTENT_ALIGNMENT,
    this::setContentAlignment,
    VerticalContentAlignment::fromSpecification,
    Node::fromEnum);

  @Override
  public VerticalContentAlignment getContentAlignment() {
    return contentAlignment.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IHorizontalStack setContentAlignment(final VerticalContentAlignment contentAlignment) {
    this.contentAlignment.setValue(contentAlignment);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IHorizontalStackStyle createStyle() {
    return new HorizontalStackStyle();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlCssBuilder<IHorizontalStack, IHorizontalStackStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlHtmlBuilder<IHorizontalStack> getHtmlBuilder() {
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

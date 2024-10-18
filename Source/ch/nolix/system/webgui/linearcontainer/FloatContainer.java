package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.systemapi.webguiapi.controltoolapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;

public final class FloatContainer //NOSONAR: A FloatContainer is a LinearContainer.
extends LinearContainer<FloatContainer, FloatContainerStyle> {

  private static final FloatContainerHtmlBuilder HTML_BUILDER = new FloatContainerHtmlBuilder();

  private static final FloatContainerCssBuilder CSS_BUILDER = new FloatContainerCssBuilder();

  @Override
  protected FloatContainerStyle createStyle() {
    return new FloatContainerStyle();
  }

  @Override
  protected IControlCssBuilder<FloatContainer, FloatContainerStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  @Override
  protected IControlHtmlBuilder<FloatContainer> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  @Override
  protected void resetContainer() {
    //Does nothing.
  }
}

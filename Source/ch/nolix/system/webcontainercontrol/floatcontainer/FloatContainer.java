package ch.nolix.system.webcontainercontrol.floatcontainer;

import ch.nolix.system.webcontainercontrol.linearcontainer.AbstractLinearContainer;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

/**
 * @author Silvan Wyss
 */
public final class FloatContainer //NOSONAR: A FloatContainer is a LinearContainer.
extends AbstractLinearContainer<FloatContainer, FloatContainerStyle> {
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

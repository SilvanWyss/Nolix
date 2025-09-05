package ch.nolix.systemapi.webgui.main;

import ch.nolix.coreapi.component.guicomponent.IGuiComponent;
import ch.nolix.coreapi.web.cssmodel.ICssRule;
import ch.nolix.coreapi.web.html.IHtmlGetter;
import ch.nolix.systemapi.gui.background.IBackgroundHolder;
import ch.nolix.systemapi.gui.box.ContentAlignment;
import ch.nolix.systemapi.style.stylable.IStylableElement;

public interface ILayer<L extends ILayer<L>>
extends
IBackgroundHolder<L>,
IGuiComponent<IWebGui<?>>,
IHtmlGetter,
IRootControlOwner<L>,
IStylableElement<L> {
  boolean containsControl(IControl<?, ?> control);

  ContentAlignment getContentAlignment();

  ICssRule getCssRule();

  String getInternalId();

  double getOpacity();

  LayerRole getRole();

  boolean hasInternalId(String internalId);

  boolean hasRole();

  void internalSetParentGui(IWebGui<?> parentGui);

  void removeSelfFromGui();

  L setContentAlignment(ContentAlignment contentAlignment);

  L setRole(LayerRole role);

  L setOpacity(double opacity);
}

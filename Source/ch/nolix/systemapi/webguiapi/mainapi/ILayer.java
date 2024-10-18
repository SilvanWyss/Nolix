package ch.nolix.systemapi.webguiapi.mainapi;

import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlGetter;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;
import ch.nolix.systemapi.guiapi.canvasapi.ICanvas;
import ch.nolix.systemapi.guiapi.contentalignmentproperty.ContentAlignment;

public interface ILayer<L extends ILayer<L>>
extends
IHtmlGetter,
ICanvas<L>,
IStylableElement<L>,
IRootControlOwner<L> {

  boolean belongsToGui();

  boolean containsControl(IControl<?, ?> control);

  ContentAlignment getContentAlignment();

  ICssRule getCssRule();

  String getInternalId();

  double getOpacity();

  IWebGui<?> getStoredParentGui();

  LayerRole getRole();

  boolean hasInternalId(String internalId);

  boolean hasRole();

  void internalSetParentGui(IWebGui<?> parentGui);

  void removeSelfFromGui();

  L setContentAlignment(ContentAlignment contentAlignment);

  L setRole(LayerRole role);

  L setOpacity(double opacity);
}

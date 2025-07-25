package ch.nolix.system.webgui.main;

import java.util.Optional;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.ILayerStack;
import ch.nolix.systemapi.webgui.main.IWebGui;

public final class LayerStack implements ILayerStack {

  private final IWebGui<?> parentGui;

  private Runnable removeLayerAction;

  //multi-atribute
  private final ILinkedList<ILayer<?>> layers = LinkedList.createEmpty();

  private LayerStack(final IWebGui<?> parentGui) {

    Validator.assertThat(parentGui).thatIsNamed("parent gui").isNotNull();

    this.parentGui = parentGui;
  }

  public static LayerStack forWebGui(final IWebGui<?> webGui) {
    return new LayerStack(webGui);
  }

  @Override
  public boolean containsControl(final IControl<?, ?> control) {
    return getStoredLayers().containsAny(l -> l.containsControl(control));
  }

  @Override
  public void clear() {
    while (containsAny()) {
      removeLayer(getStoredTopLayer());
    }
  }

  @Override
  public int getLayerCount() {
    return getStoredLayers().getCount();
  }

  @Override
  public Optional<IControl<?, ?>> getOptionalStoredControlByInternalId(String internalId) {

    for (final var l : getStoredLayers()) {

      final var control = l.getOptionalStoredControlByInternalId(internalId);

      if (control.isPresent()) {
        return control;
      }
    }

    return Optional.empty();
  }

  @Override
  public IContainer<IControl<?, ?>> getStoredControls() {
    return getStoredLayers().toMultiples(ILayer::getStoredControls);
  }

  @Override
  public IContainer<ILayer<?>> getStoredLayers() {
    return layers;
  }

  @Override
  public ILayer<?> getStoredTopLayer() {
    return getStoredLayers().getStoredLast();
  }

  @Override
  public boolean hasRemoveLayerAction() {
    return (removeLayerAction != null);
  }

  @Override
  public boolean isEmpty() {
    return getStoredLayers().isEmpty();
  }

  @Override
  public ILayerStack pushLayer(ILayer<?> layer) {

    layer.internalSetParentGui(parentGui);

    layers.addAtEnd(layer);

    return this;
  }

  @Override
  public ILayerStack pushLayerWithRootControl(IControl<?, ?> rootControl) {
    return pushLayer(new Layer().setRootControl(rootControl));
  }

  @Override
  public void removeLayer(final ILayer<?> layer) {

    layers.removeStrictlyFirstOccurrenceOf(layer);

    runProbableRemoveLayerAction();
  }

  //mehod
  @Override
  public ILayerStack setRemoveLayerAction(Runnable removeLayerAction) {

    Validator.assertThat(removeLayerAction).thatIsNamed("remove layer action").isNotNull();

    this.removeLayerAction = removeLayerAction;

    return this;
  }

  private void runProbableRemoveLayerAction() {
    if (hasRemoveLayerAction()) {
      removeLayerAction.run();
    }
  }
}

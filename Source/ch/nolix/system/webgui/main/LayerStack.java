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

/**
 * @author Silvan Wyss
 */
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

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsControl(final IControl<?, ?> control) {
    return getStoredLayers().containsAny(l -> l.containsControl(control));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    while (containsAny()) {
      removeLayer(getStoredTopLayer());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getLayerCount() {
    return getStoredLayers().getCount();
  }

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<IControl<?, ?>> getStoredControls() {
    return getStoredLayers().toMultiples(ILayer::getStoredControls);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<ILayer<?>> getStoredLayers() {
    return layers;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ILayer<?> getStoredTopLayer() {
    return getStoredLayers().getStoredLast();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasRemoveLayerAction() {
    return (removeLayerAction != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return getStoredLayers().isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ILayerStack pushLayer(ILayer<?> layer) {
    layer.internalSetParentGui(parentGui);

    layers.addAtEnd(layer);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ILayerStack pushLayerWithRootControl(IControl<?, ?> rootControl) {
    return pushLayer(new Layer().setRootControl(rootControl));
  }

  /**
   * {@inheritDoc}
   */
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

//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.ILayerStack;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public final class LayerStack implements ILayerStack {

  //attribute
  private final IWebGui<?> parentGui;

  //optional attribute
  private Runnable removeLayerAction;

  //multi-atribute
  private final ILinkedList<ILayer<?>> layers = new LinkedList<>();

  //constructor
  private LayerStack(final IWebGui<?> parentGui) {

    GlobalValidator.assertThat(parentGui).thatIsNamed("parent gui").isNotNull();

    this.parentGui = parentGui;
  }

  //static method
  public static LayerStack forWebGui(final IWebGui<?> webGui) {
    return new LayerStack(webGui);
  }

  //method
  @Override
  public void clear() {
    while (containsAny()) {
      removeLayer(getStoredTopLayer());
    }
  }

  //method
  @Override
  public IControl<?, ?> getStoredControlOrNullByInternalId(String internalId) {

    for (final var l : getStoredLayers()) {

      final var control = l.getStoredControlOrNullByInternalId(internalId);

      if (control != null) {
        return control;
      }
    }

    return null;
  }

  //method
  @Override
  public IContainer<IControl<?, ?>> getStoredControls() {
    return getStoredLayers().toFromGroups(ILayer::getStoredControls);
  }

  //method
  @Override
  public IContainer<ILayer<?>> getStoredLayers() {
    return layers;
  }

  //method
  @Override
  public ILayer<?> getStoredTopLayer() {
    return getStoredLayers().getStoredLast();
  }

  //method
  @Override
  public boolean hasRemoveLayerAction() {
    return (removeLayerAction != null);
  }

  //method
  @Override
  public boolean isEmpty() {
    return getStoredLayers().isEmpty();
  }

  //method
  @Override
  public ILayerStack pushLayer(ILayer<?> layer) {

    layer.technicalSetParentGui(parentGui);

    layers.addAtEnd(layer);

    return this;
  }

  //method
  @Override
  public ILayerStack pushLayerWithRootControl(IControl<?, ?> rootControl) {
    return pushLayer(new Layer().setRootControl(rootControl));
  }

  //method
  @Override
  public void removeLayer(final ILayer<?> layer) {

    layers.removeFirstOccurrenceOf(layer);

    runProbableRemoveLayerAction();
  }

  //mehod
  @Override
  public ILayerStack setRemoveLayerAction(Runnable removeLayerAction) {

    GlobalValidator.assertThat(removeLayerAction).thatIsNamed("remove layer action").isNotNull();

    this.removeLayerAction = removeLayerAction;

    return this;
  }

  //method
  private void runProbableRemoveLayerAction() {
    if (hasRemoveLayerAction()) {
      removeLayerAction.run();
    }
  }
}

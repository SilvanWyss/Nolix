//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IUploader;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IUploaderStyle;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public final class Uploader extends Control<IUploader, IUploaderStyle> implements IUploader {

  //constant
  private static final UploaderHtmlBuilder HTML_BUILDER = new UploaderHtmlBuilder();

  //constant
  private static final UploaderCssBuilder CSS_BUILDER = new UploaderCssBuilder();

  //optional attribute
  private byte[] file;

  @Override
  public byte[] getFile() {

    assertHasFile();

    return file.clone();
  }

  //method
  @Override
  public ISingleContainer<String> getOptionalJavaScriptUserInputFunction() {
    return new SingleContainer<>();
  }

  //method
  @Override
  public IContainer<IControl<?, ?>> getStoredChildControls() {
    return new ImmutableList<>();
  }

  //method
  @Override
  public String getUserInput() {
    return StringCatalogue.EMPTY_STRING;
  }

  //method
  @Override
  public boolean hasFile() {
    return (file != null);
  }

  //method
  @Override
  public boolean hasRole(final String role) {
    return false;
  }

  //method
  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    //Does nothing.
  }

  //method
  @Override
  public void runHtmlEvent(final String htmlEvent) {
    //Does nothing.
  }

  //method
  @Override
  public IUploader setUserInput(final String userInput) {

    //Does nothing.

    return this;
  }

  //method
  @Override
  public void technicalSetFile(final byte[] file) {

    GlobalValidator.assertThat(file).thatIsNamed(LowerCaseCatalogue.FILE).isNotNull();

    this.file = file; //NOSONAR: A Uploader operates on the original input.
  }

  //method
  @Override
  protected IUploaderStyle createStyle() {
    return new UploaderStyle();
  }

  //method
  @Override
  protected IControlCssBuilder<IUploader, IUploaderStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  //method
  @Override
  protected IControlHtmlBuilder<IUploader> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  //method
  @Override
  protected void resetControl() {
    removeFile();
  }

  //method
  private void assertHasFile() {
    if (!hasFile()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.FILE);
    }
  }

  //method
  private void removeFile() {
    file = null;
  }
}

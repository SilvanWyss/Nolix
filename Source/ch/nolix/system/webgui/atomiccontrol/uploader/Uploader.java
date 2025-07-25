package ch.nolix.system.webgui.atomiccontrol.uploader;

import java.util.Optional;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.programatom.stringcatalog.StringCatalog;
import ch.nolix.coreapi.programatom.variable.LowerCaseVariableCatalog;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.uploaderapi.IUploader;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.uploaderapi.IUploaderStyle;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

public final class Uploader extends Control<IUploader, IUploaderStyle> implements IUploader {

  private static final UploaderHtmlBuilder HTML_BUILDER = new UploaderHtmlBuilder();

  private static final UploaderCssBuilder CSS_BUILDER = new UploaderCssBuilder();

  private byte[] file;

  public Uploader() {

    //A reset is required to achieve a well-defined initial state, although everything would work without a reset.
    reset();
  }

  @Override
  public byte[] getFile() {

    assertHasFile();

    return file.clone();
  }

  @Override
  public Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.empty();
  }

  @Override
  public IContainer<IControl<?, ?>> getStoredChildControls() {
    return ImmutableList.createEmpty();
  }

  @Override
  public String getUserInput() {
    return StringCatalog.EMPTY_STRING;
  }

  @Override
  public boolean hasFile() {
    return (file != null);
  }

  @Override
  public boolean hasRole(final String role) {
    return false;
  }

  @Override
  public void internalSetFile(final byte[] file) {

    Validator.assertThat(file).thatIsNamed(LowerCaseVariableCatalog.FILE).isNotNull();

    this.file = file; //NOSONAR: A Uploader operates on the original input.
  }

  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    //Does nothing.
  }

  @Override
  public void runHtmlEvent(final String htmlEvent) {
    //Does nothing.
  }

  @Override
  public IUploader setUserInput(final String userInput) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUserInput");
  }

  @Override
  protected IUploaderStyle createStyle() {
    return new UploaderStyle();
  }

  @Override
  protected IControlCssBuilder<IUploader, IUploaderStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  @Override
  protected IControlHtmlBuilder<IUploader> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  @Override
  protected void resetControl() {
    removeFile();
  }

  private void assertHasFile() {
    if (!hasFile()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.FILE);
    }
  }

  private void removeFile() {
    file = null;
  }
}

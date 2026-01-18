package ch.nolix.system.webatomiccontrol.uploader;

import java.util.Optional;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.webatomiccontrol.uploader.IUploader;
import ch.nolix.systemapi.webatomiccontrol.uploader.IUploaderStyle;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IHtmlElementEvent;

/**
 * @author Silvan Wyss
 */
public final class Uploader extends Control<IUploader, IUploaderStyle> implements IUploader {
  private static final UploaderHtmlBuilder HTML_BUILDER = new UploaderHtmlBuilder();

  private static final UploaderCssBuilder CSS_BUILDER = new UploaderCssBuilder();

  private byte[] memberFile;

  public Uploader() {
    //A reset is required to achieve a well-defined initial state, although everything would work without a reset.
    reset();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public byte[] getFile() {
    assertHasFile();

    return memberFile.clone();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.empty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<IControl<?, ?>> getStoredChildControls() {
    return ImmutableList.createEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getUserInput() {
    return StringCatalog.EMPTY_STRING;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasFile() {
    return (memberFile != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasRole(final String role) {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void internalSetFile(final byte[] file) {
    Validator.assertThat(file).thatIsNamed(LowerCaseVariableCatalog.FILE).isNotNull();

    memberFile = file; //NOSONAR: A Uploader operates on the original input.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void runHtmlEvent(final String htmlEvent) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IUploader setUserInput(final String userInput) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUserInput");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IUploaderStyle createStyle() {
    return new UploaderStyle();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlCssBuilder<IUploader, IUploaderStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlHtmlBuilder<IUploader> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
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
    memberFile = null;
  }
}

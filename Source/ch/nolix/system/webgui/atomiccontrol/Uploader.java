//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.function.FunctionCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
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
	public static final IElementTaker<Byte[]> DEFAULT_FILE_TAKER = FunctionCatalogue::takeObjectAndDoNothing;
	
	//attribute
	private IElementTaker<Byte[]> fileTaker = DEFAULT_FILE_TAKER;
	
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
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	@Override
	public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
		//TODO: Implement.
	}
	
	//method
	@Override
	public void runHtmlEvent(final String htmlEvent) {
		//TODO: Implement.
	}
	
	//method
	@Override
	public IUploader setFileTaker(final IElementTaker<Byte[]> fileTaker) {
		
		GlobalValidator.assertThat(fileTaker).thatIsNamed("file taker").isNotNull();
		
		this.fileTaker = fileTaker;
		
		return this;
	}
	
	//method
	@Override
	public IUploader setUserInput(final String userInput) {
		
		GlobalValidator.assertThat(userInput).thatIsNamed("user input").isEmpty();
		
		return this;
	}
	
	//method
	@Override
	protected IUploaderStyle createStyle() {
		return new UploaderStyle();
	}
	
	//method
	@Override
	protected IControlCssBuilder<IUploader, IUploaderStyle> getCssRuleCreator() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	protected IControlHtmlBuilder<IUploader> getHtmlBuilder() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	protected void resetControl() {
		setFileTaker(DEFAULT_FILE_TAKER);
	}
}

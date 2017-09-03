//package declaration
package ch.nolix.system.baseGUIClient;

//own imports
import ch.nolix.core.container.AccessorContainer;
import ch.nolix.core.fileSystem.FileSystemAccessor;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specification.Statement;
import ch.nolix.core.util.PopupWindowProvider;
import ch.nolix.system.client.Client;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 120
 */
public abstract class BaseGUIClient<BGUIC extends BaseGUIClient<BGUIC>>
extends Client<BGUIC> {

	//method
	/**
	 * Lets the counterpart of this base GUI client
	 * create a file with the given relative file path and content.
	 * 
	 * @param relativeFilePath
	 * @param content
	 * @return this base GUI client.
	 */
	@SuppressWarnings("unchecked")
	public final BGUIC createFile(
		final String relativeFilePath,
		final String content
	) {
		
		internal_getRefDuplexController().run(
			Protocol.CREATE_FILE_COMMAND
			+ "("
			+ StandardSpecification.createEscapeString(relativeFilePath)
			+ ","
			+ StandardSpecification.createEscapeString(content)
			+ ")"
		);
		
		return (BGUIC)this;
	}
	
	//method
	/**
	 * Lets the counterpart of this base GUI client open a file explorer.
	 * 
	 * @return this base GUI client.
	 */
	@SuppressWarnings("unchecked")
	public BGUIC openFileExplorer() {
		
		internal_getRefDuplexController()
		.run(Protocol.OPEN_FILE_EXPLORER_COMMAND);
		
		return (BGUIC)this;
	}
	
	//method
	/**
	 * Lets this base GUI client show the given error message.
	 * 
	 * @param errorMessage
	 * @return this base GUI client.
	 * @throws NullArgumentException if the given error message is null.
	 */
	@SuppressWarnings("unchecked")
	public final BGUIC showErrorMessage(final String errorMessage) {
		
		internal_getRefDuplexController().run(
			Protocol.SHOW_ERROR_MESSAGE
			+ "("
			+ StandardSpecification.createEscapeString(errorMessage)
			+ ")"
		);
		
		return (BGUIC)this;
	}
	
	//method
	/**
	 * Lets this base GUI client run the given command.
	 * 
	 * @param command
	 * @throws InvalidArgumentException if the given command is not valid.
	 */
	protected void internal_run(final Statement command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case Protocol.SHOW_ERROR_MESSAGE:
				internal_showErrorMessage(command.getOneAttributeToString());
				break;
			case Protocol.CREATE_FILE_COMMAND:
				
				final AccessorContainer<StandardSpecification> parameters
				= command.getRefAttributes();
				
				internal_createFile(
					parameters.getRefAt(1).toString(),
					parameters.getRefAt(2)
				);
				
				break;
			case Protocol.OPEN_FILE_EXPLORER_COMMAND:
				openFileExplorer();
				break;
			default:
				
				//Calls method of the base class.
				super.internal_run(command);
		}
	}

	//method
	/**
	 * Lets this base GUI client a file with the given relative file path and content.
	 * 
	 * @param relativeFilePath
	 * @param content
	 */
	protected final void internal_createFile(
		final String relativeFilePath,
		final Specification content
	) {
		new FileSystemAccessor()
		.createFileIncrementingFileName(relativeFilePath, content.toString());
	}
	
	//method
	/**
	 * Lets this base GUI client open a file explorer.
	 */
	protected final void internal_openFileExplorer() {
		//TODO: Implement opening of a file explorer.
	}
	
	//method
	/**
	 * Lets this base GUI client show the given error message.
	 * 
	 * @param errorMessage
	 */
	protected final void internal_showErrorMessage(final String errorMessage) {
		PopupWindowProvider.showErrorWindow(errorMessage);
	}
}

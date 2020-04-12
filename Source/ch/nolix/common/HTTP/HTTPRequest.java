//package declaration
package ch.nolix.common.HTTP;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.validator.Validator;

//class
public final class HTTPRequest {
	
	//constants
	public static final String ACCEPT_HEADER = "Accept";
	public static final String CONTENT_TYPE_HEADER = "Content-Type";
	public static final String HOST_HEADER = "Host";
	public static final String HTTP_HEADER = "HTTP";
	
	//attribute
	private final String content;
	
	//static method
	public static boolean canBe(final IContainer<String> lines) {	
		return
		lines.contains(l -> l.contains(HTTP_HEADER))
		&& lines.contains(l -> l.contains(HOST_HEADER))
		&& lines.contains(l -> l.contains(ACCEPT_HEADER + ": text/html"));
	}
	
	//constructor
	public HTTPRequest(final String content) {
		
		Validator.assertThat(content).thatIsNamed(VariableNameCatalogue.CONTENT).isNotBlank();
		
		this.content = content;
	}
	
	//method
	public String getContent() {
		return content;
	}
	
	//method
	public String toString() {
		return HTTP_HEADER + "\n";
	}
}

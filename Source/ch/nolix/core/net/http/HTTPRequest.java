//package declaration
package ch.nolix.core.net.http;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;

//class
public record HTTPRequest(String content) {
	
	//constants
	public static final String ACCEPT_HEADER = "Accept";
	public static final String CONTENT_TYPE_HEADER = "Content-Type";
	public static final String HOST_HEADER = "Host";
	public static final String HTTP_HEADER = "HTTP";
	
	//static method
	public static boolean canBe(final IContainer<String> lines) {	
		return
		lines.containsAny(l -> l.contains(HTTP_HEADER))
		&& lines.containsAny(l -> l.contains(HOST_HEADER))
		&& lines.containsAny(l -> l.contains(ACCEPT_HEADER + ": text/html"));
	}
	
	//constructor
	public HTTPRequest(final String content) { //NOSONAR
		
		GlobalValidator.assertThat(content).thatIsNamed(LowerCaseCatalogue.CONTENT).isNotBlank();
		
		this.content = content;
	}
	
	//method
	public String getContent() {
		return content;
	}
	
	//method
	@Override
	public String toString() {
		return HTTP_HEADER + "\n";
	}
}

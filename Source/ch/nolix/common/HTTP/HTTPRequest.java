//package declaration
package ch.nolix.common.HTTP;

//own import
import ch.nolix.common.containers.IContainer;

//class
public final class HTTPRequest {
	
	//constants
	public static final String CONTENT_TYPE_HEADER = "Content-Type";
	public static final String HOST_HEADER = "Host";
	public static final String HTTP_HEADER = "HTTP";	
	
	//TODO: Get rid of SuppressWarnings.
	//static method
	@SuppressWarnings("unchecked")
	public static boolean canBe(final IContainer<String> lines) {
		
		//TODO: Improve check.
		return lines.containsAll(l -> l.contains(HTTP_HEADER), l -> l.contains(HOST_HEADER));
	}
}

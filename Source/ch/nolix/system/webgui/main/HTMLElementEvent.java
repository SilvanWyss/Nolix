//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.webguiapi.mainapi.IHTMLElementEvent;

//record
public record HTMLElementEvent(String mHTMLElementId, String mHTMLEvent) implements IHTMLElementEvent {
	
	//static method
	public static HTMLElementEvent withHTMLElementIdAndHTMLEvent(final String pHTMLElementId, final String pHTMLEvent) {
		return new HTMLElementEvent(pHTMLElementId, pHTMLEvent);
	}
	
	//constructor
	public HTMLElementEvent(final String mHTMLElementId, final String mHTMLEvent) { //NOSONAR
		
		GlobalValidator.assertThat(mHTMLElementId).thatIsNamed("HTML element id").isNotBlank();
		GlobalValidator.assertThat(mHTMLEvent).thatIsNamed("HTML event").isNotBlank();
		
		this.mHTMLElementId = mHTMLElementId;
		this.mHTMLEvent = mHTMLEvent;
	}
	
	//method
	@Override
	public String getHTMLElementId() {
		return mHTMLElementId;
	}
	
	//method
	@Override
	public String getHTMLEvent() {
		return mHTMLEvent;
	}
}

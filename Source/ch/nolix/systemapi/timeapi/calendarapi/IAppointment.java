//package declaration
package ch.nolix.systemapi.timeapi.calendarapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.ISubjectHolder;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IAppointment<A extends IAppointment<A>> extends ISubjectHolder {
	
	//method declaration
	ITime getEndTime();
	
	//method declaration
	ITime getStartTime();
}

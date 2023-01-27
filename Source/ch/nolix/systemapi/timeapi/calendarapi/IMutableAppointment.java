//package declaration
package ch.nolix.systemapi.timeapi.calendarapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi.IMutableSubjectHolder;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IMutableAppointment<MA extends IMutableAppointment<MA>>
extends IAppointment<MA>, IMutableSubjectHolder<MA> {
	
	//method declaration
	MA setEndTime(ITime endTime);
	
	//method declaration
	MA setStartTime(ITime startTime);
}

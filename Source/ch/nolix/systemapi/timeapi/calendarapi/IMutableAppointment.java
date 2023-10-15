//package declaration
package ch.nolix.systemapi.timeapi.calendarapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableSubjectHolder;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IMutableAppointment<MA extends IMutableAppointment<MA>>
    extends IAppointment<MA>, IFluentMutableSubjectHolder<MA> {

  // method declaration
  MA setEndTime(ITime endTime);

  // method declaration
  MA setStartTime(ITime startTime);
}

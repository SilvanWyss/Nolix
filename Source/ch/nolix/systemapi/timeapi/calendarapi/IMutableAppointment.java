//package declaration
package ch.nolix.systemapi.timeapi.calendarapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableTitleHolder;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IMutableAppointment<MA extends IMutableAppointment<MA>>
    extends IAppointment<MA>, IFluentMutableTitleHolder<MA> {

  //method declaration
  MA setEndTime(ITime endTime);

  //method declaration
  MA setStartTime(ITime startTime);
}

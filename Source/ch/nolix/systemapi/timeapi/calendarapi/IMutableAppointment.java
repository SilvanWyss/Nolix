//package declaration
package ch.nolix.systemapi.timeapi.calendarapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.FluentTitleable;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IMutableAppointment<MA extends IMutableAppointment<MA>>
    extends IAppointment<MA>, FluentTitleable<MA> {

  //method declaration
  MA setEndTime(ITime endTime);

  //method declaration
  MA setStartTime(ITime startTime);
}

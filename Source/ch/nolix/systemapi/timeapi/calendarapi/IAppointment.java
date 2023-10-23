//package declaration
package ch.nolix.systemapi.timeapi.calendarapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Titled;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IAppointment<A extends IAppointment<A>> extends Titled {

  //method declaration
  ITime getEndTime();

  //method declaration
  ITime getStartTime();
}

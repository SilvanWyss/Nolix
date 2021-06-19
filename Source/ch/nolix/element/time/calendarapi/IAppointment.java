//package declaration
package ch.nolix.element.time.calendarapi;

//own imports
import ch.nolix.common.attributeapi.mutablemandatoryattributeapi.IMutableSubjectHolder;
import ch.nolix.element.time.base.Time;

//interface
public interface IAppointment<A extends IAppointment<A>> extends IMutableSubjectHolder<A> {
	
	//method declaration
	Time getEndTime();
	
	//method declaration
	Time getStartTime();
	
	//method
	default boolean isAfter(final IAppointment<?> appointment) {
		return isAfter(appointment.getEndTime());
	}
	
	//method
	default boolean isAfter(final Time time) {
		return getStartTime().isAfter(time);
	}
	
	//method
	default boolean isBefore(final Time time) {
		return getEndTime().isBefore(time);
	}
	
	//method
	default boolean isBefore(final IAppointment<?> appointment) {
		return isBefore(appointment.getStartTime());
	}
	
	//method declaration
	A setEndTime(Time endTime);
	
	//method declaration
	A setStartTime(Time startTime);
}

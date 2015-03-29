import java.util.Calendar;
import java.util.Set;

/**
 * Class FutureMeetingImpl - extends the MeetingImpl class and implements the FutureMeeting Interface.
 * 
 * @author Daryl Smith, MSc IT 
 * @version 1
 */

public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting
{

	FutureMeetingImpl(int Id, Calendar myCalendar, Set<Contact> mySet) 
	{
		super(Id, myCalendar, mySet);
	}
}

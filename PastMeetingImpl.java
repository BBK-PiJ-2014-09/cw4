import java.util.Calendar;
import java.util.Set;

/**
 * Class PastMeetingImpl - extends the MeetingImpl class and implements the PastMeeting Interface.
 * 
 * @author Daryl Smith, MSc IT 
 * @version 2
 */

public class PastMeetingImpl extends MeetingImpl implements PastMeeting 
{
  private String notes; 
	
	PastMeetingImpl(int Id, Calendar myCalendar, Set<Contact> mySet, String notes) 
	{
		super(Id, myCalendar, mySet);
		this.notes = notes;
	}

	@Override
	public String getNotes() 
	{
		if (this.notes == null)
		{
			return "";
		}
		return this.notes;
	}

	/** 
	  * A mutator to set notes for a meeting
	  * @param notes (New notes)
	  *  
	*/ 
	public void setNotes(String notes) 
	{
		this.notes = notes;
	}
}
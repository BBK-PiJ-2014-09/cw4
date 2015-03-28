import java.util.Calendar;
import java.util.Set;

/**
 * Class MeetingImpl - implements the Meeting Interface.
 * 
 * @author Daryl Smith, MSc IT 
 * @version 1
 */

public class MeetingImpl implements Meeting 
{
	private int Id;
	private Calendar myCalendar;
	private Set<Contact> mySet;
	
	MeetingImpl(int Id, Calendar myCalendar, Set<Contact> mySet) 
	{
		this.Id = Id;
		this.myCalendar = myCalendar;
		this.mySet = mySet;
	}
	
	@Override
	public int getId() 
	{
		return Id;
	}

	@Override
	public Calendar getDate() 
	{
		return myCalendar;
	}

	@Override
	public Set<Contact> getContacts() 
	{
		return mySet;
	}
}
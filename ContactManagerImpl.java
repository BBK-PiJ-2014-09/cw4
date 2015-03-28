import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class ContactManagerImpl - implements ContactManager Interface.
 * 
 * @author Daryl Smith, MSc IT 
 * @version 2
 */

public class ContactManagerImpl implements ContactManager {

	private int meetingId;
	private int contactId;
	private List<Meeting> myMeetings;
	private Set<Contact> myContacts;
	
	ContactManagerStubImpl() {
		this.contactId = 1;
		this.myContacts = new HashSet<Contact>();
	}

	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PastMeeting getPastMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FutureMeeting getFutureMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Meeting getMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) 
	{		
		//test for null arguments
		if (contacts == null || date == null || text == null) 
		{
			throw new NullPointerException("At least one of the supplied parameters was null");
		}
		
		//test that contacts are known and exist 
		Iterator<Contact> myIterator = contacts.iterator();
		if (contacts.isEmpty()) 
		{
			throw new IllegalArgumentException("The supplied list of contacts is empty.");
		}
		while (myIterator.hasNext()) 
		{
			//Contact tmpContact = myIterator.next();
			if (!this.myContacts.contains(myIterator.next())) 
			{
				throw new IllegalArgumentException("The contact is unknown.");
			}
		}

		PastMeeting myPastMeeting = new PastMeetingImpl(this.meetingId, date, contacts, text);
		this.myMeetings.add(myPastMeeting);
		this.meetingId++;
	}		

	@Override
	public void addMeetingNotes(int id, String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNewContact(String name, String notes) 
	{
		if (name == null || notes == null) 
		{
			throw new NullPointerException();
		}
	
		ContactImpl myContact = new ContactImpl(this.contactId, name, notes);
		this.myContacts.add(myContact);
		this.contactId++;
	}

	@Override
	public Set<Contact> getContacts(int... ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Contact> getContacts(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}	
}

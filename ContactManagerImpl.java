import java.io.BufferedReader;	
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Class ContactManagerImpl - implements ContactManager Interface.
 * 
 * @author Daryl Smith, MSc IT
 * @version 10
 */

public class ContactManagerImpl implements ContactManager {

	private int meetingId;
	private int contactId;
	private List<Meeting> myMeetings;
	private Set<Contact> myContacts;
	
	ContactManagerImpl() {
		this.meetingId = 1;
		this.contactId = 1;
		this.myMeetings = new ArrayList<Meeting>();
		this.myContacts = new HashSet<Contact>();
	}
	
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) 
	{
		
		// if the given date is set in the past:
		if (date.before(Calendar.getInstance())) 
		{
			throw new IllegalArgumentException("Can't add historic date as a future meeting.");
		}
		
		//test that contact are known and exist 
		Iterator<Contact> myIterator = contacts.iterator();
		while (myIterator.hasNext()) 
		{
			//Contact tmpContact = myIterator.next();
			if (!this.myContacts.contains(myIterator.next())) 
			{
				throw new IllegalArgumentException("The contact is unknown.");
			}
		}		
		
		FutureMeeting myFutureMeeting = new FutureMeetingImpl(this.meetingId, date, contacts);
		this.myMeetings.add(myFutureMeeting);
		return this.meetingId++;
	}

	@Override
	public PastMeeting getPastMeeting(int id) 
	{
		for(int i = 0; i < myMeetings.size(); i++) 
		{
			if (myMeetings.get(i).getId() == id)
				if (myMeetings.get(i) instanceof PastMeeting) 
				{
					return (PastMeeting)myMeetings.get(i);
				}
				else if (myMeetings.get(i) instanceof FutureMeeting) 
				{
					throw new IllegalArgumentException("This meeting is actually scheduled for a future date, whan a past date is expected.");
				}
			}

		return null;
	}

	@Override
	public FutureMeeting getFutureMeeting(int id) 
	{
		for(int i = 0; i < myMeetings.size(); i++) 
		{
			if (myMeetings.get(i).getId() == id)
				if (myMeetings.get(i) instanceof FutureMeeting) 
				{
					return (FutureMeeting)myMeetings.get(i);
				}
				else if (myMeetings.get(i) instanceof PastMeeting ||
						 myMeetings.get(i).getDate().before(Calendar.getInstance())) 
				{
					throw new IllegalArgumentException("This date for this meeting is has already passed. A date in tne future is expected.");
				}
			}
		return null;
	}

	@Override
	public Meeting getMeeting(int id) 
	{
		for(int i = 0; i < myMeetings.size(); i++) 
		{
			if (myMeetings.get(i).getId() == id)
				return myMeetings.get(i);
		}
		return null;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) 
	{
		// Check if contact exists:
		if(!this.myContacts.contains(contact)) 
		{
			throw new IllegalArgumentException("A Contact for this meeting is unknown.");
		}
		
		List<Meeting> myMeetingsList = new ArrayList<Meeting>();

		for(int i = 0; i < myMeetings.size(); i++) 
		{
			if (myMeetings.get(i) instanceof FutureMeeting) 
			{
				if (myMeetings.get(i).getContacts().contains(contact)) 
				{
					myMeetingsList.add(myMeetings.get(i));
				}
			}
		}
		
		//use an anonymous class to sort the meetings
		myMeetingsList.sort(new Comparator<Meeting>() 
		{
			@Override
			public int compare(Meeting arg0, Meeting arg1) 
			{
				return arg0.getDate().compareTo(arg1.getDate());
			}
		});
		
		//now dedupe the sorted list (date, time and contacts are all the same)
		for(int i = 1; i < myMeetingsList.size(); i++) 
		{
			if (myMeetingsList.get(i).getDate().equals(myMeetingsList.get(i-1).getDate()) &&
					myMeetingsList.get(i).getContacts().equals(myMeetingsList.get(i-1).getContacts())) 
			{
				myMeetingsList.remove(i);
			}
		}

		return myMeetingsList;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) 
	{
		List<Meeting> myMeetingsList = new ArrayList<Meeting>(); 

		for(int i = 0; i < myMeetings.size(); i++) 
		{
			if (myMeetings.get(i) instanceof FutureMeeting) 
			{
				if (myMeetings.get(i).getDate().get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
					myMeetings.get(i).getDate().get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR)) 				
				{
					myMeetingsList.add(myMeetings.get(i));
				}
			}
		}

		//use an anonymous class to sort the meetings
		myMeetingsList.sort(new Comparator<Meeting>() 
		{
			@Override
			public int compare(Meeting arg0, Meeting arg1) 
			{
				return arg0.getDate().compareTo(arg1.getDate());
			}
		});
		
		//now dedupe the sorted list (date, time and contacts are all the same)
		for(int i = 1; i < myMeetingsList.size(); i++) 
		{
			if (myMeetingsList.get(i).getDate().equals(myMeetingsList.get(i-1).getDate()) &&
					myMeetingsList.get(i).getContacts().equals(myMeetingsList.get(i-1).getContacts())) 
			{
				myMeetingsList.remove(i);
			}
		}

		return myMeetingsList;	
	}

	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) 
	{
		List<PastMeeting> myMeetingsList = new ArrayList<PastMeeting>(); 

		// Check if contact exists:
		if(!this.myContacts.contains(contact)) 
		{
			throw new IllegalArgumentException("A Contact for this meeting is unknown.");
		}

		for(int i = 0; i < myMeetings.size(); i++) 
		{
			if (myMeetings.get(i) instanceof PastMeeting) 
			{
				if (myMeetings.get(i).getContacts().contains(contact)) 
				{
					myMeetingsList.add((PastMeeting)myMeetings.get(i));
				}
			}
		}
		
		//use an anonymous class to sort the meetings
		myMeetingsList.sort(new Comparator<Meeting>() 
		{
			@Override
			public int compare(Meeting arg0, Meeting arg1) 
			{
				return arg0.getDate().compareTo(arg1.getDate());
			}
		});

		//now dedupe the sorted list (date, time and contacts are all the same)
		for(int i = 1; i < myMeetingsList.size(); i++) 
		{
			if (myMeetingsList.get(i).getDate().equals(myMeetingsList.get(i-1).getDate()) &&
					myMeetingsList.get(i).getContacts().equals(myMeetingsList.get(i-1).getContacts())) 
			{
				myMeetingsList.remove(i);
			}
		}

		return myMeetingsList;	
	}

	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {		
		//test for null arguments
		if (contacts == null || date == null || text == null) {
			throw new NullPointerException("At least one of the supplied parameters was null");
		}
		//test that contacts are known and exist 
		Iterator<Contact> myIterator = contacts.iterator();
		if (contacts.isEmpty()) {
			throw new IllegalArgumentException("The supplied list of contacts is empty.");
		}
		while (myIterator.hasNext()) {
			//Contact tmpContact = myIterator.next();
			if (!this.myContacts.contains(myIterator.next())) {
				throw new IllegalArgumentException("The contact is unknown.");
			}
		}
		
		PastMeeting myPastMeeting = new PastMeetingImpl(this.meetingId, date, contacts, text);
		this.myMeetings.add(myPastMeeting);
		this.meetingId++;
	}		
	
	
	@Override
	public void addMeetingNotes(int id, String text) 
	{
		//test for null notes
		if (text == null) 
		{
			throw new NullPointerException("The supplied notes for this meeting is null");
		}

		boolean idFound = false;
		for(int i = 0; i < myMeetings.size(); i++) 
		{
			if (myMeetings.get(i).getId() == id) 
			{
				idFound = true;
				if (myMeetings.get(i) instanceof PastMeeting) 
				{
					((PastMeetingImpl)myMeetings.get(i)).setNotes(text);
				}
				else if (myMeetings.get(i) instanceof FutureMeeting) 
				{
					// check whether the meeting is now in the past
					if (myMeetings.get(i).getDate().after(Calendar.getInstance())) 
					{
						throw new IllegalStateException("The meeting is set for a date in the future .");
					}

					PastMeetingImpl myPastMeeting = new PastMeetingImpl(id, myMeetings.get(i).getDate(), myMeetings.get(i).getContacts(), text);
					myMeetings.set(i, myPastMeeting);				
				}
			}
		}
		if (!idFound) 
		{
			throw new IllegalArgumentException("The meeting supplied does not exist.");
		}	
	}

	@Override
	public void addNewContact(String name, String notes) {
		if (name == null || notes == null) {
			throw new NullPointerException();
		}
	
		ContactImpl myContact = new ContactImpl(this.contactId, name, notes);
		this.myContacts.add(myContact);
		this.contactId++;
	}
	
	@Override
	public Set<Contact> getContacts(int... ids) 
	{
		Set<Contact> contactSet = new HashSet<Contact>();

		Iterator<Contact> myIterator = this.myContacts.iterator();

		for (int i = 0; i < ids.length; i++) 
		{
			while (myIterator.hasNext()) 
			{
				Contact tmpContact = myIterator.next();
				if (tmpContact.getId() == ids[i]) 
				{
					contactSet.add(tmpContact);
				}
			}
			myIterator = this.myContacts.iterator();
		}
		
		if (contactSet.size() != ids.length) 
		{
			throw new IllegalArgumentException("Not found all ids");
		}
		
		return contactSet;
	}

	@Override
	public Set<Contact> getContacts(String name) 
	{
		if (name == null) 
		{
			throw new NullPointerException();
		}
		
		Set<Contact> contactSet = new HashSet<Contact>();

		Iterator<Contact> myIterator = this.myContacts.iterator();
		
		while (myIterator.hasNext()) 
		{
			Contact tmpContact = myIterator.next();
			if (tmpContact.getName().contains(name)) 
			{
				contactSet.add(tmpContact);
			}
		}
		return contactSet;
	}

	/** 
	  * Method to read contact data from a file.
	  * 
	*/ 
	public void reader() 
	{
		FileReader myFileReader;
		try 
		{
			myFileReader = new FileReader("contacts.csv");
			BufferedReader myReader = new BufferedReader(myFileReader);

			String data = myReader.readLine();
			
			while (data != null) 
			{
				//System.out.println(data);
				String[] myArray = data.split(",");
				
				//System.out.println(myArray[1]);
				if (myArray[0].equals("CONTACT")) 
				{
					ContactImpl contact = new ContactImpl(Integer.parseInt(myArray[2]), myArray[1], myArray[3]);
					myContacts.add(contact);
				}
				if (myArray[0].equals("PASTMEETING")) 
				{
					Calendar cal = new GregorianCalendar(Integer.parseInt(myArray[2]), 
							Integer.parseInt(myArray[3]), 
							Integer.parseInt(myArray[4]), 
							Integer.parseInt(myArray[5]), 
							Integer.parseInt(myArray[6]));
					
					int[] myContactArray = new int[myArray.length-8]; 
					for (int i = 8; i< myArray.length; i++) 
					{
						myContactArray[i-8] = Integer.parseInt(myArray[i]); 
					}
					Set<Contact> myMeetingContacts = getContacts(myContactArray);

					PastMeetingImpl pastMeeting = new PastMeetingImpl(Integer.parseInt(myArray[1]), cal, myMeetingContacts, myArray[7]);
					
					myMeetings.add(pastMeeting);
				}
					
				if (myArray[0].equals("FUTUREMEETING")) 
				{
					Calendar cal = new GregorianCalendar(Integer.parseInt(myArray[2]), 
							Integer.parseInt(myArray[3]), 
							Integer.parseInt(myArray[4]), 
							Integer.parseInt(myArray[5]), 
							Integer.parseInt(myArray[6]));
					
					int[] myContactArray = new int[myArray.length-7]; 
					for (int i = 7; i< myArray.length; i++) 
					{
						myContactArray[i-7] = Integer.parseInt(myArray[i]); 
					}
					Set<Contact> myMeetingContacts = getContacts(myContactArray);

					FutureMeetingImpl futureMeeting = new FutureMeetingImpl(Integer.parseInt(myArray[1]), cal, myMeetingContacts);
					
					myMeetings.add(futureMeeting);
				}
					
				data = myReader.readLine();
			}
		} catch (FileNotFoundException e) 
		{
			System.out.println("file not found");
		} catch (IOException e) 
		{
			System.out.println("error reading file");			
		}
	}
	@Override
	public void flush() 
	{
		FileWriter myWriter = null;
		try 
		{
			myWriter = new FileWriter("contacts.csv");
			
			Iterator<Contact> myIterator = this.myContacts.iterator();
			
			while (myIterator.hasNext()) 
			{
				Contact tmpContact = myIterator.next();
				myWriter.append("CONTACT," + tmpContact.getName() + "," + tmpContact.getId() + "," + tmpContact.getNotes() + "\n");
			}

			for(int i = 0; i < myMeetings.size(); i++) 
			{
				if (myMeetings.get(i) instanceof PastMeeting) 
				{
					myWriter.append("PASTMEETING," +  myMeetings.get(i).getId() + "," 
					+ myMeetings.get(i).getDate().get(Calendar.YEAR) + ","
					+ myMeetings.get(i).getDate().get(Calendar.MONTH) + ","
					+ myMeetings.get(i).getDate().get(Calendar.DAY_OF_MONTH) + ","
					+ myMeetings.get(i).getDate().get(Calendar.HOUR) + ","
					+ myMeetings.get(i).getDate().get(Calendar.MINUTE) + ","	
					+ ((PastMeeting)myMeetings.get(i)).getNotes());
				}
				if (myMeetings.get(i) instanceof FutureMeeting) 
				{
					myWriter.append("FUTUREMEETING," +  myMeetings.get(i).getId() + "," 
					+ myMeetings.get(i).getDate().get(Calendar.YEAR) + ","
					+ myMeetings.get(i).getDate().get(Calendar.MONTH) + ","
					+ myMeetings.get(i).getDate().get(Calendar.DAY_OF_MONTH) + ","
					+ myMeetings.get(i).getDate().get(Calendar.HOUR) + ","
					+ myMeetings.get(i).getDate().get(Calendar.MINUTE));
				}
				
				Set<Contact> contactSet = myMeetings.get(i).getContacts();
				Iterator<Contact> myContactIterator = contactSet.iterator();
				
				while (myContactIterator.hasNext()) 
				{
					Contact tmpContact = myContactIterator.next();
					myWriter.append("," + tmpContact.getId());
				}
				myWriter.append("\n");				
			}
			myWriter.flush();
			myWriter.close();		
		} catch (IOException e) 
		{
			System.out.println("error writing to file");
		}
	}
}
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Class JUnitContactManagerTests - This is JUNIT test class for Contact Manager.
 * 
 * @author Daryl Smith, MSc IT 
 * @version 18
 */

public class JUnitContactManagerTests
{
	@Test(expected = NullPointerException.class)
	public void testAddNewContact1() 
	{
		//test illegal argument	(attempt to add a contact with a null name)
		//test 1
		ContactManagerImpl manager = new ContactManagerImpl();
		manager.addNewContact(null, "VIP");
	}

	@Test(expected = NullPointerException.class)
	public void testAddNewContact2() 
	{
		//test null name (attempt to add a contact with null notes)
		//test 2
		ContactManagerImpl manager = new ContactManagerImpl();
		manager.addNewContact("Johnny", null);
	}

	@Test
	public void testGetContactId() 
	{
		//test Contact getId()
		//test 3
		int id = 1;
		String name = "John Smith";
		String notes = "VIP client";
		Contact myContact = new ContactImpl(id, name, notes);
		int outputId = myContact.getId();
		int expectedId = id;
		assertEquals(outputId, expectedId);
	}

	@Test
	public void testGetContactName() 
	{
	//test Contact getName()
	//test 4
		int id = 1;
		String name = "John Smith";
		String notes = "VIP client";
		Contact myContact = new ContactImpl(id, name, notes);
		String outputName = myContact.getName();
		String expectedName = name;
		assertEquals(outputName, expectedName);
	}

	@Test
	public void testGetContactNotes() 
	{
	//test Contact getNotes()
	//test 5
		int id = 1;
		String name = "John Smith";
		String notes = "VIP client";
		Contact myContact = new ContactImpl(id, name, notes);
		String outputNotes = myContact.getNotes();
		String expectedNotes = notes;
		assertEquals(outputNotes, expectedNotes);
	}

	@Test
	public void testGetEmptyNotes() 
	{
	//test Contact getNotes() empty: If we have not written anything about the contact, the empty string is returned.
	//test 6
		int id = 1;
		String name = "John Smith";
		String notes = "";
		Contact myContact = new ContactImpl(id, name, notes);
		String outputNotes = myContact.getNotes();
		String expectedNotes = "";
		assertEquals(outputNotes, expectedNotes);
	}

	@Test
	public void testGetMeetingId() 
	{
	//test Meeting getId()
	//test 7
		int id = 1;
		Calendar cal = new GregorianCalendar(2015,Calendar.JUNE,15,13,0);

		Contact contact = new ContactImpl(1,"Johnny","VIP");
		Contact contact2 = new ContactImpl(2, "Jane", "High net worth");
		Contact contact3 = new ContactImpl(3, "Sally", "Just wasting our time");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		contacts.add(contact2);
		contacts.add(contact3);
		
		Meeting myMeeting = new MeetingImpl(id, cal, contacts);
		int outputId = myMeeting.getId();
		int expectedId = id;
		assertEquals(outputId, expectedId);
	}

	@Test
	public void testGetMeetingDate() 
	{
	//test Meeting getDate()
	//test 8
		int id = 1;
		Calendar cal = new GregorianCalendar(2015,Calendar.JUNE,15,13,0);

		Contact contact = new ContactImpl(1,"Johnny","VIP");
		Contact contact2 = new ContactImpl(2, "Jane", "High net worth");
		Contact contact3 = new ContactImpl(3, "Sally", "Just wasting our time");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		contacts.add(contact2);
		contacts.add(contact3);

		Meeting myMeeting = new MeetingImpl(id, cal, contacts);
		assertEquals(myMeeting.getDate(), cal);
	}

	@Test
	public void testGetMeetingContacts() 
	{
	//test Meeting getContacts()
	//test 9
		int id = 1;
		Calendar cal = new GregorianCalendar(2015,Calendar.JUNE,15,13,0);

		Contact contact = new ContactImpl(1,"Johnny","VIP");
		Contact contact2 = new ContactImpl(2, "Jane", "High net worth");
		Contact contact3 = new ContactImpl(3, "Sally", "Just wasting our time");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		contacts.add(contact2);
		contacts.add(contact3);
		
		Meeting myMeeting = new MeetingImpl(id, cal, contacts);
		assertEquals(myMeeting.getContacts(), contacts);
	}

	//	MeetingImpl
	//	10 The list contains a minimum of one contact (if there were just two people: the user and the contact) and may contain an arbitrary number of them.

	@Test(expected = IllegalArgumentException.class)
	public void testAddNewPastMeetingWithNoContacts() 
	{
		//test illegal argument exception (if the list of contacts is empty)
		//test 11
		Calendar cal = new GregorianCalendar(2015,Calendar.JANUARY,15,13,0);

		Set<Contact> contacts = new HashSet<Contact>();

		ContactManagerImpl manager = new ContactManagerImpl();
		manager.addNewPastMeeting(contacts, cal, "Test meeting");
	}


	@Test(expected = IllegalArgumentException.class)
	public void testAddNewPastMeetingWithNonExistantContact() 
	{
		//test illegal argument exception (if any of the contacts does not exist)
		//test 12
		Calendar cal = new GregorianCalendar(2015,Calendar.JANUARY,15,13,0);

		Contact contact = new ContactImpl(1, null, null);
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);

		ContactManagerImpl manager = new ContactManagerImpl();
		manager.addNewPastMeeting(contacts, cal, "Test meeting");
	}

	@Test(expected = NullPointerException.class)
	public void testAddNewPastMeetingNullArg() 
	{
		//test null pointer exception (if any of the arguments are null)
		//test 13
		Calendar cal = new GregorianCalendar(2015,Calendar.JANUARY,15,13,0);

		Contact contact = new ContactImpl(1,"Johnny","VIP");
		Contact contact2 = new ContactImpl(2, "Jane", "High net worth");
		Contact contact3 = new ContactImpl(3, "Sally", "Just wasting our time");
		Contact contact4 = new ContactImpl(4, null, null);
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		contacts.add(contact2);
		contacts.add(contact3);
		contacts.add(contact4);

		ContactManagerImpl manager = new ContactManagerImpl();
		manager.addNewPastMeeting(null, cal, "Test meeting");
		manager.addNewPastMeeting(contacts, null, "Test meeting");
		manager.addNewPastMeeting(contacts, cal, null);
	}

	@Test
	public void testGetPastMeetingNotes() 
	{
		//test return the notes from the past meeting.
		//test 14
		int id = 1;
		Calendar cal = new GregorianCalendar(2015,Calendar.JANUARY,15,13,0);

		Contact contact = new ContactImpl(1,"Johnny","VIP");
		Contact contact2 = new ContactImpl(2, "Jane", "High net worth");
		Contact contact3 = new ContactImpl(3, "Sally", "Just wasting our time");

		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		contacts.add(contact2);
		contacts.add(contact3);

		String notes = "Exploratory initial meeting";
		
		PastMeetingImpl myMeeting = new PastMeetingImpl(id, cal, contacts, notes);
		assertEquals(myMeeting.getNotes(), notes);
	}

	@Test
	public void testGetPastMeetingNullNotes() 
	{
		//test if there are no notes, then empty string is returned.
		//test 15
		int id = 1;
		Calendar cal = new GregorianCalendar(2015,Calendar.JANUARY,15,13,0);

		Contact contact = new ContactImpl(1,"Johnny","VIP");
		Contact contact2 = new ContactImpl(2, "Jane", "High net worth");
		Contact contact3 = new ContactImpl(3, "Sally", "Just wasting our time");

		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		contacts.add(contact2);
		contacts.add(contact3);

		PastMeetingImpl myMeeting = new PastMeetingImpl(id, cal, contacts, null);
		assertEquals(myMeeting.getNotes(), "");
	}

	@Test
	public void testGetFutureMeetingId() 
	{
		//test return the ID for the future meeting
		//test 16
		final int INITIAL_MEETING_ID = 1;
		ContactManagerImpl manager = new ContactManagerImpl();
		
		manager.addNewContact("Johnny", "VIP");
		manager.addNewContact("Jane", "High net worth");
		manager.addNewContact("Sally", "Just wasting our time");

		Calendar cal = new GregorianCalendar(2015,Calendar.OCTOBER,15,13,0);

		Contact contact = new ContactImpl(1,"Johnny","VIP");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		assertEquals(manager.addFutureMeeting(contacts,cal), INITIAL_MEETING_ID);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetFutureMeetingWithPastDate() 
	{
		//test throws IllegalArgumentException if the meeting is set for a time in the past
		//test 17
		ContactManagerImpl manager = new ContactManagerImpl();
		
		manager.addNewContact("Johnny", "VIP");
		manager.addNewContact("Jane", "High net worth");
		manager.addNewContact("Sally", "Just wasting our time");

		Calendar cal = new GregorianCalendar(2015,Calendar.JANUARY,15,13,0);

		Contact contact = new ContactImpl(1,"Johnny","VIP");
		Contact contact2 = new ContactImpl(2,"Jane","High net worth");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		contacts.add(contact2);
		manager.addFutureMeeting(contacts,cal);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetFutureMeetingWithUnknownContact() 
	{
		//test throws IllegalArgumentException if any contact is unknown
		//test 18
		ContactManagerImpl manager = new ContactManagerImpl();
		
		manager.addNewContact("Johnny", "VIP");
		manager.addNewContact("Jane", "High net worth");
		manager.addNewContact("Sally", "Just wasting our time");

		Calendar cal = new GregorianCalendar(2015,Calendar.OCTOBER,15,13,0);

		Contact contact = new ContactImpl(1,"Johnny123","VIP");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		manager.addFutureMeeting(contacts, cal);
	}
}
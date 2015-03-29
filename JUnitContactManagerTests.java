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
 * @version 41
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

	@Test
	public void testPastMeetingId() 
	{
		//test return the past meeting with the requested ID
		//test 19
		final int INITIAL_MEETING_ID = 1;
		Calendar cal = new GregorianCalendar(2015,Calendar.JANUARY,15,13,0);

		ContactManagerImpl manager = new ContactManagerImpl();
	
		manager.addNewContact("Johnny", "VIP");
		manager.addNewContact("Jane", "High net worth");
		manager.addNewContact("Sally", "Just wasting our time");

		Contact contact = new ContactImpl(1, "Johnny", "VIP");
		Contact contact2 = new ContactImpl(2, "Jane", "High net worth");
		Contact contact3 = new ContactImpl(3, "Sally", "Just wasting our time");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		contacts.add(contact2);
		contacts.add(contact3);

		manager.addNewPastMeeting(contacts, cal, "past meeting test");
		int actualMeetingId = manager.getPastMeeting(INITIAL_MEETING_ID).getId();
		Calendar actualMeetingDate = manager.getPastMeeting(INITIAL_MEETING_ID).getDate();
		Set<Contact> actualMeetingContacts = manager.getPastMeeting(INITIAL_MEETING_ID).getContacts();
		assertEquals(actualMeetingId, INITIAL_MEETING_ID);
		assertEquals(actualMeetingDate, cal);
		assertEquals(actualMeetingContacts, contacts);
	}

	@Test
	public void testNoPastMeetingId() 
	{
		//test return null if it there is no past meeting
		//test 20
		final int NON_EXISTANT_MEETING_ID = 99;
		//Calendar cal = new GregorianCalendar(2015,Calendar.JANUARY,15,13,0);

		ContactManagerImpl manager = new ContactManagerImpl();
	
		//manager.addNewContact("Johnny", "VIP");
		//manager.addNewContact("Jane", "High net worth");
		//manager.addNewContact("Sally", "Just wasting our time");

		//Contact contact = new ContactImpl(1, "Johnny", "VIP");
		//Contact contact2 = new ContactImpl(2, "Jane", "High net worth");
		//Contact contact3 = new ContactImpl(3, "Sally", "Just wasting our time");
		//Set<Contact> contacts = new HashSet<Contact>();
		//contacts.add(contact);
		//contacts.add(contact2);
		//contacts.add(contact3);

		//manager.addNewPastMeeting(contacts, cal, "past meeting test");
		//int actualMeetingId = manager.getPastMeeting(NON_EXISTANT_MEETING_ID).getId();
		//Calendar actualMeetingDate = manager.getPastMeeting(NON_EXISTANT_MEETING_ID).getDate();
		//Set<Contact> actualMeetingContacts = manager.getPastMeeting(NON_EXISTANT_MEETING_ID).getContacts();
		//assertEquals(actualMeetingId, null);
		//assertEquals(actualMeetingDate, null);
		//assertEquals(actualMeetingContacts, null);
		assertEquals(manager.getPastMeeting(NON_EXISTANT_MEETING_ID), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPastMeetingWithFutureDate() 
	{
		//test IllegalArgumentException if there is a meeting with that ID happening in the future 
		//test 21
		final int INITIAL_MEETING_ID = 1;
		Calendar cal = new GregorianCalendar(2015,Calendar.OCTOBER,15,13,0);

		ContactManagerImpl manager = new ContactManagerImpl();
	
		manager.addNewContact("Johnny", "VIP");
		manager.addNewContact("Jane", "High net worth");
		manager.addNewContact("Sally", "Just wasting our time");

		Contact contact = new ContactImpl(1, "Johnny", "VIP");
		Contact contact2 = new ContactImpl(2, "Jane", "High net worth");
		Contact contact3 = new ContactImpl(3, "Sally", "Just wasting our time");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		contacts.add(contact2);
		contacts.add(contact3);

		//FutureMeeting myFutureMeeting = new FutureMeetingImpl(INITIAL_MEETING_ID, cal, contacts);
		int futureMeetingId = manager.addFutureMeeting(contacts, cal);
		manager.getPastMeeting(futureMeetingId);
	}			

	@Test
	public void testFutureMeetingId() 
	{
		//test return the future meeting with the requested ID
		//test 22
		final int INITIAL_MEETING_ID = 1;
		Calendar cal = new GregorianCalendar(2015,Calendar.OCTOBER,15,13,0);

		ContactManagerImpl manager = new ContactManagerImpl();
	
		manager.addNewContact("Johnny", "VIP");
		manager.addNewContact("Jane", "High net worth");
		manager.addNewContact("Sally", "Just wasting our time");

		Contact contact = new ContactImpl(1, "Johnny", "VIP");
		Contact contact2 = new ContactImpl(2, "Jane", "High net worth");
		Contact contact3 = new ContactImpl(3, "Sally", "Just wasting our time");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		contacts.add(contact2);
		contacts.add(contact3);

		manager.addFutureMeeting(contacts, cal);
		int actualMeetingId = manager.getFutureMeeting(INITIAL_MEETING_ID).getId();
		Calendar actualMeetingDate = manager.getFutureMeeting(INITIAL_MEETING_ID).getDate();
		Set<Contact> actualMeetingContacts = manager.getFutureMeeting(INITIAL_MEETING_ID).getContacts();
		assertEquals(actualMeetingId, INITIAL_MEETING_ID);
		assertEquals(actualMeetingDate, cal);
		assertEquals(actualMeetingContacts, contacts);
	}

	@Test
	public void testNoFutureMeetingId() 
	{
		//test return null if it there is no future meeting with the requested ID.
		//test 23
		final int NON_EXISTANT_MEETING_ID = 99;

		ContactManagerImpl manager = new ContactManagerImpl();
	
		assertEquals(manager.getFutureMeeting(NON_EXISTANT_MEETING_ID), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetFutureMeetingWithPastDate2() 
	{
		//test throws IllegalArgumentException if there is a meeting with that ID happening in the past
		//test 24
		//final int INITIAL_MEETING_ID = 1;
		Calendar cal = new GregorianCalendar(2015,Calendar.JANUARY,15,13,0);

		ContactManagerImpl manager = new ContactManagerImpl();
	
		manager.addNewContact("Johnny", "VIP");
		manager.addNewContact("Jane", "High net worth");
		manager.addNewContact("Sally", "Just wasting our time");

		Contact contact = new ContactImpl(1, "Johnny", "VIP");
		Contact contact2 = new ContactImpl(2, "Jane", "High net worth");
		Contact contact3 = new ContactImpl(3, "Sally", "Just wasting our time");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		contacts.add(contact2);
		contacts.add(contact3);

		//FutureMeeting myFutureMeeting = new FutureMeetingImpl(INITIAL_MEETING_ID, cal, contacts);
		int futureMeetingId = manager.addFutureMeeting(contacts, cal);
		manager.getFutureMeeting(futureMeetingId);
		//assertEquals(manager.addFutureMeeting(contacts, cal) ,futureMeetingId);
	}

	@Test
	public void testGetMeeting() 
	{
		//test return the meeting with the requested ID
		//test 25
		final int INITIAL_MEETING_ID = 1;
		Calendar cal = new GregorianCalendar(2015,Calendar.OCTOBER,15,13,0);

		ContactManagerImpl manager = new ContactManagerImpl();
	
		manager.addNewContact("Johnny", "VIP");
		manager.addNewContact("Jane", "High net worth");
		manager.addNewContact("Sally", "Just wasting our time");

		Contact contact = new ContactImpl(1, "Johnny", "VIP");
		Contact contact2 = new ContactImpl(2, "Jane", "High net worth");
		Contact contact3 = new ContactImpl(3, "Sally", "Just wasting our time");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		contacts.add(contact2);
		contacts.add(contact3);

		manager.addFutureMeeting(contacts, cal);
		int actualMeetingId = manager.getMeeting(INITIAL_MEETING_ID).getId();
		Calendar actualMeetingDate = manager.getMeeting(actualMeetingId).getDate();
		Set<Contact> actualMeetingContacts = manager.getMeeting(actualMeetingId).getContacts();
		assertEquals(actualMeetingId, INITIAL_MEETING_ID);
		assertEquals(actualMeetingDate, cal);
		assertEquals(actualMeetingContacts, contacts);
	}

	@Test
	public void testNoMeetingId() 
	{
		//test return null if it there is no meeting with the requested ID
		//test 26
		final int NON_EXISTANT_MEETING_ID = 99;

		ContactManagerImpl manager = new ContactManagerImpl();
	
		assertEquals(manager.getMeeting(NON_EXISTANT_MEETING_ID), null);
	}

	@Test
	public void testGetFutureMeetingListByContact() 
	{
		//return the list of future meeting(s) scheduled with this contact
		//test 27	
		ContactManagerImpl manager = new ContactManagerImpl();
		
		manager.addNewContact("Johnny", "VIP");
		manager.addNewContact("Jane", "High net worth");
		manager.addNewContact("Sally", "Just wasting our time");

		Calendar cal = new GregorianCalendar(2015,Calendar.JUNE,15,13,0);
		Calendar cal2 = new GregorianCalendar(2015,Calendar.JULY,14,14,0);
		Calendar cal3 = new GregorianCalendar(2015,Calendar.JULY,19,13,0);
		Calendar cal4 = new GregorianCalendar(2015,Calendar.OCTOBER,21,13,0);

		Contact contact = new ContactImpl(1,"Johnny","VIP");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		manager.addFutureMeeting(contacts,cal);
		manager.addFutureMeeting(contacts,cal2);
		manager.addFutureMeeting(contacts,cal3);
		manager.addFutureMeeting(contacts,cal4);
		
		List<Meeting> myMeetings = manager.getFutureMeetingList(contact);
		assertEquals(myMeetings.get(0).getDate(),cal);
		assertEquals(myMeetings.get(1).getDate(),cal2);
		assertEquals(myMeetings.get(2).getDate(),cal3);
		assertEquals(myMeetings.get(3).getDate(),cal4);		
	}

	@Test
	public void testGetFutureMeetingListByContactWitNoMeetings() 
	{
		//If there are no meetings, the returned list will be empty.
		//test 28	
		ContactManagerImpl manager = new ContactManagerImpl();
		
		manager.addNewContact("Johnny", "VIP");
		manager.addNewContact("Jane", "High net worth");

		Calendar cal = new GregorianCalendar(2015,Calendar.JUNE,15,13,0);

		Contact contact = new ContactImpl(1,"Johnny","VIP");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		manager.addFutureMeeting(contacts,cal);

		
		//Contact otherContact = new ContactImpl(1,"Johnny", "VIP");
		Contact otherContact = new ContactImpl(2,"Jane","High net worth");
		List<Meeting> myMeetings = manager.getFutureMeetingList(otherContact);
		
		assertTrue(myMeetings.size() == 0);
	}	

	@Test
	public void testGetFutureMeetingListByContactSorted() 
	{
		//test future meetings are created and returned in date order
		//test 29	
		ContactManagerImpl manager = new ContactManagerImpl();
		
		manager.addNewContact("Johnny", "VIP");
		manager.addNewContact("Jane", "High net worth");
		manager.addNewContact("Sally", "Just wasting our time");

		Calendar cal = new GregorianCalendar(2015,Calendar.JUNE,15,13,0);
		Calendar cal2 = new GregorianCalendar(2015,Calendar.JULY,14,14,0);
		Calendar cal3 = new GregorianCalendar(2015,Calendar.JULY,19,13,0);
		Calendar cal4 = new GregorianCalendar(2015,Calendar.OCTOBER,21,13,0);

		Contact contact = new ContactImpl(1,"Johnny","VIP");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		manager.addFutureMeeting(contacts,cal2);
		manager.addFutureMeeting(contacts,cal3);
		manager.addFutureMeeting(contacts,cal);
		manager.addFutureMeeting(contacts,cal4);
		
		List<Meeting> myMeetings = manager.getFutureMeetingList(contact);
		assertEquals(myMeetings.get(0).getDate(),cal);
		assertEquals(myMeetings.get(1).getDate(),cal2);
		assertEquals(myMeetings.get(2).getDate(),cal3);
		assertEquals(myMeetings.get(3).getDate(),cal4);		
	}

	@Test
	public void testGetFutureMeetingListByContactWithoutDupes() 
	{
		//The list will not contain any duplicates.
		//test 30	
		ContactManagerImpl manager = new ContactManagerImpl();
		
		manager.addNewContact("Johnny", "VIP");
		manager.addNewContact("Jane", "High net worth");
		manager.addNewContact("Sally", "Just wasting our time");

		Calendar cal = new GregorianCalendar(2015,Calendar.JUNE,15,13,0);
		Calendar cal2 = new GregorianCalendar(2015,Calendar.JULY,14,14,0);
		Calendar cal3 = new GregorianCalendar(2015,Calendar.JULY,19,13,0);
		Calendar cal4 = new GregorianCalendar(2015,Calendar.OCTOBER,21,13,0);
		Calendar cal5 = new GregorianCalendar(2015,Calendar.JULY,19,13,0);
		Calendar cal6 = new GregorianCalendar(2015,Calendar.OCTOBER,21,13,0);

		Contact contact = new ContactImpl(1,"Johnny","VIP");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		manager.addFutureMeeting(contacts,cal2);
		manager.addFutureMeeting(contacts,cal3);
		manager.addFutureMeeting(contacts,cal);
		manager.addFutureMeeting(contacts,cal4);
		manager.addFutureMeeting(contacts,cal5);
		manager.addFutureMeeting(contacts,cal6);
		
		List<Meeting> myMeetings = manager.getFutureMeetingList(contact);
		assertTrue(myMeetings.size() == 4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetFutureMeetingListContactDoesNotExist() 
	{
		//test illegal argument	(attempt to add a contact that doesn't exist)
		//test 31
		ContactManagerImpl manager = new ContactManagerImpl();
		
		manager.addNewContact("Johnny", "VIP");

		Calendar cal = new GregorianCalendar(2015,Calendar.JUNE,15,13,0);

		Contact contact = new ContactImpl(1,"Johnny","VIP");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		manager.addFutureMeeting(contacts,cal);

		
		Contact otherContact = new ContactImpl(123,"Johnny123","hello");
		List<Meeting> myMeetings = manager.getFutureMeetingList(otherContact);		
	}

	@Test
	public void testGetFutureMeetingListByDate() 
	{
	//returns the list of meetings that are scheduled for the specified date.
	//test 32
		ContactManagerImpl manager = new ContactManagerImpl();
		
		manager.addNewContact("Johnny", "VIP");
		manager.addNewContact("Jane", "High net worth");
		manager.addNewContact("Sally", "Just wasting our time");

		Calendar cal1 = new GregorianCalendar(2015,Calendar.OCTOBER,21,9,0);
		Calendar cal2 = new GregorianCalendar(2015,Calendar.OCTOBER,21,10,0);
		Calendar cal3 = new GregorianCalendar(2015,Calendar.OCTOBER,21,13,0);
		Calendar cal4 = new GregorianCalendar(2015,Calendar.OCTOBER,21,14,0);

		Contact contact1 = new ContactImpl(1,"Johnny","VIP");
		Contact contact2 = new ContactImpl(2,"Jane", "High net worth");
		//Contact contact3 = new ContactImpl(3,"Sally", "Just wasting our time");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact1);
		contacts.add(contact2);
		
		manager.addFutureMeeting(contacts,cal1);
		manager.addFutureMeeting(contacts,cal2);
		manager.addFutureMeeting(contacts,cal3);
		manager.addFutureMeeting(contacts,cal4);
		
		Calendar calTest = new GregorianCalendar(2015,Calendar.OCTOBER,21);
		List<Meeting> myMeetings = manager.getFutureMeetingList(calTest);
		assertEquals(myMeetings.get(0).getDate(),cal1);
		assertEquals(myMeetings.get(1).getDate(),cal2);
		assertEquals(myMeetings.get(2).getDate(),cal3);
		assertEquals(myMeetings.get(3).getDate(),cal4);		
	}

	//returns the list of meetings that took place on the specified date.
	//test 33
	//cannot implement this test as it is not possible to set a past date for a future meeting 

	@Test
	public void testGetFutureMeetingListByDateWithNoMeetings() 
	{
		//if there are no meetings, the returned list will be empty.
		//test 34
		ContactManagerImpl manager = new ContactManagerImpl();
		
		Calendar calTest = new GregorianCalendar(2015,Calendar.OCTOBER,21);
		List<Meeting> myMeetings = manager.getFutureMeetingList(calTest);
		assertTrue(myMeetings.size() == 0);
	}

	@Test
	public void testGetFutureMeetingListByDateSorted() 
	{
		//The list will be chronologically sorted 
		//test 35
		ContactManagerImpl manager = new ContactManagerImpl();
		
		manager.addNewContact("Johnny", "VIP");
		manager.addNewContact("Jane", "High net worth");
		manager.addNewContact("Sally", "Just wasting our time");

		Calendar cal1 = new GregorianCalendar(2015,Calendar.OCTOBER,21,9,0);
		Calendar cal2 = new GregorianCalendar(2015,Calendar.OCTOBER,21,14,0);
		Calendar cal3 = new GregorianCalendar(2015,Calendar.OCTOBER,21,13,0);
		Calendar cal4 = new GregorianCalendar(2015,Calendar.OCTOBER,21,10,0);

		Contact contact1 = new ContactImpl(1,"Johnny","VIP");
		Contact contact2 = new ContactImpl(2,"Jane", "High net worth");
		//Contact contact3 = new ContactImpl(3,"Sally", "Just wasting our time");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact1);
		contacts.add(contact2);
		
		manager.addFutureMeeting(contacts,cal1);
		manager.addFutureMeeting(contacts,cal2);
		manager.addFutureMeeting(contacts,cal3);
		manager.addFutureMeeting(contacts,cal4);
		
		Calendar calTest = new GregorianCalendar(2015,Calendar.OCTOBER,21);
		List<Meeting> myMeetings = manager.getFutureMeetingList(calTest);
		assertEquals(myMeetings.get(0).getDate(),cal1);
		assertEquals(myMeetings.get(1).getDate(),cal4);
		assertEquals(myMeetings.get(2).getDate(),cal3);
		assertEquals(myMeetings.get(3).getDate(),cal2);		
	}

	@Test
	public void testGetFutureMeetingListByDateNoDupes() 
	{
		//The list will not contain any duplicates. 
		//test 36
		ContactManagerImpl manager = new ContactManagerImpl();
		
		manager.addNewContact("Johnny", "VIP");
		manager.addNewContact("Jane", "High net worth");
		manager.addNewContact("Sally", "Just wasting our time");

		Calendar cal1 = new GregorianCalendar(2015,Calendar.OCTOBER,21,9,0);
		Calendar cal2 = new GregorianCalendar(2015,Calendar.OCTOBER,21,14,0);
		Calendar cal3 = new GregorianCalendar(2015,Calendar.OCTOBER,21,13,0);
		Calendar cal4 = new GregorianCalendar(2015,Calendar.OCTOBER,21,10,0);
		Calendar cal5 = new GregorianCalendar(2015,Calendar.OCTOBER,21,9,0);
		Calendar cal6 = new GregorianCalendar(2015,Calendar.OCTOBER,21,14,0);

		Contact contact1 = new ContactImpl(1,"Johnny","VIP");
		Contact contact2 = new ContactImpl(2,"Jane", "High net worth");
		//Contact contact3 = new ContactImpl(3,"Sally", "Just wasting our time");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact1);
		contacts.add(contact2);
		
		manager.addFutureMeeting(contacts,cal1);
		manager.addFutureMeeting(contacts,cal2);
		manager.addFutureMeeting(contacts,cal3);
		manager.addFutureMeeting(contacts,cal4);
		manager.addFutureMeeting(contacts,cal5);
		manager.addFutureMeeting(contacts,cal6);
		
		Calendar calTest = new GregorianCalendar(2015,Calendar.OCTOBER,21);
		List<Meeting> myMeetings = manager.getFutureMeetingList(calTest);
		assertTrue(myMeetings.size() == 4);
		//assertEquals(myMeetings.get(0).getDate(),cal1);
		//assertEquals(myMeetings.get(1).getDate(),cal4);
		//assertEquals(myMeetings.get(2).getDate(),cal3);
		//assertEquals(myMeetings.get(3).getDate(),cal2);		
	}

	@Test
	public void testGetPastMeetingListByContact() 
	{
		//return the list of future (I take it you really mean 'past' here- DGS) meeting(s) scheduled with this contact
		//test 37	
		ContactManagerImpl manager = new ContactManagerImpl();
		
		manager.addNewContact("Johnny", "VIP");
		manager.addNewContact("Jane", "High net worth");
		manager.addNewContact("Sally", "Just wasting our time");

		Calendar cal = new GregorianCalendar(2015,Calendar.JANUARY,14,13,0);
		Calendar cal2 = new GregorianCalendar(2015,Calendar.JANUARY,15,14,0);
		Calendar cal3 = new GregorianCalendar(2015,Calendar.JANUARY,19,13,0);
		Calendar cal4 = new GregorianCalendar(2015,Calendar.JANUARY,21,13,0);

		Contact contact = new ContactImpl(1,"Johnny","VIP");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		manager.addNewPastMeeting(contacts,cal, "Past meeting1");
		manager.addNewPastMeeting(contacts,cal2, "Past meeting2");
		manager.addNewPastMeeting(contacts,cal3, "Past meeting3");
		manager.addNewPastMeeting(contacts,cal4, "Past meeting4");
		
		List<PastMeeting> myMeetings = manager.getPastMeetingList(contact);
		assertEquals(myMeetings.get(0).getDate(),cal);
		assertEquals(myMeetings.get(1).getDate(),cal2);
		assertEquals(myMeetings.get(2).getDate(),cal3);
		assertEquals(myMeetings.get(3).getDate(),cal4);		
	}

	@Test
	public void testGetPastMeetingListWithByContactNoMeetings() 
	{
		//If there are no meetings, the returned list will be empty.
		//test 38	
		ContactManagerImpl manager = new ContactManagerImpl();
		
		manager.addNewContact("Johnny", "VIP");
		manager.addNewContact("Jane", "High net worth");

		Contact otherContact = new ContactImpl(2,"Jane","High net worth");
		List<PastMeeting> myMeetings = manager.getPastMeetingList(otherContact);
		
		assertTrue(myMeetings.size() == 0);
	}	

	@Test
	public void testGetPastMeetingListByContactSorted() 
	{
		//test past meetings are returned in date order
		//test 39	
		ContactManagerImpl manager = new ContactManagerImpl();
		
		manager.addNewContact("Johnny", "VIP");
		manager.addNewContact("Jane", "High net worth");
		manager.addNewContact("Sally", "Just wasting our time");

		Calendar cal = new GregorianCalendar(2015,Calendar.JANUARY,14,13,0);
		Calendar cal2 = new GregorianCalendar(2015,Calendar.JANUARY,15,14,0);
		Calendar cal3 = new GregorianCalendar(2015,Calendar.JANUARY,19,13,0);
		Calendar cal4 = new GregorianCalendar(2015,Calendar.JANUARY,21,13,0);

		Contact contact = new ContactImpl(1,"Johnny","VIP");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		manager.addNewPastMeeting(contacts,cal2, "Past Meeting2");
		manager.addNewPastMeeting(contacts,cal3, "Past Meeting3");
		manager.addNewPastMeeting(contacts,cal,"Past Meeting1");
		manager.addNewPastMeeting(contacts,cal4, "Past Meeting4");
		
		List<PastMeeting> myMeetings = manager.getPastMeetingList(contact);
		assertEquals(myMeetings.get(0).getDate(),cal);
		assertEquals(myMeetings.get(1).getDate(),cal2);
		assertEquals(myMeetings.get(2).getDate(),cal3);
		assertEquals(myMeetings.get(3).getDate(),cal4);		
	}

	@Test
	public void testGetPastMeetingListByContactNoDupes() 
	{
		//The list will not contain any duplicates. 
		//test 40
		ContactManagerImpl manager = new ContactManagerImpl();
		
		manager.addNewContact("Johnny", "VIP");
		manager.addNewContact("Jane", "High net worth");
		manager.addNewContact("Sally", "Just wasting our time");

		Calendar cal1 = new GregorianCalendar(2015,Calendar.JANUARY,21,9,0);
		Calendar cal2 = new GregorianCalendar(2015,Calendar.JANUARY,21,14,0);
		Calendar cal3 = new GregorianCalendar(2015,Calendar.JANUARY,21,13,0);
		Calendar cal4 = new GregorianCalendar(2015,Calendar.JANUARY,21,10,0);
		Calendar cal5 = new GregorianCalendar(2015,Calendar.JANUARY,21,9,0);
		Calendar cal6 = new GregorianCalendar(2015,Calendar.JANUARY,21,14,0);

		Contact contact1 = new ContactImpl(1,"Johnny","VIP");
		Contact contact2 = new ContactImpl(2,"Jane", "High net worth");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact1);
		contacts.add(contact2);
		
		manager.addNewPastMeeting(contacts,cal1, "Past Meeting1");
		manager.addNewPastMeeting(contacts,cal2, "Past Meeting2");
		manager.addNewPastMeeting(contacts,cal3, "Past Meeting3");
		manager.addNewPastMeeting(contacts,cal4, "Past Meeting4");
		manager.addNewPastMeeting(contacts,cal5, "Past Meeting5");
		manager.addNewPastMeeting(contacts,cal6, "Past Meeting6");
		
		List<PastMeeting> myMeetings = manager.getPastMeetingList(contact1);
		assertTrue(myMeetings.size() == 4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPastMeetingListByNonExistantContact() 
	{
		//test illegal argument	(if the contact does not exist)
		//test 41
		ContactManagerImpl manager = new ContactManagerImpl();
		
		manager.addNewContact("Johnny", "VIP");

		Calendar cal = new GregorianCalendar(2015,Calendar.JUNE,15,13,0);

		Contact contact = new ContactImpl(1,"Johnny","VIP");
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		manager.addNewPastMeeting(contacts,cal, "Past Meeting1");
	
		Contact otherContact = new ContactImpl(123,"Johnny123","hello");
		List<PastMeeting> myMeetings = manager.getPastMeetingList(otherContact);
	}
}
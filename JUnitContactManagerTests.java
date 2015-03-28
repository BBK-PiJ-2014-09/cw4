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
 * @version 7
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
}
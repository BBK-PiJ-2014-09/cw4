import org.junit.*;
import static org.junit.Assert.*;

/**
 * Class JUnitContactManagerTests - This is JUNIT test class for Contact Manager.
 * 
 * @author Daryl Smith, MSc IT 
 * @version 5
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
}
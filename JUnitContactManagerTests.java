import org.junit.*;
import static org.junit.Assert.*;

/**
 * Class JUnitContactManagerTests - This is JUNIT test class for Contact Manager.
 * 
 * @author Daryl Smith, MSc IT 
 * @version 2
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
}
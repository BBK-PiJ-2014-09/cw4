/**
 * Class ContactImpl - implements the Contact Interface.
 * 
 * @author Daryl Smith, MSc IT 
 * @version 1
 */

public class ContactImpl implements Contact 
{
	private int Id;
	private String name;
	private String notes;
	
	ContactImpl(int Id, String name, String notes) 
	{
		this.Id = Id;
		this.name = name;
		this.notes = notes;
	}
	
	@Override
	public int getId() 
	{
		return Id;
	}

	@Override
	public String getName() 
	{
		return name;
	}

	@Override
	public String getNotes() 
	{
		return notes;
	}

	@Override
	public void addNotes(String note) 
	{
		this.notes = this.notes + note;
	}
	
	@Override
	public boolean equals(Object other) 
	{
		
		if (this == other) { return true; }
		if (other instanceof Contact) {
			Contact contact = (Contact)other;
			if (this.Id == contact.getId() &&
					this.name.equals(contact.getName()) &&
					this.notes.equals(contact.getNotes())) 
			{
				return true;
			}	
		}
		return false;
	}

	@Override 
	public int hashCode() 
	{ 
		final int prime = 17; 
		int result = 1; 
		result = prime * result + this.Id;
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode()); 
		result = prime * result + ((this.notes == null) ? 0 : this.notes.hashCode()); 
		return result; 
	}
}
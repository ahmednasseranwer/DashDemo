package Persons;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Persons
{
	public List<Person> persons= new ArrayList<Person>();

	public Persons(){}
	
	public List<Person> getPersons() 
	{
		return persons;
	}
	public void setPersons(List<Person> persons) 
	{
		this.persons = persons;
	}	
}


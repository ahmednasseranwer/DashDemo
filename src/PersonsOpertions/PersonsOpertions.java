package PersonsOpertions;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import Persons.Person;
import Persons.Persons;
public abstract class PersonsOpertions {

	public PersonsOpertions(){}

	public void AddPerson(Person newPerson) throws JsonParseException, JsonMappingException, IOException
	{
		// File Empty 
		if(ReadFile()==null)
		{
			Persons listOfPersons = new Persons();		
			listOfPersons.persons.add(newPerson);
			WriteFile(listOfPersons);
			System.out.println(" \n New Person is added with name "+ newPerson.getFirstName()+ " "+newPerson.getLastName()+ " to " + Person.getFileType() +"\n");

		}
		else
		{
			Persons listOfPersons = ReadFile();
			Boolean foundIt = false; 
			for(int i=0;i<listOfPersons.persons.size();i++)
			{
				// Check Duplication, Search Same Email or Phone Number  
				if(listOfPersons.persons.get(i).getMail().equals(newPerson.getMail()) || listOfPersons.persons.get(i).getPhone().equals(newPerson.getPhone()))
				{
						foundIt= true;
				}
			}
			if(!foundIt)	
			{	
				System.out.println(" \n New Person is added with name "+ newPerson.getFirstName()+ " "+newPerson.getLastName()+ " to " + Person.getFileType() +"\n");
				listOfPersons.persons.add(newPerson);
				WriteFile(listOfPersons);
			}
			else
			{
				System.out.println("\n Person Exists, Another Person is founded with Same Email or Phone Number \n");
			}
		}
	}
	
	public void ListPerson() throws JsonParseException, JsonMappingException, IOException
	{
		Persons listOfPersons =  ReadFile();
		// Check File Empty
		if(listOfPersons != null)
		{	
			// Check contains any Person, if File not Empty but not have any persons 
			// Like "JsonFile.json" contains {"persons":[]}
			if(!listOfPersons.persons.isEmpty() )
			{
				for (int i=0;i<listOfPersons.persons.size();i++)
				{
					// Check contains any Person Returns, if File not Empty but have Header only
					// Like "CSVFile.json" contains only one row "firstName,lastName,title,phone,age,mail"
					if(listOfPersons.persons.get(i).getFileType().equals("CSVFile") && listOfPersons.persons.size()==1)
						System.out.println("\n List Of Persons not have any Person \n ");
					
				    // Ignore First Row in "CSVFile.csv" "firstName,lastName,title,phone,age,mail"
					else if(i==0 && listOfPersons.persons.get(i).getFileType().equals("CSVFile"))
						continue;
					
					listOfPersons.persons.get(i).PersonDetails();
				}
			}
			else 
			{
				System.out.println(" \n List of Persons not have any Person\n");
			}
		}	
		else 
		{
				System.out.println("\n File is Empty \n");
		}
	}

	public void DeletePerson(String selectedMail) throws JsonParseException, JsonMappingException, IOException
	{
		Persons listOfPersons = ReadFile();
		// Check File Empty
		if(listOfPersons != null)
		{
			// Check contains any Person, if File not Empty but not have any persons 
			// Like "JsonFile.json" contains {"persons":[]}
			if(!listOfPersons.persons.isEmpty())
			{
				for(int i=0;i<listOfPersons.persons.size();i++)
				{		
					// Check contains any Person Returns, if File not Empty but have Header only
					// Like "CSVFile.json" contains only one row "firstName,lastName,title,phone,age,mail"
					if(listOfPersons.persons.get(i).getFileType().equals("CSVFile") && listOfPersons.persons.size()==1)
						System.out.println("\n Not Found Person with this Mail, List Of Persons not have any Person \n ");

				    // Ignore Check First Row in "CSVFile.csv"  "firstName,lastName,title,phone,age,mail"
					else if(i==0 && listOfPersons.persons.get(i).getFileType().equals("CSVFile"))
						continue;
				
					// Check Selected Mail to Delete
					else if(listOfPersons.persons.get(i).getMail().equals(selectedMail) )
					{	
						System.out.println("\n Person Name : "+ listOfPersons.persons.get(i).getFirstName()+ " "+ listOfPersons.persons.get(i).getLastName() + " with Mail : " +listOfPersons.persons.get(i).getMail() +" is Deleted \n");
						listOfPersons.persons.remove(i);
						break;
					}
					// Not Found Person with this Mail
					else if(i==listOfPersons.persons.size()-1)
					{
						System.out.println("\n Not Found Person with this Mail \n");	
					}
				}
				WriteFile(listOfPersons);
			}
			else 
			{
				System.out.println(" \n Not Found Person with this Mail, List of Persons not have any Person\n");
			}
		}	
		else 
		{
				System.out.println("\n Not Found Person with this Mail , File is Empty \n");
		}
	}

	public  void UpdatePersonInformation(String selectedMail, Person updatedPerson) throws JsonParseException, JsonMappingException, IOException
	{		
		Persons listOfPersons = ReadFile();
		// Check File Empty
		if(listOfPersons!=null)
		{
			// Check contains any Person, if File not Empty but not have any persons 
			// Like "JsonFile.json" contains {"persons":[]}
			if(!listOfPersons.persons.isEmpty())
			{		
			    for(int i=0;i<listOfPersons.persons.size();i++)
				{		
			    	// Check contains any Person Returns, if File not Empty but have Header only
					// Like "CSVFile.json" contains only one row "firstName,lastName,title,phone,age,mail"
					if(listOfPersons.persons.get(i).getFileType().equals("CSVFile") && listOfPersons.persons.size()==1)
						System.out.println("\n Not Found Person with this Mail, List Of Persons not have any Person \n ");

					// Ignore First Row in checking "CSVFile.csv" "firstName,lastName,title,phone,age,mail"
					else if(i==0 && listOfPersons.persons.get(i).getFileType().equals("CSVFile"))
						continue;
	
					else if(listOfPersons.persons.get(i).getMail().equals(selectedMail) )
					{				
						listOfPersons.persons.get(i).setFirstName(updatedPerson.getFirstName());
						listOfPersons.persons.get(i).setLastName(updatedPerson.getLastName());
						listOfPersons.persons.get(i).setPhone(updatedPerson.getPhone());
						listOfPersons.persons.get(i).setMail(updatedPerson.getMail());
						listOfPersons.persons.get(i).setAge(updatedPerson.getAge());
						listOfPersons.persons.get(i).setTitle(updatedPerson.getTitle());
						break;
					}
					// Not Found Person with this Mail
					else if(i == listOfPersons.persons.size()-1)
					{
						System.out.println("\n Not Found Person with this Mail \n");	
					}
				}
					WriteFile(listOfPersons);
			}
			else 
			{
				System.out.println(" \n Not Found Person with this Mail, List of Persons not have any Person\n");
			}
		}	
		else 
		{
				System.out.println("\n Not Found Person with this Mail, File is Empty \n");
		}
	}

	public  void SortOnAnyField(String selectedField) throws JsonParseException, JsonMappingException, IOException
	{
		Persons listOfPersons =	ReadFile();
		// Check File Empty
		if(listOfPersons!= null)
		{
			// Check contains any Person, if File not Empty but not have any persons 
			// Like "JsonFile.json" contains {"persons":[]}
			if(!listOfPersons.persons.isEmpty() )
			{
				//setSortBy thats need for Tocompare in Person.class
				for(int i=0;i<listOfPersons.persons.size();i++)
				{
					listOfPersons.persons.get(i).setSortBy(selectedField);
				}
				//Sort all rows in "JsonFile.json"
				if(Person.getFileType().equals("JsonFile"))
					Collections.sort(listOfPersons.persons);
				//Sort all rows in "CSVFile.csv" expect header row "firstName,lastName,title,phone,age,mail"
				else if(Person.getFileType().equals("CSVFile"))
					Collections.sort(listOfPersons.persons.subList(1, listOfPersons.persons.size()));
					
				WriteFile(listOfPersons);
				ListPerson();
			}
			else 
			{
				System.out.println(" \n List of Persons not have any Person\n");
			}
		}	
		else 
		{
				System.out.println("\n File is Empty \n");
		}	
	}
	
	public abstract Persons FilterByAnyField(String field,String Value) throws ParseException, IOException;
	
	protected abstract Persons ReadFile() throws JsonParseException, JsonMappingException, IOException;
	
	protected abstract void WriteFile(Persons AllPersons) throws JsonGenerationException, JsonMappingException, IOException;
}

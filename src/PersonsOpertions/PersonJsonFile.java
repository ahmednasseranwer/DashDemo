package PersonsOpertions;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.acl.Permission;
import java.util.Collections;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import Persons.Person;
import Persons.Persons;

public class PersonJsonFile extends PersonsOpertions {

	public PersonJsonFile() 
	{
		super();
		Person.setTypeFile("JsonFile");
	}
	
	@Override
    protected Persons ReadFile() throws JsonParseException, JsonMappingException, IOException 
	{
		if(new File("JsonFile.json").length()==0)
		{
			return null;
		}		
		ObjectMapper mapper = new ObjectMapper();
		Persons	listOfPersons = mapper.readValue(new File("JsonFile.json"),Persons.class);
		
		return listOfPersons;		  
	}

	@Override
	protected void WriteFile(Persons allPersons) throws JsonGenerationException, JsonMappingException, IOException
    {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("JsonFile.json"),allPersons);
	}

	@Override
	public Persons FilterByAnyField(String filterField, String filterValue) throws ParseException, IOException 
	{	
		Persons CustomPersons = new Persons();
		JSONParser parser = new JSONParser();
   	//Check File Empty    
	if(new File("JsonFile.json").length()!=0)
		{
			Object object = parser.parse(new FileReader("JsonFile.json"));
			//get "persons"
			JSONObject jsonObject = (JSONObject)object;   
			JSONArray listOfPersons = (JSONArray) jsonObject.get("persons");
		    //Check listofPersons Empty 
			if(listOfPersons.isEmpty())
			{
				System.out.println("\n Not found value of "+filterValue + " in Field "+filterField +",List of Persons not have any Person \n");
			     return null;
			}
			for(int i=0;i<listOfPersons.size();i++)
			{
				//get list of persons
				JSONObject object1 = (JSONObject) listOfPersons.get(i);
				String SelectedValue =	object1.get(filterField).toString();
				if(SelectedValue.equals(filterValue))
				{
					Gson gson=new Gson();
					//from jsonobject to person class
					Person filterPerson = gson.fromJson( listOfPersons.get(i).toString(), Person.class);
					CustomPersons.persons.add(filterPerson);
				}
			}
			if(CustomPersons.persons.isEmpty())
			{
				System.out.println("\n Not found value of "+filterValue + " in Field "+filterField + "\n");
			}
			for(int i=0;i<CustomPersons.persons.size();i++)
			{
				CustomPersons.persons.get(i).PersonDetails();
			}
		}
	else
	  {
		System.out.println("\n Not found value of "+filterValue + " in Field "+filterField+",File is Empty \n");
		return null;
      }
	return CustomPersons;
	}
}

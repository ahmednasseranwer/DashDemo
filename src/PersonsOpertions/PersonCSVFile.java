package PersonsOpertions;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.parser.ParseException;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import Persons.Person;
import Persons.Persons;

public class PersonCSVFile extends PersonsOpertions {

	public PersonCSVFile() {
		super();
		Person.setTypeFile("CSVFile");
	}
	@Override
	protected Persons ReadFile() throws IOException 
	{
		if(new File("CSVFile.csv").length()==0)
			return null;		
		String csvFile = "CSVFile.csv";
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		Persons GetAllPersons = new Persons();
		String line=null;		
		while ((line = br.readLine()) != null) 
		{ 
			 String[] country = line.split(",");
			 GetAllPersons.persons.add(new Person(country[0],country[1],country[2],country[3],country[4],country[5]) );
		}
		br.close();
		return GetAllPersons;	
	}

	@Override
	protected void WriteFile(Persons listOfPersons) throws IOException 
	{	
        CSVWriter csvWriter = new CSVWriter(new FileWriter("CSVFile.csv",false),
        		CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        //check if contain only newPerson not header in "CSVFile.csv" add header before it 
        if(listOfPersons.persons.size()==1 && !listOfPersons.persons.get(0).getFirstName().equals("firstName"))
        {		
        	String[] headerRecord = {"firstName", "lastName", "title", "phone","age","mail"};
        	csvWriter.writeNext(headerRecord);
        }
	   for(int i=0;i<listOfPersons.persons.size();i++)
	   {
		Person P = listOfPersons.persons.get(i);
		csvWriter.writeNext(new String[]{ P.getFirstName(),P.getLastName(),P.getTitle(),P.getPhone(),P.getAge(),P.getMail()});
	   }
	   csvWriter.close();		
	}

	@Override
	public Persons FilterByAnyField(String filterField, String filterValue) throws ParseException, IOException {
		
		BufferedReader	br = new BufferedReader(new FileReader("CSVFile.csv"));  
		Persons CustomPersons = new Persons();
		String line;
		// get first line "header"
		if((line = br.readLine()) != null)
		{
			int IndexField;
			ArrayList<String> headerPerson =new ArrayList<>(Arrays.asList(line.split(",")));
			//get index for filterField in headerPerson
			IndexField= headerPerson.indexOf(filterField);
						
			while ((line = br.readLine()) != null) 
			{  	
				ArrayList<String> personCsv =new ArrayList<>(Arrays.asList(line.split(",")));
				//Check value of IndexField with filterValue if Eqaul add to List
				if(personCsv.get(IndexField).equals(filterValue))
				{
				    CustomPersons.persons.add(new Person(personCsv.get(0),personCsv.get(1),personCsv.get(2),personCsv.get(3),personCsv.get(4),personCsv.get(5)));
				}
			}
			if(CustomPersons.persons.size()==0)
			{
				System.out.println("\n Not found value of "+filterValue+" in Field "+filterField+" \n");
			}

			for(int i=0;i< CustomPersons.persons.size();i++)
			{
				CustomPersons.persons.get(i).PersonDetails();
			}
			return CustomPersons;
		}
		else 
		{
			System.out.println("\n Not found value of "+filterValue+" in Field "+filterField+",File is Empty \n");
			return null;
		}
	}
}

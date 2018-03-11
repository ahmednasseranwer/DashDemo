package Main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Object;
import java.nio.charset.StandardCharsets;
import java.security.acl.LastOwnerException;
import java.util.Scanner;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.parser.ParseException;
import org.omg.CORBA.Environment;
import org.omg.CORBA_2_3.portable.InputStream;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;

import Persons.Person;
import PersonsOpertions.PersonCSVFile;
import PersonsOpertions.PersonJsonFile;
import PersonsOpertions.PersonsOpertions;
import SeedWork.Enums;

public class MainTask {

	
	public static void main(String args[]) throws Exception
	{
		PersonsOpertions persons_opertions=null;
		System.out.println("Select File Type: \n1-Json\n2-CSV");
		Scanner Sc = new Scanner(System.in);
		String FileType=Sc.nextLine();
		if(FileType.equals("1"))
		{
			persons_opertions = new PersonJsonFile();
		}
		else if(FileType.equals("2"))
		{
			persons_opertions = new PersonCSVFile();	
		}
		
		while(true)
		{
			System.out.println("\n Select your Choice \n 1-Add Person \n 2-Update Person's information \n 3-List All Persons \n 4-Delete Specific Person \n 5-Filter by any Field \n 6-Sort on any Field \n");
			String Choice=Sc.nextLine();
			
			if(Choice.equals("1"))
			{
				String Line,FirstName,LastName,Title,Age,Phone,Mail;
				
				System.out.println("Enter Person's First Name: ");
				while((Line=Sc.nextLine()).isEmpty() )
				{
					System.out.println("Enter Person's First Name: ");
				}
				FirstName=Line;
				
				System.out.println("Enter Person's Last Name: ");
				while((Line=Sc.nextLine()).isEmpty() )
				{
					System.out.println("Enter Person's Last Name: ");
				}
				LastName=Line;
				
				System.out.println("Enter Person's Title: ");
				while((Line=Sc.nextLine()).isEmpty() )
				{
					System.out.println("Enter Person's Title: ");
				}
				Title=Line;
				
				System.out.println("Enter Person's Phone: ");
				while((Line=Sc.nextLine()).isEmpty() )
				{
					System.out.println("Enter Person's Phone: ");
				}
				Phone=Line;
				
				System.out.println("Enter Person's Age: ");
				while((Line=Sc.nextLine()).isEmpty() )
				{
					System.out.println("Enter Person's Age: ");
				}
				Age=Line;
				
				System.out.println("Enter Person's Mail: ");
				while((Line=Sc.nextLine()).isEmpty() )
				{
					System.out.println("Enter Person's Mail: ");
				}
				Mail=Line;

				persons_opertions.AddPerson(new Person(FirstName,LastName,Title,Phone,Age,Mail));
				System.out.println();
			}
			else if(Choice.equals("2"))
			{
				String UpdateMail,Line;
				System.out.println("Enter Person Mail to Update");
				while((Line=Sc.nextLine()).isEmpty() )
				{
					System.out.println("Enter Person Mail to Update");
				}
				UpdateMail=Line;

				String FirstName,LastName,Title,Age,Phone,Mail;
				System.out.println("Enter Updated Person's First Name: ");
				while((Line=Sc.nextLine()).isEmpty() )
				{
					System.out.println("Enter Updated Person's First Name: ");
				}
				FirstName=Line;
				
				System.out.println("Enter Updated Person's Last Name: ");
				while((Line=Sc.nextLine()).isEmpty() )
				{
					System.out.println("Enter Updated Person's Last Name: ");
				}
				LastName=Line;
				
				System.out.println("Enter Updated Person's Title: ");
				while((Line=Sc.nextLine()).isEmpty() )
				{
					System.out.println("Enter Updated Person's Title: ");
				}
				Title=Line;
				
				System.out.println("Enter Updated Person's Phone: ");
				while((Line=Sc.nextLine()).isEmpty() )
				{
					System.out.println("Enter Updated Person's Phone: ");
				}
				Phone=Line;
				
				System.out.println("Enter Updated Person's Age: ");
				while((Line=Sc.nextLine()).isEmpty() )
				{
					System.out.println("Enter Updated Person's Age: ");
				}
				Age=Line;
				
				System.out.println("Enter Updated Person's Mail: ");
				while((Line=Sc.nextLine()).isEmpty() )
				{
					System.out.println("Enter Updated Person's Mail: ");
				}
				Mail=Line;
				
				persons_opertions.UpdatePersonInformation(UpdateMail, new Person(FirstName,LastName,Title,Phone,Age,Mail));
			}
			else if(Choice.equals("3"))
			{
				persons_opertions.ListPerson();
			}
			else if(Choice.equals("4"))
			{
				String DeleteMail,Line;
				System.out.println("Enter Person Mail to Delete");
				while((Line=Sc.nextLine()).isEmpty())
				{	
					System.out.println("Enter Person Mail to Delete");
				}
				DeleteMail=Line;
				
				persons_opertions.DeletePerson(DeleteMail);
			}
			else if (Choice.equals("5"))
			{
				String indexField="",Line;
				System.out.println("Enter Field to Filter form 1 to 6 \n 1-firstName \n 2-lastName \n 3-title \n 4-phone \n 5-age \n 6-mail");
				// User Enter :  "asbjdjak","a", "123","10" ,"", "2" 
				// "2" only Accept from 1 to 6 
				while((Line=Sc.nextLine()).isEmpty() || Line.length() > 1 || !((int)Line. charAt(0)>'0' && (int)Line. charAt(0)<='6'))
				{
					System.out.println("Enter Field to Filter form 1 to 6 \n 1-firstName \n 2-lastName \n 3-title \n 4-phone \n 5-age \n 6-mail");
				}
				indexField=Line;				
				String filterFiled =Enums.Type.getValue(Integer.parseInt(indexField));
				
				
				String valueFilter="";
				System.out.println("Enter Value to Filter");
				while((Line=Sc.nextLine()).isEmpty())
				{	
					System.out.println("Enter Value to Filter");
				}
				valueFilter=Line;
				persons_opertions.FilterByAnyField(filterFiled, valueFilter);
			}
			else if(Choice.equals("6"))
			{
				String IndexField="",Line;
				System.out.println("Enter Field to Sort form 1 to 6 \n 1-firstName \n 2-lastName \n 3-title \n 4-phone \n 5-age \n 6-mail");
				// User Enter :  "asbjdjak","a", "123","10" ,"", "2" 
				// "2" only Accept from 1 to 6
				while((Line=Sc.nextLine()).isEmpty() || Line.length() > 1 || !((int)Line.charAt(0)>'0' && (int)Line.charAt(0)<='6'))
				{
					System.out.println("\n Enter Field to Sort form 1 to 6 \n 1-firstName \n 2-lastName \n 3-title \n 4-phone \n 5-age \n 6-mail \n ");
				}
				IndexField=Line;				
				String SortType	=Enums.Type.getValue(Integer.parseInt(IndexField));
				persons_opertions.SortOnAnyField(SortType);
			}
			else {
				System.out.println("\n Please Select From 1 to 6....\n ");
			}
		}
	}
}

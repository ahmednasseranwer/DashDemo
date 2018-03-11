package Persons;
import java.util.Comparator;
import com.opencsv.bean.CsvBindByName;

public class Person implements Comparable<Person>  {
    
	public String firstName;
    public String lastName;
	public String title;
    public String phone;
    public String age;
    public String mail;

	public static String sortBy;
	public static String fileType;

    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public static String getSortBy() {
		return sortBy;
	}

	public static void setSortBy(String sortBy) {
		Person.sortBy = sortBy;
	}

	public static String getFileType() {
		return fileType;
	}

	public static void setTypeFile(String fileType) {
		Person.fileType = fileType;
	}
	
	public Person(){}

	public Person(String firstname,String lastName,String title,String phone,String age,String mail)
	{
		this.firstName=firstname;
		this.lastName=lastName;
		this.title=title;
		this.phone=phone;
		this.age=age;
		this.mail=mail;
	}
	
	public void PersonDetails()
	{
		System.out.println("FirstName is : " + firstName+ "\n" +"LastName is : "+ lastName+ "\n "+"Title is : "+ title+ "\n"+"Phone is : "+phone+ "\n"+"Age is : "+age+ "\n"+"Mail is : "+mail+ "\n");
	}

	@Override
	public int compareTo(Person arg) {
		switch(sortBy)
		{
		case "firstName":
			return firstName.toUpperCase().compareTo(arg.firstName.toUpperCase());
		case "lastName":
			return lastName.toUpperCase().compareTo(arg.lastName.toUpperCase());
		case "mail":
			return mail.toUpperCase().compareTo(arg.mail.toUpperCase());
		case "phone":
			return phone.compareTo(arg.phone);
		case "title":
			return title.toUpperCase().compareTo(arg.title.toUpperCase());
		case "age":
			return age.compareTo(arg.age);
		}
		return 0;
	}
	
};
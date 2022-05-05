public class Student {

	static int noOfStudents=0;
	
	String name;
	int age;
	int year;
	String email;
	
	Student(String name, int age, int year, String email){
		this.name = name;
		this.age = age;
		this.year = year;
		this.email = email;
		Student.noOfStudents++;
	}
}

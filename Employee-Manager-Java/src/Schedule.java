
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Schedule {
	static List<List<Employee>> amList = new ArrayList<>();
	static List<List<Employee>> pmList = new ArrayList<>();
	
	static {
		for(int i = 0; i < 7; i++) {
			amList.add(new ArrayList<Employee>());
			pmList.add(new ArrayList<Employee>());

		}
	}	

	//make sure to pass 0-6 for day
	public static void assignEmployee(int day, Employee emp, boolean isAM) {
		if(isAM) {
			amList.get(day).add(emp);
			saveSchedule("amSchedule.txt", Schedule.amList);
		}
		else {
			pmList.get(day).add(emp);
			saveSchedule("pmSchedule.txt", Schedule.pmList);
		}
//		itrList(amList);
		
	}
	public static void itrList(List<List<Employee>> amList2) {
		List<Employee> e;
		for(int i=0; i < amList2.size(); i++) {
			System.out.println(amList2.get(i).toString());
			
		}

		
	}
	
	public static String amListToString(int index) {
		List<Employee> dayList = amList.get(index);
		String existingData = "";
		for(Employee emp: dayList) {
			existingData += emp.toString() + "<br>";
		}
		return "<html>" + existingData + "</html>";

	}
	public static String pmListToString(int index) {
		List<Employee> nightList = pmList.get(index);
		String existingData = "";
		for(Employee emp: nightList) {
			existingData += emp.toString() + "<br>";
		}
		return "<html>" + existingData + "</html>";

	}

	public static void saveSchedule(String fileName, List<List<Employee>> amOrPmList)
	{
		try 
		{
			File file = new File(fileName);
			file.createNewFile();
			BufferedWriter empWriter = new BufferedWriter(new FileWriter(file));
			
			for(List<Employee> dayOrNightList: amOrPmList) {
				for(Employee emp : dayOrNightList)
				{
					empWriter.write(emp.toString()+"^");
				}
				empWriter.write("\n");
			}

			empWriter.close();
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
	public static void loadInfo()
	{
		try {
			File file1 = new File("amSchedule.txt");
			file1.createNewFile();
			File file2 = new File("pmSchedule.txt");
			file2.createNewFile();
			
			BufferedReader pmReader = new BufferedReader(new FileReader(file2));
			BufferedReader amReader = new BufferedReader(new FileReader(file1));
			
			String line;
			int amCol = -1;
			int pmCol = -1;

			int row =0;
			for(int day = 0; day<7;day++) {
				amCol++;
				pmCol++;
				while(((line = amReader.readLine())!= null)&& !("".equals(line)))
				{
					
					row=0;
					
					//How to split in a file
					String s1[] = line.split("\\^");
					for(String s : s1) {
	//		    		System.out.println(s);
						Employee tempEmp = new Employee();
						if(s!=null) {
							String[] nameSpace = s.split(" ");
							tempEmp.setFirstName(nameSpace[0]);
							tempEmp.setLastName(nameSpace[1]);
							assignEmployee(amCol,tempEmp,row==0);
							
							System.out.println(nameSpace[0]);
							System.out.println(nameSpace[1]+":last");
						}
			    	}amCol++;
				}
				
				while(((line = pmReader.readLine())!= null)&& !("".equals(line)))
				{
					
					row=1;
					
	
					String s1[] = line.split("\\^");
					for(String s : s1) {
	//		    		System.out.println(s);
						Employee tempEmp = new Employee();
						if(s!=null) {
							String[] nameSpace = s.split(" ");
							tempEmp.setFirstName(nameSpace[0]);
							tempEmp.setLastName(nameSpace[1]);
							assignEmployee(pmCol,tempEmp,row==0);
							
							System.out.println(nameSpace[0]);
							System.out.println(nameSpace[1]+":last");
						}
			    	}
					pmCol++;

				}
			}
			
			pmReader.close();
			amReader.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	


}
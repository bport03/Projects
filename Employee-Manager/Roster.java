package sql.src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Roster 
{
	static ArrayList<Employee> employeesList = new ArrayList<Employee>();
	static ArrayList<Manager> managerList = new ArrayList<Manager>();
	
	static void addEmp(Employee newEmp) 
	{
		employeesList.add(newEmp);
		saveEmployees();
	}
	static Employee viewEmployeesName(String userID) {
		for(Employee e : employeesList) {
			if(e.getId()==Integer.parseInt(userID)) {
				return e;
			}
//			+" last: "+" ",e.getLastName(),e.getId(),e.getSocialSecurity(),e.getDateOfBirth(),e.getHourlyWage()
		}
		return null;
	}

	
	
	static void saveEmployees()
	{
		try 
		{
			File file = new File("Employees.txt");
			file.createNewFile();
			BufferedWriter empWriter = new BufferedWriter(new FileWriter(file));
			
			for(Employee emp : employeesList)
			{
				empWriter.write(emp.getFirstName()+"\n");
				empWriter.write(emp.getLastName()+"\n");
				empWriter.write(emp.getId()+"\n");
				empWriter.write(emp.getSocialSecurity()+"\n");
				empWriter.write(emp.getDateOfBirth()+"\n");
				empWriter.write(emp.getHourlyWage()+"\n");
			}

			empWriter.close();
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
		
		private void saveManagers()
		{
			try 
			{
				File file = new File("Manager.txt");
				file.createNewFile();
				BufferedWriter managerWriter = new BufferedWriter(new FileWriter(file));
				
				for(Manager manager : managerList)
				{
					managerWriter.write(manager.getName()+"\n");
					managerWriter.write(manager.getId()+"\n");
					managerWriter.write(manager.getDob()+"\n");
				}
				
				managerWriter.close();
			}
			catch(IOException e) 
			{
				e.printStackTrace();
			}
		
	}
	
		
		public void loadInfo()
		{
			try {
				File file1 = new File("Employees.txt");
				file1.createNewFile();
				File file2 = new File("Managers.txt");
				file2.createNewFile();
				
				BufferedReader managerReader = new BufferedReader(new FileReader(file2));
				BufferedReader empReader = new BufferedReader(new FileReader(file1));
				
				String line;
				while((line = managerReader.readLine())!= null)
				{
					Manager tempManager = new Manager();
					tempManager.setName(line);
					tempManager.setId(Integer.parseInt(managerReader.readLine()));
					tempManager.setDob(managerReader.readLine());
					managerList.add(tempManager);
				}
				
				while((line = empReader.readLine())!= null)
				{
					Employee tempEmp = new Employee();
					tempEmp.setFirstName(line);
					tempEmp.setLastName(empReader.readLine());
					tempEmp.setId(Integer.parseInt(empReader.readLine()));
					tempEmp.setSocialSecurity(Integer.parseInt(empReader.readLine()));
					tempEmp.setDateOfBirth(Integer.parseInt(empReader.readLine()));
					tempEmp.setHourlyWage(Double.parseDouble(empReader.readLine()));
					employeesList.add(tempEmp);
				}
				
				managerReader.close();
				empReader.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	
}


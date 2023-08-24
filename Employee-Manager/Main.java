package sql.src;//package Manager;

public class Main {

    public static void main(String[] args) {
	// write your code here
    	Roster roster = new Roster();
    	Employee exampleEmployee = new Employee("exampleFirst","exampleLast",123,123,123,10);
    	
    	RequestList.loadInfo();
    	MessageList.loadInfo();
    	
//    	roster.addEmp(exampleEmployee);
        roster.loadInfo();
        Schedule.loadInfo();

        Login log = new Login();
        log.startProgram();


    }
}
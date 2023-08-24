package sql.src;

import java.util.*;

public class Manager{
	private int id;
	private String password;
	private String name;
	private String dob;

	Manager(){
		id = -1;
		password = "";
		name = "";
		dob = "";

	}

	Manager(int i, String n, String d){
		id = i;
		name = n;
		dob = d;
	}

	public int getId(){
		return id;
	}

	public void setId(int i){
		id = i;
	}

	public String getName(){
		return name;
	}

	public void setName(String n){
		name = n;
	}

	public String getDob(){
		return dob;
	}

	public void setDob(String d){
		dob = d;
	}

	/* creates an account for a manager */
	public void createAcc(){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your new Id: ");
		setId(input.nextInt());
		String dummy = input.nextLine();

		int flag = -1;

		/* checks if password was entered correctly using infinite loop*/
		while(flag == -1){
			System.out.println("\nEnter your password: ");
			dummy = input.nextLine();
			System.out.println("\nConfirm password: ");
			if(dummy.equals(input.nextLine())){
				flag = 1;
			} else
			 	System.out.println("\nConfirmation failed, try again");
		}
		System.out.println("\nEnter your name: ");
		setName(input.nextLine());

		System.out.println("\nEnter your dob: ");
		setDob(input.nextLine());
	}

	/* sets parameters to default value and prints message*/
	public void deleteAcc(){
		setId(-1);
		setName("");
		setDob("");

		System.out.println("\nYour account has been deleted");
	}

	/* simple login */
	public void login(){
		int flag = -1;
		int attempts = 0;
		int intInput;
		String stringInput;
		String dummy;
		Scanner input = new Scanner(System.in);

		/* loops until password is correct or until too many attempts*/
		while(flag == -1 && attempts < 3){
			System.out.println("\nEnter your id number: ");
			intInput = input.nextInt();
			dummy = input.nextLine();
			System.out.println("\nEnter your password: ");
			stringInput = input.nextLine();

			/* checks if id or password is correct */
			if(intInput == id && stringInput.equals(password)){
				flag = 1;
				System.out.println("\nYou have succesfully logged in");
			} else
				System.out.print("\nIncorrect Id or password");

			/* ckecks if there have been too many attempts */
			if (attempts < 2 && flag == -1){
				System.out.println(", try again");
			} else if (flag == -1)
				System.out.println("\nToo many attempts try logging in again ");

			attempts++;
		}


	}

	/* prints the id, name, and date of birth of the manager */
	public void display(){
		/* checks if id is set or not*/
		if(id == -1){
			Scanner input = new Scanner(System.in);
			System.out.println("You do not have an account. Would you like to create one? ");
			String answer = input.nextLine();

			/* if yes call createAcc(), if no do nothing*/
			switch(answer){
				case "y":
				case "Y":
				case "yes":
				case "Yes":
					createAcc();
					break;
				case "n":
				case "N":
				case "no":
				case "No":
					System.out.println("\nGoodBye\n");
					break;
				default:
					System.out.println("Error invalid operation");
			}
		} else {
			System.out.println("\n\tId: " + id);
			System.out.println("\n\tName: " + name);
			System.out.println("\n\tDob: " + dob);
		}
	}

	
}
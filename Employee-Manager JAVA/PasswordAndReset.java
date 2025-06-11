package sql.src;//package Manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class PasswordAndReset {
    HashMap<String,String> info=new HashMap<String,String>();
    HashMap<String,String> manager=new HashMap<String,String>();


    PasswordAndReset(){
        info.put("brandon","user");
        info.put("christian","user");
        info.put("andres","user");
        info.put("zar","user");
        info.put("bryan","user");
        manager.put("manager","user");
    }

    protected HashMap<String, String> getinfo(){
        return info;
    }
    protected HashMap<String,String> getManager(){return manager;}


    public int  filePass(String password, String username)
    {
        try {
            File file1 = new File("Employees.txt");
            file1.createNewFile();
            File file2 = new File("Managers.txt");
            file2.createNewFile();

            BufferedReader managerReader = new BufferedReader(new FileReader(file2));
            BufferedReader empReader = new BufferedReader(new FileReader(file1));

            Scanner myReader = new Scanner(file1);
            Scanner myReader2 = new Scanner(file2);
            int count=0;
            int manager=2;
            while (myReader.hasNextLine()) {
                String user= myReader.nextLine();
               if(Objects.equals(user, username)){
                   count++;
               }
               if(Objects.equals(user, password)){
                   count++;
               }
               if(count==2){
                   return 2;
               }
            }


            while (myReader2.hasNextLine()) {
                String user= myReader2.nextLine();
                if(Objects.equals(user, username)){
                    manager++;
                }
                if(Objects.equals(user, password)){
                    manager++;
                }
                if(manager==4){
                    return 4;

                }
            }


            managerReader.close();
            empReader.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return -1;
    }


}

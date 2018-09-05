package containers;

import java.util.HashMap;
import java.util.List;


public class Model {
    private String name;
    private String surname;
    private String accountType;
    private String password;
    private HashMap<String, Assignment> assignments;

        Model(String name, String surname, String accountType, String password, HashMap<String, Assignment> assignments){
            this.name = name;
            this.surname = surname;
            this.accountType = accountType;
            this.password = password;
            this.assignments = assignments;
        }


        public void setName(String name){
            this.name = name;
        }


        public void setSurname(String surname){
            this.surname = surname;
        }


        public void setAccountType(String accountType){
            this.accountType = accountType;
        }


        public void addAssignment(String id, Assignment assignment){
            assignments.put(id, assignment);
        }


        public String getName(){
            return this.name;
        }


        public String getSurname(){
            return this.surname;
        }


        public String getAccountType(){
            return this.accountType;
        }


        public String getPassword(){
            return this.password;
        }


        public Assignment getAssignment(String id){
            return assignments.get(id);
        }




}

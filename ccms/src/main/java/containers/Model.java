package containers;

import java.util.HashMap;
import java.util.Map;


public class Model {
    private String name;
    private String surname;
    private String accountType;
    private String password;
    private String login;
    private Map<String, Assignment> assignments;

    public Model(String name, String surname, String accountType, String password, String login, Map<String, Assignment> assignments){
        this.name = name;
        this.surname = surname;
        this.accountType = accountType;
        this.password = password;
        this.login = login;
        this.assignments = assignments;
    }


    public Model(String name, String surname, String accountType, String password, String login){
        this.name = name;
        this.surname = surname;
        this.accountType = accountType;
        this.password = password;
        this.login = login;
    }


        public Model(String name, String surname, String login) {
            this.name = name;
            this.surname = surname;
            this.login = login;
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


        public void setLogin(String login) { this.login = login; }


        public void addAssignment(String id, Assignment assignment){
            assignments.put(id, assignment);
        }


        public String getName(){ return this.name; }


        public String getSurname(){
            return this.surname;
        }


        public String getAccountType(){
            return this.accountType;
        }


        public String getPassword(){ return this.password; }


        public String getLogin() { return this.login; }


        public Assignment getAssignment(String id){
            return assignments.get(id);
        }


        public Map<String, Assignment> getAssignments() { return assignments; }




}

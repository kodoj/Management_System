package DAO;

import containers.Model;

public interface DAOSingleObject {
    public Model get(String firstName, String lastName, String accountType);
    public void add(Model model);
    public void delete(Model model);
    public Model createModel();
}

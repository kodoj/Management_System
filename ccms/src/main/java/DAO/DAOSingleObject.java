package DAO;

import containers.Model;

public interface DAOSingleObject {

    public Model get(String login);
    public void add(Model model);
    public void delete(String login);
}
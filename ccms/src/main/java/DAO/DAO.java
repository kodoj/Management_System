package DAO;

import containers.Model;

public interface DAO {
    public void getData();
    public void setData();
    public void deleteData();
    public Model createModel();
}

package dao;

import containers.Assignment;
import containers.Model;

import java.util.List;

public interface DAOMultipleObjects {
    public List<Model> getAllStudents();
    public List<Model> getAllEmployers();
    public List<Assignment> getAllAssignments();
}
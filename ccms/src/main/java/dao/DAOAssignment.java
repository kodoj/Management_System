package dao;

import connectors.Connector;

public class DAOAssignment {

    Connector connector;

    public DAOAssignment() {
        connector = new Connector();
    }

    public void addAssignment(String assignmentID) {
        connector.addAssignmentToXML(assignmentID);
    }
}

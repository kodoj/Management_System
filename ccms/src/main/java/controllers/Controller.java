package controllers;


import containers.Model;

public abstract class Controller {

    private Model myModel;
    private boolean loggedIn;


    public Model getMyModel() {
        return myModel;
    }

    public void setMyModel(Model myModel) {
        this.myModel = myModel;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    public void setloggedIn(boolean loggedIn){
        this.loggedIn = loggedIn;
    }

}

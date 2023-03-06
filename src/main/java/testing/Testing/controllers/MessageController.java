package testing.Testing.controllers;

public class MessageController {

    public String getMessage(String name){
        return String.format("Tested by %s",name);
    }
}

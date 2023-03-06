package testing.Testing.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageControllerTest {

    //Unit Test
    @Test
    void getMessage() {
        MessageController messageController = new MessageController();
        String response = messageController.getMessage("Kenan");
        assertEquals("Tested by Kenan",response);
    }
}
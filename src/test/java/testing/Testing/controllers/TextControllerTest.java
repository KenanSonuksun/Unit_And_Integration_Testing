package testing.Testing.controllers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.http.MediaType;
import testing.Testing.models.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TextController.class)
class TextControllerTest {

    @Autowired
    private MockMvc mvc;

    //Integration Test without any Param
    @Test
    void getText() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getText");
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
        assertEquals("Tested by Kenan",mvcResult.getResponse().getContentAsString());
    }

    //Integration Test with a Param
    @Test
    public void getTextWithParam() throws Exception{
        mvc.perform(get("/getText?name=Jack")).andExpect(content().string("Tested by Jack"));
    }

    //Integration Test with Params
    @Test
    public void postTextWithRequestBody() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person();
        person1.setName("Kenan");
        person1.setSurname("Son");
        person1.setAge(22);
        person1.setAlive(true);
        personList.add(person1);
        Person person2 = new Person();
        person2.setName("John");
        person2.setSurname("Wick");
        person2.setAge(45);
        person2.setAlive(true);
        personList.add(person2);

        String requestBody = objectMapper.writeValueAsString(personList);
        MvcResult result = mvc.perform(post("/postPerson")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).isEqualTo(requestBody);
    }


}
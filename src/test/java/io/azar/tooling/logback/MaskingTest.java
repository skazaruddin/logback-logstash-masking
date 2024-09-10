package io.azar.tooling.logback;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@WebMvcTest // Specify the controller to be tested
public class MaskingTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testSaveEmployee() throws Exception {
        SaveEmployeeRequestDto requestDto = new SaveEmployeeRequestDto();
        requestDto.setEmployeeId("123456");
        requestDto.setFirstName("Sam");
        requestDto.setLastName("Zuchini");
        requestDto.setDepartment("ACCOUNTS");
        requestDto.setPosition("ACCOUNTING MANAGER");
        requestDto.setSalary(40000d);
        requestDto.setEmail("sam.zuchini@demo.com");
        requestDto.setSsn("856-45-6789");
        requestDto.setPassword("MyPassword");

        // Set Address values
        Address address = new Address();
        address.setStreet("123 Main St");
        address.setCity("Metropolis");
        address.setState("NY");
        address.setZipCode("10101");
        address.setCountry("USA");
        requestDto.setAddress(address);

        String jsonRequest = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        // You can add more assertions here, like checking the response body
        ;
    }
}

package com.example.backend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class BackendApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void allResourceControllersAreRegistered() throws Exception {
        for (String path : new String[]{
                "/v1/employees",
                "/v1/managers",
                "/v1/workday-types",
                "/v1/planned-overtimes",
                "/v1/actual-overtimes",
                "/v1/use-overtimes"
        }) {
            mockMvc.perform(get(path)).andExpect(status().isOk());
        }
    }

    @Test
    void employeeCrudUsesDtoAndRepositoryLayers() throws Exception {
        long id = Long.MAX_VALUE;
        String employee = """
                {"employeeId":%d,"employeeName":"Test Employee","department":"Engineering",
                 "position":"Developer","workDays":"Monday-Friday","workHours":"09:00-17:00"}
                """.formatted(id);

        mockMvc.perform(post("/v1/employees")
                        .contentType("application/json")
                        .content(employee))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.employeeName").value("Test Employee"));

        mockMvc.perform(get("/v1/employees/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId").value(id));

        String updatedEmployee = employee.replace("Test Employee", "Updated Employee");
        mockMvc.perform(put("/v1/employees/{id}", id)
                        .contentType("application/json")
                        .content(updatedEmployee))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeName").value("Updated Employee"));

        mockMvc.perform(delete("/v1/employees/{id}", id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/v1/employees/{id}", id))
                .andExpect(status().isNotFound());
    }
}

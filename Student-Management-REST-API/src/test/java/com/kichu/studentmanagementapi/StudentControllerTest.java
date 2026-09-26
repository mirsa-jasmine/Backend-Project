package com.kichu.studentmanagementapi;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetAllStudents() throws Exception {

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Alice"));

    }
    @Test
    void shouldGetStudentById() throws Exception {
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    @DirtiesContext
    void shouldCreateStudent() throws Exception {
        mockMvc.perform(
                        post("/students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"Bob\",\"department\":\"CSE\",\"age\":22}")

                )
                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.name").value("Bob"));
    }
    @Test
    @DirtiesContext
    void shouldUpdateStudent() throws Exception {
        mockMvc.perform(put("/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
            {
                "name": "Alice Updated",
                "department": "CSE",
                "age": 23
            }
        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Alice Updated"));
    }
    @Test
    @DirtiesContext
    void shouldDeleteStudent() throws Exception {
        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isNoContent());
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isNotFound());
    }
    @Test
    @DirtiesContext
    void shouldGetNonStudentById() throws Exception {
        mockMvc.perform(get("/students/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value("Student with id 999 not found"));

    }
    @Test
    @DirtiesContext
    void shouldRejectBlankName() throws Exception {
        mockMvc.perform(
                        post("/students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                            {
                                "name": "",
                                "department": "CSE",
                                "age": 20
                            }
                            """)
                )
                .andExpect(status().isBadRequest());
    }
    @Test
    @DirtiesContext
    void shouldRejectBlankDepartment() throws Exception {
        mockMvc.perform(
                        post("/students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                            {
                                "name": "Alice",
                                "department": "",
                                "age": 20
                            }
                            """)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DirtiesContext
    void shouldRejectUnderageStudent() throws Exception {
        mockMvc.perform(
                        post("/students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                            {
                                "name": "Alice",
                                "department": "CSE",
                                "age": 16
                            }
                            """)
                )
                .andExpect(status().isBadRequest());
    }

}

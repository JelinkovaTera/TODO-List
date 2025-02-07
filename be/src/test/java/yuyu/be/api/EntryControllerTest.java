package yuyu.be.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import yuyu.be.models.TodoEntryGetModel;
import yuyu.be.service.EntryService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EntryController.class)
public class EntryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EntryService entryService;

    @Test
    public void testGetEntryList() throws Exception {
        TodoEntryGetModel todo1 = new TodoEntryGetModel(1, "Test TODO 1", "in progress");
        TodoEntryGetModel todo2 = new TodoEntryGetModel(2, "Test TODO 2", "done");
        List<TodoEntryGetModel> mockTodos = Arrays.asList(todo1, todo2);

        when(entryService.getTODOEntryList()).thenReturn(mockTodos);

        mockMvc.perform(get("/api/1.0/entries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].description").value("Test TODO 1"))
                .andExpect(jsonPath("$[1].description").value("Test TODO 2"));

        verify(entryService, times(1)).getTODOEntryList();
    }

    @Test
    public void testPutEntryBadRequest() throws Exception {
        mockMvc.perform(put("/api/1.0/entries/incorrectId"))
                .andExpect(status().isBadRequest());
    }
}

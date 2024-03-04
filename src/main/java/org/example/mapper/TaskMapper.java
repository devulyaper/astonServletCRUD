package org.example.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.dto.TaskDTO;
import org.example.entity.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // Register JavaTimeModule to support Java 8 date/time types
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public static Task readJsonRequest(HttpServletRequest req) throws IOException {
        TaskDTO taskDTO = objectMapper.readValue(req.getReader(), TaskDTO.class);
        return new Task(
                taskDTO.getTitle(),
                taskDTO.getDescription(),
                taskDTO.getDeadlineDate(),
                taskDTO.isDone(),
                taskDTO.getEmployeeId()
        );
    }

    public static void writeJsonResponse(HttpServletResponse resp, List<Task> tasks) throws IOException {

        List<TaskDTO> tasksDTO = new ArrayList<>();
        for (Task task : tasks) {
            tasksDTO.add(new TaskDTO(
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getDeadlineDate(),
                    task.isDone(),
                    task.getEmployeeId()
            ));
        }
        objectMapper.writeValue(resp.getWriter(), tasksDTO);
    }

    public static void writeJsonResponse(HttpServletResponse resp, Task task) throws IOException {
        TaskDTO taskDTO = new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDeadlineDate(),
                task.isDone(),
                task.getEmployeeId()
        );
        objectMapper.writeValue(resp.getWriter(), taskDTO);
    }
}

package org.example.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.PCDTO;
import org.example.dto.TaskDTO;
import org.example.entity.PC;
import org.example.entity.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PCMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void writeJsonResponse(HttpServletResponse resp, List<PC> pcList) throws IOException {
        List<PCDTO> pcsDTO = new ArrayList<>();
        for (PC pc : pcList) {
            pcsDTO.add(new PCDTO(
                    pc.getId(),
                    pc.getModel(),
                    pc.getEmployeeId()
            ));
        }
        objectMapper.writeValue(resp.getWriter(), pcsDTO);
    }

    public static void writeJsonResponse(HttpServletResponse resp, PC pc) throws IOException {
        PCDTO pcdto = new PCDTO(
                pc.getId(),
                pc.getModel(),
                pc.getEmployeeId()
        );

        objectMapper.writeValue(resp.getWriter(), pcdto);
    }

    public static PC readJsonRequest(HttpServletRequest req) throws IOException {
        PCDTO pcdto = objectMapper.readValue(req.getReader(), PCDTO.class);
        return new PC(
                pcdto.getModel(),
                pcdto.getEmployeeId()
        );
    }
}

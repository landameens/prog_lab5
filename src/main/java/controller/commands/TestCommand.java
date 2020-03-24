package controller.commands;

import app.query.Query;
import controller.response.Response;
import controller.response.ResponseDTO;
import controller.response.Status;

import java.util.Map;

public class TestCommand extends Command {
    public TestCommand(String type,
                       Map<String, String> args) {
        super(type, args);
    }

    @Override
    public Response execute(Query query) {
        responseDTO = new ResponseDTO();
        responseDTO.answer = "test";
        responseDTO.status = Status.SUCCESSFULLY.getCode();

        return Response.getResponse(responseDTO);
    }
}

package controller.responce;

public class Response {

    private Status status;
    private String answer;

    public Response(Status status, String answer) {
        this.status = status;
        this.answer = answer;
    }

    public Status getStatus() {
        return status;
    }

    public String getAnswer() {
        return answer;
    }

    public static Response getResponse(ResponseDTO responseDTO){
        Status returnedStatus = Status.getStatusEnum(String.valueOf(responseDTO.status));

        return new Response(returnedStatus,
                responseDTO.answer);
    }

    public static ResponseDTO getResponseDTO(Response response){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.answer = response.getAnswer();
        responseDTO.status = response.getStatus().getCode();

        return responseDTO;
    }
}

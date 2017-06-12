package DTO.Response;

/**
 * Created by Jaho on 2017/5/17.
 * JSON返回结果泛型
 */
public class Response<Obj> {

    private Error error;
    private Obj data;


    public Response(){
        super();
    }

    public Response(Error error) {
        this.error = error;
        this.data = null;
    }

    public Response(Error error, Obj data) {
        this.error = error;
        this.data = data;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Obj getData() {
        return data;
    }

    public void setData(Obj data) {
        this.data = data;
    }
}

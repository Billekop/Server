package endpoints;

import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * Created by jesperbruun on 17/10/2016.
 */
public interface IEndpoints {
  public Response get() throws Exception;
  public Response get(int id) throws Exception;
  public Response create(String data) throws Exception;
  public Response edit(int id, String data) throws Exception;
  public Response delete(int id) throws Exception;
}

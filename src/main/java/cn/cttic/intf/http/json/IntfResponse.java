package cn.cttic.intf.http.json;
import cn.cttic.sysframe.remote.JsonServiceResponse;

public class IntfResponse extends JsonServiceResponse  {
	@Override
    public String serviceCodeNodeName() {
	    return "operCode";
    }

	@Override
    public String statusCodeNodeName() {
	    return "statusCode";
    }

	@Override
    public String statusDescNodeName() {
	    return "statusDesc";
    }

	@Override
	public  String resultNodeName() {
	    return "resResponseBody";
    }

}

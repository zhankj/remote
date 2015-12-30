package cn.cttic.intf.http.json;



import cn.cttic.sysframe.remote.JsonServiceRequest;

public class IntfRequest  extends JsonServiceRequest  {
	
	public IntfRequest(String message)
	{
		super(message);	
	}
	
	@Override
    public String serviceCodeNodeName() {
	    return "operCode";
    }

	@Override
    public String paramsNodeName() {
	    return "requestBody";
    }
   
}

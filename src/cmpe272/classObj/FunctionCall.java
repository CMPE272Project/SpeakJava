package cmpe272.classObj;

public class FunctionCall extends Body{

	public FunctionCall(String description) {
		super(description);
	}

	public String getDescription()
	{
		String output="";
		if(tempBody!=null)
			output=tempBody.getDescription();
		
		output+=description+"();";
		return(output);
	}
}

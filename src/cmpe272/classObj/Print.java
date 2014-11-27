package cmpe272.classObj;

public class Print extends BodyDecorator{

	public Print(BodyDecorator tempBody, String description) {
		super(tempBody, description);
	}
	
	public Print(String description)
	{
		super(description);
	}
	
	public String getDescription()
	{
		String output="";
		if(tempBody!=null)
			output=tempBody.getDescription();
		
		output+="System.out.println(\"" + description + "\");";
		return(output);
	}
	
}

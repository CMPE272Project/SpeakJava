package cmpe272.classObj;

public class Print extends Body{

	public Print(String description)
	{
		super(description);
	}
	
	public String getDescription()
	{
		String output="";
		if(tempBody!=null)
			output=tempBody.getDescription();
		
		output+="System.out.println(" + description + ");";
		return(output);
	}
	
}

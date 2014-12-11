package cmpe272.classObj;

public class ClassVariables extends CompositeBody{
	
	public ClassVariables(String description)
	{
		super(description);
	}
	
	public String getDescription() {
		String output="";
		
		output=description+ "\n";
		for(ClassComponents component: components)
			output+=component.getDescription();
		output+="\n";
		return output;
	}
}

package cmpe272.classObj;

//This is a composite node for if block
public class If extends CompositeBody{
	
	public If(String description)
	{
		super(description);
	}
	
	public String getDescription() {
		String output="";
		
		output=description+ "\n{\n";
		for(ClassComponents component: components)
			output+=component.getDescription();
		output+="\n}\n";
		return output;
	}
}

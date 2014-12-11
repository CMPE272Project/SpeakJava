package cmpe272.classObj;

public class WhileLoop extends CompositeBody {
	
	public WhileLoop(String description)
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

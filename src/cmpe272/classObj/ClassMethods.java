package cmpe272.classObj;

public class ClassMethods extends CompositeBody{
	
	public ClassMethods(String description)
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

package cmpe272.classObj;

public class ClassVariable extends CompositeBody{
	
	private String name;
	private String access_specifier;
	private String variable_type="void";
	
	

	
	
	public ClassVariable(String description)
	{
		super(description);
	}
	
	
	public ClassVariable(String name, String access_specifier, String variable_type)
	{
		this.name=name;
		this.access_specifier=access_specifier;
		if(variable_type!=null && variable_type!="")
		this.variable_type=variable_type;
		
		description=access_specifier + " " + variable_type + " " + name + "()";
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

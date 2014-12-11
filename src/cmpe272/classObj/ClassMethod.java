package cmpe272.classObj;

import java.util.Arrays;
import java.util.List;

public class ClassMethod extends CompositeBody{
	
	private String name;
	private String access_specifier;
	private String return_type="void";
	private List<String> parameters= Arrays.asList() ;
	private List<String> param_type = Arrays.asList() ;
	
	public ClassMethod(String description)
	{
		super(description);
	}
	
	
	public ClassMethod(String name, String access_specifier, String return_type, List<String> parameters, List<String> param_type)
	{	
		String param="";
		this.name=name;
		this.access_specifier=access_specifier;
		if(return_type!=null && return_type!="")
		this.return_type=return_type;
		
		this.parameters=parameters;
		this.param_type=param_type;
		
		for(String str: parameters)
		{
			param+=param_type.get(parameters.indexOf(str))+" " + str + ", "; 
		}
		
		if(param.length()!=0) {
		param.substring(0, param.length()-1); }
		description=access_specifier + " " + return_type + " " + name + "("+param+")";
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

package cmpe272.app;
import java.util.Arrays;
import java.util.List;

import cmpe272.classObj.*;

public class ClassCreator {
	
	private MyClass myclass;
	
	private ClassMethods class_methods;
	private ClassVariables class_variables;
	private Speech speech;
	
	private List<String> acc_spec= Arrays.asList("public","protected","private");
	private List<String> data_types= Arrays.asList("integer","string","double");
	
	public ClassCreator()
	{
		speech=new Speech();
	}
	
	public void getClassDetails()
	{
		String str;
		
		System.out.println("Enter the class name:");
		str=speech.getUserInput(2000);
		
		myclass = new MyClass("Public class " + str);
	}
	
	public void getClassVariables()
	{
		
	}
	
	public void getClassMethods()
	{	
		String temp="";
		
		class_methods= new ClassMethods("// Class Methods");
		while(!temp.equals("semicolon"))
		{
			System.out.println("Say NEXT to add a new method or SEMICOLON to exit:");
			temp=speech.getUserInput(2000);
			
			if(temp.equals("next"))
				getClassMethod();
			else if(temp.equals("semicolon"))
				break;
			else
				System.out.println("Invalid input!");
		}
		
		System.out.println("Say MAIN METHOD to add main method or SEMICOLON to exit:");
		temp=speech.getUserInput(2000);
		
		if(temp.equals("main method"))
			getMainMethod();
		
		myclass.addChild(class_methods);
	
	}
	
	public void getClassMethod()
	{	
		String access_specifier="public";
		String return_type="void";
		String name="";
		ClassMethod class_method ;
		
		System.out.println("Enter the access specifier: (Public | Private | Protected)");
		//access_specifier=speech.getUserInput(2000);
		access_specifier=getValidatedInput(acc_spec);
		System.out.println("Enter the return type: (String | Integer | Float | Double)");
		return_type=speech.getUserInput(2000);
				
		System.out.println("Enter the function name: ");
		name=speech.getUserInput(2000);
				
		class_method = new ClassMethod(name,access_specifier,return_type);
		
		class_methods.addChild(class_method);
	}

	public void getMainMethod()
	{
		class_methods.addChild( new ClassMethod("public static void main(String args[])"));

	}
	
	public void printClass()
	{
		System.out.println(myclass.getDescription());
	}
	
	private String getValidatedInput(List<String> lst)
	{
		String out="";
		//boolean gotInput=false;
		
		for(int i=0; i<3 ; i++)
		{
			out=speech.getUserInput(2000);
			
			if(lst.contains(out))
			{
				//gotInput=true;
				return out;
			} else { 
				for(String str : lst) {
					if(out.contains(str.substring(0,2))) {
						out=str;
						//gotInput=true;
						return out;
					}
				}
			}
			
			System.out.println("Invalid keyword!");
		}
		
		return(lst.get(0));
	}
}

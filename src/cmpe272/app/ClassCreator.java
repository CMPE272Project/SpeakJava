package cmpe272.app;
import cmpe272.editor.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//Other package imports
import cmpe272.classObj.*;
import cmpe272.text2speech.*;

//imports for json
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.*;

// This is the main class from where all the structure creation calls are made
public class ClassCreator {
	
	private MyClass myclass;
	private String classname;
	
	private Imports class_imports;
	private ClassVariables class_variables;
	private ClassMethods class_methods;
	
	private Speech speech;
	private TextToSpeechRunner tts;
	private List<String> keywords = Arrays.asList("abstract","continue","for","new","switch","default","goto","package","boolean","do","for","while","if","else","double","import","try","catch","static","final","long","short","return","int","void","const","extends","float","case","byte","case","implements","throw");
	private List<String> main_menu = Arrays.asList("class variable","class method","main method","exit");
	private List<String> toc= Arrays.asList("general","abstract","interface");
	private List<String> bodyOptions= Arrays.asList("declare variable","for loop","if else","while loop","assignment","function call","exit");
	private List<String> acc_spec= Arrays.asList("public","protected","private");
	private List<String> data_types= Arrays.asList("integer","string","double","floating point","boolean","integer array","string array","double array","floating point array");
	
	private List<String> mathematical_operators = Arrays.asList("add","subtract","multiply","divide","modulus") ;
	private List<String> logical_operators = Arrays.asList("and","or","not") ;
	private List<String> conditional_operators = Arrays.asList("equal to","greater than","less than","greater than equal to","less than equal to","not equal to");
	private List<String> for_loop = Arrays.asList("loop through an array","simple for loop") ;
	private List<String> exit_menu=Arrays.asList("save","compile and run","exit");
	private JSONObject validator;
	
	public ClassCreator()
	{
		speech=new Speech();
		tts=new TextToSpeechRunner();
		
		initializeObjects();
		
	}
	
	
	public void initializeObjects()
	{	
		//init jsons
		try {
			String content = readFile("src/validation.json", StandardCharsets.UTF_8);
			validator=new JSONObject(content).getJSONObject("validation");

			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
			e.printStackTrace();
			}
	}
	
	public void createClass()
	{
		getClassDetails();
		
		getBody();
	
		printClass();
		
		exit();
	}
	
	public void getClassDetails()
	{
		String type="general";
		String name;
		String[] impl={};
		String ext="";
		
		//writeToConsole("Select a type of class to create :",toc);
		//type=getValidatedInput(toc);
		writeToConsole("Speech Mode Initiated . . .");
		writeToConsole("Speak your class name:");
		name=speech.getUserInput(2000);
		
		updateValidatorList("basic", name , classToCamelCase(name));
		name = classToCamelCase(name);
		classname=name;
		myclass = new MyClass(name,type,ext,impl,"public");
		
		//editor functionality
		clearConsole();
		printClass();
	}
	
	public void getBody()
	{
		class_variables = new ClassVariables("// Class variables");
		class_methods = new ClassMethods("// Class Methods");
		
		myclass.addChild(class_variables);
		myclass.addChild(class_methods);
		
		String choice = "";
		while(!choice.equals("exit")) {
			writeToConsole("Choose one option from menu:", main_menu);
			
			choice=getValidatedInput(main_menu);
			
			switch(choice)
			{
			case "class variable": getClassVariable();
			 					break;
			case "class method": getClassMethod();
			 					break;
			case "main method": getMainMethod();
								main_menu= Arrays.asList("class variable","class method","exit");
			 					break;
			case "exit": 		break;
			}
		}
		
	}
	
	public void getClassVariable()
	{
		String access_specifier="public";
        String name="";
        String inp;
		String type;
		String val="";
				
					
		writeToConsole("Set the visibility of the variable: ");
		access_specifier=getValidatedInput(acc_spec);
		
		writeToConsole("Select a data type or data type array from the following:", data_types);
		type=getValidatedInput("basic", data_types);
		
		writeToConsole("Say the variable name");
		name=speech.getUserInput(2000);
		
		if(type.equals("int")||type.equals("String")||type.equals("int[]")||type.equals("String[]"))
		{
			writeToConsole("Do you want to initialize " + name + " ?");
			System.out.println("Yes/No");
			
			if(speech.getUserInput(2000).equals("yes")){
				if(type.contains("[]")){
					inp="";
					if(type.equals("String"))
						val="{\"";
					else
						val="{";
					while(!inp.equals("stop"))
					{ writeToConsole("next");
						inp=speech.getUserInput(2000);
					val+=inp+",";}
					
					if(type.equals("String")) {
						val=val.replace(",stop,", "\"}");
						val=val.replace(",", "\",\"");
					}
					else
						val=val.replace(",stop,", "}");
				} else {
					writeToConsole("say the " + type + "value:");
					inp=speech.getUserInput(2000);
					if(type.equals("String"))
						val="\""+inp+"\"";
					else
						val=inp;
				}
			}
				
		}
		
		Variable var;
		if(val.equals(""))
		 var = new Variable(access_specifier+ " "+ type+" "+ name + " ;");
		else
			var=new Variable(access_specifier+ " "+type+ " " + name + "=" + val + " ;");
		
		class_variables.addChild(var);
		//Adding to the validator list
		updateValidatorList("literals", name, type);
		
		//editor functionality
				clearConsole();
				printClass();
	}

	
	public void getClassMethod()
	{	
		String access_specifier="public";
		String return_type="void";
		String name="";
		List<String> parameters= Arrays.asList() ;
		List<String> param_type = Arrays.asList() ;
		ClassMethod class_method ;
		String inp="";
		String type="";
		String var="";
		
		writeToConsole("Enter the access specifier:", acc_spec);
		access_specifier= getValidatedInput("access specifier",acc_spec);
		
		writeToConsole("Does function return anything?");
		System.out.println("Yes/No");
		inp=speech.getUserInput(2000);
		if(inp.equals("yes")) {
		writeToConsole("Enter the return type:", data_types);
		return_type=getValidatedInput("basic",data_types);
		}
		
		writeToConsole("Enter the function name: ");
		name=speech.getUserInput(2000);
		
		writeToConsole("Say ADD PARAMETER to add a new parameter or CLOSE to enter the function body");
		inp=speech.getUserInput(2000);
		
		while(!inp.equals("close")) {
			if(inp.equals("add parameter"))
			{
				writeToConsole("Say <data type> or <data type> array :", data_types);
				type=getValidatedInput("basic",data_types);
							
				writeToConsole("Say the variable name");
				var=speech.getUserInput(2000);
				
				parameters.add(var);
				param_type.add(type);
				
			} else {
				writeToConsole("Invalid input!! Please say ADD PARAMETER to add a new parameter or CLOSE to enter the function body");
			}
		}
		class_method = new ClassMethod(methodToCamelCase(name),access_specifier,return_type,parameters,param_type);
		
		getInnerBody(class_method);
		
		class_methods.addChild(class_method);
		
		//Adding to the validator list
		updateValidatorList("functions", name, return_type);
		
		//editor functionality
		clearConsole();
		printClass();
	}

	public void getMainMethod()
	{	
		ClassMethod main_method=new ClassMethod("public static void main(String args[])"); 
		getInnerBody(main_method);
		class_methods.addChild(main_method);
		
		//editor functionality
		clearConsole();
		printClass();
	}
	
	
	public void getInnerBody(ClassComponents class_object)
	{
		String option="";
		
		while(!option.equals("exit")) {
			writeToConsole("Choose one option from menu:", bodyOptions);
			
			option=getValidatedInput(bodyOptions);
			
		switch (option) {
			case "declare variable": addVariable(class_object);
				break;
			case "for loop": addFor(class_object);
				break;
			case "while loop": addWhile(class_object);
				break;
			case "assignment": addStatement(class_object);
				break;
			case "if else": addIf(class_object);
				break;
			case "function call": addFunctionCall(class_object);
				break;
			default:
				break;
			}
		}
	}
	
	public void addVariable(ClassComponents class_object)
	{
		String inp;
		String type;
		String name;
		String val="";
				
		writeToConsole("Select a data type or data type array from the following:", data_types);
		type=getValidatedInput("basic",data_types);
					
		writeToConsole("Say the variable name");
		name=speech.getUserInput(2000);
		
		if(type.equals("int")||type.equals("String")||type.equals("int[]")||type.equals("String[]"))
		{
			writeToConsole("Do you want to initialize " + name + " ?");
			System.out.println("Yes/No");
			
			if(speech.getUserInput(2000).equals("yes")){
				if(type.contains("[]")){
					inp="";
					if(type.equals("String"))
						val="{\"";
					else
						val="{";
					while(!inp.equals("stop"))
					{ writeToConsole("next");
						inp=speech.getUserInput(2000);
					val+=inp+",";}
					
					if(type.equals("String")) {
						val=val.replace(",stop,", "\"}");
						val=val.replace(",", "\",\"");
					}
					else
						val=val.replace(",stop,", "}");
				} else {
					writeToConsole("say the " + type + "value:");
					inp=speech.getUserInput(2000);
					if(type.equals("String"))
						val="\""+inp+"\"";
					else
						val=inp;
				}
			}
				
		} else if(type.equals("boolean")) { 
			writeToConsole("Do you want to initialize " + name + " ?");
			System.out.println("Yes/No");
			if(speech.getUserInput(2000).equals("yes"))
			{
				writeToConsole("Enter the value as true or false");
				val=speech.getUserInput(2000);
			}
		} else if(type.equals(classname)) {
			writeToConsole("Do you want to initialize " + name + " ?");
			System.out.println("Yes/No");
			if(speech.getUserInput(2000).equals("yes"))
			{
				val="new "+ classname+"()";
			}
		}
		
		Variable var;
		if(val.equals(""))
		 var = new Variable(type+" "+ name + " ;");
		else
			var=new Variable(type+ " " + name + "=" + val + " ;");
		
		class_object.addChild(var);
		updateValidatorList("local_literals", name, type);
		
		//editor functionality
		clearConsole();
		printClass();
			
	}
	
	public void addFor(ClassComponents class_object)
	{
		String option="";
				
		writeToConsole("Choose for loop option from menu:", for_loop);
		
		option=getValidatedInput(for_loop);
		
		switch (option) {
			case "loop through an array": arrayFor(class_object);
				break;
			case "simple for loop": simpleFor(class_object);
				break;
			default:
				break;
		}
		
		//editor functionality
		clearConsole();
		printClass();
		
	}
	
	public void arrayFor(ClassComponents class_object)
	{
		String arr;
		writeToConsole("Enter the array name");
		arr=speech.getUserInput(2000);
		
		ForLoop fl = new ForLoop("for(int i=0; i<"+arr+".lenght-1;i++)");
		updateValidatorList("local_literals", "i", "int");
		getInnerBody(fl);
		class_object.addChild(fl);
	}
	
	public void simpleFor(ClassComponents class_object)
	{
		
	}
	
	public void addWhile(ClassComponents class_object)
	{
		writeToConsole("Enter the condition for while loop");
		WhileLoop wl= new WhileLoop("while("+getCondition()+")");
		getInnerBody(wl);
		class_object.addChild(wl);
	}
	
	public void addIf(ClassComponents class_object)
	{
		writeToConsole("Enter the condition for if");
		If i= new If("if("+getCondition()+")");
		getInnerBody(i);
		Else e =  new Else("else");
		writeToConsole("Enter else body");
		getInnerBody(e);
		class_object.addChild(i);
		class_object.addChild(e);
	}
	
	public void addFunctionCall(ClassComponents class_object)
	{
		List<String> func;
		List<String> sys = Arrays.asList("print");
		String inp="";
		String obj="";
		
		try {
			func=getList(validator.getJSONObject("functions"));
			if(func.isEmpty())
				func=sys;
			else
				func.addAll(sys);
			writeToConsole("select a function to call",func);
			inp=getValidatedInput(func);
			
		} catch (JSONException e) {
			inp="print";
			e.printStackTrace();
		}
		
		if(inp.equals("print"))
			addPrintln(class_object);
		else
		{
			writeToConsole("Enter the object name to call this mthod: ");
			obj=speech.getUserInput(2000);
			FunctionCall fc =new FunctionCall(obj+"."+methodToCamelCase(inp));
		}
	}
	
	public void addStatement(ClassComponents class_object)
	{
		String lhs="";
		String rhs="";
		
		writeToConsole("Enter the LHS variable name ");
		
		lhs=getVariable();
		
		if(getType("local_literals", lhs).equals("boolean"))
		{
			writeToConsole("Enter the value as true or false");
			rhs=speech.getUserInput(2000);
		} else {
		
			writeToConsole("Enter RHS ");
			rhs=getComputation();
		}
		Statement stmt = new Statement(lhs +" = " + rhs+";");
		
		class_object.addChild(stmt);
	}
	
	public void addPrintln(ClassComponents class_object)
	{
		String inp="";
		String print="";
		
		writeToConsole("chooose ADD TEXT or ADD VARIABLE or say SEMICOLON to exit");
		while(!inp.equals("semicolon"))
		{
			writeToConsole("Enter your Choice");
			inp=speech.getUserInput(2000);
			
			if(inp.equals("add text")){
				writeToConsole("Say the text you want to print");
				print+="\""+speech.getUserInput(2000)+"\"+";
			} else if(inp.equals("add variable")) {
				writeToConsole("Select the variable");
				print+=getVariable()+"+";
			}
			
		}
		print=print.substring(0, print.length()-1);
		
		Print println = new Print(print);
		class_object.addChild(println);
	}
	
	public String getComputation()
	{
		String var;
		String opr;
		String comp;
		String inp="";
		
		writeToConsole("Select a variable from the list");
		var=getVariable();
		comp=var;
		
		writeToConsole("Say semicolon to exit or continue to add operations");
		inp=speech.getUserInput(2000);
		
		while(!inp.equals("semicolon")) {
			writeToConsole("select an operator from the list",mathematical_operators);
			opr=getValidatedInput("operators", mathematical_operators);
			comp+=opr;
			writeToConsole("say VARIABLE to add a variable or CONSTANT to add a constant");
			inp=speech.getUserInput(2000);
			if(inp.equals("variable"))
				var=getVariable();
			else
				var=speech.getUserInput(2000);
			comp+=var;
			writeToConsole("Say semicolon to exit or continue to add operations");
			inp=speech.getUserInput(2000);
		}
		
		return comp;
	}
	
	public String getCondition()
    {
        String var1="";
        String op="" ;
        String var2="";
        String inp;
        
		writeToConsole("Select a variable from the following");
        var1 = getVariable();
        
        if(getType("literals", var1) .contains("[]")||getType("local_literals", var1) .contains("[]")) {
        	writeToConsole("Please enter the index of array");
        	inp=speech.getUserInput(2000);
        	var1=var1+"["+inp+"]";
        }
        
        writeToConsole("Do you want to add any conditional operator?");
        System.out.println("Yes/No");
        inp=speech.getUserInput(2000);
        
        if(inp.equals("yes")) {
	        writeToConsole("Select a conditional operator from the following");
	        writeToConsole(conditional_operators);
	        op = getValidatedInput("conditional_operators", conditional_operators);
	        
	        writeToConsole("Select another variable from the following");
	        var2 = getVariable();
	        
	        if(getType("literals", var1).contains("[]")||getType("local_literals", var1).contains("[]")) {
	        	writeToConsole("Please enter the index of array");
	        	inp=speech.getUserInput(2000);
	        	var2=var2+"["+inp+"]";
	        }
	        return(var1+op+var2);
        }
        
        return(var1);
        
             
       
    }
	
	public String getVariable()
	{
		List<String> literals = Arrays.asList();
		String var;
		String inp;
		try {
			literals = getList(validator.getJSONObject("literals"));
			List<String> local_literals = getList(validator.getJSONObject("local_literals"));
			literals.addAll(local_literals);
			writeToConsole(literals);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		var=getValidatedInput(literals);
		if(getType("literals", var) .contains("[]")||getType("local_literals", var) .contains("[]")) {
			writeToConsole("Please enter the index of array");
        	inp=speech.getUserInput(2000);
        	var=var+"["+inp+"]";
		}
		return(var);
	}
	
	public String getType(String group, String key)
	{
		try {
			return(validator.getJSONObject(group).getString(key));
		} catch (JSONException e) {
			e.printStackTrace();
			return("");
		}
	}

	public List<String> getLiteralsOfType(String type)
	{
		List<String> lst=Arrays.asList();
		Iterator<?> keys;
		String key;
		JSONObject obj;
		try {
			obj = validator.getJSONObject("literals");
			
			keys =obj.keys();
			while(keys.hasNext())
			{
				key=(String) keys.next();
				if(obj.getString(key).equals(type))
				lst.add(key);
			}
			
			obj = validator.getJSONObject("local_literals");
			
			keys =obj.keys();
			while(keys.hasNext())
			{
				key=(String) keys.next();
				if(obj.getString(key).equals(type))
				lst.add(key);
			}
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		return lst;
	}
	
	private String getValidatedInput(String group, List<String> lst)
	{
		try {
			return(validator.getJSONObject(group).getString(getValidatedInput(lst)));
		} catch (JSONException e) {
			e.printStackTrace();
			return(lst.get(0));
		}
		
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
	
	private void updateValidatorList(String group, String key, String value)
	{
		try {
			validator.getJSONObject(group).put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private List<String> getList(JSONObject obj)
	{	
		List<String> lst=new ArrayList<String>();
		Iterator<?> keys = obj.keys();
		String key;
		while(keys.hasNext())
		{
			key=(String) keys.next();
			lst.add(key);
		}
		
		return lst;
	}
	
	public void exit()
	{
		String inp="";
		writeToConsole("Choose an option from the menu",exit_menu);
		
		inp=getValidatedInput(exit_menu);
		
		while(!inp.equals("exit"))
		{
			if(inp.equals("compile and run")) {
				compileApp();
				runApp();
			} else if(inp.equals("save")){
				saveClass();
			}
			
			writeToConsole("Choose an option");
			inp=getValidatedInput(exit_menu);
		}
	}

	//Utilities
	
	public String classToCamelCase(String s)
	{
	    String[] parts = s.split(" ");
	    String camelCaseString = "";
	    for (String part : parts) {
	 	    camelCaseString = camelCaseString + toProperCase(part);
	    }
	    return camelCaseString;
	}

	
	public String methodToCamelCase(String s)
	{
	    String[] parts = s.split(" ");
	    String camelCaseString = "";
	    boolean firstPart = true;
	    for (String part : parts){
	 	   if(firstPart) {
	 		   firstPart=false;
	 	   camelCaseString+=part;
	 	   }else{
	 		   camelCaseString = camelCaseString + toProperCase(part);
	 	   }
	    }
	    return camelCaseString;
	}

	public String toProperCase(String s) 
	{
     return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
	
	String readFile(String path, Charset encoding) throws IOException 
	{
	  byte[] encoded = Files.readAllBytes(Paths.get(path));
	  return new String(encoded, encoding);
	}
	
	private void writeToConsole(String message, List<String> menu)
	{
		System.out.println(message);
		tts.speak(message);
		for(String str: menu) {
			System.out.println((menu.indexOf(str)+1) + ") "+ str);
			tts.speak(str);}
	}
	
	private void writeToConsole(List<String> menu)
	{
		for(String str: menu)
		System.out.println((menu.indexOf(str)+1) + ") "+ str);
	
	}
	
	private void writeToConsole(String message)
	{
		System.out.println(message);
		tts.speak(message);
	}
	
	//Editor functionalities
	
	public void printClass()
	{
		SpeechCoder.editorFrame.updateUI1(myclass.getDescription(),-1);
	}

	public void runApp()
	{
		SpeechCoder.editorFrame.runApp(classname);
	}

	public void compileApp()
	{
		SpeechCoder.editorFrame.compileApp(classname);
	}

	public void saveClass()
	{
		SpeechCoder.editorFrame.saveFile(classname);
	}

	public void clearConsole()
	{
		SpeechCoder.editorFrame.consoleTextArea.setText("");
	}
}
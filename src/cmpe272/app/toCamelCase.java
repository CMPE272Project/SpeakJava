package cmpe272.app;

public class toCamelCase {
    public static void main( String args[])
    {
    String str = "enter my date by birth";
    /*boolean bMustCapitalize = true ;
    StringBuffer result = new StringBuffer(str.length());
    String strl = str.toLowerCase();
   
    for (int i = 0; i < strl.length(); i++)
    {
      char c = strl.charAt(i);
      if (c >= 'a' && c <= 'z')
      {
        if (bMustCapitalize)
        {
          result.append(strl.substring(i, i+1).toUpperCase());
          bMustCapitalize = false;
        }
        else
        {
          result.append(c);
        }
      }
      else
      {
        bMustCapitalize = true;
      }
    }
    System.out.println(result);*/
    System.out.println(toCamelCase1(str));

}
    
       static String toCamelCase1(String s){
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

    static String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() +
                   s.substring(1).toLowerCase();
    }
}
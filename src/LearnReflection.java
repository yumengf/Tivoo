import java.lang.reflect.Method;


public class LearnReflection {
	public void main(String[] arg0){
	 try {
		Class c = Class.forName("java.lang.String");
		Method m[] = c.getDeclaredMethods();
		System.out.println(m[0].toString());
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	}
}

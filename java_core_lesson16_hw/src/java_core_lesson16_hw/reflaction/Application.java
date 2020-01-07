package java_core_lesson16_hw.reflaction;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Iterator;

public class Application {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Class deputatClass=Deputat.class; //object of Class
		//get name of class
		String nameOfClass=deputatClass.getName();
		System.out.println("Full name of Class="+nameOfClass);
		String simpleName=deputatClass.getSimpleName();
		System.out.println();
		System.out.println("Simple name of Class="+simpleName);
		// get modifiers;
		int modifier=deputatClass.getModifiers();
		String modifierText=Modifier.toString(modifier);
		System.out.println("Class modifier="+modifierText+" int= "+modifier);
		modifier=2;
		modifierText=Modifier.toString(modifier);
		System.out.println("Class modifier="+modifierText+" int= "+modifier);
		
		//is Abctract current modifiers (modifier is value private modifiers - int 2)?
		System.out.println("is abstract?"+Modifier.isAbstract(modifier)); //false
		//is Private current modifiers (modifier is value private modifiers - int 2)?
		System.out.println("is private?"+Modifier.isPrivate(modifier)); //true 
		
		//info about package
		Package classPackage = deputatClass.getPackage();
		System.out.println("Package  of current class is "+classPackage);
		
		//info about superclass for Deputat
		Class superclass = deputatClass.getSuperclass();
		System.out.println("SuperClass for Deputat is class "+superclass);
		
		//info for interface
		Class[] interfaces = deputatClass.getInterfaces();
		System.out.println("Class Deputat implements interface "+Arrays.toString(interfaces));
		
		System.out.println("Get all constructors:");
		System.out.println();
		Constructor<Deputat> [] deputatConstructor=deputatClass.getConstructors();
		//or can use statment "for loop"
		for (Constructor<Deputat> constructor : deputatConstructor) {
			System.out.println(constructor);
		}
		//get count constructors
		System.out.println("Constructor size (count)="+deputatConstructor.length);
		
		System.out.println();
		//access to constructor
		Constructor<Deputat> constructor=deputatConstructor[0];
		System.out.println("Constructior index  0= "+constructor);
		System.out.println();
		System.out.println("parameters type of constructors:");
		Class<?>[] parameterTypes = constructor.getParameterTypes();
		for (Class<?> class1 : parameterTypes) {
			System.out.println(class1);
		}
		
		
		System.out.println("Get Constructors by 2 parametrs:");
		Constructor single—onstructor = deputatClass.getConstructor(String.class, String.class);
		System.out.println(single—onstructor);
		
		//Manipulation with constructor
		
		
		//get constructor by 5 parametrs and create object Deputat
		Constructor<Deputat> constructor1=deputatClass.getConstructor(String.class,String.class,boolean.class,int.class,int.class);
		Deputat newInstanseDeputat=constructor1.newInstance("Pupkin","Ivan",true,80,120);
		System.out.println("Reflaction Deputat = "+newInstanseDeputat);
		
		System.out.println();
		System.out.println("Get field for class");
		System.out.println("1. output public field:");
		Field [] fieldPublic=deputatClass.getFields();
		for (int i = 0; i < fieldPublic.length; i++) {
			Field field = fieldPublic[i];
			System.out.println(field);
		}
		
		System.out.println();
		System.out.println("2. output all field ( private, public and other:");
		Field [] fields=deputatClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			System.out.println(field);
		}		
		
		//change data in public field of object
		Field surnameField=deputatClass.getField("surname");
		surnameField.set(newInstanseDeputat, "Sokol");
		System.out.println("Deputat new surname is "+newInstanseDeputat);
		
		//change data in private field of object by index or name field
		
		Field privateHabarnik=deputatClass.getDeclaredField("habarnik");
		privateHabarnik.setAccessible(true);
		//change value of private field habarnik
		privateHabarnik.set(newInstanseDeputat, false);
		System.out.println("New info  about habarnik. Is "+deputatClass.getField("surname").get(newInstanseDeputat)+"habarnik? "+privateHabarnik.get(newInstanseDeputat));
		
		//methods
		System.out.println();
		System.out.println("All methods:");
		Method [] methods=deputatClass.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			System.out.println(method);
		}
		
		//put method by index
		System.out.println("\n newInstanceDeputat before change field "+methods[2].getName()+ " using invoke: "+newInstanseDeputat);
		methods[2].invoke(newInstanseDeputat, "Nikola");
		System.out.println("info deputat after changed field "+methods[2].getName()+ ":" +newInstanseDeputat);
		
		//using getMethod(String name, arg..)
		Method method=deputatClass.getMethod("setHabarnik",boolean.class);
		System.out.println("\n newInstanceDeputat before change field using invoke: "+newInstanseDeputat);
		method.invoke(newInstanseDeputat, true);
		System.out.println("info deputat after changed foield of habarnik:" +newInstanseDeputat);
		
		method=deputatClass.getMethod("setSizeOfHabarofHabarnik", int.class, boolean.class);
		System.out.println("Method "+method.getName()+" has parametrs: "+Arrays.toString(method.getParameterTypes()));
		System.out.println("Brfor using invoke to method "+method.getName()+" for changed field:" +newInstanseDeputat);
		method.invoke(newInstanseDeputat, 425, false);
		System.out.println("info deputat after changed foield of habarnik:" +newInstanseDeputat);
		
		
		
		
	}

}

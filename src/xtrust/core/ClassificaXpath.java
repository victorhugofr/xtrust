package xtrust.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.reflections.Reflections;

import annotations.XTrust;
public final class ClassificaXpath {

	@XTrust
	public String xpath = "/a";
	
	public static void classifica() throws  ClassNotFoundException, InstantiationException, IllegalAccessException {
		   Reflections reflections = null;
		Set<String> files=new HashSet<>();
     listOfPackage("src/",files);
     for(String pack : files) {
     	reflections = new Reflections(pack);
     	Set<Class> classes = findAllClassesUsingClassLoader(pack);
     	for(Class<?> f: classes) {
     		Field[] variaveis = f.getDeclaredFields();
     		for(Field variavel: variaveis) {
     			if(variavel.getAnnotationsByType(XTrust.class).length>0) {
     				variavel.setAccessible(true);
     				Object value = variavel.get(Class.forName(f.getName()).newInstance());
     				printResultado(value.toString(),calcula(value.toString()));
     			}
     		}
     	}
     }
     
	}
	
	private static void printResultado(String xpath,String valor) {
 	   if(valor.equals("3")) {
		   System.out.println("O xpath "+xpath+" é fácil de quebrar");
	   }else if(valor.equals("0")) {
		   System.out.println("O xpath "+xpath+" não é válido");
	   }else if(valor.equals("2")) {
		   System.out.println("O xpath "+xpath+" é razoável");
	   }else if(valor.equals("1")) {
		   System.out.println("O xpath "+xpath+" é confiável");
	   }
	}
	
	private static Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
          .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
          .filter(line -> line.endsWith(".class"))
          .map(line -> getClass(line, packageName))
          .collect(Collectors.toSet());
    }
	
	private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
              + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
	
	private static void listOfPackage(String directoryName, Set<String> pack) {
        File directory = new File(directoryName);

        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile() && 
            		(file.getPath().contains("main"+File.separator+"java")
            				||file.getPath().contains("test"+File.separator+"java")) ) {
                String path=file.getPath();
                String packName=path.substring(path.indexOf("src")+14, path.lastIndexOf(File.separator.toCharArray()[0]));
                pack.add(packName.replace(File.separator.toCharArray()[0], '.'));
            }  else if (file.isFile()) {
                String path=file.getPath();
                String packName=path.substring(path.indexOf("src")+4, path.lastIndexOf(File.separator.toCharArray()[0]));
                pack.add(packName.replace(File.separator.toCharArray()[0], '.'));
            }else if (file.isDirectory()) {

                listOfPackage(file.getAbsolutePath(), pack);
            }
        }
    }
	
	private Map<String,String> classificaXpath(Set<String> xpaths,Map<String,String>mapa){
		String temp;
		for(String s : xpaths){
			temp= calcula(s);
			mapa.put(s, temp);
        }
		return mapa;
	}
	
	private static  String calcula(String xpaths){
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		try {
			XPathExpression expr = xpath.compile(xpaths);
		} catch (XPathExpressionException e) {
			return "0";
		}
		if(xpaths.split("/").length>6) {
			return "3";
		}else {
			if(xpaths.contains("@class=") || xpaths.contains("@id=")) {
				return "2";
			}else {
				return "1";
			}
		}
		
	}
}

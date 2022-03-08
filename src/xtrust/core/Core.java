package xtrust.core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import annotations.XTrust;
public class Core {

//	@XTrust
//	public String teste =  "teste";
	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//		FileInputStream stream = new FileInputStream("resources/xpaths.txt");
//        InputStreamReader reader = new InputStreamReader(stream);
//        BufferedReader br = new BufferedReader(reader);
//        String linha = br.readLine();
//        Set<String> xpaths = new HashSet<String>();
//        while(linha != null) {
//            xpaths.add(linha);
//            linha = br.readLine();
//        }
//        ClassificaXpath cX = new ClassificaXpath();
//        Map<String,String>mapa = new HashMap<String,String>();
//        Map<String,String>retorno = cX.classificaXpath(xpaths,mapa);
//       for(String a : xpaths) {
//    	   if(retorno.get(a).equals("3")) {
//    		   System.out.println("O xpath "+a+" é fácil de quebrar");
//    	   }else if(retorno.get(a).equals("0")) {
//    		   System.out.println("O xpath "+a+" não é válido");
//    	   }else if(retorno.get(a).equals("2")) {
//    		   System.out.println("O xpath "+a+" é razoável");
//    	   }else if(retorno.get(a).equals("1")) {
//    		   System.out.println("O xpath "+a+" é confiável");
//    	   }
//       }
//       br.close();
		ClassificaXpath cX = new ClassificaXpath();
		cX.classifica();
	}

}

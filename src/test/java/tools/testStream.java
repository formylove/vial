package tools;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import org.springframework.util.StringUtils;

import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.entity.Note;

public class testStream {

	public testStream() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		String line = null;
		String filename = "d:\\x.xml";
		//scanner ��ȡ�ı�
		InputStream in = new FileInputStream(new File(filename)); 
		Scanner s = new Scanner(in); 
		while(s.hasNextLine()){ 
			System.out.println(s.nextLine()); 
		} 
		//reader ��ȡ�ı�
		BufferedReader br = new BufferedReader(new FileReader(filename));
		while ((line = br.readLine()) != null) {
		//���ı���ӡ������̨
		System.out.println(line);
		}
		
		Scanner scan = new Scanner(System.in);
		 while (true) { 
             line = scan.nextLine(); 
             if (line.equals("exit")) break; 
             System.out.println(">>>" + line); 
     } 

s.close();
scan.close();
br.close();
		
	}
}

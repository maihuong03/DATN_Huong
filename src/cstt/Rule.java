/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cstt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author 24h
 */
public class Rule implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int maLuat;
	private String IF;
	private String THEN;
	private String[] codeIf;

	public Rule() {

	}

	public Rule(int maLuat, String IF, String THEN) {
		this.maLuat = maLuat;
		this.IF = IF;
		this.THEN = THEN;
//        this.itemCode = itemCode;
	}

	public int getMaLuat() {
		return maLuat;
	}

	public void setMaLuat(int maLuat) {
		this.maLuat = maLuat;
	}

	public String getIF() {
		return IF;
	}

	public void setIF(String IF) {
		this.IF = IF;
	}

	public String getTHEN() {
		return THEN;
	}

	public void setTHEN(String THEN) {
		this.THEN = THEN;
	}

	public static boolean ghi(String filename, ArrayList<Rule> rule) throws FileNotFoundException, IOException {
		FileOutputStream f;
		f = new FileOutputStream(filename);
		ObjectOutputStream oStream = new ObjectOutputStream(f);
		oStream.writeObject(rule);
		oStream.close();
		return true;
	}

	public static ArrayList<Rule> docsk(String filename)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<Rule> sk = new ArrayList<>();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
		sk = (ArrayList<Rule>) ois.readObject();
		ois.close();
		return sk;
	}

	public String getLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRight() {
		// TODO Auto-generated method stub
		return null;
	}

	// luật cho robot phục vụ

	public Rule(int maLuat, String[] codeIf,String IF, String THEN) {
		this.maLuat = maLuat;
		this.IF = IF;
		this.THEN = THEN;
		this.codeIf = codeIf;
//        this.itemCode = itemCode;
	}
	
	public Rule(int maLuat, String[] codeIf) {
		this.maLuat = maLuat;
		this.codeIf = codeIf;
//        this.itemCode = itemCode;
	}
	
	public String[] getCodeIf() {
		return codeIf;
	}

	public void setCodeIf(String[] codeIf) {
		this.codeIf = codeIf;
	}
	

	
	public static boolean ghi2(String filename, ArrayList<Rule> rule) throws FileNotFoundException, IOException {
		FileOutputStream f;
		f = new FileOutputStream(filename);
		ObjectOutputStream oStream = new ObjectOutputStream(f);
		oStream.writeObject(rule);
		oStream.close();
		return true;
	}

	public static ArrayList<Rule> docsk2(String filename)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<Rule> sk = new ArrayList<>();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
		sk = (ArrayList<Rule>) ois.readObject();
		ois.close();
		return sk;
	}
}

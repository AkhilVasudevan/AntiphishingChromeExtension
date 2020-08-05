/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.nio.*;
/**
 *
 * @author Akhil
 */
public class SpellCheck {
    public static boolean spellCheck(String ts) {
		//try {
			//List<String> allLines = Files.readAllLines(Paths.get("E:\\sample\\src\\sample\\dictionary\\urls.txt"));
                        MySpellChecker spellChecker = new MySpellChecker("E:\\blockerapp\\src\\java\\dictionary\\dictionary.txt");
			//for (String line : allLines) {
                                String lines=ts.replace('.',' ');
                                ts = spellChecker.doCorrection(lines);
                                if(lines.equals(ts))
                                {
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
			//}
		//} 
                //catch (Exception e) 
                //{
		//	e.printStackTrace();
		//}
	}
}

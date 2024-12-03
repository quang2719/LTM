/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cau3;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class test {
    public static void main(String[] args) {
        String s= "asdfasdfsadf12";
        Map<Character,Integer> mp = new HashMap<>();
        for (int i = 0;i< s.length();i++){
            if (!mp.containsKey(s.charAt(i))){
                mp.put(s.charAt(i), 1);
            }else{
                int count = mp.get(s.charAt(i));
                mp.remove(s.charAt(i));
                mp.put(s.charAt(i), count+1);
            }
        }
        for(Character c :mp.keySet()){
            System.out.println(c + "-" + (mp.get(c) +"0"));
        }
//        System.out.println(mp.toString());
        
    }
}

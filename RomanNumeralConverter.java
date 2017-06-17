/**
 * Author: Ayomikun Orogun
 * Creation Date: June 7, 2017
 * Purpose: A program that can convert Arabic numbers between 1 and 1000, to Roman numerals.
 * Note: This program can be modified easily to convert numbers of higher numbers of 10.
 * */

import java.util.*;

public class RomanNumeralConverter{
  /*Table containing the values of the fundamental Roman Numerals*/
  public static HashMap<Integer,String> romanTable = new HashMap<Integer, String>(){{
    put(1,"I");
    put(5,"V");
    put(10,"X");
    put(50,"L");
    put(100,"C");
    put(500,"D");
    put(1000,"M");
  }};
  
  public static int tensValue =0;
  public static String valHolder = "";
  public static int fDigit = 0;
  public static Scanner input = new Scanner(System.in);
  
  public static void main(String[] args){
    int userInput = 1;
    while(userInput !=0){
      System.out.println("\nEnter a number between 1 and 1000 or 0 to exit: ");
      userInput = input.nextInt();
      System.out.println("The roman numeral for "+userInput+" is: "+convertToRoman(userInput));
    }
  }
  
  public static String convertToRoman(int num){
    String numeralFinal = "";
    if(romanTable.containsKey(num)){
      return romanTable.get(num);
    }
    else{
      tensValue = getTensValue(num);
      
      for(int i=0; i<String.valueOf(num).length(); i++){
        valHolder = romanTable.get(tensValue); //Lookup the tensValue in the HashMap
        fDigit = getFirstDigit(Character.getNumericValue((String.valueOf(num)).charAt(i))); //Get each digit in the figure
        numeralFinal += makeRomanString(fDigit, tensValue, valHolder);
        tensValue = tensValue/10; //The tens position changes from digit to digit (Unit, Tens, Hundreds)
      }
      return numeralFinal;
    }
  }
  
  public static String makeRomanString(int digit, int tens, String numeral){//Builds the roman numeral string
    String tempRoman = ""; //Store the roman numeral 
    
    if((digit*tens)<=(5*tens)){ //For digits less than multiples of 5
      while(digit>0 && digit<4){
        tempRoman += numeral;
        digit--;
      }
      if(digit == 4){//Number right before five are preceeded by the tens numeral followed by the multiple of 5 numeral
        tempRoman = romanTable.get(tens)+""+romanTable.get(5*tens);
      }
      if(digit == 5){//Multiples of 5 that are not multiples of 10, can be looked up
        tempRoman = romanTable.get(5*tens);
      }
    }
    else if((digit*tens)>(5*tens)){ //For digits greater than multiples of 5
      while(digit>5 && digit<9){
        tempRoman += numeral;
        digit--;
      }
      tempRoman = romanTable.get(5*tens)+""+tempRoman;
      
      if(digit==9){
        tempRoman = romanTable.get(tens)+""+romanTable.get(tens*10);
      }
    }
    return tempRoman;
  }
  
  public static int getTensValue(int num){
    int multiplier = String.valueOf(num).length(); //Convert the number to string and find its length
    return (int)Math.pow(10, (multiplier-1)); //Had to cast to int cos of possible loss
  }
  
  public static int getFirstDigit(int num){
    return Character.getNumericValue((String.valueOf(num)).charAt(0)); //Convert the number to a string, get the first character and return its integer value
  }
}
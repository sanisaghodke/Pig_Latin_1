import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Book
{
  private String book;

  public Book(String url){
    readBook (url);
  }

  private void readBook(String link){
    try{
      URL url = new URL(link);
      Scanner s = new Scanner(url.openStream());

      while(s.hasNext()){
        String text = s.nextLine();
        System.out.println(text);
        book += text;
      }
    }

    catch(IOException ex){
      ex.printStackTrace();
    }

  }


  public String pigLatin(String word)
  {
    String newWord = "";
    String digits = "0123456789";
    String vowels = "aeiouyAEIOUY";
    String punctuation = ".,;!?'";
    boolean isCapital = false;
    boolean isVowel = false; 
    boolean isPunctuation = false;
    int punctuationIndex = word.length();
    StringBuffer sb = new StringBuffer();

   // System.out.println("the word is: "+ word);

    if(word.isEmpty()){
      return word;
    }
    if(digits.indexOf(word.substring(0, 1)) >= 0) {
      return word;
    }

    if(vowels.indexOf(word.substring(0,1))>=0) {
      return word + "yay";
    }else if (word.length() == 1){
      return word + "ay";
    }

    //checking if the word is capitalized or not
    if (Character.isUpperCase(word.charAt(0)) == true){
      isCapital = true; 
  
      //System.out.println("character is uppercase");

      // word.substring(0, 1).toUpperCase().equals(word.charAt(0));
    }

    //checking if the first letter of a word is a vowel
    if ((vowels.indexOf(word.charAt(0)) != -1)){
      isVowel = true;

     // System.out.println("found vowel");
    }

    //checking if there is punctuation at the end of the word
    for(int i = word.length()-1; i>=0; i--){
      if(punctuation.indexOf(word.charAt(i)) != -1){
        isPunctuation = true;
        punctuationIndex = i;

       // System.out.println("there is punctuation");
      }else{
        break;
      }

     // System.out.println(word.charAt(i));

    }

    //System.out.println("Location of punctuation: " + punctuationIndex);

    for(int i = 0; i <= word.length(); i++){
      if(vowels.indexOf(word.substring(i, i+1))>=0){
        String left = word.substring(0, i);
        String right = word.substring(i, punctuationIndex);
        System.out.println( "this is left: " + left + " this is right: " + right);  

        if (isPunctuation == true){
          if(isCapital == true){
          return right.substring(0, 1).toUpperCase()
                            +right.substring(1, right.length())
                            + left.substring(0, 1)
                            + left.substring(1, left.length())
                            + "ay"
                            + word.substring(punctuationIndex, word.length());

          }else{
            
            return right + left + "ay" + word.substring(punctuationIndex, word.length());
              
          }
        } else{
          if (isCapital == true){
            return right.substring(0, 1).toUpperCase()
                            + right.substring(1, right.length())         
                            + (left.substring(0, 1).toLowerCase()
                            + left.substring(1, left.length()))
                            + "ay";
          } else {
            return right + left + "ay";
          }
          
        }
        
      }
    }
    
    return newWord;
  }

  public String translateWord(String word)    //to share with class
  {
    String convertedWord = pigLatin(word);
    return convertedWord;

   
  }

  public String translateSentence(String sentence)
  {

    int spaceIndex;
    String word; 
    String sent = sentence;
    String retSentence = "";
    String translatedWord;

    spaceIndex = sent.indexOf (" ");

    /*
     * Taking the first word using substring and the location of the first space then translate the word
     * then find the location of the next space and translate the word between the first space and the second space, and keep doing that until you reach the end of the sentence
     */

    while(spaceIndex >= 0){
      word = sent.substring(0, spaceIndex);
      //System.out.println("this is the word: " + word);
      translatedWord = translateWord(word);
      //System.out.println("This is the translated word: "+ translatedWord);
      sent = sent.substring(spaceIndex+1, sent.length());
      //System.out.println("This is the sentence: "+ sent);
      spaceIndex = sent.indexOf(" ");
      retSentence = retSentence + " " + translatedWord;

    }

    translatedWord = translateWord(sent);
    retSentence = retSentence + " " + translatedWord;


    //if(sent.length(1>0)){
    //translate(sent);
    //}

    return retSentence;
  }

  
    
  }


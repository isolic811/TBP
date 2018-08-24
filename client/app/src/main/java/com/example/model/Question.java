package com.example.model;

import java.util.Vector;

public class Question {
    private String questionText;
    private String level;
    private String correctAnswer;
    private String ans1;
    private String ans2;
    private String ans3;

    public Question(String questionText, String level, String correctAnswer, String ans1, String ans2, String ans3) {
        this.questionText = questionText;
        this.level = level;
        this.correctAnswer = correctAnswer;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
    }

    public String getAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public static Vector<Question> localQuestions(){
        Vector<Question> questionList=new Vector<Question>();
        Question question= new Question("Što je zdravo za zube?","easy","mlijeko","sok","sladoled","čokolada");
        questionList.add(question);
        question=new Question("Koliko puta dnevno moramo prati zube?","easy","barem dva puta","nijednom","jednom","sto puta");
        questionList.add(question);
        question=new Question("Što se ne nalazi u ustima?","easy","zjenica","zubi","jezik","nepce");
        questionList.add(question);
        question=new Question("Što nije vrsta zuba?","easy","mlječnjaci","kutnjaci","sjekutići","očnjaci");
        questionList.add(question);
        question=new Question("Kada dijete mora prvi put ići kod stomatologa?","medium","kada nikne prvi zub","1 mjesec","1 godina","4 godine");
        questionList.add(question);
        question=new Question("Od čega se sastoji zub?","medium","krune i korijena","vrha i baze","vrha i dna","krune i baze");
        questionList.add(question);
        question=new Question("Osim za žvakanje, zubi nam služe i za?","medium","pričanje","okus","slušanje","razmišljanje");
        questionList.add(question);
        question=new Question("Kako se zove sjajna tvrda tvar koja pokriva zub?","hard","caklina","karijes","vosak","dentin");
        questionList.add(question);
        question=new Question("Što u pulpi šalje signale u mozak ako je sladoled prehladan?","hard","živci","valovi","zvukovi","žile");
        questionList.add(question);
        question=new Question("Što se nalazi na površini korjena zuba?","hard","cement","pasta","hitin","vapnenac");
        questionList.add(question);
        return questionList;
    }
}

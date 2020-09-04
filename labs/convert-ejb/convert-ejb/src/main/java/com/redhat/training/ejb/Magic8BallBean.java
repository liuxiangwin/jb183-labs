package com.redhat.training.ejb;

import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.Stateless;

//TODO Make this a stateless EJB

public class Magic8BallBean {

    public String answerQuestion(String question) {

      String[] answers = new String[10];
      answers[0] = "It is decidedly so.";
      answers[1] = "Ask again later.";
      answers[2] = "My reply is no.";
      answers[3] = "Cannot predict now.";
      answers[4] = "Don't count on it.";
      answers[5] = "As I see it, yes.";
      answers[6] = "Signs point to yes.";
      answers[7] = "My sources say no.";
      answers[8] = "Yes.";
      answers[9] = "No.";

      String answer = answers[ThreadLocalRandom.current().nextInt(0, 10)];

      // respond back with Question: {question}    |    Answer: {answer}.
      return "Question: " + question + "   |    Answer: " + answer;
    }
}

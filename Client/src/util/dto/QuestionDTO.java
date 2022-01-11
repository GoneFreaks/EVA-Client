package util.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionDTO {

	private String question;
	private String correct;
	private String eva00;
	private String eva01;
	private String eva02;

	public String getQuestion() {
		return question;
	}
	
	public QuestionDTO ArrayToDTO(String[] args) {
		if(args.length == 5) {
			question = args[0];
			correct = args[1];
			eva00 = args[2];
			eva01 = args[3];
			eva02 = args[4];
		}
		return this;
	}
	
	public List<String> getRandomOrder(){
		List<String> result = new ArrayList<>();
		result.add(correct);
		result.add(eva00);
		result.add(eva01);
		result.add(eva02);
		Collections.shuffle(result);
		return result;
	}
	
	public boolean isCorrect(String answer) {
		return answer.equals(correct);
	}

	@Override
	public String toString() {
		return question + "," + correct + "," + eva00 + "," + eva01 + "," + eva02;
	}
}

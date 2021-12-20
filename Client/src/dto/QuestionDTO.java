package dto;

public class QuestionDTO {

	private String question;
	private String correct;
	private String eva00;
	private String eva01;
	private String eva02;

	public String getQuestion() {
		return question;
	}

	public String getCorrect() {
		return correct;
	}

	public String getEva00() {
		return eva00;
	}

	public String getEva01() {
		return eva01;
	}

	public String getEva02() {
		return eva02;
	}
	
	public void StringToDTO(String[] args) {
		if(args.length == 5) {
			question = args[0];
			correct = args[1];
			eva00 = args[2];
			eva01 = args[3];
			eva02 = args[4];
		}
	}
	
	public boolean isCorrect(String answer) {
		return answer.equals(correct);
	}

	@Override
	public String toString() {
		return question + "," + correct + "," + eva00 + "," + eva01 + "," + eva02;
	}
}

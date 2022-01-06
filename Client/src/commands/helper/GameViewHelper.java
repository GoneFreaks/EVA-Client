package commands.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import dto.QuestionDTO;

public class GameViewHelper {

	public static QuestionDTO prepareQuestion(String input) {
		String[] args = input.split(",");
		List<Integer> random_order = new ArrayList<>();
		random_order.add(0);
		random_order.add(1);
		random_order.add(2);
		random_order.add(3);
		Collections.shuffle(random_order);
		return new QuestionDTO().ArrayToDTO(args).setRandomOrder(random_order);
	}
	
	public static String resultToString(String input, String id) {
		List<String> list = Arrays.asList(input.split(",,"));
		if(!list.get(0).startsWith(id)) Collections.reverse(list);
		
		StringBuilder b = new StringBuilder("");
		list.forEach((k) -> {
			String[] temp = k.split(",");
			if(temp[0].equals(id)) b.append("Spieler: " + temp[1] + (b.length() > 0? "":"    "));
			else b.append("Gegner: " + temp[1] + (b.length() > 0? "":"    "));
		});
		
		return b.toString();
	}
	
}

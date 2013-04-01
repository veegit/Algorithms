import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPattern {
	public static void main(String args[]) {
		String REGEX = "(C|J)\\s(C|J)(\\d{1,})\\sH:(\\d{1,})\\sE:(\\d{1,})\\sP:(\\d{1,})(.*)";
		String INPUT = "C C1999 H:9 E:5 P:9";
		String INPUT2 = "J J0 H:7 E:6 P:0 C453,C1706,C318,C271,C1958,C1051,C241,C1736,C304,C518";
		String INPUT3 = "";
		Pattern p = Pattern.compile(REGEX);
        Matcher matcher = p.matcher(INPUT3);
		int i = 0;
		if(matcher.find())
			while(i++<matcher.groupCount())
				System.out.println("--"+matcher.group(i) + "--");
		System.out.println("DONE " + matcher.groupCount());
	}
}
	
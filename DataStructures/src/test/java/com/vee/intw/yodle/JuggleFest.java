import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.lang.Comparable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.Iterator;
import java.lang.Integer;
import java.io.File;
import java.io.FileNotFoundException;

public class JuggleFest {
	final static String REGEX = "(C|J)\\s(C|J)(\\d{1,})\\sH:(\\d{1,})\\sE:(\\d{1,})\\sP:(\\d{1,})(.*)";
		
	Map<Integer,Circuit> map;
    List<Juggler> jugglers;
	int assignedSize;
	
	JuggleFest(String filename) {
		map = new HashMap<Integer,Circuit>();
		jugglers = new LinkedList<Juggler>();
		init(filename);
	}
	
	public void init(String filename) {
		try {
			Scanner scan = new Scanner(new File(filename));
			Pattern p = Pattern.compile(REGEX);
			while(scan.hasNextLine()) {
				String s = scan.nextLine();
				Matcher matcher = p.matcher(s);
				int i=0;
				if(matcher.find()) {
					String type = matcher.group(1);
					int id = Integer.parseInt(matcher.group(3));
					int H = Integer.parseInt(matcher.group(4));
					int E = Integer.parseInt(matcher.group(5));
					int P = Integer.parseInt(matcher.group(6));
					if(type.equals("C"))
						map.put(id,new Circuit(id,H,E,P));
					else {
						String prefstring = matcher.group(7);
						String prefarray[] = prefstring.trim().split(",");
						Juggler juggler = new Juggler(id,H,E,P);
						for (String str : prefarray) {
							int circuitId = Integer.parseInt(str.substring(1));
							Circuit circuit = map.get(circuitId);
							int score = circuit.H*H + circuit.E*E + circuit.P*P;
							juggler.addPreference(new JugglerPreference(circuit,score));	
							circuit.updateResult(new CircuitResult(juggler,score));
						}
						jugglers.add(juggler);
					}
				}
				else
					continue;
			}
			assignedSize = jugglers.size() / map.size();
		} catch (FileNotFoundException e) { }
	}
	
	public void display() {
		Iterator<Map.Entry<Integer,Circuit>> it = map.entrySet().iterator();
		recurse(null,it);
	}
	
	private void recurse(Map.Entry<Integer,Circuit> e,Iterator<Map.Entry<Integer,Circuit>> it) {
		if(!it.hasNext()) {
			return;
		} 
		else {
			Map.Entry<Integer,Circuit> entry = it.next();
			recurse(entry,it);
			Circuit c = entry.getValue();
			String circuitString = "C"+c.id+" ";
			Iterator<CircuitResult> resultIterator = c.results.iterator();
			while(resultIterator.hasNext())
				circuitString += resultIterator.next().toDisplayString() + ", ";
			System.out.println(circuitString);
		}
	}
	
	public static void main(String args[]) {
		JuggleFest e = new JuggleFest(args[0]);
		e.display();
	}
}
	
class Circuit {
	int id;
	int H;
	int E;
	int P;
	Set<CircuitResult> results = new TreeSet<CircuitResult>();
	int assignedSize;
	
	Circuit(int id, int H, int E, int P) {
		this.id = id;
		this.H = H;
		this.E = E;
		this.P = P;
	}
	
	void updateResult(CircuitResult result) {
		results.add(result);
	}
}
	
class CircuitResult implements Comparable<CircuitResult> {
	Juggler j;
	int score;
	boolean eligible;
	
	CircuitResult(Juggler j, int score) {
		this.j = j;
		this.score = score;
	}
	
	public int compareTo(CircuitResult cr) {
		int match =  cr.score - score;
		return match == 0 ? (j.compareTo(cr.j)) : match;
	}
		
	public String toDisplayString() {
		return j.toDisplayString();
	}
}
	
class Juggler implements Comparable<Juggler> {
	int id;
	int H;
	int E;
	int P;
	List<JugglerPreference> preferences = new ArrayList<JugglerPreference>();
	Circuit assigned;
	
	Juggler(int id, int H, int E, int P) {
		this.id = id;
		this.H = H;
		this.E = E;
		this.P = P;
	}
	
	Juggler(int id, int H, int E, int P, JugglerPreference pref) {
		this(id,H,E,P);
		this.preferences.add(pref);
	}
	
	void addPreference(JugglerPreference pref) {
		this.preferences.add(pref);
	}
	
	public String toDisplayString() {
		String str = "J"+id+" ";
		for(JugglerPreference pref : preferences)
			str += pref.toDisplayString() + " ";
		return str.trim();
	}
	
	public int compareTo(Juggler j) {
		return id - j.id;
	}
}

class JugglerPreference {
	Circuit c;
	int score=0;
	
	JugglerPreference(Circuit c) {
		this.c = c;
	}
	
	JugglerPreference(Circuit c, int score) {
		this.c = c;
		this.score = score;
	}
	
	void setScore(int score) {
		this.score = score;
	}
	
	public String toDisplayString() {
		return "C"+c.id +":"+ score;
	}
}
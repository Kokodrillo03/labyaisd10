
import java.util.*;

public class Document implements IWithName{
	public String name;
	// TODO? You can change implementation of Link collection
	public TwoWayCycledOrderedListWithSentinel link;
	public Document(String name) {
		this.name=name.toLowerCase();
		link=new TwoWayCycledOrderedListWithSentinel<>();
	}

	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new TwoWayCycledOrderedListWithSentinel();
		load(scan);
	}
	public void load(Scanner scan) {
		//TODO
		String line = scan.nextLine();
		while (!line.equals("eod")) {
			String[] words = line.split("\\s");
			for (String w : words) {
				if (w.length() > 5) {
					if (w.substring(0, 5).toLowerCase().equals("link=") && isCorrectId(w.substring(5))) {
						int ind1 = w.indexOf("(") + 1;
						int ind2 = w.indexOf(")");
						if (ind1 != 0 && ind2 != -1) {
							int temp = Integer.parseInt(w.substring(ind1, ind2));
							link.add(new Link(w.substring(5, ind1 - 1).toLowerCase(), temp));
						} else
							link.add(new Link(w.substring(5).toLowerCase()));
					}
				}
			}
			line = scan.nextLine();
		}
	}

	public static boolean isCorrectId(String id) {
		Boolean flag = Character.isLetter(id.charAt(0));
		int ind1 = id.indexOf("(") + 1;
		int ind2 = id.indexOf(")");
		if ((ind1 != 0 && ind2 == -1) || (ind1 == 0 && ind2 != -1))
			flag = false;
		if (ind1 != 0 && ind2 != -1) {
			try {
				int num = Integer.parseInt(id.substring(ind1, ind2));
				if (num <= 0)
					return false;
				return true;
			} catch (NumberFormatException e) {
				flag = false;
			}
		}
		return flag;
	}

	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	static Link createLink(String w) {
		if (isCorrectId(w)) {
			int ind1 = w.indexOf("(") + 1;
			int ind2 = w.indexOf(")");
			if (ind1 != 0 && ind2 != -1) {
				int temp = Integer.parseInt(w.substring(ind1, ind2));
				return (new Link(w.substring(0, ind1 - 1).toLowerCase(), temp));
			} else
				return (new Link(w));
		}
		return null;
	}

	@Override
	public String toString() {
		String retStr="Document: "+name+"\n";
		//TODO?
		retStr+=link;
		return retStr;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String getName() {
		return name;
	}
}

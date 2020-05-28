import java.util.Queue;
import java.util.Stack;

/*
 * SD2x Homework #2
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

public class HtmlValidator {
	
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
		Stack<HtmlTag> resStack = new Stack<HtmlTag>();
		int initialSizeTags = tags.size();
		System.out.println(initialSizeTags);
		for (int it = 0; it < initialSizeTags; it++) {
			if(tags.peek().isOpenTag()) {
				resStack.push(tags.poll());
			} else if (tags.peek().isSelfClosing() && resStack.empty() && tags.size() <= 1) {
				return null;
			} else if (!tags.peek().isSelfClosing()) {
				if(resStack.empty()) {
					return null;
				}
				HtmlTag tag = tags.poll();
				if (tag.matches(resStack.peek())) {
					resStack.pop();
				} else {
					return resStack;
				}
			}
		}
		return resStack;
	}
}


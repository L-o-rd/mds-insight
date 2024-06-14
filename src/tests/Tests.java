package tests;

import org.junit.*;

import com.insight.graphics.TextBox;

import static org.junit.Assert.assertEquals;

public class Tests {
	@Test
	public void shouldHaveText() {
		TextBox tb = new TextBox(0, 10, 6);
		tb.sb.append("test");
		assertEquals("TextBox text does not match.", "test", tb.sb.toString());
	}
}

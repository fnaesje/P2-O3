import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class FizzBuzzTest {

	@Test
	public void fizzbuzz_Given1_ShouldReturn1_AsString() {
		String result = FizzBuzz.fb(1);
		assertThat(result, equalTo("1"));
	}
	@Test
	public void fizzbuzz_Given2_ShouldReturn_2AsString() {
		String result = FizzBuzz.fb(2);
		assertThat(result, equalTo("2"));
	}
	@Test
	public void fizzbuzz_Given3_ShouldReturn_fizz() {
		String result = FizzBuzz.fb(3);
		assertThat(result, equalTo("fizz"));
	}

}

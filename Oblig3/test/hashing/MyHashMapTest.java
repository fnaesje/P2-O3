package hashing;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;

public class MyHashMapTest {
	MyHashMap<String, Integer> map;
	@Before
	public void setup() {
		map = new MyHashMap<>();
	    map.put("Smith", 30);
	    map.put("Anderson", 31);
	    map.put("Lewis", 29);
	    map.put("Cook", 29);
	    map.put("Smith", 430);
	}
	@Test
	public void getAllLookupSmithShouldReturnTwoOccurences() {
		java.util.Set<Integer> shouldBe = new java.util.HashSet<>();
		shouldBe.add(30);
		shouldBe.add(430);
		java.util.Set<Integer> actualResult = map.getAll("Smith");
		assertThat(actualResult,equalTo(shouldBe));
	}
	
	// ikke ferdig...
}

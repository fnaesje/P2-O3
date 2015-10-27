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
	public void getAll_LookupSmith_ShouldReturnTwoOccurences() {
		java.util.Set<Integer> shouldBe = new java.util.HashSet<>();
		shouldBe.add(30);
		shouldBe.add(430);
		java.util.Set<Integer> actualResult = map.getAll("Smith");
		assertThat(actualResult,equalTo(shouldBe));
	}
	
	@Test
	public void getAll_afterRemove_LookupSmithShouldReturnLastOccurence() {
		java.util.Set<Integer> shouldBe = new java.util.HashSet<>();
		shouldBe.add(430);
		map.remove("Smith");
		java.util.Set<Integer> actualResult = map.getAll("Smith");
		assertThat(actualResult,equalTo(shouldBe));
	}
	
	@Test
	public void getAll_afterRemoveBothSmiths_LookupSmithShouldReturnEmptySet() {
		map.remove("Smith");
		map.remove("Smith");
		java.util.Set<Integer> actualResult = map.getAll("Smith");
		assertThat(actualResult.isEmpty(), is(true));
	}
	
	
	
}

package graph;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.List;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import graph.AbstractGraph;
import graph.UnweightedGraph;

public class AbstractGraphTest {
	UnweightedGraph<String> graph1;
	UnweightedGraph<String> graph2;
	UnweightedGraph<String> graph3;
	String[] vertices1 = {"Seattle", "San Francisco", "Los Angeles", "Denver", "Kansas City", "Chicago"};
	int[][] edges1 = {
		      {0, 1}, {0, 3}, {0, 5},
		      {1, 0}, {1, 2}, {1, 3},
		      {2, 1}, {2, 3}, {2, 4},
		      {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
		      {4, 2}, {4, 3}, {4, 5},
		      {5, 0}, {5, 3}, {5, 4},
		     
		    };
	String[] vertices2 = {"Seattle", "San Francisco"};
	int[][] edges2= {
		      {0, 1},
		      {1, 0},
		      
		    };
	
	String[] vertices3 = {"Seattle", "San Francisco", "Los Angeles", "Denver"};
	int[][] edges3= {
		      {0, 1},
		      {1, 0},
		      {2, 3},
		      {3, 2},		      
		    };  // disconnected..

	@Before
	public void setUp() throws Exception {
		graph1 = new UnweightedGraph<>(vertices1, edges1);
		graph2 = new UnweightedGraph<>(vertices2, edges2);
		graph3 = new UnweightedGraph<>(vertices3, edges3);
	}

	@Test
	public void dfs_StartFromChicago_ShouldGiveKansasCityAsLast() {
		List<Integer> searchOrdersResult = new ArrayList<>();
		searchOrdersResult.add(5);
		searchOrdersResult.add(0);
		searchOrdersResult.add(1);
		searchOrdersResult.add(2);
		searchOrdersResult.add(3);
		searchOrdersResult.add(4);
		AbstractGraph<String>.Tree dfs = 
			      graph1.dfs(graph1.getIndex("Chicago"));
		java.util.List<Integer> searchOrders = dfs.getSearchOrder();
		assertThat(searchOrders, equalTo(searchOrdersResult));
	}
	
	@Test
	public void dfs_Disconnected_ShouldOnlyTraverseSeattleSanFrancisco() {
		List<Integer> searchOrdersResult = new ArrayList<>();
		searchOrdersResult.add(0);
		searchOrdersResult.add(1);
		
		AbstractGraph<String>.Tree dfs = 
			      graph3.dfs(graph3.getIndex("Seattle"));
		java.util.List<Integer> searchOrders = dfs.getSearchOrder();
		assertThat(searchOrders, equalTo(searchOrdersResult));
	}
	
	@Test
	public void getPath_StartFromSeattleEndChicago_ShouldVisitOnlyThose() {
		List<Integer> result = new ArrayList<>();
		result.add(5);
		result.add(0);
				
		List<Integer> path = graph1.getPath(0,5);
		assertThat(path, equalTo(result));
	}
	
	@Test
	public void getPath_StartFromSFEndChicago_HasTwoPossiblePaths() {
		List<Integer> result1 = new ArrayList<>();
		result1.add(5);
		result1.add(3);
		result1.add(1);
		List<Integer> result2 = new ArrayList<>();
		result2.add(5);
		result2.add(0);
		result2.add(1);
						
		List<Integer> path = graph1.getPath(1,5);
		assertThat(path, anyOf(equalTo(result1), equalTo(result2)));
	}
	
	@Test
	public void getPath_StartAndEndInDiconnectedGraph_ShouldReturnNull() {
		List<Integer> path = graph3.getPath(0,3);
		assertThat(path, equalTo(null));
	}
	
	@Test
	public void isCyclic_complexGraphWithManyCyclesShouldReturnTrue() {
		assertThat(graph1.isCyclic(), is(true));
	}
	
	@Test
	public void isCyclic_verySimpleGraphWithNoCyclesShouldReturnFalse() {
		assertThat(graph2.isCyclic(), is(false));
	}
	
	@Test
	public void isCyclic_SimpleGraphWithCyclesShouldReturnTrue() {
		graph2.addVertex("Los Angeles");
		graph2.addEdge(0, 2);
		graph2.addEdge(1, 2);
		graph2.addEdge(2, 0);
		graph2.addEdge(2, 1);
		
		assertThat(graph2.isCyclic(), is(true));
	}
	
	@Test
	public void isCyclic_SimpleGraphWithNOCyclesShouldReturnFalse() {
		graph2.addVertex("Los Angeles");
		graph2.addEdge(0, 2);
		graph2.addEdge(2, 0);
				
		assertThat(graph2.isCyclic(), is(false));
	}
	
	@Test
	public void isConnected_ConnectedGraphShouldReturnTrue() {
			assertThat(graph1.isConnected(), is(true));
	}
	
	@Test
	public void isConnected_DisConnectedGraphShouldReturnFalse() {			
		assertThat(graph3.isConnected(), is(false));
	}
	
}

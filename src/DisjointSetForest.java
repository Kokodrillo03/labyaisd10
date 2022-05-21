
public class DisjointSetForest implements DisjointSetDataStructure {

	private class Element{
		int rank;
		int parent;
	}

	Element []arr;

	public DisjointSetForest(int size) {
		//TODO
		this.arr = new Element[size];
		for (int i = 0; i<size; i++) {
			makeSet(i);
		}
	}

	@Override
	public void makeSet(int item) {
		//TODO
		arr[item] = new Element();
		arr[item].rank = 0;
		arr[item].parent = item;

	}

	@Override
	public int findSet(int item) {
		//TODO
		if (item < 0 || item > arr.length)return -1;

		if (item != arr[item].parent)
			arr[item].parent = findSet(arr[item].parent);
		return arr[item].parent;
	}

	@Override
	public boolean union(int itemA, int itemB) {
		//TODO
		if (findSet(itemA) == findSet(itemB))
			return false;
		return link(findSet(itemA), findSet(itemB));
	}

	@Override
	public int countSets() {
		return 0;
	}

	public boolean link(int x, int y){
		if(arr[x].rank > arr[y].rank)
			arr[y].parent = x;
		else{
			arr[x].parent = y;
			if (arr[x].rank == arr[y].rank)
				arr[y].rank++;
		}
		return true;
	}


	@Override
	public String toString() {
		//TODO
		String tostr = "Disjoint sets as forest:";
		for (int i = 0; i<arr.length; i++){
			tostr += "\n" + i + " -> " + arr[i].parent;
		}
		return tostr;
	}
}
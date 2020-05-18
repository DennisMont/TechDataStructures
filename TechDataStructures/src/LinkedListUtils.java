import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class LinkedListUtils {
	
	public static void insertSorted(LinkedList<Integer> list, int value) {
		if (list == null) {return;}
		else if (list.isEmpty()) {
			list.addFirst(value);
			return;
		}
		int index = 0;
		while(value > list.get(index)) {
			if (list.size() <= index + 1) {
				list.add(value);
				return;
			} else {
				index++;
			}
		}
		list.add(index, value);
	}
	

	public static void removeMaximumValues(LinkedList<String> list, int N) {
		// Lista vacia o null, N 0 o negativo
		if(list == null || N <= 0 || list.isEmpty()) {
			return;
		}
		//Elementos a borrar mayor al tamaño de la lista
		if (list.size() <= N) {
			list.clear();
			return;
		}
		//Copiar llinkedlist a otro LlinkedList para modificar
		LinkedList<String> listToSort = new LinkedList<String>();
		listToSort.addAll(list);
		//Ordenar por numero de caracteres, de menor a mayor
		Collections.sort(listToSort, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
		});
		//De la lista ordenada, añadir elementos a arraylist eliminando repetidos
		ArrayList<String> elementsToDelete = new ArrayList<String>();
		for(String element : listToSort) {
			if (!elementsToDelete.contains(element)) {
				elementsToDelete.add(element);
			}
		}
		//De arraylist, eliminar los elementos que no seran borados de LinkedList
		int iterations = elementsToDelete.size() - N;
		for (int i = 0; i < iterations; i++) {
			elementsToDelete.remove(0);
		}
		//De LinkedList original, quitar los elementos incluidos en arraylist.
		list.removeAll(elementsToDelete);
	}
	
	public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {
		if (one == null || one.isEmpty() || two == null || two.isEmpty()) return false;
		if(one.size() < two.size()) {
			return false;
		} else if (one.size() == two.size()) {
			return one.equals(two);
		} else if (one.contains(two.peekFirst())) {
			int index = one.indexOf(two.peekFirst());
			return subsequenceValidation(one, two, index);
		}
		return false;
	}
	
	public static boolean subsequenceValidation(LinkedList<Integer> seq, LinkedList<Integer> subSeq, int indexSeq) {
		if(seq.size()-indexSeq < subSeq.size()) {		//Si el tamaño de la posible sequencia es menor al de la subsequencia.
			return false;
		} else {
			//Iterar n veces eltamaño de la subsequencia
			for (int indexSubSeq = 0; indexSubSeq < subSeq.size(); indexSubSeq++) {
				if (seq.get(indexSeq) != subSeq.get(indexSubSeq)) {
					return false;
				} else {
					indexSeq++;
				}
			}
			return true;
		}
	}
}

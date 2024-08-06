package a01a.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SubsequenceCombinerFactoryImpl implements SubsequenceCombinerFactory {

    @Override
    public SubsequenceCombiner<Integer, Integer> tripletsToSum() {
        return new SubsequenceCombiner<Integer, Integer>() {

            @Override
            public List<Integer> combine(List<Integer> list) {
                List<Integer> finalList = new ArrayList<>();
                for (int i = 0; i < list.size(); i = i + 3) {
                    int sum = 0;
                    for (int j = i; j < i + 3; j++) {
                        if (j < list.size()) {
                            sum += list.get(j);
                        }
                    }
                    finalList.add(sum);
                }
                return finalList;
            }

        };
    }

    @Override
    public <X> SubsequenceCombiner<X, List<X>> tripletsToList() {
        return new SubsequenceCombiner<X, List<X>>() {

            @Override
            public List<List<X>> combine(List<X> list) {
                List<List<X>> finalList = new ArrayList<>();
                for (int i = 0; i < list.size(); i = i + 3) {
                    List<X> acc = new ArrayList<>();
                    for (int j = i; j < i + 3; j++) {
                        if (j < list.size()) {
                            acc.add(list.get(j));
                        }
                    }
                    finalList.add(acc);
                }
                return finalList;
            }

        };
    }

    @Override
    public SubsequenceCombiner<Integer, Integer> countUntilZero() {
        return new SubsequenceCombiner<Integer, Integer>() {

            @Override
            public List<Integer> combine(List<Integer> list) {
                List<Integer> finalList = new ArrayList<>();
                int count = 0; // Variabile per contare il numero di elementi tra gli zeri

                for (var elem : list) { // per ogni elem di list

                    if (elem.equals(0) || elem.equals(list.get(list.size() - 1))) {// Se l'elemento corrente è uno zero
                                                                                   // o l'ultimo elemento della lista
                        if (elem.equals(list.get(list.size() - 1)) && elem != 0) {
                            count++;
                        }
                        finalList.add(count);
                        count = 0;
                    } else {
                        count++;
                    }
                }
                return finalList;
            }

        };
    }

    @Override
    public <X, Y> SubsequenceCombiner<X, Y> singleReplacer(Function<X, Y> function) {
        return new SubsequenceCombiner<X, Y>() {

            @Override
            public List<Y> combine(List<X> list) {
                List<Y> finaList = new ArrayList<>();
                for (var el : list) {
                    finaList.add(function.apply(el));
                }
                return finaList;

            }

        };

    }

    @Override
    public SubsequenceCombiner<Integer, List<Integer>> cumulateToList(int threshold) {
        return new SubsequenceCombiner<Integer, List<Integer>>() {

            @Override
            public List<List<Integer>> combine(List<Integer> list) {
                List<List<Integer>> finalList = new ArrayList<>();
                int count = 0;
                List<Integer> acc = new ArrayList<>();
                for (var el : list) {
                    // Se la somma corrente è maggiore o uguale alla soglia o è l'ultimo
                    // elemento(come da consegna)
                    if (count >= 100 || el.equals(list.get(list.size() - 1))) {
                        finalList.add(acc);
                        count = 0; // azzero il count
                        acc = new ArrayList<>(); // azzero la lista
                    }
                    acc.add(el);// Aggiunge l'elemento corrente alla lista temporanea
                    count += el; // Aggiunge l'elemento corrente alla somma
                }
                finalList.add(acc);
                return finalList;
            }

        };
    }

}

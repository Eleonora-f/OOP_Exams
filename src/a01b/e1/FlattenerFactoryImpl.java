package a01b.e1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlattenerFactoryImpl implements FlattenerFactory {
    // FATTO
    @Override
    public Flattener<Integer, Integer> sumEach() {
        return new Flattener<Integer, Integer>() {

            @Override
            public List<Integer> flatten(List<List<Integer>> list) {
                List<Integer> finalList = new ArrayList<>();
                for (var el : list) {
                    int sum = 0;
                    for (var e : el) {// sfoglio gli elementi contenuti nelle sottoliste
                        sum += e;
                    }
                    finalList.add(sum);
                }
                return finalList;
            }

        };
    }

    @Override
    public <X> Flattener<X, X> flattenAll() {
        return new Flattener<X, X>() {

            @Override
            public List<X> flatten(List<List<X>> list) {
                List<X> finalList = new ArrayList<>();
                for (var el : list) {
                    finalList.addAll(el);
                }
                return finalList;
            }

        };
    }

    @Override
    public Flattener<String, String> concatPairs() {
        return new Flattener<String, String>() {

            @Override
            public List<String> flatten(List<List<String>> list) {
                List<String> finalList = new ArrayList<>();
                for (int i = 0; i < list.size(); i = i + 2) {
                    if (i < list.size() - 2) {
                        var c = Stream.concat(list.get(i).stream(), list.get(i + 1).stream())
                                .collect(Collectors.toList());
                        finalList.add(c.toString());
                    } else {
                        finalList.add(list.get(i).toString());
                    }

                }
                return finalList;
            }
        };
    }

    @Override
    public <I, O> Flattener<I, O> each(Function<List<I>, O> mapper) {
        return new Flattener<I, O>() {

            @Override
            public List<O> flatten(List<List<I>> list) {
                List<O> finalList = new ArrayList<>();
                for (var el : list) {
                    finalList.add(mapper.apply(el));
                }
                return finalList;
            }

        };
    }

    @Override
    public Flattener<Integer, Integer> sumVectors() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sumVectors'");
    }

}

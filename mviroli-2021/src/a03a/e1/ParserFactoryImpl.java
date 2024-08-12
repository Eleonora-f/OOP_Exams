package a03a.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class ParserFactoryImpl implements ParserFactory {
    // FATTO
    @Override
    public <X> Parser<X> fromFinitePossibilities(Set<List<X>> acceptedSequences) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                List<X> list = new ArrayList<>();
                while (iterator.hasNext()) {
                    list.add(iterator.next());
                }
                for (var el : acceptedSequences) {
                    if (el.equals(list)) {
                        return true;
                    }
                }
                return false;
            }

        };
    }

    @Override
    public <X> Parser<X> fromGraph(X x0, Set<Pair<X, X>> transitions, Set<X> acceptanceInputs) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                for (var tr : transitions) {
                    while (iterator.hasNext()) {
                        acceptanceInputs.add(iterator.next());
                    }
                    if (tr.equals(acceptanceInputs)) {
                        return true;
                    }
                }
                return false;

            }

        };
    }

    @Override
    public <X> Parser<X> fromIteration(X x0, Function<X, Optional<X>> next) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                return false;
            }

        };
    }

    @Override
    public <X> Parser<X> recursive(Function<X, Optional<Parser<X>>> nextParser, boolean isFinal) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                if (!iterator.hasNext()) { // se l'iteratore non ha più elementi, allora metto il boolean
                    return isFinal;
                }
                return nextParser.apply(iterator.next()).map(p -> p.accept(iterator)).orElse(false);
                // se l'optional contiene un val(parser)->applica la funz. map, la map chiama
                // ricorsivamente il metodo accept
            }

        };
    }

    @Override
    public <X> Parser<X> fromParserWithInitial(X x, Parser<X> parser) {
        return recursive(i -> i.equals(x) ? Optional.of(parser) : Optional.empty(), false); // se i è = a x allora
                                                                                            // restituisce il parser
                                                                                            // dato, altrimenti un
                                                                                            // optional empty
        // false indica che una sequenza vuota non è considerata valida per questo
        // parser
    }

}

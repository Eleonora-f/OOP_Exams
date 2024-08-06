package a03b.e1;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class LazyTreeFactoryImpl implements LazyTreeFactory {

    @Override
    public <X> LazyTree<X> constantInfinite(X value) {
        return new LazyTree<X>() {

            private LazyTree<X> left;
            private LazyTree<X> right;

            @Override
            public boolean hasRoot() {
                return true;
            }

            @Override
            public X root() {
                return value;
            }

            @Override
            public LazyTree<X> left() {
                if (left == null) {
                    left = constantInfinite(value);
                }
                return left;
            }

            @Override
            public LazyTree<X> right() {
                if (right == null) {
                    right = constantInfinite(value);
                }
                return right;
            }

        };

    }

    @Override
    public <X> LazyTree<X> fromMap(X root, Map<X, Pair<X, X>> map) {
        return new LazyTree<X>() {

            @Override
            public boolean hasRoot() {
                return true;
            }

            @Override
            public X root() {
                return root;
            }

            @Override
            public LazyTree<X> left() {
                if (map.get(root).getX() == null) { // root è la key per ottenere la coppia di figli e con .getX ottengo
                    // il figlio sinistro
                    throw new NoSuchElementException();
                }
                return fromMap(map.get(root).getX(), map); // se il figlio sinistro esiste creo un nuovo albero con getX
                                                           // come nuova radice e la stessa mappa per definire i suoi
                                                           // figli

            }

            @Override
            public LazyTree<X> right() {
                if (map.get(root).getY() == null) { // root è la key per ottenere la coppia di figli e con .getY ottengo
                                                    // il figlio destro
                    throw new NoSuchElementException();
                }
                return fromMap(map.get(root).getY(), map);
            }

        };
    }

    @Override
    public <X> LazyTree<X> cons(Optional<X> root, Supplier<LazyTree<X>> leftSupp, Supplier<LazyTree<X>> rightSupp) {
        return new LazyTree<X>() {

            @Override
            public boolean hasRoot() {
                return root.isPresent();
            }

            @Override
            public X root() {
                /*
                 * if (root == null) {
                 * throw new NoSuchElementException();
                 * }
                 */
                return root.get();
            }

            @Override
            public LazyTree<X> left() {
                return leftSupp.get();
            }

            @Override
            public LazyTree<X> right() {
                return rightSupp.get();
            }

        };
    }

    @Override
    public <X> LazyTree<X> fromTwoIterations(X root, UnaryOperator<X> leftOp, UnaryOperator<X> rightOp) {
        return new LazyTree<X>() {

            @Override
            public boolean hasRoot() {
                return true;
            }

            @Override
            public X root() {
                return root;
            }

            @Override
            public LazyTree<X> left() {
                return fromTwoIterations(leftOp.apply(root), leftOp, rightOp);
            }

            @Override
            public LazyTree<X> right() {
                return fromTwoIterations(rightOp.apply(root), leftOp, rightOp);
            }

        };

    }

    @Override
    public <X> LazyTree<X> fromTreeWithBound(LazyTree<X> tree, int bound) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromTreeWithBound'");
    }

}

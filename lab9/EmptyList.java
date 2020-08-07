import java.util.function.Supplier; 
import java.util.function.BiFunction; 
import java.util.function.Consumer; 
import java.util.function.Function;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.Optional;
import java.util.function.UnaryOperator;

class EmptyList<T> extends InfiniteListImpl<T> {
    EmptyList() {
        super(null, null);
    }

    @Override 
    public <R> InfiniteListImpl<R> map(Function<? super T, ? extends R> mapper) {
        return new EmptyList<R>(); 
    }

    @Override 
    public InfiniteListImpl<T> filter(Predicate<? super T> predicate) {
        return this;
    }

    @Override 
    public InfiniteListImpl<T> takeWhile(Predicate<? super T> predicate) {
        return this;
    }
}

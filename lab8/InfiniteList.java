import java.util.function.Supplier;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Consumer; 
import java.util.function.Predicate;
import java.util.function.BinaryOperator;

interface InfiniteList<T> {
    public static <T> InfiniteList<T> generate(Supplier<? extends T> supplier) {
        return InfiniteListImpl.generate(supplier); 
    }

    public static <T> InfiniteList<T> iterate(T seed, Function<T, T> func) {
        return InfiniteListImpl.iterate(seed, func);
    }

    public InfiniteList<T> limit(long maxSize);

    public void forEach(Consumer<? super T> action);

    public <S> InfiniteList<S> map(Function<? super T, ? extends S> mapper);

    public InfiniteList<T> takeWhile(Predicate<? super T> predicate);
    
    public InfiniteList<T> filter(Predicate<? super T> predicate);

    public Optional<T> reduce(BinaryOperator<T> accumulator); 

    public T reduce(T identity, BinaryOperator<T> accumulator);
    
    public long count();
    
    public Object[] toArray(); 

    Optional<T> get(); 
}

import java.util.function.Supplier; 
import java.util.function.BiFunction; 
import java.util.function.Consumer; 
import java.util.function.Function;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.ArrayList;

class InfiniteListImpl<T> implements InfiniteList<T> {
    private final Lazy<T> head; 
    private final Supplier<InfiniteListImpl<T>> tail; 

    protected InfiniteListImpl(Lazy<T> head, Supplier<InfiniteListImpl<T>> tail) {
        this.head = head; 
        this.tail = tail; 
    }

    public static <T> InfiniteListImpl<T> generate(Supplier<? extends T> s) {
        Lazy<T> temp = Lazy.generate(s);
        return new InfiniteListImpl<T>(temp, () -> InfiniteListImpl.generate(s));    
    }

    public static <T> InfiniteListImpl<T> iterate(T seed, UnaryOperator<T> next) { 
        Lazy<T> temp = Lazy.generate(() -> seed);
        return new InfiniteListImpl<T>(
                temp, 
                () -> InfiniteListImpl.iterate(next.apply(seed), next));
    }

    public <R> InfiniteListImpl<R> map(Function<? super T, ? extends R> mapper) {
        return new InfiniteListImpl<R>(head.map(mapper), () -> tail.get().map(mapper));
    }

    public InfiniteListImpl<T> filter(Predicate<? super T> predicate) {
        if (this.isEmpty()) {
            return new EmptyList<T>();
        } else {
            return new InfiniteListImpl<T>(head.filter(predicate), () -> tail.get().filter(predicate));
        }
    }

    public InfiniteListImpl<T> limit(long n) { 
        if (n < 1 || this.isEmpty()) {
            return new EmptyList<T>();
        }
        return new InfiniteListImpl<T>(head, () -> {
                if (head.get().isEmpty()) {
                    return tail.get().limit(n);
                } else {
                    if (n == 1) { 
                        return new EmptyList<T>();
                    } else {
                        return tail.get().limit(n - 1);
                    }   
                }
            });

    }

    public InfiniteListImpl<T> takeWhile(Predicate<? super T> predicate) {
        if (this.isEmpty()) {
            return new EmptyList<T>();
        }
        Lazy<T> newHead = head.filter(predicate);
        return new InfiniteListImpl<T>(newHead, () -> {
                if (head.get().isPresent()) {
                    if (newHead.get().isPresent()) {
                        return tail.get().takeWhile(predicate);
                    }
                    return new EmptyList<T>();
                } else {
                    return tail.get().takeWhile(predicate);
                }
            });
    }

    public Object[] toArray() {
        ArrayList<T> array = new ArrayList<>();
        InfiniteListImpl<T> current = this;
        while(!current.isEmpty()) {
            Optional<T> curr = current.head.get(); 
            if (curr.isPresent()) {
                array.add(curr.get());
            } 
            current = current.tail.get();
        }
        return array.toArray();
    }

    public long count() {
        long counter = 0; 
        InfiniteListImpl<T> current = this; 
        while(!current.isEmpty()) {
            Optional<T> curr = current.head.get(); 
            if(curr.isPresent()) {
                counter++; 
            }
            current = current.tail.get();
        }
        return counter;
    }

    public <U> U reduce (U identity, BiFunction<U, ? super T, U> accumulator) {
        U temp = identity; 
        InfiniteListImpl<T> current = this; 
        while(!current.isEmpty()) {
            Optional<T> curr = current.head.get(); 
            if(curr.isPresent()) {
                temp = accumulator.apply(temp, curr.get());
            }
            current = current.tail.get(); 
        }
        return temp;
    }

    public void forEach(Consumer<? super T> action) {
        InfiniteListImpl<T> current = this; 
        while(!current.isEmpty()) {
            Optional<T> curr = current.head.get();
            curr.ifPresent(action); 
            current = current.tail.get();
        }
    }

    public InfiniteListImpl<T> peek() {
        if (this.isEmpty()) {
            return new EmptyList<T>();
        }
        Optional<T> curr = head.get();
        if (curr.isPresent()) {
            System.out.println(curr.orElseThrow().toString());
        }
        return this.tail.get();
    }

    public boolean isEmpty() {
        if (this instanceof EmptyList) {
            return true; 
        }
        return false;
    }
}

import java.util.function.Supplier;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Consumer; 
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.BinaryOperator;

abstract class InfiniteListImpl<T> implements InfiniteList<T> { 
    public static <T> InfiniteListImpl<T> generate(Supplier<? extends T> s) {
        return new InfiniteListImpl<T>() { 
            public Optional<T> get() {
                return Optional.of(s.get());
            }
        };
    }

    public static <T> InfiniteListImpl<T> iterate(T seed, Function<T, T> func) {
        return new InfiniteListImpl<T>() {
            private T element = seed; 
            private boolean firstElement = true; 
            public Optional<T> get() {
                if(firstElement) {
                    firstElement = false; 
                } else { 
                    element = func.apply(element); 
                }
                return Optional.of(element); 
            }
        }; 
    }

    public void forEach(Consumer<? super T> action) {
        boolean checker = true; 
        while (checker) {
            Optional<T> temp = get(); 
            if(temp.isEmpty()) {
                checker = false;
            } else { 
                temp.ifPresent(action);  
            }
        }
    }

    public <S> InfiniteList<S> map(Function<? super T, ? extends S> mapper) {
        return new InfiniteListImpl<S>() {
            public Optional<S> get() {
                Optional<T> curr = InfiniteListImpl.this.get();
                return curr.map(mapper);
            }
        };
    }

    public InfiniteList<T> filter(Predicate<? super T> predicate) {
        return new InfiniteListImpl<>() {
            public Optional<T> get() {
                Optional<T> curr = InfiniteListImpl.this.get(); 
                while(curr.isPresent()) {
                    if(predicate.test(curr.get())) {
                        return curr;
                    } else {
                        curr = InfiniteListImpl.this.get();
                    }
                }
                return curr;
            }
        };
    }

    public InfiniteList<T> takeWhile(Predicate<? super T> predicate) {
        return new InfiniteListImpl<>() {
            public Optional<T> get() {
                Optional<T> curr = InfiniteListImpl.this.get(); 
                while(curr.isPresent()) {
                    if(predicate.test(curr.get())) {
                        return curr; 
                    } else { 
                        curr = Optional.empty(); 
                    }
                }
                return curr; 
            }
        };
    }

    public InfiniteList<T> limit(long maxSize) {
        if (maxSize < 0) {
            throw new IllegalArgumentException(Long.toString(maxSize)); 
        }
        return new InfiniteListImpl<>() {
            private long counter = maxSize;
            public Optional<T> get() {
                if (counter == 0) {
                    return Optional.empty(); 
                } else { 
                    counter--; 
                    return InfiniteListImpl.this.get();
                }
            }
        };
    }

    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        Optional<T> curr = InfiniteListImpl.this.get(); 
        Optional<T> next = InfiniteListImpl.this.get(); 
        if(curr.isEmpty() || next.isEmpty()) {
            return Optional.empty();
        }
        while(next.isPresent()) {
            curr = Optional.of(accumulator.apply(curr.get(), next.get()));
            next = InfiniteListImpl.this.get();
        }
        return curr;
    }

    public T reduce(T identity, BinaryOperator<T> accumulator) {
        T sum = identity; 
        Optional<T> curr = InfiniteListImpl.this.get();
        while(curr.isPresent()) {
            sum = accumulator.apply(sum, curr.get());
            curr = InfiniteListImpl.this.get(); 
        }
        return sum; 
    }

    public long count() {
        Optional<T> curr = InfiniteListImpl.this.get(); 
        long counter = 0; 
        while(curr.isPresent()) {
            counter++; 
            curr = InfiniteListImpl.this.get();
        }
        return counter; 
    }

    public Object[] toArray() {
        ArrayList<Object> array = new ArrayList<>(); 
        Optional<T> current = get(); 
        while (current.isPresent()) {
            array.add(current.get());
            current = get();
        }
        return array.toArray();
    }
}

import java.util.Optional; 
import java.util.function.Function; 
import java.util.function.Supplier;
import java.util.function.Predicate; 

class Lazy<T> { 
    private T v;
    final private Supplier<? extends T> supplier;
    private boolean nullVariable;

    Lazy(T v) {
        this.v = v;
        this.supplier = () -> v;
        this.nullVariable = v == null ? true : false; 
    }

    Lazy(Supplier<? extends T> supplier) {
        this.v = null; 
        this.supplier = supplier;
        this.nullVariable = false; 
    }

    public static <T> Lazy<T> ofNullable(T v) {
         return new Lazy<T>(v); 
    }

    public static <T> Lazy<T> generate(Supplier<? extends T> supplier) {
        return new Lazy<>(supplier); 
    }

    public Optional<T> get() {
        if (v == null && !nullVariable) {
            T temp = supplier.get();
            if (temp == null) {
                nullVariable = true; 
            }
            v = temp; 
        } 
        return Optional.ofNullable(v);
    }

    public <R> Lazy<R> map(Function<? super T, ? extends R> mapper) {    
        return Lazy.generate(() -> this.get().map(mapper).orElse(null));
    }

    public Lazy<T> filter(Predicate<? super T> predicate) {
        return Lazy.generate(() -> this.get().filter(predicate).orElse(null));
    }

    public String toString() {
        if (nullVariable) {
            return "null"; 
        }
        if (v == null) {
            return "?";
        } else {
            return v.toString(); 
        }
    }       
}

import java.util.ArrayList;
import java.util.List; 
import java.util.function.Predicate;
import java.util.function.Function; 
import java.io.File;
import java.util.Collections;
import java.util.Comparator;

class ImmutableList<T> {
    private final List<T> immutableList;
    
    public ImmutableList(List<T> list) {
        List<T> newArray = new ArrayList<T>(list);
        this.immutableList = newArray; 
    }

    @SafeVarargs
    public ImmutableList(T...args) { 
        List<T> newArray = new ArrayList<T>(); 
        for (int i = 0; i < args.length; i++) {
            newArray.add(args[i]); 
        }
        this.immutableList = newArray; 
    }

    public ImmutableList<T> remove(T num) { 
        List<T> newArray = new ArrayList<T>(immutableList);
        newArray.remove(num); 
        return new ImmutableList<T>(newArray);  
    }

    public ImmutableList<T> add(T num) { 
        List<T> newArray = new ArrayList<T>(immutableList);
        newArray.add(num); 
        return new ImmutableList<T>(newArray); 
    }

    public ImmutableList<T> replace(T first, T second) {
        List<T> newArray = new ArrayList<T>(immutableList); 
        for (int i = 0; i < immutableList.size(); i++) {
            if (newArray.get(i) == first) {
                newArray.set(i, second); 
            }
        } 
        return new ImmutableList<T>(newArray); 
    }

    public ImmutableList<T> filter(Predicate<? super T> pred) {
        List<T> newArray = new ArrayList<T>(immutableList);
        List<T> newArray2 = new ArrayList<T>(); 
        for (T item: immutableList) {
            if (pred.test(item)) { 
                newArray2.add(item); 
            }
        }
        return new ImmutableList<T>(newArray2); 
    }

    public <U> ImmutableList<U> map(Function<? super T, ? extends U> func) { 
        List<U> newArray = new ArrayList<>(); 
        for (T item : immutableList) {
            newArray.add(func.apply(item)); 
        }
        return new ImmutableList<U>(newArray); 
    }
 
    public ImmutableList<T> sorted(Comparator<? super T> comparator) {
        if (comparator == null) {
            throw new NullPointerException("Comparator is null"); 
        }
        List<T> newArray = new ArrayList<T>(immutableList);
        for(T item : immutableList) {
            if (!(item instanceof Comparable)) { 
                throw new IllegalStateException("List elements do not implement Comparable"); 
            } 
        }
        Collections.sort(newArray, comparator); 
        return new ImmutableList<T>(newArray); 
    }
    
    public ImmutableList<T> sorted() {
        for (T item : immutableList) {
            if(!(item instanceof Comparable)) {
                throw new IllegalStateException("List elements do not implement Comparable");
            }
        }
        List<T> newArray = new ArrayList<T>(immutableList);
        @SuppressWarnings("unchecked")
        Comparator<T> compare = (Comparator<T>) Comparator.naturalOrder();
        newArray.sort((compare));
        return new ImmutableList<T>(newArray);
    }

    public ImmutableList<T> limit(long num) {  
        if (num < 0) { 
            throw new IllegalArgumentException("limit size < 0");
        } else if (num >= immutableList.size()) {
            return new ImmutableList<T>(new ArrayList<T>(immutableList));
        } else { 
            List<T> newArray = new ArrayList<>(); 
            for (int i = 0; i < num; i++) { 
                newArray.add(immutableList.get(i));
            }
            return new ImmutableList<T>(newArray); 
        }
    }

    public Object[] toArray() { 
        List<T> newArray = new ArrayList<T>(immutableList);
        return newArray.toArray();
    }

    public <U> U[] toArray(U[] array) {  
        List<T> newArray = new ArrayList<T>(immutableList);
        if (array == null) {
            throw new NullPointerException("Input array cannot be null"); 
        }
        try { 
            return newArray.toArray(array); 
        } catch (ArrayStoreException e) {
            throw new ArrayStoreException("Cannot add element to array as it is the wrong type");
        }
    }

    public String toString() {
        String str = ""; 
        for(int i = 0; i < immutableList.size(); i++) { 
            if (i == 0) { 
                str = str +  "[" + immutableList.get(i) + ", "; 
            } else if (i == immutableList.size() - 1) {
                str = str + immutableList.get(i) + "]"; 
            } else {
                str = str + immutableList.get(i) + ", ";
            }
        }
        if (immutableList.size() == 0) {
            return "[]";
        } else if (immutableList.size() == 1) {
            return "[" + immutableList.get(0) + "]"; 
        } else { 
            return str; 
        }
    }
}

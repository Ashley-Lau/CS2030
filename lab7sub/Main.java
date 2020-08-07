import java.util.stream.*; 
import java.util.Optional; 

class Main { 
    static boolean isPrime(int n) { 
        return IntStream.range(2,n).noneMatch(x -> n % x == 0);   
    }

    static int[] twinPrimes(int n) {
        final int[] array = 
        IntStream.rangeClosed(3,n).filter(x -> isPrime(x) && (isPrime(x + 2) || isPrime(x - 2))).toArray();
        return array;
    }

    static int gcd(int m, int n) { 
        return Stream.iterate(new Pair(m, n), 
                p -> p.min > 0, p -> new Pair(p.min, p.max % p.min)).mapToInt
                    (p -> p.min).sorted().findFirst().getAsInt();
    }

    @SafeVarargs
    static long countRepeats(int... array) { 
        final int[] arr = IntStream.rangeClosed(0, array.length - 2)
            .filter(x -> x > 0 && x < array.length - 1  
            ? array[x] == array[x + 1] && array[x] != array[x - 1]
            : array[x] == array[x + 1]).toArray(); 
        return arr.length; 
    }

    static double normalizedMean(Stream<Integer> stream) { 
        final double[] array = stream.mapToDouble(x -> (double) x).sorted().toArray(); 
        if (array.length <= 1) { 
            return (double) 0; 
        }
        final double max = array[array.length - 1]; 
        final double min = array[0];
        if (max == min) {
            return (double) 0; 
        }
        final double t = DoubleStream.of(array).sum(); 
        final double normalizedt = (t / (double) array.length - min) / ( max - min); 
        return normalizedt;
    }
}

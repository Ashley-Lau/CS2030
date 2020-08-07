/open Lazy.java
/open InfiniteList.java
/open InfiniteListImpl.java
/open EmptyList.java
UnaryOperator<Integer> op = x -> { System.out.printf("iterate: %d -> %d\n", x, x + 1); return x + 1; };
Predicate<Integer> truefilter = x -> {System.out.printf("filter: %d -> %b\n", x , true); return true;};
        System.out.println(InfiniteList.iterate(0, op).filter(truefilter).limit(4).count());
Predicate<Integer> divby2 = x -> { System.out.printf("filter: %d -> %b\n", x, x % 2 == 0); return x % 2 == 0;};
        Predicate<Integer> lessThan10 = x -> { System.out.printf("takeWhile: %d -> %b\n", x, x < 10); return x < 10;};
        System.out.println(InfiniteList.iterate(0, op).filter(divby2).takeWhile(lessThan10).count());

/exit
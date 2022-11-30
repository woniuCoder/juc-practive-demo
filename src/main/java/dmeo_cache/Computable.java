package dmeo_cache;

public interface Computable<A,V> {

    V compute(A arg) throws Exception;

}

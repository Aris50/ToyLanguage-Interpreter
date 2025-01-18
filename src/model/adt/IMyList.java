package model.adt;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface IMyList<T> {
    List<T> getAll();
    void add(T item);

    Stream<T> stream();
}

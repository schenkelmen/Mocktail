package de.hsos.swa.shared.dal;

import de.hsos.swa.shared.Rezept;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class RezeptRepository {
    private final Map<Long, Rezept> rezepte = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Rezept> findAll() {
        return new ArrayList<>(rezepte.values());
    }

    public Optional<Rezept> findById(Long id) {
        return Optional.ofNullable(rezepte.get(id));
    }

    public Long create(Rezept rezept) {
        long id = idGenerator.getAndIncrement();
        rezept.setId(id);
        rezepte.put(id, rezept);
        return id;
    }

    public boolean delete(Long id) {
        return rezepte.remove(id) != null;
    }

    public Optional<Rezept> update(Long id, Rezept updated) {
        if (rezepte.containsKey(id)) {
            updated.setId(id);
            rezepte.put(id, updated);
            return Optional.of(updated);
        }
        return Optional.empty();
    }
}

package de.hsos.swa.menu.gateway;

import de.hsos.swa.menu.entity.Mocktail;
import de.hsos.swa.menu.entity.MocktailVerwalter;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.jboss.logging.Logger;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ApplicationScoped
public class MocktailRepository implements MocktailVerwalter {

    private static final Logger LOG = Logger.getLogger(MocktailRepository.class);

    private ConcurrentMap<String, Mocktail> mocktails = new ConcurrentHashMap<>();

    @Override
    @Retry(maxRetries = 3, delay = 200)
    @Timeout(2000)
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.5, delay = 5000)
    @Fallback(fallbackMethod = "fallbackAnlegenNeuMocktail")
    public String anlegenNeuMocktail(String name, String zutaten, String zubereitung) {
        String id = UUID.randomUUID().toString();
        Mocktail mocktail = new Mocktail(id, name, zutaten, zubereitung);
        this.mocktails.put(id, mocktail);
        LOG.info("Neuer Mocktail angelegt mit ID: " + id);
        return id;
    }

    public String fallbackAnlegenNeuMocktail(String name, String zutaten, String zubereitung) {
        LOG.error("Fallback: Mocktail konnte nicht angelegt werden.");
        return "fallback-id"; // Notlösung
    }

    @Override
    public boolean entfernen(String id) {
        try {
            Mocktail remove = this.mocktails.remove(id);
            if(remove == null) {
                LOG.warn("Mocktail zum Entfernen nicht gefunden: " + id);
                return false;
            }
            LOG.info("Mocktail erfolgreich entfernt: " + id);
            return true;
        } catch (Exception e) {
            LOG.error("Fehler beim Entfernen des Mocktails mit ID: " + id, e);
            return false;
        }
    }

    @Override
    public Optional<Mocktail> aendereZutaten(String id, String zutaten) {
        try {
            Optional<Mocktail> customerOptional = Optional.ofNullable(this.mocktails.get(id));
            if(customerOptional.isPresent()) {
                Mocktail customer = customerOptional.get();
                customer.setZutaten(zutaten);
                LOG.info("Zutaten geändert für Mocktail ID: " + id);
            }
            return customerOptional;
        } catch (IllegalArgumentException ie) {
            LOG.error("Fehler beim Ändern der Zutaten für Mocktail: " + id, ie);
            throw new IllegalArgumentException("Zutaten not updated.", ie);
        }
    }

    @Override
    public Optional<Mocktail> aendereZubereitung(String id, String zubereitung) {
        try {
            Optional<Mocktail> customerOptional = Optional.ofNullable(this.mocktails.get(id));
            if(customerOptional.isPresent()) {
                Mocktail customer = customerOptional.get();
                customer.setZubereitung(zubereitung);
                LOG.info("Zubereitung geändert für Mocktail ID: " + id);
            }
            return customerOptional;
        } catch (IllegalArgumentException ie) {
            LOG.error("Fehler beim Ändern der Zubereitung für Mocktail: " + id, ie);
            throw new IllegalArgumentException("Zubereitung not updated.", ie);
        }
    }

    @Override
    public Optional<Mocktail> findeMocktailMitId(String id) {
        Mocktail customer = this.mocktails.get(id);
        if (customer == null) {
            LOG.warn("Mocktail nicht gefunden bei ID: " + id);
        } else {
            LOG.info("Mocktail gefunden bei ID: " + id);
        }
        return Optional.ofNullable(customer);
    }

    @Override
    public Collection<Mocktail> alleMocktails() {
        LOG.info("Alle Mocktails werden abgefragt.");
        return Collections.unmodifiableCollection(this.mocktails.values());
    }
}

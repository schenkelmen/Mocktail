package de.hsos.swa.menu.gateway;

import de.hsos.swa.menu.entity.Mocktail;
import de.hsos.swa.menu.entity.MocktailVerwalter;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ApplicationScoped
public class MocktailRepository implements MocktailVerwalter {
    private ConcurrentMap<String, Mocktail> mocktails = new ConcurrentHashMap<>();

    @Override
    public String anlegenNeuMocktail(String name, String zutaten, String zubereitung) {
        String id = UUID.randomUUID().toString();
        Mocktail mocktail = new Mocktail(id, name, zutaten, zubereitung);
        this.mocktails.put(id, mocktail);

        return id;
    }

    @Override
    public boolean entfernen(String id) {
        try {
            Mocktail remove = this.mocktails.remove(id);
            if(remove == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Mocktail> aendereZutaten(String id, String zutaten) {
        try{
            Optional<Mocktail> customerOptional = Optional.ofNullable(this.mocktails.get(id));
            if(customerOptional.isPresent()) {
                Mocktail customer = customerOptional.get();
                customer.setZutaten(zutaten);
            }
    
            return customerOptional;    
        }catch(IllegalArgumentException ie) {
            throw new IllegalArgumentException("Zutaten not updated.", ie);
        }
        
    }

    @Override
    public Optional<Mocktail> aendereZubereitung(String id, String zubereitung) {
        try{
            Optional<Mocktail> customerOptional = Optional.ofNullable(this.mocktails.get(id));
            if(customerOptional.isPresent()) {
                Mocktail customer = customerOptional.get();
                customer.setZubereitung(zubereitung);
            }

            return customerOptional;
        }catch(IllegalArgumentException ie) {
            throw new IllegalArgumentException("Zubereitung not updated.", ie);
        }

    }

    @Override
    public Optional<Mocktail> findeMocktailMitId(String id) {
        Mocktail customer = this.mocktails.get(id);
        return Optional.ofNullable(customer);
    }

    @Override
    public Collection<Mocktail> alleMocktails() {
        return Collections.unmodifiableCollection(this.mocktails.values());
    }
}

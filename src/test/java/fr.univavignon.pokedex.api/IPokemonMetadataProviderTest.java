package fr.univavignon.pokedex.api;

import org.junit.Test;

import static org.junit.Assert.*;

public class IPokemonMetadataProviderTest {

    private final IPokemonMetadataProvider pkmDataProvider = new PokemonMetadataProvider();
    private final PokemonMetadata pokemonMetadata= new PokemonMetadata(133, "Aquali", 186, 168, 260);

    @Test
    public void shouldReturnAqualiMetadataWhenIndexIs133() throws PokedexException {
        PokemonMetadata pokemonMetadataTest = pkmDataProvider.getPokemonMetadata(133);
        assertEquals(pokemonMetadata.getIndex(), pokemonMetadataTest.getIndex());
        assertEquals(pokemonMetadata.getAttack(), pokemonMetadataTest.getAttack());
        assertEquals(pokemonMetadata.getDefense(), pokemonMetadataTest.getDefense());
        assertEquals(pokemonMetadata.getStamina(), pokemonMetadataTest.getStamina());
        assertEquals(pokemonMetadata.getName(), pokemonMetadataTest.getName());
    }

    @Test
    public void shouldRaisePokedexExceptionWhenBadIndex() {
        assertThrows(PokedexException.class, () -> pkmDataProvider.getPokemonMetadata(5));
    }

    @Test
    public void shouldReturnAqualiAttackValue(){
        assertEquals(186, pokemonMetadata.getAttack());
    }

    @Test
    public void shouldReturnAqualiDefenseValue(){
        assertEquals(168, pokemonMetadata.getDefense());
    }

    @Test
    public void shouldReturnAqualiStaminaValue(){
        assertEquals(260, pokemonMetadata.getStamina());
    }

}

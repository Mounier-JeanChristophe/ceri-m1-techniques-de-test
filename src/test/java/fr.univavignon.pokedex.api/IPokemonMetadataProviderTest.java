package fr.univavignon.pokedex.api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IPokemonMetadataProviderTest {

    private final IPokemonMetadataProvider pkmDataProvider = new PokemonMetadataProvider();
    private final PokemonMetadata pokemonMetadata= new PokemonMetadata(133, "Aquali", 186, 168, 260);

    @Test
    public void getPokemonMetadataTest() throws PokedexException {
        PokemonMetadata pokemonMetadataTest = pkmDataProvider.getPokemonMetadata(133);
        assertEquals(pokemonMetadata.getIndex(), pokemonMetadataTest.getIndex());
        assertEquals(pokemonMetadata.getAttack(), pokemonMetadataTest.getAttack());
        assertEquals(pokemonMetadata.getDefense(), pokemonMetadataTest.getDefense());
        assertEquals(pokemonMetadata.getStamina(), pokemonMetadataTest.getStamina());
        assertEquals(pokemonMetadata.getName(), pokemonMetadataTest.getName());
    }

    @Test
    public void getAttackTest(){
        assertEquals(186, pokemonMetadata.getAttack());
    }

    @Test
    public void getDefenseTest(){
        assertEquals(168, pokemonMetadata.getDefense());
    }

    @Test
    public void getStaminaTest(){
        assertEquals(260, pokemonMetadata.getStamina());
    }

}

package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IPokedexTest {

    IPokedex pokedex;
    IPokedexFactory pokedexFactory;
    IPokemonFactory pokemonFactory;
    IPokemonMetadataProvider pokemonMetadataProvider;
    Pokemon bulbi, aqua;

    @Before
    public void setUp() throws PokedexException{
        pokedexFactory = new PokedexFactory();
        pokemonFactory = new PokemonFactory();
        pokemonMetadataProvider = new PokemonMetadataProvider();
        pokedex = pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory);

        bulbi = pokemonFactory.createPokemon(0, 10, 56, 12, 2);
        aqua = pokemonFactory.createPokemon(133, 13, 49, 7, 1);

        pokedex.addPokemon(bulbi);
        pokedex.addPokemon(aqua);
    }

    @Test
    public void sizeTest(){
        assertEquals(2,pokedex.size());
    }

    @Test
    public void addPokemonTest() throws PokedexException{
        Pokemon aquaTest = pokemonFactory.createPokemon(133, 10, 50, 6, 1);
        assertEquals(2, pokedex.addPokemon(aquaTest));
    }

    @Test
    public void getPokemonTest() throws PokedexException {
        assertEquals(bulbi, pokedex.getPokemon(0));
    }

    @Test
    public void getPokemonsTest(){
        List<Pokemon> res = pokedex.getPokemons();

        assertEquals(bulbi, res.get(0));
        assertEquals(aqua, res.get(1));
    }

    @Test
    public void createPokemonTest() throws PokedexException{
        Pokemon aquaTest = pokemonFactory.createPokemon(133, 10, 50, 6, 1);
        double iv = aquaTest.getIv();
        assertEquals(133, aquaTest.getIndex());
        assertEquals(10, aquaTest.getCp());
        assertEquals(50, aquaTest.getHp());
        assertEquals(6, aquaTest.getDust());
        assertEquals(1, aquaTest.getCandy());
        assertEquals(186, aquaTest.getAttack());
        assertEquals(168,aquaTest.getDefense());
        assertEquals(260,aquaTest.getStamina());
        assertEquals(iv, aquaTest.getIv(), 0);

    }

    @Test
    public void getOrderPokemonsTest(){
        ArrayList<Pokemon> nameOrder = new ArrayList<>();
        ArrayList<Pokemon> cpOrder = new ArrayList<>();
        ArrayList<Pokemon> indexOrder = new ArrayList<>();

        nameOrder.add(aqua);
        nameOrder.add(bulbi);
        cpOrder.add(bulbi);
        cpOrder.add(aqua);
        indexOrder.add(bulbi);
        indexOrder.add(aqua);

        assertEquals(nameOrder, pokedex.getPokemons(PokemonComparators.NAME));
        assertEquals(cpOrder, pokedex.getPokemons(PokemonComparators.CP));
        assertEquals(indexOrder, pokedex.getPokemons(PokemonComparators.INDEX));
    }

    @Test
    public void getPokemonMetadataTest() throws PokedexException {
        PokemonMetadata pokemonMetadata = pokemonMetadataProvider.getPokemonMetadata(0);

        assertEquals("Bulbizarre",pokemonMetadata.getName());
        assertEquals(126,pokemonMetadata.getAttack());
        assertEquals(126,pokemonMetadata.getDefense());
        assertEquals(90,pokemonMetadata.getStamina());
        assertEquals(0,pokemonMetadata.getIndex());
    }

    @Test
    public void shouldRaisePokedexExceptionWhenBadID(){
        Exception exception = assertThrows(PokedexException.class, () -> pokedex.getPokemon(6));
        Exception exception2 = assertThrows(PokedexException.class, () -> pokedex.getPokemon(-1));

        String actualMsg = exception.getMessage();
        String actualMsg2 = exception2.getMessage();

        assertTrue(actualMsg.contains("ID "+ 6 +" introuvable"));
        assertTrue(actualMsg2.contains("ID "+ -1 +" introuvable"));
    }
}

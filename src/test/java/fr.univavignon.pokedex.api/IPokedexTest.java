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
    Pokemon bulbi, aquali;

    @Before
    public void setUp() throws PokedexException{
        pokedexFactory = new PokedexFactory();
        pokemonFactory = new PokemonFactory();
        pokemonMetadataProvider = new PokemonMetadataProvider();
        pokedex = pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory);

        bulbi = pokemonFactory.createPokemon(0, 10, 56, 12, 2);
        bulbi = new Pokemon(0,"Bulbizarre", 126, 126, 90, 10,56, 12, 2, 75);
        aquali = new Pokemon(133,"Aquali", 186,168,260,13,49,7,1,50);//pokemonFactory.createPokemon(133, 13, 49, 7, 1);

        pokedex.addPokemon(bulbi);
        pokedex.addPokemon(aquali);
    }

    @Test
    public void shouldReturnPokedexSize(){
        assertEquals(2,pokedex.size());
    }

    @Test
    public void shouldReturn2WhenAddingAquali() throws PokedexException{
        Pokemon aquaTest = pokemonFactory.createPokemon(133, 10, 50, 6, 1);
        assertEquals(2, pokedex.addPokemon(aquaTest));
    }

    @Test
    public void shouldReturnBulbizarreWhenIndexIs0() throws PokedexException {
        assertEquals(bulbi, pokedex.getPokemon(0));
    }

    @Test
    public void shouldReturnPokemonList(){
        List<Pokemon> res = pokedex.getPokemons();
        assertEquals(bulbi, res.get(0));
        assertEquals(aquali, res.get(1));
    }

    @Test
    public void shouldReturnAqualiWhenIndexIs133() throws PokedexException{
        Pokemon aquaTest = pokemonFactory.createPokemon(133, 13, 49, 7, 1);
        assertEquals(aquali.getIndex(), aquaTest.getIndex());
        assertEquals(aquali.getCp(), aquaTest.getCp());
        assertEquals(aquali.getHp(), aquaTest.getHp());
        assertEquals(aquali.getDust(), aquaTest.getDust());
        assertEquals(aquali.getCandy(), aquaTest.getCandy());
    }

    @Test
    public void shouldReturnOrderedListDependingOnComparator(){
        ArrayList<Pokemon> nameOrder = new ArrayList<>();
        ArrayList<Pokemon> cpOrder = new ArrayList<>();
        ArrayList<Pokemon> indexOrder = new ArrayList<>();

        nameOrder.add(aquali);
        nameOrder.add(bulbi);
        cpOrder.add(bulbi);
        cpOrder.add(aquali);
        indexOrder.add(bulbi);
        indexOrder.add(aquali);

        assertEquals(nameOrder, pokedex.getPokemons(PokemonComparators.NAME));
        assertEquals(cpOrder, pokedex.getPokemons(PokemonComparators.CP));
        assertEquals(indexOrder, pokedex.getPokemons(PokemonComparators.INDEX));
    }

    @Test
    public void shoulReturnBulbiMetadataWhenIndexIs0() throws PokedexException {
        PokemonMetadata pokemonMetadata = pokemonMetadataProvider.getPokemonMetadata(0);

        assertEquals(bulbi.getName(),pokemonMetadata.getName());
        assertEquals(bulbi.getAttack(),pokemonMetadata.getAttack());
        assertEquals(bulbi.getDefense(),pokemonMetadata.getDefense());
        assertEquals(bulbi.getStamina(),pokemonMetadata.getStamina());
        assertEquals(bulbi.getIndex(),pokemonMetadata.getIndex());
    }

    @Test
    public void shouldRaisePokedexExceptionWhenBadID(){
        Exception exception = assertThrows(PokedexException.class, () -> pokedex.getPokemon(6));
        Exception exception2 = assertThrows(PokedexException.class, () -> pokedex.getPokemon(-1));
        Exception metadataException = assertThrows(PokedexException.class, () -> pokemonMetadataProvider.getPokemonMetadata(-1));
        Exception exceptionWhenCreatePokemon = assertThrows(PokedexException.class, () -> pokemonFactory.createPokemon(5,0,0,0,0));

        String actualMsg = exception.getMessage();
        String actualMsg2 = exception2.getMessage();
        String actualMsg3 = metadataException.getMessage();
        String actualMsg4 = exceptionWhenCreatePokemon.getMessage();

        assertTrue(actualMsg.contains("ID "+ 6 +" introuvable"));
        assertTrue(actualMsg2.contains("ID "+ -1 +" introuvable"));
        assertTrue(actualMsg3.contains("PokemonMetadata introuvable pour l'index:" + -1));
        assertTrue(actualMsg4.contains("PokemonMetadata introuvable pour l'index:" + 5));
    }
}

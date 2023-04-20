package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IPokemonFactoryTest {

    Pokemon bulbi;
    Pokemon aqua;
    IPokemonFactory pokemonFactory = new PokemonFactory();
    PokemonComparators pkmCompName = PokemonComparators.NAME;
    PokemonComparators pkmCompCP = PokemonComparators.CP;
    PokemonComparators pkmCompId = PokemonComparators.INDEX;

    @Before
    public void setUp(){
        bulbi = new Pokemon(0, "Bulbizarre", 126, 126, 90, 50, 140, 56, 12, 2);
        aqua = new Pokemon(133, "Aquali", 186, 168, 260, 100, 90, 49, 7, 1);
    }

    @Test
    public void createPokemonTest() throws PokedexException{

        Pokemon bulbiTest = pokemonFactory.createPokemon(0, 50, 140, 56, 12);
        Pokemon bulbiBase = new Pokemon(0, "Bulbizarre", 126, 126, 90, 50, 140, 56, 12, bulbiTest.getIv());
        assertEquals(bulbiBase.getCp(), bulbiTest.getCp());
        assertEquals(bulbiBase.getHp(), bulbiTest.getHp());
        assertEquals(bulbiBase.getDust(), bulbiTest.getDust());
        assertEquals(bulbiBase.getCandy(), bulbiTest.getCandy());
        assertEquals(bulbiBase.getIndex(), bulbiTest.getIndex());
        assertEquals(bulbiBase.getAttack(), bulbiTest.getAttack());
        assertEquals(bulbiBase.getIv(), bulbiTest.getIv(), 0);
        assertEquals(bulbiBase.getDefense(), bulbiTest.getDefense());
    }

    @Test
    public void comparatorTestEqual(){
        Pokemon bulbiTest = new Pokemon(0, "Bulbizarre", 126, 126, 90, 50, 140, 56, 12, 2);
        assertEquals(0, pkmCompCP.compare(bulbi, bulbiTest));
        assertEquals(0,pkmCompId.compare(bulbi, bulbiTest));
        assertEquals(0, pkmCompName.compare(bulbi,bulbiTest));
    }

    @Test
    public void testCompareWhenFirstPkmIsLower(){
        assertTrue(pkmCompCP.compare(bulbi, aqua) < 0);
        assertTrue(pkmCompId.compare(bulbi, aqua) < 0);
        assertTrue(pkmCompName.compare(aqua, bulbi) < 0);
    }

    @Test
    public void testCompareWhenFirstPkmIsUpper(){
        assertTrue(pkmCompCP.compare(aqua, bulbi) > 0);
        assertTrue(pkmCompId.compare(aqua, bulbi) > 0);
        assertTrue(pkmCompName.compare(bulbi, aqua) > 0);
    }

    @Test
    public void getHpTest(){
        assertEquals(140, bulbi.getHp());
    }

    @Test
    public void getDustTest(){
        assertEquals(56, bulbi.getDust());
    }

    @Test
    public void getCandyTest(){
        assertEquals(12,bulbi.getCandy());
    }

    @Test
    public void getIvTest(){
        assertEquals(2,bulbi.getIv(),0);
    }

    @Test
    public void shouldRaisePokedexExceptionWhenBadIndex(){
        assertThrows(PokedexException.class, () -> pokemonFactory.createPokemon(5,0,0,0,0));
    }
}

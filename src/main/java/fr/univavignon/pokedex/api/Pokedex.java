package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Pokedex implements IPokedex{

    ArrayList<Pokemon> pokemonList;
    IPokemonMetadataProvider pokemonMetadataProvider;
    IPokemonFactory pokemonFactory;

    public Pokedex(IPokemonMetadataProvider pokemonMetadataProvider, IPokemonFactory pokemonFactory){
        this.pokemonList = new ArrayList<>();
        this.pokemonMetadataProvider = pokemonMetadataProvider;
        this.pokemonFactory = pokemonFactory;
    }

    @Override
    public int size() {
        return pokemonList.size();
    }

    @Override
    public int addPokemon(Pokemon pokemon) {
        pokemonList.add(pokemon);
        return pokemonList.indexOf(pokemon);
    }

    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        if(id < 0 || id >= pokemonList.size()){
            throw new PokedexException("ID " + id + " introuvable");
        }
        return pokemonList.get(id);
    }

    @Override
    public List<Pokemon> getPokemons() {
        return pokemonList;
    }

    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        pokemonList.sort(order);
        return pokemonList;
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        return  pokemonFactory.createPokemon(index, cp, hp,dust, candy);
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return pokemonMetadataProvider.getPokemonMetadata(index);
    }
}
